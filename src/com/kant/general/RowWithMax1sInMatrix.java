/**
 * 
 */
package com.kant.general;

/**
 * http://www.geeksforgeeks.org/find-the-row-with-maximum-number-1s/
 * 
 * @author shaskant
 *
 */
public class RowWithMax1sInMatrix {

	static int rowWithMax1s(int mat[][]) {
		int i, index;
		int R = mat.length;
		int C = mat[0].length;

		// Initialize max using values from first row.
		int max_row_index = 0;
		int max = searchForFirstOne(mat[0], 0, C - 1);

		/**
		 * Traverse for each row and count number of 1s by finding the index of
		 * first 1
		 */
		for (i = 1; i < R; i++) {
			/**
			 * Count 1s in this row only if this row has more 1s than max so far
			 */
			if (max != -1 && mat[i][C - max - 1] == 1) {
				// search only till known max index of 1.
				index = searchForFirstOne(mat[i], 0, C - max);
				if (index != -1 && C - index > max) {
					max = C - index;
					max_row_index = i;
				}
			} else {
				max = searchForFirstOne(mat[i], 0, C - 1);
			}
		}
		return max_row_index;
	}

	private static int searchForFirstOne(int[] arr, int low, int high) {
		if (low <= high) {
			int mid = low + (high - low) / 2;
			if ((mid == 0 || arr[mid - 1] == 0) && arr[mid] == 1) {
				return mid;
			} else if (arr[mid] == 0)
				return searchForFirstOne(arr, mid + 1, high);
			else
				return searchForFirstOne(arr, low, mid - 1);
		}

		return -1;
	}

	/**
	 * O(m+n)
	 * 
	 * @param mat
	 * @return
	 */
	static int rowWithMax1sOptimal(int mat[][]) {
		// Initialize first row as row with max 1s
		int max_row_index = 0;
		int R = mat.length;
		int C = mat[0].length;
		
		// The function first() returns index of first 1 in row 0.
		// Use this index to initialize the index of leftmost 1 seen so far
		int j = searchForFirstOne(mat[0], 0, C - 1);
		if (j == -1) // if 1 is not present in first row
			j = C - 1;

		for (int i = 1; i < R; i++) {
			// Move left until a 0 is found
			while (j >= 0 && mat[i][j] == 1) {
				j = j - 1; // Update the index of leftmost 1 seen so far
				max_row_index = i; // Update max_row_index
			}
		}
		return max_row_index;
	}
}
