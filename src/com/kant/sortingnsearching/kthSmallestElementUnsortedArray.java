package com.kant.sortingnsearching;

/**
 * 
 * http://www.geeksforgeeks.org/kth-smallestlargest-element-unsorted-array/
 * <p>
 * METHOD3 QuickSort, we pick a pivot element, then move the pivot element to
 * its correct position and partition the array around it. The idea is, not to
 * do complete quicksort, but stop at the point where pivot itself is kth
 * smallest element. Also, not to recur for both left and right sides of pivot,
 * but recur for one of them according to the position of pivot. The worst case
 * time complexity of this method is O(n2), but it works in O(n) on average.
 * </p>
 * 
 * 
 * @author shashi
 * 
 */
public class kthSmallestElementUnsortedArray {
	/**
	 * 
	 * @param arr
	 * @param l
	 *            array low range
	 * @param r
	 *            array high included ranges
	 * @param k
	 *            rank
	 * @return
	 */
	public int kthSmallest(int arr[], int l, int r, int k) {
		if (k > 0 && k <= r - l + 1) {
			int pos = partition(arr, l, r);
			//pos - l + 1 is rank of pivot element
			if (pos - l + 1 == k)
				return arr[pos];
			if (pos - l + 1 > k)
				return kthSmallest(arr, l, pos - 1, k);
			return kthSmallest(arr, pos + 1, r, k - pos + l - 1);
		}

		// If k is more than number of elements in array
		return Integer.MAX_VALUE;
	}

	/**
	 * 
	 * @param arr
	 * @param l
	 * @param r
	 * @return
	 */
	private int partition(int arr[], int l, int r) {
		int i = l;
		int pivot = arr[r];
		for (int j = l; j < r; j++) {
			if (arr[j] <= pivot) {
				swapp(arr, j, i);
				i++;
			}
		}
		// swap the pivot value to it's location and return location
		swapp(arr, r, i);
		return i;
	}

	private void swapp(int[] arr, int r, int i) {
		int temp = arr[i];
		arr[i] = arr[r];
		arr[r] = temp;
	}
}
