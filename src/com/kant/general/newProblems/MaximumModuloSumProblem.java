/**
 * 
 */
package com.kant.general.newProblems;

/**
 * https://www.quora.com/What-is-the-logic-used-in-the-HackerRank-Maximise-Sum-
 * problem/answer/Jinyao-Xu?srid=XyZG
 * 
 * @author shaskant
 *
 */
public class MaximumModuloSumProblem {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] arr = { 3, 3, 9, 9, 5 };
		int M = 7;
		int n = arr.length;
		int ret = moduloSumBruteForce(arr, M, n);

		System.out.println(ret);

	}

	/**
	 * (a + b)%M = (a%M + b%M)%M --- 1 <br/>
	 * (a − b)%M = (a%M − b%M)%M --- 2
	 * 
	 * prefix[n] = (a[0] + a[1] +...+ a[n])%M
	 * 
	 * sumModular[i,j] = (prefix[j] − prefix[i−1] + M )%M --3
	 * 
	 * @param arr
	 * @param M
	 * @param n
	 * @return
	 */
	private static int moduloSumBruteForce(int[] arr, int M, int n) {
		int[] prefix = new int[n];
		int curr = 0;
		for (int i = 0; i < n; i++) {
			curr = (arr[i] % M + curr) % M;
			prefix[i] = curr;
		}

		int ret = 0;
		for (int i = 0; i < n; i++) {
			for (int j = i - 1; j >= 0; j--) {
				/**
				 * add M to make sure modulus value is +ve. Closest prefix[j]
				 * wrt prefix[i] which is slightly greater than will give
				 * maximum modulo value.
				 * 
				 */
				ret = Math.max(ret, (prefix[i] - prefix[j] + M) % M);
			}
			ret = Math.max(ret, prefix[i]); // Don't forget sum from beginning.
		}
		return ret;
	}

}
