/**
 * 
 */
package com.kant.datastructure.stacks;

/**
 * TESTED working
 * 
 * @author shaskant
 *
 */
public class StackHoldingMin<T extends Comparable<T>> extends
		StackListImplementation<T> {

	private Stack<T> minStore = new StackListImplementation<>(false);

	public StackHoldingMin(boolean logEnabled) {
		super(logEnabled);
	}

	@Override
	public void push(T data) {
		if (minStore.isEmpty() || data.compareTo(minStore.peek()) < 0) {
			minStore.push(data);
		}
		super.push(data);
	}

	@Override
	public T pop() {
		T temp = super.pop();
		if (temp != null) {
			if (minStore.peek().compareTo(temp) == 0)
				minStore.pop();
		}
		return temp;
	}

	public T getMin() {
		return minStore.peek();
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		StackHoldingMin<Integer> stackMin = new StackHoldingMin<>(false);
		stackMin.push(1);
		stackMin.push(4);
		stackMin.push(3);
		stackMin.push(2);
		// stackMin.pop();
		// stackMin.pop();
		System.out.println(stackMin.getMin());
	}
}
