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
	public void reverseStack(StackListImplementation<String> stack) {
		if (!stack.isEmpty()) {
			String temp = stack.pop();
			reverseStack(stack);
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
