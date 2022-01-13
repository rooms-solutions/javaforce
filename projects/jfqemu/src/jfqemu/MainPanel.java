package jfqemu;

/**
 * Created : Mar 19 ,2012
 *
 * @author pquiring
 */

import java.io.*;
import java.util.*;
import java.net.*;
import javax.swing.*;

import javaforce.*;
import javaforce.awt.*;
import javaforce.jbus.*;

public class MainPanel extends javax.swing.JPanel {

  /**
   * Creates new form MainPanel
   */
  public MainPanel(JFrame frame) {
    initComponents();
    JFLog.init(JF.getUserPath() + "/.jfqemu.log", true);
    this.frame = frame;
    listVMs();
    if (JF.isJFLinux()) {
      jbusClient = new JBusClient("org.jflinux.jfqemu.Client" + Math.abs(new Random().nextInt()), new JBusMethods());
      jbusClient.start();
    } else {
      jflinuxOnly.setVisible(false);
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

    jScrollPane1 = new javax.swing.JScrollPane();
    vmlist = new javax.swing.JList();
    newVM = new javax.swing.JButton();
    startVM = new javax.swing.JButton();
    editVM = new javax.swing.JButton();
    jLabel1 = new javax.swing.JLabel();
    name = new javax.swing.JTextField();
    jLabel2 = new javax.swing.JLabel();
    os = new javax.swing.JTextField();
    jLabel3 = new javax.swing.JLabel();
    state = new javax.swing.JTextField();
    jflinuxOnly = new javax.swing.JPanel();
    nicConfig = new javax.swing.JButton();
    startVMservice = new javax.swing.JButton();
    connectVMservice = new javax.swing.JButton();

    vmlist.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
    vmlist.setModel(vmlistModel);
    vmlist.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
      public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
        vmlistValueChanged(evt);
      }
    });
    jScrollPane1.setViewportView(vmlist);

    newVM.setText("Create New Virtual Machine");
    newVM.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        newVMActionPerformed(evt);
      }
    });

    startVM.setText("Start Virtual Machine");
    startVM.setEnabled(false);
    startVM.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        startVMActionPerformed(evt);
      }
    });

    editVM.setText("Edit Virtual Machine");
    editVM.setEnabled(false);
    editVM.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        editVMActionPerformed(evt);
      }
    });

    jLabel1.setText("Name:");

    name.setEditable(false);

    jLabel2.setText("OS:");

    os.setEditable(false);

    jLabel3.setText("State:");

    state.setEditable(false);

    nicConfig.setText("Configure Network Devices");
    nicConfig.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        nicConfigActionPerformed(evt);
      }
    });

    startVMservice.setText("Start VM as a Service");
    startVMservice.setEnabled(false);
    startVMservice.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        startVMserviceActionPerformed(evt);
      }
    });

    connectVMservice.setText("Connect to VM running as a Service");
    connectVMservice.setEnabled(false);
    connectVMservice.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        connectVMserviceActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout jflinuxOnlyLayout = new javax.swing.GroupLayout(jflinuxOnly);
    jflinuxOnly.setLayout(jflinuxOnlyLayout);
    jflinuxOnlyLayout.setHorizontalGroup(
      jflinuxOnlyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jflinuxOnlyLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jflinuxOnlyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(nicConfig, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
          .addComponent(startVMservice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(connectVMservice, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE))
        .addContainerGap())
    );
    jflinuxOnlyLayout.setVerticalGroup(
      jflinuxOnlyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jflinuxOnlyLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(nicConfig)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(startVMservice)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(connectVMservice)
        .addContainerGap())
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(newVM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(startVM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(editVM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jLabel1)
              .addComponent(jLabel2)
              .addComponent(jLabel3))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(state)
              .addComponent(name)
              .addComponent(os)))
          .addComponent(jflinuxOnly, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jScrollPane1)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(newVM)
        .addGap(49, 49, 49)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel1)
          .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel2)
          .addComponent(os, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel3)
          .addComponent(state, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(18, 18, 18)
        .addComponent(startVM)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(editVM)
        .addGap(18, 18, 18)
        .addComponent(jflinuxOnly, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
  }// </editor-fold>//GEN-END:initComponents

  private void vmlistValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_vmlistValueChanged
    int idx = vmlist.getSelectedIndex();
    if (idx == -1) {
      name.setText("");
      os.setText("");
      return;
    }
    loadVM(idx);
  }//GEN-LAST:event_vmlistValueChanged

  private void newVMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newVMActionPerformed
    newVM();
  }//GEN-LAST:event_newVMActionPerformed

  private void editVMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editVMActionPerformed
    int idx = vmlist.getSelectedIndex();
    if (idx == -1) return;
    editVM(idx);
  }//GEN-LAST:event_editVMActionPerformed

  private void startVMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startVMActionPerformed
    int idx = vmlist.getSelectedIndex();
    if (idx == -1) return;
    startVM(idx);
  }//GEN-LAST:event_startVMActionPerformed

  private void startVMserviceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startVMserviceActionPerformed
    int idx = vmlist.getSelectedIndex();
    if (idx == -1) return;
    VM vm = vms.get(idx);
    String vmXML = vm.folder + "/jfqemu.xml";
    startVMasService(vmXML);
  }//GEN-LAST:event_startVMserviceActionPerformed

  private void connectVMserviceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectVMserviceActionPerformed
    int idx = vmlist.getSelectedIndex();
    if (idx == -1) return;
    connectVMasService(idx);
  }//GEN-LAST:event_connectVMserviceActionPerformed

  private void nicConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nicConfigActionPerformed
    NetworkDialog dialog = new NetworkDialog(null, true);
    dialog.setVisible(true);
  }//GEN-LAST:event_nicConfigActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton connectVMservice;
  private javax.swing.JButton editVM;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JPanel jflinuxOnly;
  private javax.swing.JTextField name;
  private javax.swing.JButton newVM;
  private javax.swing.JButton nicConfig;
  private javax.swing.JTextField os;
  private javax.swing.JButton startVM;
  private javax.swing.JButton startVMservice;
  private javax.swing.JTextField state;
  private javax.swing.JList vmlist;
  // End of variables declaration//GEN-END:variables

  public static ArrayList<VM> vms = new ArrayList<VM>();
  private DefaultListModel vmlistModel = new DefaultListModel();
  private ArrayList<File> vmfiles = new ArrayList<File>();
  private JFrame frame;
  private JBusClient jbusClient;

  private void listVM(File file) throws Exception {
    XML xml = new XML();
    FileInputStream fis = new FileInputStream(file);
    xml.read(fis);
    fis.close();
    VM vm = new VM();
    xml.writeClass(vm);
    vmlistModel.addElement(vm.name);
    vms.add(vm);
    vmfiles.add(file);
  }

  private void listVMs() {
    clearVM();
    vmlistModel.clear();
    vms.clear();
    vmfiles.clear();
    try {
      File myVMs = new File(JF.getUserPath() + "/jfqemu/");
      myVMs.mkdirs();
      File folders[] = myVMs.listFiles();
      for(int a=0;a<folders.length;a++) {
        if (!folders[a].exists()) return;
        if (!folders[a].isDirectory()) return;
        File xml = new File(folders[a].getAbsolutePath() + "/jfqemu.xml");
        if (xml.exists()) listVM(xml);
      }
    } catch (Exception e) {
      JFLog.log(e);
    }
  }

  private boolean isServiceActive(int id) {
    //just check if qemu is listening on the port
    try {
      Socket s = new Socket("127.0.0.1", 5900 + id);
      s.close();
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  private void clearVM() {
    name.setText("");
    os.setText("");
    state.setText("");
    startVM.setEnabled(false);
    editVM.setEnabled(false);
    startVMservice.setEnabled(false);
    connectVMservice.setEnabled(false);
  }

  private void loadVM(int idx) {
    VM vm = vms.get(idx);
    clearVM();
    name.setText(vm.name);
    os.setText(vm.os);
    startVM.setEnabled(true);
    editVM.setEnabled(true);
    if (vm.serviceID != -1) {
      if (isServiceActive(vm.serviceID)) {
        editVM.setEnabled(false);
        startVM.setEnabled(false);
        startVMservice.setEnabled(false);
        connectVMservice.setEnabled(true);
        state.setText("Active");
      } else {
        startVMservice.setEnabled(true);
        state.setText("Not Active");
      }
    }
  }

  private void newVM() {
    VMDialog dialog = new VMDialog(null, true, null);
    dialog.setVisible(true);
    if (!dialog.accepted) return;
    VM vm = new VM();
    dialog.getValues(vm);
    try {
      File vmPath = new File(vm.folder);
      vmPath.mkdirs();
      File vmXML = new File(vm.folder + "/jfqemu.xml");
      FileOutputStream fos = new FileOutputStream(vmXML);
      XML xml = new XML();
      xml.readClass("vm", vm);
      xml.write(fos);
      fos.close();
      if (vm.serviceID != -1) {
        addVMasService(vmXML.getAbsolutePath());
      }
    } catch (Exception e) {
      JFLog.log(e);
      JFAWT.showError("Error", "Failed to create VM");
    }
    listVMs();
  }

  private void editVM(int idx) {
    VM vm = vms.get(idx);
    int oldServiceID = vm.serviceID;
    VMDialog dialog = new VMDialog(null, true, vm);
    dialog.setVisible(true);
    if (!dialog.accepted) return;
    dialog.getValues(vm);
    try {
      File vmXML = new File(vm.folder + "/jfqemu.xml");
      FileOutputStream fos = new FileOutputStream(vmXML);
      XML xml = new XML();
      xml.readClass("vm", vm);
      xml.write(fos);
      fos.close();
      if (oldServiceID != vm.serviceID) {
        //serviceID is changing
        removeVMasService(vmXML.getAbsolutePath());
        if (vm.serviceID != -1) {
          addVMasService(vmXML.getAbsolutePath());
        }
      }
    } catch (Exception e) {
      JFLog.log(e);
      JFAWT.showError("Error", "Failed to edit VM");
    }
    listVMs();
  }

  private void startVM(int idx) {
    String cmd[] = vms.get(idx).getCMD(false);
    for(int a=0;a<cmd.length;a++) {
      System.out.print(cmd[a] + " ");
    }
    System.out.println("");
    try {
      Process p = Runtime.getRuntime().exec(cmd);
      System.exit(0);
    } catch (Exception e) {
      JFLog.log(e);
      JFAWT.showError("Error", "Failed to start VM");
    }
  }


  private void addVMasService(String xml) {
    try {
      jbusClient.call("org.jflinux.service.jfqemu", "addVM", "\"" + xml + "\"");
    } catch (Exception e) {
      JFLog.log(e);
    }
  }

  private void removeVMasService(String xml) {
    try {
      jbusClient.call("org.jflinux.service.jfqemu", "removeVM", "\"" + xml + "\"");
    } catch (Exception e) {
      JFLog.log(e);
    }
  }

  private void startVMasService(String xml) {
    try {
      jbusClient.call("org.jflinux.service.jfqemu", "startVM", "\"" + xml + "\"");
    } catch (Exception e) {
      JFLog.log(e);
    }
  }

  private void connectVMasService(int idx) {
    VM vm = vms.get(idx);
    //use a java-based vnc client of course
    String cmd[] = {"jfvnc", "127.0.0.1:" + vm.serviceID};
    try {
      Runtime.getRuntime().exec(cmd);
    } catch (Exception e) {
      JFLog.log(e);
      JFAWT.showError("Error", "Failed to start as service");
    }
  }

  public class JBusMethods {
  }
}
