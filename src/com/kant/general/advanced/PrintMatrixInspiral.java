/**
 * 
 */
package com.kant.general.advanced;

/**
 * For anti spiral .. store spiral print in Stack.
 * 
 * http://www.geeksforgeeks.org/print-matrix-antispiral-form/
 * 
 * http://www.geeksforgeeks.org/print-a-given-matrix-in-spiral-form/
 * 
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
	 * works for any case
	 * 
	 *  (rowG,ColG)+-------------------+
	 *  		   |                   |
	 *             |                   |
	 *             |                   |
	 *             +-------------------+ (m-1,n-1)
	 *  
	 *  row++ -> n-- -> m-- -> col++
	 *  
	 * @param m
	 * @param n
	 * @param a
	 */
	public static void spiralPrint(int m, int n, int[][] a) {
		int rowG = 0, colG = 0;
		while (rowG < m && colG < n) {
			/**
			 * print first row
			 */
			for (int i = colG; i < n; i++) {
				System.out.print(a[rowG][i] + " ");
			}
			rowG++; // reduce box size from top.

			/**
			 * last column of box is printed
			 */
			for (int i = rowG; i < m; i++) {
				System.out.print(a[i][n - 1] + " ");
			}
			n--; // reduce box size from right

			/**
			 * if there still a row left to print
			 */
			if (rowG < m) {
				for (int i = n - 1; i >= colG; i--) {
					System.out.print(a[m - 1][i] + " ");
				}
				m--; // reduce box size from bottom.
			}

			/**
			 *  if there still a column left to print
			 */
			if (colG < n) {
				for (int i = m - 1; i >= rowG; i--) {
					System.out.print(a[i][colG] + " ");
				}
				colG++; // reduce box size from left
			}
		}

	}
}