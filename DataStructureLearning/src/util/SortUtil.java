package util;

public class SortUtil {
	static void quickSort(int[] a, int low, int high){
		int i = low-1, j = high+1, k = a[(low + high)>>1];
		while(i < j){
			while(a[++i] < k); //i++;
			while(a[--j] > k); //j--;
			if(i <= j){
				int temp = a[i];
				a[i] = a[j];
				a[j] = temp;
				//i++;
				//j--;
			}
		}
		if(low < j) quickSort(a, low, j);
		if(i < high) quickSort(a, i, high);
	}
}
