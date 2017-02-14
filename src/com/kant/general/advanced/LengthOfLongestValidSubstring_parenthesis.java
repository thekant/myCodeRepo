/**
 * 
 */
package com.kant.general.advanced;

import java.util.Stack;

/**
 * http://www.geeksforgeeks.org/length-of-the-longest-valid-substring/
 * 
 * @author shaskant
 *
 */
public class LengthOfLongestValidSubstring_parenthesis {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String str = "((()()";
		System.out.println(findMaxLen(str));
	    str = "()(()))))";
	    System.out.println(findMaxLen(str));
	}

	public static int findMaxLen(String str) {
		int n = str.length();

		// Create a stack and push -1 as initial index to it.
		Stack<Integer> stk = new Stack<>();
		stk.push(-1);

		// Initialize result
		int result = 0;

		// Traverse all characters of given string
		for (int i = 0; i < n; i++) {
			// If opening bracket, push index of it
			if (str.charAt(i) == '(')
				stk.push(i);

			else // If closing bracket, i.e.,str[i] = ')'
			{
				// Pop the previous opening bracket's index
				stk.pop();

				// Check if this length formed with base of
				// current valid substring is more than max
				// so far
				if (!stk.empty())
					result = Math.max(result, i - stk.peek());

				// If stack is empty. push current index as
				// base for next valid substring (if any)
				else
					stk.push(i);
			}
		}

		return result;
	}
}
