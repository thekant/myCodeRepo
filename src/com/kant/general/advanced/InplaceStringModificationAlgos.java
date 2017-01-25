/**
 * 
 */
package com.kant.general.advanced;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author shashi
 * 
 */
public class InplaceStringModificationAlgos {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		char[] input = "this is abcgreat ododabcabc".toCharArray();
		char[] pattern = "abc".toCharArray();
		// stringReplacement(input, pattern);
		// removeDuplicates(input);

		char[] input2 = "aaaacccbdfgheee".toCharArray();
		addRepeatitionAsNumber(input2);

		char[] input3 = "abcdefgh".toCharArray();
		shuffleSubString(input3, 3, 5);
	}

	/**
	 * TODO shuffle inplace
	 */
	public static void shuffleSubString(char[] input3, int subStart, int subEnd) {
		StringBuilder sb = new StringBuilder();
		for (int i = subStart; i <= subEnd && i < input3.length; i++) {
			sb.append(input3[i]);
		}

		String shuffled = shuffleString(sb.toString());
		int index = 0;
		for (int i = subStart; i <= subEnd && i < input3.length; i++) {
			input3[i] = shuffled.charAt(index++);
		}
		System.out.println(input3);
	}

	/**
	 * shuffle a string
	 * 
	 * @param word
	 * @return
	 */
	public static String shuffleString(String word) {
		List<Character> characters = new ArrayList<Character>();
		for (char c : word.toCharArray()) {
			characters.add(c);
		}
		Collections.shuffle(characters);
		StringBuilder sb = new StringBuilder();
		for (char c : characters) {
			sb.append(c);
		}
		return sb.toString();
	}

	/**
	 * 
	 * @param s
	 * @return
	 */
	public static String shuffleWordsInSentance(String s) {
		List<String> words = Arrays.asList(s.split(" "));
		Collections.shuffle(words);
		StringBuilder sb = new StringBuilder();
		for (String w : words) {
			// w = shuffleString(w);
			sb.append(w);
			sb.append(" ");
		}
		return sb.toString().trim();
	}

	/**
	 * 
	 * @param input
	 */
	public static void addRepeatitionAsNumber(char[] input) {
		int resultIndex = 1;
		int curIndex = 1;// start from 1
		int count = 0;
		while (curIndex < input.length) {
			count = 0;
			while (curIndex < input.length
					&& input[curIndex] == input[curIndex - 1]) {
				curIndex++;
				count++;
			}
			if (count > 0) {
				input[resultIndex++] = (char) (count + '0');
			}
			if (curIndex < input.length) {
				input[resultIndex++] = input[curIndex++];
			}
		}

		for (curIndex = resultIndex; curIndex < input.length; curIndex++)
			input[curIndex] = ' ';

		System.out.println(input);

	}

	/**
	 * In place algo. for removing duplicates.
	 * 
	 * @param input
	 */
	public static void removeDuplicates(char[] input) {
		int resultIndex = 0;
		int curIndex = 0;
		boolean[] charMap = new boolean[256];
		Arrays.fill(charMap, false);

		for (; curIndex < input.length; curIndex++) {
			// if not repeated char then store
			if (!charMap[input[curIndex]]) {
				input[resultIndex] = input[curIndex];
				charMap[input[curIndex]] = true;
				resultIndex++;
			}
		}

		for (curIndex = resultIndex; curIndex < input.length; curIndex++)
			input[curIndex] = ' ';
		System.out.println(input);
	}

	/**
	 * replaces pattern in input with X.
	 * 
	 * @param input
	 * @param pattern
	 */
	public static void stringReplacement(char[] input, char[] pattern) {
		int resultIndex = 0;
		int curIndex = 0;
		int plen = pattern.length;
		boolean match;
		// reduces adjacent matches of pattern
		while (curIndex < input.length) {
			match = false;
			// match if pattern is here
			while (isMatch(input, curIndex, pattern)) {
				match = true;
				curIndex += plen;
			}

			if (match) {
				input[resultIndex++] = 'X';
			}
			if (curIndex < input.length)
				input[resultIndex++] = input[curIndex++];
		}

		for (curIndex = resultIndex; curIndex < input.length; curIndex++)
			input[curIndex] = ' ';
		System.out.println(input);

	}

	/**
	 * 
	 * finds if from startIndex input matches up with pattern.
	 * 
	 * @param input
	 * @param startIndex
	 * @param pattern
	 * @return
	 */
	public static boolean isMatch(char[] input, int startIndex, char[] pattern) {
		int cur = startIndex;
		int pat = 0;
		while (cur < input.length && pat < pattern.length) {
			if (input[cur++] != pattern[pat++]) {
				return false;
			}
		}
		if (cur - startIndex != pattern.length) {
			return false;
		}
		return true;
	}

}
