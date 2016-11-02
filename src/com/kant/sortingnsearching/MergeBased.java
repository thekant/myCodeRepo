/**
 * 
 */
package com.kant.sortingnsearching;

import java.util.Arrays;

/**
 * @author shaskant
 *
 */
public class MergeBased {

	/**
	 * 
	 * Make sure dA1 points to shorter array
	 * 
	 * @param dA1
	 * @param dA2
	 * @return
	 */
	public static int[] merge(int[] dA1, int[] dA2) {

		if (dA1 == null || dA1.length == 0)
			return dA2;
		else if (dA2 == null || dA2.length == 0)
			return dA1;

		int[] mA = new int[dA1.length + dA2.length];
		Arrays.fill(mA, -1);

		int x1 = 0, x2 = 0, x3 = 0;
		for (; x1 < dA1.length && x2 < dA2.length;) {
			if (dA1[x1] < dA2[x2]) {
				mA[x3] = dA1[x1++];
			} else {
				mA[x3] = dA2[x2++];
			}
			x3++;
		}

		// leftovers from dA1
		while (x1 < dA1.length) {
			mA[x3++] = dA1[x1++];
		}

		// leftovers from dA2
		while (x2 < dA2.length) {
			mA[x3++] = dA2[x2++];
		}

		return mA;
	}

	/**
	 * 
	 * @param data
	 * @return
	 */
	public static int[] mergeSort(int[] data) {
		if (data.length <= 1)
			return data;
		int mid = (int) Math.ceil(data.length / 2);
		int[] first = new int[mid];
		int[] second = new int[data.length - first.length];

		System.arraycopy(data, 0, first, 0, first.length);
		System.arraycopy(data, first.length, second, 0, second.length);

		return merge(mergeSort(first), mergeSort(second));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] data1 = { 1, 3, 5, 7, 9, 11 };
		int[] data2 = { 0, 2, 4, 6, 8 };

		System.out.print("Output: ");
		MyUtil.printArrayInt(merge(data1, data2));

		int[] data3 = { 3, 1, 5, 7, 11, 15, 11 };
		// MyUtil.printArrayInt(mergesort(data3, 0, data3.length - 1));
		MyUtil.printArrayInt(mergeSort(data3));
		/**
		 * test code
		 */
		String[] test = { "we", "are", "testing", "here" };
		MyUtil.printArrayType(test);
	}

}
