/**
 * 
 */
package assign04;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Patrick Watt
 * @version 2022.02.05
 */
class LargestNumberSolverTest
{
	Integer[] arr1, arr2;
	
	@BeforeEach
	void setUp()
	{
		arr1 = new Integer[] {1,45,9};
		arr2 = new Integer[] {999, 639, 1, 7, 58, 9,};
	}
	
	@Test
	void testFindLargestNumber() {
		assertEquals(9451, LargestNumberSolver.findLargestNumber(arr1));
	}
	@Test
	void testFindLargestNumber01() {
		assertThrows(OutOfRangeException.class, () -> {LargestNumberSolver.findLargestNumber(arr2);});
	}
}
