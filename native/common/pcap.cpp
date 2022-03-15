/* pcap */

//pcap types

struct pcap_addr {
  struct pcap_addr *next;
  struct sockaddr *addr;      /* address */
  struct sockaddr *netmask;   /* netmask for that address */
  struct sockaddr *broadaddr; /* broadcast address for that address */
  struct sockaddr *dstaddr;   /* P2P destination address for that address */
};

typedef struct pcap_addr pcap_addr_t;

struct pcap_if {
  struct pcap_if *next;
  char *name;
  char *description;
  struct pcap_addr *addresses;
  int flags;
};

#define PCAP_IF_LOOPBACK                          0x00000001  /* interface is loopback */
#define PCAP_IF_UP                                0x00000002  /* interface is up */
#define PCAP_IF_RUNNING                           0x00000004  /* interface is running */
#define PCAP_IF_WIRELESS                          0x00000008  /* interface is wireless (*NOT* necessarily Wi-Fi!) */
#define PCAP_IF_CONNECTION_STATUS                 0x00000030  /* connection status: */
#define PCAP_IF_CONNECTION_STATUS_UNKNOWN         0x00000000  /* unknown */
#define PCAP_IF_CONNECTION_STATUS_CONNECTED       0x00000010  /* connected */
#define PCAP_IF_CONNECTION_STATUS_DISCONNECTED    0x00000020  /* disconnected */
#define PCAP_IF_CONNECTION_STATUS_NOT_APPLICABLE  0x00000030  /* not applicable */

#define PCAP_NETMASK_UNKNOWN	0xffffffff

typedef struct pcap_if pcap_if_t;

struct pcap;  //opaque type

typedef struct pcap pcap_t;

struct pcap_pkthdr {
  struct timeval ts;
  int caplen;
  int len;
};

typedef void (*pcap_handler)(u_char *, const struct pcap_pkthdr *, const u_char *);

struct bpf_program {
  int len;
  void* bf_insns;  //opaque
};

//functions

JF_LIB_HANDLE library;

int (*pcap_init)(int opts, char* errbuf);
int (*pcap_findalldevs)(pcap_if_t** devs, char *errbuf);
void (*pcap_freealldevs)(pcap_if_t* devs);
pcap_t* (*pcap_open_live)(const char* device, int snaplen, int promisc, int to_ms, char *errbuf);
void (*pcap_close)(pcap_t* handle);
int (*pcap_compile)(pcap_t *p, struct bpf_program *fp, const char *str, int optimize, int netmask);
int (*pcap_setfilter)(pcap_t *p, struct bpf_program *fp);
int (*pcap_dispatch)(pcap_t *p, int cnt, pcap_handler handler, const char* user);
int (*pcap_sendpacket)(pcap_t *p, void* ptr, int length);

//other pcap functions not implemented yet
//pcap_create();
//pcap_activate();
//pcap_lookupdev();
//pcap_lookupnet();
//pcap_next();
//pcap_next_ex();
//pcap_dispatch();
//pcap_loop();
//pcap_break_loop();
//pcap_dispatch();
//pcap_send_packet();

#define PCAP_ERRBUF_SIZE 256

JNIEXPORT jboolean JNICALL Java_javaforce_net_PacketCapture_ninit
  (JNIEnv *e, jclass cls, jstring lib)
{
  char err[PCAP_ERRBUF_SIZE];
  const char *clib = e->GetStringUTFChars(lib, NULL);

  library = JF_LIB_OPEN(clib JF_LIB_OPTS);
  if (library == NULL) {
    return JNI_FALSE;
  }

  getFunction(library, (void**)(&pcap_init), "pcap_init");
  getFunction(library, (void**)(&pcap_open_live), "pcap_open_live");
  getFunction(library, (void**)(&pcap_findalldevs), "pcap_findalldevs");
  getFunction(library, (void**)(&pcap_freealldevs), "pcap_freealldevs");
  getFunction(library, (void**)(&pcap_compile), "pcap_compile");
  getFunction(library, (void**)(&pcap_setfilter), "pcap_setfilter");
  getFunction(library, (void**)(&pcap_dispatch), "pcap_dispatch");
  getFunction(library, (void**)(&pcap_sendpacket), "pcap_sendpacket");

  e->ReleaseStringUTFChars(lib, clib);

  int ret = (*pcap_init)(0, err);
  if (ret != 0) {
    printf("Error:pcap_init:%d:%s\n", ret, err);
    library = NULL;
    return JNI_FALSE;
  }

  return JNI_TRUE;
}

#define UP_RUNNING (PCAP_IF_UP | PCAP_IF_RUNNING | PCAP_IF_CONNECTION_STATUS_CONNECTED)

