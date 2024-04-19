package javaforce.awt.security;

/** JF Key Manager
 *
 * @author pquiring
 */

import java.security.KeyStore;
import java.security.Key;
import java.security.PublicKey;
import java.security.cert.Certificate;

import java.io.*;
import java.util.*;

import javax.swing.table.*;

import javaforce.*;
import javaforce.awt.*;

public class KeyMgr extends javax.swing.JDialog {

  private KeyMgmt keys;
  private DefaultTableModel model;
  private String root = "root";
  private static boolean debug = false;

  /**
   * Creates new form KeyMgr
   */
  public KeyMgr(KeyMgmt keys) {
    super((javax.swing.JFrame)null, true);
    this.keys = keys;
    initComponents();
    init();
  }

  /**
   * Creates new form KeyMgr
   */
  public KeyMgr(String keystore, String password) {
    super((javax.swing.JFrame)null, true);
    keys = new KeyMgmt();
    keys.setFile(keystore);
    keys.setKeyStorePass(password);
    keys.open();
    initComponents();
    init();
  }

  private void init() {
    model = (DefaultTableModel)table.getModel();
    reload();
    JFAWT.centerWindow(this);
    //NetBeans is ignoring floatable property ???
    bar1.setFloatable(false);
    bar2.setFloatable(false);
    bar3.setFloatable(false);
    bar4.setFloatable(false);
    bar5.setFloatable(false);
  }

