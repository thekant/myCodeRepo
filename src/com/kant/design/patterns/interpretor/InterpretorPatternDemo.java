/**
 * 
 */
package com.kant.design.patterns.interpretor;

import java.util.Stack;

/**
 * @author shaskant
 *
 */
public class InterpretorPatternDemo {

	/**
	 * <b>the Parser</b>
	 * 
	 * Algorithm for postfix expression parser implementation is simpler than
	 * for infix.
	 * 
	 * Read token one by one and loop till end of expression Is read element a
	 * number true then, push it to a stack false then, pop two elements from
	 * stack apply the operator push the result to stack
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String tokenString = "4 3 2 - 1 + *";
		Stack<IExpression> stack = new Stack<>();

		String[] tokenList = tokenString.split(" ");
		for (String s : tokenList) {
			if (isOperator(s)) {
				IExpression rightExpression = stack.pop();
				IExpression leftExpression = stack.pop();
				IExpression operator = getOperatorInstance(s, leftExpression,
						rightExpression);
				int result = operator.interpret();
				stack.push(new NumberExpression(result));
			} else {
				IExpression i = new NumberExpression(s);
				stack.push(i);
			}
		}
		System.out.println("Result: " + stack.pop().interpret());
	}

	public static boolean isOperator(String s) {
		if (s.equals("+") || s.equals("-") || s.equals("*"))
			return true;
		else
			return false;
	}

	public static IExpression getOperatorInstance(String s, IExpression left,
			IExpression right) {
		switch (s) {
		case "+":
			return new PlusExpression(left, right);
		case "-":
			return new MinusExpression(left, right);
		case "*":
			return new MultiplyExpression(left, right);
		}
		return null;
	}

}

interface IExpression {
	public int interpret();
}

class NumberExpression implements IExpression {
	int number;

	public NumberExpression(int i) {
		number = i;
	}

	public NumberExpression(String s) {
		number = Integer.parseInt(s);
	}

	@Override
	public int interpret() {
		return number;
	}

}

class PlusExpression implements IExpression {
	IExpression leftExpression;
	IExpression rightExpresion;

	public PlusExpression(IExpression leftExpression, IExpression rightExpresion) {
		this.leftExpression = leftExpression;
		this.rightExpresion = rightExpresion;
	}

	@Override
	public int interpret() {
		return leftExpression.interpret() + rightExpresion.interpret();
	}

}

class MinusExpression implements IExpression {

	IExpression leftExpression;
	IExpression rightExpresion;

	public MinusExpression(IExpression leftExpression,
			IExpression rightExpresion) {
		this.leftExpression = leftExpression;
		this.rightExpresion = rightExpresion;
	}

	@Override
	public int interpret() {

		return leftExpression.interpret() - rightExpresion.interpret();
	}

}

class MultiplyExpression implements IExpression {

	IExpression leftExpression;
	IExpression rightExpresion;

	public MultiplyExpression(IExpression leftExpression,
			IExpression rightExpresion) {
		this.leftExpression = leftExpression;
		this.rightExpresion = rightExpresion;
	}

	@Override
	public int interpret() {

		return leftExpression.interpret() * rightExpresion.interpret();
	}

}
