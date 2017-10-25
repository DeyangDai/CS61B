/* DListNode1.java */

/**
 *  A DListNode1 is a node in a DList1 (doubly-linked list).
 */

public class DListNode {

  /**
   *  item references the item stored in the current node.
   *  prev references the previous node in the DList.
   *  next references the next node in the DList.
   *
   *  DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
   */

  public int runLength;
  public int r;
  public int g;
  public int b;
  
  public DListNode prev;
  public DListNode next;

  /**
   *  DListNode1() constructor.
   */
  DListNode() {
    prev = null;
    next = null;
  }

  DListNode(int runLength, int r, int g, int b) {
	this.runLength = runLength;
	this.r = r;
	this.g = g;
	this.b = b;
    prev = null;
    next = null;
  }
}