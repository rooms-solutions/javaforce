package javaforce.voip;

/** PacketBuffer
 *
 * Combines RTP fragments into H264 or H265 packets.
 *
 */

import javaforce.*;

public class PacketBuffer {
  private static final int maxPacketsSize = 16 * 1024 * 1024;
  private static final int maxPackets = 256;

  /** PacketBuffer
   *
   * @param codecType = H264 or H265
   */
  public PacketBuffer(int codecType) {
    this.codecType = codecType;
    data = new byte[maxPacketsSize];
    nextFrame.data = new byte[maxPacketsSize];
    switch(codecType) {
      case CodecType.H264: h264 = new RTPH264(); break;
      case CodecType.H265: h265 = new RTPH265(); break;
    }
  }
  public byte[] data;
  private Packet nextFrame = new Packet();
  private RTPH264 h264;
  private RTPH265 h265;
  private int codecType;
  public int[] offset = new int[maxPackets];
  public int[] length = new int[maxPackets];
  public byte[] type = new byte[maxPackets];
  public int nextOffset;
  public int head, tail;
  public int log;
  public void setLog(int id) {
    this.log = id;
  }
  public void reset() {
    //TODO : need to lock this from consumer
    head = 0;
    tail = 0;
    nextOffset = 0;
  }
  private boolean calcOffset(int nextLength) {
    if (nextOffset + nextLength >= maxPacketsSize) {
      nextOffset = 0;
    }
    int next_head = head + 1;
    if (next_head == maxPackets) {
      next_head = 0;
    }
    if (next_head == tail) {
      JFLog.log(log, "Error : Buffer Overflow (# of packets exceeded)");
      reset();
      return false;
    }
    int _tail = tail;
    if (head == _tail) return true;  //empty
    int total_length = 0;
    while (_tail != head) {
      total_length += length[_tail];
      _tail++;
      if (_tail == maxPackets) _tail = 0;
    }
    if (total_length + nextLength > maxPacketsSize) {
      JFLog.log(log, "Error : Buffer Overflow (# of bytes exceeded)");
      reset();
      return false;
    }
    return true;
  }
  public void add(Packet packet) {
    if (!calcOffset(packet.length)) return;
    try {
      System.arraycopy(packet.data, packet.offset, data, nextOffset, packet.length);
    } catch (Exception e) {
      JFLog.log(log, "Error:arraycopy(src," + packet.offset + ",dst," + nextOffset + "," + packet.length + ")");
      JFLog.log(log, e);
      return;
    }
    offset[head] = nextOffset;
    length[head] = packet.length;
    switch (codecType) {
      case CodecType.H264: type[head] = RTPH264.get_nal_type(packet.data, packet.offset + 4); break;
      case CodecType.H265: type[head] = RTPH265.get_nal_type(packet.data, packet.offset + 4); break;
    }
    nextOffset += packet.length;
    int new_head = head + 1;
    if (new_head == maxPackets) new_head = 0;
    head = new_head;
  }
  public void removePacket() {
    if (tail == head) {
      JFLog.log(log, "Error:Packets Buffer underflow");
      return;
    }
    int new_tail = tail + 1;
    if (new_tail == maxPackets) new_tail = 0;
    tail = new_tail;
  }
  public void cleanPackets(boolean mark) {
    //only keep back to the last keyFrame (type 5)
    int key_frames = 0;
    for(int pos=tail;pos!=head;) {
      switch (codecType) {
        case CodecType.H264: if (h264.isKeyFrame(type[pos])) key_frames++; break;
        case CodecType.H265: if (h265.isKeyFrame(type[pos])) key_frames++; break;
      }
      pos++;
      if (pos == maxPackets) pos = 0;
    }
    if (key_frames <= 1) return;
    if (mark) {
      boolean i_frame = false;
      for(;tail!=head;) {
        switch (codecType) {
          case CodecType.H264: if (h264.isIFrame(type[head])) i_frame = true; else if (i_frame) return; break;
          case CodecType.H265: if (h265.isIFrame(type[head])) i_frame = true; else if (i_frame) return; break;
        }
        int new_tail = tail + 1;
        if (new_tail == maxPackets) new_tail = 0;
        tail = new_tail;
      }
    }
  }
  public boolean haveCompleteFrame() {
    for(int pos=tail;pos!=head;) {
      switch (codecType) {
        case CodecType.H264: if (h264.isFrame(type[pos])) return true; break;
        case CodecType.H265: if (h265.isFrame(type[pos])) return true; break;
      }
      pos++;
      if (pos == maxPackets) {
        pos = 0;
      }
    }
    return false;
  }
  public boolean isNextFrame_KeyFrame() {
    for(int pos=tail;pos!=head;) {
      switch (codecType) {
        case CodecType.H264: if (h264.isKeyFrame(type[pos])) return true; break;
        case CodecType.H265: if (h265.isKeyFrame(type[pos])) return true; break;
      }
      switch (codecType) {
        case CodecType.H264: if (h264.isIFrame(type[pos])) return false; break;
        case CodecType.H265: if (h265.isIFrame(type[pos])) return false; break;
      }
      pos++;
      if (pos == maxPackets) pos = 0;
    }
    return false;
  }
  public Packet getNextFrame() {
    next_frame_packets = 0;
    if (!haveCompleteFrame()) {
      JFLog.log(log, "Error : getNextFrame() called but don't have one ???");
      return null;
    }
    nextFrame.length = 0;
    for(int pos=tail;pos!=head;) {
      System.arraycopy(data, offset[pos], nextFrame.data, nextFrame.length, length[pos]);
      nextFrame.length += length[pos];
      next_frame_packets++;
      boolean br = false;
      switch (codecType) {
        case CodecType.H264: if (h264.isFrame(type[pos])) br = true; break;
        case CodecType.H265: if (h265.isFrame(type[pos])) br = true; break;
      }
      if (br) break;
      pos++;
      if (pos == maxPackets) pos = 0;
    }
    return nextFrame;
  }
  public void removeNextFrame() {
    while (next_frame_packets > 0) {
      int new_tail = tail + 1;
      if (new_tail == maxPackets) new_tail = 0;
      tail = new_tail;
      next_frame_packets--;
    }
  }
  public int next_frame_packets;
  public String toString() {
    return "Packets:tail=" + tail + ":head=" + head;
  }
}
