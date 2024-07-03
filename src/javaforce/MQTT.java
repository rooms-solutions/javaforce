package javaforce;

/** MQTT client
 *
 * https://docs.oasis-open.org/mqtt/mqtt/v5.0/os/mqtt-v5.0-os.html
 *
 * @author peter.quiring
 */

import java.io.*;
import java.net.*;
import java.util.*;

import javaforce.service.*;

import static javaforce.service.MQTTServer.*;

public class MQTT {
  private Socket s;
  private InputStream is;
  private OutputStream os;
  private MQTTEvents events;
  private Worker worker;

  public boolean connect(String host) {
    return connect(host, 1883);
  }

  public boolean connect(String host, int port) {
    disconnect();
    try {
      s = new Socket(host, port);
      is = s.getInputStream();
      os = s.getOutputStream();
      worker = new Worker(s);
      worker.start();
      return true;
    } catch (Exception e) {
      JFLog.log(e);
      return false;
    }
  }

  public void disconnect() {
    if (s != null) {
      try { s.close(); } catch (Exception e) {}
      s = null;
    }
    if (worker != null) {
      worker.cancel();
      worker = null;
    }
  }

  public void setListener(MQTTEvents events) {
    this.events = events;
  }

  private int calcPacketLength(boolean has_id, int topic_length, int msg_length) {
    //does NOT include the header or length itself
    int length = 0;
    if (has_id) {
      length += 2;  //id
    }
    if (topic_length > 0) {
      length += 2;  //short length;
      length += topic_length;
    }
    //TODO : has_id2 ???
    if (msg_length > 0) {
      length += msg_length;
    }
    return length;
  }

  private short id = 0x0001;

  public void publish(String topic, String msg) {
    byte[] topic_bytes = topic.getBytes();
    int topic_length = topic_bytes.length;
    byte[] msg_bytes = msg.getBytes();
    int msg_length = msg_bytes.length;
    int length = calcPacketLength(true, topic_length, msg_length);
    byte[] packet = new byte[length];
    packet[0] = (byte)((CMD_PUBLISH << 4) | QOS_1);
    setPacketLength(packet);
    int pos = 1 + getLengthBytes(length);
    setPacketID(packet, pos, id++);
    pos += 2;
    setTopicLength(packet, pos, (short)topic_length);
    pos += 2;
    System.arraycopy(topic_bytes, 0, packet, pos, topic_length);
    pos += topic_length;
    System.arraycopy(msg_bytes, 0, packet, pos, msg_length);
    pos += msg_length;
    try {
      os.write(packet);
    } catch (Exception e) {
      JFLog.log(e);
    }
  }

  public void subscribe(String topic) {
    byte[] topic_bytes = topic.getBytes();
    int topic_length = topic_bytes.length;
    int length = calcPacketLength(true, topic_length, 0);
    byte[] packet = new byte[length];
    packet[0] = (byte)(CMD_SUBSCRIBE << 4);
    setPacketLength(packet);
    int pos = 1 + getLengthBytes(length);
    setPacketID(packet, pos, id++);
    pos += 2;
    setTopicLength(packet, pos, (short)topic_length);
    pos += 2;
    System.arraycopy(topic_bytes, 0, packet, pos, topic_length);
    pos += topic_length;
    try {
      os.write(packet);
    } catch (Exception e) {
      JFLog.log(e);
    }
  }

  public void unsubscribe(String topic) {
    byte[] topic_bytes = topic.getBytes();
    int topic_length = topic_bytes.length;
    int length = calcPacketLength(true, topic_length, 0);
    byte[] packet = new byte[length];
    packet[0] = (byte)(CMD_UNSUBSCRIBE << 4);
    setPacketLength(packet);
    int pos = 1 + getLengthBytes(length);
    setPacketID(packet, pos, id++);
    pos += 2;
    setTopicLength(packet, pos, (short)topic_length);
    pos += 2;
    System.arraycopy(topic_bytes, 0, packet, pos, topic_length);
    pos += topic_length;
    try {
      os.write(packet);
    } catch (Exception e) {
      JFLog.log(e);
    }
  }

  private static int bufsiz = 4096;

  private int getPacketLength(byte[] data, int length) {
    int multi = 1;
    int value = 0;
    int pos = 1;
    int next;
    do {
      if (pos >= length) return -1;
      next = data[pos++] & 0xff;
      value += (next & 0x7f) * multi;
      multi *= 0x80;
    } while (next >= 0x80);
    return value;
  }

  private int getTopicLength(byte[] data, int topicPosition) {
    return BE.getuint16(data, topicPosition);
  }

  private short getPacketID(byte[] data, int idPosition) {
    return (short)BE.getuint16(data, idPosition);
  }

  private void setPacketLength(byte[] packet) {
    int value = packet.length - 2;
    int pos = 1;
    byte ebyte;
    do {
      ebyte = (byte)(value % 0x80);
      value /= 0x80;
      if (value > 0) {
        ebyte |= 0x80;
      }
      packet[pos++] = ebyte;
    } while (value > 0);
  }

