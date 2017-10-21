package util;

import java.util.HashMap;

public class DList<T> {
	protected int size;	//number of elements
	protected DNode<T> header, trailer;	//sentinels
	
	/**
	 * Constructor that creates an empty list.
	 */
	public DList(){
		size = 0;
		header = new DNode<T>(null, null, null);
		trailer = new DNode<T>(null, header, null);
		header.setNext(trailer);
	}
	
	/**
	 * @return the number of elements in the list
	 */
	public int size(){
		return size;
	}

	/**
	 * @return whether the list is empty
	 */
	public boolean isEmpty(){
		return size == 0;
	}
	
	/**
	 * @return the first node of the list
	 * @throws IllegalStateException
	 */
	public DNode<T> getFirst() throws IllegalStateException{
		if(isEmpty()){
			throw new IllegalStateException("List is empty");
		}
		return header.getNext();
	}
	
	/**
	 * @return the last node of the list
	 * @throws IllegalStateException
	 */
	public DNode<T> getLast() throws IllegalStateException{
		if(isEmpty()){
			throw new IllegalStateException("List is empty");
		}
		return trailer.getPrev();
	}
	
	/**
	 * @param v the given node
	 * @return the node before the given node
	 * @throws IllegalStateException an error occurs if v is the header
	 */
	public DNode<T> getPrev(DNode<T> v) throws IllegalStateException{
		if(v == header){
			throw new IllegalStateException
			("Cannot move back past the header of the list");
		}
		return v.getPrev();
	}
	
	/**
	 * @param v the given node
	 * @return the node after the given node v
	 * @throws IllegalStateException an error occurs if v is the trailer
	 */
	public DNode<T> getNext(DNode<T> v) throws IllegalStateException{
		if(v == trailer){
			throw new IllegalStateException
			("Cannot move forward past the trailer of the list");
		}
		return v.getNext();
	}
	
	/**
	 * Inserts the given node z before the given node v
	 * @param v 
	 * @param z 
	 * @throws IllegalStateException an error occurs if v is the head
	 */
	public void addBefore(DNode<T> v, DNode<T> z) throws IllegalStateException{
		DNode<T> u = getPrev(v); // may throw an IllegalStateException
		z.setPrev(u);
		z.setNext(v);
		v.setPrev(z);
		u.setNext(z);
		size++;
	}
	
	/**
	 * Inserts the given node z after the given node v
	 * @param v 
	 * @param z 
	 * @throws IllegalStateException an error occurs if v is the head
	 */
	public void addAfter(DNode<T> v, DNode<T> z){
		DNode<T> w = getNext(v); // may throw an IllegelStateException
		z.setPrev(v);
		z.setNext(w);
		w.setPrev(z);
		v.setNext(z);
		size++;
	}
	
	/**
	 * Inserts the given node at the head of the list
	 * @param v
	 */
	public void addFirst(DNode<T> v){
		addAfter(header, v);
	}
	
	/**
	 * Inserts the given node v at the tail of the list
	 * @param v
	 */
	public void addLast(DNode<T> v){
		addBefore(trailer, v);
	}
	
	/**
	 * Removes the given node v from the list.
	 * An error occurs if v is the header or trailer.
	 * @param v
	 */
	public void remove(DNode<T> v){
		DNode<T> u = getPrev(v); // may throw an IllegalStateException
		DNode<T> w = getNext(v); // may throw an IllegalStateException
		
		//unlink the node from the list
		w.setPrev(u);
		u.setNext(w);
		v.setNext(null);
		v.setPrev(null);
		size--;
	}
	
	/**
	 * @param v
	 * @return whether a given node has a previous node
	 */
	public boolean hasPrev(DNode<T> v){
		return v != header;
	}
	
	/**
	 * @param v
	 * @return whether a given node has a next node
	 */
	public boolean hasNext(DNode<T> v){
		return v != trailer;
	}
	
	@Override
	public String toString() {
		String string = "[";
		DNode<T> v = header.getNext();
		while(v != trailer){
			string += v.getElement();
			v = v.getNext();
			if(v != trailer){
				string += ",";
			}
		}
		string += "]";
		return string;
	}
}
