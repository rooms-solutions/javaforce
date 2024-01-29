package javaforce.utils;

/** Tests Camera function
 *
 * @author pquiring
 *
 * Created : Jun 9, 2014
 */

import java.io.*;
import java.util.*;

import javaforce.*;
import javaforce.awt.*;
import javaforce.jni.*;
import javaforce.media.*;
import javaforce.webui.*;
import javaforce.webui.event.*;

public class TestCamera extends javax.swing.JFrame implements WebUIHandler, MediaIO {

  /**
   * Creates new form TestCamera
   */
  public TestCamera() {
    JFNative.load();
    if (!MediaCoder.init()) {
      JFAWT.showError("Error", "FFmpeg init failed");
      System.exit(1);
    }
    initComponents();
    pack();
    listCameras();
    new WebUIServer().start(this, 8080, false);
    encoder_dash = new MediaEncoder();
    encoder_dash.setProfileLevel(MediaCoder.PROFILE_MAIN);
    encoder_h264 = new MediaEncoder();
    encoder_h264.setProfileLevel(MediaCoder.PROFILE_MAIN);
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    start = new javax.swing.JButton();
    stop = new javax.swing.JButton();
    preview = new javax.swing.JLabel();
    webView = new javax.swing.JButton();
    cameraList = new javax.swing.JComboBox<>();
    transcodeBox = new javax.swing.JCheckBox();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("Camera Test");

    start.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
    start.setText("Start");
    start.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        startActionPerformed(evt);
      }
    });

    stop.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
    stop.setText("Stop");
    stop.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        stopActionPerformed(evt);
      }
    });

    webView.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
    webView.setText("Web Stream");
    webView.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        webViewActionPerformed(evt);
      }
    });

    cameraList.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

    transcodeBox.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
    transcodeBox.setText("transcode h264 -> dash");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(preview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addGroup(layout.createSequentialGroup()
            .addComponent(start, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(stop, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(cameraList, 0, 370, Short.MAX_VALUE)
              .addComponent(transcodeBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(webView, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
          .addComponent(start, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
          .addComponent(webView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(stop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addGroup(layout.createSequentialGroup()
            .addComponent(cameraList, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(transcodeBox)))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(preview, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
        .addContainerGap())
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void startActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startActionPerformed
    setState(false);
    transcode = transcodeBox.isSelected();
    camera = new Camera();
    if (!camera.init()) {
      JFAWT.showError("Error", "Camera init failed");
      return;
    }
    String[] devices = camera.listDevices();
    if (devices == null || devices.length == 0) {
      camera.uninit();
      JFAWT.showError("Error", "No camera found");
    }
    int camIdx = cameraList.getSelectedIndex();
    if (camIdx >= devices.length) {
      camIdx = 0;
    }
    JFLog.log("camera=" + devices[camIdx]);
    for(int a=0;a<3;a++) {JFLog.log(" ------------------------------- ");}
    encoder_dash.start(this, 640, 480, 10, -1, -1, "dash", true, false);
    if (transcode) {
      for(int a=0;a<3;a++) {JFLog.log(" ------------------------------- ");}
      encoder_h264.start(new MediaIO() {
        public int read(MediaCoder coder, byte[] data) {
          return 0;
        }
        public int write(MediaCoder coder, byte[] data) {
          JFLog.log("h264.write:" + data.length);
          encoder_dash.addVideoEncodedTS(data, 0, data.length, false, encoder_h264.getLastDTS(), encoder_h264.getLastPTS());
          encoder_dash.flush();
          return data.length;
        }
        public long seek(MediaCoder coder, long pos, int how) {
          return 0;
        }
      }, 640, 480, 10, -1, -1, "h264", true, false);
    }
    for(int a=0;a<3;a++) {JFLog.log(" ------------------------------- ");}
    camera.start(camIdx, 640, 480);
    width = camera.getWidth();
    height = camera.getHeight();
    timer = new Timer();
    timer.scheduleAtFixedRate(new TimerTask() {
      public void run() {
        java.awt.EventQueue.invokeLater(new Runnable() {public void run() {
          int[] px = camera.getFrame();
          if (px == null) return;  //not ready
          if (img == null) {
            img = new JFImage(width, height);
          }
          System.arraycopy(px, 0, img.getBuffer(), 0, width * height);
          preview.setIcon(img);
          preview.repaint();
          JFLog.log("addFrame:" + frameCount++);
          if (transcode) {
            encoder_h264.addVideo(px);
            encoder_h264.flush();
          } else {
            encoder_dash.addVideo(px);
            encoder_dash.flush();
          }
          JFLog.log("============================");
        }});
      }
    }, 100, 100);
  }//GEN-LAST:event_startActionPerformed

  private void stopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopActionPerformed
    if (camera == null) {
      return;
    }
    timer.cancel();
    timer = null;
    camera.stop();
    camera = null;
    if (transcode) {
      encoder_h264.stop();
    }
    encoder_dash.stop();
    setState(true);
  }//GEN-LAST:event_stopActionPerformed

  private void webViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_webViewActionPerformed
    JFAWT.openURL("http://localhost:8080");
  }//GEN-LAST:event_webViewActionPerformed

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new TestCamera().setVisible(true);
      }
    });
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JComboBox<String> cameraList;
  private javax.swing.JLabel preview;
  private javax.swing.JButton start;
  private javax.swing.JButton stop;
  private javax.swing.JCheckBox transcodeBox;
  private javax.swing.JButton webView;
  // End of variables declaration//GEN-END:variables

  private Camera camera;
  private Timer timer;
  private int frameCount = 0;
  private int encoderCount = 1;
  private int fps = 10;
  private JFImage img;
  private int width, height;
  private MediaEncoder encoder_dash;
  private MediaEncoder encoder_h264;
  private WebUIClient client;
  private Video video;
  private Video video_capture;
  private byte[] init_segment;
  private boolean transcode;

  public void listCameras() {
    camera = new Camera();
    if (!camera.init()) {
      JFAWT.showError("Error", "Camera init failed");
      return;
    }
    String[] devices = camera.listDevices();
    camera.uninit();
    if (devices == null || devices.length == 0) {
      JFAWT.showError("Error", "No camera found");
      return;
    }
    JFLog.log("device count=" + devices.length);
    for (int a = 0; a < devices.length; a++) {
      JFLog.log("device=" + devices[a]);
      cameraList.addItem(devices[a]);
    }
  }

  public String getCurrentTime() {
    double currentTime = encoderCount;
    currentTime /= fps;
    return String.format("%.3f", currentTime);
  }

  public void setState(boolean state) {
    transcodeBox.setEnabled(state);
    cameraList.setEnabled(state);
    webView.setEnabled(state);
  }

  //WebUIHandler

  public void clientConnected(WebUIClient client) {
    JFLog.log("clientConnected:" + client);
    this.client = client;
    client.setProperty("init-segment", "false");  //got init segment (ftyp)
    client.setProperty("start-segment", "false");  //got start of segment (styp) (else wait for next styp)
  }

  public void clientDisconnected(WebUIClient client) {
    JFLog.log("clientDisconnected:" + client);
    this.client = null;
    //System.exit(0);
  }

  public byte[] getResource(String url) {
    //TODO : return static images, etc needed by webpage
    return null;
  }

  public Panel getRootPanel(WebUIClient client) {
    Panel panel = new Panel();

    video = new Video();
    video.setWidth(640);
    video.setHeight(480);
    panel.add(video);

    video.addActionListener(new Action() {
      public void action(Component cmp) {
        video.setLiveSource(encoder_dash.getCodecMimeType("dash", true, false));
      }
    });

    video_capture = new Video();
    video_capture.setWidth(1024);
    video_capture.setHeight(720);
    panel.add(video_capture);

    video_capture.addActionListener(new Action() {
      public void action(Component cmp) {
        video_capture.setCapture(true, true);
        try {
          client.setOutputStream(new FileOutputStream("test.mkv"));
        } catch (Exception e) {}
      }
    });

    return panel;
  }

  //MediaIO

  public int read(MediaCoder coder, byte[] data) {
    JFLog.log("read:" + data.length);
    return -1;
  }

  /*
  struct mp4_header { int size; char type[4]; }
  types:
    ftyp / moov = init segment (describes video details)
    styp = start of segment
    moof / mdat = frame
  */

  public int write(MediaCoder coder, byte[] data) {
    JFLog.log("dash.write:" + data.length + ":" + getCurrentTime());
    boolean is_init = init_segment == null;
    boolean is_start_segment = false;
    if (data.length >= 8) {
      String type = new String(data, 4, 4);
      if (type.equals("styp")) {
        is_start_segment = true;
      }
    }
    if (init_segment == null) {
      init_segment = new byte[data.length];
      System.arraycopy(data, 0, init_segment, 0, data.length);
    }
    //send fragments
    if (client != null) {
      if (!video.isPlaying()) {
        return data.length;
      }
      JFLog.log("send frame:" + data.length);
      if (client.getProperty("init-segment").equals("false")) {
        if (is_init) {
          client.setProperty("init-segment", "true");
          client.setProperty("start-segment", "true");
        }
      }
      if (!is_start_segment) {
        if (client.getProperty("start-segment").equals("false")) {
          return data.length;
        }
      } else {
        if (client.getProperty("start-segment").equals("false")) {
          client.setProperty("init-segment", "true");
          client.sendDataEvent(init_segment, video.getID(), "media_add_buffer", null);
          client.setProperty("start-segment", "true");
          //because player has missed some segments it will need to seek to currentTime
          client.sendEvent(video.getID(), "media_seek", new String[] {"time=" + getCurrentTime()});
        }
      }
      client.sendDataEvent(data, video.getID(), "media_add_buffer", null);
      if (is_init) {
        client.setProperty("init-segment", "true");
      }
    }
    return data.length;
  }

  public long seek(MediaCoder coder, long pos, int type) {
    JFLog.log("seek:" + pos + "," + type);
    return pos;
  }
}
