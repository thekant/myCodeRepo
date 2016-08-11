/**
 * 
 */
package com.kant.algorithms.dynamicprogramming;

/**
 * Following is detailed algorithm to print the LCS. It uses the same 2D table L[][].

1) Construct L[m+1][n+1] using the steps discussed in previous post.

2) The value L[m][n] contains length of LCS. Create a character array lcs[] of length equal to the length of lcs plus 1 (one extra to store \0).

2) Traverse the 2D array starting from L[m][n]. Do following for every cell L[i][j]
…..a) If characters (in X and Y) corresponding to L[i][j] are same (Or X[i-1] == Y[j-1]), then include this character as part of LCS.
…..b) Else compare values of L[i-1][j] and L[i][j-1] and go in direction of greater value.

The following table (taken from Wiki) shows steps (highlighted) followed by the above algorithm.

0	1	2	3	4	5	6	7
Ø	M	Z	J	A	W	X	U
0	Ø	0	0	0	0	0	0	0	0
1	X	0	0	0	0	0	0	1	1
2	M	0	1	1	1	1	1	1	1
3	J	0	1	1	2	2	2	2	2
4	Y	0	1	1	2	2	2	2	2
5	A	0	1	1	2	3	3	3	3
6	U	0	1	1	2	3	3	3	4
7	Z	0	1	2	2	3	3	3	4

 * @author shaskant
 */
public class PrintingLCS extends CalculatingLCS {

	/**
	 * Prints longest sequence found.
	 * 
	 * @return
	 */
	public String printLcs(String strL, String strR) {
		StringBuffer result = new StringBuffer();
		for (int i = strL.length(), j = strR.length(); i > 0 && j > 0;) {
			if (strL.charAt(i - 1) == strR.charAt(j - 1)) {
				result.append(strL.charAt(i - 1));
				i--;
				j--;
			} else if (mstore[i - 1][j] > mstore[i][j - 1]) {
				i--;
			} else
				j--;
		}
		return result.reverse().toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PrintingLCS printSol = new PrintingLCS();
		String x = "AGGTAB";
		String y = "GXTXAYB";
		System.out.println(printSol.getLCS(x, x.length(), y, y.length()));
		System.out.println(printSol.printLcs(x, y));
	}

}
