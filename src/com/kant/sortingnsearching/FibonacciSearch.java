/**
 * 
 */
package com.kant.sortingnsearching;

/**
 * http://www.geeksforgeeks.org/fibonacci-search/
 * 
 * Basic understanding of algo [yes]
 * TODO [understand code and corner cases]
 * @author shaskant
 *
 */
public class FibonacciSearch {

	/**
	 * 
	 * @param value
	 * @param data
	 * @param low
	 * @param high
	 * @return
	 */
	public int find(int value, int[] data, int low, int high) {
		int fn2 = 0, fn1 = 1;// n-2 and n-1 th
		int fn = fn2 + fn1; // nth
		while (fn < high) {
			fn2 = fn1 + fn;
			fn1 = fn;
			fn = fn2;
		}

		return -1;
	}

	private int min(int x, int y) {
		return x <= y ? x : y;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int arr[] = { 2, 3, 4, 6, 8, 10, 40, 42 };
		FibonacciSearch fibSearch = new FibonacciSearch();
		System.out.println(fibSearch.find(4, arr, 0, arr.length - 1));
	}

}