  private void setPacketID(byte[] data, int offset, short id) {
    BE.setuint16(data, offset, id);
  }

  private void setTopicLength(byte[] data, int offset, short length) {
    BE.setuint16(data, offset, length);
  }

  private int getLengthBytes(int length) {
    if (length <= 0x7f) return 1;
    if (length <= 0x3ff) return 2;
    if (length <= 0x1fffff) return 3;
    if (length <= 0xfffffff) return 4;
    return -1;
  }

  private class Worker extends Thread {
    private boolean active = true;
    public Socket s;
    public InputStream is;
    public OutputStream os;
    public String ip;
    public Worker(Socket s) {
      this.s = s;
    }
    public void run() {
      try {
        is = s.getInputStream();
        os = s.getOutputStream();
        ip = s.getInetAddress().getHostAddress();
        if (debug) JFLog.log("connect:" + ip);
        byte[] buf = new byte[bufsiz];
        while (active) {
          int totalRead = 0;
          int packetLength = -1;  //excluding header + length fields
          int totalLength = -1;  //total packet length
          int read;
          Arrays.fill(buf, (byte)0);
          while (active) {
            if (packetLength == -1) {
              read = is.read(buf, totalRead, 1);
            } else {
              read = is.read(buf, totalRead, totalLength - totalRead);
            }
            if (debug) JFLog.log("read=" + read);
            if (read == -1) throw new Exception("bad read");
            totalRead += read;
            if (totalRead < 2) continue;
            if (packetLength == -1) {
              packetLength = getPacketLength(buf, totalRead);
              if (packetLength != -1) {
                totalLength = 1 + getLengthBytes(packetLength) + packetLength;
                if (debug) JFLog.log("totalLength=" + totalLength);
              }
            }
            if (packetLength == -1) continue;
            if (totalRead < totalLength) continue;
            try {
              process(buf, totalLength, packetLength);
            } catch (Exception e) {
              JFLog.log(e);
            }
            break;
          }
        }
      } catch (Exception e) {
        JFLog.log(e);
      }
      if (debug) JFLog.log("disconnect:" + ip);
    }
    private void process(byte[] packet, int totalLength, int packetLength) throws Exception {
      byte[] reply = null;
      byte cmd = (byte)((packet[0] & 0xf0) >> 4);
      short id;
      int idPosition;
      int topicPosition;
      int topicLength;
      String topic;
      int msgPosition;
      int msgLength;
      String msg;
      if (debug) JFLog.log("cmd=" + cmd);
      switch (cmd) {
        case CMD_CONNECT:
          reply = new byte[4];
          //reply = header , size , ack_flags, return_code=0
          reply[0] = (byte)(CMD_CONNECT_ACK << 4);
          setPacketLength(reply);
          break;
        case CMD_PUBLISH: {
          //header, size, topic, id, msg
          topicPosition = 1 + getLengthBytes(packetLength);
          topicLength = getTopicLength(packet, topicPosition);
          if (debug) JFLog.log("topic=" + topicPosition + "/" + topicLength);
          topic = new String(packet, topicPosition + 2, topicLength);
          idPosition = topicPosition + 2 + topicLength;
          id = getPacketID(packet, idPosition);
          if (debug) JFLog.log("id=" + id);
          msgPosition = idPosition + 2;
          msgLength = totalLength - msgPosition;
          if (debug) JFLog.log("msg=" + msgPosition + "/" + msgLength);
          msg = new String(packet, msgPosition, msgLength);
          if (debug_msg) JFLog.log("PUBLISH:" + ip + ":" + topic + ":" + msg + "!");
          reply = new byte[4];
          reply[0] = (byte)(CMD_PUBLISH_ACK << 4);
          //reply = header , size , id_hi, id_lo
          setPacketLength(reply);
          setPacketID(reply, 2, id);
          if (events != null) {
            events.message(topic, msg);
          }
          break;
        }
        case CMD_PUBLISH_ACK:
          //???
          break;
        case CMD_PUBLISH_REC:
          reply = new byte[4];
          reply[0] = (byte)(CMD_PUBLISH_REL << 4);
          setPacketLength(reply);
          id = getPacketID(packet, 2);
          setPacketID(reply, 2, id);
          break;
        case CMD_PUBLISH_REL:
          //???
          break;
        case CMD_PUBLISH_CMP:
          //???
          break;
        case CMD_PING:
          reply = new byte[2];
          reply[0] = (byte)(CMD_PONG << 4);
//          setPacketLength(reply);  //zero
          if (debug_msg) JFLog.log("PING:" + ip);
          break;
        case CMD_DISCONNECT:
          break;
      }
      if (reply != null) {
        send(reply);
      }
    }
    private void send(byte[] reply) throws Exception {
      os.write(reply);
    }
    public void cancel() {
      active = false;
      if (s != null) {
        try { s.close(); } catch (Exception e) {}
        s = null;
      }
    }
  }
}
