package com.kant.algorithms.dynamicprogramming;

public class LongestIncreasingSubsequence {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int arr[] = { 10, 22, 9, 33, 21, 50, 41, 60 };
		int n = arr.length;
		System.out.println("Length of lis is " + lis(arr, n) + "\n");
	}

	/**
	 * O(n^2)<br/>
	 * L(i) = { 1 + Max ( L(j) ) } where j < i and arr[j] < arr[i] and if there
	 * is no such j then L(i) = 1
	 * 
	 * @param arr
	 * @param n
	 * @return
	 */
	public static int lis(int[] arr, int n) {
		int[] lis = new int[n];
		for (int i = 0; i < n; i++)
			lis[i] = 1;

		for (int i = 1; i < n; i++) {
			for (int j = 0; j < i; j++) {
				if (arr[i] > arr[j] && lis[i] < lis[j] + 1)
					lis[i] = lis[j] + 1; //lis[i] changes to the maximum L[j] so is set to max till the current cycle
			}
		}

		int max = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			if (max < lis[i])
				max = lis[i];
		}

		return max;
	}

}
