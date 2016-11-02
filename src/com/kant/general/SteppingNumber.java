/**
 * 
 */
package com.kant.general;

/**
 * http://www.geeksforgeeks.org/stepping-numbers/ Given two integers ‘n’ and
 * ‘m’, find all the stepping numbers in range [n, m]. A number is called
 * stepping number if all adjacent digits have an absolute difference of 1. 321
 * is a Stepping Number while 421 is not.
 * 
 * Examples:
 * 
 * Input : n = 0, m = 21 Output : 0 1 2 3 4 5 6 7 8 9 10 12 21
 * 
 * Input : n = 10, m = 15 Output : 10, 12
 * 
 * @author shaskant
 *
 */
public class SteppingNumber {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int input = 1236;

		if (isSteppingNumber(input))
			System.out.println(input + " is stepping Nuumber");
		else
			System.out.println("Not stepping number");
	}

	/**
	 * BruteForce approach
	 * 
	 * @param input
	 */
	private static boolean isSteppingNumber(int input) {
		int curDig = 0;
		int prevDig = -1;
		boolean flag = true;

		int n = input;
		while (n > 0) {
			curDig = n % 10;
			if (prevDig != -1) {
				if (Math.abs(curDig - prevDig) != 1) {
					flag = false;
					break;
				}
			}
			prevDig = curDig;
			n = n / 10;
		}
		return flag;
	}

}
