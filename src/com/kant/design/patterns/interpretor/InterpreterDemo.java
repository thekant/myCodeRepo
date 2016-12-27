/**
 * 
 */
package com.kant.design.patterns.interpretor;

import java.util.HashMap;
import java.util.Stack;

/**
 * an operand can be an {@link Expression} {@link Variable} {@link Number}
 * 
 * @author shaskant
 *
 */
interface Operand {
	double evaluate(HashMap<String, Integer> context);

	void traverse(int level);
}

/**
 * 
 * @author shaskant
 *
 */
class Expression implements Operand {
	private char m_operator;
	public Operand left, right;

	public Expression(char op) {
		m_operator = op;
	}

	public void traverse(int level) {
		left.traverse(level + 1);
		System.out.print("" + level + m_operator + level + " ");
		right.traverse(level + 1);
	}

	public double evaluate(HashMap<String, Integer> context) {
		double result = 0.;
		double a = left.evaluate(context);
		double b = right.evaluate(context);
		if (m_operator == '+')
			result = a + b;
		else if (m_operator == '-')
			result = a - b;
		else if (m_operator == '*')
			result = a * b;
		else if (m_operator == '/')
			result = a / b;
		return result;
	}
}

/**
 * 
 * @author shaskant
 *
 */
class Variable implements Operand {
	private String m_name;

	public Variable(String name) {
		m_name = name;
	}

	public void traverse(int level) {
		System.out.print(m_name + " ");
	}

	public double evaluate(HashMap<String, Integer> context) {
		return context.get(m_name);
	}
}

/**
 * 
 * @author shaskant
 *
 */
class Number implements Operand {
	private double m_value;

	public Number(double value) {
		m_value = value;
	}

	public void traverse(int level) {
		System.out.print(m_value + " ");
	}

	/**
	 * always a constant value
	 */
	public double evaluate(HashMap context) {
		return m_value;
	}
}

/**
 * Interpreter suggests modeling the domain with a recursive grammar. Each rule
 * in the grammar is either a 'composite' (a rule that references other rules)
 * or a terminal (a leaf node in a tree structure). Interpreter relies on the
 * recursive traversal of the Composite pattern to interpret the 'sentences' it
 * is asked to process
 * 
 * @author shaskant
 *
 */
public class InterpreterDemo {
	private static final String THE_SPACE = " ";

	/**
	 * 
	 **/
	public static boolean precedence(char a, char b) {
		String high = "*/", low = "+-";
		if (a == '(')
			return false;
		if (a == ')' && b == '(') {
			System.out.println(")-(");
			return false;
		}
		if (b == '(')
			return false;
		if (b == ')')
			return true;
		if (high.indexOf(a) > -1 && low.indexOf(b) > -1)
			return true;
		if (high.indexOf(a) > -1 && high.indexOf(b) > -1)
			return true;
		if (low.indexOf(a) > -1 && low.indexOf(b) > -1)
			return true;
		return false;
	}

	/**
	 * infix to postfix conversion.
	 **/
	public static String convert_to_postfix(String expr) {
		Stack<Character> op_stack = new Stack<Character>();
		StringBuffer out = new StringBuffer();
		String opers = "+-*/()";
		char top_sym = '+';
		boolean empty;
		String[] tokens = expr.split(THE_SPACE);

		for (int i = 0; i < tokens.length; i++)
			if (opers.indexOf(tokens[i].charAt(0)) == -1) {
				out.append(tokens[i]);
				out.append(THE_SPACE.charAt(0));// always add space , necessary
												// to tokenize
			} else {
				while (!(empty = op_stack.isEmpty())
						&& precedence(top_sym = op_stack.pop(),
								tokens[i].charAt(0))) {
					out.append(top_sym);
					out.append(THE_SPACE.charAt(0));// always add space ,
													// necessary to tokenize
				}
				if (!empty)
					op_stack.push(top_sym);
				if (empty || tokens[i].charAt(0) != ')')
					op_stack.push(tokens[i].charAt(0));
				else
					top_sym = op_stack.pop();
			}
		while (!op_stack.isEmpty()) {
			out.append(op_stack.pop());
			out.append(THE_SPACE.charAt(0));
		}
		return out.toString();
	}

	/**
	 * returns root of the postfix tree.
	 * 
	 */
	public static Operand build_syntax_tree(String tree) {
		Stack<Operand> stack = new Stack<Operand>();
		String opers = "+-*/"; // operators
		String[] tokens = tree.split(THE_SPACE);
		for (int i = 0; i < tokens.length; i++) {
			// If token is a number or variable
			if (opers.indexOf(tokens[i].charAt(0)) == -1) {
				Operand term = null;
				try {
					term = new Number(Double.parseDouble(tokens[i]));
				} catch (NumberFormatException ex) {
					term = new Variable(tokens[i]);
				}
				stack.push(term);
				// else if token is an operator
			} else {
				Expression expr = new Expression(tokens[i].charAt(0));
				expr.right = stack.pop();
				expr.left = stack.pop();
				stack.push(expr);
			}
		}
		return stack.pop();
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String infixExpression = "celsi * 9 / 5 + thirty";
		System.out.println(infixExpression);
		String postfix = convert_to_postfix(infixExpression);
		System.out.println(postfix);
		Operand expr = build_syntax_tree(postfix);
		expr.traverse(1);
		System.out.println();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("thirty", 30);
		for (int i = 0; i <= 100; i += 10) {
			map.put("celsi", i);
			System.out.println("C is " + i + ",  F is " + expr.evaluate(map));
		}
	}
}