JNIEXPORT jobjectArray JNICALL Java_javaforce_net_PacketCapture_listLocalInterfaces
  (JNIEnv *e, jobject obj)
{
  char err[PCAP_ERRBUF_SIZE];
  int list_count = 0;
  pcap_if_t *list_elements, *c;
  pcap_addr_t *addr;
  char name[256], ip[16];
  jobjectArray array;

  if (library == NULL) return NULL;

  int ret = (*pcap_findalldevs)(&list_elements, err);
  if (ret != 0) {
    printf("Error:pcap_findalldevs:%d:%s\n", ret, err);
    return NULL;
  }
  if (list_elements == NULL) {
    printf("Error:No interfaces found\n");
    return NULL;
  }

  c = list_elements;
  while (c != NULL) {
    if ((c->flags & UP_RUNNING) == UP_RUNNING && c->addresses != NULL) {
      list_count++;
    }
    c = c->next;
  }

  array = (jobjectArray)e->NewObjectArray(list_count,e->FindClass("java/lang/String"),e->NewStringUTF(""));

  c = list_elements;
  int idx = 0;
  while (c != NULL) {
    if ((c->flags & UP_RUNNING) == UP_RUNNING && c->addresses != NULL) {
      strcpy(name, c->name);
      addr = c->addresses;
      while (addr != NULL) {
        if (addr->addr->sa_family == AF_INET) {
          strcat(name, ",");
          sprintf(ip, "%d.%d.%d.%d", addr->addr->sa_data[2] & 0xff, addr->addr->sa_data[3] & 0xff, addr->addr->sa_data[4] & 0xff, addr->addr->sa_data[5] & 0xff);
          strcat(name, ip);
        }
        addr = addr->next;
      }
      e->SetObjectArrayElement(array,idx++,e->NewStringUTF(name));
    }
    c = c->next;
  }

  (*pcap_freealldevs)(list_elements);

  return array;
}

JNIEXPORT jlong JNICALL Java_javaforce_net_PacketCapture_start
  (JNIEnv *e, jobject obj, jstring dev)
{
  if (library == NULL) return 0;
  char err[PCAP_ERRBUF_SIZE];
  const char *cdev = e->GetStringUTFChars(dev, NULL);

  jlong handle = (jlong)(*pcap_open_live)(cdev, 100, 1, 10, err);

  e->ReleaseStringUTFChars(dev, cdev);

  if (handle == 0) {
    printf("Error:pcal_open_live:%s\n", err);
  }

  return handle;
}

JNIEXPORT void JNICALL Java_javaforce_net_PacketCapture_stop
  (JNIEnv *e, jobject obj, jlong handle)
{
  if (library == NULL) return;
  (*pcap_close)((pcap_t*)handle);
}

struct bpf_program cap_program;

JNIEXPORT jboolean JNICALL Java_javaforce_net_PacketCapture_compile
  (JNIEnv *e, jobject obj, jlong handle, jstring program)
{
  const char *cprogram = e->GetStringUTFChars(program, NULL);

  int ret = (*pcap_compile)((pcap_t*)handle, &cap_program, cprogram, 0, PCAP_NETMASK_UNKNOWN);

  e->ReleaseStringUTFChars(program, cprogram);

  if (ret != 0) {
    printf("Error:pcap_compile:%d\n", ret);
  } else {
    ret = (*pcap_setfilter)((pcap_t*)handle, &cap_program);
    if (ret != 0) {
      printf("Error:pcap_setfilter:%d\n", ret);
    }
  }

  return ret == 0;
}

static int cap_size = 0;
static jbyte* cap_buffer = NULL;

static void cap_callback(u_char *user, const struct pcap_pkthdr *pkt, const u_char *bytes)
{
  cap_size = pkt->caplen;
  cap_buffer = (jbyte*)bytes;
}

JNIEXPORT jbyteArray JNICALL Java_javaforce_net_PacketCapture_read
  (JNIEnv *e, jobject obj, jlong handle)
{
  cap_size = 0;
  cap_buffer = NULL;

  int cnt = (*pcap_dispatch)((pcap_t*)handle, 1, &cap_callback, NULL);

  if (cnt > 0 && cap_size > 0) {
    jbyteArray ba = e->NewByteArray(cap_size);

    e->SetByteArrayRegion(ba, 0, cap_size, (jbyte*)cap_buffer);

    return ba;
  } else {
    return NULL;
  }
}

JNIEXPORT jboolean JNICALL Java_javaforce_net_PacketCapture_write
  (JNIEnv *e, jobject obj, jlong handle, jbyteArray ba, jint offset, jint length)
{
  jboolean isCopy;
  jbyte *ba_ptr = (jbyte*)e->GetPrimitiveArrayCritical(ba, &isCopy);
  if (!shownCopyWarning && isCopy == JNI_TRUE) copyWarning();

  int ret = (*pcap_sendpacket)((pcap_t*)handle, ba_ptr + offset, length);
  if (ret != 0) {
    printf("Error:pcap_sendpacket:%d\n", ret);
  }

  e->ReleasePrimitiveArrayCritical(ba, ba_ptr, JNI_ABORT);

  return ret == 0;
}
