/**
 * Created : Mar 31, 2012
 *
 * @author pquiring
 */

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import javaforce.*;
import javaforce.linux.*;
import javaforce.jbus.*;
import javax.swing.Icon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Logon extends javax.swing.JFrame implements ActionListener {

  /**
   * Creates new form LogonApp
   */
  public Logon() {
    This = this;
    try {
      initComponents();
      loadNetworkIcons();
      if (new File("/etc/.lastLogon").exists()) {
        Properties props = new Properties();
        props.load(new FileInputStream("/etc/.lastLogon"));
        lastUser = props.getProperty("lastUser");
        if (lastUser == null) lastUser = "";
        lastDomain = props.getProperty("lastDomain");
        if (lastDomain == null) lastDomain = "";
      } else {
        lastUser = "";
        lastDomain = "";
      }
      if ((!new File("/etc/domains.lst").exists()) || (!new File("/usr/bin/ntlm_auth").exists())) {
        remove(logonTo);
        remove(computer);
        //pack() later
        computer.addItem("localhost");
      } else {
        listComputers();
      }
      listUsers();
      if (lastUser.length() == 0) {
        username.setSelectedItem("");
        username.grabFocus();
      } else {
        username.setSelectedItem(lastUser);
        password.grabFocus();
      }
      JFImage icon = loadIcon("jflogon-power");
      icon = scaleIcon(icon, 18, 18);
      shutdown.setText("");
      shutdown.setIcon(icon);
      icon = loadIcon("jflogon-network");
      icon = scaleIcon(icon, 18, 18);
      network.setText("");
      network.setIcon(icon);
      pack();
      JFAWT.centerWindow(this);
      //connect to JBus
      if (jbusClient == null) {
        jbusClient = new JBusClient("org.jflinux.jflogon", new JBusMethods());
        jbusClient.start();
      }
      getWAPList();
    } catch (Exception e) {
      JFLog.log(e);
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

    xMenu = new javax.swing.JPopupMenu();
    Sleep = new javax.swing.JMenuItem();
    Reboot = new javax.swing.JMenuItem();
    Shutdown = new javax.swing.JMenuItem();
    NetworkPopup = new javax.swing.JPopupMenu();
    lUser = new javax.swing.JLabel();
    username = new javax.swing.JComboBox();
    lPass = new javax.swing.JLabel();
    password = new javax.swing.JPasswordField();
    logonTo = new javax.swing.JLabel();
    computer = new javax.swing.JComboBox();
    logon = new javax.swing.JButton();
    shutdown = new javax.swing.JButton();
    jLabel4 = new javax.swing.JLabel();
    network = new javax.swing.JButton();

    Sleep.setText("Sleep");
    Sleep.setToolTipText("");
    Sleep.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        SleepActionPerformed(evt);
      }
    });
    xMenu.add(Sleep);

    Reboot.setText("Reboot");
    Reboot.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        RebootActionPerformed(evt);
      }
    });
    xMenu.add(Reboot);

    Shutdown.setText("Shutdown");
    Shutdown.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        ShutdownActionPerformed(evt);
      }
    });
    xMenu.add(Shutdown);

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    lUser.setText("Username");

    username.setEditable(true);
    username.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyReleased(java.awt.event.KeyEvent evt) {
        usernameKeyReleased(evt);
      }
      public void keyTyped(java.awt.event.KeyEvent evt) {
        usernameKeyTyped(evt);
      }
    });

    lPass.setText("Password");

    password.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyReleased(java.awt.event.KeyEvent evt) {
        passwordKeyReleased(evt);
      }
      public void keyTyped(java.awt.event.KeyEvent evt) {
        passwordKeyTyped(evt);
      }
    });

    logonTo.setText("Logon to");

    logon.setText("Logon");
    logon.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        logonActionPerformed(evt);
      }
    });

    shutdown.setText("X");
    shutdown.setComponentPopupMenu(xMenu);
    shutdown.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        shutdownActionPerformed(evt);
      }
    });

    jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel4.setText("Logon");

    network.setText("W");
    network.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        networkActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addGroup(layout.createSequentialGroup()
            .addComponent(shutdown)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(network)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(logon))
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(lUser)
              .addComponent(lPass)
              .addComponent(logonTo))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(computer, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
              .addComponent(password)
              .addComponent(username, 0, 306, Short.MAX_VALUE))))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jLabel4)
        .addGap(18, 18, 18)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(lUser)
          .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(18, 18, 18)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(lPass)
          .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(18, 18, 18)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(logonTo)
          .addComponent(computer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(18, 18, 18)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(logon, javax.swing.GroupLayout.Alignment.TRAILING)
          .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
            .addComponent(shutdown)
            .addComponent(network)))
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void logonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logonActionPerformed
    doLogon();
  }//GEN-LAST:event_logonActionPerformed

  private void shutdownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shutdownActionPerformed
    xMenu.show(shutdown, 0, shutdown.getHeight());
  }//GEN-LAST:event_shutdownActionPerformed

  private void RebootActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RebootActionPerformed
    setVisible(false);
    if (!JFAWT.showConfirm("Confirm", "Are you sure you want to reboot?")) {
      setVisible(true);
      return;
    }
    Startup.reboot();
  }//GEN-LAST:event_RebootActionPerformed

  private void ShutdownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShutdownActionPerformed
    setVisible(false);
    if (!JFAWT.showConfirm("Confirm", "Are you sure you want to shutdown?")) {
      setVisible(true);
      return;
    }
    Startup.shutdown("-P");
  }//GEN-LAST:event_ShutdownActionPerformed

  private void SleepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SleepActionPerformed
    try {
      JF.exec(new String[] {"pm-suspend"});
    } catch (Exception e) {
      JFLog.log(e);
    }
  }//GEN-LAST:event_SleepActionPerformed

  private void networkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_networkActionPerformed
    if (networkTimer != null) {
      cancelNetworkConnection();
    } else {
      getVPNList();  //triggers showNetworkPopup();
    }
  }//GEN-LAST:event_networkActionPerformed

  private void usernameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernameKeyTyped
    int kc = evt.getKeyCode();
    int mod = evt.getModifiers();
    if ((kc == KeyEvent.VK_ENTER) && (mod == 0)) {
      logonActionPerformed(null);
    }
  }//GEN-LAST:event_usernameKeyTyped

  private void passwordKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordKeyTyped
    int kc = evt.getKeyCode();
    int mod = evt.getModifiers();
    if ((kc == KeyEvent.VK_ENTER) && (mod == 0)) {
      logonActionPerformed(null);
    }
  }//GEN-LAST:event_passwordKeyTyped

  private void passwordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordKeyReleased
    int kc = evt.getKeyCode();
    int mod = evt.getModifiers();
    if ((kc == KeyEvent.VK_ENTER) && (mod == 0)) {
      logonActionPerformed(null);
    }
  }//GEN-LAST:event_passwordKeyReleased

  private void usernameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernameKeyReleased
    int kc = evt.getKeyCode();
    int mod = evt.getModifiers();
    if ((kc == KeyEvent.VK_ENTER) && (mod == 0)) {
      logonActionPerformed(null);
    }
  }//GEN-LAST:event_usernameKeyReleased

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JPopupMenu NetworkPopup;
  private javax.swing.JMenuItem Reboot;
  private javax.swing.JMenuItem Shutdown;
  private javax.swing.JMenuItem Sleep;
  private javax.swing.JComboBox computer;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel lPass;
  private javax.swing.JLabel lUser;
  private javax.swing.JButton logon;
  private javax.swing.JLabel logonTo;
  private javax.swing.JButton network;
  private javax.swing.JPasswordField password;
  private javax.swing.JButton shutdown;
  private javax.swing.JComboBox username;
  private javax.swing.JPopupMenu xMenu;
  // End of variables declaration//GEN-END:variables

  public static JBusClient jbusClient;
  public static Logon This;
  private String lastUser, lastDomain;

  private void listUsers() {
    username.removeAllItems();
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
        username.addItem(f[0]);
      }
    } catch (Exception e) {
      JFLog.log(e);
    }
  }

  private void listComputers() {
    computer.removeAllItems();
    computer.addItem("localhost");
    server.clear();
    server.add("localhost");
    try {
      File file = new File("/etc/domains.lst");
      if (!file.exists()) return;
      FileInputStream fis = new FileInputStream(file);
      String lst = new String(JF.readAll(fis));
      String lns[] = lst.split("\n");
      for(int a=0;a<lns.length;a++) {
        if (lns[a].length() == 0) continue;
        String fs[] = lns[a].split(",");
        String _domain = null, _server = null;
        for(int f=0;f<fs.length;f++) {
          if (fs[f].startsWith("domain=")) _domain = fs[f].substring(7);
          if (fs[f].startsWith("server=")) _server = fs[f].substring(7);
        }
        if ((_domain != null) && (_server != null)) {
          computer.addItem(_domain);
          server.add(_server);
          if (_domain.equals(lastDomain)) {
            computer.setSelectedIndex(computer.getItemCount()-1);
          }
        }
      }
    } catch (Exception e) {
      JFLog.log(e);
    }
  }

  private ArrayList<String> server = new ArrayList<String>();
  private String errmsg;
  private String env_names[];
  private String env_values[];
  private String user, pass, domain;
  private boolean domainLogon;

  private void doLogon() {
    user = (String)username.getSelectedItem();
    pass = new String(password.getPassword());
    domain = (String)computer.getSelectedItem();
    if (!authUser()) {
      showError("Logon Failed : " + errmsg);
      return;
    }
    //save lastUser/lastDomain
    try {
      Properties props = new Properties();
      props.setProperty("lastUser", (String)username.getSelectedItem());
      props.setProperty("lastDomain", domain);
      props.store(new FileOutputStream("/etc/.lastLogon"), "");
    } catch (Exception e) {
      JFLog.log(e);
    }
    runSession();
  }

  private boolean authUser() {
    errmsg = "Auth failed";
    env_names = null;
    env_values = null;
    domainLogon = false;
    if (domain.equals("localhost")) {
      return Linux.authUser(user, pass);
    }
    domainLogon = true;
    String domainUser = domain + "+" + user;
    ShellProcess sp = new ShellProcess();
    sp.addResponse("password:", pass + "\n", false);
    sp.run(new String[] {"ntlm_auth", "--domain=" + domain, "--username=" + user}, true);
    if (sp.getErrorLevel() != 0) return false;
    //create user and set password
    if (!new File("/home/" + domainUser + "/.jfdesktop.xml").exists()) {
      sp = new ShellProcess();
      sp.run(new String[] {"useradd", "-m", "-U", domainUser}, false);
      if (sp.getErrorLevel() != 0) {
        errmsg = "failed to create local user";
        return false;
      }
      sp = new ShellProcess();
      sp.addResponse("Enter new UNIX password:", pass + "\n", true);
      sp.addResponse("Retype new UNIX password:", pass + "\n", true);
      sp.run(new String[] {"passwd", user}, true);  //NOTE : prompts are sent to stderr
      if (sp.getErrorLevel() != 0) {
        errmsg = "failed to set local password";
        return false;
      }
    }
    //create extra env variables
    env_names = new String[]  {"USERDOMAIN", "DOMAINNAME", "PASSWORD", "SERVER"};
    env_values = new String[] {domain      , user        , pass      , server.get(computer.getSelectedIndex())};
    //change local username
    user = domainUser;
    return true;
  }

  private void runSession() {
    dispose();
    new Thread() {
      public void run() {
        try {
          Startup.runSession(user, "/usr/bin/jfdesktop", env_names, env_values, domainLogon);
        } catch (Exception e) {
          JFAWT.showError("Session Failed", e.toString());
        }
        Linux.x11_rr_reset("800x600");
        java.awt.EventQueue.invokeLater(new Runnable() {public void run() {
          new Logon().setVisible(true);
        }});
      }
    }.start();
  }

  private void showError(String msg) {
    setVisible(false);
    setEnabled(false);
    JFAWT.showError("Error", msg);
    setVisible(true);
    setEnabled(true);
  }

  public static JFImage loadIcon(String iconName) {
    JFImage icon = new JFImage();
    icon.loadPNG(icon.getClass().getClassLoader().getResourceAsStream(iconName + ".png"));
    return icon;
  }

  public static JFImage scaleIcon(JFImage image,int x,int y) {
    if ((image.getWidth() == x) && (image.getHeight() == y)) return image;
    JFImage scale = new JFImage(x, y);
    scale.fill(0, 0, x, y, 0, true);
    scale.getGraphics().drawImage(image.getImage(), 0,0, x-1,y-1, 0,0, image.getWidth()-1,image.getHeight()-1, null);
    return scale;
  }

  public void actionPerformed(ActionEvent ae) {
    String action = ae.getActionCommand();
    if (action.startsWith("#vpn#")) {
      connectVPN(action.substring(5));
    } else if (action.startsWith("#wap#")) {
      int idx = Integer.valueOf(action.substring(5));
      WAP wap = wapItems.get(idx);
      connectWAP(wap.dev, wap.ssid, wap.encType);
    }
  }

  private String wapList;
  private String vpnList;
  private String vpnList2[];

  private boolean checkWireless() {
    ShellProcess sp = new ShellProcess();
    String output = sp.run(new String[] {"iwconfig"}, false);
    if (output.indexOf("ESSID") == -1) return false;
    return true;
  }

  private static String quote(String str) {
    return "\"" + str + "\"";
  }

  private void getWAPList() {
    jbusClient.call("org.jflinux.jfnetworkmgr", "getWAPList", quote(jbusClient.pack));
  }

  private void getVPNList() {
    jbusClient.call("org.jflinux.jfnetworkmgr", "getVPNList", quote(jbusClient.pack));
  }

  private static class WAP {
    public String dev, ssid, encType;
  }
  ArrayList<WAP> wapItems = new ArrayList<WAP>();

  private void showNetworkPopup() {
    NetworkPopup.removeAll();
    if (checkWireless()) {
      JMenu subWireless = new JMenu("Wireless");
      NetworkPopup.add(subWireless);
      //list wireless access points
      String lns[] = wapList.split("[|]");
      boolean wapActive = false;
      int idx = 0;
      String dev = null;
      int cnt = 0;
      int w = 0;
      wapItems.clear();
      while (idx < lns.length) {
        if (cnt == 0) {
          dev = lns[idx++];
          cnt = JF.atoi(lns[idx++]);
        }
        if (cnt == 0) continue;
        String ssid = lns[idx++];
        String encType = lns[idx++];
        WAP wap = new WAP();
        wap.dev = dev;
        wap.ssid = ssid;
        wap.encType = encType;
        wapItems.add(wap);
        if (ssid.endsWith(" *")) {
          wapActive = true;
        }
        JMenuItem w1 = new JMenuItem(ssid);
        w1.setActionCommand("#wap#" + (w++));
        w1.addActionListener(this);
        subWireless.add(w1);
        cnt--;
      }

      if (wapActive) {
        JMenuItem w1 = new JMenuItem("Disconnect...");
        w1.setActionCommand("#wap-disconnect");
        w1.addActionListener(this);
        subWireless.add(w1);
      }
    }

    //list vpn
    JMenu subVPN = new JMenu("VPN");
    NetworkPopup.add(subVPN);
    //list VPN
    vpnList2 = vpnList.split("[|]");
    for(int a=0;a<vpnList2.length;a++) {
      JMenuItem v1 = new JMenuItem(vpnList2[a]);
      v1.setActionCommand("#vpn#" + a);
      v1.addActionListener(this);
      subVPN.add(v1);
    }

    NetworkPopup.show(network, 0, network.getHeight());
  }

  private java.util.Timer networkTimer;
  private int networkIconIdx;
  private JFImage networkIcons[];
  private Icon networkOrgIcon;
  private final Object networkLock = new Object();
  private int networkTimerCount;
  private String cancelNetworkMethod;

  private void loadNetworkIcons() {
    networkIcons = new JFImage[3];
    for(int a=0;a<3;a++) {
      JFImage img = loadIcon("jflogon-network-" + (a+1));
      networkIcons[a] = scaleIcon(img, 18, 18);
    }
  }

  private void startNetworkTimer(String method) {
    if (networkTimer != null) return;
    cancelNetworkMethod = method;
    networkIconIdx = 0;
    networkTimerCount = 0;
    networkTimer = new java.util.Timer();
    networkTimer.schedule(new TimerTask() {
      public void run() {
        synchronized(networkLock) {
          if (networkTimer == null) return;
          networkTimerCount++;
          if (networkTimerCount == 60) {
            //after 30 seconds
            networkTimer.cancel();
            networkTimer = null;
            resetNetworkIcon();
            cancelNetworkConnection();
            return;
          }
          try {
            java.awt.EventQueue.invokeAndWait(new Runnable() {
              public void run() {
                if (networkOrgIcon == null) networkOrgIcon = network.getIcon();
                networkIconIdx++;
                if (networkIconIdx == networkIcons.length) networkIconIdx = 0;
                network.setIcon(networkIcons[networkIconIdx]);
              }
            });
          } catch (Exception e) {
            JFLog.log(e);
          }
        }
      }
    }, 0, 500);
  }

  private void stopNetworkTimer() {
    synchronized(networkLock) {
      networkTimer.cancel();
      networkTimer = null;
    }
    resetNetworkIcon();
  }

  private void resetNetworkIcon() {
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        network.setIcon(networkOrgIcon);
        networkOrgIcon = null;
      }
    });
  }

  private void cancelNetworkConnection() {
    if (cancelNetworkMethod == null) return;
    jbusClient.call("org.jflinux.jfnetworkmgr", cancelNetworkMethod, "");
    cancelNetworkMethod = null;
    showNetworkFailed();
  }

  private void showNetworkFailed() {
    JFAWT.showError("Error", "Connection failed!");
  }

  private void disconnectVPN(String name) {
    jbusClient.call("org.jflinux.jfnetworkmgr", "disconnectVPN", quote(name));
  }

  private void connectVPN(String name) {
    if (name.endsWith(" *")) {
      disconnectVPN(name.substring(0, name.length() - 2));
    } else {
      startNetworkTimer("cancelVPN");
      jbusClient.call("org.jflinux.jfnetworkmgr", "connectVPN", quote(jbusClient.pack) + "," + quote(name));
    }
  }

  private void disconnectWAP(String ssid) {
    jbusClient.call("org.jflinux.jfnetworkmgr", "disconnectWAP" , quote(ssid));
  }

  private void connectWAP(String dev, String ssid, String encType) {
    if (ssid.endsWith(" *")) {
      disconnectWAP(ssid.substring(0, ssid.length() - 2));
    } else {
      String key = "";
      if (encType.equals("WEP")) {
        key = JFAWT.getString("Enter WEP encryption key (26 or 10 hex digits)", "");
      } else if (encType.equals("WPA")) {
        key = JFAWT.getString("Enter WPA pass phrase", "");
      }
      startNetworkTimer("cancelWAP");
      jbusClient.call("org.jflinux.jfnetworkmgr", "connectWAP",
        quote(jbusClient.pack) + "," + quote(dev) + "," + quote(ssid) + "," + quote(encType) + "," + quote(key));
    }
  }

  public class JBusMethods {
    public void setWAPList(String list) {
      wapList = list;
    }
    public void setVPNList(String list) {
      vpnList = list;
      showNetworkPopup();
    }
    public void vpnFailed(String id) {
      stopNetworkTimer();
      showNetworkFailed();
    }
    public void vpnSuccess(String id) {
      stopNetworkTimer();
    }
    public void wapFailed() {
      stopNetworkTimer();
      showNetworkFailed();
    }
    public void wapSuccess() {
      stopNetworkTimer();
    }
  }
}
