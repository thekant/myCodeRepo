package com.kant.datastructure.stacks;

/**
 * http://www.geeksforgeeks.org/sort-a-stack-using-recursion/
 * 
 * 
 * algorithm to sort stack elements:
 * 
 * sortStack(stack S) if stack is not empty: temp = pop(S); sortStack(S);
 * sortedInsert(S, temp); Below algorithm is to insert element is sorted order:
 * 
 * sortedInsert(Stack S, element) if stack is empty OR element > top element
 * push(S, elem) else temp = pop(S) sortedInsert(S, element) push(S, temp)
 * 
 * @author shaskant
 *
 */
public class StackSortUsingRecurssion<T extends Comparable<T>> {

	/**
	 * 
	 */
	public StackSortUsingRecurssion() {
	}

	/**
	 * TESTED
	 * 
	 * @param stack
	 */
	public void sortStack(Stack<T> stack) {
		if (!stack.isEmpty()) {
			T temp = stack.pop();
			sortStack(stack);
			insertToSortedStack(stack, temp);
		}
	}

	/**
	 * TESTED
	 * 
	 * @param stack
	 * @param item
	 */
	private void insertToSortedStack(Stack<T> stack, T item) {
		if (stack.isEmpty() || (stack.peek().compareTo(item) > 0)) {
			stack.push(item);
		} else {
			T temp = stack.pop();
			insertToSortedStack(stack, item);
			stack.push(temp);

		}
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		StackSortUsingRecurssion<Integer> sortOP = new StackSortUsingRecurssion<>();
		Stack<Integer> stack = new StackListImplementation<>(false);
		stack.push(4);
		stack.push(3);
		stack.push(2);
		stack.push(1);
		stack.push(5);
		sortOP.sortStack(stack);

		while (!stack.isEmpty()) {
			System.out.println(stack.pop());
		}
	}
}
