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
		System.out.println(new StockResaleProblem().maxDifference(stockData));
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
	public int maxDifference(int arr[]) {
		int maxDiff = -1;
		int minElement = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] < minElement) {
				minElement = arr[i];
			}else if (arr[i] - minElement > maxDiff) {
				maxDiff = arr[i] - minElement;
			}
		}
		System.out.println(minElement);
		return maxDiff;
	}

}
