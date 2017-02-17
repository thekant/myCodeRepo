/**
 * 
 */
package com.kant.algorithms.dynamicprogramming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * http://www.geeksforgeeks.org/find-all-possible-outcomes-of-a-given-expression
 * http://www.geeksforgeeks.org/all-ways-to-add-parenthesis-for-evaluation/
 * 
 * @author shaskant
 *
 */
public class EvaluateAllParanthesis {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EvaluateAllParanthesis problem = new EvaluateAllParanthesis();
		String input = "5*4-3*2"; // "1*2+3*4";// "5*4-3*2";
		List<Integer> res = problem.possibleResults(input);

		for (int i = 0; i < res.size(); i++)
			System.out.println(res.get(i));
	}

	public List<Integer> possibleResults(String input) {
		Map<String, List<Integer>> store = new HashMap<String, List<Integer>>();
		return possibleResultUtil(input, store);
	}

	/**
	 * 1) Initialize result 'res' as empty. <br/>
	 * 2) Do following for every operator 'x'. <br/>
	 * a) Recursively evaluate all possible values on left of 'x'. Let the list
	 * of values be 'l'. <br/>
	 * a) Recursively evaluate all possible values on right of 'x'. Let the list
	 * of values be 'r'. <br/>
	 * c) Loop through all values in list 'l' loop through all values in list
	 * 'r' Apply current operator 'x' on current items of 'l' and 'r' and add
	 * the evaluated value to 'res' <br/>
	 * 3) Return 'res'.
	 * 
	 * @param input
	 * @param store
	 * @return
	 */
	private List<Integer> possibleResultUtil(String input,
			Map<String, List<Integer>> store) {
		List<Integer> result = new ArrayList<Integer>();
		// already evaluated.
		if (store.containsKey(input)) {
			return store.get(input);
		}

		// Base cases
		if (input.length() == 1) {
			result.add(input.charAt(0) - '0');
			store.put(input, result);
			return result;
		} else if (input.length() == 3) {
			int num = eval(input.charAt(0) - '0', input.charAt(1),
					input.charAt(2) - '0');

			result.add(num);
			store.put(input, result);
			return result;
		}

		for (int i = 0; i < input.length(); i++) {
			if (isOperator(input.charAt(i))) {
				/**
				 * If character is operator then split and calculate recursively
				 */
				List<Integer> resLeft = possibleResultUtil(
						input.substring(0, i), store);
				List<Integer> resRight = possibleResultUtil(
						input.substring(i + 1), store);

				// Combine all possible combination
				for (int j = 0; j < resLeft.size(); j++) {
					for (int k = 0; k < resRight.size(); k++) {
						result.add(eval(resLeft.get(j), input.charAt(i),
								resRight.get(k)));
					}
				}
				store.put(input, result);
			}
		}
		return result;
	}

	private boolean isOperator(char c) {
		return c == '+' || c == '-' || c == '*' || c == '/';
	}

	/**
	 * Utility function to evaluate a simple expression with one operator only.
	 **/
	private int eval(int a, char op, int b) {
		if (op == '+')
			return a + b;
		if (op == '-')
			return a - b;
		if (op == '*')
			return a * b;
		else
			return a / b;
	}

}
