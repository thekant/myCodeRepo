/**
 * 
 */
package com.kant.sortingnsearching;

/**
 * @author shaskant
 *
 */
public class BubbleSortingStrategy {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int arr[] = { 64, 34, 25, 12, 22, 11, 90 };
		bubbleSort(arr);
		MyUtil.printArrayInt(arr);
	}

	/**
	 * iterative impl
	 * 
	 * @param arr
	 */
	static public void bubbleSort(int[] arr) {
		int n = arr.length;
		for (int i = 0; i < n - 1; i++) {
			// last element already sorted out
			for (int j = 0; j < n - i - 1; j++) {
				if (arr[j] > arr[j + 1])
					MyUtil.swap(arr, j, j + 1);
			}
		}
	}

	/**
	 * Recursive impl
	 * 
	 * @param arr
	 * @param n
	 */
	static public void bubbleSort(int arr[], int n) {
		if (n == 1)
			return;

		/**
		 * One pass of bubble sort. After this pass, the largest element is
		 * moved (or bubbled) to end.
		 */
		for (int i = 0; i < n - 1; i++)
			if (arr[i] > arr[i + 1])
				MyUtil.swap(arr, i, i + 1);

		// Largest element is fixed,
		// recur for remaining array
		bubbleSort(arr, n - 1);
	}
}
