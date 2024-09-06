/**
 * Created : Jun 15, 2012
 *
 * @author pquiring
 */

import java.awt.*;
import java.awt.event.*;

import javaforce.*;
import javaforce.awt.*;
import javaforce.media.*;

public class AudioApp extends javax.swing.JFrame implements KeyEventDispatcher {

  public static String version = "0.22";

  /**
   * Creates new form AudioApp
   */
  public AudioApp() {
    initComponents();
    setPosition();
    mainPanel = new MainPanel();
    setContentPane(mainPanel);
    KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(this);
    setTitle("jfAudio Editor/" + version);
    JFImage icon = new JFImage();
    icon.loadPNG(this.getClass().getClassLoader().getResourceAsStream("jfaudio.png"));
    setIconImage(icon.getImage());
    Menu.create(this, mainPanel);
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
    setTitle("jfAudio Editor");
    addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowClosing(java.awt.event.WindowEvent evt) {
        formWindowClosing(evt);
      }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 880, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 794, Short.MAX_VALUE)
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
    mainPanel.close();
  }//GEN-LAST:event_formWindowClosing

  /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {
    MediaCoder.init();
    if (!Paths.testPaths()) return;
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new AudioApp().setVisible(true);
      }
    });
  }
  // Variables declaration - do not modify//GEN-BEGIN:variables
  // End of variables declaration//GEN-END:variables

  private MainPanel mainPanel;

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
  public static boolean inDialog = false;

  public boolean dispatchKeyEvent(KeyEvent e) {
    if (inDialog) return false;
    int id = e.getID();
    char ch = e.getKeyChar();
    int cc = e.getKeyCode();
    int mod = e.getModifiersEx() & JFAWT.KEY_MASKS;
//    JFLog.log("keyEvent:" + e);
    if (mod == KeyEvent.CTRL_DOWN_MASK) {
      switch (id) {
        case KeyEvent.KEY_TYPED:
          break;
        case KeyEvent.KEY_PRESSED:
          switch (cc) {
            case 'X': cut(); break;
            case 'C': copy(); break;
            case 'V': paste(); break;
            case 'Z': undo(); break;
          }
          break;
        case KeyEvent.KEY_RELEASED:
          break;
      }
    }
    if (mod != 0) return false;
    switch (id) {
      case KeyEvent.KEY_TYPED:
        break;
      case KeyEvent.KEY_PRESSED:
        switch (cc) {
          case KeyEvent.VK_DELETE: delete(); break;
        }
        break;
      case KeyEvent.KEY_RELEASED:
        break;
    }
    return false;
  }
  public void cut() {
    mainPanel.cut();
  }
  public void copy() {
    mainPanel.copy();
  }
  public void paste() {
    mainPanel.paste();
  }
  public void delete() {
    mainPanel.delete();
  }
  public void undo() {
    mainPanel.undo();
  }
}
