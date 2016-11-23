/**
 * 
 */
package com.kant.algorithms.dynamicprogramming;

import java.util.Arrays;

/**
 * http://www.geeksforgeeks.org/count-distinct-occurrences-as-a-subsequence/
 * 
 * Input : S = banana, T = ban Output : 3 T appears in S as below three
 * subsequences. [ban], [ba n], [b an]
 * 
 * Input : S = geeksforgeeks, T = ge Output : 6 T appears in S as below three
 * subsequences. [ge], [ ge], [g e], [g e] [g e] and [ g e]
 * 
 * @author shaskant
 *
 */
public class CountDistinctSubsequencesOFStringInOther {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String T = "ge";
		String S = "geeksforgeeks";

		// System.out.println(solveRecursive(T, T.length(), S, S.length()));
		System.out.println(solveIterative(S, T));

	}

	/**
	 * Working TODO add memorization
	 * 
	 * @param T
	 * @param S
	 */
	private static int solveRecursive(String T, int t, String S, int s) {
		if (t == 0)
			return 1;
		if (s == 0)
			return 0;

		if (S.charAt(s - 1) == T.charAt(t - 1)) {
			return solveRecursive(T, t, S, s - 1)
					+ solveRecursive(T, t - 1, S, s - 1);
		} else
			return solveRecursive(T, t, S, s - 1);

	}

	/**
	 * DP solution bottom up approach.
	 * 
	 * 
	 * // Returns count of subsequences of S that match T // m is length of T
	 * and n is length of S subsequenceCount(S, T, n, m)
	 * 
	 * // An empty string is subsequence of all. 1) If length of T is 0, return
	 * 1.
	 * 
	 * // Else no string can be a sequence of empty S. 2) Else if S is empty,
	 * return 0.
	 * 
	 * 3) Else if last characters of S and T don't match, remove last character
	 * of S and recur for remaining return subsequenceCount(S, T, n-1, m)
	 * 
	 * 4) Else (Last characters match), the result is sum of two counts.
	 * 
	 * // Remove last character of S and recur. a) subsequenceCount(S, T, n-1,
	 * m) +
	 * 
	 * // Remove last characters of S and T, and recur. b) subsequenceCount(S,
	 * T, n-1, m-1)
	 * 
	 * @param S
	 * @param T
	 * @return
	 */
	private static int solveIterative(String S, String T) {
		int[][] store = new int[S.length() + 1][T.length() + 1];
		for (int[] arr : store) {
			Arrays.fill(arr, -1);
		}

		for (int i = 0; i < S.length() + 1; i++) {
			for (int j = 0; j < T.length() + 1; j++) {
				if (j == 0)
					store[i][j] = 1;
				else if (i == 0)
					store[i][j] = 0;
				else if (S.charAt(i - 1) == S.charAt(j - 1)) {
					store[i][j] = store[i - 1][j] + store[i - 1][j - 1];
				} else
					store[i][j] = store[i - 1][j];
			}
		}
		return store[S.length()][T.length()];
	}
}
