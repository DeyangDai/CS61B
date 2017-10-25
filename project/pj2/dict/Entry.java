/* Entry.java */

package dict;

/**
 * A class for dictionary entries.
 *
 * DO NOT CHANGE THIS FILE. It is part of the interface of the Dictionary ADT.
 **/

public class Entry<K,V> {

	protected K key;
	protected V value;

	public K key() {
		return key;
	}

	public V value() {
		return value;
	}

}
