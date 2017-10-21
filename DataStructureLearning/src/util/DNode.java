package util;

/**
 * Node of a doubly linked list.
 */
public class DNode<T> {
	protected T element;
	protected DNode<T> next;
	protected DNode<T> prev;
	
	/**
	 * Constructor that creates a node with given fileds.
	 * @param elem the element stored in this node
	 * @param p the previous node of this node
	 * @param n the next node of this node
	 */
	public DNode(T elem, DNode<T> p, DNode<T> n){
		element = elem;
		prev = p;
		next = n;
	}
	
	/**
	 * @return the element of this node
	 */
	public T getElement(){
		return element;
	}
	
	/**
	 * @return the previous node of this node
	 */
	public DNode<T> getPrev(){
		return prev;
	}
	
	/**
	 * @return the next node of this node
	 */
	public DNode<T> getNext(){
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
	 * Sets the previous node of this node.
	 * @param newPrev
	 */
	public void setPrev(DNode<T> newPrev){
		prev = newPrev;
	}
	
	/**
	 * Sets the next node of this node.
	 * @param newNext
	 */
	public void setNext(DNode<T> newNext){
		next = newNext;
	}
}
