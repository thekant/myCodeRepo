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
	public static int[] mergeTwoArrays(int[] dA1, int[] dA2) {
		if (dA1 == null && dA2 == null)
			return null;
		else if (dA1 == null && dA2 != null)
			return dA2;
		else if (dA1 != null && dA2 == null)
			return dA1;
		if (dA1.length > dA2.length) {
			int[] temp = dA1;
			dA1 = dA2;
			dA2 = temp;
		}
		int[] mA = new int[dA1.length + dA2.length];
		Arrays.fill(mA, -1);

		System.out.print("Input1: ");
		MyUtil.printArrayInt(dA1);
		System.out.print("Input2: ");
		MyUtil.printArrayInt(dA2);

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
	 * @param low
	 * @param high
	 * @return
	 */
	public static int[] mergesort(int[] data, int low, int high) {
		int mid = -1;
		if (low < high) {
			mid = low + (high - low) / 2;

			mergesort(data, low, mid);
			mergesort(data, mid + 1, high);
			mergeUp(data, low, mid, high);
		}

		return data;
	}

	/**
	 * 1 2 [3] 4 5 6 [mid-low+1 ]=>(2-0 +1) & [high - mid]=>(5-2) 1 2 [3] 4 5
	 * =>(2-0+1) & (4-2)
	 * 
	 * @param data
	 * @param low
	 * @param mid
	 * @param high
	 */
	private static void mergeUp(int[] data, int low, int mid, int high) {
		int[] lowA = MyUtil.getAnotherSubCopyOf(data, low, mid+1);
		int[] highA = MyUtil.getAnotherSubCopyOf(data, mid + 1, high+1);

		int[] result = mergeTwoArrays(lowA, highA);
		int count = low;
		for (int i = 0; i < result.length; i++) {
			data[count++] = result[i];
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] data1 = { 1, 3, 5, 7, 9, 11 };
		int[] data2 = { 0, 2, 4, 6, 8 };

		System.out.print("Output: ");
		MyUtil.printArrayInt(mergeTwoArrays(data1, data2));

		int[] data3 = { 3, 1, 5, 7, 11, 15, 11 };
		MyUtil.printArrayInt(mergesort(data3, 0, data3.length - 1));
		/**
		 * test code
		 */
		String[] test = { "we", "are", "testing", "here" };
		MyUtil.printArrayType(test);
	}

}
