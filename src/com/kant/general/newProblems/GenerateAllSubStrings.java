package com.kant.general.newProblems;

/**
 * 
 * @author shaskant
 *
 */
public class GenerateAllSubStrings {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String input = "abcab";

		printAllSubStrings(input);
	}

	/**
	 * @param input
	 */
	private static void printAllSubStrings(String input) {
		for (int i = 0; i < input.length(); i++) {
			for (int len = 1; len <= input.length() - i; len++) {
				String subs = input.substring(i, len + i);
				System.out.println(subs);
			}
		}
	}
}
