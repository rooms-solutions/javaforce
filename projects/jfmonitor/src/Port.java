/** Network Port
 *
 * @author peterq.admin
 */

import java.io.Serializable;

import java.util.*;

import javaforce.*;
import javaforce.webui.*;

public class Port implements Serializable {
  public static final long serialVersionUID = 1;

  public transient boolean valid;

  public String id;
  public String name;

  //interface
  public String ip, mask;

  //switchport
  public String mode;  //trunk or access
  public ArrayList<String> vlans = new ArrayList<>();  //allowed vlans
  public String vlan;  //native vlan
  public String group;

  public transient boolean link;
  public transient Component cell;  //TODO : needs to be per webui session

  public String getVLANs() {
    return VLAN.joinVLANs(vlans.toArray(JF.StringArrayType));
  }

  public void setVLANs(String[] _vlans) {
    vlans.clear();
    for(String vlan : _vlans) {
      vlans.add(vlan);
    }
  }

  public String getVLAN() {
    if (vlan == null) vlan = "1";
    return vlan;
  }

  public void setVLAN(String _vlan) {
    vlan = _vlan;
  }

  public String getPortNumber() {
    int idx = JF.indexOfDigit(id);
    if (idx == -1) {
      return id;
    } else {
      return id.substring(idx);
    }
  }

  public String toString() {
    return getPortNumber();
  }
}
