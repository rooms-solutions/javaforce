package jfnetboot;

/** TFTP Service.
 *
 * UDP Port 69
 *
 * @author pquiring
 */

import java.io.*;
import java.util.*;
import java.net.*;

import javaforce.*;

public class TFTP extends Thread {

  public static boolean debugMsgs = false;
  public static boolean debugException = true;

  /* Opcodes */
  private static final int oc_read = 1;
  private static final int oc_write = 2;
  private static final int oc_data = 3;
  private static final int oc_ack = 4;
  private static final int oc_error = 5;
  private static final int oc_optack = 6;

  /* Error codes */
  private static final int err_undefined = 0;
  private static final int err_not_found = 1;
  private static final int err_access_denied = 2;
  private static final int err_disk_full = 3;
  private static final int err_bad_opcode = 4;
  private static final int err_bad_tid = 5;
  private static final int err_already_exists = 6;
  private static final int err_unknown_user = 7;
  private static final int err_option_neg_failed = 8;

  private static Object next_port_lock = new Object();
  private static int next_port = 4096;

  public static class ImageFile {
    public String name;
    public byte[] data;
  }

  public class TFTPClient extends Thread {
    public String ip;
    public ImageFile imgfile;
    public int block;
    public int blocksize;
    public boolean done;
    public boolean tsize;
    public DatagramSocket cs;
    public boolean active;
    public int this_port;
    public int serial;

    public boolean init() {
      try {
        synchronized(next_port_lock) {
          this_port = next_port++;
          if (next_port == 8192) {
            next_port = 4096;
          }
        }
        cs = new DatagramSocket(this_port);
        return true;
      } catch (Exception e) {
        if (debugException) JFLog.log(e);
        return false;
      }
    }

    public void close() {
      if (debugMsgs) JFLog.log("TFTP:Client:close:" + this_port);
      active = false;
      if (cs != null) {
        cs.close();
        cs = null;
      }
    }

    public void run() {
      if (debugMsgs) JFLog.log("TFTP:Client:run:" + this_port + ":file=" + imgfile.name);
      active = true;
      try {
        while (active) {
          byte[] data = new byte[256];
          DatagramPacket p = new DatagramPacket(data, data.length);
          cs.receive(p);
          int opCode = BE.getuint16(data, 0);
//          if (debugMsgs) JFLog.log("TFTP:Client:opCode=" + opCode);
          try {
            switch (opCode) {
              case oc_ack:
                doAck(p);
                break;
              default:
                doError(p);
                break;
            }
          } catch (Exception e) {
            if (debugException) JFLog.log(e);
          }
        }
      } catch (Exception e) {
        if (debugException) JFLog.log(e);
      }
    }

    private void sendNextBlock(DatagramPacket src) {
      int toSend = blocksize;
      int offset = block * blocksize;
      int blocks = imgfile.data.length / blocksize;
      int partial = imgfile.data.length % blocksize;
      blocks++;
      if (block == blocks) {
        //client has acked last block
        synchronized(clients) {
          close();
          clients.remove(this);
          return;
        }
      }
      if (offset + blocksize > imgfile.data.length) {
        toSend = partial;
      }
      byte[] data = new byte[4 + toSend];
      BE.setuint16(data, 0, oc_data);
      BE.setuint16(data, 2, block+1);  //one based
      System.arraycopy(imgfile.data, offset, data, 4, toSend);
      DatagramPacket reply = new DatagramPacket(data, data.length, src.getAddress(), src.getPort());
      try {
        cs.send(reply);
      } catch (Exception e) {
        if (debugException) JFLog.log(e);
      }
    }

    private void doAck(DatagramPacket src) {
      byte[] data = src.getData();
      block = BE.getuint16(data, 2);
      sendNextBlock(src);
    }

    private void doError(DatagramPacket src) {
      synchronized(clients) {
        close();
        clients.remove(this);
      }
    }
  }

  private DatagramSocket s;
  private static boolean active;
  private ArrayList<TFTPClient> clients = new ArrayList<>();
  private ArrayList<ImageFile> files = new ArrayList<>();

  public void run() {
    active = true;
    JFLog.log("TFTP starting on port 69...");
    try {
      s = new DatagramSocket(69);
      while (active) {
        byte[] data = new byte[256];
        DatagramPacket p = new DatagramPacket(data, data.length);
        s.receive(p);
        //check if new request
        int opCode = BE.getuint16(data, 0);
//        if (debugMsgs) JFLog.log("TFTP:opCode=" + opCode);
        try {
          switch (opCode) {
            case oc_read:
              doRead(p);
              break;
          }
        } catch (Exception e) {
          if (debugException) JFLog.log(e);
        }
      }
    } catch (Exception e) {
      if (debugException) JFLog.log(e);
    }
  }

  public void cancel() {
    active = false;
    if (s != null) {
      s.close();
    }
  }

