/**
 * 
 */
package assign04;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Patrick Watt & Jackson Fairbourn
 * @version 2022.02.05
 */
class LargestNumberSolverTest
{
	Integer[] arr1, arr2, arr3, longArrHuge;
	List<Integer[]> intArrList = new ArrayList<Integer[]>();
	
	@BeforeEach
	void setUp()
	{
		arr1 = new Integer[] {1,45,9};
		arr2 = new Integer[] {5, 12, 52, 37, 4 }; // 55243712
		arr3 = new Integer[] {999, 639, 1, 7, 58, 9,};
		
		
		intArrList.add(arr1);
		intArrList.add(arr2);
		
	}
	
	@Test 
	void testInsertionSort() {
		fail("Uniplemented Test");
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
	void testFindLargestNumberEmpty() {
		assertEquals(new BigInteger("0"), LargestNumberSolver.findLargestNumber(new Integer[0]));
	}
//	
	@Test
    void testFindLargestLong() {
		Integer[] arr2Copy = arr2.clone();
        assertEquals(99997639581L, LargestNumberSolver.findLargestLong(arr2));
		assertEquals(arr2Copy, arr2);
    }
	
	@Test
	void testFindLargestLongOutOfRange() {
		for(int i = 0; i < 64; i++) {
			longArrHuge[i] = 99;
		}
		assertThrows(OutOfRangeException.class, () -> {LargestNumberSolver.findLargestLong(longArrHuge);});
	}
	
	@Test
	void testFindLargestInt() {
		Integer[] arr1Copy = arr1.clone();
		assertEquals(9451, LargestNumberSolver.findLargestInt(arr1));
		assertEquals(arr1Copy, arr1);
	}
	
	@Test
	void testFindLargestIntOutOfRange() {
		assertThrows(OutOfRangeException.class, () -> {LargestNumberSolver.findLargestInt(arr2);}); 
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
		assertEquals(arr1Copy, arr1);
		assertEquals(arr2Copy, arr2);
	}
	
	@Test
	void testFindKthLargestEffectExeption() {
		assertThrows(IllegalArgumentException.class, () -> {LargestNumberSolver.findKthLargest(intArrList, -1);});
	}
	
	@Test
	void testReadFile() {
		List<Integer[]> testList = LargestNumberSolver.readFile("integersSmall.txt");
		
		List<Integer[]> expectedList = new ArrayList<Integer[]>();
		Integer[] tmp1 = {62, 42, 20, 69, 56};
		Integer[] tmp2 = {67, 10, 45, 31, 61};
		Integer[] tmp3 = {98, 5231, 67, 96, 88};
		Integer[] tmp4 = {88, 51};
		
		expectedList.add(tmp1);
		expectedList.add(tmp2);
		expectedList.add(tmp3);
		expectedList.add(tmp4);
		
		assertEquals(expectedList, testList);
	}
	
	@Test
	void testReadFileEmpty() {
		List<Integer[]> testList = LargestNumberSolver.readFile("DNE.txt");
		List<Integer[]> expectedList = new ArrayList<Integer[]>();
		
		assertEquals(expectedList, testList);
	}
}
