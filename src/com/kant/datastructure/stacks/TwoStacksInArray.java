/**
 * 
 */
package com.kant.datastructure.stacks;

/**
 * http://www.geeksforgeeks.org/implement-two-stacks-in-an-array/ <br/>
 * TESTED
 * 
 * @author shaskant
 *
 */
public class TwoStacksInArray {

	private int[] stacksData;
	private int sizeD;
	private int topILeft = -1;
	private int topIRight = -1;

	/**
	 * 
	 */
	public TwoStacksInArray(int size) {
		sizeD = size;
		stacksData = new int[sizeD];
		topIRight = sizeD;
	}

	public boolean isEmpty() {
		return (topIRight - topILeft - 1) > 0;
	}

	public void push1(int data) {
		if (isEmpty()) {
			topILeft++;
			stacksData[topILeft] = data;
		}
	}

	public void push2(int data) {
		if (isEmpty()) {
			topIRight--;
			stacksData[topIRight] = data;
		}
	}

	public int pop1() {
		if (topILeft != -1) {
			int temp = stacksData[topILeft];
			topILeft--;
			return temp;
		}
		return -1;
	}

	public int pop2() {
		if (topIRight != sizeD) {
			int temp = stacksData[topIRight];
			topIRight++;
			return temp;
		}
		return -1;
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		TwoStacksInArray stacks = new TwoStacksInArray(6);
		stacks.push1(1);
		stacks.push1(2);
		stacks.push1(3);
		stacks.push2(4);
		stacks.push2(5);
		stacks.push2(6);
		System.out.println(stacks.pop1());
		System.out.println(stacks.pop2());
		System.out.println(stacks.pop1());
		System.out.println(stacks.pop2());
		System.out.println(stacks.pop1());
		System.out.println(stacks.pop2());
	}
}
