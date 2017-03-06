package com.kant.algorithms.dynamicprogramming;

import java.util.Stack;

/**
 * 
 * @author shaskant
 *
 */
public class MaximumAreaUnderHistogram {

	/**
	 * 
	 * @param input
	 * @return
	 */
	public int maxHistogram(int input[]) {
		Stack<Integer> stack = new Stack<Integer>();
		int maxArea = 0;
		int area = 0;
		int i;
		for (i = 0; i < input.length;) {
			if (stack.isEmpty() || input[stack.peek()] <= input[i]) {
				stack.push(i++);
			} else {
				int top = stack.pop();
				// if stack is empty means everything till i has to be
				// greater or equal to input[top] so get area by
				// input[top] * i;
				// if stack is not empty then everything from i-1 to
				// input.peek()
				// + 1
				// has to be greater or equal to input[top]
				// so area = input[top]*(i - stack.peek() - 1);

				area = input[top]
						* ((stack.isEmpty()) ? i : (i - stack.peek() - 1));
				if (area > maxArea) {
					maxArea = area;
				}
			}
		}
		while (!stack.isEmpty()) {
			int top = stack.pop();
			// if stack is empty means everything till i has to be
			// greater or equal to input[top] so get area by
			// input[top] * i;
			area = input[top]
					* ((stack.isEmpty()) ? i : (i - stack.peek() - 1));
			if (area > maxArea) {
				maxArea = area;
			}
		}
		return maxArea;
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		MaximumAreaUnderHistogram mh = new MaximumAreaUnderHistogram();
		int input[] = { 2, 2, 2, 6, 1, 5, 4, 2, 2, 2, 2 };
		int maxArea = mh.maxHistogram(input);
		assert maxArea == 12;
		System.out.println(maxArea);
	}
}
