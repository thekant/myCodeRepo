/**
 * 
 */
package com.kant.algorithms.dynamicprogramming;

import java.util.Arrays;

/**
 * * Partition problem is to determine whether a given set can be partitioned
 * into two subsets such that the sum of elements in both subsets is same.
 * 
 * http://www.geeksforgeeks.org/dynamic-programming-set-18-partition-problem/
 * 
 * Time Complexity: O(sum*n) Auxiliary Space: O(sum*n)  DP solution
 * 
 * @author shashi
 * 
 */
public class PartitionDataIntoTwoPartsOfEqualSum {

	/**
	 * 
	 * @param data
	 * @return
	 */
	public boolean isSolvable(int[] data, boolean solveByDp) {
		int sum = 0;
		for (int x : data)
			sum = sum + x;

		// sum is odd then no solution possible
		if (sum % 2 != 0) {
			return false;
		} else {
			if (!solveByDp)
				return isSubsetPossible(data, data.length, sum / 2);
			else {
				return isSubsetPossibleViaDp(data, data.length, sum / 2);
			}
		}

	}

	/**
	 * 
	 * @param data
	 * @param n
	 * @param sum
	 * @return
	 */
	private boolean isSubsetPossible(int[] data, int n, int sum) {
		if (sum == 0)
			return true;
		if (n == 0 && sum != 0)
			return false;
		if (sum < data[n - 1])
			return isSubsetPossible(data, n - 1, sum);
		return isSubsetPossible(data, n - 1, sum)
				|| isSubsetPossible(data, n - 1, sum - data[n - 1]);
	}

	/**
	 * 
	 */
	boolean[][] stateStore;

	/**
	 * 
	 * Bottom-Up approach or Dynamic programming.
	 * 
	 * @param data
	 * @param number
	 * @param sum
	 * @return
	 */
	private boolean isSubsetPossibleViaDp(int[] data, int number, int sum) {
		stateStore = new boolean[number + 1][];
		for (int x = 0; x <= number; x++) {
			stateStore[x] = new boolean[sum + 1];
			Arrays.fill(stateStore[x], false);
		}

		for (int n = 0; n <= number; n++) {
			for (int s = 0; s <= sum; s++) {
				if (s == 0)
					stateStore[n][s] = true;
				else if (n == 0 && s != 0)
					stateStore[n][s] = false;
				else {
					if (data[n - 1] > s)
						stateStore[n][s] = stateStore[n - 1][s];
					else
						stateStore[n][s] = stateStore[n - 1][s]
								|| stateStore[n - 1][s - data[n - 1]];
				}
			}
		}
		return stateStore[number][sum];
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int arr[] = { 3, 1, 5, 9, 10, 2 };
		PartitionDataIntoTwoPartsOfEqualSum prob = new PartitionDataIntoTwoPartsOfEqualSum();
		System.out
				.println(prob.isSolvable(arr, true) ? "Can be divided into two subsets of equal sum"
						: "Cannot be divided into two subsets of equal sum");
	}
}
