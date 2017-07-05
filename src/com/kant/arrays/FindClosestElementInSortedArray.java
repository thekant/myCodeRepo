/**
 * 
 */
package com.kant.arrays;

/**
 * http://www.geeksforgeeks.org/find-k-closest-elements-given-value/
 * 
 * @author shaskant
 *
 */
public class FindClosestElementInSortedArray {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int arr[] = { 12, 16, 22, 30, 35, 39, 42, 45, 48, 50, 53, 55, 56 };
		int x = 35;
		System.out.println(findClosest(arr, x, 0, arr.length - 1)); // 4
		printKclosest(arr, x, 4);// 39 30 42 45
	}

	/**
	 * return index of closest element in a sorted array
	 * 
	 * @param data
	 * @param x
	 * @param low
	 * @param high
	 * @return
	 */
	public static int findClosest(int[] data, int x, int low, int high) {
		if (data[high] <= x) {
			return high;
		}

		if (data[low] > x)
			return low;

		int mid = (high + low) / 2;
		// i.e data[mid] is as same as x
		if (data[mid] <= x && data[mid + 1] > x) {
			return mid;
		}
		if (data[mid] < x)
			return findClosest(data, x, mid + 1, high);

		return findClosest(data, x, low, mid - 1);
	}

	public static void printKclosest(int arr[], int x, int k) {
		int left = findClosest(arr, x, 0, arr.length - 1);
		int right = left + 1;
		if (arr[left] == x)
			left--;

		int count = k;
		while (left >= 0 && right < arr.length && count > 0) {
			if ((x - arr[left]) < (arr[right] - x)) {
				System.out.print(arr[left] + " ");
				left--;
			} else {
				System.out.print(arr[right] + " ");
				right++;
			}
			count--;
		}

		while (count > 0 && left >= 0) {
			System.out.print(arr[left--] + " ");
			count++;
		}

		while (count > 0 && right < arr.length) {
			System.out.print(arr[right++] + " ");
			count++;
		}

	}
}
