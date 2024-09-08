package viewer;

/**
 * Created : Mar 25, 2012
 *
 * @author pquiring
 */

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import javax.swing.*;

import javaforce.*;
import javaforce.awt.*;
import javaforce.media.*;
import javaforce.voip.*;

public class ViewerApp extends javax.swing.JFrame {

  /**
   * Creates new form MediaApp
   */
  public ViewerApp() {
    RTSPURL.register();
    initComponents();
    JFImage icon = new JFImage();
    icon.loadPNG(this.getClass().getClassLoader().getResourceAsStream("jfdvr.png"));
    setIconImage(icon.getImage());
    cameraicon = new JFImage();
    cameraicon.loadPNG(this.getClass().getClassLoader().getResourceAsStream("camera.png"));
    self = this;
    root = getContentPane();
    setPosition();
    setTitle("jfDVR Viewer/" + service.ConfigService.version + " (F1 = Help | F2 = Select View)");
    RTP.setPortRange(40000, 50000);
    Config.randomPort();
    for(String arg : args) {
      if (arg.startsWith("rtsp://")) {
        if (viewer == null) {
          viewer = new Viewer();
          try {
            Config.createURL(arg);
            viewer.play(Config.url);
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      } else if (arg.equals("debug")) {
        debug = true;
        VideoPanel.debug = true;
      } else {
        if (selector == null) {
          selector = new SelectView();
          if (args.length > 0) {
            selector.setServer(arg);
          }
          setPanel(selector);
        }
      }
    }
    setExtendedState(Frame.MAXIMIZED_BOTH);
    fullscreen = true;
    ToolTipManager.sharedInstance().setInitialDelay(100);  //force faster tool tips
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("jfDVR Viewer");
    setPreferredSize(new java.awt.Dimension(1500, 720));
    addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowClosed(java.awt.event.WindowEvent evt) {
        formWindowClosed(evt);
      }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 1250, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 801, Short.MAX_VALUE)
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
    if (selector != null) {
      selector.close();
    }
    if (viewer != null) {
      viewer.stop(true);
    }
  }//GEN-LAST:event_formWindowClosed

  /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {
    MediaCoder.init();
    ViewerApp.args = args;
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new ViewerApp().setVisible(true);
      }
    });
  }
  // Variables declaration - do not modify//GEN-BEGIN:variables
  // End of variables declaration//GEN-END:variables

  public static String[] args;
  public static Viewer viewer;
  public static SelectView selector;
  public static ViewerApp self;
  public static Container root;
  public static JFImage cameraicon;
  public static boolean debug;
  public boolean fullscreen;

  private void setPosition() {
    Dimension d = getSize();
    Rectangle s = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
    if ((d.width > s.width) || (d.height > s.height)) {
      if (d.width > s.width) d.width = s.width;
      if (d.height > s.height) d.height = s.height;
      setSize(d);
    }
    setLocation(s.width/2 - d.width/2, s.height/2 - d.height/2);
  }

  public boolean fullScreen = false;

  public void toggleFullScreen() {
    GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    if (fullScreen) {
      gd.setFullScreenWindow(null);
    } else {
      gd.setFullScreenWindow(this);
    }
    fullScreen = !fullScreen;
  }

  public boolean isFullScreen() {
    return fullScreen;
  }

  public static void stopViewer() {
    if (viewer == null) return;
    viewer.stop(true);
    viewer = null;
  }

  public static void showHelp() {
    JFAWT.showMessage("Help",
      "jfDVR/" + service.ConfigService.version + "\n\n" +
      "F1 = Help\n" +
      "F2 = Select View\n" +
      "F5 = Refresh\n" +
      "F11 = Full Screen\n"
    );
  }

  public static void selectView() {
    stopViewer();
    selector = new SelectView();
    setPanel(selector);
    selector.setVisible(true);
  }

  public void selectView(String type, String name) {
    try {
      Config.changeURL("/" + type + "/" + name);
      viewer = new Viewer();
      viewer.play(Config.url);
    } catch (Exception e) {
      JFLog.log(e);
    }
  }

  public static void refresh() {
    if (selector != null) {
      selector.refresh();
    } else {
      viewer.refresh();
    }
  }

  public static void toggleFullscreen() {
    if (self.fullScreen) {
      self.setExtendedState(Frame.NORMAL);
    } else {
      self.setExtendedState(Frame.MAXIMIZED_BOTH);
    }
    self.fullScreen = !self.fullScreen;
  }

  public static void setPanel(JPanel panel) {
    JFLog.log("setPanel:" + panel);
    Container old = self.getContentPane();
    if (old instanceof VideoPanel) {
      VideoPanel video = (VideoPanel)old;
      video.stop();
    }
    if (panel == null) {
      self.setContentPane(root);
      return;
    }
    if (panel instanceof SelectView) {
      viewer = null;
    }
    self.setContentPane(panel);
    JFAWT.assignHotKey(panel.getRootPane(), new Runnable() {public void run() {showHelp();}}, KeyEvent.VK_F1);
    JFAWT.assignHotKey(panel.getRootPane(), new Runnable() {public void run() {selectView();}}, KeyEvent.VK_F2);
    JFAWT.assignHotKey(panel.getRootPane(), new Runnable() {public void run() {refresh();}}, KeyEvent.VK_F5);
    JFAWT.assignHotKey(panel.getRootPane(), new Runnable() {public void run() {toggleFullscreen();}}, KeyEvent.VK_F11);
    panel.revalidate();
  }
}
