/**
 * Created : Mar 24, 2012
 *
 * @author pquiring
 */

import java.awt.*;
import java.io.*;
import javaforce.*;
import javax.swing.*;

import javaforce.awt.*;

public class ExtractDialog extends javax.swing.JDialog implements ShellProcessListener {

  /**
   * Creates new form ExtractDialog
   */
  public ExtractDialog(java.awt.Frame parent, boolean modal) {
    super(parent, modal);
    initComponents();
    setPosition();
    listDevices();
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
    devs = new javax.swing.JComboBox<>();
    jButton1 = new javax.swing.JButton();
    jButton2 = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("Extract ISO Image");

    jLabel1.setText("Select Drive:");

    jButton1.setText("Extract");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton1ActionPerformed(evt);
      }
    });

    jButton2.setText("Cancel");
    jButton2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton2ActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addComponent(jLabel1)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(devs, 0, 278, Short.MAX_VALUE))
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addGap(0, 0, Short.MAX_VALUE)
            .addComponent(jButton2)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jButton1)))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel1)
          .addComponent(devs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jButton2)
          .addComponent(jButton1))
        .addContainerGap())
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    dispose();
  }//GEN-LAST:event_jButton2ActionPerformed

  private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    extract();
  }//GEN-LAST:event_jButton1ActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JComboBox<String> devs;
  private javax.swing.JButton jButton1;
  private javax.swing.JButton jButton2;
  private javax.swing.JLabel jLabel1;
  // End of variables declaration//GEN-END:variables

  private void listDevices() {
    devs.removeAllItems();
    File file = new File("/dev/");
    File files[] = file.listFiles(new FileFilter() {
      public boolean accept(File file) {
        if (file.getName().startsWith("sr")) return true;
        return false;
      }
    });
    for(int a=0;a<files.length;a++) {
      devs.addItem(files[a].getAbsolutePath());
    }
  }

  private void extract() {
    int idx = devs.getSelectedIndex();
    if (idx == -1) return;
    String dev = (String)devs.getSelectedItem();
    JFileChooser chooser = new JFileChooser();
    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    chooser.setMultiSelectionEnabled(false);
    File path = new File(JF.getUserPath());
    chooser.setCurrentDirectory(path);
    if (chooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) return;
    String filename = chooser.getSelectedFile().getAbsolutePath();
    //exec : dd if=/dev/cdrom of=$filename
    ShellProcess sp = new ShellProcess();
    sp.addListener(this);
    String cmd[] = {"dd", "if=" + dev, "of=" + filename};
    String output = sp.run(cmd, false);
    //TODO - handle errors?
    JFAWT.showMessage("Notice", "CD-Image Extracted");
    dispose();
  }

  public void shellProcessOutput(String string) {
    //TODO - does dd output stats?
  }

  private void setPosition() {
    Rectangle s = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
    Dimension d = getPreferredSize();
    setLocation(s.width/2 - d.width/2, s.height/2 - (d.height/2));
  }
}
