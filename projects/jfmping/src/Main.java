/** jfmping
 *
 * Multi Ping
 *
 * @author pquiring
 */

import java.io.*;
import java.util.*;
import java.awt.*;

import javaforce.*;
import javaforce.jni.*;
import javaforce.awt.*;
import javaforce.net.*;

public class Main extends javax.swing.JFrame {

  /**
   * Creates new form Main
   */
  public Main() {
    initComponents();
    resized();
    JFNative.load();
    if (!PacketCapture.init()) {
      JFAWT.showError("error", "unable to init packet capture");
      System.exit(1);
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

    jToolBar1 = new javax.swing.JToolBar();
    start = new javax.swing.JButton();
    image = new javax.swing.JLabel() {
      public void paint(Graphics g) {
        drawImage(g);
      }
    }
    ;

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("jf Multi Ping");

    jToolBar1.setRollover(true);

    start.setText("Start");
    start.setFocusable(false);
    start.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    start.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    start.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        startActionPerformed(evt);
      }
    });
    jToolBar1.add(start);

    image.addComponentListener(new java.awt.event.ComponentAdapter() {
      public void componentResized(java.awt.event.ComponentEvent evt) {
        imageComponentResized(evt);
      }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 889, Short.MAX_VALUE)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(image, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(image, javax.swing.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
        .addContainerGap())
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void startActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startActionPerformed
    if (worker == null) start(); else stop();
  }//GEN-LAST:event_startActionPerformed

  private void imageComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_imageComponentResized
    resized();
  }//GEN-LAST:event_imageComponentResized

  /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {
    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new Main().setVisible(true);
      }
    });
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JLabel image;
  private javax.swing.JToolBar jToolBar1;
  private javax.swing.JButton start;
  // End of variables declaration//GEN-END:variables

  static JFImage img;
  Worker worker;

  public void start() {
    worker = new Worker();
    worker.start();
    start.setText("Stop");
  }

  public void stop() {
    if (worker != null) {
      worker.cancel();
      worker = null;
    }
    start.setText("Start");
  }

  public void resized() {
    int w = image.getWidth();
    int h = image.getHeight();
    System.out.println("size=" + w + "x" + h);
    if (w == 0 || h == 0) return;
    img = new JFImage(w,h);
    img.fill(0,0,w,h,0xffffff);  //fill white
  }

  public void drawImage(Graphics g) {
    if (img == null) return;
    g.drawImage(img.getImage(), 0, 0, null);
  }

  public void gui(Runnable task) {
    try {
      java.awt.EventQueue.invokeAndWait(task);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public class Worker extends Thread {
    private boolean active;
    private String iface_ip;
    private String iface_nic;
    private ArrayList<IP4> ips;
    private int pos;
    private int delay = 1000;
    private int timeout = 2000;
    private PacketCapture pcap;
    private long id;
    public void run() {
      active = true;
      //read config
      try {
        ips = new ArrayList<IP4>();
        JFLog.log("Reading ip-config.txt");
        {
          FileInputStream fis = new FileInputStream("ip-config.txt");
          byte[] data = fis.readAllBytes();
          fis.close();
          String[] lns = new String(data).replaceAll("\r", "").split("\n");
          for (int a=0;a<lns.length;a++) {
            String ln = lns[a].trim();
            if (ln.length() == 0 || ln.startsWith("#")) continue;
            int idx = ln.indexOf('=');
            if (idx == -1) continue;
            String key = ln.substring(0, idx);
            String value = ln.substring(idx + 1);
            switch (key) {
              case "iface_ip":
                iface_ip = value;
                System.out.println("iface_ip=" + iface_ip);
                break;
              case "delay":
                delay = Integer.valueOf(value);
                System.out.println("delay=" + delay);
                break;
              case "timeout":
                timeout = Integer.valueOf(value);
                System.out.println("timeout=" + timeout);
                break;
            }
          }
        }
        JFLog.log("Reading ip-list.txt");
        {
          FileInputStream fis = new FileInputStream("ip-list.txt");
          byte[] data = fis.readAllBytes();
          fis.close();
          String[] lns = new String(data).replaceAll("\r", "").split("\n");
          for (int a=0;a<lns.length;a++) {
            String ln = lns[a].trim();
            if (ln.length() == 0 || ln.startsWith("#")) continue;
            IP4 ip = new IP4();
            ip.setIP(ln);
            ips.add(ip);
          }
        }
        System.out.println("ips.size()=" + ips.size());
        if (ips.size() == 0) {
          System.out.println("no IPs to monitor");
          return;
        }
        pcap = new PacketCapture();
//        PacketCapture.debug = true;
        iface_nic = pcap.findInterface(iface_ip.toString());
        if (iface_nic == null) {
          System.out.println("unable to find proper interface");
          return;
        }
        id = pcap.start(iface_nic, iface_ip.toString());
        if (id <= 0) {
          System.out.println("unable to start pcap");
          return;
        }
        PacketCapture.compile(id, "arp");
        pos = 0;
        while (active) {
          img.line(pos, 0, pos, ips.size()-1, 0x000000);  //black line
          updateImage();
          for(int a=0;a<ips.size();a++) {
            IP4 ip = ips.get(a);
            byte[] mac = pcap.arp(id, ip.toString(), timeout);
            if (mac == null) {
              img.putPixel(pos, a, 0xff0000);  //red
            } else {
              img.putPixel(pos, a, 0x00ff00);  //green
              JF.sleep(10);
            }
          }
          pos++;
          if (pos >= img.getWidth()) {
            pos = 0;
          }
          updateImage();
          JF.sleep(delay);
        }
        PacketCapture.stop(id);
      } catch (Exception e) {
        JFLog.log(e);
      }
    }
    public void cancel() {
      active = false;
    }
    private void updateImage() {
      gui(new Runnable() {
        public void run() {
          image.repaint();
        }
      });
    }
  }
}
