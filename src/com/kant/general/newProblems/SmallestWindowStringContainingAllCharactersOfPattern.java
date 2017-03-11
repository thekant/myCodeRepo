package com.kant.general.newProblems;

/**
 * 
 * @author shaskant
 *
 */
public class SmallestWindowStringContainingAllCharactersOfPattern {

	private static final int MAX_CHAR = 256;
	private static int[] patternCharFrequency = new int[MAX_CHAR];
	private static int[] textCharFrequency = new int[MAX_CHAR];

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String text = "this is a test string";
		String pat = "a is";
		System.out.println(findMinWindow(text, pat));
	}

	/**
	 * text hash should have same or more number of characters from pattern
	 * hash.
	 * 
	 * @return
	 */
	private static boolean containsAllChar() {
		for (int i = 0; i < MAX_CHAR; ++i) {
			if (textCharFrequency[i] < patternCharFrequency[i])
				return false;
		}
		return true;
	}

	/**
	 * 
	 * @param text
	 * @param pattern
	 * @return
	 */
	public static int findMinWindow(String text, String pattern) {
		if (text == null || pattern == null)
			return 0;
		if (pattern.isEmpty())
			return 0;

		int text_length = text.length();
		int pattern_length = pattern.length();
		if (text_length < pattern_length)
			return 0;

		for (int i = 0; i < pattern_length; ++i) {
			patternCharFrequency[pattern.charAt(i)] += 1;
		}

		int min_length = Integer.MAX_VALUE;
		int start = 0;
		for (int i = 0; i < text_length; ++i) {
			if (patternCharFrequency[text.charAt(i)] != 0) {
				textCharFrequency[text.charAt(i)] += 1;
				/**
				 * if pattern has the current text character and current text
				 * window contains all characters from pattern.
				 */
				if (containsAllChar()) {
					/**
					 * remove from begining of window , the useless characters.
					 */
					while (containsAllChar() && (start < i)) {
						if (patternCharFrequency[text.charAt(start)] != 0)
							textCharFrequency[text.charAt(start)] -= 1;
						start++;
					}
					--start;
					textCharFrequency[text.charAt(start)] += 1;
					min_length = Math.min(min_length, i - start + 1);
				}
			}
		}
		return min_length;
	}
}
