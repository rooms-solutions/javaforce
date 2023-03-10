package jfconfig;

/**
 * Created : Mar 14, 2012
 *
 * @author pquiring
 */

import java.io.*;
import java.util.*;
import javax.swing.*;

import javaforce.*;
import javaforce.awt.*;
import javaforce.linux.*;

public class FirewallPanel extends javax.swing.JPanel {

  /**
   * Creates new form FirewallPanel
   */
  public FirewallPanel() {
    initComponents();
    loadRules();
    updateRules();
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
    addRule = new javax.swing.JButton();
    jButton2 = new javax.swing.JButton();
    jButton3 = new javax.swing.JButton();
    jButton4 = new javax.swing.JButton();
    jButton5 = new javax.swing.JButton();
    preview = new javax.swing.JButton();
    up = new javax.swing.JButton();
    down = new javax.swing.JButton();
    jScrollPane1 = new javax.swing.JScrollPane();
    rules = new javax.swing.JList();
    basicFirewall = new javax.swing.JCheckBox();

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

    addRule.setText("Add Rule");
    addRule.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        addRuleActionPerformed(evt);
      }
    });
    jToolBar1.add(addRule);

    jButton2.setText("Delete Rule");
    jButton2.setFocusable(false);
    jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    jButton2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton2ActionPerformed(evt);
      }
    });
    jToolBar1.add(jButton2);

    jButton3.setText("Edit Rule");
    jButton3.setFocusable(false);
    jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    jButton3.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton3ActionPerformed(evt);
      }
    });
    jToolBar1.add(jButton3);

    jButton4.setText("Save");
    jButton4.setFocusable(false);
    jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    jButton4.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton4ActionPerformed(evt);
      }
    });
    jToolBar1.add(jButton4);

    jButton5.setText("Apply");
    jButton5.setFocusable(false);
    jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    jButton5.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton5ActionPerformed(evt);
      }
    });
    jToolBar1.add(jButton5);

    preview.setText("Preview");
    preview.setFocusable(false);
    preview.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    preview.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    preview.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        previewActionPerformed(evt);
      }
    });
    jToolBar1.add(preview);

    up.setText("Up");
    up.setFocusable(false);
    up.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    up.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    up.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        upActionPerformed(evt);
      }
    });
    jToolBar1.add(up);

    down.setText("Down");
    down.setFocusable(false);
    down.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    down.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    down.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        downActionPerformed(evt);
      }
    });
    jToolBar1.add(down);

    rules.setModel(rulesModel);
    rules.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    jScrollPane1.setViewportView(rules);

    basicFirewall.setText("Enable Basic Firewall Protection (block all inbound traffic to localhost)");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jScrollPane1)
          .addComponent(basicFirewall, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(basicFirewall)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
        .addContainerGap())
    );
  }// </editor-fold>//GEN-END:initComponents

  private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
    ConfigApp.setPanel(new NetworkPanel());
  }//GEN-LAST:event_backActionPerformed

  private void addRuleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRuleActionPerformed
    addRule();
  }//GEN-LAST:event_addRuleActionPerformed

  private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    int idx = rules.getSelectedIndex();
    if (idx == -1) return;
    delRule(idx);
  }//GEN-LAST:event_jButton2ActionPerformed

  private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    int idx = rules.getSelectedIndex();
    if (idx == -1) return;
    editRule(idx);
  }//GEN-LAST:event_jButton3ActionPerformed

  private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
    saveRules();
  }//GEN-LAST:event_jButton4ActionPerformed

  private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
    applyRules();
  }//GEN-LAST:event_jButton5ActionPerformed

  private void previewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previewActionPerformed
    String str = previewRules();
    JFAWT.showMessage("Preview", str);
  }//GEN-LAST:event_previewActionPerformed

  private void upActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upActionPerformed
    int idx = rules.getSelectedIndex();
    if (idx == -1) return;
    up(idx);
  }//GEN-LAST:event_upActionPerformed

  private void downActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downActionPerformed
    int idx = rules.getSelectedIndex();
    if (idx == -1) return;
    down(idx);
  }//GEN-LAST:event_downActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton addRule;
  private javax.swing.JButton back;
  private javax.swing.JCheckBox basicFirewall;
  private javax.swing.JButton down;
  private javax.swing.JButton jButton2;
  private javax.swing.JButton jButton3;
  private javax.swing.JButton jButton4;
  private javax.swing.JButton jButton5;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JToolBar jToolBar1;
  private javax.swing.JButton preview;
  private javax.swing.JList rules;
  private javax.swing.JButton up;
  // End of variables declaration//GEN-END:variables

  private DefaultListModel<String> rulesModel = new DefaultListModel<>();

  public static class Rule {
    public boolean enabled;
    public String name;
    public int type;  //see FirewallRuleDialog.java
    public String opts;
  }

  public static class Config {
    public Rule rule[];
    public boolean basicFirewall;
  }

  private Config config;
  private String configFolder = "/etc/jfconfig.d/";
  private String configFile = "firewall.xml";

  private void loadRules() {
    config = new Config();
    config.rule = new Rule[0];
    try {
      XML xml = new XML();
      FileInputStream fis = new FileInputStream(configFolder + configFile);
      xml.read(fis);
      xml.writeClass(config);
    } catch (FileNotFoundException e1) {
      defaultConfig();
    } catch (Exception e2) {
      JFLog.log(e2);
      defaultConfig();
    }
    basicFirewall.setSelected(config.basicFirewall);
  }

  private void saveRules() {
    config.basicFirewall = basicFirewall.isSelected();
    try {
      XML xml = new XML();
      File tmpFile = File.createTempFile("firewall", ".xml");
      FileOutputStream fos = new FileOutputStream(tmpFile);
      xml.readClass("firewall", config);
      xml.write(fos);
      fos.close();
      Linux.mkdir(configFolder);
      if (!Linux.copyFile(tmpFile.getAbsolutePath(), configFolder + configFile)) {
        tmpFile.delete();
        throw new Exception("file io error");
      }
      tmpFile.delete();
    } catch (Exception e) {
      JFLog.log(e);
    }
  }

  private void defaultConfig() {
    config = new Config();
    config.rule = new Rule[0];
  }

  private void up(int idx) {
    if (idx == 0) return;
    Rule tmp = config.rule[idx-1];
    config.rule[idx-1] = config.rule[idx];
    config.rule[idx] = tmp;
    updateRules();
    rules.setSelectedIndex(idx-1);
  }

  private void down(int idx) {
    if (idx == config.rule.length-1) return;
    Rule tmp = config.rule[idx+1];
    config.rule[idx+1] = config.rule[idx];
    config.rule[idx] = tmp;
    updateRules();
    rules.setSelectedIndex(idx+1);
  }

  private String getRuleString(Rule rule) {
    String ret = "";
    switch (rule.type) {
      case 0: ret += "(exc)"; break;
      case 1: ret += "(nat)"; break;
      case 2: ret += "(pf)"; break;
      case 3: ret += "(dmz)"; break;
    }
    ret += " ";
    ret += rule.name;
    return ret;
  }

  private void updateRules() {
    rulesModel.clear();
    for(int a=0;a<config.rule.length;a++) {
      rulesModel.addElement(getRuleString(config.rule[a]));
    }
  }

  private void addRule() {
    FirewallRuleDialog dialog = new FirewallRuleDialog(ConfigApp.This, true, null, "rule-" + (config.rule.length+1));
    dialog.setVisible(true);
    if (!dialog.accepted) return;
    Rule newRule = new Rule();
    newRule.name = dialog.getName();
    newRule.enabled = dialog.getEnabled();
    newRule.type = dialog.getRuleType();
    newRule.opts = dialog.getOpts();
    config.rule = Arrays.copyOf(config.rule, config.rule.length + 1);
    config.rule[config.rule.length-1] = newRule;
    updateRules();
  }

  private void delRule(int idx) {
    Rule rule = config.rule[idx];
    if (!JFAWT.showConfirm("Confirm", "Are you sure you want to delete '" + rule.name + "'?")) return;
    int len = config.rule.length;
    Rule newList[] = new Rule[len-1];
    System.arraycopy(config.rule, 0, newList, 0, idx);
    System.arraycopy(config.rule, idx+1, newList, idx, len - idx - 1);
    config.rule = newList;
    updateRules();
  }

  private void editRule(int idx) {
    Rule rule = config.rule[idx];
    FirewallRuleDialog dialog = new FirewallRuleDialog(ConfigApp.This, true, rule, null);
    dialog.setVisible(true);
    if (!dialog.accepted) return;
    rule.name = dialog.getName();
    rule.enabled = dialog.getEnabled();
    rule.type = dialog.getRuleType();
    rule.opts = dialog.getOpts();
    updateRules();
  }

  private void applyRules() {
    //save previewRules() to /etc/jfconfig.d/firewall.sh
    try {
      File tmpFile = File.createTempFile("firewall", ".sh");
      FileOutputStream fos = new FileOutputStream(tmpFile);
      fos.write("#!/bin/bash\n".getBytes());
      fos.write("iptables -F\n".getBytes());
      String rules = previewRules();
      fos.write(rules.getBytes());
      fos.close();
      if (!Linux.copyFile(tmpFile.getAbsolutePath(), "/etc/jfconfig.d/firewall.sh")) {
        tmpFile.delete();
        throw new Exception("file io error");
      }
      if (!Linux.setExec("/etc/jfconfig.d/firewall.sh")) {
        tmpFile.delete();
        throw new Exception("file io error");
      }
      tmpFile.delete();
    } catch (Exception e) {
      JFLog.log(e);
      JFAWT.showError("Error", "Failed to save rules to /etc/jfconfig.d/firewall.sh");
      return;
    }
    //exec /etc/jfconfig.d/firewall.sh to apply now
    try {
      ShellProcess sp = new ShellProcess();
      String cmd[] = {"sudo", "/etc/jfconfig.d/firewall.sh"};
      sp.run(cmd, false);
    } catch (Exception e) {
      JFLog.log(e);
      JFAWT.showError("Error", "Failed to exec /etc/jfconfig.d/firewall.sh");
      return;
    }
    //add /etc/jfconfig.d/firewall.sh to /etc/rc.local for next reboot
    try {
      FileInputStream fis = new FileInputStream("/etc/rc.local");
      byte data[] = JF.readAll(fis);
      String str = new String(data);
      String lns[] = str.split("\n");
      int exitIdx = -1;
      for(int a=0;a<lns.length;a++) {
        if (lns[a].startsWith("exit ")) exitIdx = a;
        if (lns[a].indexOf("/etc/jfconfig.d/firewall.sh") != -1) return;  //already done
      }

      File tmpFile = File.createTempFile("rc_local", ".tmp");
      FileOutputStream fos = new FileOutputStream(tmpFile);
      for(int a=0;a<lns.length;a++) {
        if (a == exitIdx) {
          fos.write("/etc/jfconfig.d/firewall.sh\n".getBytes());
        }
        fos.write(lns[a].getBytes());
        fos.write("\n".getBytes());
      }
      if (exitIdx == -1) {
        fos.write("/etc/jfconfig.d/firewall.sh\n".getBytes());
      }
      fos.close();
      if (!Linux.copyFile(tmpFile.getAbsolutePath(), "/etc/rc.local")) {
        tmpFile.delete();
        throw new Exception("file io error");
      }
      if (!Linux.setExec("/etc/rc.local")) {
        tmpFile.delete();
        throw new Exception("file io error");
      }
      tmpFile.delete();
    } catch (Exception e) {
      JFLog.log(e);
      JFAWT.showError("Error", "Failed to add firewall.sh to /etc/rc.local");
    }
  }

