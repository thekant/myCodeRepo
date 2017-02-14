/**
 * 
 */
package com.kant.algorithms.greedy;

import java.util.Arrays;

import com.kant.sortingnsearching.MyUtil;

/**
 * other way:
 * http://www.geeksforgeeks.org/minimize-cash-flow-among-given-set-friends-
 * borrowed-money/
 * 
 * @author shaskant
 *
 */
public class MinCashFlowProblem {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/**
		 * graph[i][j] indicates the amount that person i needs to pay person j
		 */
		int[][] graph = { { 0, 1000, 1000, 1000 }, { 0, 0, 4000, 0 },
				{ 0, 0, 0, 1000 }, { 0, 0, 0, 0 } };
		// Print the solution
		int result = minCashFlow(graph, 4);
		System.out.println(result);
	}

	/**
	 * calculates net amount person owes/gets
	 * 
	 * @param graph
	 * @param N
	 *            number of persons
	 * @return
	 */
	public static int minCashFlow(int[][] graph, int N) {
		int[] amount = new int[N];
		for (int person = 0; person < N; person++)
			for (int i = 0; i < N; i++)
				amount[person] += (graph[i][person] - graph[person][i]);
		return minCashFlowSolution(amount);
	}

	/**
	 * 
	 * @param amount
	 * @return
	 */
	private static int minCashFlowSolution(int[] amount) {
		Arrays.sort(amount);
		int countTrans = 0;
		int iPos = amount.length - 1, jNeg = 0;
		while (jNeg < iPos) {
			countTrans++;
			// +ve sum
			if ((amount[iPos] + amount[jNeg]) > 0) {
				amount[iPos] += amount[jNeg];
				amount[jNeg] = 0;
				jNeg++;
			}// -ve sum
			else if ((amount[iPos] + amount[jNeg]) < 0) {
				amount[jNeg] += amount[iPos];
				amount[iPos] = 0;
				iPos--;
			}// +ve == -ve
			else {
				amount[iPos] = 0;
				iPos--;
				amount[jNeg] = 0;
				jNeg++;
			}
		}
		// should be 0'ed as all positives match up for negatives
		MyUtil.printArrayInt(amount);
		return countTrans;
	}

}
