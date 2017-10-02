/* DList1.java */

/**
 *  A DList1 is a mutable doubly-linked list.  (No sentinel, not
 *  circularly linked.)
 */

public class DList {

  /**
   *  head references the first node.
   *  tail references the last node.
   *
   *  DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
   */

  protected DListNode head;
  protected DListNode tail;
  protected DListNode current;
  private int size;

  /* DList1 invariants:
   *  1)  head.prev == null.
   *  2)  tail.next == null.
   *  3)  For any DListNode1 x in a DList, if x.next == y and x.next != null,
   *      then y.prev == x.
   *  4)  For any DListNode1 x in a DList, if x.prev == y and x.prev != null,
   *      then y.next == x.
   *  5)  The tail can be accessed from the head by a sequence of "next"
   *      references.
   *  6)  size is the number of DListNode1s that can be accessed from the
   *      head by a sequence of "next" references.
   */

  /**
   *  DList1() constructor for an empty DList1.
   */
  public DList() {
    head = null;
    tail = null;
    size = 0;
  }

  /**
   *  DList1() constructor for a one-node DList1.
   */
  public DList(int runLength, int r, int g, int b) {
    head = new DListNode(runLength, r, g, b);
    tail = head;
    size = 1;
  }


  /**
   *  insertFront() inserts an item at the front of a DList1.
   */
  public void insertFront(int runLength, int r, int g, int b) {
    // Your solution here.
	if(size == 0){
		head = new DListNode(runLength, r, g, b);
		tail = head;
		size = 1;
	} else{
		head.prev = new DListNode(runLength, r, g, b);
		head.prev.next = head;
		head = head.prev;
		size++;
	}
  }

  /**
   *  removeFront() removes the first item (and node) from a DList1.  If the
   *  list is empty, do nothing.
   */
  public void removeFront() {
    // Your solution here.
	if(size > 1){
		head.next.prev = null;
		head = head.next;
		size--;
	} else if(size == 1){
		head = null;
		tail = null;
		size--;
	}
  }

  public void insertLast(int runLength, int r, int g, int b){
	  if(size == 0){
			insertFront(runLength, r, g, b);
	  } else{
		  DListNode newNode = new DListNode(runLength, r, g, b);
		  newNode.prev = tail;
		  tail.next = newNode;
		  tail = tail.next;
		  size++;
	  }
  }
  
  public int length(){
	  return size;
  }
  
  public int getRunLengthSum(){
	  current = head;
	  int count = current.runLength;
	  while(current.next != null){
		  count += current.next.runLength;
		  current = current.next;
	  }
	  return count;
  }
  
  protected void update(int index){
	  if(index != getRunLengthSum()){
		  removeNth(index + 1);
	  }
	  deduplicate();
  }
  
  public void deduplicate() {
	  current = head;
	  while(current.next != null){
		  if(current.runLength == 0){
			  DListNode temp = current.next;
			  remove(current);
			  current = temp;
		  }
		  if(current.r == current.next.r && current.g == current.next.g && current.b == current.next.g){
			  current.runLength += current.next.runLength;
			  remove(current.next);
			  continue;
		  }
		  if(current.next != null)
			  current = current.next;
	  }
  }
  
  public void remove(DListNode node) {
	  if(node.next != null){
		  if(node.prev == null){
			  removeFront();
		  } else{
			  node.prev.next = node.next;
			  node.next.prev = node.prev;
			  size--;
		  }
	  } else{
		  removeLast();
	  }
	
  }

  public void removeLast(){
	  if(size > 1){
		  tail = tail.prev;
		  tail.next = null;
		  size--;
	  } else if(size == 1){
		  removeFront();
	  }
  }
  
	private void removeNth(int position){
		current = head;
		int count = 0;
		while(true){
			count += current.runLength;
			if(count >= position){
				decrease(current);
				break;
			} else {
				if(current.next != null){
					current = current.next;
				}
			}
		}
		
			  
	}
  
  private void decrease(DListNode current) {
	  	if(current.runLength == 1){
	  		remove(current);
	  	} else{
	  		current.runLength--;
	  	}
	}



/**
   *  toString() returns a String representation of this DList.
   *
   *  DO NOT CHANGE THIS METHOD.
   *
   *  @return a String representation of this DList.
   */
  public String toString() {
    String string = "";
    DListNode current = head;
    while (current != null) {
		string += String.format("(%d r:%d g:%d b:%d) ", current.runLength, current.r, current.g, current.b);
		current = current.next;
	}
    string += "\r\n";
    return string;
  }

  public static void main(String[] args) {
	DList list = new DList();
	list.insertLast(2, 1, 1, 1);
	list.insertLast(2, 2, 2, 2);
	list.insertLast(3, 3, 3, 3);
	list.insertLast(4, 4, 4, 4);
	list.insertLast(5, 5, 5, 5);
	System.out.println(list.length());
	System.out.println(list + "(should be (2,1)(2,2)(3,3)(4,4)(5,5))"); 
	list.removeLast();
	System.out.println(list.length());
	System.out.println(list + "(should be (2,1)(2,2)(3,3)(4,4))");
	list.current = list.head;
	list.current = list.current.next;
	list.remove(list.current);   //private
	System.out.println(list.length());
	System.out.println(list + "(should be (2,1)(3,3)(4,4))");
	list.removeNth(2); //private
	System.out.println(list.length());
	System.out.println(list + "(should be (1,1)(3,3)(4,4))");
	list.removeNth(2); //private
	System.out.println(list.length());
	System.out.println(list + "(should be (1,1)(2,3)(4,4))");
	list.removeNth(3); //private
	System.out.println(list.length());
	System.out.println(list + "(should be (1,1)(1,3)(4,4))");
	list.removeNth(2); //private
	System.out.println(list.length());
	System.out.println(list + "(should be (1,1)(4,4))");
	
  }
  
}