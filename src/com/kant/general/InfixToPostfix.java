package com.kant.general;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * infix to postfix conversion
 * 
 * @author shashi
 * 
 */
public class InfixToPostfix {

	public static void main(String[] args) {
		Map<Integer, Integer> myPrecedenceMap = new HashMap<Integer, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put((int) '(', 0); // set to the least precedence.
				put((int) '+', 1);
				put((int) '-', 1);
				put((int) '*', 2);
				put((int) '/', 2);
				put((int) '^', 3);
			}
		};

		System.out.println(InfixToPostfix.convert("(a*b+(c-e)/d)",
				myPrecedenceMap));
	}

	/**
	 * Infix to postfix conversion. Based on precedence map.
	 * 
	 * @param input
	 * @return
	 */
	public static String convert(String input, Map<Integer, Integer> precTable) {
		if (!isValid(input)) {
			System.err.println("Input string not in valid format");
			return null;
		}
		LinkedList<SCharacter> operatorstack = new LinkedList<SCharacter>();
		StringBuffer output = new StringBuffer();
		for (int i = 0; i < input.length(); i++) {
			SCharacter schar = new SCharacter(input.charAt(i), precTable);
			if (schar.isBlank())
				continue; // ignoring white spaces
			if (schar.isOperand())
				output.append(schar);
			else if (schar.isLeftBrace())
				operatorstack.push(schar);
			else if (schar.isOperator()) {
				if (operatorstack.isEmpty())
					operatorstack.push(schar);
				else {
					// as long as there are higher priority operators on stack
					// pop & print
					while (!operatorstack.isEmpty()
							&& operatorstack.peek().preceeds(schar)) {
						output.append(operatorstack.pop());
					}
					operatorstack.push(schar);
				}
			} else if (schar.isRightBrace()) {
				while (!operatorstack.isEmpty()
						&& !operatorstack.peek().isLeftBrace()) {
					output.append(operatorstack.pop());
				}
				if (operatorstack.peek().isLeftBrace())
					operatorstack.pop();
			}
		}
		return output.toString();
	}

	/**
	 * TODO
	 */
	public static boolean isValid(String input) {
		return true;
	}

}

/**
 * 
 * @author shaskant
 *
 */
class SCharacter {
	private char val;
	private int precedence = -1;

	/**
	 * 
	 * @param aVal
	 */
	public SCharacter(char aVal, Map<Integer, Integer> precedenceMap) {
		val = aVal;
		if (isOperator() || isLeftBrace()) {
			precedence = precedenceMap.get((int) val);
		}
	}

	public char getVal() {
		return val;
	}

	public int getPrecedence() {
		return precedence;
	}

	public boolean isOperand() {
		return Character.isLetterOrDigit(val);
	}

	public boolean isLeftBrace() {
		return val == '(';
	}

	public boolean isRightBrace() {
		return val == ')';
	}

	public boolean isOperator() {
		return (val == '+' || val == '-' || val == '*' || val == '/' || val == '^');
	}

	public boolean isBlank() {
		return val == ' ';
	}

	public boolean preceeds(SCharacter op2) {
		if (op2 == null || !op2.isOperator())
			return false;
		return this.precedence > op2.getPrecedence();
	}

	@Override
	public String toString() {
		// Character.toString(val);
		return String.valueOf(val);
	}

}