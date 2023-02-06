/*
 * JEdit.java
 *
 * Created on July 30, 2007, 10:13 AM
 */

/**
 * Multi-tabbed text editor.
 *
 * @author  pquiring
 */

import javax.swing.*;
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.text.*;

import javaforce.*;
import javaforce.awt.*;

public class JEdit extends javax.swing.JFrame implements FindEvent, ReplaceEvent, DocumentListener, ActionListener {

  private String getVersion() { return "0.19"; }

  /** Creates new form jfedit */
  public JEdit() {
    initComponents();
    folder.setModel(new DefaultListModel());
    initApp();
    JFImage icon = new JFImage();
    icon.loadPNG(this.getClass().getClassLoader().getResourceAsStream("jfedit.png"));
    setIconImage(icon.getImage());
    cut.addActionListener(this);
    copy.addActionListener(this);
    paste.addActionListener(this);
    delete.addActionListener(this);
  }

  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    popup = new javax.swing.JPopupMenu();
    cut = new javax.swing.JMenuItem();
    copy = new javax.swing.JMenuItem();
    paste = new javax.swing.JMenuItem();
    delete = new javax.swing.JMenuItem();
    tabs = new javax.swing.JTabbedPane();
    split = new javax.swing.JSplitPane();
    folderPane = new javax.swing.JScrollPane();
    folder = new javax.swing.JList<>();

    FormListener formListener = new FormListener();

    cut.setText("Cut");
    popup.add(cut);

    copy.setText("Copy");
    popup.add(copy);

    paste.setText("Paste");
    popup.add(paste);

    delete.setText("Delete");
    popup.add(delete);

    folder.setModel(new javax.swing.AbstractListModel<String>() {
      String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
      public int getSize() { return strings.length; }
      public String getElementAt(int i) { return strings[i]; }
    });
    folder.addMouseListener(formListener);
    folder.addKeyListener(formListener);
    folder.addListSelectionListener(formListener);
    folderPane.setViewportView(folder);

    setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
    addComponentListener(formListener);
    addWindowStateListener(formListener);
    addWindowListener(formListener);
    getContentPane().setLayout(new java.awt.GridLayout(1, 0));

