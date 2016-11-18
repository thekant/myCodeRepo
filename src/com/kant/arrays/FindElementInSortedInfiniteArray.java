/**
 * 
 */
package com.kant.arrays;

/**
 * http://www.geeksforgeeks.org/find-position-element-sorted-array-infinite-
 * numbers/
 * 
 * @author shaskant
 *
 */
public class FindElementInSortedInfiniteArray {

	/**
	 * s
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int arr[] = { 3, 5, 7, 9, 10, 90, 100, 130, 140, 160, 170 };
		int ans = findPositionInBigArray(arr, 10);
		System.out.println(ans);
	}

	/**
	 * 
	 * @param arr
	 * @param key
	 * @return
	 */
	public static int findPositionInBigArray(int[] arr, int key) {
		int l = 0, h = 1;
		int val = arr[l];
		while (val < key) {
			l = h;
			if (2 * h < arr.length)
				h *= 2;
			else
				h = arr.length - 1;
			val = arr[l];
		}

		return binarySearch(arr, l, h, key);
	}

	public static int binarySearch(int[] data, int low, int high, int key) {
		if (low <= high) {
			int mid = low + (high - low) / 2;
			if (data[mid] == key)
				return mid;
			else if (data[mid] > key)
				return binarySearch(data, low, mid - 1, key);
			else
				return binarySearch(data, mid + 1, high, key);
		}
		return -1;
	}

}
