package com.kant.sortingnsearching;

import com.kant.sortingnsearching.MaxHeap;
import com.kant.sortingnsearching.MyUtil;

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
 * or use a {@link MaxHeap} or MinHeap to get O(klogN)
 * 
 * @author shashi
 * 
 */
public class kthSmallestElementUnsortedArray {
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		kthSmallestElementUnsortedArray prob =new kthSmallestElementUnsortedArray();
		int arr[] = {12, 3, 5, 7, 4, 19, 26};
		MyUtil.printArrayInt(arr);
		System.out.println(prob.solve(arr, 0, arr.length-1, 3));
	}
	/**
	 * @return kth smallest element from array
	 */
	public int solve(int arr[], int l, int r, int k) {
		if (k > 0 && k <= r - l + 1) {
			int pos = partition(arr, l, r);
			// pos - l + 1 is rank of pivot element
			if (pos - l + 1 == k)
				return arr[pos];
			if (pos - l + 1 > k)
				return solve(arr, l, pos - 1, k);
			return solve(arr, pos + 1, r, k - pos + l - 1);
		}
		// If k is more than number of elements in array
		return Integer.MAX_VALUE;
	}

	/**
	 * 1. keep ignoring all elements > pivot , until a smaller element comes..
	 * 2. swap smaller with 1st ignored elem. <br/>
	 * 3. keep foing step and step 2 until step 1 is possible or array end
	 * reached.
	 * 
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
