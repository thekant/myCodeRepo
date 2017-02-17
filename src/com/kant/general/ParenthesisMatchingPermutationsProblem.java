/**
 * http://www.geeksforgeeks.org/print-all-combinations-of-balanced-parentheses/
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
		printPar(4);
	}

	/**
	 * 
	 */
	public static void printParanthesis(int open, int close, char[] str, int pos) {
		if (close == 0) {
			System.out.println(str);
		} else {
			if (open > 0) {
				str[pos] = '(';
				printParanthesis(open - 1, close, str, pos + 1);
			}

			/**
			 * number of open braces should be > number of close braces. Here
			 * values are decreasing so reverse.
			 */
			if (close > open) {
				str[pos] = ')';
				printParanthesis(open, close - 1, str, pos + 1);
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