/*
 * iptables -F
 * iptables -A chain <spec>
 * chains : INPUT, FORWARD, OUTPUT
 * spec: -p <protocol:tcp,udp,icmp,all>
 * spec: -s <source/mask>[,...]
 * spec: -d <dest/mask>[,...]
 * spec: --sport <sourceport>[:<sourceportrange>]
 * spec: --dport <destport>[:<destportrange>]
 *
 * Config file : /etc/jfconfig.d/firewall.sh (run from /etc/rc.local)
 */

  private String getOpt(String opt, String opts) {
    //opts = "protocol=x;port=y;etc"
    String ol[] = opts.split(";");
    for(int a=0;a<ol.length;a++) {
      String f[] = ol[a].split("=");
      if (f[0].equals(opt)) return f[1];
    }
    return "";
  }

  private String previewRules() {
    StringBuilder str = new StringBuilder();
    boolean enable_ip_forwarding = false;
    boolean dmz = false, warned = false, nat = false;
    for(int a=0;a<config.rule.length;a++) {
      Rule rule = config.rule[a];
      if (!rule.enabled) continue;
      str.append("#" + rule.name + "\n");
      String opts = rule.opts;
      switch(rule.type) {
        case 0:  //exception for localhost
          //iptables -A INPUT -p $PROTOCOL -s ! 127.0.0.1 --dport $PORT -j $ACTION
          str.append("iptables -A INPUT -p ");
          str.append(getOpt("protocol", opts));
          str.append(" ! -s 127.0.0.1/32");
          str.append(" --dport ");
          str.append(getOpt("port", opts));
          str.append(" -j ");
          str.append(getOpt("action", opts));
          str.append("\n");
          break;
        case 1:  //nat
          //iptables -t nat -A POSTROUTING -o $WAN -j MASQUERADE
          //iptables -A FORWARD -i $WAN -o $LAN -m state --state RELATED,ESTABLISHED -j ACCEPT
          //iptables -A FORWARD -i $LAN -o $WAN -j ACCEPT
          enable_ip_forwarding = true;
          if (nat) {
            JFAWT.showError("Warning", "You have more than one NAT rule");
          }
          nat = true;
          str.append("iptables -t nat -A POSTROUTING -o ");
          str.append(getOpt("wan", opts));
          str.append(" -j MASQUERADE\n");
          str.append("iptables -A FORWARD -i ");
          str.append(getOpt("wan", opts));
          str.append(" -o ");
          str.append(getOpt("lan", opts));
          str.append(" -m state --state RELATED,ESTABLISHED -j ACCEPT\n");
          str.append("iptables -A FORWARD -i ");
          str.append(getOpt("lan", opts));
          str.append(" -o ");
          str.append(getOpt("wan", opts));
          str.append(" -j ACCEPT\n");
          break;
        case 2:  //port forwarding
          //iptables -t nat -A PREROUTING -p $PROTOCOL -i $WAN --dport $PORT -j DNAT --to $IP:$DPORT
          //iptables -A FORWARD -p $PROTOCOL -i $WAN -d $IP --dport $DPORT -j ACCEPT
          enable_ip_forwarding = true;
          if ((dmz) && (!warned)) {
            warned = true;
            JFAWT.showError("Warning", "You have a port forwarding rule after your DMZ rule.\nYou must move the DMZ after all port forwarding rules, or they will not work.");
          }
          str.append("iptables -t nat -A PREROUTING -p tcp -i ");
          str.append(getOpt("wan", opts));
          str.append(" --dport ");
          int port = JF.atoi(getOpt("port", opts));
          str.append(port);
          int length = JF.atoi(getOpt("length", opts));
          if (length > 1) {
            str.append(":");
            str.append( (port + length - 1) );
          }
          str.append(" -j DNAT --to ");
          str.append(getOpt("ip", opts));
          str.append(":");
          str.append(getOpt("dport", opts));
          str.append("\n");
          str.append("iptables -A FORWARD -p ");
          str.append(getOpt("protocol", opts));
          str.append(" -i ");
          str.append(getOpt("wan", opts));
          str.append(" -d ");
          str.append(getOpt("ip", opts));
          str.append(" --dport ");
          str.append(getOpt("dport", opts));
          str.append(" -j ACCEPT\n");
          break;
        case 3:  //dmz
          //DMZ should be compatible with NATing (there will be two MASQUERADEs -- not an issue)
          //iptables -t nat -A PREROUTING -i $WAN [-d $PUBLICIP] -j DNAT --to $PRIVATEIP
          //iptables -A FORWARD -m state --state RELATED,ESTABLISHED -j ACCEPT
          //iptables -A FORWARD -i $WAN -d $PRIVATEIP -m state --state NEW -j ACCEPT
          //iptables -t nat -A POSTROUTING -o $WAN -j MASQUERADE
          enable_ip_forwarding = true;
          dmz = true;
          str.append("iptables -t nat -A PREROUTING -i ");
          str.append(getOpt("wan", opts));
          if (getOpt("publicip", opts).length() > 0) {
            str.append(" -d ");
            str.append(getOpt("publicip", opts));
          }
          str.append(" -j DNAT --to ");
          str.append(getOpt("privateip", opts));
          str.append("\n");
          str.append("iptables -A FORWARD -m state --state RELATED,ESTABLISHED -j ACCEPT\n");
          str.append("iptables -A FORWARD -i ");
          str.append(getOpt("wan", opts));
          str.append(" -d ");
          str.append(getOpt("privateip", opts));
          str.append(" -m state --state NEW -j ACCEPT\n");
          str.append("iptables -t nat -A POSTROUTING -o ");
          str.append(getOpt("wan", opts));
          str.append(" -j MASQUERADE\n");
          break;
      }
    }
    if (enable_ip_forwarding) {
      str.append("#enable IP forwarding\n");
      str.append("echo 1 > /proc/sys/net/ipv4/ip_forward\n");
      str.append("iptables -P FORWARD DROP\n");
    }
    if (config.basicFirewall) {
      str.append("#basicFirewall rule\n");
      str.append("iptables -A INPUT -s 127.0.0.1/32 -j ACCEPT\n");  //not sure why I need this?
      str.append("iptables -A INPUT -m state --state RELATED,ESTABLISHED -j ACCEPT\n");
      str.append("iptables -A INPUT -m state --state NEW -j DROP\n");
    }
    return str.toString();
  }
}
