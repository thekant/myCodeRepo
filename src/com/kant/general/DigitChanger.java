package com.kant.general;

/**
 * 
 * @author shaskant
 *
 */
public class DigitChanger {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

	}

	/**
	 *  56445--->replace(5 by 6)--> 66446 
	 */
	static int changeDigit(int number, int from, int to) {
		int dig;
		int multiplier = 1;
		int result = 0;

		while (number > 0) {
			dig = number % 10;
			if (dig == from) {
				result = result + to * multiplier;
			} else
				result = result + dig * multiplier;

			number = number / 10;
			multiplier = 10 * multiplier;
		}

		return result;
	}
}
