package util;

/**
 * Singly linked list.
 */
public class SLinkedList<T> {
	protected SNode<T> head;	//head node of the list
	protected int size;	//number of nodes in the list
	
	/**
	 * Default constructor that creates an empty list.
	 */
	public SLinkedList(){
		head = null;
		size = 0;
	}
}
