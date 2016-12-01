/**
 * 
 */
package com.kant.datastructure.strings.patterns;

import java.util.Iterator;
import java.util.List;

/**
 * A Suffix trie implementation see {@link SuffixTrieNode} for details
 * 
 * @author shaskant
 *
 */
public class SuffixTrie {
	private SuffixTrieNode root;

	/**
	 * 
	 */
	public SuffixTrie() {
		root = new SuffixTrieNode();
	}

	public void insert(String txt) {
		for (int i = 0; i < txt.length(); i++)
			root.insert(txt.substring(i), i);
	}

	public List<Integer> search(String word) {
		return root.search(word);
	}

	public void printSuffixTrie() {
		root.traverse(0);
	}

	/**
	 * [TO PROOF]
	 * http://www.geeksforgeeks.org/count-distinct-substrings-string-using-
	 * suffix-trie/
	 * 
	 * @return
	 */
	public int countDistinctSubStrings() {
		return root.countNumberOfNodes();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String txt = "ababa";
		SuffixTrie trie = new SuffixTrie();
		trie.insert(txt);

		List<Integer> result = trie.search("ee");
		if (result != null) {
			Iterator<Integer> iterator = result.iterator();
			while (iterator.hasNext()) {
				System.out.println(iterator.next());
			}
		} else {
			System.out.println("text doesn't have the pattern");
		}

		System.out.println(trie.countDistinctSubStrings());
		// trie.printSuffixTrie();
		printAllSubstrings("ababa");
	}

	/**
	 * prints all substrings of a string
	 * 
	 * @param txt
	 */
	public static void printAllSubstrings(String txt) {
		for (int i = 0; i < txt.length(); i++) {
			for (int j = i; j < txt.length() + 1; j++) { // start from i+1 to
															// get rid off
															// (empty) ""
															// string outputs
				System.out.print("\"" + txt.substring(i, j) + "\" ");
			}
		}
		System.out.println();
	}

}
