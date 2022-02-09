/**
 * 
 */
package assign04;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Patrick Watt & Jackson Fairbourn
 * @version 2022.02.05
 */
class LargestNumberSolverTest{
	
	Integer[] arr1, arr2, arr3, arr4, longArrHuge;
	List<Integer[]> intArrList = new ArrayList<Integer[]>();
	
	@BeforeEach
	void setUp(){
		arr1 = new Integer[] {1,45,9};
		arr2 = new Integer[] {5, 12, 52, 37, 4 };      // 55243712
		arr3 = new Integer[] {999, 639, 1, 7, 58, 9,}; // 99997639581L 
		arr4 = new Integer[] {11, 67, 79, 7, 22, 13};  // 79767221311
		
		longArrHuge = new Integer[64];
		for(int i = 0; i < 64; i++) {
			longArrHuge[i] = 99;
		}
		
		intArrList.add(arr1);
		intArrList.add(arr2);
	}
	
	@Test 
	void testInsertionSort() {
		Integer[] expected1 = {1, 9, 45};
		Integer[] expected2 = {4, 5, 12, 37, 52};
		Integer[] expected3 = {1, 7, 9, 58, 639, 999};
		
		LargestNumberSolver.insertionSort(arr1, new basicComparator());
		LargestNumberSolver.insertionSort(arr2, new basicComparator());
		LargestNumberSolver.insertionSort(arr3, new basicComparator());
		
		for(int i = 0; i < expected1.length; i++) {
			assertEquals(expected1[i], arr1[i]);
		}
		
		for(int i = 0; i < expected2.length; i++) {
			assertEquals(expected2[i], arr2[i]);
		}
		
		for(int i = 0; i < expected3.length; i++) {
			assertEquals(expected3[i], arr3[i]);
		}
	}
	
	private static class basicComparator implements Comparator<Integer>{
		/**
		 * @return 
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		public int compare(Integer number, Integer other){
			return number.compareTo(other);
		}
	}
	
	@Test
	void testFindLargestNumber() {
		Integer[] arr1Copy = arr1.clone();
		
		assertEquals(new BigInteger("9451"), LargestNumberSolver.findLargestNumber(arr1));
		
		for (int i = 0; i < arr1Copy.length; i++) {
			assertEquals(arr1Copy[i], arr1[i]);
		}
	}
	
	@Test
	void testFindLargestNumberAdditional() {		
		assertEquals(new BigInteger("9451"), LargestNumberSolver.findLargestNumber(arr1));
		assertEquals(new BigInteger("55243712"), LargestNumberSolver.findLargestNumber(arr2));
		assertEquals(new BigInteger("99997639581"), LargestNumberSolver.findLargestNumber(arr3));
		assertEquals(new BigInteger("79767221311"), LargestNumberSolver.findLargestNumber(arr4));
	}
	
	@Test
	void testFindLargestNumberEmpty() {
		assertEquals(new BigInteger("0"), LargestNumberSolver.findLargestNumber(new Integer[0]));
	}
	
	@Test
    void testFindLargestLong() {
		Integer[] arr3Copy = arr3.clone();
        assertEquals(99997639581L, LargestNumberSolver.findLargestLong(arr3));
        for (int i = 0; i < arr3Copy.length; i++) {
			assertEquals(arr3Copy[i], arr3[i]);
		}
    }
	
	@Test
	void testFindLargestLongOutOfRange() {
		assertThrows(OutOfRangeException.class, () -> {LargestNumberSolver.findLargestLong(longArrHuge);});
	}
	
	@Test
	void testFindLargestInt() {
		Integer[] arr1Copy = arr1.clone();
		assertEquals(9451, LargestNumberSolver.findLargestInt(arr1));
		for (int i = 0; i < arr1Copy.length; i++) {
			assertEquals(arr1Copy[i], arr1[i]);
		}
	}
	
	@Test
	void testFindLargestIntOutOfRange() {
		assertThrows(OutOfRangeException.class, () -> {LargestNumberSolver.findLargestInt(longArrHuge);}); 
	}
	
	@Test
	void testSum() {
		BigInteger sum = new BigInteger("55253163");
		ArrayList<Integer[]> sumIntList = new ArrayList<Integer[]>(intArrList);

		assertEquals(sum, LargestNumberSolver.sum(intArrList));
		assertEquals(sumIntList, intArrList);
	}
	
	@Test
	void testFindKthLargest() {
		assertEquals(arr1, LargestNumberSolver.findKthLargest(intArrList, 1));
		assertEquals(arr2, LargestNumberSolver.findKthLargest(intArrList, 0));
	}

	@Test
	void testFindKthLargestDoesNotChange() {
		ArrayList<Integer[]> listCopy = new ArrayList<Integer[]>(intArrList);
		Integer[] arr1Copy = arr1.clone();
		Integer[] arr2Copy = arr2.clone();
		
		LargestNumberSolver.findKthLargest(intArrList, 0);
		
		assertEquals(listCopy, intArrList);
		
		for (int i = 0; i < arr1Copy.length; i++) {
			assertEquals(arr1Copy[i], arr1[i]);
		}
		for (int i = 0; i < arr2Copy.length; i++) {
			assertEquals(arr2Copy[i], arr2[i]);
		}
	}
	
	@Test
	void testFindKthLargestEffectExeption() {
		assertThrows(IllegalArgumentException.class, () -> {LargestNumberSolver.findKthLargest(intArrList, -1);});
	}
	
	@Test
	void testReadFile() {
		List<Integer[]> testList = LargestNumberSolver.readFile("src/assign04/integersSmall.txt");
		
		List<Integer[]> expectedList = new ArrayList<Integer[]>();
		Integer[] tmp1 = {62, 42, 20, 69, 56};
		Integer[] tmp2 = {67, 10, 45, 31, 61};
		Integer[] tmp3 = {98, 5231, 67, 96, 88};
		Integer[] tmp4 = {88, 51};
		
		expectedList.add(tmp1);
		expectedList.add(tmp2);
		expectedList.add(tmp3);
		expectedList.add(tmp4);
		
		for (int i = 0; i < expectedList.size(); i++)
			for (int j = 0; j < expectedList.get(i).length; j++)
				assertEquals(expectedList.get(i)[j], testList.get(i)[j]);
	}
	
	@Test
	void testReadFileEmpty() {
		List<Integer[]> testList = LargestNumberSolver.readFile("DNE.txt");
		List<Integer[]> expectedList = new ArrayList<Integer[]>();
		
		assertEquals(expectedList, testList);
	}
}
