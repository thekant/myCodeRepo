/**
 * 
 */
package com.kant.general.advanced;

/**
 * @author shashi
 * 
 */
public class ReverseWordsInString {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		char[] test = "< this is great to have it    >".toCharArray();
		reverseWords(test);
		System.out.println(test);
	}

	/**
	 * 
	 * @param input
	 */
	public static void reverseWords(char[] input) {
		int startIndex = -1;
		int index = 0;
		// revert every single word in this input
		while (index < input.length) {
			if (startIndex == -1 && input[index] != ' ') {
				startIndex = index;
			}
			if (startIndex != -1
					&& (index + 1 == input.length || input[index + 1] == ' ')) {
				reverseArray(input, startIndex, index);
				startIndex = -1;
			}
			index++;
		}

		// revert the whole array
		reverseArray(input, 0, input.length - 1);
	}

	/**
	 * 
	 * @param input
	 * @param start
	 * @param end
	 */
	public static void reverseArray(char[] input, int start, int end) {
		while (start < end) {
			char temp = input[start];
			input[start] = input[end];
			input[end] = temp;
			start++;
			end--;
		}
	}
}
