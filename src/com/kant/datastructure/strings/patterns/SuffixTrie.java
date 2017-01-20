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

	/**
	 * 
	 * @param txt
	 */
	public void insert(String txt) {
		System.out.println("indexing... \"" + txt + "\"");
		for (int i = 0; i < txt.length(); i++)
			root.insert(txt.substring(i), i);
		System.out.println("indexing complete");
	}

	/**
	 * 
	 * @param word
	 * @return
	 */
	public List<Integer> search(String word) {
		System.out.println("searching... \"" + word + "\"");
		return root.search(word);
	}

	public void printSuffixTrie() {
		root.traverse(0);
	}

	/**
	 * http://www.geeksforgeeks.org/count-distinct-substrings-string-using-
	 * suffix-trie/
	 * 
	 * @return
	 */
	public int countDistinctSubStrings() {
		System.out.print("\nDistinct number of nodes: ");
		return root.countNumberOfNodes();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String txt = "this is quite intemesting problem  em";
		SuffixTrie trie = new SuffixTrie();
		trie.insert(txt);

		List<Integer> result = trie.search("em");
		if (result != null) {
			System.out.print("Indexed at: ");
			Iterator<Integer> iterator = result.iterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next() + " ");
			}
		} else {
			System.out.println("text doesn't have the pattern");
		}

		System.out.println(trie.countDistinctSubStrings());
		// trie.printSuffixTrie();

		printAllSubstrings("ababa");
	}

	/**
	 * prints all substrings of a string.
	 * 
	 * @param txt
	 */
	public static void printAllSubstrings(String txt) {
		System.out.println("Printing all substrings of \"" + txt + "\":");
		for (int i = 0; i < txt.length(); i++) {
			for (int j = i+1; j < txt.length() + 1; j++) {
				// NOTE: charAt(j) is not printed
				System.out.print("\"" + txt.substring(i, j) + "\" ");
			}
		}
		System.out.println("\"\"");//print the empty substring
	}

}
