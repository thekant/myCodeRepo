/**
 * 
 */
package com.kant.algorithms.dynamicprogramming;

import java.util.Arrays;

/**
 * @author shaskant
 *
 */
public class CalculatingLCS {

	protected int[][] mstore=null;
	/**
	 * Non-Dynamic approach
	 * 
	 * @param strL
	 * @param l
	 * @param strM
	 * @param m
	 * @return
	 */
	public int getLCSr(String strL, int l, String strM, int m) {
		if (l == 0 || m == 0)
			return 0;
		if (strL.charAt(l - 1) == strM.charAt(m - 1))
			return 1 + getLCSr(strL, l - 1, strM, m - 1);
		return Math.max(getLCSr(strL, l - 1, strM, m),
				getLCSr(strL, l, strM, m - 1));
	}

	/**
	 *  Dynamic programming approach with tabulation {params l & m and hence 2d array} 
	 */
	public int getLCS(String strL, int l, String strM, int m) {
		prepareForTabulation(l, m);

		for (int x = 0; x <= l; x++)
			for (int y = 0; y <= m; y++) {
				if (x == 0 || y == 0) {
					mstore[x][y] = 0;
					continue;
				}

				if (strL.charAt(x - 1) == strM.charAt(y - 1))
					mstore[x][y] = 1 + mstore[x - 1][y - 1];
				else {
					mstore[x][y] = Math.max(mstore[x - 1][y], mstore[x][y - 1]);
				}
			}
		return mstore[l][m];
	}

	/**
	 * @param l
	 * @param m
	 * @return
	 */
	private int[][] prepareForTabulation(int l, int m) {
		mstore = new int[l + 1][m + 1];
		for (int i = 0; i < mstore.length; i++) {
			Arrays.fill(mstore[i], 0);
		}
		return mstore;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CalculatingLCS calculator = new CalculatingLCS();
		String x = "AGGTAB";
		String y = "GXTXAYB";
		System.out.println(calculator.getLCS(x, x.length(), y, y.length()));
	}

}
