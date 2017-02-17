/**
 * 
 */
package com.kant.algorithms.dynamicprogramming;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * A first Simple and then second Dynamic Programming based methods to test
 * whether a given string can be segmented into space separated words in
 * dictionary.
 * 
 * @author shashi
 */
public class WordBreakProblem {
	/**
	 * Holds the dictionary
	 */
	private Set<String> dict;

	/**
	 * 
	 * @param dictionary
	 */
	public WordBreakProblem(String[] dictionary) {
		dict = new HashSet<>();
		setUpDictionaryOfWords(dictionary);
	}

	/**
	 * 
	 * @param dictionary
	 */
	private void setUpDictionaryOfWords(String[] dictionary) {
		for (String item : dictionary) {
			dict.add(item);
		}
	}

	/**
	 * Recursive and simple solution .<br/>
	 * Add memory for a substring that's already calculated [DP solution]
	 * 
	 * function(item,int POS)
	 * Only variable is POS.
	 * 
	 * store[POS] = function(item,POS);
	 * 
	 * 
	 * @param item
	 * @return
	 */
	public boolean wordBreakSolve(String item) {
		if (item.length() == 0)
			return true;
		for (int i = 1; i <= item.length(); i++) {
			// if current substring is in dictionary
			if (this.containsWord(item.substring(0, i))) {
				System.out.print(item.substring(0, i) + " ");
				if (wordBreakSolve(item.substring(i))) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Checks up into HashSet "dict" if the string/word is present or not.
	 * 
	 * @param item
	 * @return true or false.
	 */
	private boolean containsWord(String item) {
		return dict.contains(item);
	}

	/**
	 * Returns true if string can be segmented into space separated words,
	 * otherwise returns false.
	 * 
	 * @param str
	 * @return
	 */
	public boolean wordBreakDpSolution(String str) {
		int size = str.length();
		if (size == 0)
			return true;

		boolean[] wb = new boolean[size + 1];
		Arrays.fill(wb, false);

		for (int i = 1; i <= size; i++) {
			if (wb[i] == false && this.containsWord(str.substring(0, i))) {
				wb[i] = true;
			}
			if (wb[i] == true) {
				// if wb[full length of text] == true then return true.
				if (i == size)
					return true;
				// for picked up prefix , check if remaining part of text forms
				// the sentence
				for (int j = i + 1; j <= size; j++) {
					if (wb[j] == false
							&& this.containsWord(str.substring(i, j))) {
						wb[j] = true;
					}
					// if wb[full length of text] == true then return true.
					if (j == size && wb[j] == true) {
						return true;
					}
				}
			}
		}

		// If we have tried all prefixes and none of them worked
		return false;
	}

	/**
	 * To print only
	 * 
	 * @param word
	 */
	public void wordBreakRecursivePrint(String word) {
		wordBreakPrintUtil(word, "");
	}

	/**
	 * 
	 * @param word
	 * @param result
	 * @return
	 */
	private void wordBreakPrintUtil(String word, String result) {
		if (word.length() == 0) {
			System.out.println(result);
			return;
		}
		for (int i = 1; i <= word.length(); i++) {
			String prefix = word.substring(0, i);
			if (containsWord(prefix)) {
				wordBreakPrintUtil(word.substring(i), result + prefix + " ");
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] dictionary = { "mobile", "samsung", "sam", "sung", "man",
				"mango", "icecream", "and", "go", "i", "hate", "ice", "cream" };

		WordBreakProblem prob = new WordBreakProblem(dictionary);
		prob.wordBreakRecursivePrint("ihatesamsung");
	}

}
