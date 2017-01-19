/**
 * 
 */
package com.kant.algorithms.dynamicprogramming;

/**
 * 
 * The longest common suffix has following optimal substructure property
 * LCSuff(X, Y, m, n) = LCSuff(X, Y, m-1, n-1) + 1 if X[m-1] = Y[n-1] 0
 * Otherwise (if X[m-1] != Y[n-1])
 * 
 * The maximum length Longest Common Suffix is the longest common substring.
 * LCSubStr(X, Y, m, n) = Max(LCSuff(X, Y, i, j)) where 1 <= i <= m and 1 <= j
 * <= n
 * 
 * http://www.geeksforgeeks.org/longest-common-substring/
 * 
 * https://youtu.be/BysNXJHzCEs   [explanation]
 * 
 * @author shashi
 * 
 */
public class LongestCommonSubString {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String X = "OldSite:GeeksforGeeks.org";
		String Y = "NewSite:GeeksQuiz.com";
		LongestCommonSubString lowestCommonSubString = new LongestCommonSubString();
		System.out.println(lowestCommonSubString.lcsSbStr(X.toCharArray(),
				Y.toCharArray(), X.length(), Y.length()));

	}

	/**
	 * Returns length of longest common substring of X[0..m-1] and Y[0..n-1]
	 * 
	 */
	public int lcsSbStr(char[] X, char[] Y, int m, int n) {

		/**
		 * Create a table to store lengths of longest common suffixes of
		 * substrings. <br/>
		 * Note: that LCSuff[i][j] contains length of longest common suffix of
		 * X[0..i-1] and Y[0..j-1]. The first row and first column entries have
		 * no logical meaning, they are used only for simplicity of program
		 */
		int LCSuff[][] = new int[m + 1][n + 1];
		int result = 0;

		for (int i = 0; i <= m; i++) {
			for (int j = 0; j <= n; j++) {
				if (i == 0 || j == 0)
					LCSuff[i][j] = 0;

				else if (X[i - 1] == Y[j - 1]) {
					LCSuff[i][j] = LCSuff[i - 1][j - 1] + 1;
					result = Math.max(result, LCSuff[i][j]); // store the max of
																// all
				} else
					LCSuff[i][j] = 0;
			}
		}
		return result;
	}

}
