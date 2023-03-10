/**
 *
 * @author pquiring
 *
 * Created : Oct 2, 2013
 */

import javaforce.*;
import javaforce.awt.*;

public class BlurProps extends javax.swing.JPanel {

  /**
   * Creates new form CutProps
   */
  public BlurProps(Element element) {
    initComponents();
    length.setValue((Integer)(element.length));
    String fs[] = element.fx.split(";");
    for(int a=0;a<fs.length;a++) {
      String f = fs[a];
      if (f.startsWith("radius=")) {
        radius.setValue(JF.atoi(f.substring(7)));
      } else if (f.equals("fadein")) {
        fadein.setSelected(true);
      } else if (f.equals("fadeout")) {
        fadeout.setSelected(true);
      }
    }
  }

  /**
   * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
   * content of this method is always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    length1 = new javax.swing.JLabel();
    length = new javax.swing.JSpinner();
    length2 = new javax.swing.JLabel();
    radius = new javax.swing.JSlider();
    jLabel1 = new javax.swing.JLabel();
    fadein = new javax.swing.JCheckBox();
    fadeout = new javax.swing.JCheckBox();

    length1.setText("Blur Duration");

    length.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(5), Integer.valueOf(1), null, Integer.valueOf(1)));

    length2.setText("Seconds");

    radius.setMajorTickSpacing(1);
    radius.setMaximum(10);
    radius.setMinimum(3);
    radius.setPaintLabels(true);
    radius.setPaintTicks(true);
    radius.setSnapToTicks(true);
    radius.setToolTipText("");
    radius.setValue(5);

    jLabel1.setText("Radius");

    fadein.setText("Fade In");

    fadeout.setText("Fade Out");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addComponent(fadein)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(fadeout))
          .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(length1)
              .addComponent(jLabel1))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(radius, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addGroup(layout.createSequentialGroup()
                .addComponent(length, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(length2)))))
        .addContainerGap(121, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(length1)
          .addComponent(length, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(length2))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(radius, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel1))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(fadein)
          .addComponent(fadeout))
        .addContainerGap(205, Short.MAX_VALUE))
    );
  }// </editor-fold>//GEN-END:initComponents
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JCheckBox fadein;
  private javax.swing.JCheckBox fadeout;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JSpinner length;
  private javax.swing.JLabel length1;
  private javax.swing.JLabel length2;
  private javax.swing.JSlider radius;
  // End of variables declaration//GEN-END:variables

  public void save(Element element) {
    element.length = (Integer)length.getValue();
    String fx = "type=gaussian;radius=" + radius.getValue();
    if (fadein.isSelected()) fx += ";fadein";
    if (fadeout.isSelected()) fx += ";fadeout";
    element.fx = fx;
  }
}
