package javaforce.vm;

/** Represents memory or storage sizes.
 *
 * @author pquiring
 */

import java.io.*;

public class Size implements Serializable {
  private static final long serialVersionUID = 1L;

  //https://en.wikipedia.org/wiki/Metric_prefix
  public static final int B = 0;
  public static final int KB = 1;
  public static final int MB = 2;
  public static final int GB = 3;
  public static final int TB = 4;
  public static final int PB = 5;

  public int size;
  public int unit;

  public Size(int size, int unit) {
    this.size = size;
    this.unit = unit;
  }

  public char getUnitChar() {
    switch (unit) {
      case KB: return 'K';
      case MB: return 'M';
      case GB: return 'G';
      case TB: return 'T';
      case PB: return 'P';
    }
    return 'B';
  }

  /** Return size as : 1G */
  public String getSize() {
    return String.format("%d%c", size, getUnitChar());
  }

    /** Return size as : 1GiB */
  public String getSize_iB() {
    return String.format("%d%ciB", size, getUnitChar());
  }

  private static final long _KB = 1024L;
  private static final long _MB = 1024L * 1024L;
  private static final long _GB = 1024L * 1024L * 1024L;
  private static final long _TB = 1024L * 1024L * 1024L * 1024L;
  private static final long _PB = 1024L * 1024L * 1024L * 1024L * 1024L;

  public long toLong() {
    switch (unit) {
      case KB: return _KB * size;
      case MB: return _MB * size;
      case GB: return _GB * size;
      case TB: return _TB * size;
      case PB: return _PB * size;
    }
    return size;
  }

  public String toString() {
    return getSize();
  }

  public String toMemoryXML() {
    return "<memory unit='" + getUnitChar() + "iB'>" + size + "</memory>";
  }
}
