/**
 * 
 */
package com.kant.algorithms.dynamicprogramming;

/**
 * http://www.geeksforgeeks.org/maximum-sum-such-that-no-two-elements-are-
 * adjacent/
 * 
 * @author shashi
 * 
 */
public class MaximumSubArraySumNumbersNotAdjacent {

	/**
	 * DP solution:
	 * 
	 * M(i) = max(arr[i] + M(i-2), M(i-1))
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int arr[] = { 5, 5, 10, 40, 50, 35 };

		int inclusive = arr[0];// M(i-2)
		int exclusive = arr[1];// M(i-1)
		int maxSum = 0; // M(i)
		for (int i = 2; i < arr.length; i++) {
			/**
			 *  M(i) = max(arr[i] + M(i-2), M(i-1))
			 */
			maxSum = Math.max(arr[i] + inclusive, exclusive);

			inclusive = exclusive;
			exclusive = maxSum;
		}

		System.out.println(exclusive);
	}
}
