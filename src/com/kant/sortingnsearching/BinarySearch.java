/**
 * 
 */
package com.kant.sortingnsearching;

import java.lang.invoke.MethodHandles;

/**
 * It falls in case II of Master Method and solution of the recurrence is
 * \Theta(Logn). Auxiliary Space: O(1) in case of iterative implementation. In
 * case of recursive implementation, O(Logn) recursion call stack space.
 * Algorithmic Paradigm: Divide and Conquer
 * 
 * @author shaskant
 *
 */
public class BinarySearch {

	/**
	 * 
	 * @param from
	 *            input Array
	 * @param value
	 *            item to search
	 * @param low
	 *            start of portion of array [0]
	 * @param high
	 *            end of portion of array [arr.length-1]
	 * @return
	 */
	public int find(int[] from, int value, int low, int high) {
		int mid = (high - low) / 2 + low;// because low+high can be a value
		// greater than MAX value of Int

		if (low <= high) {
			if (from[mid] == value) {
				return mid;
			} else if (from[mid] < value) {
				return find(from, value, mid + 1, high);
			}
			return find(from, value, low, mid - 1);
		}

		return -1;
	}

	/**
	 * 
	 * @param from
	 * @param value
	 * @param low
	 * @param high
	 * @return
	 */
	public int findIterative(int[] from, int value, int low, int high) {
		int mid = 0;
		int l = low;
		int h = high;
		// optimize by one extra comparison [l<=h]
		while (h - l > 0) {
			mid = (h - l) / 2 + l;
			if (from[mid] == value) {
				return mid;
			} else if (from[mid] < value) {
				l = mid + 1;
			} else
				h = mid - 1;
		}
		// one last element left to check for case when l==h
		if (from[l] == value) {
			return l;
		}
		return -1;
	}

	/**
	 * Problem Statement: Given an array of N distinct integers, find floor
	 * value of input ‘key’. Say, A = {-1, 2, 3, 5, 6, 8, 9, 10} and key = 7, we
	 * should return 6 as outcome.
	 */
	public int findFloor(int[] from, int value, int low, int high) {
		int l = low;
		int h = high;
		int mid = -1;
		// if first entry in array is greater than value return -1.
		if (from[low] > value) {
			return -1;
		}

		while (l < h) {
			mid = (h - l) / 2 + l;
			if (from[mid] == value) {
				return mid;
			} else if (from[mid] < value) {
				l = mid + 1;
			} else
				h = mid - 1;
		}
		/**
		 * We can use the above optimized implementation to find floor value of
		 * key. We keep moving the left pointer to right most as long as the
		 * invariant holds. Eventually left pointer points an element less than
		 * or equal to key (by definition floor value). The following are
		 * possible corner cases,
		 * 
		 * —> If all elements in the array are smaller than key, left pointer
		 * moves till last element.
		 * 
		 * —> If all elements in the array are greater than key, it is an error
		 * condition.
		 * 
		 * —> If all elements in the array equal and <= key, it is worst case
		 * input to our implementation.
		 */
		return l;
	}

	/**
	 * Given a sorted array with possible duplicate elements. Find number of
	 * occurrences of input ‘key’ in log N time.<br/>
	 * 
	 * <b>NOTE: As array is sorted all duplicate values of a element will be
	 * contiguous.</b> The idea here is finding left and right most occurrences
	 * of key in the array using binary search. We can modify floor function to
	 * trace right most occurrence and left most occurrence
	 */
	public TupleOutput findRange(int[] from, int value, int low, int high) {
		int x2 = findRightOccurance(from, value, low, high);
		int x1 = findLeftOccurance(from, value, low, high);
		return new TupleOutput(x1, x2);
	}

	private int findLeftOccurance(int[] from, int value, int low, int high) {
		int l = low;
		int h = high;
		int mid = -1;
		while (h - l > 1) { // or h-l >1
			mid = l + (h - l) / 2;

			if (from[mid] >= value) {
				h = mid;
			} else {
				l = mid;
			}
		}
		return h;
	}

