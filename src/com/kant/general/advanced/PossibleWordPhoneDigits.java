/**
 * 
 */
package com.kant.general.advanced;

/**
 * Given a keypad as shown in diagram, and a n digit number, list all words
 * which are possible by pressing these numbers.
 * 
 * @author shashi
 * 
 */
public class PossibleWordPhoneDigits {
	private String[] characterTable;
	public static long counter = 0;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PossibleWordPhoneDigits generator = new PossibleWordPhoneDigits();
		generator.printWordsForInput("23");
		System.out.println(PossibleWordPhoneDigits.counter);
	}

	/**
	 * 
	 */
	public PossibleWordPhoneDigits() {
		characterTable = new String[10];
		characterTable[0] = "+";
		characterTable[1] = "";
		characterTable[2] = "ABC";
		characterTable[3] = "DEF";
		characterTable[4] = "GHI";
		characterTable[5] = "JKL";
		characterTable[6] = "MNO";
		characterTable[7] = "PQRS";
		characterTable[8] = "TUV";
		characterTable[9] = "WXYZ";
	}

	/**
	 * Utility method for user
	 * 
	 * @param input
	 */
	public void printWordsForInput(String input) {
		generateWord(input, new StringBuffer(), 0);
	}

	/**
	 * DFS way of moving down.[backtracking algorithm]
	 * 
	 * @param input
	 * @param output
	 * @param n
	 */
	private void generateWord(String input, StringBuffer output, int n) {
		if (n == input.length()) {
			counter++;
			System.out.println(output);
			return;
		}

		int digit = input.charAt(n) - '0';
		for (int secCount = 0; secCount < characterTable[digit].length(); secCount++) {
			// add the character
			output = output.append(characterTable[digit].charAt(secCount));
			// generate those numbers
			generateWord(input, output, n + 1);
			// remove the character
			output.deleteCharAt(output.length() - 1);
		}
	}

}
