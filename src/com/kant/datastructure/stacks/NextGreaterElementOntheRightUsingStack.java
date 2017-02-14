/**
 * 
 */
package com.kant.datastructure.stacks;

/**
 * Time Complexity: O(n). The worst case occurs when all elements are sorted in
 * decreasing order. If elements are sorted in decreasing order, then every
 * element is processed at most 4 times.
 *  a) Initially pushed to the stack. <br/>
 *  b) Popped from the stack when next element is being processed.<br/> 
 *  c) Pushed back to the stack because next element is smaller. <br/>
 *  d) Popped from the stack in step 3 of algo.
 * 
 * @author shaskant
 *
 */
public class NextGreaterElementOntheRightUsingStack {

	Stack<Integer> stack;

	public NextGreaterElementOntheRightUsingStack() {
		stack = new StackListImplementation<Integer>(false);
	}

	public void printNextGreaterElement(int[] data) {
		stack.push(data[0]);
		for (int i = 1; i < data.length; i++) {
			if (!stack.isEmpty()) {
				Integer popVal = stack.pop();

				while (popVal.intValue() < data[i]) {
					System.out.println(popVal + "  NGE " + data[i]);
					if (stack.isEmpty())
						break;
					popVal = stack.pop();
				}
				if (popVal.intValue() > data[i])
					stack.push(popVal);
			}
			// push after making sure stack top element is greater or stack is
			// empty.
			stack.push(data[i]);
		}

		while (!stack.isEmpty()) {
			Integer popVal = stack.pop();
			System.out.println(popVal + "  NGE " + -1);
		}
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		NextGreaterElementOntheRightUsingStack nxgtElem = new NextGreaterElementOntheRightUsingStack();
		nxgtElem.printNextGreaterElement(new int[] { 11, 13, 21, 3 });
	}

}
