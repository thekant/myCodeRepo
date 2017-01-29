package com.kant.general;

/**
 * 
 * @author shashi
 * 
 */
public class TransposeAMatrix {
	/**
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		int matrix[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		int m = matrix.length;
		int n = matrix[0].length;
		int transpose[][] = new int[n][m];

		for (int c = 0; c < m; c++) {
			for (int d = 0; d < n; d++)
				transpose[d][c] = matrix[c][d];
		}

		System.out.println("Transpose of entered matrix:-");

		for (int c = 0; c < n; c++) {
			for (int d = 0; d < m; d++)
				System.out.print(transpose[c][d] + "\t");
			System.out.print("\n");
		}
	}
}