package assign04;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LargestNumberSolverTimer {
	
	
		public static void main(String[] args){
			// To achieve running times consistent with the insertion sort in findKthLargest, 
			//and not the insertion sort in findLargestNumber, N should be the size of the input list.
			Random rng = new Random();
			
			// Do 10000 lookups per n and use the average running time
			int timesToLoop = 10_000;
			long startTime, midpointTime, stopTime;
			
			System.out.println("--------------------------------findKthLargest() Times--------------------------------");

			// For each problem size n . . .
			for(int n = 100_000; n <= 2_000_000; n += 100_000) {
				
				// Set up
				
				List<Integer[]> list= new ArrayList<Integer[]>();
				Integer[] testArr;
				for (int i = 0; i < n; i++) {
					testArr =  new Integer[] { i, i + 1};
					list.add(testArr);
				}
				
				// First, spin computing stuff until one second has gone by
				// This allows this thread to stabilize
				startTime = System.nanoTime();
				while(System.nanoTime() - startTime < 1000_000_000) { // empty block
				}

				// Now, run the test
				startTime = System.nanoTime();

				for(int i = 0; i < timesToLoop; i++) {
					LargestNumberSolver.findKthLargest(list, i);
				}
					
				
				midpointTime = System.nanoTime();

				// Run a loop to capture the cost of running the "timesToLoop" loop
				for(int i = 0; i < timesToLoop; i++) {
					testArr =  new Integer[] { i, i + 1};
					LargestNumberSolver.findLargestNumber(testArr); 
				}

				stopTime = System.nanoTime();

				// Compute the time, subtract the cost of running the loop
				// from the cost of running the loop and doing the lookups
				// Average it over the number of runs
				double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / 
						(double) timesToLoop;

				System.out.println(n + "\t" + averageTime);
			}
	}
}
