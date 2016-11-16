/**
 * 
 */
package com.kant.general;

import java.util.Stack;

/**
 * @author shaskant
 *
 */
public class DivisorsOfNumber {

	/**
	 * brute O(N)
	 * 
	 * @param n
	 */
	public static void printNaturalDivisiors(int n) {
		for (int i = 1; i <= n; i++) {
			if (n % i == 0) {
				System.out.print(i + " ");
			}
		}
	}

	/**
	 * O(sqrt(N))
	 * 
	 * @param n
	 */
	public static void naturalDivisorOptimized1(int n) {
		for (int i = 1; i <= Math.sqrt(n); i++) {
			if (n % i == 0) {
				System.out.print(i + " ");
				if (n / i != i)
					System.out.print(n / i + " ");
			}
		}
	}

	/**
	 * Sorted output
	 * 
	 * @param n
	 */
	public static void naturalDivisorOptimizedSorted(int n) {
		Stack<Integer> stack = new Stack<>();
		for (int i = 1; i <= Math.sqrt(n); i++) {
			if (n % i == 0) {
				System.out.print(i + " "); // normally print first half
				if (n / i != i)
					stack.push(n / i); // store second half as it's stored in
										// decreasing order
			}
		}
		while (!stack.isEmpty()) {
			System.out.print(stack.pop() + " ");
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DivisorsOfNumber.printNaturalDivisiors(100);
		System.out.println();
		DivisorsOfNumber.naturalDivisorOptimizedSorted(100);
	}

}
