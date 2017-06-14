package com.kant.algorithms.dynamicprogramming;

import java.util.Stack;

/**
 * https://www.youtube.com/watch?v=ZmnqCZp9bBs
 * 
 * [SOLVED]
 * 
 * @author shaskant
 *
 */
public class MaximumAreaUnderHistogram {

	/**
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		MaximumAreaUnderHistogram mh = new MaximumAreaUnderHistogram();
		int input[] = { 2, 2, 2, 6, 1, 5, 4, 2, 2, 2, 2 };
		int maxArea = mh.maxHistogram(input);
		assert maxArea == 12; // must be the answer
		System.out.println(maxArea);
	}

	/**
	 * 
	 * @param input
	 * @return
	 */
	public int maxHistogram(int input[]) {
		Stack<Integer> stack = new Stack<Integer>();
		int maxArea = 0;
		int pos;
		for (pos = 0; pos < input.length;) {
			if (stack.isEmpty() || input[stack.peek()] <= input[pos]) {
				stack.push(pos++);
			} else {
				// keep removing greater items on stack for this position pos.
				maxArea = calculateArea(input, stack, maxArea, pos);
			}
		}
		while (!stack.isEmpty()) {
			maxArea = calculateArea(input, stack, maxArea, pos);
		}
		return maxArea;
	}

	/**
	 * if stack is empty means everything till currentIndex has to be greater or
	 * equal to input[top] so get area = input[top] * currentIndex;
	 * 
	 * if stack is not empty then every block from currentIndex to input.peek()
	 * has to be greater or equal to input[top] so area =
	 * input[top]*(currentIndex - stack.peek() - 1);
	 * 
	 * @param input
	 * @param stack
	 * @param maxArea
	 * @param pos
	 * @return
	 */
	private int calculateArea(int[] input, Stack<Integer> stack, int maxArea,
			int pos) {
		int area;
		int top = stack.pop();
		if (stack.isEmpty()) {
			area = input[top] * pos;
		} else
			area = input[top] * (pos - stack.peek() - 1);
		if (area > maxArea) {
			maxArea = area;
		}
		return maxArea;
	}

}