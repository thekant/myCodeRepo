/**
 * 
 */
package com.kant.algorithms.dynamicprogramming;

import java.util.Arrays;

/**
 * 
 * http://www.geeksforgeeks.org/dynamic-programming-set-5-edit-distance/
 * 
 * @author shashi
 * 
 */
public class EdiDistanceProblem {

	/**
	 * recursive implementation.can take upto O(3^m) in worst case
	 * 
	 * @param X
	 * @param Y
	 * @param m
	 * @param n
	 * @return
	 */
	public int solveRec(char[] X, char[] Y, int m, int n) {
		// base case
		if (m == 0)
			return n;
		if (n == 0)
			return m;

		// If last characters of two strings are same, nothing
		// much to do. Ignore last characters and get count for
		// remaining strings.
		if ((X[m - 1] == Y[n - 1]))
			return solveRec(X, Y, m - 1, n - 1);

		// If last characters are not same, consider all three
		// operations on last character of first string, recursively
		// compute minimum cost for all three operations and take
		// minimum of three values.
		return 1 + min(solveRec(X, Y, m, n - 1),// insert (add ch2)
				solveRec(X, Y, m - 1, n),// remove (del ch1)
				solveRec(X, Y, m - 1, n - 1));// replace (make ch1=ch2)
	}

	/**
	 * dp solution
	 * 
	 * @param X
	 * @param Y
	 * @return
	 */
	public int solveByDp(char[] X, char[] Y) {
		int m = X.length;
		int n = Y.length;
		int[][] result = new int[m + 1][];
		for (int i = 0; i <= m; i++) {
			result[i] = new int[n + 1];
			Arrays.fill(result[i], 0);
		}

		for (int i = 0; i <= m; i++) {
			for (int j = 0; j <= n; j++) {
				if (i == 0)
					result[i][j] = j;
				else if (j == 0)
					result[i][j] = i;

				else if (X[i - 1] == Y[j - 1]) {
					result[i][j] = result[i - 1][j - 1];
				} else {
					result[i][j] = 1 + min(result[i][j - 1], result[i - 1][j],
							result[i - 1][j - 1]);
				}
			}
		}
		return result[m][n];
	}

	private int min(int a, int b, int c) {
		if (a < b & a < c)
			return a;
		if (b < a & b < c)
			return b;
		return c;
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		EdiDistanceProblem prob = new EdiDistanceProblem();
		String text1 = "itssunday or friday you may better know";
		String text2 = "itssaturday or friday you may better know";
		System.out.println(prob.solveRec(text1.toCharArray(),
				text2.toCharArray(), text1.length(), text2.length()));

		System.out.println(prob.solveByDp(text1.toCharArray(),
				text2.toCharArray()));
	}
}
