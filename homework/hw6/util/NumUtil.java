/* NumUtil.java */

package util;

/**
 * A class for Number Utility.
 **/

public class NumUtil {

	/**
	 * 
	 * @param num
	 * @return true if num is prime, or false if not.
	 */
	public static boolean isPrime(int num) {
		if(num == 2)
			return true;
		if(num <= 1 || num % 2 == 0)
			return false;
		for(int i = 3; i * i < num; i++){
			if(num % i == 0)
				return false;
		}
		return true;
	}
}
