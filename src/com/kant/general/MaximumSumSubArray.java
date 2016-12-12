/**
 * 
 */
package com.kant.general;

import com.kant.sortingnsearching.MyUtil;

/**
 * KADANE's algorithm: pick only the positive pieces<br/>
 * TODO http://www.geeksforgeeks.org/maximum-sum-pairs-specific-difference/
 * 
 * @author shaskant
 *
 */
public class MaximumSumSubArray {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] data = new int[] { 8, 2, 6, 3, 100, -99, 1000 };
		int[] diffArray = new int[data.length];
		for (int index = 0; index < data.length - 1; index++) {
			diffArray[index] = data[index + 1] - data[index];
		}
		MyUtil.printArrayInt(diffArray);
		System.out.println(robustSolution(diffArray));

	}

	/**
	 * Algo will not find solution in case of all -ve numbers.
	 * 
	 * @param data
	 * @return
	 */
	public static int solve(int[] data) {
		int maximumSum = -1;
		int curSum = 0;

		for (int i = 0; i < data.length; i++) {
			curSum += data[i];
			if (curSum < 0)
				curSum = 0;
			else if (curSum > maximumSum)
				maximumSum = curSum;
		}
		return maximumSum;
	}

	/**
	 * in case of all negatives it returns least negative number.
	 * 
	 * @param data
	 * @return
	 */
	public static int robustSolution(int[] data) {
		int maximumSum = data[0];
		int curSum = data[0];
		for (int i = 1; i < data.length; i++) {
			// if data[i] is itself bigger than adding prev sum .. leave the
			// previous sum.
			curSum = Math.max(data[i], curSum + data[i]);
			maximumSum = Math.max(curSum, maximumSum);
		}

		return maximumSum;

	}

}
