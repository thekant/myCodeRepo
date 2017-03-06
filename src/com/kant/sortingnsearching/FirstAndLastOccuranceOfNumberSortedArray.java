/**
 * 
 */
package com.kant.sortingnsearching;

/**
 * http://www.geeksforgeeks.org/find-first-last-occurrences-element-sorted-array
 * /
 * 
 * @author shaskant
 *
 */
public class FirstAndLastOccuranceOfNumberSortedArray {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int arr[] = { 1, 2, 2, 2, 2, 3, 4, 7, 8, 8 };
		int x = 8;
		System.out.println(getfirstOccurance(arr, 0, arr.length - 1, x,
				arr.length));
		System.out.println(getLastOccurance(arr, 0, arr.length - 1, x,
				arr.length));
	}

	/**
	 * if x is present in arr[] then returns the index of FIRST occurrence of x
	 * in arr[0..n-1], otherwise returns -1
	 */
	public static int getfirstOccurance(int arr[], int low, int high, int x,
			int n) {
		if (low <= high) {
			int mid = low + (high - low) / 2;
			if ((mid == 0 || arr[mid - 1] < x) && arr[mid] == x)
				return mid;
			else if (x > arr[mid])
				return getfirstOccurance(arr, (mid + 1), high, x, n);
			else
				return getfirstOccurance(arr, low, (mid - 1), x, n);
		}
		return -1;
	}

	/**
	 * if x is present in arr[] then returns the index of LAST occurrence of x
	 * in arr[0..n-1], otherwise returns -1
	 */
	public static int getLastOccurance(int arr[], int low, int high, int x,
			int n) {
		if (low <= high) {
			int mid = low + (high - low) / 2;
			if ((mid == n - 1 || x < arr[mid + 1]) && arr[mid] == x)
				return mid;
			else if (x < arr[mid])
				return getLastOccurance(arr, low, (mid - 1), x, n);
			else
				return getLastOccurance(arr, (mid + 1), high, x, n);
		}
		return -1;
	}

}
