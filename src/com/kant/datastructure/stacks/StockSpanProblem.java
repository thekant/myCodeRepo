/**
 * 
 */
package com.kant.datastructure.stacks;

import com.kant.sortingnsearching.MyUtil;

/**
 * http://www.geeksforgeeks.org/the-stock-span-problem/ <br/>
 * 
 * 
 * The stock span problem is a financial problem where we have a series of n
 * daily price quotes for a stock and we need to calculate span of stock’s price
 * for all n days. The span Si of the stock’s price on a given day i is defined
 * as the maximum number of consecutive days just before the given day, for
 * which the price of the stock on the current day is less than or equal to its
 * price on the given day. For example, if an array of 7 days prices is given as
 * {100, 80, 60, 70, 60, 75, 85}, then the span values for corresponding 7 days
 * are {1, 1, 1, 2, 1, 4, 6}
 * 
 * @author shaskant
 *
 */
public class StockSpanProblem {

	int[] stockData;// prices

	/**
	 * 
	 */
	public StockSpanProblem(int[] stockData) {
		this.stockData = stockData;
	}

	/**
	 * General approach. TESTED
	 * 
	 * @return
	 */
	public int[] getStackSpan() {
		int[] result = new int[stockData.length];

		for (int i = 0; i < stockData.length; i++) {
			result[i] = 1;
			for (int j = i - 1; j > 0; j--) {
				if (stockData[i] > stockData[j])
					result[i]++;
			}
		}
		return result;
	}

	/**
	 * TESTED
	 * 
	 * @return
	 */
	public int[] getStackSpanOptimal() {
		int[] result = new int[stockData.length];
		result[0] = 1;
		// store indexes
		Stack<Integer> stack = new StackListImplementation<>(false);
		stack.push(0);

		for (int i = 1; i < stockData.length; i++) {
			// pop as long as top element is lesser.
			while (!stack.isEmpty()
					&& stockData[stack.peek().intValue()] <= stockData[i]) {
				stack.pop();
			}
			// if stack is empty , current stock price is greater than all
			// before it , else index difference between current and index of
			// greater element at top of stack
			result[i] = (stack.isEmpty()) ? (i + 1) : (i - stack.peek());
			stack.push(i);
		}
		return result;
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		StockSpanProblem stockSpan = new StockSpanProblem(new int[] { 100, 80,
				60, 70, 60, 75, 85 });
		int[] result = stockSpan.getStackSpanOptimal();
		MyUtil.printArrayInt(result);
	}

}
