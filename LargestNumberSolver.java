/**
 * 
 */
package assign04;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author Patrick Watt & Jackson Fairbourn
 * @version 2022.02.05
 */
public class LargestNumberSolver 
{
	/**
	 * This generic method sorts the input array using an insertion sort and the input Comparator object.
	 * @param <T>
	 * @param arr the array to sort
	 * @param cmp how you sort the elements.
	 */
	public static <T> void insertionSort(T[] arr, Comparator<? super T> cmp){
		T previous, temp;

		for(int i = 1; i < arr.length; i++) { // sort each value
			int j = i - 1;
			temp = arr[i];
			previous = arr[j];

			while (cmp.compare(temp, previous) > 0 && j >= 0){ // shift value to correct position (compare to previously sorted items)
				arr[j + 1] = arr[j]; // compare to previous item
				j = j - 1;
				if(j >= 0) { 		 // set previous to be next item to compare.
					previous = arr[j];
				}
			}
			arr[j + 1] = temp;
		}
	}

/**
 * This method returns the largest number that can be formed by arranging the integers of the given array, in any order.   
 * If the array is empty, the largest number that can be formed is 0.  
 * This method must not alter the given array and must call your insertionSort method with a Comparator or lambda expression that you design.
 * @param arr
 * @return
 */
public static BigInteger findLargestNumber(Integer[] arr) { // 9 58 7 999 639 1
	if(arr.length == 0) {
		return new BigInteger("0");
	}
	
	// call insertionsort
	String number = "";
	Integer[] arrCopy = arr.clone();
	
	
	insertionSort(arrCopy, new SolverCompare());
	
	StringBuilder bigNumber = new StringBuilder();
	for(int i = 0; i < arrCopy.length; i++) {
		bigNumber.append(arrCopy[i]);
	}
	
	number = bigNumber.toString();
	
	return new BigInteger(number);
}

private static class SolverCompare implements Comparator<Integer>
{
	/**
	 * @return 
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Integer number, Integer other){
		char[] numberStr = number.toString().toCharArray();
		char[] otherStr = other.toString().toCharArray();
		int i = 0;
		
		while(i < numberStr.length && i < otherStr.length){ // 999 1
			//if(length is same do this) values are different
			if(numberStr[i] < otherStr[i])		// First Digit comparison
				return -1;
			if(numberStr[i] > otherStr[i])
				return 1;
			i++;
		}
		
		if(numberStr.length < otherStr.length)	 // other digit comparison
			return 1;
		if(numberStr.length > otherStr.length)
			return -1;
		return 0;
	}
}

/**
 * This method returns the largest int value that can be formed by arranging the integers of the given array, in any order.  
 * An OutOfRangeException Download OutOfRangeException is thrown if the largest number that can be formed is too large for the int data type.  
 * Logic for solving the problem of determining the largest number should not appear again in this method — call an existing public method or a helper method.  
 * This method must not alter the given array.
 * @param arr the array to look through
 * @return the largest int value that can be formed by arranging the integers of the given array, in any order.
 * @throws OutOfRangeException if overflows int capacity.
 */
public static int findLargestInt(Integer[] arr) throws OutOfRangeException{
	BigInteger bigIntNum = findLargestNumber(arr);
	int intNum = bigIntNum.intValue();
	if(bigIntNum.equals(new BigInteger(((Integer)intNum).toString()))){
		return intNum;
	}
	throw new OutOfRangeException("int");
}

/**
 * This method behaves the same as the previous method, but for data type long instead of data type int.
 * @param arr
 * @return
 * @throws OutOfRangeException
 */
public static long findLargestLong(Integer[] arr) throws OutOfRangeException{
	BigInteger bigIntNum = findLargestNumber(arr);
	long longNum = bigIntNum.longValue();
	if(bigIntNum.equals(new BigInteger(((Long)longNum).toString()))){
		return longNum;
	}
	throw new OutOfRangeException("long");
}

/**
 * This method sums the largest numbers that can be formed by each array in the given list.  
 * This method must not alter the given list.
 * @param list
 * @return
 */
public static BigInteger sum(List<Integer[]> list) {
	BigInteger sum = new BigInteger("0");
	
	for (Integer[] e : list) {
		sum = sum.add(new BigInteger(findLargestNumber(e).toString()));
	}
	
	return sum;
}

/**
 * This method determines the kth largest number that can be formed by each array in the given list.  
 * E.g., if k=0 returns the largest overall, if k=list.size()-1 returns the smallest overall.  
 * This method returns the original array that represents the kth largest number, not the kth largest number itself.  
 * An IllegalArgumentException (Links to an external site.) is thrown if k is not a valid position in the list.  
 * This method must not alter the given list and must call your insertionSort method with a Comparator or lambda expression that you design.
 * @param list
 * @param k
 * @return
 * @throws IllegalArgumentException
 */
public static Integer[] findKthLargest(List<Integer[]> list, int k) throws IllegalArgumentException{
	return null;

}

/**
 * This method generates list of integer arrays from an input file, 
 * such that each line corresponds to one array of integers separated by blank spaces, 
 * and returns an empty list if the file does not exist.
 * @param filename
 * @return
 */
public static List<Integer[]> readFile(String filename){
	return null;
}
}
