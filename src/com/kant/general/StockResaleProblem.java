package com.kant.general;

/**
 * Problem : When should one buy and sell the stock so that max profit is
 * attained. Basically one is supposed to find a pair of numbers in order with
 * the maximum difference.
 * 
 * @author shashi
 * 
 */
public class StockResaleProblem {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] stockData = { 190, 180, 260, 310, 410, 230, 695 };
		System.out.println(new StockResaleProblem().maxProfit(stockData));
	}

	/**
	 * Logic: max difference needs a minimum value to subtract by. So maintain a
	 * minimum value and a max difference obtained by subtracting this minimum
	 * values from current values in data array.
	 * 
	 * @param arr
	 * @param arr_size
	 * @return
	 */
	public int maxProfit(int arr[]) {
		int profit = 0;
		int minElement = Integer.MAX_VALUE;
		for (int i = 0; i < arr.length; i++) {
			minElement = Math.min(minElement, arr[i]);
			// if (arr[i] < minElement) {
			// minElement = arr[i];
			// }
			profit = Math.max(arr[i] - minElement, profit);
			// if (arr[i] - minElement > profit) {
			// profit = arr[i] - minElement;
			// }
		}
		System.out.println(minElement);
		return profit;
	}
}
