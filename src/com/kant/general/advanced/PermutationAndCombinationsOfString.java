/**
 * 
 */
package com.kant.general.advanced;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * more advanced version of combination problem
 * http://www.geeksforgeeks.org/print
 * -all-possible-combinations-of-r-elements-in-a-given-array-of-size-n/
 * 
 * http://www.mytechinterviews.com/combinations-of-a-string
 * 
 * @author shashi
 * 
 */
public class PermutationAndCombinationsOfString {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		permutation("aabc".toCharArray());
		// combinations("aabc".toCharArray());
		// printAllpermutationsAndCombinations("aabc".toCharArray());
	}

	/**
	 * dictionary to be implemented as trie.
	 */
	public static void getValidWordPermutations() {
		// Set getValidWordsPermutation(char[] in):
		// Set validWords = new HashSet();
		// //initially, used[] set to false and depth is 0
		// permuteWords(in, out, used, 0, validWords);
		// return validWords;
		//
		// permuteWords(char[] in, buffer out, bool[] used, int depth, Set
		// validWords):
		// // if the current prefix is not present in the dictionary, then do
		// not permute further with the rest of the unused letters
		// if !dict.hasPrefix(out): //or dict.hasPrefix(out[out.length()])
		// return
		//
		// if depth = in.length:
		// return
		//
		// for (int i = 0; i < in.length; i++):
		// if used[i] = true:
		// continue
		// used[i] = true
		// out.append(in[i])
		//
		// //if current prefix is a word, add to the set
		// if dict.isValidWord(out):
		// validWords.add(out)
		//
		// permuteWords(in, used, out, depth +1, validWords)
		//
		// out.length = out.length -1
		// used[i] = false
	}

	/**
	 * utility method.
	 * 
	 * @param in
	 */
	public static void printAllpermutationsAndCombinations(char[] in) {
		Map<Character, Integer> charMap = getCharMap(in);
		int[] count = new int[charMap.entrySet().size()];
		char[] theInput = new char[charMap.entrySet().size()];
		int index = 0;
		for (Entry<Character, Integer> mapEntry : charMap.entrySet()) {
			count[index] = mapEntry.getValue();
			theInput[index] = mapEntry.getKey();
			index++;
		}
		StringBuffer out = new StringBuffer();
		diffpermute(theInput, out, count, 0);
	}

	/**
	 * 
	 */
	private static void diffpermute(char[] in, StringBuffer out, int[] count,
			int depth) {
		if (depth == in.length)
			return;
		for (int i = 0; i < in.length; i++) {
			if (count[i] == 0)
				continue;
			count[i]--;
			out.append(in[i]);
			System.out.println(out);
			diffpermute(in, out, count, depth + 1);

			out.deleteCharAt(out.length() - 1);
			count[i]++;
		}
	}

	/**
	 * TreeMap will sort according to natural ordering of keys.
	 * 
	 * @param in
	 * @return
	 */
	private static Map<Character, Integer> getCharMap(char[] in) {
		Map<Character, Integer> charMap = new TreeMap<>();
		for (int i = 0; i < in.length; i++) {
			if (charMap.containsKey(in[i])) {
				charMap.put(in[i], charMap.get(in[i]).intValue() + 1);
			} else
				charMap.put(in[i], 1);
		}
		return charMap;
	}

	/**
	 * support for duplicate characters added.
	 * 
	 * https://www.youtube.com/watch?v=nYFd7VHKyWQ&t=1185s
	 * 
	 * @param in
	 */
	public static void permutation(char[] in) {
		/**
		 * use a charMap to store count of each character in string. and then
		 * build theINput with all unique chars in sorted order.
		 */
		Map<Character, Integer> charMap = getCharMap(in);
		int[] count = new int[charMap.entrySet().size()];
		char[] theInput = new char[charMap.entrySet().size()];
		int index = 0;
		for (Entry<Character, Integer> mapEntry : charMap.entrySet()) {
			count[index] = mapEntry.getValue();
			theInput[index] = mapEntry.getKey();
			index++;
		}
		// initially depth is 0
		StringBuffer out = new StringBuffer();
		permute(theInput, out, count, 0);
	}

	/**
	 * Backtracking algo.
	 */
	private static void permute(char[] in, StringBuffer out, int[] count,
			int depth) {
		if (depth == in.length) {
			System.out.println(out);
			return;
		}

		for (int i = 0; i < in.length; i++) {
			if (count[i] == 0)
				continue;
			// add one character
			count[i]--;
			out.append(in[i]);
			permute(in, out, count, depth + 1);

			// remove that character <- backtracking step
			out.deleteCharAt(out.length() - 1);
			count[i]++;
		}

	}

	/**
	 * Support for duplicate chars added.
	 * 
	 * @param in
	 */
	public static void combinations(char[] in) {
		Map<Character, Integer> charMap = getCharMap(in);
		int[] count = new int[charMap.entrySet().size()];
		char[] theInput = new char[charMap.entrySet().size()];
		int index = 0;
		for (Entry<Character, Integer> mapEntry : charMap.entrySet()) {
			count[index] = mapEntry.getValue();
			theInput[index] = mapEntry.getKey();
			index++;
		}

		StringBuffer out = new StringBuffer();
		combine(theInput, count, out, 0);
	}

	/**
	 * [ explanation] https://www.youtube.com/watch?v=xTNFs5KRV_g
	 * 
	 * @param in
	 * @param out
	 * @param start
	 */
	private static void combine(char[] in, int[] count, StringBuffer out,
			int start) {
		if (start == in.length)
			return;

		for (int i = start; i < in.length; i++) {
			if (count[i] == 0)
				continue;
			out.append(in[i]);
			System.out.println(out);
			count[i]--;
			combine(in, count, out, i);

			out.deleteCharAt(out.length() - 1);
			count[i]++;
		}
	}
}
