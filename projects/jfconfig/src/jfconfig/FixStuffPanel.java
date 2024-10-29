package jfconfig;

/**
 * Created : Mar 14, 2012
 *
 * @author pquiring
 */

import java.io.*;
import java.util.*;
import javaforce.*;

import javaforce.linux.*;
import javaforce.awt.*;

public class FixStuffPanel extends javax.swing.JPanel {

  /**
   * Creates new form FixStuffPanel
   */
  public FixStuffPanel() {
    initComponents();
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jToolBar1 = new javax.swing.JToolBar();
    back = new javax.swing.JButton();
    jLabel2 = new javax.swing.JLabel();
    fixTomcat = new javax.swing.JButton();
    jTextArea1 = new javax.swing.JTextArea();
    jLabel3 = new javax.swing.JLabel();
    fixSSHslow = new javax.swing.JButton();
    jLabel4 = new javax.swing.JLabel();
    nopasswd = new javax.swing.JButton();
    jLabel5 = new javax.swing.JLabel();
    samba = new javax.swing.JButton();
    jLabel6 = new javax.swing.JLabel();
    grub_mkconfig = new javax.swing.JButton();
    jLabel7 = new javax.swing.JLabel();
    initramfs = new javax.swing.JButton();

    jToolBar1.setFloatable(false);
    jToolBar1.setRollover(true);

    back.setText("<Back");
    back.setFocusable(false);
    back.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    back.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    back.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        backActionPerformed(evt);
      }
    });
    jToolBar1.add(back);

    jLabel2.setText("Allow Tomcat to Listen on ports below 1024 (setcap)");

    fixTomcat.setText("Fix it");
    fixTomcat.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        fixTomcatActionPerformed(evt);
      }
    });

    jTextArea1.setColumns(20);
    jTextArea1.setEditable(false);
    jTextArea1.setRows(5);
    jTextArea1.setText("Another solution is to use a port forwarding rule in the Firewall\n  module to redirect port 80 to 8080 (443 -> 8443).");
    jTextArea1.setFocusable(false);

    jLabel3.setText("Establishing SSH connections are VERY slow. (Ubuntu)");

    fixSSHslow.setText("Fix it");
    fixSSHslow.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        fixSSHslowActionPerformed(evt);
      }
    });

    jLabel4.setText("Stop asking for password to run admin tasks (sudoers)");
    jLabel4.setToolTipText("You will be prompted twice for your password to do this.");

    nopasswd.setText("Fix it");
    nopasswd.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        nopasswdActionPerformed(evt);
      }
    });

    jLabel5.setText("Add Samba to authentication configuration (nsswitch.conf)");

    samba.setText("Fix it");
    samba.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        sambaActionPerformed(evt);
      }
    });

    jLabel6.setText("Reconfigure Boot Options (grub-mkconfig)");

    grub_mkconfig.setText("Fix it");
    grub_mkconfig.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        grub_mkconfigActionPerformed(evt);
      }
    });

    jLabel7.setText("Rebuild Boot Image (update-initramfs)");

    initramfs.setText("Fix it");
    initramfs.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        initramfsActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addComponent(jLabel2)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(fixTomcat))
          .addGroup(layout.createSequentialGroup()
            .addGap(12, 12, 12)
            .addComponent(jTextArea1))
          .addGroup(layout.createSequentialGroup()
            .addComponent(jLabel3)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(fixSSHslow))
          .addGroup(layout.createSequentialGroup()
            .addComponent(jLabel4)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(nopasswd))
          .addGroup(layout.createSequentialGroup()
            .addComponent(jLabel5)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
            .addComponent(samba))
          .addGroup(layout.createSequentialGroup()
            .addComponent(jLabel6)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(grub_mkconfig))
          .addGroup(layout.createSequentialGroup()
            .addComponent(jLabel7)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(initramfs)))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel4)
          .addComponent(nopasswd))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel2)
          .addComponent(fixTomcat))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jTextArea1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel3)
          .addComponent(fixSSHslow))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel5)
          .addComponent(samba))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel6)
          .addComponent(grub_mkconfig))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel7)
          .addComponent(initramfs))
        .addContainerGap(78, Short.MAX_VALUE))
    );
  }// </editor-fold>//GEN-END:initComponents

  private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
    ConfigApp.This.setPanel(new MainPanel());
  }//GEN-LAST:event_backActionPerformed

  private void fixTomcatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fixTomcatActionPerformed
    Linux.detectDistro();
    switch (Linux.distro) {
      case Debian:
        if (!Linux.isInstalled("libcap2-bin")) {
          if (!Linux.installPackage("libcap2-bin", "setcap tool")) {
            JFAWT.showError("Error", "Failed to install required tool");
            return;
          }
        }
        break;
      case Fedora:
        if (!Linux.isInstalled("libcap")) {
          if (!Linux.installPackage("libcap", "setcap tool")) {
            JFAWT.showError("Error", "Failed to install required tool");
            return;
          }
        }
        break;
      default:
        JFAWT.showError("Error", "Unsupported distro");
        return;
    }
    String javabin = "";
    try {
      File file = new File("/usr/bin/java");
      javabin = file.getCanonicalPath();  //resolves symlinks
    } catch (Exception e) {
      JFLog.log(e);
      JFAWT.showError("Error", "Failed to find java binary");
      return;
    }
    ShellProcess sp = new ShellProcess();
    ArrayList<String> cmd = new ArrayList<String>();
    cmd.add("sudo");
    cmd.add("setcap");
    cmd.add("'cap_net_bind_service=ep'");
    cmd.add(javabin);
    String output = sp.run(cmd, false);
    if (output == null) {
      JFLog.log("Failed to exec setcap");
      JFAWT.showError("Error", "Failed to exec setcap.");
      return;
    }
    JFAWT.showMessage("Notice", "Privledges granted.  You will have to do this again if the Java package is upgraded.");
  }//GEN-LAST:event_fixTomcatActionPerformed

  private void fixSSHslowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fixSSHslowActionPerformed
    //This is a fix for Ubuntu
    /* edit /etc/ssh/ssh/ssh_config
     * and comment these lines:
     *   GSSAPIAuthentication yes
     *   GSSAPIDelegateCredentials no
     */
    try {
      //copy /etc/ssh/ssh_config to tmpFile changing settings on the fly
      File tmpFile = File.createTempFile("ssh_config", "");
      FileOutputStream fos = new FileOutputStream(tmpFile);
      FileInputStream fis = new FileInputStream("/etc/ssh/ssh_config");
      BufferedReader br = new BufferedReader(new InputStreamReader(fis));
      String ln;
      while ((ln = br.readLine()) != null) {
        if (ln.trim().startsWith("GSSAPI")) ln = "#" + ln;  //comment them out
        fos.write(ln.getBytes());
        fos.write("\n".getBytes());
      }
      fos.close();
      fis.close();
      //copy tmpFile back to /etc/ssh/ssh_config
      if (!Linux.copyFile(tmpFile.getAbsolutePath(), "/etc/ssh/ssh_config"))
        throw new Exception("file copy error");
      JFAWT.showMessage("Notice", "SSH Config has been fixed.");
    } catch (Exception e) {
      JFLog.log(e);
      JFAWT.showError("Error", "Failed to apply settings.");
    }
  }//GEN-LAST:event_fixSSHslowActionPerformed

  private void nopasswdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nopasswdActionPerformed
    /* edit /etc/sudoers
     * %wheel for Fedora
     * %sudo for Ubuntu 11.10-
     * %admin for Ubuntu 12.04+
     */
    try {
      //copy /etc/sudoers to tmpFile
      File tmpFile_org = File.createTempFile("sudoers", "org");
      if (!Linux.copyFile("/etc/sudoers", tmpFile_org.getAbsolutePath()))
        throw new Exception("file copy error");
      File tmpFile_new = File.createTempFile("sudoers", "new");
      FileOutputStream fos = new FileOutputStream(tmpFile_new);
      FileInputStream fis = new FileInputStream(tmpFile_org);
      BufferedReader br = new BufferedReader(new InputStreamReader(fis));
      String ln;
      while ((ln = br.readLine()) != null) {
        if (ln.startsWith("%sudo") || ln.startsWith("%wheel") || ln.startsWith("%admin")) {
          if (ln.indexOf("NOPASSWD") == -1) {
            int idx = ln.indexOf(")");
            if (idx != -1) {
              ln = ln.substring(0, idx+1) + " NOPASSWD: " + ln.substring(idx+1);
            }
          }
        }
        fos.write((ln + "\n").getBytes());
      }
      fos.close();
      fis.close();
      //copy tmpFile back to /etc/sudoers
      if (!Linux.copyFile(tmpFile_new.getAbsolutePath(), "/etc/sudoers"))
        throw new Exception("file copy error");
      JFAWT.showMessage("Notice", "sudoers has been patched.");
    } catch (Exception e) {
      JFLog.log(e);
      JFAWT.showError("Error", "Failed to apply settings.");
    }
  }//GEN-LAST:event_nopasswdActionPerformed

  private void sambaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sambaActionPerformed
    fixSamba(false);
  }//GEN-LAST:event_sambaActionPerformed

  private void grub_mkconfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grub_mkconfigActionPerformed
    if (!Linux.runScript(new String[] {
      "grub-mkconfig --output=/boot/grub/grub.cfg"
    })) {
      JFAWT.showError("Error", "Failed to recreate boot options");
    } else {
      JFAWT.showMessage("Notice", "Boot Options reconfigured");
    }
  }//GEN-LAST:event_grub_mkconfigActionPerformed

  private void initramfsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_initramfsActionPerformed
    if (!Linux.runScript(new String[] {
      "update-initramfs -u"
    })) {
      JFAWT.showError("Error", "Failed to recreate initramfs");
    } else {
      JFAWT.showMessage("Notice", "Boot Image recreated");
    }
  }//GEN-LAST:event_initramfsActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton back;
  private javax.swing.JButton fixSSHslow;
  private javax.swing.JButton fixTomcat;
  private javax.swing.JButton grub_mkconfig;
  private javax.swing.JButton initramfs;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JLabel jLabel6;
  private javax.swing.JLabel jLabel7;
  private javax.swing.JTextArea jTextArea1;
  private javax.swing.JToolBar jToolBar1;
  private javax.swing.JButton nopasswd;
  private javax.swing.JButton samba;
  // End of variables declaration//GEN-END:variables

  public static void fixSamba(boolean quiet) {
    //add winbind to passwd, group and shadow
    //also fixes hosts lookup order
    try {
      //copy /etc/nsswitch.conf to tmpFile
      File tmpFile = File.createTempFile("nsswitch-new", ".conf");
      FileOutputStream fos = new FileOutputStream(tmpFile);
      FileInputStream fis = new FileInputStream("/etc/nsswitch.conf");
      BufferedReader br = new BufferedReader(new InputStreamReader(fis));
      String ln;
      while ((ln = br.readLine()) != null) {
        if (ln.startsWith("passwd:") || ln.startsWith("group:") || ln.startsWith("shadow:")) {
          if (ln.indexOf("winbind") == -1) {
            ln = ln + " winbind";
          }
        }
        if (ln.startsWith("hosts:")) {
          ln = "hosts:          files dns mdns4_minimal [NOTFOUND=return] mdns4";
        }
        fos.write((ln + "\n").getBytes());
      }
      fos.close();
      fis.close();
      //copy tmpFile back to /etc/nsswitch.conf
      if (!Linux.copyFile(tmpFile.getAbsolutePath(), "/etc/nsswitch.conf"))
        throw new Exception("file copy error");
      if (!quiet) JFAWT.showMessage("Notice", "Configuration patched successfully!");
    } catch (Exception e) {
      JFLog.log(e);
      JFAWT.showError("Error", "Failed to fix /etc/nsswitch.conf");
    }
  }

}
