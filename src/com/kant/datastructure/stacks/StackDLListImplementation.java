/**
 * 
 */
package com.kant.datastructure.stacks;

import com.kant.datastructure.list.ListDNode;

/**
 * 
 * @author shaskant
 *
 */
public class StackDLListImplementation<T> implements Stack<T> {

	private ListDNode<T> topHead = null;
	private boolean logFlag;
	private boolean midMaintainFlag;
	private ListDNode<T> midNode = null;
	private int itemCount = 0;

	/**
	 * 
	 */
	public StackDLListImplementation(boolean logEnabled) {
		this(logEnabled, false);
	}

	public StackDLListImplementation(boolean logEnabled, boolean maintainMid) {
		logFlag = logEnabled;
		midMaintainFlag = maintainMid;
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
		itemCount--;
		if (logFlag)
			System.out.println("[Popping data]: " + data);
		if (midMaintainFlag) {
			if (itemCount == 0) {
				midNode = null;
			} else {
				topHead.setPrev(null);
				if (itemCount % 2 != 0) {
					midNode = midNode.getNext();
				}
			}
		}
		return data;
	}

	@Override
	public void push(T data) {
		if (logFlag)
			System.out.println("[Pushing data]: " + data);
		ListDNode<T> temp = new ListDNode<T>(data, topHead);
		itemCount++;
		if (midMaintainFlag) {
			if (itemCount == 1) {
				midNode = temp;
			} else {
				topHead.setPrev(temp);
				if (itemCount % 2 == 0) {
					midNode = midNode.getPrev();
				}
			}
		}
		topHead = temp;
	}

	/**
	 * 
	 * @return
	 */
	public T getMidNode() {
		if (midNode != null) {
			if (logFlag)
				System.out.println("[Fetching Mid Node]: " + midNode.getData());
			return midNode.getData();
		}
		if (logFlag)
			System.out.println("[Fetching Mid Node]: " + "STACK EMPTY");
		return null;
	}

	public static void main(String[] args) {
		StackDLListImplementation<String> stack = new StackDLListImplementation<>(
				true, true);
		stack.push("1");
		stack.push("2");
		stack.push("3");
		stack.push("4");
		stack.push("5");

		// stack.pop();
		// stack.pop();
		// stack.pop();
		System.out.println(stack.getMidNode());
		System.out.println(stack.isEmpty());
	}

}
