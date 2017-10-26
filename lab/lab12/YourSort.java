/* YourSort.java */

public class YourSort {

	static int MAX_INSERT_SIZE = 32;
	
	public static void sort(int[] A) {
		// Place your Part III code here.
		//1-32 insert
		//33-300 quick
		if(A.length <= MAX_INSERT_SIZE){
			Sort.insertionSort(A);
		} else{
			Sort.quicksort(A);
		}
	}
}
