package javaforce.service;

/**
 *
 * @author pquiring
 *
 * Created : Dec 24, 2013
 */

import java.util.*;
import javax.swing.*;

import javaforce.*;
import javaforce.awt.*;
import javaforce.jbus.*;

public class STUNApp extends javax.swing.JFrame {

  /**
   * Creates new form STUNApp
   */
  public STUNApp() {
    initComponents();
    //create tray icon to open app
    JFImage img = new JFImage();
    img.loadPNG(this.getClass().getResourceAsStream("/javaforce/icons/stun.png"));
    setIconImage(img.getImage());
    new Thread() {
      public void run() {
        Random r = new Random();
        busClient = new JBusClient(STUNServer.busPack + ".client" + r.nextInt(), new JBusMethods());
        busClient.setPort(STUNServer.getBusPort());
        busClient.start();
        busClient.call(STUNServer.busPack, "getConfig", "\"" + busClient.pack + "\"");
      }
    }.start();
    JFAWT.centerWindow(this);
  }

  public void writeConfig() {
    busClient.call(STUNServer.busPack, "setConfig", busClient.quote(busClient.encodeString(config.getText())));
  }

  public void restart() {
    busClient.call(STUNServer.busPack, "restart", "");
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    save = new javax.swing.JButton();
    jScrollPane1 = new javax.swing.JScrollPane();
    config = new javax.swing.JTextArea();
    jLabel1 = new javax.swing.JLabel();
    jButton1 = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("STUN/TURN Server");

    save.setText("Save");
    save.setEnabled(false);
    save.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        saveActionPerformed(evt);
      }
    });

    config.setColumns(20);
    config.setRows(5);
    config.setText(" [ loading ... ]\n");
    config.setEnabled(false);
    jScrollPane1.setViewportView(config);

    jLabel1.setText("STUN/TURN Configuration:");

    jButton1.setText("View Log");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton1ActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE)
          .addGroup(layout.createSequentialGroup()
            .addComponent(jButton1)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(save))
          .addGroup(layout.createSequentialGroup()
            .addComponent(jLabel1)
            .addGap(0, 0, Short.MAX_VALUE)))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jLabel1)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(save)
          .addComponent(jButton1))
        .addContainerGap())
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
    writeConfig();
    restart();
    JFAWT.showMessage("Notice", "Settings saved!");
  }//GEN-LAST:event_saveActionPerformed

  private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    showViewLog();
  }//GEN-LAST:event_jButton1ActionPerformed

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new STUNApp().setVisible(true);
      }
    });
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JTextArea config;
  private javax.swing.JButton jButton1;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JButton save;
  // End of variables declaration//GEN-END:variables

  public ViewLog viewer;

  public void showViewLog() {
    if (viewer == null || viewer.isClosed) {
      viewer = new ViewLog(STUNServer.getLogFile());
      viewer.setTitle("STUN Log");
    }
    viewer.setVisible(true);
  }

  public JBusClient busClient;

  public class JBusMethods {
    public void getConfig(String cfg) {
      final String _cfg = cfg;
      java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
          config.setText(JBusClient.decodeString(_cfg));
          config.setEnabled(true);
          save.setEnabled(true);
        }
      });
    }
  }

}
