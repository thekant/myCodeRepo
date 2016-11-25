/**
 * 
 */
package com.kant.algorithms.greedy;

import java.util.Arrays;

/**
 * @author shaskant
 *
 */
public class MinCashFlowProblem {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// pay person j
		int[][] graph = { { 0, 1000, 1000, 1000 }, { 0, 0, 4000, 0 },
				{ 0, 0, 0, 1000 }, { 0, 0, 0, 0 } };
		// Print the solution
		int result = minCashFlow(graph, 4);
		System.out.println(result);
	}

	/**
	 * 
	 * @param graph
	 * @param N
	 * @return
	 */
	public static int minCashFlow(int[][] graph, int N) {
		int[] amount = new int[N];
		for (int p = 0; p < N; p++)
			for (int i = 0; i < N; i++)
				amount[p] += (graph[i][p] - graph[p][i]);
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
		int i = amount.length - 1, j = 0;
		while (j < i) {
			countTrans++;
			if ((amount[i] + amount[j]) > 0) {
				amount[i] += amount[j];
				amount[j] = 0;
				j++;
			} else if ((amount[i] + amount[j]) < 0) {
				amount[j] += amount[i];
				amount[i] = 0;
				i--;
			} else {
				amount[i] = 0;
				i--;
				amount[j] = 0;
				j++;
			}
		}
		return countTrans;
	}

}
