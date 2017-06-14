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
		// printPar(2);
		printParentheses(4);
	}

	/**
	 * 
	 * @param count
	 */
	public static void printPar(int count) {
		char[] str = new char[count * 2];
		printParanthesis(count, count, str, 0);
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
	 * Way better solution.
	 * 
	 * @param n
	 */
	public static void printParentheses(int n) {
		Validparentheses(n / 2, n / 2, "");
	}

	/**
	 * To print only valid paren­the­ses, make sure at any given point of time,
	 * close paren­the­ses count is not less that open paren­the­ses count
	 * because it means close paren­the­ses has been printed with its
	 * respec­tive open parentheses.
	 */
	public static void Validparentheses(int openP, int closeP, String string) {
		if (openP == 0 && closeP == 0) // mean all opening and closing in
										// string,
										// print it
			System.out.println(string);
		if (openP > closeP) // means closing parentheses is more than open ones
			return;
		if (openP > 0)
			Validparentheses(openP - 1, closeP, string + "("); // put ( and
																// reduce
																// the count by
																// 1
		if (closeP > 0)
			Validparentheses(openP, closeP - 1, string + ")"); // put ) and
																// reduce
																// the count by
																// 1
	}

}
