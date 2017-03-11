/**
 * 
 */
package com.kant.sortingnsearching;

/**
 * @author shaskant
 *
 */
public class SmallestAndSecondSmallest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int arr[] = { 12, 13, 1, 10, 34, 1 };
		solve(arr);
	}

	/**
	 * http://www.geeksforgeeks.org/to-find-smallest-and-second-smallest-element
	 * -in-an-array/ <br/>
	 * O(n)
	 * 
	 * @param arr
	 */
	private static void solve(int[] arr) {
		int smallest = Integer.MAX_VALUE;
		int smallest2 = Integer.MAX_VALUE;
		for (int i = 0; i < arr.length; i++) {
			if (smallest > arr[i]) {
				smallest2 = smallest;//take the now 2nd smallest value.
				smallest = arr[i];
			} else if (arr[i] != smallest && smallest2 > arr[i]) {
				smallest2 = arr[i];
			}
		}
		System.out.println(smallest + " " + smallest2);
	}

}
