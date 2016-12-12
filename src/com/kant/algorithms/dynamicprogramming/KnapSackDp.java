package com.kant.algorithms.dynamicprogramming;

import java.util.Arrays;

/**
 * Dynamic Programming solution to 0-1 knapsack problem
 * 
 * @author shashi
 */
public class KnapSackDp {

	int[][] stateStore;

	/**
	 * 
	 * @param W
	 * @param numberOfItems
	 */
	public KnapSackDp(int W, int numberOfItems) {
		stateStore = new int[numberOfItems + 1][W + 1];
		for (int i = 0; i <= numberOfItems; i++) {
			stateStore[i] = new int[W + 1];
			Arrays.fill(stateStore[i], -1);
		}
	}

	/**
	 * 
	 * @param values
	 * @param weights
	 * @param Weight
	 * @param number
	 * @return
	 */
	public int solveKnapSackProb(int[] values, int weights[], int Weight, int number) {
		for (int n = 0; n <= number; n++)
			for (int w = 0; w <= Weight; w++) {
				if (n == 0 || w == 0)
					stateStore[n][w] = 0;
				else if (weights[n - 1] <= w)
					stateStore[n][w] = Math.max(values[n - 1]
							+ stateStore[n - 1][w - weights[n - 1]],
							stateStore[n - 1][w]);
				else
					stateStore[n][w] = stateStore[n - 1][w];
			}

		return stateStore[number][Weight];
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int val[] = { 60, 100, 120 };
		int wt[] = { 10, 20, 30 };
		int W = 50;
		System.out.println(new KnapSackDp(W, val.length).solveKnapSackProb(val,
				wt, W, val.length));
	}
}
