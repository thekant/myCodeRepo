/**
 * 
 */
package com.kant.general.tofactorIn;

/**
 * @author shashi
 * 
 */
public class RotatedArrayPivot {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int arr1[] = { 6, 5 };
		System.out.println(findPivot(arr1));
		int arr2[] = { 0, 0, 0, 0, 0, 1 };
		System.out.println(findFirstOne(arr2));
	}

	public static int findPivot(int[] data) {
		int start = 0;
		int end = data.length - 1;
		while (start <= end) {
			int mid = start + (end - start) / 2;
			if (data[mid] > data[mid + 1]) {
				return mid;
			} else if (data[mid - 1] > data[mid]) {
				return mid - 1;
			}
			if (data[start] > data[mid]) {
				end = mid - 1;
			} else
				start = mid + 1;
		}
		return -1;
	}

	public static int findFirstOne(int[] data) {
		int start = 0;
		int end = data.length - 1;
		if (data.length == 1) {
			if (data[0] == 1)
				return 1;
			else
				return -1;
		}
		while (start <= end) {
			int mid = start + (end - start) / 2;

			if (mid < end && data[mid] == 0 && data[mid + 1] == 1) {
				return mid + 1;
			} else if (mid > start && data[mid] == 1 && data[mid - 1] == 0) {
				return mid;
			}
			if (data[mid] == 0)
				start = mid + 1;
			else
				end = mid - 1;
		}
		return -1;
	}
}