  private ImageFile readFile(int serial, String arch, String filename, String ip) {
    Client client = Clients.getClient(serial, arch);
    client.reinitCommand();
    client.setIP(ip);
    String full = client.getFileSystem().getBootPath() + "/" + filename;
    if (debugMsgs) JFLog.log("read file:" + full);
    File file = new File(full);
    if (!file.exists()) {
      if (debugMsgs) JFLog.log("file not found:" + full);
      return null;
    }
    try {
      FileInputStream fis = new FileInputStream(file);
      ImageFile imgfile = new ImageFile();
      imgfile.name = filename;
      imgfile.data = fis.readAllBytes();
      fis.close();
      return imgfile;
    } catch (Exception e) {
      if (debugException) JFLog.log(e);
      return null;
    }
  }

  private int index(byte[] data, int off, byte v) {
    for(int idx=off;idx<data.length;idx++) {
      if (data[idx] == v) return idx;
    }
    return -1;
  }

  private int index(byte[] data, int off, byte[] v) {
    int dlen = data.length;
    int vlen = v.length;
    int max = dlen - vlen - 1;
    for(int didx=off;didx<max;didx++) {
      boolean match = true;
      for(int vidx=0;vidx<vlen;vidx++) {
        if (data[didx+vidx] != v[vidx]) {match = false; break;}
      }
      if (match) return didx;
    }
    return -1;
  }

  private ImageFile getFile(int serial, String arch, String filename, String ip) {
    synchronized(files) {
      for(ImageFile file : files) {
        if (file.name.equals(filename)) return file;
      }
      ImageFile file = readFile(serial, arch, filename, ip);
      if (file == null) {
        if (debugMsgs) JFLog.log("TFTP:file not found:" + filename);
        return null;
      }
      files.add(file);
      return file;
    }
  }

  private void doRead(DatagramPacket src) {
    /* Read = 0x0001 filename\0 octet\0 tsize\00\0 blocksize\01024\0 */
    byte[] data = src.getData();
    int idx = index(data, 2, (byte)0);
    if (idx == -1) {
      if (debugMsgs) JFLog.log("TFTP:Invalid read cmd");
      return;
    }
    String filename = new String(data, 2, idx - 2);
    String arch;
    if (filename.startsWith("boot/")) {
      //Intel Boot Agent V1.5.x
      String remote_ip = src.getAddress().getHostAddress().toString();
      String mac = JF.getRemoteMAC(remote_ip);
      //serial # is lower 32bits of MAC address
      String serial = mac.substring(4);
      //remove boot/
      filename = filename.substring(5);
      if (filename.startsWith("pxelinux.cfg")) {
        //strip off UUID
        filename = "pxelinux.cfg";
      }
      filename = serial + "/" + filename;
      arch = "x86";
    } else if (filename.endsWith("grub.cfg")) {
      //UEFI grub.cfg
      String remote_ip = src.getAddress().getHostAddress().toString();
      String mac = JF.getRemoteMAC(remote_ip);
      //serial # is lower 32bits of MAC address
      String serial = mac.substring(4);
      filename = serial + "/grub.cfg";
      arch = "x86";
    } else {
      //Raspberry PI4
      arch = "arm";
    }
    if (debugMsgs) JFLog.log("TFTP:filename=" + filename);
    int fidx = filename.indexOf("/");
    int lidx = filename.lastIndexOf("/");
    if (fidx == -1 || fidx != lidx || filename.startsWith("boot")) {
      if (debugMsgs) JFLog.log("TFTP:Error:File not found:" + filename);
      sendErrorNotFound(src);
      return;
    }
    int serial = -1;
    serial = Long.valueOf(filename.substring(0, fidx), 16).intValue();
    filename = filename.substring(lidx+1);
    if (serial == -1) {
      JFLog.log("TFTP:Error:Serial not detected:" + filename);
      sendErrorNotFound(src);
      return;
    }
    ImageFile imgfile = null;
    if (filename.equals("cmdline.txt")) {
      //RPi
      InetAddress server_address = JF.getLocalAddress(src);
      String server_ip = server_address.getHostAddress();
      Client client = Clients.getClient(serial, arch);
      String cmdline = "console=tty0 root=/dev/nfs nfsroot=" + server_ip + ":/" + String.format("%08x", serial) + "/arm,vers=3,tcp rw ip=dhcp rootwait elevator=deadline " + client.opts;
      imgfile = new ImageFile();
      imgfile.data = cmdline.getBytes();
      imgfile.name = "cmdline.txt";
/*
    } else if (filename.equals("config.txt")) {
      //RPi
      //need to modify hardware config
      //see https://www.raspberrypi.org/forums/viewtopic.php?t=276264
      imgfile = getFile(serial, filename);
      String lns[] = new String(imgfile.data).split("\n");
      StringBuilder sb = new StringBuilder();
      for(String ln : lns) {
        if (ln.startsWith("enable_uart")) continue;
        sb.append(ln);
        sb.append("\n");
      }
      sb.append("dtparam=sd_poll_once\n");
      imgfile.data = sb.toString().getBytes();
      //none of this makes a difference
*/
    } else if (filename.equals("pxelinux.cfg")) {
      //x86
      InetAddress server_address = JF.getLocalAddress(src);
      String server_ip = server_address.getHostAddress();
      StringBuilder sb = new StringBuilder();
      Client client = Clients.getClient(serial, arch);
      sb.append("DEFAULT menu.c32\n");
      sb.append("PROMPT 1\n");
      sb.append("TIMEOUT 1\n");
      sb.append("LABEL PXE\n");
      sb.append("  MENU LABEL PXE\n");
      sb.append("  KERNEL vmlinuz\n");
      sb.append("  APPEND initrd=initrd.img root=/dev/nfs nfsroot=" + server_ip + ":/" + String.format("%08x", serial) + "/x86,vers=3,tcp rw ip=dhcp rootwait elevator=deadline " + client.opts + "\n");
      imgfile = new ImageFile();
      imgfile.data = sb.toString().getBytes();
      imgfile.name = "pxelinux.cfg";
    } else if (filename.equals("grub.cfg")) {
      InetAddress server_address = JF.getLocalAddress(src);
      String server_ip = server_address.getHostAddress();
      StringBuilder sb = new StringBuilder();
      Client client = Clients.getClient(serial, arch);
      //generate grub.cfg
      sb.append("set default=\"0\"\n");
      sb.append("set timeout=3\n");
      sb.append("menuentry 'boot' {\n");
      sb.append("  linux boot/vmlinuz root=/dev/nfs nfsroot=" + server_ip + ":/" + String.format("%08x", serial) + "/x86,vers=3,tcp rw ip=dhcp rootwait elevator=deadline " + client.opts + "\n");
      sb.append("  initrd boot/initrd.img\n");
      sb.append("}\n");
      imgfile = new ImageFile();
      imgfile.data = sb.toString().getBytes();
      imgfile.name = "grub.cfg";
    } else {
      imgfile = getFile(serial, arch, filename, src.getAddress().getHostAddress());
    }
    if (imgfile == null) {
      if (debugMsgs) JFLog.log("TFTP:Error:File not found:" + filename);
      sendErrorNotFound(src);
      return;
    }
    int blocksize = 512;
    int blocksize_idx = index(data, idx, "blksize".getBytes());
    if (blocksize_idx != -1) {
      int blocksize_null = index(data, blocksize_idx + 8, (byte)0);
      if (blocksize_null != -1) {
        blocksize = Integer.valueOf(new String(data, blocksize_idx + 8, blocksize_null - blocksize_idx - 8));
        if (debugMsgs) JFLog.log("TFTP:blksize=" + blocksize);
      }
    }
    TFTPClient c = new TFTPClient();
    c.ip = src.getAddress().getHostAddress();
    c.imgfile = imgfile;
    c.blocksize = blocksize;
    c.tsize = index(data, idx, "tsize".getBytes()) != -1;
    c.block = 1;
    c.serial = serial;
    if (!c.init()) {
      return;
    }
    synchronized(clients) {
      clients.add(c);
    }
    sendOptAck(c, src);
    c.start();
  }

