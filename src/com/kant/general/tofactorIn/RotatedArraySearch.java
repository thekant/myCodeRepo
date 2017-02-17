/**
 * 
 */
package com.kant.general.tofactorIn;

/**
 * 
 * Use the findPivot() approach and then do binary search on the part of array
 * that can contain data.
 * 
 * @author shashi
 * 
 */
public class RotatedArraySearch {

	public static int findInRotatedArray(int[] data, int item, int start,
			int last) {
		if (start == last && data[start] == item)
			return start;
		else if (start > last)
			return -1;

		int mid = start + (last - start) / 2;
		if (data[mid] == item)
			return mid;
		if (item > Math.max(data[mid], data[start])) {
			if (data[start] > data[mid]) {
				return findInRotatedArray(data, item, start, mid - 1);
			}
			return findInRotatedArray(data, item, mid + 1, last);
		}
		if ((item < data[mid] && item < data[start])
				|| (item > data[mid] && item <= data[last]))
			return findInRotatedArray(data, item, mid + 1, last);

		return findInRotatedArray(data, item, start, mid - 1);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] data = { 6, 1, 2, 3, 4, 5 };
		for (int index = 0; index < data.length; index++) {
			System.out.println(findInRotatedArray(data, data[index], 0,
					data.length - 1));
		}
	}

}
