package jfqemu;

/**
 * Created : July 8, 2012
 *
 * @author pquiring
 */

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.io.*;
import java.util.*;

import javaforce.*;

public class NetworkDialog extends javax.swing.JDialog {

  /**
   * Creates new form NetworkDialog
   */
  public NetworkDialog(java.awt.Frame parent, boolean modal) {
    super(parent, modal);
    initComponents();
    setPosition();
    listIFs();
    listBRs();
    listTAPs();
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jLabel1 = new javax.swing.JLabel();
    jPanel1 = new javax.swing.JPanel();
    jLabel3 = new javax.swing.JLabel();
    tapID = new javax.swing.JTextField();
    jLabel4 = new javax.swing.JLabel();
    tapBridge = new javax.swing.JComboBox();
    createTAP = new javax.swing.JButton();
    close = new javax.swing.JButton();
    jPanel2 = new javax.swing.JPanel();
    jLabel2 = new javax.swing.JLabel();
    brID = new javax.swing.JTextField();
    createBridge = new javax.swing.JButton();
    jLabel5 = new javax.swing.JLabel();
    ifName = new javax.swing.JComboBox();
    jPanel3 = new javax.swing.JPanel();
    tapSelect = new javax.swing.JComboBox();
    deleteTAP = new javax.swing.JButton();
    jPanel4 = new javax.swing.JPanel();
    bridgeSelect = new javax.swing.JComboBox();
    deleteBridge = new javax.swing.JButton();
    help = new javax.swing.JButton();
    netmgr = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("Bridge/TAP Config");
    setResizable(false);

    jLabel1.setText("From here you can create TAPs (virtual NIC) and bridge them to real NICs.");

    jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Create TAP"));

    jLabel3.setText("ID #");

    tapID.setText("0");

    jLabel4.setText("Add to Bridge");

    createTAP.setText("Create");
    createTAP.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        createTAPActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jLabel3)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(tapID, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jLabel4)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(tapBridge, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(createTAP)
        .addContainerGap())
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel3)
          .addComponent(tapID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel4)
          .addComponent(tapBridge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(createTAP))
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    close.setText("Close");
    close.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        closeActionPerformed(evt);
      }
    });

    jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Create Bridge"));

    jLabel2.setText("ID #");

    brID.setText("0");

    createBridge.setText("Create");
    createBridge.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        createBridgeActionPerformed(evt);
      }
    });

    jLabel5.setText("Bind to Interface");

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
      jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel2Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jLabel2)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(brID, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jLabel5)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(ifName, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(createBridge)
        .addContainerGap())
    );
    jPanel2Layout.setVerticalGroup(
      jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel2Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel2)
          .addComponent(brID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(createBridge)
          .addComponent(jLabel5)
          .addComponent(ifName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Delete TAP"));

    tapSelect.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

    deleteTAP.setText("Delete");
    deleteTAP.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        deleteTAPActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
    jPanel3.setLayout(jPanel3Layout);
    jPanel3Layout.setHorizontalGroup(
      jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel3Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(tapSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(deleteTAP)
        .addContainerGap())
    );
    jPanel3Layout.setVerticalGroup(
      jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel3Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(tapSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(deleteTAP))
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Delete Bridge"));

    bridgeSelect.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

    deleteBridge.setText("Delete");
    deleteBridge.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        deleteBridgeActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
    jPanel4.setLayout(jPanel4Layout);
    jPanel4Layout.setHorizontalGroup(
      jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel4Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(bridgeSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(deleteBridge)
        .addContainerGap())
    );
    jPanel4Layout.setVerticalGroup(
      jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel4Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(bridgeSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(deleteBridge))
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    help.setText("Help");
    help.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        helpActionPerformed(evt);
      }
    });

    netmgr.setText("Network Manager");
    netmgr.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        netmgrActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addComponent(netmgr)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(help)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(close))
          .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        .addGap(18, 18, 18)
        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(23, 23, 23)
        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(close)
          .addComponent(help)
          .addComponent(netmgr))
        .addContainerGap())
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void createBridgeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createBridgeActionPerformed
    createBridge();
  }//GEN-LAST:event_createBridgeActionPerformed

  private void createTAPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createTAPActionPerformed
    createTAP();
  }//GEN-LAST:event_createTAPActionPerformed

  private void deleteTAPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteTAPActionPerformed
    deleteTAP();
  }//GEN-LAST:event_deleteTAPActionPerformed

  private void deleteBridgeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBridgeActionPerformed
    deleteBridge();
  }//GEN-LAST:event_deleteBridgeActionPerformed

  private void closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeActionPerformed
    dispose();
  }//GEN-LAST:event_closeActionPerformed

  private void helpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpActionPerformed
    try {
      Runtime.getRuntime().exec(new String[] {"jfhelp", "jfqemu"});
    } catch (Exception e) {}
  }//GEN-LAST:event_helpActionPerformed

  private void netmgrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_netmgrActionPerformed
    try {
      Runtime.getRuntime().exec(new String[] {"jfconfig", "interfaces"});
    } catch (Exception e) {}
  }//GEN-LAST:event_netmgrActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JTextField brID;
  private javax.swing.JComboBox bridgeSelect;
  private javax.swing.JButton close;
  private javax.swing.JButton createBridge;
  private javax.swing.JButton createTAP;
  private javax.swing.JButton deleteBridge;
  private javax.swing.JButton deleteTAP;
  private javax.swing.JButton help;
  private javax.swing.JComboBox ifName;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JPanel jPanel2;
  private javax.swing.JPanel jPanel3;
  private javax.swing.JPanel jPanel4;
  private javax.swing.JButton netmgr;
  private javax.swing.JComboBox tapBridge;
  private javax.swing.JTextField tapID;
  private javax.swing.JComboBox tapSelect;
  // End of variables declaration//GEN-END:variables

  private void listIFs() {
    ifName.removeAllItems();
    ShellProcess sp = new ShellProcess();
    String output = sp.run(new String[] {"ifconfig", "-a"}, false);
    String lns[] = output.split("\n");
    for(int a=0;a<lns.length;a++) {
      if (!lns[a].startsWith(" ")) {
        int idx = lns[a].indexOf(" ");
        if (idx == -1) continue;
        String name = lns[a].substring(0,idx);
        if (name.startsWith("br")) continue;
        if (name.startsWith("tap")) continue;
        if (name.equals("lo")) continue;
        ifName.addItem(name);
      }
    }
  }

  private void listBRs() {
    tapBridge.removeAllItems();
    bridgeSelect.removeAllItems();
    ShellProcess sp = new ShellProcess();
    String output = sp.run(new String[] {"ifconfig", "-a"}, false);
    String lns[] = output.split("\n");
    for(int a=0;a<lns.length;a++) {
      if (!lns[a].startsWith(" ")) {
        int idx = lns[a].indexOf(" ");
        if (idx == -1) continue;
        String name = lns[a].substring(0,idx);
        if (!name.startsWith("br")) continue;
        tapBridge.addItem(name);
        bridgeSelect.addItem(name);
      }
    }
  }

  private void listTAPs() {
    tapSelect.removeAllItems();
    ShellProcess sp = new ShellProcess();
    String output = sp.run(new String[] {"ifconfig", "-a"}, false);
    String lns[] = output.split("\n");
    for(int a=0;a<lns.length;a++) {
      if (!lns[a].startsWith(" ")) {
        int idx = lns[a].indexOf(" ");
        if (idx == -1) continue;
        String name = lns[a].substring(0,idx);
        if (!name.startsWith("tap")) continue;
        tapSelect.addItem(name);
      }
    }
  }

  private void createBridge() {
    String iface = (String)ifName.getSelectedItem();
    if (iface == null) return;
    //create a script to do this
    try {
      int id = Integer.valueOf(brID.getText());
      File file = File.createTempFile("netcfg", ".sh", new File("/tmp"));
      FileOutputStream fos = new FileOutputStream(file);
      fos.write("#!/bin/bash\n".getBytes());
      fos.write(("ifconfig " + iface + " 0.0.0.0 down\n").getBytes());
      fos.write(("brctl addbr br" + id + "\n").getBytes());
      fos.write(("brctl addif br" + id + " " + iface + "\n").getBytes());
      fos.close();
      file.setExecutable(true);
      ShellProcess sp = new ShellProcess();
      String output = sp.run(new String[] {"sudo", file.getAbsolutePath()}, true);
      file.delete();
      JFAWT.showMessage("Notice", "Bridge added, please goto Network Manager and config/enable the bridge adapter");
    } catch (Exception e) {
      JFAWT.showError("Error", "Exception : " + e);
      JFLog.log(e);
    }
    listBRs();
  }

  private void createTAP() {
    String bridge = (String)tapBridge.getSelectedItem();
    if (bridge == null) return;
    //create a script to do this
    try {
      int id = Integer.valueOf(tapID.getText());
      File file = File.createTempFile("netcfg", ".sh", new File("/tmp"));
      FileOutputStream fos = new FileOutputStream(file);
      fos.write("#!/bin/bash\n".getBytes());
      fos.write(("ip tuntap add dev tap" + id + " mode tap\n").getBytes());
      fos.write(("brctl addif " + bridge + " tap" + id + "\n").getBytes());
      fos.close();
      file.setExecutable(true);
      ShellProcess sp = new ShellProcess();
      String output = sp.run(new String[] {"sudo", file.getAbsolutePath()}, true);
      file.delete();
      JFAWT.showMessage("Notice", "TAP added, please goto Network Manager and config/enable the TAP adapter");
    } catch (Exception e) {
      JFAWT.showError("Error", "Exception : " + e);
      JFLog.log(e);
    }
    listTAPs();
  }

  private void deleteBridge() {
    String bridge = (String)bridgeSelect.getSelectedItem();
    if (bridge == null) return;
    //create a script to do this
    try {
      File file = File.createTempFile("netcfg", ".sh", new File("/tmp"));
      FileOutputStream fos = new FileOutputStream(file);
      fos.write("#!/bin/bash\n".getBytes());
      fos.write(("ifconfig " + bridge + " 0.0.0.0 down\n").getBytes());
      fos.write(("brctl delbr " + bridge + "\n").getBytes());
      fos.close();
      file.setExecutable(true);
      ShellProcess sp = new ShellProcess();
      String output = sp.run(new String[] {"sudo", file.getAbsolutePath()}, true);
      file.delete();
      JFAWT.showMessage("Notice", "Bridge deleted!");
    } catch (Exception e) {
      JFAWT.showError("Error", "Exception : " + e);
      JFLog.log(e);
    }
    listBRs();
  }

  private void deleteTAP() {
    String tap = (String)tapSelect.getSelectedItem();
    if (tap == null) return;
    //create a script to do this
    try {
      File file = File.createTempFile("netcfg", ".sh", new File("/tmp"));
      FileOutputStream fos = new FileOutputStream(file);
      fos.write("#!/bin/bash\n".getBytes());
      fos.write(("ifconfig " + tap + " 0.0.0.0 down\n").getBytes());
      fos.write(("ip tuntap del dev " + tap + " mode tap\n").getBytes());
      fos.close();
      file.setExecutable(true);
      ShellProcess sp = new ShellProcess();
      String output = sp.run(new String[] {"sudo", file.getAbsolutePath()}, true);
//      file.delete();
      JFAWT.showMessage("Notice", "TAP deleted!");
    } catch (Exception e) {
      JFAWT.showError("Error", "Exception : " + e);
      JFLog.log(e);
    }
    listTAPs();
  }

  private void setPosition() {
    Dimension d = getSize();
    Rectangle s = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
    setLocation(s.width/2 - d.width/2, s.height/2 - d.height/2);
  }
}
