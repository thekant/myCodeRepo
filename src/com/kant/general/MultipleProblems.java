/**
 * 
 */
package com.kant.general;

import java.util.HashMap;

/**
 * @author shaskant
 *
 */
public class MultipleProblems {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(findFirstOne(new int[] { 0, 0, 0, 0, 0, 0, 1 }));
		System.out.println(isSumPossible(new int[] { 11, 18, 21, 28, 31, 38,
				40, 55, 60, 62 }, 51));
	}

	/**
	 * find occurrence of first one. Can be modified for a [low to high] range.
	 * 
	 * @param data
	 * @return
	 */
	private static int findFirstOne(int[] data) {
		int low = 0;
		int high = data.length - 1;
		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (data[mid] == 1) {
				if (((mid > 0) && data[mid - 1] == 0) || mid == 0) {
					return mid;
				}
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return -1;
	}

	/**
	 * sum of any two numbers in array whose sum is give by N
	 * 
	 * @return
	 */
	public static boolean isSumPossible(int[] data, int N) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < data.length; i++) {
			if (map.containsKey(N - data[i])) {
				return true;
			} else
				map.put(data[i], data[i]);
		}
		return false;
	}
}
