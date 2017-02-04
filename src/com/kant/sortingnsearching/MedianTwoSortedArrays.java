/**
 * 
 */
package com.kant.sortingnsearching;

/**
 * DONE
 * 
 * @author shashi
 * 
 */
public class MedianTwoSortedArrays {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int ar1[] = { 1, 2, 3, 6 };
		int ar2[] = { 4, 6, 8, 10 };
		System.out.println(getMedian(ar1, ar2, ar1.length));
		System.out.println(getMedianMethod2(ar1, ar2, 0, ar1.length - 1, 0,
				ar2.length - 1));
	}

	/**
	 * O(n) ar1.length ==ar2.length
	 * 
	 * @return
	 */
	public static int getMedian(int ar1[], int ar2[], int n) {
		int i = 0; /* Current index of i/p array ar1[] */
		int j = 0; /* Current index of i/p array ar2[] */
		int count;
		int m1 = -1, m2 = -1;

		/**
		 * Since there are 2n elements, median will be average of elements at
		 * index n-1 and n in the array obtained after merging ar1 and ar2
		 */
		for (count = 0; count <= n; count++) {
			/**
			 * Below is to handle case where all elements of ar1[] are smaller
			 * than smallest(or first) element of ar2[]
			 */
			if (i == n) {
				m1 = m2;
				m2 = ar2[0];
				break;
			}

			/**
			 * Below is to handle case where all elements of ar2[] are smaller
			 * than smallest(or first) element of ar1[]
			 */
			else if (j == n) {
				m1 = m2;
				m2 = ar1[0];
				break;
			}

			if (ar1[i] < ar2[j]) {
				m1 = m2; /* Store the prev median */
				m2 = ar1[i];
				i++;
			} else {
				m1 = m2; /* Store the prev median */
				m2 = ar2[j];
				j++;
			}
		}

		return (m1 + m2) / 2;
	}

	/**
	 * This function returns median of ar1[] and ar2[]. Assumptions in this
	 * function: Both ar1[] and ar2[] are sorted arrays Both have n elements
	 */
	public static int getMedianMethod2(int ar1[], int ar2[], int start1,
			int end1, int start2, int end2) {
		int n = end1 - start1 + 1;
		if (n != end2 - start2 + 1)
			return -1;
		/* return -1 for invalid input */
		if (n <= 0)
			return -1;
		if (n == 1)
			return (ar1[start1] + ar2[start2]) / 2;
		if (n == 2)
			return (Math.max(ar1[start1], ar2[start2]) + Math.min(ar1[end1],
					ar2[end2])) / 2;

		int m1 = median(ar1, start1, end1); /* get the median of the first array */
		int m2 = median(ar2, start2, end2); /*
											 * get the median of the second
											 * array
											 */

		if (m1 == m2)
			return m1;
		if (m1 < m2) {
			if (n % 2 == 0) {
				return getMedianMethod2(ar1, ar2, start1 + n / 2 - 1, end1,
						start2, start2 + n / 2);
			} else
				return getMedianMethod2(ar1, ar2, start1 + n / 2, end1, start2,
						start2 + n / 2);
		} else {
			if (n % 2 == 0) {
				return getMedianMethod2(ar1, ar2, start1, start1 + n / 2,
						start2 + n / 2 - 1, end2);
			} else
				return getMedianMethod2(ar1, ar2, start1, start1 + n / 2,
						start2 + n / 2, end2);
		}

	}

	/**
	 * calculates median of an Array
	 * 
	 * @param data
	 * @param startIndx
	 *            {included}
	 * @param endIndx
	 *            {included}
	 * @return
	 */
	public static int median(int[] data, int startIndx, int endIndx) {
		int n = endIndx - startIndx + 1;
		if (n % 2 != 0)
			return data[startIndx + n / 2];
		else {
			return (data[startIndx + n / 2] + data[startIndx + n / 2 - 1]) / 2;
		}
	}
}
