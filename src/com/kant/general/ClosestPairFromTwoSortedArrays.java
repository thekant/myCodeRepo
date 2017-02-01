/**
 * 
 */
package com.kant.general;

/**
 * http://quiz.geeksforgeeks.org/given-sorted-array-number-x-find-pair-array-
 * whose-sum-closest-x/
 * 
 * http://www.geeksforgeeks.org/given-two-sorted-arrays-number-x-find-pair-whose
 * -sum-closest-x/
 * 
 * @author shashi
 * 
 */
public class ClosestPairFromTwoSortedArrays {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] data1 = { 1, 4, 5, 7 };
		int[] data2 = { 10, 20, 30, 40 };

		ClosestPairFromTwoSortedArrays platform = new ClosestPairFromTwoSortedArrays();

		platform.findClosestPair(platform.mergeTwoArrays(data1, data2), 32);

	}

	/**
	 * returns closest pair diff
	 * 
	 * @param data
	 * @param sum
	 */
	public void findClosestPair(int[] data, int sum) {
		int l = 0;
		int r = data.length - 1;

		int diff = Integer.MAX_VALUE;
		int minI = 0, minJ = 0;
		while (l <= r) {
			if (diff > Math.abs(sum - data[l] - data[r])) {
				diff = Math.abs(sum - data[l] - data[r]);
				minI = l;
				minJ = r;
			}
			if (data[l] + data[r] > sum) {
				r--;
			} else
				l++;
		}
		System.out.println();
		System.out.println(data[minI] + " & " + data[minJ]);
	}

	/**
	 * @param data1
	 * @param data2
	 * @return
	 */
	public int[] mergeTwoArrays(int[] data1, int[] data2) {
		// merge two arrays
		int[] dataM = new int[data1.length + data2.length];

		int indexFirst = 0;
		int indexSec = 0;
		int indexM = 0;

		while (indexFirst < data1.length && indexSec < data2.length) {
			if (data1[indexFirst] <= data2[indexSec])
				dataM[indexM++] = data1[indexFirst++];
			else
				dataM[indexM++] = data2[indexSec++];
		}

		if (indexFirst == data1.length) {
			while (indexSec < data2.length)
				dataM[indexM++] = data2[indexSec++];
		} else
			while (indexFirst < data1.length)
				dataM[indexM++] = data1[indexFirst++];

		for (int item : dataM) {
			System.out.print(item + " ");
		}
		return dataM;
	}
}