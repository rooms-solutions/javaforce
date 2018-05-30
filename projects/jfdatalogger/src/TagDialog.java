/**
 *  add / edit tag dialog.
 *
 * @author pquiring
 */

import java.awt.Color;
import javax.swing.JColorChooser;

import javaforce.*;
import javaforce.controls.*;

public class TagDialog extends javax.swing.JDialog {

  /**
   * Creates new form AddDialog
   */
  public TagDialog(java.awt.Frame parent, boolean modal) {
    super(parent, modal);
    initComponents();
    JFAWT.centerWindow(this);
    color.setBackground(Color.black);
    clr = 0x000000;
    setMinMax();
    JFAWT.assignHotKey(this, ok, java.awt.event.KeyEvent.VK_ENTER);
    JFAWT.assignHotKey(this, cancel, java.awt.event.KeyEvent.VK_ESCAPE);
  }

  /**
   * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jLabel1 = new javax.swing.JLabel();
    type = new javax.swing.JComboBox<>();
    jLabel2 = new javax.swing.JLabel();
    tag = new javax.swing.JTextField();
    ok = new javax.swing.JButton();
    cancel = new javax.swing.JButton();
    jLabel4 = new javax.swing.JLabel();
    size = new javax.swing.JComboBox<>();
    max = new javax.swing.JTextField();
    min = new javax.swing.JTextField();
    l_max = new javax.swing.JLabel();
    l_min = new javax.swing.JLabel();
    jLabel7 = new javax.swing.JLabel();
    host = new javax.swing.JTextField();
    jLabel8 = new javax.swing.JLabel();
    color = new javax.swing.JButton();
    help = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("Tag Properties");

    jLabel1.setText("Type");

    type.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Siemens", "AllenBradley", "ModBus", "NI" }));
    type.addItemListener(new java.awt.event.ItemListener() {
      public void itemStateChanged(java.awt.event.ItemEvent evt) {
        typeItemStateChanged(evt);
      }
    });

    jLabel2.setText("Tag");

    ok.setText("OK");
    ok.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        okActionPerformed(evt);
      }
    });

    cancel.setText("Cancel");
    cancel.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cancelActionPerformed(evt);
      }
    });

    jLabel4.setText("Size");

    size.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "bit", "int8", "int16", "int32", "float32", "float64" }));
    size.addItemListener(new java.awt.event.ItemListener() {
      public void itemStateChanged(java.awt.event.ItemEvent evt) {
        sizeItemStateChanged(evt);
      }
    });

    max.setText("1");
    max.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        maxActionPerformed(evt);
      }
    });

    min.setText("0");

    l_max.setText("max");

    l_min.setText("min");

    jLabel7.setText("Host");

    jLabel8.setText("color");

    color.setForeground(new java.awt.Color(0, 153, 204));
    color.setText("Change...");
    color.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        colorActionPerformed(evt);
      }
    });

    help.setText("Help");
    help.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        helpActionPerformed(evt);
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
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jLabel1)
              .addComponent(jLabel7))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(type, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
              .addComponent(host)))
          .addGroup(layout.createSequentialGroup()
            .addComponent(jLabel2)
            .addGap(16, 16, 16)
            .addComponent(tag))
          .addGroup(layout.createSequentialGroup()
            .addComponent(ok)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(help)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(cancel))
          .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jLabel4)
              .addComponent(l_max)
              .addComponent(l_min)
              .addComponent(jLabel8))
            .addGap(11, 11, 11)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(color, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
              .addComponent(size, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
              .addComponent(max)
              .addComponent(min))))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel1)
          .addComponent(type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jLabel7)
          .addComponent(host, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(tag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel2))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel4))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(max, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(l_max))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(min, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(l_min))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel8)
          .addComponent(color))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(ok)
          .addComponent(cancel)
          .addComponent(help))
        .addContainerGap())
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okActionPerformed
    if (!valid()) return;
    accepted = true;
    dispose();
  }//GEN-LAST:event_okActionPerformed

  private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
    accepted = false;
    dispose();
  }//GEN-LAST:event_cancelActionPerformed

  private void sizeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_sizeItemStateChanged
    setMinMax();
  }//GEN-LAST:event_sizeItemStateChanged

  private void maxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maxActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_maxActionPerformed

  private void colorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colorActionPerformed
    changeColor();
  }//GEN-LAST:event_colorActionPerformed

  private void typeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_typeItemStateChanged
    if (type.getSelectedIndex() == 3) {
      tag.setEnabled(false);
      tag.setText("");
    } else {
      tag.setEnabled(true);
    }
  }//GEN-LAST:event_typeItemStateChanged

  private void helpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpActionPerformed
    JF.openURL("http://jfdatalogger.sf.net/help.php");
  }//GEN-LAST:event_helpActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton cancel;
  private javax.swing.JButton color;
  private javax.swing.JButton help;
  private javax.swing.JTextField host;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel7;
  private javax.swing.JLabel jLabel8;
  private javax.swing.JLabel l_max;
  private javax.swing.JLabel l_min;
  private javax.swing.JTextField max;
  private javax.swing.JTextField min;
  private javax.swing.JButton ok;
  private javax.swing.JComboBox<String> size;
  private javax.swing.JTextField tag;
  private javax.swing.JComboBox<String> type;
  // End of variables declaration//GEN-END:variables

  private boolean accepted;
  private int clr;

  public void load(Tag in) {
    switch (in.type) {
      case ControllerType.S7: type.setSelectedIndex(0); break;
      case ControllerType.AB: type.setSelectedIndex(1); break;
      case ControllerType.MB: type.setSelectedIndex(2); break;
      case ControllerType.NI: type.setSelectedIndex(3); break;
    }
    host.setText(in.host);
    tag.setText(in.tag);
    boolean isFloat = false;
    switch (in.size) {
      case TagType.bit: size.setSelectedIndex(0); in.max = 0; break;
      case TagType.int8: size.setSelectedIndex(1); break;
      case TagType.int16: size.setSelectedIndex(2); break;
      case TagType.int32: size.setSelectedIndex(3); break;
      case TagType.float32: size.setSelectedIndex(4); isFloat = true; break;
      case TagType.float64: size.setSelectedIndex(5); isFloat = true; break;
    }
    setMinMax();
    if (!isFloat) {
      max.setText(Integer.toString(in.max));
      min.setText(Integer.toString(in.min));
    } else {
      max.setText(Float.toString(in.fmax));
      min.setText(Float.toString(in.fmin));
    }
    color.setBackground(new Color(in.color));
    clr = in.color;
  }

  public void bitOnly() {
    size.removeAllItems();
    size.addItem("bit");
  }

  public boolean save(Tag out) {
    try {
      switch (type.getSelectedIndex()) {
        case 0: out.type = ControllerType.S7; break;
        case 1: out.type = ControllerType.AB; break;
        case 2: out.type = ControllerType.MB; break;
        case 3: out.type = ControllerType.NI; break;
      }
      out.host = host.getText();
      out.tag = tag.getText();
      boolean isFloat = false;
      switch (size.getSelectedIndex()) {
        case 0: out.size = TagType.bit; break;
        case 1: out.size = TagType.int8; break;
        case 2: out.size = TagType.int16; break;
        case 3: out.size = TagType.int32; break;
        case 4: out.size = TagType.float32; isFloat = true; break;
        case 5: out.size = TagType.float64; isFloat = true; break;
      }
      if (!isFloat) {
        out.min = Integer.valueOf(min.getText());
        out.max = Integer.valueOf(max.getText());
      } else {
        out.fmin = Float.valueOf(min.getText());
        out.fmax = Float.valueOf(max.getText());
      }
      out.color = clr;
    } catch (Exception e) {
      JFLog.log(e);
      return false;
    }
    return true;
  }

  public boolean accepted() {
    return accepted;
  }

  public void changeColor() {
    Color newClr = JColorChooser.showDialog(this, "Tag Color", new Color(clr));
    if (newClr == null) return;
    clr = newClr.getRGB() & 0xffffff;
    color.setBackground(newClr);
  }

  private void setMinMax() {
    int idx = size.getSelectedIndex();
    min.setText("0");
    if (idx == 0) {
      max.setEnabled(false);
      l_max.setText("n/a");
      l_min.setText("0-100");
    } else {
      max.setEnabled(true);
      l_max.setText("max");
      l_min.setText("min");
    }
    switch (idx) {
      case 0: max.setText("0"); break;
      case 1: max.setText("255"); break;
      case 2: max.setText("65535"); break;
      case 3: max.setText("16777216"); break;
      case 4: max.setText("1.0"); break;
    }
  }

  private boolean valid() {
    if (size.getSelectedIndex() == 0) {
      int _min = Integer.valueOf(min.getText());
      if (_min < 0 || _min > 100) {
        min.requestFocus();
        return false;
      }
    }
    return true;
  }
}
