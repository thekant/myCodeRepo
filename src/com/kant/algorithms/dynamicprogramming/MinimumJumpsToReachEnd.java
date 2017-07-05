/**
 * 
 */
package com.kant.algorithms.dynamicprogramming;

import java.util.Arrays;

/**
 * http://www.geeksforgeeks.org/minimum-number-of-jumps-to-reach-end-of-a-given-
 * array/
 * 
 * @author shaskant
 *
 */
public class MinimumJumpsToReachEnd {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int arr[] = { 1, 3, 6, 3, 2, 3, 6, 8, 9, 5 };
		System.out.printf("Minimum number of jumps to reach end is %d ",
				minJumpsIterative(arr));
	}

	/**
	 * NAIVE recursive approach
	 * 
	 * @param arr
	 * @param src
	 * @param target
	 * @return
	 */
	static int minJumps(int[] arr, int src, int target) {
		if (src == target) {
			return 0;
		}
		if (arr[src] == 0)
			return Integer.MAX_VALUE;

		int minVal = Integer.MAX_VALUE;
		for (int i = src + 1; i <= src + arr[src] && i <= target; i++) {
			int jumps = minJumps(arr, i, target);

			if (jumps != Integer.MAX_VALUE && minVal > jumps + 1) {
				minVal = jumps + 1;
			}
		}
		return minVal;
	}

	/**
	 * Dynamic programming approach O(n^2)
	 * 
	 * @param arr
	 * @return
	 */
	static int minJumpsIterative(int[] arr) {
		int n = arr.length;
		if (n == 0 || arr[0] == 0)
			return Integer.MAX_VALUE;

		int[] jumps = new int[n];
		Arrays.fill(jumps, 0);
		for (int i = 1; i < n; i++) {
			jumps[i] = Integer.MAX_VALUE;
			/**
			 * Of all the possible jumps that can reach cur index , take the one
			 * which takes least number of steps.
			 */
			for (int j = 0; j < i; j++) {
				if (i <= j + arr[j] && jumps[j] != Integer.MAX_VALUE) {
					jumps[i] = Math.min(jumps[i], 1 + jumps[j]);
				}
			}
		}
		return jumps[n - 1];
	}

	/**
	 * O(n) solution.
	 * 
	 * http://www.geeksforgeeks.org/minimum-number-jumps-reach-endset-2on-
	 * solution/
	 * 
	 * @param arr
	 * @return
	 */
	public int minJumps(int arr[]) {
		if (arr.length <= 1)
			return 0;

		// Return -1 if not possible to jump
		if (arr[0] == 0)
			return -1;

		// initialization
		int maxReach = arr[0];
		int step = arr[0];
		int jump = 1;

		/**
		 * Before consuming available steps greedily select the best possible jump.
		 */
		for (int i = 1; i < arr.length; i++) {
			// Check if we have reached the end of the array
			if (i == arr.length - 1)
				return jump;

			// update maxReach
			maxReach = Math.max(maxReach, i + arr[i]);
			// we use a step to get to the current index
			step--;
			// If no further steps left
			if (step == 0) {
				// we must have used a jump
				jump++;

				// current index is beyond maxReach so return -1.  
				if (i >= maxReach)
					return -1;

				/**
				 * re-initialize the steps to the amount of steps to reach
				 * maxReach from position i.
				 */
				step = maxReach - i;
			}
		}

		return -1;
	}
}
