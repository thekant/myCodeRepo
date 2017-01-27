package com.kant.general.advanced;

import java.util.Stack;

/**
 * http://www.geeksforgeeks.org/largest-rectangle-under-histogram/
 * 
 * @author shashi
 * 
 */
public class LargestRectangleUnderHistogram {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		LargestRectangleUnderHistogram histProb = new LargestRectangleUnderHistogram();
		int hist[] = { 6, 2, 5, 4, 5, 1, 6 }; // ans 12
		System.out.println(histProb.getMaxArea(hist));
	}

	/**
	 * 
	 * @param hist
	 * @return
	 */
	public int getMaxArea(int[] hist) {
		Stack<Integer> stack = new Stack<Integer>();
		int maxArea = 0;
		int curArea = 0;

		int barIndx = 0;
		for (; barIndx < hist.length;) {
			if (stack.isEmpty() || hist[stack.peek()] <= hist[barIndx]) {
				stack.push(barIndx++);
			} else {
				int topBarIndx = stack.pop();
				curArea = hist[topBarIndx]
						* ((stack.empty() ? barIndx : barIndx - stack.peek()
								- 1));
				if (maxArea < curArea)
					maxArea = curArea;
			}
		}

		// Now pop the remaining bars from stack and calculate area with every
		// popped bar as the smallest bar
		while (!stack.empty()) {
			int top = stack.peek();
			stack.pop();
			curArea = hist[top]
					* ((stack.empty() ? barIndx : barIndx - stack.peek() - 1));
			if (maxArea < curArea)
				maxArea = curArea;
		}
		return maxArea;
	}
}
