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

	public static void main(String[] args) {
		int arr[] = { 1, 3, 6, 3, 2, 3, 6, 8, 9, 5 };
		System.out.printf("Minimum number of jumps to reach end is %d ",
				minJumpsIterative(arr));
	}

	/**
	 * NAIVE recursive approach
	 * 
	 * @param arr
	 * @param l
	 * @param h
	 * @return
	 */
	private static int minJumps(int[] arr, int l, int h) {
		if (l == h) {
			return 0;
		}
		if (arr[l] == 0)
			return Integer.MAX_VALUE;

		int minVal = Integer.MAX_VALUE;
		for (int i = l + 1; i <= h && i <= l + arr[l]; i++) {
			int jumps = minJumps(arr, i, h);

			if (jumps != Integer.MAX_VALUE && minVal > jumps + 1) {
				minVal = jumps + 1;
			}
		}
		return minVal;
	}

	/**
	 * Dynamic programming approach
	 * 
	 * @param arr
	 * @return
	 */
	private static int minJumpsIterative(int[] arr) {
		int n = arr.length;
		if (n == 0 || arr[0] == 0)
			return Integer.MAX_VALUE;
		int[] jumps = new int[n];
		Arrays.fill(jumps, 0);

		for (int i = 1; i < n; i++) {
			jumps[i] = Integer.MAX_VALUE;
			// of all the possible jumps that can reach this point/index , take
			// the one which takes least number of steps
			for (int j = 0; j < i; j++) {
				if (i <= j + arr[j] && jumps[j] != Integer.MAX_VALUE) {
					jumps[i] = Math.min(jumps[i], 1 + jumps[j]);
				}
			}
		}
		return jumps[n - 1];
	}
}
