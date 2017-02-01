/**
 * 
 */
package com.kant.general.advanced;

/**
 * @author shashi
 * 
 */
public class PrintMatrixInspiral {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int a[][] = { { 1, 2, 3, 4, 5, 6 }, { 7, 8, 9, 10, 11, 12 },
				{ 13, 14, 15, 16, 17, 18 } };
		printMatrix(a, 3, 6);
		// printMatrixInSpiral(a, 4, 4);
		System.out.println();
		spiralPrint(3, 6, a);
	}

	/**
	 * 
	 * @param mat
	 * @param m
	 * @param n
	 */
	public static void printMatrix(int[][] mat, int m, int n) {
		System.out.println("-----------");
		for (int i = 0; i < m; i++) {

			for (int j = 0; j < n; j++) {
				System.out.printf("%2d ", mat[i][j]);
			}
			System.out.println();
		}
		System.out.println("------------");
	}

	/**
	 * Works for m==n case
	 * 
	 * @param mat
	 * @param m
	 *            number of rows
	 * @param n
	 *            number of columns
	 */
	public static void printMatrixInSpiral(int[][] mat, int m, int n) {
		for (int layer = 0; layer < m / 2 + 1; layer++) {
			int first = layer;

			// top left to top right
			for (int i = first; i <= n - 1 - layer; i++) {
				System.out.print(mat[first][i] + " ");
			}

			// top right to bottom right
			for (int i = first + 1; i <= m - 1 - layer; i++) {
				System.out.print(mat[i][n - 1 - layer] + " ");
			}

			// bottom right to bottom left
			for (int i = n - 1 - layer - 1; i >= first; i--) {
				System.out.print(mat[m - 1 - layer][i] + " ");
			}

			// bottom left to top left
			for (int i = m - 1 - layer - 1; i > first; i--) {
				System.out.print(mat[i][first] + " ");
			}

		}
	}

	/**
	 * works for any case
	 * 
	 * @param m
	 * @param n
	 * @param a
	 */
	public static void spiralPrint(int m, int n, int[][] a) {
		int rowG = 0, colG = 0;
		while (rowG < m && colG < n) {
			// first row is printed
			for (int i = colG; i < n; i++) {
				System.out.print(a[rowG][i] + " ");
			}
			rowG++; // won't have to print this row again

			// last column of box is printed
			for (int i = rowG; i < m; i++) {
				System.out.print(a[i][n - 1] + " ");
			}
			n--;// won't have to print this column again

			// if there still a row left to print
			if (rowG < m) {
				for (int i = n - 1; i >= colG; i--) {
					System.out.print(a[m - 1][i] + " ");
				}
				m--;// last row of box is printed
			}

			// if there still a column left to print
			if (colG < n) {
				for (int i = m - 1; i >= rowG; i--) {
					System.out.print(a[i][colG] + " ");
				}
				colG++;// first column of box is printed
			}

		}

	}
}