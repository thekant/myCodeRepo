/**
 * 
 */
package com.kant.datastructure.stacks;

/**
 * TESTED
 * 
 * http://www.geeksforgeeks.org/reverse-a-stack-using-recursion/
 * 
 * A queue can reverse a stack. A queue can be implemented with 2 stacks.
 * 
 * @author shaskant
 *
 */
public class ReverseStackUsingRecurssion {

	/**
	 * TESTED
	 * 
	 * @param stack
	 */
	public void reverseStack(java.util.Stack<String> stack) {
		if (!stack.isEmpty()) {
			String temp = stack.pop();
			reverseStack(stack);
			insertAtBottom(stack, temp);
		}
	}

	private void insertAtBottom(java.util.Stack<String> stack, String data) {
		if (stack.isEmpty()) {
			stack.push(data);
		} else {
			String temp = stack.pop();
			insertAtBottom(stack, data);
			stack.push(temp);
		}
	}

	
	public void revert(java.util.Stack<String> stack){
		revert(stack, stack.pop());
	}
	
	private void revert(java.util.Stack<String> stack, String data) {
		if (stack.isEmpty()) {
			stack.push(data);
		} else {
			String temp = stack.pop();
			revert(stack, temp);
			stack.push(data);
		}
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		java.util.Stack<String> stack = new java.util.Stack<>();
		stack.push("?");
		stack.push("shashi");
		stack.push("this");
		stack.push("is");
		stack.push("ok");
		ReverseStackUsingRecurssion stackReverser = new ReverseStackUsingRecurssion();
		stackReverser.revert(stack);
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
	}
}
