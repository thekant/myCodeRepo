/**
 * 
 */
package com.kant.datastructure.strings.patterns;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author shashi
 * 
 */
public class TrieBasedDictionary {

	DTrieNode root;

	public TrieBasedDictionary() {
		root = new DTrieNode();
	}

	/**
	 * 
	 * @param items
	 */
	public void addWordsFromList(List<String> items) {
		for (String item : items) {
			String[] keyVal = item.split(":");
			root.addWord(keyVal[0], keyVal[1]);
		}
	}

	/**
	 * 
	 * @param text
	 * @return
	 */
	public String findWordMeaning(String text) {
		return root.findWordMeaning(text.toUpperCase());
	}

	/**
	 * 
	 * @param text
	 * @return
	 */
	public boolean hasPrefix(String text) {
		return root.isValidPrefix(text);
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		TrieBasedDictionary dictionary = new TrieBasedDictionary();
		List<String> words = new ArrayList<>();
		words.add("AGNOSTIC:someone who believes that it is impossible to know whether there is a God or not.");
		words.add("ALOOF:being distant in manner or feeling, unsympathetic");
		words.add("BONA FIDE:a gesture made in good faith ");
		words.add("CAVEAT:a warning or caution given");
		words.add("DESPICABLE:deserving of contempt");

		dictionary.addWordsFromList(words);
		System.out.println(dictionary.hasPrefix("BONA "));
		System.out.println(dictionary.findWordMeaning("BONA FIDE"));
	}

}

/**
 * 
 * @author shashi
 * 
 */
class DTrieNode {
	DTrieNode[] children;
	boolean isAWord;
	int countPrefixes; // number of prefixes via this node.
	String meaning; // meaning of word

	/**
	 * 
	 */
	public DTrieNode() {
		countPrefixes = 0;
		isAWord = false;
		children = new DTrieNode[255];
		Arrays.fill(children, null);
		meaning = null;
	}

	/**
	 * 
	 * @param input
	 * @param meaning
	 */
	public void addWord(String input, String meaning) {
		if (input.isEmpty()) {
			this.meaning = meaning;
			this.isAWord = true;
			return;
		}
		countPrefixes++;
		int first = input.charAt(0);
		if (children[first] == null) {
			children[first] = new DTrieNode();
		}
		children[first].addWord(input.substring(1), meaning);
	}

	/**
	 * Finds the meaning of for the word
	 * 
	 * @param key
	 * @return
	 */
	public String findWordMeaning(String key) {
		if (key != null) {
			if (key.isEmpty()) {
				if (isAWord) {
					return meaning;
				}
				System.out.println("word not found");
				return null;
			}
			int first = key.charAt(0);
			if (children[first] != null) {
				return children[first].findWordMeaning(key.substring(1));
			}
		}
		System.out.println("invalid word");
		return null;
	}

	/**
	 * if text is a prefix on trie.
	 * 
	 * @param text
	 * @return
	 */
	public boolean isValidPrefix(String text) {
		if (text != null) {
			if (text.isEmpty())
				return true;
			int first = text.charAt(0);
			if (children[first] != null) {
				/*
				 * if (text.length() == 1) { return true; }
				 */
				return children[first].isValidPrefix(text.substring(1));
			}
		}
		return false;
	}
}
