/**
 * 
 */
package com.kant.datastructure.stacks;

import com.kant.datastructure.list.ListNode;

/**
 * 
 * @author shaskant
 *
 */
public class StackListImplementation<T> implements Stack<T> {

	private ListNode<T> topHead = null;
	private boolean logFlag;
	private int itemCount = 0;

	/**
	 * 
	 */
	public StackListImplementation(boolean logEnabled) {
		logFlag = logEnabled;
	}

	@Override
	public boolean isEmpty() {
		return (topHead == null) ? true : false;
	}

	@Override
	public T peek() {
		if (isEmpty())
			return null;
		if (logFlag)
			System.out.println("[Peeking Data]: " + topHead.getData());
		return topHead.getData();
	}

	@Override
	public T pop() {
		if (isEmpty())
			return null;
		T data = topHead.getData();
		topHead = topHead.getNext();
		if (logFlag)
			System.out.println("[Popping data]: " + data);
		itemCount--;
		return data;
	}

	@Override
	public void push(T data) {
		if (logFlag)
			System.out.println("[Pushing data]: " + data);
		ListNode<T> temp = new ListNode<T>(data, topHead);
		topHead = temp;
		itemCount++;
	}

	public static void main(String[] args) {
		StackListImplementation<String> stack = new StackListImplementation<>(
				true);
		stack.push("1");
		stack.push("2");
		stack.push("3");
		stack.push("4");

		stack.pop();
		stack.pop();
		stack.peek();
		System.out.println(stack.isEmpty());
	}

}
