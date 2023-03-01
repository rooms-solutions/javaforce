package jfconfig;

/**
 * Created : Mar 4, 2012
 *
 * @author pquiring
 */

import java.io.*;
import java.util.*;
import javax.swing.*;

import javaforce.*;
import javaforce.awt.*;
import javaforce.linux.Linux;

public class UsersPanel extends javax.swing.JPanel {

  /**
   * Creates new form ConfigPanel
   */
  public UsersPanel() {
    initComponents();
    loadUsers();
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    split = new javax.swing.JSplitPane();
    jScrollPane1 = new javax.swing.JScrollPane();
    users = new javax.swing.JList();
    jTabbedPane1 = new javax.swing.JTabbedPane();
    jPanel2 = new javax.swing.JPanel();
    jLabel1 = new javax.swing.JLabel();
    username = new javax.swing.JTextField();
    resetPasswd = new javax.swing.JButton();
    jPanel1 = new javax.swing.JPanel();
    jScrollPane2 = new javax.swing.JScrollPane();
    groups = new javax.swing.JList();
    addGroup = new javax.swing.JButton();
    deleteGroup = new javax.swing.JButton();
    manageGroups = new javax.swing.JButton();
    addGroupCombo = new javax.swing.JComboBox();
    jToolBar1 = new javax.swing.JToolBar();
    back = new javax.swing.JButton();
    addUser = new javax.swing.JButton();
    deleteUser = new javax.swing.JButton();

    split.setDividerLocation(164);
    split.setResizeWeight(0.5);

    users.setModel(usersModel);
    users.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    users.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
      public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
        usersValueChanged(evt);
      }
    });
    jScrollPane1.setViewportView(users);

    split.setLeftComponent(jScrollPane1);

    jLabel1.setText("Username:");

    username.setEditable(false);

    resetPasswd.setText("Reset Password");
    resetPasswd.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        resetPasswdActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
      jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel2Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(jPanel2Layout.createSequentialGroup()
            .addComponent(jLabel1)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(username))
          .addGroup(jPanel2Layout.createSequentialGroup()
            .addComponent(resetPasswd)
            .addGap(0, 1249, Short.MAX_VALUE)))
        .addContainerGap())
    );
    jPanel2Layout.setVerticalGroup(
      jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel2Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel1)
          .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(resetPasswd)
        .addContainerGap(448, Short.MAX_VALUE))
    );

    jTabbedPane1.addTab("General", jPanel2);

    groups.setModel(groupsModel);
    groups.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    jScrollPane2.setViewportView(groups);

    addGroup.setText("Add Group");
    addGroup.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        addGroupActionPerformed(evt);
      }
    });

    deleteGroup.setText("Delete Group");
    deleteGroup.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        deleteGroupActionPerformed(evt);
      }
    });

    manageGroups.setText("Manage Groups");
    manageGroups.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        manageGroupsActionPerformed(evt);
      }
    });

    addGroupCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jScrollPane2)
          .addGroup(jPanel1Layout.createSequentialGroup()
            .addComponent(addGroupCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(addGroup)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(deleteGroup)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 933, Short.MAX_VALUE)
            .addComponent(manageGroups)))
        .addContainerGap())
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(addGroup)
          .addComponent(deleteGroup)
          .addComponent(manageGroups)
          .addComponent(addGroupCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap())
    );

    jTabbedPane1.addTab("Groups", jPanel1);

    split.setRightComponent(jTabbedPane1);

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

    addUser.setText("Add User");
    addUser.setFocusable(false);
    addUser.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    addUser.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    addUser.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        addUserActionPerformed(evt);
      }
    });
    jToolBar1.add(addUser);

    deleteUser.setText("Delete User");
    deleteUser.setFocusable(false);
    deleteUser.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    deleteUser.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    deleteUser.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        deleteUserActionPerformed(evt);
      }
    });
    jToolBar1.add(deleteUser);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
      .addComponent(split)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(split))
    );
  }// </editor-fold>//GEN-END:initComponents

  private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
    ConfigApp.This.setPanel(new MainPanel());
  }//GEN-LAST:event_backActionPerformed

  private void addUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addUserActionPerformed
    UserAddDialog dialog = new UserAddDialog(ConfigApp.This, true);
    dialog.setVisible(true);
    if (!dialog.accepted) return;
    ShellProcess sp = new ShellProcess();
    sp.log = true;
    ArrayList<String> cmd = new ArrayList<String>();
    cmd.add("sudo");
    cmd.add("useradd");
    cmd.add("-s");
    cmd.add("/bin/bash");
    cmd.add("-m");
    cmd.add("-U");  //create group with same name
    if (dialog.getAdmin()) {
      cmd.add("-G");
      cmd.add("sudo");
    }
    cmd.add("-c");
    cmd.add("\"" + dialog.getFullName() + "\"");
    cmd.add(dialog.getUserName());
    String output = sp.run(cmd, false);
    if (output == null) {
      JFAWT.showError("Error", "Failed to create new user.");
      return;
    }
    sp = new ShellProcess();
    sp.addResponse("Enter new UNIX password:", dialog.getPassWord() + "\n", true);
    sp.addResponse("Retype new UNIX password:", dialog.getPassWord() + "\n", true);
    output = sp.run(new String[] {"sudo", "passwd", dialog.getUserName()}, true);
    if ((output == null) || (output.indexOf("passwd: Authentication token manipulation error") != -1)) {
      JFAWT.showError("Error", "Failed to set new password.");
    }
    sp.log = false;
    loadUsers();
  }//GEN-LAST:event_addUserActionPerformed

  private void deleteUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteUserActionPerformed
    int idx = users.getSelectedIndex();
    if (idx == -1) return;
    //TODO : make sure not deleting last admin or current user
    String userName = usersModel.fields.get(idx)[0];
    ShellProcess sp = new ShellProcess();
    ArrayList<String> cmd = new ArrayList<String>();
    if (!JFAWT.showConfirm("Confirm", "Are you sure you want to delete user '" + userName + "'?")) return;
    cmd.add("sudo");
    cmd.add("userdel");
    cmd.add("-r");  //remove files
    cmd.add(userName);
    String output = sp.run(cmd, false);
    if (output == null) {
      JFAWT.showError("Error", "Failed to delete user.");
    }
    loadUsers();
  }//GEN-LAST:event_deleteUserActionPerformed

  private void resetPasswdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetPasswdActionPerformed
    int idx = users.getSelectedIndex();
    if (idx == -1) return;
    String userName = usersModel.fields.get(idx)[0];
    GetPasswordDialog dialog = new GetPasswordDialog(ConfigApp.This, true);
    dialog.setVisible(true);
    if (dialog.cancelled) return;
    ShellProcess sp = new ShellProcess();
    sp.addResponse("Enter new UNIX password:", dialog.getPassWord() + "\n", true);
    sp.addResponse("Retype new UNIX password:", dialog.getPassWord() + "\n", true);
    String output = sp.run(new String[] {"sudo" ,"passwd", userName}, true);
    if ((output == null) || (output.indexOf("passwd: Authentication token manipulation error") != -1)) {
      JFAWT.showError("Error", "Failed to set new password.");
    }
    loadUsers();
  }//GEN-LAST:event_resetPasswdActionPerformed

  private void addGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addGroupActionPerformed
    int idx = users.getSelectedIndex();
    if (idx == -1) return;
    if (addGroupCombo.getSelectedIndex() == -1) return;
    String userName = usersModel.fields.get(idx)[0];
    ShellProcess sp = new ShellProcess();
    ArrayList<String> cmd = new ArrayList<String>();
    cmd.add("sudo");
    cmd.add("usermod");
    cmd.add("-a");  //append group
    cmd.add("-G");
    cmd.add((String)addGroupCombo.getSelectedItem());
    cmd.add(userName);
    String output = sp.run(cmd, false);
    if (output == null) {
      JFAWT.showError("Error", "Failed to add user to group.");
      return;
    }
    loadGroups(idx);
  }//GEN-LAST:event_addGroupActionPerformed

  private void deleteGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteGroupActionPerformed
    int idx = users.getSelectedIndex();
    if (idx == -1) return;
    int gidx = groups.getSelectedIndex();
    if (gidx == -1) return;
    String userName = usersModel.fields.get(idx)[0];
    ShellProcess sp = new ShellProcess();
    ArrayList<String> cmd = new ArrayList<String>();
    cmd.add("sudo");
    cmd.add("usermod");
    cmd.add("-G");
    cmd.add(getUserGroups(idx, gidx));
    cmd.add(userName);
    String output = sp.run(cmd, false);
    if (output == null) {
      JFAWT.showError("Error", "Failed to add user to group.");
      return;
    }
    loadGroups(idx);
  }//GEN-LAST:event_deleteGroupActionPerformed

  private void usersValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_usersValueChanged
    int idx = users.getSelectedIndex();
    if (idx == -1) return;
    loadUser(idx);
  }//GEN-LAST:event_usersValueChanged

  private void manageGroupsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageGroupsActionPerformed
    ConfigApp.This.setPanel(new GroupsPanel());
  }//GEN-LAST:event_manageGroupsActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton addGroup;
  private javax.swing.JComboBox addGroupCombo;
  private javax.swing.JButton addUser;
  private javax.swing.JButton back;
  private javax.swing.JButton deleteGroup;
  private javax.swing.JButton deleteUser;
  private javax.swing.JList groups;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JPanel jPanel2;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JScrollPane jScrollPane2;
  private javax.swing.JTabbedPane jTabbedPane1;
  private javax.swing.JToolBar jToolBar1;
  private javax.swing.JButton manageGroups;
  private javax.swing.JButton resetPasswd;
  private javax.swing.JSplitPane split;
  private javax.swing.JTextField username;
  private javax.swing.JList users;
  // End of variables declaration//GEN-END:variables

  private class ListModel extends DefaultListModel {
    public ArrayList<String[]> fields = new ArrayList<String[]>();
  }

  private ListModel usersModel = new ListModel(), groupsModel = new ListModel();

  public void loadUsers() {
    usersModel.clear();
    usersModel.fields.clear();
    try {
      FileInputStream fis = new FileInputStream("/etc/passwd");
      int len = fis.available();
      byte buf[] = new byte[len];
      int pos = 0;
      while (pos < len) {
        int read = fis.read(buf, pos, len-pos);
        if (read <= 0) throw new Exception("file error");
        pos += read;
      }
      fis.close();
      String passwd = new String(buf);
      String lns[] = passwd.split("\n");
      for(int a=0;a<lns.length;a++) {
        String f[] = lns[a].split(":", -1);
        //0=user : 1=x : 2=uid : 3=gid : 4=comment : 5=home : 6=shell
        if (f[0].equals("root")) continue;
        if (!f[6].equals("/bin/bash")) continue;
        usersModel.addElement(f[0]);
        usersModel.fields.add(f);
      }
    } catch (Exception e) {
      JFLog.log(e);
    }
    users.repaint();
    split.revalidate();
  }

  private void loadUser(int idx) {
    String f[] = usersModel.fields.get(idx);
    username.setText(f[0]);
    loadGroups(idx);
  }

  private void loadGroups(int idx) {
    groupsModel.clear();
    groupsModel.fields.clear();
    addGroupCombo.removeAllItems();
    String user = (String)users.getSelectedValue();
    ArrayList<String> groupList = new ArrayList<String>();
    try {
      FileInputStream fis = new FileInputStream("/etc/group");
      String group = new String(JF.readAll(fis));
      fis.close();
      String lns[] = group.split("\n");
      for(int a=0;a<lns.length;a++) {
        String f[] = lns[a].split(":", -1);
        //0=group : 1=x : 2=gid : 3=users
//        addGroupCombo.addItem(f[0]);
        groupList.add(f[0]);
        String u[] = f[3].split(",");
        for(int b=0;b<u.length;b++) {
          if (u[b].equals(user)) {
            groupsModel.addElement(f[0]);
            groupsModel.fields.add(f);
            break;
          }
        }
        if (f[0].equals(user)) {
          //you are always a member of your own group
          groupsModel.addElement(f[0]);
          groupsModel.fields.add(f);
        }
      }
    } catch (Exception e) {
      JFLog.log(e);
    }
    groups.repaint();
    String gl[] = groupList.toArray(new String[0]);
    Arrays.sort(gl);
    for(int a=0;a<gl.length;a++) {
      addGroupCombo.addItem(gl[a]);
    }
    addGroupCombo.repaint();
  }

  /** Returns groups that user is in, excluding gidx.*/
  private String getUserGroups(int uidx, int gidx) {
    String userName = (String)usersModel.getElementAt(uidx);
    StringBuffer out = new StringBuffer();
    for(int a=0;a<groupsModel.size();a++) {
      if (a == gidx) continue;  //exclude deleted one
      String f[] = groupsModel.fields.get(a);
      boolean ok = false;
      for(int b=0;b<f.length;b++) {
        if (f[b].equals(userName)) {ok = true; break;}
      }
      if (ok) {
        if (out.length() > 0) out.append(",");
        out.append((String)groupsModel.getElementAt(a));
      }
    }
    return out.toString();
  }
}
