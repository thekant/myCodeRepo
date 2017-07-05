/**
 * 
 */
package com.kant.arrays;

/**
 * http://www.geeksforgeeks.org/given-two-sorted-arrays-number-x-find-pair-
 * whose-sum-closest-x/
 * 
 * 
 * @author shaskant
 *
 */
public class TwoSortedArrayFindClosestPairToX {

	/**
	 * 
	 * @param ar1
	 * @param ar2
	 * @param m
	 * @param n
	 * @param x
	 */
	public static void printClosest(int ar1[], int ar2[], int m, int n, int x) {
		int diff = Integer.MAX_VALUE;
		int res_l = 0, res_r = 0;

		// Start from left side of ar1[] and right side of ar2[]
		int l = 0, r = n - 1;
		while (l < m && r >= 0) {
			/**
			 * If this pair is closer to x , then update
			 */
			if (Math.abs(ar1[l] + ar2[r] - x) < diff) {
				res_l = l;
				res_r = r;
				diff = Math.abs(ar1[l] + ar2[r] - x);
			}

			/**
			 * If sum of this pair is more than x, move to smaller side
			 */
			if (ar1[l] + ar2[r] > x)
				r--;
			else
				// else move to the greater side
				l++;
		}

		System.out.print("The closest pair is [" + ar1[res_l] + ", "
				+ ar2[res_r] + "]");
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		int ar1[] = { 1, 4, 5, 7 };
		int ar2[] = { 10, 20, 30, 40 };
		int m = ar1.length;
		int n = ar2.length;
		int x = 38;
		printClosest(ar1, ar2, m, n, x);
	}

}
