/**
 * 
 */
package com.kant.general.advanced;

import com.kant.sortingnsearching.MyUtil;

/**
 * Two elements missing
 * 
 * @author shaskant
 *
 */
public class RepeatingElementsInArray {

	/**
	 * http://www.geeksforgeeks.org/find-the-two-repeating-elements-in-a-given-
	 * array/
	 * 
	 * http://www.geeksforgeeks.org/find-duplicates-in-on-time-and-constant-
	 * extra-space/
	 * 
	 * CONDITION: Given an array of n elements which contains elements from 0 to
	 * n-1, with any of these numbers appearing any number of times. Find these
	 * repeating numbers in O(n) and using only constant memory space.
	 * 
	 * @param arr
	 * @param size
	 */
	public void printRepeating(int arr[], int size) {
		System.out.println("The repeating elements are : ");

		for (int i = 0; i < size; i++) {
			if (arr[abs(arr[i])] > 0)// if positive first occurrence
				arr[abs(arr[i])] = -arr[abs(arr[i])];
			else
				// if negative then repetition
				System.out.print(abs(arr[i]) + " ");
		}
	}

	/**
	 * http://www.geeksforgeeks.org/duplicates-array-using-o1-extra-space-set-2/
	 * 
	 * 1- Traverse the given array from i= 0 to n-1 elements Go to index
	 * arr[i]%n and increment its value by n. 3- Now traverse the array again
	 * and print all those indexes i for which arr[i]/n is greater than 1.
	 * 
	 * This approach works because all elements are in range from 0 to n-1 and
	 * arr[i]/n would be greater than 1 only if a value "i" has appeared more
	 * than once.
	 * 
	 * @param arr
	 * @param n
	 */
	public void printRepeating2(int arr[], int n) {
		// First check all the values that are
		// present in an array then go to that
		// values as indexes and increment by
		// the size of array
		for (int i = 0; i < n; i++) {
			int index = arr[i] % n;
			arr[index] += n;
		}

		// Now check which value exists more
		// than once by dividing with the size
		// of array
		for (int i = 0; i < n; i++) {
			if ((arr[i] / n) > 1)
				System.out.print(i + " ");
		}
	}

	/**
	 * Driver program to test the above function
	 */
	public static void main(String[] args) {
		RepeatingElementsInArray repeat = new RepeatingElementsInArray();
		int arr[] = { 4, 2, 4, 5, 2, 3, 1 };
		int arr_size = arr.length;
		repeat.printRepeating2(arr, arr_size);
		System.out.println();
		MyUtil.printArrayInt(arr);
	}

	public int abs(int a) {
		return (a < 0) ? -a : a;
	}

}
