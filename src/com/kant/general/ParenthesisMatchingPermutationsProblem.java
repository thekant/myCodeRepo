/**
 * 
 */
package com.kant.general;

/**
 * @author shashi
 * 
 */
public class ParenthesisMatchingPermutationsProblem {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		printPar(3);
	}

	/**
	 * 
	 * @param left
	 * @param right
	 * @param str
	 * @param count
	 */
	public static void printParanthesis(int left, int right, char[] str,
			int count) {
		if (left < 0 || right < left)
			return; // invalid state
		if (left == 0 && right == 0) {
			System.out.println(str); // found one, so print it
		} else {
			if (left > 0) { // try a left paren, if there are some available
				str[count] = '(';
				printParanthesis(left - 1, right, str, count + 1);
			}

			if (right > left) { // try a right paren, if there's a matching left
				str[count] = ')';
				printParanthesis(left, right - 1, str, count + 1);
			}
		}
	}

	/**
	 * 
	 * @param count
	 */
	public static void printPar(int count) {
		char[] str = new char[count * 2];
		printParanthesis(count, count, str, 0);
	}

}