    pack();
  }

  // Code for dispatching events from components to event handlers.

  private class FormListener implements java.awt.event.ComponentListener, java.awt.event.KeyListener, java.awt.event.MouseListener, java.awt.event.WindowListener, java.awt.event.WindowStateListener, javax.swing.event.ListSelectionListener {
    FormListener() {}
    public void componentHidden(java.awt.event.ComponentEvent evt) {
    }

    public void componentMoved(java.awt.event.ComponentEvent evt) {
      if (evt.getSource() == JEdit.this) {
        JEdit.this.formComponentMoved(evt);
      }
    }

    public void componentResized(java.awt.event.ComponentEvent evt) {
      if (evt.getSource() == JEdit.this) {
        JEdit.this.formComponentResized(evt);
      }
    }

    public void componentShown(java.awt.event.ComponentEvent evt) {
    }

    public void keyPressed(java.awt.event.KeyEvent evt) {
    }

    public void keyReleased(java.awt.event.KeyEvent evt) {
    }

    public void keyTyped(java.awt.event.KeyEvent evt) {
      if (evt.getSource() == folder) {
        JEdit.this.folderKeyTyped(evt);
      }
    }

    public void mouseClicked(java.awt.event.MouseEvent evt) {
      if (evt.getSource() == folder) {
        JEdit.this.folderMouseClicked(evt);
      }
    }

    public void mouseEntered(java.awt.event.MouseEvent evt) {
    }

    public void mouseExited(java.awt.event.MouseEvent evt) {
    }

    public void mousePressed(java.awt.event.MouseEvent evt) {
    }

    public void mouseReleased(java.awt.event.MouseEvent evt) {
    }

    public void windowActivated(java.awt.event.WindowEvent evt) {
    }

    public void windowClosed(java.awt.event.WindowEvent evt) {
    }

    public void windowClosing(java.awt.event.WindowEvent evt) {
      if (evt.getSource() == JEdit.this) {
        JEdit.this.formWindowClosing(evt);
      }
    }

    public void windowDeactivated(java.awt.event.WindowEvent evt) {
    }

    public void windowDeiconified(java.awt.event.WindowEvent evt) {
    }

    public void windowIconified(java.awt.event.WindowEvent evt) {
    }

    public void windowOpened(java.awt.event.WindowEvent evt) {
    }

    public void windowStateChanged(java.awt.event.WindowEvent evt) {
      if (evt.getSource() == JEdit.this) {
        JEdit.this.formWindowStateChanged(evt);
      }
    }

    public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
      if (evt.getSource() == folder) {
        JEdit.this.folderValueChanged(evt);
      }
    }
  }// </editor-fold>//GEN-END:initComponents

  private void formComponentMoved(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentMoved
    if (Settings.settings.bWindowMax) return;
    Point loc = getLocation();
    Settings.settings.WindowXPos = loc.x;
    Settings.settings.WindowYPos = loc.y;
  }//GEN-LAST:event_formComponentMoved

  private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
    if (Settings.settings.bWindowMax) return;
    Dimension size = getSize();
    Settings.settings.WindowXSize = size.width;
    Settings.settings.WindowYSize = size.height;
  }//GEN-LAST:event_formComponentResized

  private void formWindowStateChanged(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowStateChanged
    Settings.settings.bWindowMax = evt.getNewState() == MAXIMIZED_BOTH;
  }//GEN-LAST:event_formWindowStateChanged

  private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
    exit();
  }//GEN-LAST:event_formWindowClosing

  private void folderValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_folderValueChanged
    if (false) {
      openFileProjectList();
    }
  }//GEN-LAST:event_folderValueChanged

  private void folderKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_folderKeyTyped
    int key = evt.getKeyChar();
    int mod = evt.getModifiers();
    if (mod != 0) return;
    switch (key) {
      case KeyEvent.VK_ENTER: openFileProjectList(); break;
    }
  }//GEN-LAST:event_folderKeyTyped

  private void folderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_folderMouseClicked
    openFileProjectList();
  }//GEN-LAST:event_folderMouseClicked

  /**
   * @param args the command line arguments
   */
  public static String args[];
  public static void main(String args[]) {
    if (!JF.isWindows()) {
      if (System.getenv("DISPLAY") == null) {
        TEdit.main(args);
        return;
      }
    }
    JFAWT.removeAltGraph();
    JEdit.args = args;
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new JEdit().setVisible(true);
      }
    });
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JMenuItem copy;
  private javax.swing.JMenuItem cut;
  private javax.swing.JMenuItem delete;
  private javax.swing.JList<String> folder;
  private javax.swing.JScrollPane folderPane;
  private javax.swing.JMenuItem paste;
  private javax.swing.JPopupMenu popup;
  private javax.swing.JSplitPane split;
  private javax.swing.JTabbedPane tabs;
  // End of variables declaration//GEN-END:variables

  private class page {
    JPanel panel;
    JScrollPane scroll;
    JFTextArea txt;
    boolean dirty;
    File filename;
    boolean bUnix;
    long ts;
  };
  private Vector<page> pages;
  private boolean bLoading = false;
  private IndentBreakAction indentBreakAction = new IndentBreakAction();
  private static java.awt.Font fnt;
  private boolean projectOpen;
  private boolean projectExists;
  private boolean projectActive;
  //new vars here

  private void initApp() {
    tabs.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyPressed(java.awt.event.KeyEvent evt) {
        keyPressedEvent(evt);
      }
    });
    folder.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyPressed(java.awt.event.KeyEvent evt) {
        keyPressedEvent(evt);
      }
    });
    setTitle("jfedit");
    pages = new Vector<page>();
    loadcfg(true);
    setSize(Settings.settings.WindowXSize, Settings.settings.WindowYSize);
    setLocation(Settings.settings.WindowXPos, Settings.settings.WindowYPos);
    if (Settings.settings.bWindowMax) setExtendedState(MAXIMIZED_BOTH);
    if (args != null) {
      for(int a=0;a<args.length;a++) loadpages(args[a]);
    }
    if (pages.size() == 0) addpage("untitled");
    tabs.setSelectedIndex(0);
    pages.get(0).txt.grabFocus();
    split.setLeftComponent(folderPane);
    if (projectExists()) {
      loadProject();
    }
    setPane();
  }
  public static void loadcfg(boolean gui) {
    XML xml = new XML();
    String filename = JF.getUserPath() + "/.jfedit.xml";
    File file = new File(filename);
    if (!file.exists()) {
      System.err.println("Config not found");
      return;
    }
    if (!xml.read(filename)) {
      System.err.println("Failed to read config");
      return;
    }
    if (!xml.root.name.equals("jfedit")) {
      System.err.println("Invalid configuration");
      return;
    }
    xml.writeClass(xml.root, Settings.settings);
    if (gui) fnt = JFAWT.getMonospacedFont(0, Settings.settings.fontSize);
  }
  public static void savecfg() {
    XML xml = new XML();
    XML.XMLTag tag;
    xml.root.name = "jfedit";
    xml.readClass(xml.root, Settings.settings);
    String filename = JF.getUserPath() + "/.jfedit.xml";
    xml.write(filename);
  }
//find data
  private String findstr = "";
  private String repstr = "";
  private boolean findww;
  private boolean findcw;
//interface FindEvent
  public void findEvent(FindDialog dialog) {
    findstr = dialog.getText();
    findww = dialog.getWhole();
    findcw = dialog.getCase();
    findagain(false);
  }
