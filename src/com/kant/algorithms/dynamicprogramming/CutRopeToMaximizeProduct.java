package com.kant.algorithms.dynamicprogramming;

/**
 * Given a rope of length n meters, cut the rope in different parts of integer
 * lengths in a way that maximizes product of lengths of all parts. You must
 * make at least one cut. <br/>
 * maxProd(n) = max( i * (n-i) , i * maxProdRec(n-i) ) for all i in {1, 2, 3 ..
 * n-1}
 * 
 * @author shashi
 * 
 */
public class CutRopeToMaximizeProduct {

	/**
	 * A Dynamic Programming solution for Max Product Problem
	 * 
	 * @param N
	 * @return
	 */
	public int maxProd(int N) {
		int[] val = new int[N + 1];
		val[0] = 0;
		val[1] = 0;

		/**
		 * Build the table val[] in bottom up manner and return the last entry
		 * from the table. Max among mx_val, (i-j)*j , j* val[i-j] is max_val
		 * and hence val[] for this iteration.
		 */
		for (int n = 1; n <= N; n++) {
			int max_val = 0;

			for (int i = 1; i <= n / 2; n++)
				max_val = maxOf(max_val, i * (n - i), i * val[n - i]);

			val[n] = max_val;
		}
		return val[N];
	}

	int maxOf(int a, int b, int c) {
		if (a > b && a > c)
			return a;
		if (b > a && b > c)
			return b;
		else
			return c;
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(new CutRopeToMaximizeProduct().maxProdRecursive(9));
	}

	/**
	 * The main function that returns maximum product obtainable from a rope of
	 * length n
	 * 
	 * @param n
	 * @return
	 */
	public int maxProdRecursive(int n) {
		// Base cases
		if (n == 0 || n == 1)
			return 0;

		// Make a cut at different places and take the maximum of all
		int max_val = 0;
		for (int i = 1; i <= n / 2; i++)
			max_val = Math.max(Math.max(max_val, i * (n - i)), i
					* maxProdRecursive(n - i));

		// Return the maximum of all values
		return max_val;
	}

}
