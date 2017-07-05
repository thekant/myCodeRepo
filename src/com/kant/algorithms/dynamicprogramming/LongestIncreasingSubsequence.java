package com.kant.algorithms.dynamicprogramming;

/**
 * 
 * @author shaskant
 *
 */
public class LongestIncreasingSubsequence {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int arr[] = { 10, 22, 9, 33, 21, 50, 41, 60 };
		int n = arr.length;
		System.out.println("\nLength of lis : " + lis(arr, n) + "\n");
	}

	/**
	 * O(n^2)<br/>
	 * L(i) = { 1 + Max ( L(j) ) } where j < i and arr[j] < arr[i] and if there
	 * is no such j then L(i) = 1
	 * 
	 * 
	 * or alternatively 
	 * def LIS(S): T = sort(S) 
	 *             T = removeDuplicates(T) 
	 *             return LCS(S, T)
	 * 
	 * @param arr
	 * @param n
	 * @return
	 */
	public static int lis(int[] arr, int n) {
		int[] lis = new int[n];
		int[] prev = new int[n];
		int maxLength = 1, bestEnd = 0;
		for (int i = 0; i < n; i++) {
			lis[i] = 1;
			prev[i] = -1;
		}

		for (int i = 1; i < n; i++) {
			/**
			 * lis[i] changes to the maximum L[j] for all j < i and arr[i] >
			 * arr[j]
			 */
			for (int j = 0; j < i; j++)
				if (arr[i] > arr[j] && lis[i] < lis[j] + 1) {
					lis[i] = lis[j] + 1;
					prev[i] = j;
				}
			if (lis[i] > maxLength) {
				maxLength = lis[i];
				bestEnd = i;
			}
		}

		/**
		 * use the array 'prev' to be able to find the actual sequence not just
		 * the length of LIS.
		 * 
		 * Just go back recursively from bestEnd in a loop using prev[bestEnd].
		 * The -1 value is a sign to stop
		 */
		int[] solution = new int[maxLength];
		int j = bestEnd;
		System.out.println("LIS in reverse order: ");
		for (int i = maxLength - 1; j != -1 && i >= 0; i--) {
			solution[i] = arr[j];
			j = prev[j];
			System.out.print(solution[i] + " ");
		}
		return maxLength;
	}

	/**
	 * https://stackoverflow.com/questions/2631726/how-to-determine-the-longest-
	 * increasing-subsequence-using-dynamic-programming
	 * 
	 * O(NLOGN solution)
	 * 
	 * Let S[pos] be defined as the smallest integer that ends an increasing
	 * sequence of length pos. Now iterate through every integer X of the input
	 * set and do the following:
	 * 
	 * If X > last element in S, then append X to the end of S. This essentialy
	 * means we have found a new largest LIS.
	 * 
	 * Otherwise find the smallest element in S, which is >= than X, and change
	 * it to X.
	 * 
	 * Because S is sorted at any time, the element can be found using binary
	 * search in log(N). Total runtime - N integers and a binary search for each
	 * of them - N * log(N) = O(N log N)
	 */

}
