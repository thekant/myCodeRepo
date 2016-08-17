/**
 * 
 */
package com.kant.datastructure.stacks;

/**
 * TESTED
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
	public void reverseStack(Stack<String> stack) {
		if (!stack.isEmpty()) {
			String temp = stack.pop();
			reverseStack(stack);
			insertAtBottom(stack, temp);
		}
	}

	private void insertAtBottom(Stack<String> stack, String data) {
		if (stack.isEmpty()) {
			stack.push(data);
		} else {
			String temp = stack.pop();
			insertAtBottom(stack, data);
			stack.push(temp);
		}
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		StackListImplementation<String> stack = new StackListImplementation<>(
				false);
		stack.push("shashi");
		stack.push("is");
		stack.push("this");
		ReverseStackUsingRecurssion stackReverser = new ReverseStackUsingRecurssion();
		stackReverser.reverseStack(stack);
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());

	}

}
