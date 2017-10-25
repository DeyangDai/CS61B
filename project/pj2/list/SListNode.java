/* SListNode.java */

package list;

import java.util.LinkedList;

/**
 * An SListNode is a mutable node in an SList (singly-linked list).
 **/

public class SListNode<T> extends ListNode<T> {

	/**
	 * (inherited) item references the item stored in the current node.
	 * (inherited) myList references the List that contains this node. next
	 * references the next node in the SList.
	 *
	 * DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
	 **/

	protected SListNode<T> next;

	/**
	 * SListNode() constructor.
	 * 
	 * @param i
	 *            the item to store in the node.
	 * @param l
	 *            the list this node is in.
	 * @param n
	 *            the node following this node.
	 */
	SListNode(T i, SList<T> l, SListNode<T> n) {
		item = i;
		myList = l;
		next = n;
	}

	/**
	 * next() returns the node following this node. If this node is invalid,
	 * throws an exception.
	 *
	 * @return the node following this node.
	 * @exception InvalidNodeException
	 *                if this node is not valid.
	 *
	 *                Performance: runs in O(1) time.
	 */
	public ListNode<T> next() throws InvalidNodeException {
		if (!isValidNode()) {
			throw new InvalidNodeException("next() called on invalid node");
		}
		if (next == null) {
			// Create an invalid node.
			SListNode<T> node = ((SList<T>) myList).newNode(null, null);
			node.myList = null;
			return node;
		} else {
			return next;
		}
	}

	/**
	 * prev() returns the node preceding this node. If this node is invalid,
	 * throws an exception.
	 *
	 * @param node
	 *            the node whose predecessor is sought.
	 * @return the node preceding this node.
	 * @exception InvalidNodeException
	 *                if this node is not valid.
	 *
	 *                Performance: runs in O(this.size) time.
	 */
	public ListNode<T> prev() throws InvalidNodeException {
		if (!isValidNode()) {
			throw new InvalidNodeException("prev() called on invalid node");
		}
		SListNode<T> prev = ((SList<T>) myList).head;
		if (prev == this) {
			// Create an invalid node.
			prev = ((SList<T>) myList).newNode(null, null);
			prev.myList = null;
		} else {
			while (prev.next != this) {
				prev = prev.next;
			}
		}
		return prev;
	}

	/**
	 * insertAfter() inserts an item immediately following this node. If this
	 * node is invalid, throws an exception.
	 *
	 * @param item
	 *            the item to be inserted.
	 * @exception InvalidNodeException
	 *                if this node is not valid.
	 *
	 *                Performance: runs in O(1) time.
	 */
	public void insertAfter(T item) throws InvalidNodeException {
		if (!isValidNode()) {
			throw new InvalidNodeException("insertAfter() called on invalid node");
		}
		SListNode<T> newNode = ((SList<T>) myList).newNode(item, next);
		if (next == null) {
			((SList<T>) myList).tail = newNode;
		}
		next = newNode;
		myList.size++;
	}

	/**
	 * insertBefore() inserts an item immediately preceding this node. If this
	 * node is invalid, throws an exception.
	 *
	 * @param item
	 *            the item to be inserted.
	 * @exception InvalidNodeException
	 *                if this node is not valid.
	 *
	 *                Performance: runs in O(this.size) time.
	 */
	public void insertBefore(T item) throws InvalidNodeException {
		if (!isValidNode()) {
			throw new InvalidNodeException("insertBefore() called on invalid node");
		}
		SListNode<T> newNode = ((SList<T>) myList).newNode(item, this);
		if (this == ((SList<T>) myList).head) {
			((SList<T>) myList).head = newNode;
		} else {
			SListNode<T> prev = (SListNode<T>) prev();
			prev.next = newNode;
		}
		myList.size++;
	}

	/**
	 * remove() removes this node from its SList. If this node is invalid,
	 * throws an exception.
	 *
	 * @exception InvalidNodeException
	 *                if this node is not valid.
	 *
	 *                Performance: runs in O(this.size) time.
	 */
	public void remove() throws InvalidNodeException {
		if (!isValidNode()) {
			throw new InvalidNodeException("remove() called on invalid node");
		}
		if (this == ((SList<T>) myList).head) {
			((SList<T>) myList).head = next;
			if (next == null) {
				((SList<T>) myList).tail = null;
			}
		} else {
			SListNode<T> prev = (SListNode<T>) prev();
			prev.next = next;
			if (next == null) {
				((SList<T>) myList).tail = prev;
			}
		}
		myList.size--;

		// Make this node an invalid node, so it cannot be used to corrupt
		// myList.
		myList = null;
		// Set other reference to null to improve garbage collection.
		next = null;
	}

}
