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

  private RTSPSession sess;
  private long ts_start;
  private long ts_end;
  private long ts_delta;
  private boolean active;
  private boolean download;
  private Reader reader;

  public MediaServer(Camera camera, RTSPSession sess, String ts_start, String ts_end) {
    download = ts_end != null;
    this.camera = camera;
    active = true;
    this.ts_start = JF.atol(ts_start);
    this.ts_delta = System.currentTimeMillis() - this.ts_start;
    if (download) {
      this.ts_end = JF.atol(ts_end);
    }
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
  private class Reader extends Thread {
    private Media media;
    private long ts_current;
    private long min_now;
    private long min_last;
    public void run() {
      min_last = 0;
      loadFile();
      while (active) {
        long now = System.currentTimeMillis();
        Media.Frame frame = media.readFrame();
        if (frame == null) {
          closeFile();
          loadFile();
          continue;
        }
        if (download) {
          if (frame.ts >= ts_end) {
            abort();
            sess.channel.writeRTCP("done".getBytes(), 0, 4);
          }
        } else {
          long delay = (now - ts_delta) - frame.ts;
          if (delay > 3) {
            JF.sleep((int)delay);
          }
        }
        sess.channel.writeRTP(frame.data, frame.offset, frame.length);
      }
      if (media != null) {
        closeFile();
      }
    }
    private void loadFile() {
      ts_current = System.currentTimeMillis() - ts_delta;
      if (media != null) {
        closeFile();
      }
      //load file @ ts_current
      min_now = ts_current % 60;
      if (min_now == min_last) {
        min_now++;
      }
      min_last = min_now;
      String filename = Paths.videoPath + "/" + camera.name + "/" + min_now + ".jfav";
      File file = new File(filename);
      if (!file.exists()) {
        abort();
        return;
      }
      media = new Media();
      if (!media.open(filename)) {
        abort();
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
  }
}
