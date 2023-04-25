/**
 * Created : Jun 8, 2012
 *
 * @author pquiring
 */

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;

import javaforce.*;
import javaforce.awt.*;

public class EditEntry extends javax.swing.JDialog {

  /**
   * Creates new form EntryDialog
   */
  public EditEntry(java.awt.Frame parent, boolean modal, XMLTree.XMLTag entry, DefaultTableModel table, int row) {
    super(parent, modal);
    initComponents();
    JFAWT.assignHotKey(this, cancel, KeyEvent.VK_ESCAPE);
//    JFAWT.assignHotKey(this, ok, KeyEvent.VK_ENTER);  //need to type into
    setPosition();
    orgEchoChar = password.getEchoChar();
    this.table = table;
    this.row = row;
    if (entry != null) {
      title.setText(entry.getArg("name"));
      int cnt = entry.getChildCount();
      for(int a=0;a<cnt;a++) {
        XMLTree.XMLTag child = entry.getChildAt(a);
        if (child.name.equals("username")) {
          username.setText(child.content);
          continue;
        }
        if (child.name.equals("password")) {
          password.setText(child.content);
          confirm.setText(child.content);
          continue;
        }
        if (child.name.equals("url")) {
          url.setText(child.content);
          continue;
        }
        if (child.name.equals("notes")) {
          notes.setText(child.content);
          continue;
        }
      }
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

    jLabel1 = new javax.swing.JLabel();
    title = new javax.swing.JTextField();
    jLabel2 = new javax.swing.JLabel();
    username = new javax.swing.JTextField();
    jLabel3 = new javax.swing.JLabel();
    password = new javax.swing.JPasswordField();
    jLabel4 = new javax.swing.JLabel();
    confirm = new javax.swing.JPasswordField();
    jLabel5 = new javax.swing.JLabel();
    url = new javax.swing.JTextField();
    jLabel6 = new javax.swing.JLabel();
    jScrollPane1 = new javax.swing.JScrollPane();
    notes = new javax.swing.JTextArea();
    cancel = new javax.swing.JButton();
    ok = new javax.swing.JButton();
    showPass = new javax.swing.JButton();
    genPass = new javax.swing.JButton();
    copyPassword = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("Edit Entry");

    jLabel1.setText("Title");

    jLabel2.setText("User name");

    jLabel3.setText("Password");

    jLabel4.setText("Confirm");

    jLabel5.setText("URL");

    jLabel6.setText("Notes");

    notes.setColumns(20);
    notes.setRows(5);
    jScrollPane1.setViewportView(notes);

    cancel.setText("Cancel");
    cancel.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cancelActionPerformed(evt);
      }
    });

    ok.setText("Ok");
    ok.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        okActionPerformed(evt);
      }
    });

    showPass.setText("Show Password");
    showPass.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        showPassActionPerformed(evt);
      }
    });

    genPass.setText("Generate Password");
    genPass.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        genPassActionPerformed(evt);
      }
    });

    copyPassword.setText("Copy Password");
    copyPassword.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        copyPasswordActionPerformed(evt);
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
              .addComponent(jLabel2)
              .addComponent(jLabel1))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(title)
              .addComponent(username)))
          .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jLabel3)
              .addComponent(jLabel4)
              .addComponent(jLabel5)
              .addComponent(jLabel6))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(password)
              .addComponent(confirm)
              .addComponent(url)
              .addComponent(jScrollPane1)))
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(cancel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ok))
              .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(genPass)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(copyPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(showPass)))))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel1)
          .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel2)
          .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel3)
          .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel4)
          .addComponent(confirm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel5)
          .addComponent(url, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addComponent(jLabel6)
            .addGap(0, 0, Short.MAX_VALUE))
          .addComponent(jScrollPane1))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(showPass)
          .addComponent(copyPassword)
          .addComponent(genPass))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(ok)
          .addComponent(cancel))
        .addContainerGap())
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
    dispose();
  }//GEN-LAST:event_cancelActionPerformed

  private void okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okActionPerformed
    String pass1 = new String(password.getPassword());
    String pass2 = new String(confirm.getPassword());
    if (!pass1.equals(pass2)) {
      JFAWT.showError("Error", "Passwords do not match");
      return;
    }
    String thisTitle = title.getText();
    if (thisTitle.length() == 0) return;
    int cnt = table.getRowCount();
    for(int a=0;a<cnt;a++) {
      if (a == row) continue;
      String otherTitle = (String)table.getValueAt(a, 0);
      if (thisTitle.equals(otherTitle)) {
        JFAWT.showError("Error", "That title already exists");
        return;
      }
    }
    accepted = true;
    dispose();
  }//GEN-LAST:event_okActionPerformed

  private void genPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genPassActionPerformed
    genPassword();
  }//GEN-LAST:event_genPassActionPerformed

  private void showPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showPassActionPerformed
    showPassword();
  }//GEN-LAST:event_showPassActionPerformed

  private void copyPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyPasswordActionPerformed
    copyPassword();
  }//GEN-LAST:event_copyPasswordActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton cancel;
  private javax.swing.JPasswordField confirm;
  private javax.swing.JButton copyPassword;
  private javax.swing.JButton genPass;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JLabel jLabel6;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JTextArea notes;
  private javax.swing.JButton ok;
  private javax.swing.JPasswordField password;
  private javax.swing.JButton showPass;
  private javax.swing.JTextField title;
  private javax.swing.JTextField url;
  private javax.swing.JTextField username;
  // End of variables declaration//GEN-END:variables

  public boolean accepted;
  private DefaultTableModel table;
  private int row;

  public void saveTo(XMLTree safe, XMLTree.XMLTag parent) {
    String pass = new String(password.getPassword());
    parent.setArg("name", title.getText());
    safe.addSetTag(parent, "username", "", username.getText());
    safe.addSetTag(parent, "password", "", pass);
    safe.addSetTag(parent, "url", "", url.getText());
    safe.addSetTag(parent, "notes", "", notes.getText());
  }
  private void init() {
    chars = new char[26 + 26 + 10 + 10];  //a-z A-Z 0-9 syms
    for(int i=0;i<26;i++) {
      chars[i] = (char)('a' + i);
      chars[i+26] = (char)('A' + i);
    }
    for(int n=0;n<10;n++) {
      chars[26+26+n] = (char)('0' + n);
    }
    chars[26+26+10+0] = '!';
    chars[26+26+10+1] = '@';
    chars[26+26+10+2] = '#';
    chars[26+26+10+3] = '$';
    chars[26+26+10+4] = '%';
    chars[26+26+10+5] = '^';
    chars[26+26+10+6] = '&';
    chars[26+26+10+7] = '*';
    chars[26+26+10+8] = '(';
    chars[26+26+10+9] = ')';
  }
  private static char chars[];
  private Random r;
  private char genChar(boolean sym, boolean amb) {
    char ch = chars[r.nextInt(26+26+10+(sym?10:0))];
    if (amb) {
      //avoid ambiguous chars
      if (ch == 'I' || ch == '1' || ch == 'l' || ch == '0' || ch == 'O') return genChar(sym, amb);
    }
    return ch;
  }
  private void genPassword() {
    if (chars == null) init();
    int len = MainPanel.This.config.passwordGeneratorLength;
    if (len < 4) len = 4;
    if (len > 64) len = 64;
    boolean sym = MainPanel.This.config.passwordGeneratorSymbols;
    boolean amb = MainPanel.This.config.passwordGeneratorAmbiguous;
    String newPass = "";
    r = new Random();
    for(int a=0;a<len;a++) {
      newPass += genChar(sym, amb);
    }
    password.setText(newPass);
    confirm.setText(newPass);
  }
  private char orgEchoChar;
  private void showPassword() {
    if (password.echoCharIsSet()) {
      password.setEchoChar((char)0);
      confirm.setEchoChar((char)0);
      showPass.setText("Hide Password");
    } else {
      password.setEchoChar(orgEchoChar);
      confirm.setEchoChar(orgEchoChar);
      showPass.setText("Show Password");
    }
  }
  private void copyPassword() {
    String pass = new String(password.getPassword());
    StringSelection ss = new StringSelection(pass);
    Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
    if (cb == null) return;
    JFLog.log("copy password");
    cb.setContents(ss, ss);
    MainPanel.This.startTimer();
  }
  private void setPosition() {
    Dimension d = getSize();
    Rectangle s = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
    setLocation(s.width/2 - d.width/2, s.height/2 - d.height/2);
  }
}
