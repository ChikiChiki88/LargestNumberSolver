/**
 * 
 */
package assign04;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * This class represents a LargestNumber solver, which sorts and generates arrays that represent the largest possible number created by those arrays.
 * @author Patrick Watt & Jackson Fairbourn
 * @version 2022.02.05
 */
public class LargestNumberSolver {
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
	 * @param arr the array to search
	 * @return the largest number
	 */
	public static BigInteger findLargestNumber(Integer[] arr) {
		if(arr.length == 0)
			return new BigInteger("0");


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

	private static class SolverCompare implements Comparator<Integer>{
		/**
		 * @return 1 if the number comes after, -1 if it should come before, 0 if they are the same.
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

	private static class ArrayComparator implements Comparator<Object>{
		/**
		 * @return positive if the largestNumber created by an Integer array is bigger, negative if smaller, or 0 if the same as another Integer array
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		@Override
		public int compare(Object o1, Object o2) {
			if (!(o1 instanceof Integer[] && o2 instanceof Integer[]))
				throw new IllegalArgumentException();

			Integer[] a1 = (Integer[]) o1;
			Integer[] a2 = (Integer[]) o2;

			BigInteger first = findLargestNumber(a1);
			BigInteger second = findLargestNumber(a2);

			BigInteger result = first.subtract(second);
			return result.intValue();
		}
	}

	/**
	 * This method returns the largest int value that can be formed by arranging the integers of the given array, in any order.  
	 * An OutOfRangeException is thrown if the largest number that can be formed is too large for the int data type.  
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
	 * This method returns the largest long value that can be formed by arranging the integers of the given array, in any order.  
	 * An OutOfRangeException is thrown if the largest number that can be formed is too large for the long data type.  
	 * @param arr the array to look through
	 * @return the largest long value that can be formed by arranging the integers of the given array, in any order.
	 * @throws OutOfRangeException if overflows long capacity
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
	 * @param list the list to look through and sum
	 * @return the sum as a BigInteger object
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
	 * An IllegalArgumentException is thrown if k is not a valid position in the list.  
	 * @param list the list to look through
	 * @param k the index of the element to serach for
	 * @return  the original array that represents the kth largest number
	 * @throws IllegalArgumentException if k is an invalid index
	 */
	public static Integer[] findKthLargest(List<Integer[]> list, int k) throws IllegalArgumentException {
		if (k > list.size() - 1 || k < 0)
			throw new IllegalArgumentException();

		Object[] a = list.toArray();
		insertionSort(a, new ArrayComparator());
		
		Integer[] result = (Integer[]) a[k];
		return result;
	}

	/**
	 * This method generates list of integer arrays from an input file, 
	 * such that each line corresponds to one array of integers separated by blank spaces, 
	 * @param filename the name of the file to read
	 * @return a list of integer arrays, or  an empty list if the file does not exist.
	 */
	public static List<Integer[]> readFile(String filename){
		List<Integer[]> resultList = new ArrayList<Integer[]>();

		try {
			File file = new File(filename);
			if (!file.exists())
				return resultList;
			
			Scanner fileIn = new Scanner(file);

			while(fileIn.hasNextLine()) {				
				String thisLine = fileIn.nextLine();
				Scanner lineScanner = new Scanner(thisLine);
				List<Integer> tmp = new ArrayList<Integer>();

				while(lineScanner.hasNext()) {
					tmp.add(lineScanner.nextInt());
				}

				resultList.add(tmp.toArray(new Integer[tmp.size()]));
				tmp.clear();
				lineScanner.close();
			}
			fileIn.close();
			return resultList;
		}
		catch(FileNotFoundException e) {
			return resultList;
		}
	}
}
