/**
 * Created : Jun 8, 2012
 *
 * @author pquiring
 */

import java.awt.*;
import java.awt.event.*;

import javaforce.*;

public class PasswordsApp extends javax.swing.JFrame implements ActionListener {

  private String version = "0.24";

  /**
   * Creates new form PasswordsApp
   */
  public PasswordsApp() {
    initComponents();
    setTitle("jfPasswords/" + version);
    panel = new MainPanel();
    setContentPane(panel);

    if (panel.config.WindowXSize != -1) {
      setSize(panel.config.WindowXSize, panel.config.WindowYSize);
      setLocation(panel.config.WindowXPos, panel.config.WindowYPos);
      if (panel.config.bWindowMax) setExtendedState(MAXIMIZED_BOTH);
    } else {
      setPosition();
    }
    loaded = true;

    JFImage appicon = new JFImage();
    appicon.loadPNG(this.getClass().getClassLoader().getResourceAsStream("jfpasswords.png"));
    setIconImage(appicon.getImage());
    trayicon = new JFImage();
    trayicon.loadPNG(this.getClass().getClassLoader().getResourceAsStream("jfpasswords_tray.png"));
    addTrayIcon();
    if (panel.config.safe.length() == 0) {
      setVisible(true);
    }
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    setTitle("Passwords");
    addComponentListener(new java.awt.event.ComponentAdapter() {
      public void componentMoved(java.awt.event.ComponentEvent evt) {
        formComponentMoved(evt);
      }
      public void componentResized(java.awt.event.ComponentEvent evt) {
        formComponentResized(evt);
      }
    });
    addWindowStateListener(new java.awt.event.WindowStateListener() {
      public void windowStateChanged(java.awt.event.WindowEvent evt) {
        formWindowStateChanged(evt);
      }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 763, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 534, Short.MAX_VALUE)
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void formComponentMoved(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentMoved
    if (!loaded) return;
    if (panel.config.bWindowMax) return;
    Point loc = getLocation();
    panel.config.WindowXPos = loc.x;
    panel.config.WindowYPos = loc.y;
  }//GEN-LAST:event_formComponentMoved

  private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
    if (!loaded) return;
    if (panel.config.bWindowMax) return;
    Dimension size = getSize();
    panel.config.WindowXSize = size.width;
    panel.config.WindowYSize = size.height;
  }//GEN-LAST:event_formComponentResized

  private void formWindowStateChanged(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowStateChanged
    if (!loaded) return;
    panel.config.bWindowMax = evt.getNewState() == MAXIMIZED_BOTH;
  }//GEN-LAST:event_formWindowStateChanged

  /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new PasswordsApp();
      }
    });
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  // End of variables declaration//GEN-END:variables

  private boolean loaded = false;

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

  public void actionPerformed(ActionEvent e) {
    Object o = e.getSource();
    if (o == exit) {
      if (panel.dirty) {
        switch (JFAWT.showConfirm3("Confirm", "Save first?")) {
          case JFAWT.YES: panel.saveSafe(); break;
          case JFAWT.NO: break;
          case JFAWT.CANCEL: return;
        }
      }
      System.exit(0);
    }
    if (o == show) {
      if (isVisible()) {
        panel.checkTimestamp();
        if ((getExtendedState() & Frame.ICONIFIED) == Frame.ICONIFIED) {
          setExtendedState(getExtendedState() ^ Frame.ICONIFIED);
        }
        requestFocus();
        return;
      }
      if (panel.config.safe.length() > 0 && !panel.loaded) {
        if (!panel.loadSafe(false)) return;
      } else if (panel.config.reAuthOnShow && panel.loaded) {
        String password = GetPassword.getPassword(this);
        if (password == null) return;
        if (!password.equals(MainPanel.password)) {
          JFAWT.showMessage("Error", "Wrong Password");
          return;
        }
      }
      panel.checkTimestamp();
      setVisible(true);
      removeTrayIcon();
      addTrayIcon();
    }
  }

  private void addTrayIcon() {
    tray = SystemTray.getSystemTray();
    Dimension size = tray.getTrayIconSize();
    JFImage scaled = new JFImage(size.width, size.height);
    scaled.fill(0, 0, size.width, size.height, 0x00000000, true);  //fill with alpha transparent
    if (false) {
      //scaled image (looks bad sometimes)
      scaled.getGraphics().drawImage(trayicon.getImage()
        , 0, 0, size.width, size.height
        , 0, 0, trayicon.getWidth(), trayicon.getHeight()
        , null);
    } else {
      //center image
      scaled.getGraphics().drawImage(trayicon.getImage()
        , (size.width - trayicon.getWidth()) / 2
        , (size.height - trayicon.getHeight()) / 2
        , null);
    }
    //create tray icon
    PopupMenu popup = new PopupMenu();
    show = new MenuItem("Show");
    show.addActionListener(this);
    popup.add(show);
    popup.addSeparator();
    exit = new MenuItem("Exit");
    exit.addActionListener(this);
    popup.add(exit);
    icon = new TrayIcon(scaled.getImage(), "Passwords", popup);
    icon.addActionListener(this);
    try { tray.add(icon); } catch (Exception e) { JFLog.log(e); }
  }

  private void removeTrayIcon() {
    if (tray != null) {
      tray.remove(icon);
    }
  }

  private JFImage trayicon;
  private MainPanel panel;
  public SystemTray tray;
  public TrayIcon icon;
  public MenuItem exit, show;
}
