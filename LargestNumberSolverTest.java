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
 * @author Patrick Watt
 * @version 2022.02.05
 */
class LargestNumberSolverTest
{
	Integer[] arr1, arr2, arr3, longArrHuge;
	List<Integer[]> intList = new ArrayList<Integer[]>();
	
	@BeforeEach
	void setUp()
	{
		arr1 = new Integer[] {1,45,9};
		arr2 = new Integer[] {999, 639, 1, 7, 58, 9,};
		arr3 = new Integer[] {5, 12, 52, 37, 4 }; // 55243712
		
		
		intList.add(arr1);
		intList.add(arr2);
		
	}
	
	@Test
	void testFindLargestNumber() {
		assertEquals(9451, LargestNumberSolver.findLargestNumber(arr1));
	}
	@Test
	void testFindLargestNumberOutOfRange() {
		assertThrows(OutOfRangeException.class, () -> {LargestNumberSolver.findLargestNumber(arr2);});
	}
	
	@Test
    void testFindLargestLong() {
        assertEquals(99997639581L, LargestNumberSolver.findLargestLong(arr2));
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
		assertEquals(9451, LargestNumberSolver.findLargestNumber(arr1));
	}
	
	@Test
	void testFindLargestIntOutOfRange() {
		assertThrows(OutOfRangeException.class, () -> {LargestNumberSolver.findLargestInt(arr2);}); 
	}
	
	@Test
	void testSum() {
		int sum = (9451 + 55243712) ; // maybe bigInteger?
		ArrayList<Integer[]> sumIntList = new ArrayList<Integer[]>(intList);

		assertEquals(sum, LargestNumberSolver.sum(intList));
		assertEquals(sumIntList, intList);
	}
}
