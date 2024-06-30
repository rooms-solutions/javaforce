package javaforce.service.servlet;

/** ServletsService
 *
 */

import java.util.*;
import java.io.*;
import java.nio.file.*;

import javaforce.*;
import javaforce.service.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class ServletsService implements WebHandler {
  private static ServletsService server;
  private static boolean debug = true;

  private WebServer http;
  private WebServer https;

  private Object lock = new Object();
  private ArrayList<WAR> wars = new ArrayList<>();
  private ArrayList<WAR> delete_wars = new ArrayList<>();

  private WatchService watch;
  private Watcher watcher;

  private boolean active;

  public static void serviceStart(String[] args) {
    server = new ServletsService();
    server.start();
  }

  public static void serviceStop() {
    if (server != null) {
      server.stop();
    }
  }

  public void start() {
    active = true;
    Paths.init();
    JFLog.append(Paths.logsPath + "/system.log", true);
    JFLog.setRetention(30);
    JFLog.log("Starting jfServlets/" + Config.version + "...");
    Config.load();
    Settings.load();
    JFLog.log("Starting http...");
    http = new WebServer();
    http.start(this, Settings.current.http_port);
    JFLog.log("Starting https...");
    https = new WebServer();
    https.start(this, Settings.current.https_port, KeyMgmt.getDefaultClient());
    watchDeploy();
    deployWARs();
    registerWARs();
  }

  public void stop() {
    active = false;
    if (http != null) {
      http.stop();
      http = null;
    }
    if (https != null) {
      https.stop();
      https = null;
    }
    if (watch != null) {
      try { watch.close(); } catch (Exception e) {}
      watch = null;
    }
  }

  public void doGet(WebRequest req, WebResponse res) {
    //URL = /war/servlet?args
    // or
    //URL = /war/static_file.ext
    JFLog.log("doGet:" + req.getURL());
    String url = req.getURL();
    if (url.indexOf("..") != -1) {
      do400(req, res);
      return;
    }
    String war_name = null;
    String servlet_name = null;
    String static_name = null;
    String[] p = url.split("[/]", -1);
    if (p.length < 2) {
      do404(req, res);
      return;
    }
    if (p.length == 2) {
      war_name = "root";
      servlet_name = p[1];
      static_name = url;
    } else {
      war_name = p[1];
      servlet_name = p[2];
      static_name = url.substring(1 + war_name.length() + 1);  // /war/
    }
    WAR war = getWAR(war_name, true);
    if (war == null) {
      JFLog.log("war not found:" + war_name);
      do404(req, res);
      return;
    }
    //check if welcome page is requested
    if (servlet_name.length() == 0) {
      servlet_name = war.welcome;
      static_name = war.welcome;
    }
    //check for static resource
    byte[] data = war.getStaticResource(static_name);
    if (data != null) {
      try {
        res.getOutputStream().write(data);
      } catch (Exception e) {
        JFLog.log(e);
      }
      return;
    }
    //find servlet within war file using url pattern matching
    HttpServlet servlet = war.getServletByURL("/" + servlet_name);
    if (servlet == null) {
      JFLog.log("servlet not found:" + servlet_name);
      do404(req, res);
      return;
    }
    //build servlet request/response
    HttpServletRequest http_req = new HttpServletRequestImpl(req);
    HttpServletResponse http_res = new HttpServletResponseImpl(res);
    String method = req.getMethod();
    switch (method) {
      case "GET": servlet.doGet(http_req, http_res); break;
      case "POST": servlet.doPost(http_req, http_res); break;
      default: do501(req, res); break;
    }
  }

  public void doPost(WebRequest req, WebResponse res) {
    doGet(req, res);
  }

  public void do400(WebRequest req, WebResponse res) {
    doError(req, res, 400, "400 Bad Request");
  }

  public void do404(WebRequest req, WebResponse res) {
    doError(req, res, 404, "404 Object not found");
  }

  public void do501(WebRequest req, WebResponse res) {
    doError(req, res, 501, "501 Internal Error");
  }

  public void doError(WebRequest req, WebResponse res, int code, String msg) {
    try {
      res.setStatus(code, msg);
      res.setContentType("text/html");
      res.setContentLength(msg.length());
      res.getOutputStream().write(msg.getBytes());
    } catch (Exception e) {
      JFLog.log(e);
    }
  }

  private WAR getWAR(String name, boolean need_register) {
    synchronized (lock) {
      for(WAR war : wars) {
        if (war.name.equals(name)) {
          if (need_register && !war.registered) {
            return null;
          }
          return war;
        }
      }
    }
    return null;
  }

  /** Extract a WAR from /deploy to /working and unregistered old version if upgrading. */
  private void deployWAR(File file) {
    String name_ext = file.getName();
    String name = name_ext.substring(0, name_ext.length() - 4);  //remove .war
    String folder = Paths.workingPath + "/" + name + "-" + System.currentTimeMillis();
    JFLog.log("deployWAR:" + file + ",name=" + name + ",folder=" + folder);
    WAR war;
    synchronized (lock) {
      war = getWAR(name, false);
      if (war != null) {
        unregisterWAR(war);
      }
      new File(folder).mkdir();
      JF.unzip(file.getAbsolutePath(), folder);
      file.delete();
      war = WAR.load(folder);
      registerWAR(war);
    }
  }

  /** Deploy all files in /deploy */
  private boolean deployWARs() {
    JFLog.log("deployWARs");
    File[] files = new File(Paths.deployPath).listFiles();
    if (files == null || files.length == 0) return true;
    boolean result = true;
    for(File file : files) {
      String name = file.getName();
      if (name.endsWith(".war")) {
        JFLog.log("deployWARs:found war:" + name);
        if (zipComplete(file)) {
          deployWAR(file);
        } else {
          JFLog.log("deployWARs:incomplete war:" + name);
          result = false;
        }
      }
    }
    return result;
  }

  private static final byte[] zip_header = new byte[] {'P', 'K', 3, 4};
  private static final byte[] zip_end = new byte[] {'P', 'K', 5, 6};

  private boolean zipComplete(File file) {
    //read first 4 bytes - should be PK\3\4 for zip file
    //read last 20 bytes - if that starts with PK\5\6 the file is complete
    RandomAccessFile raf = null;
    try {
      raf = new RandomAccessFile(file, "r");
      byte[] header = new byte[4];
      if (raf.read(header) != 4) {
        throw new Exception("bad read");
      }
      for(int a=0;a<4;a++) {
        if (header[a] != zip_header[a]) {
          throw new Exception("not zip");
        }
      }
      raf.seek(raf.length() - 22);
      byte[] end = new byte[22];
      if (raf.read(end) != 22) {
        throw new Exception("bad read");
      }
      for(int a=0;a<4;a++) {
        if (end[a] != zip_end[a]) {
          throw new Exception("not complete");
        }
      }
      raf.close();
      return true;
    } catch (Exception e) {
      if (debug) JFLog.log(e);
    }
    if (raf != null) {
      try {raf.close();} catch (Exception e) {}
    }
    return false;
  }

  private void unregisterWAR(WAR war) {
    JFLog.log("unregisterWAR:" + war.name);
    wars.remove(war);
    delete_wars.add(war);
  }

  /** Register a WAR in /working */
  private void registerWAR(WAR war) {
    JFLog.log("registerWAR:" + war.name);
    war.registered = true;
    wars.add(war);
  }

  /** Register all WAR in /working */
  private void registerWARs() {
    JFLog.log("registerWARs");
    File[] folders = new File(Paths.workingPath).listFiles();
    for(File folder : folders) {
      JFLog.log("folder=" + folder);
      if (!folder.isDirectory()) continue;
      WAR war = WAR.load(folder.getAbsolutePath());
      if (war != null) {
        registerWAR(war);
      } else {
        JFLog.log("registerWARs:unable to load:" + folder);
      }
    }
  }

  /** Watch for new files in /deploy */
  private void watchDeploy() {
    try {
      watch = FileSystems.getDefault().newWatchService();
      File file = new File(Paths.deployPath);
      Path path = file.toPath();
      path.register(watch, new WatchEvent.Kind[] {StandardWatchEventKinds.ENTRY_CREATE});
      watcher = new Watcher();
      watcher.start();
    } catch (Exception e) {
      JFLog.log(e);
    }
  }

  public class Watcher extends Thread {
    public void run() {
      while (active) {
        WatchKey key = watch.poll();
        if (key == null) {
          JF.sleep(1000);
          continue;
        }
        //wait 5 seconds
        boolean result = false;
        do {
          for(int a=0;active && a<5;a++) {
            JF.sleep(1000);
          }
          result = deployWARs();
        } while (!result);
      }
    }
  }
}