//interface ReplaceEvent
  public boolean findEvent(ReplaceDialog dialog) {
    findstr = dialog.getFindText();
    findww = dialog.getWhole();
    findcw = dialog.getCase();
    boolean ret = replace_find();
    if (!ret) notfound();
    return ret;
  }
  public void replaceEvent(ReplaceDialog dialog) {
    findstr = dialog.getFindText();
    repstr = dialog.getReplaceText();
    findww = dialog.getWhole();
    findcw = dialog.getCase();
    replace_replace();
  }
  public void replaceAllEvent(ReplaceDialog dialog) {
    findstr = dialog.getFindText();
    repstr = dialog.getReplaceText();
    findww = dialog.getWhole();
    findcw = dialog.getCase();
    replace_all();
  }
  private JFTextArea createJFTextArea() {
    JFTextArea txt = new JFTextArea();
    txt.setFont(fnt);
    txt.setTabSize(Settings.settings.tabSize);
    txt.setLineWrap(Settings.settings.bLineWrap);
    txt.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyPressed(java.awt.event.KeyEvent evt) {
        keyPressedEvent(evt);
      }
    });
    txt.getDocument().addDocumentListener(this);
    txt.getActionMap().put(DefaultEditorKit.insertBreakAction, indentBreakAction);
    txt.setComponentPopupMenu(popup);
    return txt;
  }
  private page addpage(String title) {
    page pg = new page();
    pg.bUnix = Settings.settings.bUnix;
    pg.panel = JFAWT.createJPanel(new GridLayout(), null);
    pg.txt = createJFTextArea();
    pg.scroll = new JScrollPane(pg.txt);
    pg.panel.add(pg.scroll);
    pg.filename = new File(title);
    pg.dirty = false;
    pg.panel.setVisible(true);
    tabs.addTab(title, pg.panel);
    this.doLayout();
    tabs.setSelectedComponent(pg.panel);
    pages.add(pg);
    return pg;
  }
  private int getidx() { return tabs.getSelectedIndex(); }
  private boolean savepage() {
    int idx = getidx();
    if (pages.get(idx).dirty == false) return true;
    if (pages.get(idx).filename.toString().equals("untitled")) {return savepageas();}
    String tmp;
    try {
      tmp = pages.get(idx).txt.getText();
      if (Settings.settings.bClean) {
        boolean cleaned = false;
        while (tmp.indexOf(" \n") != -1) {tmp = tmp.replaceAll(" \n" ,"\n");cleaned = true;}
        if (cleaned) {
          int pos = pages.get(idx).txt.getCaretPosition();
          int line = pages.get(idx).txt.getLineOfOffset(pos);
          setText(pages.get(idx).txt, tmp);
          pages.get(idx).txt.setCaretPosition(pages.get(idx).txt.getLineStartOffset(line));
        }
      }
      if (!pages.get(idx).bUnix) {
        tmp = tmp.replaceAll("\n", "\r\n");
      }
      FileOutputStream fos = new FileOutputStream(pages.get(idx).filename);
      fos.write(tmp.getBytes("UTF-8"));
      fos.close();
      pages.get(idx).ts = pages.get(idx).filename.lastModified();
      tabs.setTitleAt(idx, pages.get(idx).filename.getName());
      pages.get(idx).dirty = false;
      return true;
    } catch (Exception e) {
      JOptionPane.showMessageDialog(this, "Failed to save '" + pages.get(idx).filename.toString() + "'", "Warning", JOptionPane.ERROR_MESSAGE);
      return false;
    }
  }
  private boolean savepageas() {
    int idx = getidx();
    JFileChooser chooser = new JFileChooser();
    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    chooser.setMultiSelectionEnabled(false);
    chooser.setCurrentDirectory(new File(JF.getCurrentPath()));
    if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
      pages.get(idx).dirty = true;
      pages.get(idx).filename = chooser.getSelectedFile();
      tabs.setTitleAt(idx, pages.get(idx).filename.getName());
      return savepage();
    }
    return false;
  }
  private boolean closepage(int idx) {
    tabs.setSelectedIndex(idx);
    return closepage();
  }
  private boolean closepage() {
    int idx = getidx();
    if (pages.get(idx).dirty) {
      //confirm to Save : Yes/No/Cancel
      switch (JOptionPane.showConfirmDialog(this, "Do you wish to save '" + pages.get(idx).filename.toString() + "' ?", "Confirm",
        JOptionPane.YES_NO_CANCEL_OPTION)) {
        case JOptionPane.YES_OPTION:
          if (!savepage()) return false;
          break;
        case JOptionPane.NO_OPTION:
          break;
        default:
        case JOptionPane.CANCEL_OPTION:
          return false;
      }
    }
    pages.remove(idx);
    tabs.remove(idx);
    if (pages.size() == 0) addpage("untitled");
    checktab();
    return true;
  }
  private void openpage() {
    int idx = getidx();
    JFileChooser chooser = new JFileChooser();
    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    chooser.setMultiSelectionEnabled(false);
    chooser.setCurrentDirectory(new File(JF.getCurrentPath()));
    if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
      //check if current page is "untitled" and !dirty
      if (pages.get(idx).filename.toString().equals("untitled") && pages.get(idx).dirty == false) {
        //load on current page
        pages.get(idx).filename = chooser.getSelectedFile();
      } else {
        addpage(chooser.getSelectedFile().getAbsolutePath());
        idx = tabs.getTabCount() - 1;
      }
      loadpage(idx);
      pages.get(idx).txt.grabFocus();
    }
  }
  private void loadpages(String filespec) {
    File f = new File(filespec);
    if (f.isDirectory()) {
/*
      String files[] = f.list();
      if (files == null || files.length == 0) return;
      for(int a=0;a<files.length;a++) {
        addpage(files[a]);
        loadpage(tabs.getTabCount() - 1);
      }
*/
    } else {
      addpage(filespec);
      loadpage(tabs.getTabCount() - 1);
    }
  }
  private void loadpage(int idx) {
    byte txt[];
    if ((pages.get(idx).filename.toString().indexOf("*") != -1) || (pages.get(idx).filename.toString().indexOf("?") != -1)) {
      closepage(idx);
      return;
    }
    try {
      File file = pages.get(idx).filename;
      if (!file.exists()) return;
      FileInputStream fis = new FileInputStream(file);
      int size = fis.available();
      if (size > 1024 * 1024) {
        if (!JFAWT.showConfirm("Warning", "File is > 1MB, load anyways?")) {
          closepage(idx);
          return;
        }
      }
      txt = new byte[size];
      fis.read(txt);
      fis.close();
      pages.get(idx).ts = pages.get(idx).filename.lastModified();
      bLoading = true;
      String str = new String(txt, "UTF-8");
      boolean bUnix = Settings.settings.bUnix;
      if (Settings.settings.bPreserve) {
        int lf = str.indexOf("\n");
        if (lf > 0) {
          if (txt[lf-1] == '\r') bUnix = false; else bUnix = true;
        }
      }
      pages.get(idx).bUnix = bUnix;
      setText(pages.get(idx).txt, str);
      pages.get(idx).txt.setCaretPosition(0);
      bLoading = false;
      tabs.setTitleAt(idx, pages.get(idx).filename.getName());
    } catch (Exception e) {
      JOptionPane.showMessageDialog(this, "Failed to open '" + pages.get(idx).filename.toString() + "'", "Warning", JOptionPane.ERROR_MESSAGE);
      closepage(idx);
    }
  }
  private void notfound() {
    JOptionPane.showMessageDialog(this, "Unable to find match", "Notice", JOptionPane.ERROR_MESSAGE);
    repaint();
  }
  private void find() {
    FindDialog.showFindDialog(this, false, findstr, findww, findcw, this);
    repaint();
  }
  private boolean isChar(char ch) {
    //return true if ch is a char that would be a part of a whole word
    if ((ch >= 'a') && (ch <= 'z')) return true;
    if ((ch >= 'A') && (ch <= 'Z')) return true;
    if ((ch >= '0') && (ch <= '9')) return true;
    if (ch == '_') return true;
    return false;
  }
  private boolean findagain(boolean quiet) {
    int idx = getidx();
    if (findstr == null) return false;
    String txt = pages.get(idx).txt.getText();
    int txtlen = txt.length();
    int findstrlen = findstr.length();
    int pos = -1;
    for(int a=pages.get(idx).txt.getCaretPosition();a<txt.length();a++) {
      if (txt.regionMatches(!findcw, a, findstr, 0, findstrlen)) {
        if (findww) {
          if ((a > 0) && (isChar(txt.charAt(a-1)))) continue;
          if ((a < txtlen - findstrlen) && (isChar(txt.charAt(a+findstrlen)))) continue;
        }
        pos = a;
        break;
      }
    }
    if (pos == -1) {if (!quiet) notfound(); return false;}
    pages.get(idx).txt.setCaretPosition(pos);
    pages.get(idx).txt.select(pos, pos + findstr.length());
    return true;
  }
  private void replace() {
    int idx = getidx();
    ReplaceDialog.showReplaceDialog(this, true, findstr, repstr, findww, findcw, this);
    repaint();
  }
  private boolean replace_find() {
    int idx = getidx();
    return findagain(true);
  }
  private void replace_replace() {
    int idx = getidx();
    pages.get(idx).txt.replaceSelection(repstr);
  }
  private void replace_all() {
    int idx = getidx();
    int cnt = 0;
    JFTextArea txt = pages.get(idx).txt;
    txt.setCaretPosition(0);
    while (true) {
      if (!replace_find()) break;
      replace_replace();
      cnt++;
    }
    JOptionPane.showMessageDialog(this,
      "Replaced " + cnt + " occurances"
      , "Info", JOptionPane.INFORMATION_MESSAGE);
  }
  private void gotopos() {
    String str;
    int line;
    int idx = getidx();
    try {
      str = JOptionPane.showInputDialog(this, "Which line?",
        "Goto", JOptionPane.QUESTION_MESSAGE);
      if (str == null) return;
      line = JF.atoi(str) - 1;
      if (line < 0) return;
      pages.get(idx).txt.setCaretPosition(pages.get(idx).txt.getLineStartOffset(line));
    } catch (Exception e2) {}
  }
  private boolean isActive(Process proc) {
    try {
      int exitValue = proc.exitValue();
      return false;
    } catch (Exception e) {
      return true;
    }
  }
  private void execute() {
    try {
      String str = JOptionPane.showInputDialog(this, "Enter OS command",
        "Execute", JOptionPane.QUESTION_MESSAGE);
      if (str == null) return;
      String strs[] = str.split(" ");
      ArrayList<String> list = new ArrayList<String>();
      for(int a=0;a<strs.length;a++) list.add(strs[a]);
      ProcessBuilder pb = new ProcessBuilder(list);
      pb.redirectErrorStream(true);
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      Process proc = pb.start();
//      proc.waitFor();  //dead locks often
      InputStream is = proc.getInputStream();
      while (isActive(proc)) {
        int fs = is.available();
        if (fs > 0) {
          byte data[] = new byte[fs];
          int read = is.read(data);
          baos.write(data, 0, read);
        }
      }
      int fs = is.available();
      if (fs > 0) {
        byte data[] = new byte[fs];
        int read = is.read(data);
        baos.write(data, 0, read);
      }
      if (baos.size() == 0) {
        JOptionPane.showMessageDialog(this, "No output", "Execute",
          JOptionPane.INFORMATION_MESSAGE);
        return;
      }
      page pg = addpage("output-" + strs[0]);
      setText(pg.txt, baos.toString());
      pg.txt.grabFocus();
      pg.txt.setCaretPosition(0);
    } catch (Exception e) {
    }
  }
  private void print() {
    int idx = getidx();
    try {
      pages.get(idx).txt.print();  //Java 1.6
    } catch (Exception e) {
      JOptionPane.showMessageDialog(this, "Failed to print '" + pages.get(idx).filename.toString() + "'", "Warning", JOptionPane.ERROR_MESSAGE);
    }
  }
  private void exit() {
    //close all windows
    int cnt = pages.size();
    for(int a=0;a<cnt;a++) {
      if (!closepage()) return;
    }
    savecfg();
    System.exit(0);
  }
  public void changedUpdate(DocumentEvent e) {
    changed();
  }
  public void insertUpdate(DocumentEvent e) {
    changed();
  }
  public void removeUpdate(DocumentEvent e) {
    changed();
  }
  public void changed() {
    if (bLoading) return;
    int idx = getidx();
    if (idx < 0 || idx >= pages.size()) return;
    if (pages.get(idx).dirty == false) {
      pages.get(idx).dirty = true;
      tabs.setTitleAt(idx, pages.get(idx).filename.getName() + " *");
    }
  }
  public void setText(JFTextArea ta, String txt) {
    txt = txt.replaceAll("\r\n","\n");  //PC file
    txt = txt.replaceAll("\r","\n");  //MAC file
    ta.setText(txt);
    ta.clearHistory();
  }
  public static class IndentBreakAction extends TextAction {
    /**
     * Creates this object with the appropriate identifier.
     */
    public IndentBreakAction() {
      super(DefaultEditorKit.insertBreakAction);
    }

    /**
     * The operation to perform when this action is triggered.
     *
     * @param e the action event
     */
    public void actionPerformed(ActionEvent e) {
      JTextComponent target = getTextComponent(e);

      if (target == null) return;

      if ((! target.isEditable()) || (! target.isEnabled())) {
        UIManager.getLookAndFeel().provideErrorFeedback(target);
        return;
      }

      try {
        //  Determine which line we are on

        Document doc = target.getDocument();
        Element rootElement = doc.getDefaultRootElement();
        int selectionStart = target.getSelectionStart();
        int line = rootElement.getElementIndex( selectionStart );

        //  Get the text for this line

        int start = rootElement.getElement(line).getStartOffset();
        int end = rootElement.getElement(line).getEndOffset();
        int length = end - start;
        String text = doc.getText(start, length);
        int offset = 0;

        //  Get the number of white spaces characters at the start of the line

        for (offset = 0; offset < length; offset++) {
          char c = text.charAt(offset);

          if (c != ' ' && c != '\t')
            break;
        }

        //  When splitting the text include white space at start of line
        //  else do default processing

        if ((selectionStart - start >= offset) && (Settings.settings.bAutoIndent))
          target.replaceSelection("\n" + text.substring(0, offset) );
        else
          target.replaceSelection("\n");
      } catch(BadLocationException ble) {}
    }
  }
  private void shift_left(char ch) {
    if (pages.size() == 0) return;
    int idx = getidx();
    JFTextArea txt = pages.get(idx).txt;
    int ss = txt.getSelectionStart();
    int se = txt.getSelectionEnd();
    if (ss == se) return;
    String lns[] = txt.getSelectedText().split("\n", -1);
    StringBuffer newString = new StringBuffer();
    for(int a=0;a<lns.length;a++) {
      if (a > 0) newString.append("\n");
      if ((lns[a].length() > 0) && (lns[a].charAt(0) == ch)) { lns[a] = lns[a].substring(1); se--; }
      newString.append(lns[a]);
    }
    txt.replaceSelection(newString.toString());
    txt.setSelectionStart(ss);
    txt.setSelectionEnd(se);
  }
  private void shift_right(char ch) {
    if (pages.size() == 0) return;
    int idx = getidx();
    JFTextArea txt = pages.get(idx).txt;
    int ss = txt.getSelectionStart();
    int se = txt.getSelectionEnd();
    if (ss == se) return;
    String lns[] = txt.getSelectedText().split("\n", -1);
    StringBuffer newString = new StringBuffer();
    String chstr = "" + ch;
    for(int a=0;a<lns.length;a++) {
      if (a > 0) newString.append("\n");
      if (lns[a].length() > 0) {
        newString.append(chstr);
        se++;
      }
      newString.append(lns[a]);
    }
    txt.replaceSelection(newString.toString());
    txt.setSelectionStart(ss);
    txt.setSelectionEnd(se);
  }

  private void lowercase() {
    if (pages.size() == 0) return;
    int idx = getidx();
    JFTextArea txt = pages.get(idx).txt;
    int ss = txt.getSelectionStart();
    int se = txt.getSelectionEnd();
    if (ss == se) return;
    txt.replaceSelection(txt.getSelectedText().toLowerCase());
    txt.setSelectionStart(ss);
    txt.setSelectionEnd(se);
  }

  private void uppercase() {
    if (pages.size() == 0) return;
    int idx = getidx();
    JFTextArea txt = pages.get(idx).txt;
    int ss = txt.getSelectionStart();
    int se = txt.getSelectionEnd();
    if (ss == se) return;
    txt.replaceSelection(txt.getSelectedText().toUpperCase());
    txt.setSelectionStart(ss);
    txt.setSelectionEnd(se);
  }

  private void keyPressedEvent(java.awt.event.KeyEvent evt) {
    //Key Pressed
    int key = evt.getKeyCode();
    int mod = evt.getModifiers();
    Object source = evt.getSource();
    boolean isText = source instanceof JFTextArea;
    int idx;
    JFTextArea txt;
    switch (mod) {
      case 0:
        switch (key) {
          case KeyEvent.VK_F1: {
            JOptionPane.showMessageDialog(this,
              "jfedit/" + getVersion() + "\n\n" +
              "F1 = Help\n" +
              "F2 = Edit Settings\n" +
              "F4 = Document Info\n" +
              "F5 = Shift Selection left 1 space\n" +
              "F6 = Shift Selection right 1 space\n" +
              "F7 = Shift Selection left 1 tab\n" +
              "F8 = Shift Selection right 1 tab\n" +
              "F9 = Lower case Selection\n" +
              "F10 = Upper case Selection\n" +
              "CTRL-O = Open\n" +
              "CTRL-W = Close\n" +
              "CTRL-S = Save\n" +
              "CTRL-Q = Save As\n" +
              "CTRL-F = Find\n" +
              "CTRL-G/F3 = Find Again\n" +
              "CTRL-R = Replace\n" +
              "CTRL-L = Goto Line #\n" +
              "CTRL-E = Execute Command\n" +
              "CTRL-P = Print\n" +
              "CTRL-D = Reload\n" +
              "ALT-# = Switch to Document\n" +
              "CTRL-1 = Switch to Project List\n" +
              "CTRL-2 = Switch to Document Tabs\n" +
              "ALT-F1 = Toggle Project List\n" +
              "ALT-F2 = Edit Project Settings\n"
              , "Help", JOptionPane.INFORMATION_MESSAGE);
            return;
          }
          case KeyEvent.VK_F2: {
            EditSettings.editSettings(this, Settings.settings);
            fnt = JFAWT.getMonospacedFont(0, Settings.settings.fontSize);
            int cnt = pages.size();
            for(int a=0;a<cnt;a++) {
              txt = pages.get(a).txt;
              txt.setFont(fnt);
              txt.setTabSize(Settings.settings.tabSize);
              txt.setLineWrap(Settings.settings.bLineWrap);
            }
            return;
          }
          case KeyEvent.VK_F3: { findagain(false); return; }
          case KeyEvent.VK_F4: {
            if (!isText) return;
            idx = getidx();
            page pg = pages.get(idx);
            txt = pg.txt;
            boolean bUnix = pg.bUnix;
            int cp, px, py;
            try {
              cp = txt.getCaretPosition();
              py = txt.getLineOfOffset(cp);
              px = cp - txt.getLineStartOffset(py);
            } catch (Exception e) {
              py = -1;
              px = -1;
            }
            px++;
            py++;
            if (false) {
              StringBuilder info = new StringBuilder();
              info.append("File : " + pg.filename + "\n");
              info.append("Lines : " + txt.getLineCount() + "\n");
              info.append("Current Position : " + px + "," + py + "\n");
              info.append("Line Endings : " + (bUnix ? "LF" : "CRLF") + "\n");
              JOptionPane.showMessageDialog(this, info.toString(), "Info", JOptionPane.INFORMATION_MESSAGE);
            } else {
              EditDocInfo info = new EditDocInfo(null, true, pg.filename.getAbsolutePath(), txt.getLineCount(), px, py, bUnix);
              info.setVisible(true);
              if (info.accepted) {
                pg.bUnix = info.getUnix();
              }
            }
            return;
          }
          case KeyEvent.VK_F5: { shift_left(' '); return; }
          case KeyEvent.VK_F6: { shift_right(' '); return; }
          case KeyEvent.VK_F7: { shift_left('\t'); return; }
          case KeyEvent.VK_F8: { shift_right('\t'); return; }
          case KeyEvent.VK_F9: { lowercase(); return; }
          case KeyEvent.VK_F10: { uppercase(); evt.consume(); return; }
          case KeyEvent.VK_TAB: {
            if (!isText) return;
            idx = getidx();
            page pg = pages.get(idx);
            txt = pg.txt;
            int start = txt.getSelectionStart();
            int end = txt.getSelectionEnd();
            if (start != end) {
              for(int a=0;a<Settings.settings.tabSize;a++) {
                shift_right(' ');
              }
              evt.consume();
              return;
            }
            if (Settings.settings.bTabToSpaces) {
              String spaces = "";
              for(int a=0;a<Settings.settings.tabSize;a++) {
                spaces += " ";
              }
              pg.txt.insert(spaces, pg.txt.getCaretPosition());
              evt.consume();
              return;
            }
          }
        }
        break;
      case KeyEvent.SHIFT_MASK:
        if (!isText) return;
        switch (key) {
          case KeyEvent.VK_TAB: {
            idx = getidx();
            page pg = pages.get(idx);
            txt = pg.txt;
            int start = txt.getSelectionStart();
            int end = txt.getSelectionEnd();
            if (start != end) {
              for(int a=0;a<Settings.settings.tabSize;a++) {
                shift_left(' ');
              }
              evt.consume();
              return;
            }
          }
        }
        break;
      case KeyEvent.CTRL_MASK:
        if (key == KeyEvent.VK_2) {switchToTabs(); return;}
        if (!isText) return;
        switch (key) {
          case KeyEvent.VK_PAGE_UP: { prevtab(); evt.consume(); return; }
          case KeyEvent.VK_PAGE_DOWN: { nexttab(); evt.consume(); return; }
          case KeyEvent.VK_N: { addpage("untitled"); return; }
          case KeyEvent.VK_S: { savepage(); return; }
          case KeyEvent.VK_Q: { savepageas(); return; }
          case KeyEvent.VK_W: { closepage(); return; }
          case KeyEvent.VK_O: { openpage(); return; }
          case KeyEvent.VK_F: { find(); return; }
          case KeyEvent.VK_G: { findagain(false); return; }
          case KeyEvent.VK_R: { replace(); return; }
          case KeyEvent.VK_L: { gotopos(); return; }
          case KeyEvent.VK_E: { execute(); return; }
          case KeyEvent.VK_P: { print(); return; }
          case KeyEvent.VK_D: { checktab(); return; }
          case KeyEvent.VK_1: {switchToProject(); return;}
        }
        break;
      case KeyEvent.ALT_MASK:
        switch (key) {
          case KeyEvent.VK_F1: {toggleProject(); return;}
          case KeyEvent.VK_F2: {editProject(); return;}
        }
        if (!isText) return;
        if ((key >= KeyEvent.VK_0) && (key <= KeyEvent.VK_9)) {
          idx = key - KeyEvent.VK_0;
          if (idx == 0) idx = 9; else idx--;
          if (idx >= pages.size()) return;
          tabs.setSelectedIndex(idx);
          checktab();
          return;
        }
        switch (key) {
          case KeyEvent.VK_MINUS: {tabs.setSelectedIndex(10); checktab(); return;}
          case KeyEvent.VK_EQUALS: {tabs.setSelectedIndex(11); checktab(); return;}
        }
        break;
    }
  }

  private void checktab() {
    int idx = getidx();
    if (pages.get(idx).filename.toString().equals("untitled")) return;
    if (!pages.get(idx).filename.exists()) return;
    long ts = pages.get(idx).filename.lastModified();
    if (ts > pages.get(idx).ts) {
      //file has been updated on disk - ask to reload
      switch (JOptionPane.showConfirmDialog(this, "File has changed : '" + pages.get(idx).filename.toString() + "'\nReload file?", "Confirm",
        JOptionPane.YES_NO_CANCEL_OPTION)) {
        case JOptionPane.YES_OPTION:
          loadpage(idx);
          break;
        case JOptionPane.NO_OPTION:
          break;
        default:
        case JOptionPane.CANCEL_OPTION:
          break;
      }
    }
  }

  private void prevtab() {
    int idx = tabs.getSelectedIndex();
    idx--;
    if (idx == -1) idx = pages.size() - 1;
    tabs.setSelectedIndex(idx);
    checktab();
  }

  private void nexttab() {
    int idx = tabs.getSelectedIndex();
    idx++;
    if (idx == pages.size()) idx = 0;
    tabs.setSelectedIndex(idx);
    checktab();
  }

  private enum Section {None, Folders, Files};
  private ArrayList<String> folders = new ArrayList<String>();
  private ArrayList<String> files = new ArrayList<String>();

  private void loadProject() {
    Project.project = new Project();
    Project.project.load();
    projectExists = true;
    projectOpen = true;
  }

  private void cut() {
    int idx = getidx();
    JFTextArea txt = pages.get(idx).txt;
    txt.cut();
  }

  private void copy() {
    int idx = getidx();
    JFTextArea txt = pages.get(idx).txt;
    txt.copy();
  }

  private void paste() {
    int idx = getidx();
    JFTextArea txt = pages.get(idx).txt;
    txt.paste();
  }

  private void delete() {
    int idx = getidx();
    JFTextArea txt = pages.get(idx).txt;
    txt.replaceSelection("");
  }

  public void actionPerformed(ActionEvent ae) {
    String a = ae.getActionCommand();
    switch (a) {
      case "Cut": cut(); break;
      case "Copy": copy(); break;
      case "Paste": paste(); break;
      case "Delete": delete(); break;
    }
  }

  private boolean projectExists() {
    return new File(Project.filename).exists();
  }

  private void setPane() {
    if (projectOpen) {
      split.setRightComponent(tabs);
      setContentPane(split);
      listProjectFiles();
    } else {
      split.setRightComponent(null);
      setContentPane(tabs);
    }
    validate();
    repaint();
    setFocus();
  }

  private void createProject() {
    Project.project = new Project();
    Project.project.save();
  }

  private void listProjectFiles() {
    DefaultListModel model = (DefaultListModel)folder.getModel();
    model.clear();
    File[] files = new File(".").listFiles();
    for(File file : files) {
      if (!file.isFile()) continue;
      String filename = file.getName();
      boolean accept = false;
      for(String wc : Project.project.fileMasks) {
        if (JF.wildcardCompare(filename, wc, true)) {
          accept = true;
          break;
        }
      }
      if (accept) {
        model.addElement(filename);
      }
    }
  }

  private void switchToProject() {
    projectActive = true;
    listProjectFiles();
    if (projectOpen) {
      setFocus();
      return;
    }
    if (projectExists) {
      projectOpen = true;
      setPane();
      return;
    }
    createProject();
    projectExists = true;
    projectOpen = true;
    setPane();
  }

  private void switchToTabs() {
    projectActive = false;
    setFocus();
  }

  private void setFocus() {
    if (projectOpen && projectActive) {
      folder.requestFocus();
    } else {
      int idx = getidx();
      pages.get(idx).txt.requestFocus();
    }
  }

  private void toggleProject() {
    projectOpen = !projectOpen;
    setPane();
  }

  private void editProject() {
    EditProject.editProject(this, Project.project);
    listProjectFiles();
  }

  private void openFileProjectList() {
    String filename = folder.getSelectedValue();
    File file = new File(filename);
    String absfile = file.getAbsolutePath();
    //search for file and switch to, else open file
    int idx = -1;
    int pos = 0;
    for(page pg : pages) {
      if (pg.filename.getAbsolutePath().equals(absfile)) {
        idx = pos;
        break;
      }
      pos++;
    }
    if (idx != -1) {
      tabs.setSelectedIndex(idx);
      checktab();
    } else {
      addpage(filename);
      loadpage(tabs.getTabCount() - 1);
    }
    switchToTabs();
  }
}
