package com.kant.datastructure.stacks;

import java.util.ArrayList;
import java.util.EmptyStackException;

/**
 * set of stacks
 * 
 * @author shashi
 * 
 */
public class SetOfStacks {
	private ArrayList<java.util.Stack<Integer>> setStacks = new ArrayList<>();
	private final int stackCapacity = 5; // capacity of each stack.
	private int stackSetIndex = -1;

	/**
	 * 
	 * @param data
	 */
	public void push(int data) {
		if (stackSetIndex == -1) {
			setStacks.add(new java.util.Stack<Integer>());
			stackSetIndex++;
		} else if (setStacks.get(stackSetIndex) != null) {
			if (isfull()) {
				setStacks.add(new java.util.Stack<Integer>());
				stackSetIndex++;
			}
		}
		setStacks.get(stackSetIndex).push(data);
	}

	/**
	 * 
	 * @return
	 */
	public int pop() {
		int val = -1;
		if (stackSetIndex != -1) {
			java.util.Stack<Integer> curStack = setStacks.get(stackSetIndex);
			try {
				val = curStack.pop();
			} catch (EmptyStackException e) {
				return -1;
			}
			if (curStack.isEmpty()) {
				// if (stackSetIndex >= 1) {
				setStacks.remove(stackSetIndex);
				stackSetIndex--;
				// }
			}
		}
		return val;
	}

	/**
	 * @return true if stack not full.
	 */
	private boolean isfull() {
		return setStacks.get(stackSetIndex).capacity() >= stackCapacity;
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SetOfStacks stacks = new SetOfStacks();
		for (int i = 0; i < 15; i++) {
			stacks.push(i);
		}
		int result = -1;
		while ((result = stacks.pop()) != -1)
			System.out.println(result);
	}
}
