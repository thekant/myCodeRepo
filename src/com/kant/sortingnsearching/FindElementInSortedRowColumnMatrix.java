/**
 * 
 */
package com.kant.sortingnsearching;

/**
 * @author shashi
 * 
 */
public class FindElementInSortedRowColumnMatrix {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[][] mat = { { 10, 20, 30, 40 }, { 15, 25, 35, 45 },
				{ 27, 29, 37, 48 }, { 32, 33, 39, 50 }, };

		MyUtil.print2DMatrix(mat, 4, 4);
		search(mat, 45);
	}

	/**
	 * 
	 * @param mat
	 * @param item
	 * @return
	 */
	public static boolean search(int mat[][], int item) {
		int n = mat.length;
		// set indexes for top right element
		// to make sure we two ways 1. that decreases 2. that increases.
		int i = 0, j = n - 1;
		while (i < n && j >= 0) {
			if (mat[i][j] == item) {
				System.out.printf("\n[%2d] Found at (%2d ,%2d )",item, i, j);
				return true;
			}
			if (mat[i][j] > item)
				j--;
			else
				// if mat[i][j] < x
				i++;
		}

		System.out.printf("\n Element not found");
		return false; // if ( i==n || j== -1 )
	}

}