  private void sendOptAck(TFTPClient c, DatagramPacket src) {
    String blksize = Integer.toString(c.blocksize);
    String tsize = Integer.toString(c.imgfile.data.length);

    int len = 2 + 7 + 1 + blksize.length() + 1;
    if (c.tsize) {
      len += 5 + 1 + tsize.length() + 1;
    }
    byte[] data = new byte[len];

    int idx = 0;

    BE.setuint16(data, idx, oc_optack);
    idx += 2;

    if (c.tsize) {
      BE.setString(data, idx, 5, "tsize");
      idx += 5;

      data[idx] = 0;
      idx++;

      BE.setString(data, idx, tsize.length(), tsize);
      idx += tsize.length();

      data[idx] = 0;
      idx++;
    }

    BE.setString(data, idx, 7, "blksize");
    idx += 7;

    data[idx] = 0;
    idx++;

    BE.setString(data, idx, blksize.length(), blksize);
    idx += blksize.length();

    data[idx] = 0;
    idx++;

    DatagramPacket reply = new DatagramPacket(data, data.length, src.getAddress(), src.getPort());
    try {
      c.cs.send(reply);
    } catch (Exception e) {
      if (debugException) JFLog.log(e);
    }
  }

  public void sendErrorNotFound(DatagramPacket src) {
    byte[] data = new byte[4 + 3 + 1];
    BE.setuint16(data, 0, oc_error);
    BE.setuint16(data, 2, err_not_found);
    BE.setString(data, 4, 3, "404");
    DatagramPacket reply = new DatagramPacket(data, data.length, src.getAddress(), src.getPort());
    reply.setLength(data.length);
    try {
      s.send(reply);
    } catch (Exception e) {
      if (debugException) JFLog.log(e);
    }
  }
}
