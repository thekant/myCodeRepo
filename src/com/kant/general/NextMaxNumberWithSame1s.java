/**
 * 
 */
package com.kant.general;

/**
 * Working solution
 * 
 * @author shaskant
 *
 */
public class NextMaxNumberWithSame1s {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int input = 12;
		printBits(input);
		findNextMax(input);
	}

	/**
	 * 
	 * @param input
	 */
	private static void findNextMax(int input) {
		int shift = 0;
		// find rightmost 1.
		while (shift <= 20) {
			if ((input & (1 << shift)) > 0) {
				break;
			}
			shift++;
		}

		int onesCount = 0;
		// find first 0 till then keep counting 1's
		while (shift <= 20) {
			if ((input & (1 << shift)) > 0) {
				input = input & ~(1 << shift);// unset all these 1's
				onesCount++;
			} else {
				// got 0 , set this bit to 1.
				input = input | (1 << shift);
				onesCount--;
				break;
			}
			shift++;
		}

		for (int x = 0; x < onesCount; x++) {
			input = input | (1 << x);
		}
		printBits(input);

	}

	/**
	 * 
	 * @param input
	 */
	private static void printBits(int input) {
		int shift = 20;
		System.out.print(input + " is [");
		while (shift >= 0) {
			if ((input & (1 << shift)) > 0) {
				System.out.print("1");
			} else
				System.out.print("0");
			shift--;
		}
		System.out.println("]");
	}
}
