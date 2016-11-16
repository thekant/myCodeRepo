/**
 * 
 */
package com.kant.general;

/**
 * @author shaskant
 *
 */
public class CountDistintSubsequencesOFStringInOther {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String T = "ge";
		String S = "geeksforgeeks";

		System.out.println(solve(T, 0, S, 0));

	}

	/**
	 * WIP
	 * TODO not working
	 * @param T
	 * @param S
	 */
	private static int solve(String T, int t, String S, int s) {
		int sum = 1;
		if (s > S.length() || t > T.length())
			return 0;
		for (int i = t; i < T.length(); i++) {
			for (int j = s; j < S.length(); j++) {
				if (T.charAt(i) == S.charAt(j)) {
					sum = sum + solve(T, i + 1, S, j + 1);
				}
			}
		}
		return sum;
	}
}
