/**
 * 
 */
package com.kant.general.newProblems;

/**
 * @author shaskant
 *
 */
public class MaximumOfSubarrayProblems {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int arr[] = { -1, 2, -1 };
		System.out.println(maximumProductSubArray(arr));
		System.out.println(maxProduct(arr));
	}

	/**
	 * DP solution
	 * 
	 * @param data
	 * @return
	 */
	public static int maxSumSubArray(int[] data) {
		int maximum = data[0];
		int maxHere = data[0];
		for (int i = 1; i < data.length; i++) {
			maxHere = Math.max(data[i], maxHere + data[i]);
			maximum = Math.max(maxHere, maximum);
		}
		return maximum;
	}

	public static int maxProduct(int[] nums) {
		int[] max = new int[nums.length];
		int[] min = new int[nums.length];

		max[0] = min[0] = nums[0];
		int result = nums[0];

		for (int i = 1; i < nums.length; i++) {
			if (nums[i] > 0) {
				max[i] = Math.max(nums[i], max[i - 1] * nums[i]);
				min[i] = Math.min(nums[i], min[i - 1] * nums[i]);
			} else {
				max[i] = Math.max(nums[i], min[i - 1] * nums[i]);
				min[i] = Math.min(nums[i], max[i - 1] * nums[i]);
			}

			result = Math.max(result, max[i]);
		}

		return result;
	}

	/**
	 * general approach
	 * 
	 * @param arr
	 * @return
	 */
	public static int maximumProductSubArray(int[] arr) {
		int maxProd = 1;
		for (int start = 0; start <= arr.length - 1; start++) {
			for (int end = arr.length - 1; end > 0; end--) {
				int prod = 1;
				for (int i = start; i <= end; i++) {
					prod *= arr[i];
				}
				maxProd = Math.max(maxProd, prod);
			}
		}
		return maxProd;
	}
}
