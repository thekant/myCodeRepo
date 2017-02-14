/**
 * 
 */
package com.kant.general;

import java.util.Stack;

import com.kant.datastructure.stacks.NextGreaterElementOntheRightUsingStack;

/**
 * {@link NextGreaterElementOntheRightUsingStack}
 * http://www.geeksforgeeks.org/next-greater-element/ <br/>
 * Time Complexity: O(n).
 * <p>
 * The worst case occurs when all elements are sorted in decreasing order. If
 * elements are sorted in decreasing order, then every element is processed at
 * most 4 times.
 * <ul>
 * <li>a) Initially pushed to the stack.
 * <li>b) Popped from the stack when next element is being processed.
 * <li>c) Pushed back to the stack because next element is smaller.
 * <li>d) Popped from the stack in step 3 of algo.
 * </ul>
 * </p>
 * 
 * @author shaskant
 *
 */
public class NextGreaterElementOnRight {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int[] data = new int[] { 4, 5, 2, 25 };
		solve(data);
	}

	/**
	 * <ul>
	 * <li>if(stack.isempty)stack.push(item);
	 * <li>else{ while(stack.isEmpty()){
	 * <li>if(stack.peek() < item){
	 * <li>stack.pop() ----> item is NGE }
	 * <li>else
	 * <li>break; }
	 * <li>stack.push(item);
	 * <li>
	 * </ul>
	 * 
	 * @param data
	 */
	public static void solve(int[] data) {
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < data.length; i++) {
			if (stack.isEmpty())
				stack.push(data[i]);
			else {
				while (!stack.isEmpty()) {
					if (stack.peek() > data[i])
						break;
					else
						// keep popping as long as top element is smaller
						System.out.println(stack.pop() + " ----> " + data[i]);
				}
				stack.push(data[i]);
			}
		}
		while (!stack.isEmpty()) {
			System.out.println(stack.pop() + " -----> " + "-1");
		}
	}
}
