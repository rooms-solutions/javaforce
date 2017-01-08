package javaforce.webui;

/** TreeNode
 *
 * @author pquiring
 */

import java.util.*;

public class TreeNode {
  private Object data;
  private TreeNode parent;
  private ArrayList<TreeNode> children = new ArrayList<TreeNode>();

  public boolean leaf;
  public boolean opened;

  public TreeNode() {
    leaf = false;
    opened = true;
  }

  public void setData(Object data) {
    this.data = data;
  }

  public Object getData() {
    return data;
  }

  public Object getUserObject() {
    return null;
  }

  public String toString() {
    if (data == null) return "null";
    return data.toString();
  }

  public void addNode(TreeNode node) {
    if (node == this) return;
    children.add(node);
  }

  public void removeNode(int idx) {
    children.remove(idx);
  }

  public void removeNode(TreeNode node) {
    children.remove(node);
  }

  public boolean hasChildren() {
    return children.size() > 0;
  }

  public int getChildCount() {
    return children.size();
  }

  public TreeNode getChildAt(int idx) {
    return children.get(idx);
  }

  public TreeNode[] getChildren() {
    return children.toArray(new TreeNode[getChildCount()]);
  }

  public void setParent(TreeNode newParent) {
    if (newParent == parent) return;
    if (parent != null) {
      parent.removeNode(this);
    }
    parent = newParent;
    parent.addNode(this);
  }

  public TreeNode getParent() {
    return parent;
  }
}
