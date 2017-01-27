/**
 * 
 */
package com.kant.general.advanced;

/**
 * normal merge sort way of moving ahead and calculating sum of two arrays while
 * delaying as much as possible for common element.<br/>
 * At common elements initialize for maximum sum found so far and repeat for
 * sums again.
 * 
 * @author shashi
 * 
 */
public class MaXPathSumForCommonPointsInArrays {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int ar1[] = { 2, 3, 7, 10, 12, 15, 30, 34 };
		int ar2[] = { 1, 5, 7, 8, 10, 15, 16, 19 };

		System.out.println(getMaxPathSum(ar1, ar2));
	}

	/**
	 * @param ar1
	 * @param ar2
	 */
	public static int getMaxPathSum(int[] ar1, int[] ar2) {
		int sum1 = 0;
		int sum2 = 0;
		int index1 = 0, index2 = 0;
		int result = 0;

		// normal merge process until we get common points
		for (; index1 < ar1.length && index2 < ar2.length;) {
			if (ar1[index1] < ar2[index2])
				sum1 += ar1[index1++];
			else if (ar1[index1] > ar2[index2])
				sum2 += ar2[index2++];
			else {
				result += Math.max(sum1, sum2);
				sum1 = 0;
				sum2 = 0;
				while (index1 < ar1.length && index2 < ar2.length
						&& ar1[index1] == ar2[index2]) {
					result += ar1[index1];
					index1++;
					index2++;
				}

			}
		}
		while (index1 < ar1.length)
			sum1 += ar1[index1++];
		while (index2 < ar2.length)
			sum2 += ar2[index2++];

		result += Math.max(sum1, sum2);
		return result;
	}
}