	private int findRightOccurance(int[] from, int value, int low, int high) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		System.out.println( new Object() { }.getClass().getEnclosingClass().getCanonicalName());
		System.out.println(MethodHandles.lookup().lookupClass().getCanonicalName());
		int l = low;
		int h = high;
		int mid = -1;
		while (h - l > 1) {
			mid = l + (h - l) / 2;

			if (from[mid] <= value) {
				l = mid;
			} else {
				h = mid;
			}
		}
		return l;
	}

	static class TupleOutput {
		public int x1;
		public int x2;

		/**
		 * @param x1
		 * @param x2
		 */
		public TupleOutput(int x1, int x2) {
			this.x1 = x1;
			this.x2 = x2;
		}

		@Override
		public String toString() {
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			return x1 + ":" + x2;
		}

	}

	/**
	 * Find the odd appearing element in O(Log n) time. Given an array where all
	 * elements appear even number of times except one. All repeating
	 * occurrences of elements appear in pairs and these pairs are not adjacent
	 * (there cannot be more than two consecutive occurrences of any element).
	 * Find the element that appears odd number of times.<br/>
	 * 
	 * {2, 2, 1, 2, 2, 1, 1} [valid]<br/>
	 * {2, 1, 2} [invalid] as repeating elements don’t appear in pairs<br/>
	 * {1, 2, 2, 2, 2} [invalid] as two pairs of 2 are adjacent.<br/>
	 * {2, 2, 2, 1} three consecutive occurance of 2.<br/>
	 * 
	 * <b>O(nlogn)</b> sort and find<br/>
	 * <b>O(n)</b> XORing all elements http://www.geeksforgeeks.org
	 * /find-the-number-occurring-odd-number-of-times/<br/>
	 * 
	 * 
	 * <b>Solution:</b><br/>
	 * 1) Find the middle index, say ‘mid’.<br/>
	 * 
	 * 2) If ‘mid’ is even, then compare arr[mid] and arr[mid + 1]. If both are
	 * same, then there is an odd occurrence of the element after ‘mid’ else
	 * before mid.<br/>
	 * 
	 * 3) If ‘mid’ is odd, then compare arr[mid] and arr[mid – 1]. If both are
	 * same, then there is an odd occurrence after ‘mid’ else before mid.<br/>
	 * 
	 * or<br/>
	 * 
	 * <br/>
	 * Also Solves
	 * Find the element that appears once in a sorted array Given a sorted array
	 * in which all elements appear twice (one after one) and one element
	 * appears only once. Find that element in O(log n) complexity.
	 * 
	 */
	public int findOddOrderElement(int[] from, int low, int high) {
		int mid = -1;
		int l = low;
		int h = high;

		while (l < h) {
			mid = l + (h - l) / 2;
			if (mid % 2 == 0) {// if even position
				if (from[mid] == from[mid + 1]) {
					l = mid + 2; // search on left side if pairs are at even
									// location.
				} else
					h = mid;
			} else { // else if odd position
				if (from[mid] == from[mid + 1]) {
					h = mid - 1; // search on right side if pairs are at odd
									// location.
				} else
					l = mid + 1;
			}
		}
		// return the only element left
		return l;
	}

	/**
	 * Find the element that appears once in a sorted array. Given a sorted
	 * array in which all elements appear twice (one after one) and one element
	 * appears only once. Find that element in O(log n) complexity.
	 * 
	 * Example:
	 * 
	 * Input: arr[] = {1, 1, 3, 3, 4, 5, 5, 7, 7, 8, 8} Output: 4
	 * 
	 * Input: arr[] = {1, 1, 3, 3, 4, 4, 5, 5, 7, 7, 8} Output: 8
	 */

	/**
	 * Test for odd and even values Test for boundary cases like middle, first
	 * and last element
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		BinarySearch ob = new BinarySearch();
		int toFind = 43;
		int arr[] = { 2, 3, 4, 4, 4, 4, 10, 40, 42 };
		int result = ob.findFloor(arr, toFind, 0, arr.length - 1);
		if (result == -1) {
			System.out.println("\nitem Not found : " + toFind);
		}
		System.out.println("\noutput: item " + toFind + " found at index: "
				+ arr[result]);

		TupleOutput range = ob.findRange(arr, 4, 0, arr.length - 1);
		System.out.println(range);

		int[] arr1 = { 2, 2, 1, 2, 2, 1, 1 };
		System.out.println(ob.findOddOrderElement(arr1, 0, arr1.length - 1));
	}

	/**
	 * 
	 * TODO read and recode DIVIDE and CONQUER policy
	 * 
	 * @param A
	 * @param l
	 * @param r
	 * @return
	 */
	int BinarySearchIndexOfMinimumRotatedArray(int A[], int l, int r) {
		// extreme condition, size zero or size two
		int m;

		// Precondition: A[l] > A[r]
		if (A[l] <= A[r])
			return l;

		while (l <= r) {
			// Termination condition (l will eventually falls on r, and r always
			// point minimum possible value)
			if (l == r)
				return l;

			m = l + (r - l) / 2; // 'm' can fall in first pulse,
									// second pulse or exactly in the middle

			if (A[m] < A[r])
				// min can't be in the range
				// (m < i <= r), we can exclude A[m+1 ... r]
				r = m;
			else
				// min must be in the range (m < i <= r),
				// we must search in A[m+1 ... r]
				l = m + 1;
		}

		return -1;
	}

	int BinarySearchIndexOfMinimumRotatedArray(int A[], int size) {
		return BinarySearchIndexOfMinimumRotatedArray(A, 0, size - 1);
	}

}
