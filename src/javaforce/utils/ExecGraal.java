package javaforce.utils;

/** Executes Graal 'native-image' command.
 *
 * @author pquiring
 */

import java.util.*;

import javaforce.*;

public class ExecGraal implements ShellProcessListener {
  public static void main(String[] args) {
    if (args.length != 2) {
      System.out.println("Usage:ExecGraal CLASSPATH MAINCLASS");
      System.exit(1);
    }
    new ExecGraal().run(args);
  }

  public void run(String[] args) {
    ShellProcess sp = new ShellProcess();
    sp.addListener(this);
    ArrayList<String> cmd = new ArrayList<String>();
    if (JF.isWindows()) {
      cmd.add("native-image.cmd");
    } else {
      cmd.add("native-image");
    }
    cmd.add("-cp");
    if (JF.isWindows()) {
      cmd.add(args[0]);  //CLASSPATH
    } else {
      cmd.add(args[0].replaceAll("[;]", ":"));  //CLASSPATH
    }
    cmd.add(args[1].replaceAll("/", "."));  //MAINCLASS
    cmd.add("-H:JNIConfigurationFiles=graal_jni.json");
    cmd.add("-H:ResourceConfigurationFiles=graal_res.json");
    cmd.add("-H:Log=registerResource");
    cmd.add("--shared");
    cmd.add("--enable-all-security-services");
    cmd.add("--allow-incomplete-classpath");
    sp.run(cmd.toArray(new String[0]), true);
  }

  public void shellProcessOutput(String str) {
    System.out.print(str);
  }
}
