package com.kant.general.advanced;

import java.util.Arrays;

/**
 * Prints next higher palindrome.
 * 
 * @author shashi
 * 
 */
public class NextHigherPalindrome {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// int num[] = { };
		int num[] = toDigitArray("2133");// "94187978322","99999","808"
		NextHigherPalindrome solver = new NextHigherPalindrome();

		int[] result = solver.nextHigherPalindrome(num);
		printData(result);
	}

	/**
	 * 
	 * @param data
	 * @return
	 */
	public int[] nextHigherPalindrome(int[] data) {
		int[] result = null;

		// result will be 1000....0001
		if (allNinersCase(data)) {
			result = new int[data.length + 1];
			result[0] = 1;
			for (int index = 1; index < data.length; index++) {
				result[index] = 0;
			}
			result[data.length] = 1;
			return result;
		}

		result = nextHigherPalindromeUtil(data);
		return result;
	}

	/**
	 * Main utility
	 * 
	 * @param data
	 * @return
	 */
	private int[] nextHigherPalindromeUtil(int[] data) {
		int[] result = Arrays.copyOf(data, data.length);
		int n = result.length;
		int mid = n / 2;
		int left = mid - 1;
		// if odd size leave middle guy at center.
		int right = (n % 2 == 1) ? mid + 1 : mid;
		boolean palindromeOrLeftSmaller = false;

		// move aside as long as things are same
		while (left >= 0 && result[left] == result[right]) {
			left--;
			right++;
		}

		/**
		 * If number is already a palindrome or left side digit is smaller than
		 * right side , then mirroring left onto right will lead to smaller
		 * element. so middle element has to be incremented. <br/>
		 * Note: left < 0 means it's palindrome
		 **/
		if (left < 0 || result[left] < result[right])
			palindromeOrLeftSmaller = true;

		/**
		 *  mirror the remaining i.e make it palindrome
		 */
		while (left >= 0) {
			result[right++] = result[left--];
		}

		if (palindromeOrLeftSmaller) {
			int carry = 1;
			left = mid - 1;
			// update mid and take carry over for next phase
			if (n % 2 == 1) {
				result[mid] += carry;
				carry = result[mid] / 10;
				result[mid] %= 10;
				right = mid + 1;
			} else
				right = mid;

			// just adding 1 from left side of mid
			while (left >= 0) {
				result[left] += carry;
				carry = result[left] / 10;
				result[left] %= 10;

				result[right++] = result[left--];
			}
		}

		return result;
	}

	/**
	 * 
	 * @param data
	 * @return
	 */
	private boolean allNinersCase(int[] data) {
		if (data == null)
			return false;
		for (int num : data) {
			if (num != 9)
				return false;
		}
		return true;
	}

	/**
	 * 
	 * @param data
	 */
	public static void printData(int[] data) {
		if (data == null)
			return;
		System.out.println();
		for (int num : data) {
			System.out.print(num);
		}
	}

	public static int[] toDigitArray(String input) {
		int[] result = new int[input.length()];
		for (int i = 0; i < input.length(); i++)
			result[i] = input.charAt(i) - '0';
		return result;
	}
}
