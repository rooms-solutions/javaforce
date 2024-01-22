package javaforce.voip;

import java.util.*;
import javaforce.*;

/**
 * Handles the server end of a RTSP link.
 */

public class RTSPServer extends RTSP implements RTSPInterface, STUN.Listener {

  public enum NAT {None, STUN, TURN, ICE};

/*
  private String remotehost, remoteip;
  private InetAddress remoteaddr;
  private int remoteport;
*/

  private RTSPServerInterface iface;
  private String localhost;
  private int localport;
  private static NAT nat = NAT.None;
  private static boolean useNATOnPrivateNetwork = false;  //do not use NATing techniques on private network servers
  private static String stunHost, stunUser, stunPass;

  public Object userobj;  //user definable
  public int expires;  //expires

  private Object clientsLock = new Object();
  private HashMap<String, RTSPSession> clients = new HashMap<>();

  /**
   * Initialize this instance for RTSP.<br>
   *
   * @param localport is the UDP port to bind to locally.<br>
   * @param iface must be a RTSPServerInterface where RTSP events are dispatched
   * to.<br>
   */
  public boolean init(int localport, RTSPServerInterface iface, TransportType type) {
    this.iface = iface;
    this.localport = localport;
    try {
      JFLog.log(log, "localhost = " + localhost);
      if (nat == NAT.STUN || nat == NAT.ICE) {
        stopSTUN();
      }
      return super.init(localhost, localport, this, false, type);
    } catch (Exception e) {
      if (stun != null) stopSTUN();
      JFLog.log(log, e);
      return false;
    }
  }

  /**
   * Free all resources.
   */
  public void uninit() {
    super.uninit();
  }

  /**
   * Sets the type of NAT traversal type (global setting).
   */
  public static void setNAT(NAT nat, String host, String user, String pass) {
    RTSPServer.nat = nat;
    stunHost = host;
    stunUser = user;
    stunPass = pass;
  }

  /**
   * Disable/enable use of NAT traversal on private networks (global setting)
   * Private networks : 192.168.x.x , 10.x.x.x , 172.[16-31].x.x
   */

  public static void useNATOnPrivateNetwork(boolean state) {
    useNATOnPrivateNetwork = state;
  }

  /**
   * Determine if server is on a local private network.
   */
  public static boolean isPrivateNetwork(String ip) {
    //in case your PBX is on your own local IP network
    //see http://en.wikipedia.org/wiki/Private_network
    if (ip.startsWith("192.168.")) {
      return true;
    }
    if (ip.startsWith("10.")) {
      return true;
    }
    if (ip.startsWith("169.254.")) {
      return true;
    }
    for(int a=16;a<=31;a++) {
      if (ip.startsWith("172." + a + ".")) return true;
    }
    return false;
  }

  /**
   * Returns local RTP IP address.
   */
  public String getlocalRTPhost(RTSPSession sess) {
    if (RTP.useTURN)
      return RTP.getTurnIP();
    else
      return sess.localhost;
  }

  private STUN stun;
  private final Object stunLock = new Object();
  private volatile boolean stunWaiting = false;
  private volatile boolean stunResponse = false;
  private boolean startSTUN() {
    stun = new STUN();
    if (!stun.start(localport, stunHost, stunUser, stunPass, this)) return false;
    return true;
  }

  private void stopSTUN() {
    if (stun == null) return;
    stun.close();
    stun = null;
  }

  //interface STUN.Listener
  public void stunPublicIP(STUN stun, String ip, int port) {
    synchronized(stunLock) {
      if (stunWaiting) {
//        localhost = ip;
        stunResponse = true;
        stunLock.notify();
      }
    }
  }
  public void turnAlloc(STUN stun, String ip, int port, byte token[], int lifetime) {}
  public void turnBind(STUN stun) {}
  public void turnRefresh(STUN stun, int lifetime) {}
  public void turnFailed(STUN stun) {}
  public void turnData(STUN stun, byte data[], int offset, int length, short channel) {}

  /**
   * Issues a reply to the RTSP client.
   */
  public boolean reply(RTSPSession sess, int reply, String msg) {
    return reply(sess, reply, msg, false);
  }

  /**
   * Issues a reply to the RTSP client.
   */
  public boolean reply(RTSPSession sess, int reply, String msg, boolean sessid) {
    JFLog.log(log, "sessid:" + sess.id + "\r\nissue reply : " + reply);
    sess.reply = reply;
    StringBuilder req = new StringBuilder();
    req.append("RTSP/1.0" + reply + " " + msg + "\r\n");
    req.append("Cseq: " + sess.cseq + "\r\n");
    req.append("User-Agent: " + useragent + "\r\n");
    if (sess.epass != null) {
      req.append(sess.epass);
    }
    if (sess.transport != null) {
      req.append(sess.transport);
    }
    if (sess.accept != null) {
      req.append("Accept: " + sess.accept + "\r\n");
    }
    if (sessid) {
      req.append("Session: " + sess.id + "\r\n");
    }
    if (sess.sdp != null) {
      req.append("Content-Type: application/sdp\r\n");
      req.append("Content-Length: " + sess.sdp.length() + "\r\n\r\n");
      req.append(sess.sdp);
    } else {
      req.append("Content-Length: 0\r\n\r\n");
    }
    return send(sess.remoteaddr, sess.remoteport, req.toString());
  }

  /**
   * Processes RTSP messages sent from clients.
   */
  public void packet(String msg[], String remoteip, int remoteport) {
    String remote = remoteip + ":" + remoteport;
    try {
      RTSPSession sess;
      synchronized (clientsLock) {
        sess = clients.get(remote);
        if (sess == null) {
          sess = new RTSPSession(localhost);
          clients.put(remote, sess);
        }
      }
      String cmd = null;
      if (remoteip.equals("127.0.0.1")) {
        remoteip = sess.localhost;
      }
      sess.remotehost = remoteip;
      sess.remoteport = remoteport;
      sess.remotecseq = getcseq(msg);
      String sid = getHeader("Session:", msg);
      if (sid != null) {
        int idx = sid.indexOf(';');
        if (idx != -1) {
          sid = sid.substring(0, idx);
        }
        sess.id = Long.valueOf(sid);
      }
      sess.headers = msg;

      int reply = getResponseType(msg);
      if (reply != -1) {
        JFLog.log(log, "id:" + sess.id + "\r\nreply=" + reply);
      } else {
        cmd = getRequest(msg);
        sess.uri = getURI(msg);
        JFLog.log(log, "id:" + sess.id + "\r\nrequest=" + cmd);
      }
      switch (reply) {
        case -1:
          //request/cmd
          switch (cmd) {
            case "OPTIONS":
              iface.onOptions(this);
              break;
            case "DESCRIBE":
              iface.onDescribe(this);
              break;
            case "SETUP":
              iface.onSetup(this);
              break;
            case "PLAY":
              iface.onPlay(this);
              break;
            case "TEARDOWN":
              iface.onTeardown(this);
              break;
            case "GET_PARAMETER":
              //just echo back garbage - used primarily as a keep alive
              break;
          }
          break;
      }
    } catch (Exception e) {
      JFLog.log(log, e);
    }
  }
}
