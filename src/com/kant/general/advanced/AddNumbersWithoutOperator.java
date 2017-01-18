/**
 * 
 */
package com.kant.general.advanced;

/**
 * http://www.geeksforgeeks.org/add-two-numbers-without-using-arithmetic-
 * operators/
 * 
 * @author shashi
 * 
 */
public class AddNumbersWithoutOperator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(noArithmaticAddition(759, 674));
		System.out.println(noArithmaticAdditionWay2(759, 674));
	}

	/**
	 * Bitwise emulation to add two numbers.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static int noArithmaticAddition(int a, int b) {
		if (b == 0)
			return a;
		int sum = a ^ b; // add without carrying
		int carry = (a & b) << 1; // carry forward
		return noArithmaticAddition(sum, carry); // recurse
	}

	/**
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static int noArithmaticAdditionWay2(int a, int b) {
		while (b > 0) {
			a++;
			b--;
		}
		return a;
	}

}
