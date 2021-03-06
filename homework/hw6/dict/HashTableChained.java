/* HashTableChained.java */

package dict;

import list.InvalidNodeException;
import list.List;
import list.ListNode;
import list.SList;
import util.NumUtil;

/**
 * HashTableChained implements a Dictionary as a hash table with chaining. All
 * objects used as keys must have a valid hashCode() method, which is used to
 * determine which bucket of the hash table an entry is stored in. Each object's
 * hashCode() is presumed to return an int between Integer.MIN_VALUE and
 * Integer.MAX_VALUE. The HashTableChained class implements only the compression
 * function, which maps the hash code to a bucket in the table's range.
 *
 * DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {

	/**
	 * Place any data fields here.
	 **/
	private int size;
	private SList[] table;
	private int collisions;
	private double loadFactor;
	
	/**
	 * Construct a new empty hash table intended to hold roughly sizeEstimate
	 * entries. (The precise number of buckets is up to you, but we recommend
	 * you use a prime number, and shoot for a load factor between 0.5 and 1.)
	 **/

	public HashTableChained(int sizeEstimate) {
		// Your solution here.
		size = 0;
		loadFactor = 0;
		collisions = 0;
		int sizeOfTable = (int) (sizeEstimate / 0.75);
		while(!NumUtil.isPrime(sizeOfTable)){
			sizeOfTable++;
		}
		table = new SList[sizeOfTable];
		
		for(int i = 0; i < sizeOfTable; i++){
			table[i] = new SList();
		}
	}

	/**
	 * Construct a new empty hash table with a default size. Say, a prime in the
	 * neighborhood of 100.
	 **/

	public HashTableChained() {
		// Your solution here.
		this(100);
	}
	
	public int getBucketSize(){
		return table.length;
	}

	public double getLoadFactor(){
		return loadFactor;
	}
	
	public double getExpectedCollisions(){
		double result = 1 - 1.0 / table.length;
		result = Math.pow(result, size) * table.length;
		result += size - table.length;
		return result;
	}
	
	public int getCollisions(){
		return collisions;
	}
	
	/**
	 * Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
	 * to a value in the range 0...(size of hash table) - 1.
	 *
	 * This function should have package protection (so we can test it), and
	 * should be used by insert, find, and remove.
	 **/

	int compFunction(int code) {
		// Replace the following line with your solution.
		return code < 0 ? (-1 * code) % table.length : code % table.length;
	}

	/**
	 * Returns the number of entries stored in the dictionary. Entries with the
	 * same key (or even the same key and value) each still count as a separate
	 * entry.
	 * 
	 * @return number of entries in the dictionary.
	 **/

	public int size() {
		// Replace the following line with your solution.
		return size;
	}

	/**
	 * Tests if the dictionary is empty.
	 *
	 * @return true if the dictionary has no entries; false otherwise.
	 **/

	public boolean isEmpty() {
		// Replace the following line with your solution.
		if(size == 0)
			return true;
		else
			return false;
	}

	/**
	 * Create a new Entry object referencing the input key and associated value,
	 * and insert the entry into the dictionary. Return a reference to the new
	 * entry. Multiple entries with the same key (or even the same key and
	 * value) can coexist in the dictionary.
	 *
	 * This method should run in O(1) time if the number of collisions is small.
	 *
	 * @param key
	 *            the key by which the entry can be retrieved.
	 * @param value
	 *            an arbitrary object.
	 * @return an entry containing the key and value.
	 **/

	public Entry insert(Object key, Object value) {
		// Replace the following line with your solution.
		Entry newEntry = new Entry();
		newEntry.key = key;
		newEntry.value = value;
		int index = compFunction(key.hashCode());
		if(table[index].length() >= 1)
			collisions++;
		table[index].insertBack(newEntry);
		size++;
		loadFactor = size / (table.length * 1.0);
		return newEntry;
	}

	/**
	 * Search for an entry with the specified key. If such an entry is found,
	 * return it; otherwise return null. If several entries have the specified
	 * key, choose one arbitrarily and return it.
	 *
	 * This method should run in O(1) time if the number of collisions is small.
	 *
	 * @param key
	 *            the search key.
	 * @return an entry containing the key and an associated value, or null if
	 *         no entry contains the specified key.
	 **/

	public Entry find(Object key) {
		// Replace the following line with your solution.
		int index = compFunction(key.hashCode());
		List list = table[index];
		ListNode current = list.front();
		Entry entry = null;
		while(list.length() != 0 && current != null){
			try {
				if(current.item() instanceof Entry)
					entry = (Entry) current.item();
				if(key.equals(entry.key))
					return entry;
				current = current.next();
			} catch (InvalidNodeException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * Remove an entry with the specified key. If such an entry is found, remove
	 * it from the table and return it; otherwise return null. If several
	 * entries have the specified key, choose one arbitrarily, then remove and
	 * return it.
	 *
	 * This method should run in O(1) time if the number of collisions is small.
	 *
	 * @param key
	 *            the search key.
	 * @return an entry containing the key and an associated value, or null if
	 *         no entry contains the specified key.
	 */

	public Entry remove(Object key) {
		// Replace the following line with your solution.
		int index = compFunction(key.hashCode());
		List list = table[index];
		ListNode current = list.front();
		Entry entry = null;
		while(list.length() != 0 && current != null){
			try {
				if(current.item() instanceof Entry)
					entry = (Entry) current.item();
				if(key.equals(entry.key)){
					current.remove();
					if(list.length() >= 2)
						collisions--;
					size--;
					return entry;
				}
				current = current.next();
			} catch (InvalidNodeException e) {
				e.printStackTrace();
			}
		}	
		return null;
	}

	/**
	 * Remove all entries from the dictionary.
	 */
	public void makeEmpty() {
		// Your solution here.
		for(int i = 0; i < table.length; i++){
			table[i] = new SList();
		}
		size = 0;
	}

	
	@Override
	public String toString() {
		String string = "";
		for(int i = 0; i < table.length; i++){
			string += "[" + table[i].length() + "]";
			if( (i+1) % 10 == 0)
				string += "\r\n";
		}
		return string;
	}

	public static void main(String[] args) {
		/*double result = 1 - 1.0 / 100;
		result = Math.pow(result, 100) * 100;
		result += 100 - 100;
		System.out.println(result);*/
		
		HashTableChained table = new HashTableChained();
		System.out.println("=====================size, isEmpty=========================");
        System.out.println("table's size is: " + table.size());
        System.out.println("table is Empty: " + table.isEmpty());
        
        System.out.println("=====================insert================================");
        table.insert("1", "The first one");
        table.insert("2", "The second one");
        table.insert("3", "The third one");
        table.insert("what", "nani?");
        table.insert("the","Eh-heng");
        table.insert("hell!","impolite");
        System.out.println("table's size is: " + table.size());
        System.out.println("table is Empty: " + table.isEmpty());
        System.out.println(table);
        
        System.out.println("====================find, remove===========================");
        Entry e1 = table.find("6");
        if(e1 != null)
                System.out.println("The item found is: [ " + e1.toString() + " ]");
        else
                System.out.println("The is no such item in the table to be found.");
        
        Entry e2 = table.remove("hell!");
        if(e2 != null)
                System.out.println("The item deleted is: [ " + e2.toString() + " ]");
        else
                System.out.println("The is no such item in the table to be deleted.");
        
        System.out.println(table);
        
        System.out.println("=====================makeEmpty=============================");
        table.makeEmpty();
        System.out.println(table);
	}

	
}
