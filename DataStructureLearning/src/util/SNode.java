package util;

/**
 * Node of a singly linked list.
 */
public class SNode<T> {
	private T element;
	private SNode<T> next;
	
	/**
	 * Creates a node with the given element and next node.
	 */
	public SNode(T elem, SNode<T> node){
		element = elem;
		next = node;
	}
	
	/**
	 * @return the element of this node 
	 */
	public T getElement(){
		return element;
	}
	
	/**
	 * @return the next node of this node
	 */
	public SNode<T> getNext(){
		return next;
	}
	
	/**
	 * Sets the element of this node.
	 * @param newElem
	 */
	public void setElement(T newElem){
		element = newElem;
	}
	
	/**
	 * Sets the next node of this node.
	 * @param newNext
	 */
	public void setNext(SNode<T> newNext){
		next = newNext;
	}
}
