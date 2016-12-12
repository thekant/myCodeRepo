/**
 * 
 */
package com.kant.algorithms.dynamicprogramming;

/**
 * @author shaskant
 *
 */
public class KnapSack_0_1 {

	/**
	 * 
	 * @param vals
	 * @param wts
	 * @param n
	 * @param capacity
	 * @return
	 */
	public int findMaxValusHoldable(int[] vals, int[] wts, int n, int capacity) {
		// N and wtCap are two params that change
		return solveR(vals, wts, n, capacity);
	}

	/**
	 * N W => N-1 W or N-1 capW-wts[n-1] 
	 */
	private int solveR(int[] vals, int[] wts, int n, int capW) {
		if (n == 0 || capW == 0) {
			return 0;
		}
		if (wts[n-1] > capW)
			return solveR(vals, wts,  n - 1, capW);
		return Math
				.max(vals[n-1]
						+ solveR(vals, wts,  n - 1, capW - wts[n-1]),
						solveR(vals, wts, n - 1, capW));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		KnapSack_0_1 knapsack = new KnapSack_0_1();
		int[] vals = { 60, 100, 120 };
		int[] wts = { 10, 20, 30 };
		int capacity = 50;
		int n = vals.length;
		System.out.println(knapsack
				.findMaxValusHoldable(vals, wts, n, capacity));
	}

}
