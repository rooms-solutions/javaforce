package service;

/** MediaServer
 *
 * Serves recorded files to a viewer.
 *
 * @author peter.quiring
 */

import java.io.*;

import javaforce.*;
import javaforce.media.*;
import javaforce.voip.*;

public class MediaServer {
  public Camera camera;

  public static boolean debug = false;

  private RTSPServer server;
  private RTSPSession sess;
  private long ts_start;
  private long ts_end;
  private long ts_delta;
  private boolean active;
  private boolean download;
  private Reader reader;

  public MediaServer(Camera camera, RTSPServer server, RTSPSession sess, String ts_start, String ts_end) {
    download = ts_end != null;
    this.camera = camera;
    active = true;
    this.ts_start = JF.atol(ts_start);
    this.ts_delta = System.currentTimeMillis() - this.ts_start;
    if (download) {
      this.ts_end = JF.atol(ts_end);
    }
    if (debug) JFLog.log("MediaServer:ts_start=" + ts_start + ",ts_delta=" + ts_delta + ",ts_end=" + ts_end);
    this.server = server;
    this.sess = sess;
  }

  public void start() {
    if (reader == null) {
      reader = new Reader();
      reader.start();
    }
  }

  public void stop() {
    if (reader != null) {
      reader.abort();
      reader = null;
    }
  }

  //reads media file and streams to player
  private class Reader extends Thread implements PacketReceiver {
    private Media media;
    private long ts_current;
    private int codec_id;
    private RTPCodec rtp_codec;
    public void run() {
      loadFile();
      while (active) {
        long now = System.currentTimeMillis();
        Packet frame = media.readFrame();
        if (frame == null) {
          closeFile();
          loadFile();
          continue;
        }
        JFLog.log("frame.ts=" + frame.ts);
        if (download) {
          if (frame.ts >= ts_end) {
            abort();
            server.set_parameter(sess, sess.uri, new String[] {"download: complete"});
            break;
          }
        } else {
          long delay = frame.ts - (now - ts_delta);
          delay -= 5;
          if (debug) JFLog.log("delay=" + delay);
          if (delay > 3 && delay < 5000) {
            JF.sleep((int)delay);
          }
        }
        //encode codec packet into RTP packets
        //TODO : get RTP codec ID ??? 96 ???
        rtp_codec.encode(frame.data, frame.offset, frame.length, 0, 0, 96, this);
      }
      if (media != null) {
        closeFile();
      }
    }
    private void loadFile() {
      if (media != null) {
        closeFile();
      }
      //load file @ ts_current
      String filename;
      File file;
      boolean exists;
      long now = System.currentTimeMillis();
      do {
        if (debug) JFLog.log("MediaServer:ts_delta=" + ts_delta);
        ts_current = now - ts_delta;
        long secs = ts_current % (60 * 1000);
        long round = 0;
        if (secs < (30 * 1000)) {
          round = 15 * 1000;
        }
        filename = DVRService.getRecordingFilename(camera.name, ts_current + round);
        file = new File(filename);
        exists = file.exists();
        if (!exists) {
          ts_delta -= 60 * 1000;  //try next minute
          if (ts_delta <= 0) {
            //skipped ahead to now
            server.set_parameter(sess, sess.uri, new String[] {"ts: 0"});
            abort();
            return;
          }
        }
      } while (!exists);
      media = new Media();
      if (!media.open(filename)) {
        if (debug) JFLog.log("Media not found:" + filename);
        abort();
        return;
      }
      codec_id = media.getStreamIDs()[0];
      switch (codec_id) {
        case MediaCoder.AV_CODEC_ID_H264: rtp_codec = new RTPH264(); break;
        case MediaCoder.AV_CODEC_ID_H265: rtp_codec = new RTPH265(); break;
      }
      media.seekTime(ts_current);
    }
    private void abort() {
      active = false;
      closeFile();
    }
    private void closeFile() {
      if (media != null) {
        media.close();
        media = null;
      }
    }

    public void onPacket(Packet frame) {
      sess.channel.writeRTP(frame.data, frame.offset, frame.length);
    }
  }
}
