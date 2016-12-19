/**
 * 
 */
package com.kant.datastructure.strings;

import java.util.Arrays;

/**
 * http://www.careercup.com/question?id=5389078581215232 Given two strings tell
 * if anagram of first is substring of another.<br/>
 * Keep a map of characters in array1 and keep checking if array2 has these
 * characters. main string : a b a c a b b and looking for a a b b c when 3rd a
 * is encountered we move index to second a and start from there.
 * 
 * Another idea is to keep a sorted linklist of string in comparison. Whenever a
 * new character is to be added remove last character from linklist and add this
 * new one.
 * 
 * Solution:
 * http://www.geeksforgeeks.org/anagram-substring-search-search-permutations/
 */
public class AnagramOfFirstSubstringOfOther {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(areAnagrams("netflix", "flixnet"));
		// tryMethod1("abcnetflixdef", "flixnet");
		modifiedRabinCarpSolution("abcnetflixdef", "flixnet");
	}

	/**
	 * O(N)
	 * 
	 * @param text
	 * @param pat
	 */
	private static void modifiedRabinCarpSolution(String text, String pat) {
		int[] countP = new int[256];
		int[] countWin = new int[256];

		int M = pat.length();
		int N = text.length();
		int i = 0;
		for (i = 0; i < M; i++)
			countP[pat.charAt(i) - (char) 0]++;
		for (i = 0; i < M; i++)
			countWin[text.charAt(i) - (char) 0]++;

		// sliding window concept from Rabin Karp Algorithm
		for (i = M; i < N; i++) {
			if (compareTwoArraysEquality(countP, countWin, 256)) {
				System.out.println(text.substring(i - M, i));
				return;
			}
			countWin[text.charAt(i) - (char) 0]++;
			countWin[text.charAt(i - M) - (char) 0]--;
		}

		if (compareTwoArraysEquality(countP, countWin, 256)) {
			System.out.println(text.substring(i - M, i));
		}
		return;

	}

	/**
	 * 
	 * @param A
	 * @param B
	 * @param size
	 * @return
	 */
	public static boolean compareTwoArraysEquality(int[] A, int[] B, int size) {
		for (int i = 0; i < size; i++) {
			if (A[i] != B[i])
				return false;
		}
		return true;
	}

	/**
	 * Brute Force O(mn) when using charsequence
	 * 
	 * @param text
	 * @param pat
	 * @return
	 */
	private static void tryMethod1(String text, String pat) {
		int patLen = pat.length();
		for (int i = 0; i <= text.length() - patLen; i++) {
			String subText = text.substring(i, patLen + i);
			if (areAnagrams(subText, pat)) {
				System.out.println(subText);
				return;
			}
		}
	}

	/**
	 * 
	 * @param src1
	 * @param src2
	 * @return
	 */
	public static boolean areAnagrams(String src1, String src2) {
		int[] countChar = new int[256];
		Arrays.fill(countChar, 0);
		if (src1.length() != src2.length())
			return false;
		int count = 0;
		while (count < src2.length()) {
			countChar[src1.charAt(count) - (char) 0]++;// credit
			countChar[src2.charAt(count) - (char) 0]--;// debit
			count++;
		}
		for (count = 0; count < countChar.length; count++) {
			if (countChar[count] != 0)
				return false;
		}

		return true;
	}

}
