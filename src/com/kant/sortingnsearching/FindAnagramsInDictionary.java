/**
 * 
 */
package com.kant.sortingnsearching;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shashi
 * 
 */
public class FindAnagramsInDictionary {

	private Map<Integer, List<String>> dictionary;
	private final int[] primes = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37,
			41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101 };

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// now one can find anagrams of a word in dictionary.
	}

	/**
	 * 
	 * @param words
	 */
	public FindAnagramsInDictionary(String[] words) {
		this.dictionary = loadDictionary(words);
	}

	/**
	 * only supported a-z
	 * 
	 * @param input
	 * @return
	 */
	private int getHashCode(String input) {
		input = input.trim().toLowerCase();
		int hash = 31;
		for (int i = 0; i < input.length(); i++) {
			hash = hash * primes[input.charAt(i) - 'a'];// anagrams will
														// multiply to same
														// value
		}
		return hash;
	}

	/**
	 * 
	 * @param words
	 * @return
	 */
	private Map<Integer, List<String>> loadDictionary(String[] words) {
		Map<Integer, List<String>> dictionary = new HashMap<>();
		for (String item : words) {
			if (!dictionary.containsKey(getHashCode(item))) {
				dictionary.put(getHashCode(item), new ArrayList<String>());
			}
			dictionary.get(getHashCode(item)).add(item);
		}
		return dictionary;
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	public int findNumberOfAnagrams(String str) {
		List<String> anagrams = dictionary.get(getHashCode(str));
		return anagrams.size();
	}

}