  public void setRootAlias(String alias) {
    root = alias;
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jButton4 = new javax.swing.JButton();
    bar1 = new javax.swing.JToolBar();
    gen_root = new javax.swing.JButton();
    gen_client = new javax.swing.JButton();
    details = new javax.swing.JButton();
    jScrollPane2 = new javax.swing.JScrollPane();
    table = new javax.swing.JTable();
    bar4 = new javax.swing.JToolBar();
    export_ks = new javax.swing.JButton();
    jButton2 = new javax.swing.JButton();
    export_key = new javax.swing.JButton();
    export_crt = new javax.swing.JButton();
    bar5 = new javax.swing.JToolBar();
    import_ks = new javax.swing.JButton();
    import_key = new javax.swing.JButton();
    bar3 = new javax.swing.JToolBar();
    gen_csr = new javax.swing.JButton();
    signcsr = new javax.swing.JButton();
    bar2 = new javax.swing.JToolBar();
    rename_alias = new javax.swing.JButton();
    validate = new javax.swing.JButton();
    jButton1 = new javax.swing.JButton();
    reload = new javax.swing.JButton();

    jButton4.setText("jButton4");

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("Key Manager");

    bar1.setBorderPainted(false);
    bar1.setFocusable(false);

    gen_root.setText("Generate Root KeyPair");
    gen_root.setFocusable(false);
    gen_root.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    gen_root.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    gen_root.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        gen_rootActionPerformed(evt);
      }
    });
    bar1.add(gen_root);

    gen_client.setText("Generate Client KeyPair");
    gen_client.setFocusable(false);
    gen_client.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    gen_client.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    gen_client.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        gen_clientActionPerformed(evt);
      }
    });
    bar1.add(gen_client);

    details.setText("View Details");
    details.setFocusable(false);
    details.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    details.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    details.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        detailsActionPerformed(evt);
      }
    });
    bar1.add(details);

    table.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {
        {null, null, null},
        {null, null, null},
        {null, null, null},
        {null, null, null}
      },
      new String [] {
        "Alias", "PrivateKey", "PublicCert"
      }
    ) {
      Class[] types = new Class [] {
        java.lang.String.class, java.lang.String.class, java.lang.String.class
      };
      boolean[] canEdit = new boolean [] {
        false, false, false
      };

      public Class getColumnClass(int columnIndex) {
        return types [columnIndex];
      }

      public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
      }
    });
    jScrollPane2.setViewportView(table);

    bar4.setBorderPainted(false);
    bar4.setFocusable(false);

    export_ks.setText("Export KeyStore");
    export_ks.setFocusable(false);
    export_ks.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    export_ks.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    export_ks.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        export_ksActionPerformed(evt);
      }
    });
    bar4.add(export_ks);

    jButton2.setText("Export Key/Cert to KeyStore");
    jButton2.setFocusable(false);
    jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    jButton2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton2ActionPerformed(evt);
      }
    });
    bar4.add(jButton2);

    export_key.setText("Export Key");
    export_key.setFocusable(false);
    export_key.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    export_key.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    export_key.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        export_keyActionPerformed(evt);
      }
    });
    bar4.add(export_key);

    export_crt.setText("Export Cert");
    export_crt.setFocusable(false);
    export_crt.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    export_crt.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    export_crt.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        export_crtActionPerformed(evt);
      }
    });
    bar4.add(export_crt);

    bar5.setBorderPainted(false);
    bar5.setFocusable(false);

    import_ks.setText("Import KeyStore");
    import_ks.setFocusable(false);
    import_ks.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    import_ks.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    import_ks.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        import_ksActionPerformed(evt);
      }
    });
    bar5.add(import_ks);

    import_key.setText("Import Key/Cert");
    import_key.setFocusable(false);
    import_key.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    import_key.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    import_key.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        import_keyActionPerformed(evt);
      }
    });
    bar5.add(import_key);

    bar3.setBorderPainted(false);
    bar3.setFocusable(false);

    gen_csr.setText("Generate Cert Sign Request");
    gen_csr.setFocusable(false);
    gen_csr.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    gen_csr.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    gen_csr.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        gen_csrActionPerformed(evt);
      }
    });
    bar3.add(gen_csr);

    signcsr.setText("Sign Request");
    signcsr.setFocusable(false);
    signcsr.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    signcsr.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    signcsr.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        signcsrActionPerformed(evt);
      }
    });
    bar3.add(signcsr);

    bar2.setBorderPainted(false);
    bar2.setFocusable(false);

    rename_alias.setText("Rename Alias");
    rename_alias.setFocusable(false);
    rename_alias.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    rename_alias.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    rename_alias.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        rename_aliasActionPerformed(evt);
      }
    });
    bar2.add(rename_alias);

    validate.setText("Verify");
    validate.setFocusable(false);
    validate.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    validate.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    validate.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        validateActionPerformed(evt);
      }
    });
    bar2.add(validate);

    jButton1.setText("Delete");
    jButton1.setFocusable(false);
    jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton1ActionPerformed(evt);
      }
    });
    bar2.add(jButton1);

    reload.setText("Reload");
    reload.setFocusable(false);
    reload.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    reload.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    reload.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        reloadActionPerformed(evt);
      }
    });
    bar2.add(reload);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(bar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(bar4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
          .addComponent(bar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
          .addComponent(bar5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(bar3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(bar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(bar2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(bar3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(bar4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(bar5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
        .addContainerGap())
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void gen_rootActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gen_rootActionPerformed
    do_gen(true);
  }//GEN-LAST:event_gen_rootActionPerformed

  private void gen_clientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gen_clientActionPerformed
    do_gen(false);
  }//GEN-LAST:event_gen_clientActionPerformed

  private void reloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reloadActionPerformed
    reload();
  }//GEN-LAST:event_reloadActionPerformed

  private void rename_aliasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rename_aliasActionPerformed
    rename();
  }//GEN-LAST:event_rename_aliasActionPerformed

  private void validateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_validateActionPerformed
    verify();
  }//GEN-LAST:event_validateActionPerformed

  private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    delete();
  }//GEN-LAST:event_jButton1ActionPerformed

  private void gen_csrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gen_csrActionPerformed
    gen_csr();
  }//GEN-LAST:event_gen_csrActionPerformed

  private void signcsrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signcsrActionPerformed
    sign_csr();
  }//GEN-LAST:event_signcsrActionPerformed

  private void export_ksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_export_ksActionPerformed
    export_ks();
  }//GEN-LAST:event_export_ksActionPerformed

  private void import_ksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_import_ksActionPerformed
    import_ks();
  }//GEN-LAST:event_import_ksActionPerformed

  private void export_keyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_export_keyActionPerformed
    export_key();
  }//GEN-LAST:event_export_keyActionPerformed

  private void export_crtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_export_crtActionPerformed
    export_crt();
  }//GEN-LAST:event_export_crtActionPerformed

  private void import_keyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_import_keyActionPerformed
    import_key();
  }//GEN-LAST:event_import_keyActionPerformed

  private void detailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detailsActionPerformed
    details();
  }//GEN-LAST:event_detailsActionPerformed

  private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    export_alias();
  }//GEN-LAST:event_jButton2ActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JToolBar bar1;
  private javax.swing.JToolBar bar2;
  private javax.swing.JToolBar bar3;
  private javax.swing.JToolBar bar4;
  private javax.swing.JToolBar bar5;
  private javax.swing.JButton details;
  private javax.swing.JButton export_crt;
  private javax.swing.JButton export_key;
  private javax.swing.JButton export_ks;
  private javax.swing.JButton gen_client;
  private javax.swing.JButton gen_csr;
  private javax.swing.JButton gen_root;
  private javax.swing.JButton import_key;
  private javax.swing.JButton import_ks;
  private javax.swing.JButton jButton1;
  private javax.swing.JButton jButton2;
  private javax.swing.JButton jButton4;
  private javax.swing.JScrollPane jScrollPane2;
  private javax.swing.JButton reload;
  private javax.swing.JButton rename_alias;
  private javax.swing.JButton signcsr;
  private javax.swing.JTable table;
  private javax.swing.JButton validate;
  // End of variables declaration//GEN-END:variables

  public static void main(String[] args) {
    if (args.length != 3) {
      usage();
      return;
    }
    switch (args[0]) {
      case "open": {
        KeyMgr dialog = new KeyMgr(args[1], args[2]);
        dialog.setVisible(true);
        break;
      }
      case "create": {
        KeyMgmt keys = new KeyMgmt(args[1], args[2]);
        keys.create();
        KeyMgr dialog = new KeyMgr(keys);
        dialog.setVisible(true);
        break;
      }
    }
    System.exit(0);
  }

  private static void usage() {
    JFLog.log("JFKeyMgr");
    JFLog.log("usage : open keystore.ks password");
    JFLog.log("usage : create keystore.ks password");
  }

  private void reload() {
    keys.open();
    KeyStore ks = keys.getKeyStore();
    char[] pwd = keys.getKeyStorePass().toCharArray();
    model.setRowCount(0);
    try {
      int cnt = 0;
      Enumeration<String> aliases = ks.aliases();
      while(aliases.hasMoreElements()) {
        String alias = aliases.nextElement();
        Certificate crt = ks.getCertificate(alias);
        Key key = ks.getKey(alias, pwd);
        model.addRow(new String[] {alias, key.toString(), crt.toString()});
        cnt++;
      }
      if (debug) JFLog.log("Aliases found:" + cnt);
    } catch (Exception e) {
      JFLog.log(e);
    }
    table.revalidate();
    repaint();
  }

  private void do_gen(boolean gen_root) {
    GenKeyPair dialog = new GenKeyPair(null, true, keys, gen_root);
    dialog.setRootAlias(root);
    dialog.setVisible(true);
    reload();
  }

  private void rename() {
    int row = table.getSelectedRow();
    if (row == -1) return;
    String alias = (String)model.getValueAt(row, 0);
    String newalias = JFAWT.getString("New Alias Name", alias);
    if (newalias == null || newalias.equals(alias)) return;
    KeyStore.Entry entry = keys.getEntry(alias);
    if (debug) JFLog.log("entry=" + entry);
    keys.deleteEntry(alias);
    keys.setEntry(newalias, entry);
    keys.save();
    reload();
  }

  private void verify() {
    int row = table.getSelectedRow();
    if (row == -1) return;
    String alias = (String)model.getValueAt(row, 0);
    if (alias == null || alias.equals(root)) return;

    keys.setRootAlias(root);
    if (keys.verify(alias)) {
      JFAWT.showMessage("Verify", "Key is verified!");
    } else {
      JFAWT.showMessage("Verify", "Key is NOT verified!");
    }
  }

  private void delete() {
    int row = table.getSelectedRow();
    if (row == -1) return;
    String alias = (String)model.getValueAt(row, 0);
    if (alias == null) return;
    keys.deleteEntry(alias);
    keys.save();
    reload();
  }

  public void gen_csr() {
    int row = table.getSelectedRow();
    if (row == -1) return;
    String alias = (String)model.getValueAt(row, 0);
    if (alias == null) return;
    String csr = JFAWT.getSaveFile(alias + ".csr");
    //NOTE:using keyStorePass for keyPass
    keys.createCertSignRequest(alias, keys.getKeyStorePass(), csr);
    reload();
  }

  public void sign_csr() {
    int row = table.getSelectedRow();
    if (row == -1) return;
    String alias = (String)model.getValueAt(row, 0);
    if (alias == null) return;
    String in_csr = JFAWT.getOpenFile(".");
    String out_crt = JFAWT.getSaveFile(alias + ".crt");
    //NOTE:using keyStorePass for keyPass
    keys.signRequest(alias, keys.getKeyStorePass(), in_csr, out_crt);
    reload();
  }

  public void export_ks() {
    String file = JFAWT.getSaveFile("export.ks");
    if (file == null) return;
    try{
      FileOutputStream fos = new FileOutputStream(file);
      keys.save(fos);
      fos.close();
    } catch (Exception e) {
      JFLog.log(e);
    }
  }

  public void import_ks() {
    String file = JFAWT.getOpenFile(".");
    if (file == null) return;
    String pass = JFAWT.getString("Enter password", "password");
    KeyMgmt newkeys = new KeyMgmt(file, pass);
    KeyStore newks = newkeys.getKeyStore();
    KeyStore ks = keys.getKeyStore();
    try {
      int cnt = 0;
      Enumeration<String> aliases = newks.aliases();
      KeyStore.PasswordProtection pp = new KeyStore.PasswordProtection(pass.toCharArray());
      while(aliases.hasMoreElements()) {
        String alias = aliases.nextElement();
        KeyStore.Entry entry = newks.getEntry(alias, pp);
        ks.setEntry(alias, entry, pp);
        cnt++;
      }
      if (debug) JFLog.log("Aliases found:" + cnt);
    } catch (Exception e) {
      JFLog.log(e);
    }
    keys.save();
    reload();
  }

  public void export_key() {
    int row = table.getSelectedRow();
    if (row == -1) return;
    String alias = (String)model.getValueAt(row, 0);
    if (alias == null) return;
    String file = JFAWT.getSaveFile(alias + ".key");
    if (file == null) return;
    String keypass = JFAWT.getString("Key Password", keys.getKeyStorePass());
    if (keypass == null) return;
    keys.exportKEY(alias, keypass, file);
  }

  public void export_crt() {
    int row = table.getSelectedRow();
    if (row == -1) return;
    String alias = (String)model.getValueAt(row, 0);
    if (alias == null) return;
    String file = JFAWT.getSaveFile(alias + ".crt");
    if (file == null) return;
    keys.exportCRT(alias, file);
  }

  public void import_key() {
    String file_key = JFAWT.getOpenFile(".", new String[][] {new String[] {".key", "Key"}});
    if (file_key == null) return;
    String file_crt = JFAWT.getOpenFile(".", new String[][] {new String[] {".crt", "Certificate"}});
    if (file_crt == null) return;
    String alias = JFAWT.getString("Enter Alias", "alias+" + keys.getCount());
    if (alias == null) return;
    String pass = JFAWT.getString("Enter Key Pass", keys.getKeyStorePass());
    if (pass == null) return;
    try {
      FileInputStream keyStream = new FileInputStream(file_key);
      FileInputStream certStream = new FileInputStream(file_crt);
      keys.loadKEYandCRT(alias, keyStream, certStream, pass);
      keyStream.close();
      certStream.close();
      keys.save();
    } catch (Exception e) {
      JFLog.log(e);
    }
    reload();
  }

  public void details() {
    int row = table.getSelectedRow();
    if (row == -1) return;
    String alias = (String)model.getValueAt(row, 0);
    if (alias == null) return;
    KeyStore ks = keys.getKeyStore();
    char[] pwd = keys.getKeyStorePass().toCharArray();
    try {
      StringBuilder txt = new StringBuilder();
      Certificate crt = ks.getCertificate(alias);
      Key key = ks.getKey(alias, pwd);
      txt.append(key.toString());
      txt.append(crt.toString());
      ViewLog log = new ViewLog(txt.toString().getBytes());
      log.setVisible(true);
    } catch (Exception e) {
      JFLog.log(e);
    }
  }

  public void export_alias() {
    int row = table.getSelectedRow();
    if (row == -1) return;
    String alias = (String)model.getValueAt(row, 0);
    if (alias == null) return;
    String file = JFAWT.getSaveFile("export.ks");
    if (file == null) return;
    try{
      KeyMgmt export = new KeyMgmt();
      export.setFile(file);
      export.setKeyStorePass(keys.getKeyStorePass());
      KeyStore.Entry entry = keys.getEntry(alias);
      export.setEntry(alias, entry);
      FileOutputStream fos = new FileOutputStream(file);
      export.save(fos);
      fos.close();
    } catch (Exception e) {
      JFLog.log(e);
    }
  }
}
