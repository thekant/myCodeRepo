/**
 * 
 */
package com.kant.datastructure.strings.patterns;

/**
 * @author shaskant
 *
 */
public class Trie {
	private TrieNode root;

	/**
	 * 
	 */
	public Trie() {
		root = new TrieNode();
	}

	public void insert(String key) {
		root.insert(key);
	}

	public boolean search(String word) {
		return root.search(word);
	}

	public void remove(String key) {
		root.removeKey(key);
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Trie trie = new Trie();
		trie.insert("welcome");
		trie.insert("welcome1");
		trie.insert("welcome12");
		trie.insert("welcome123");
		trie.insert("hello");
		trie.insert("wello");

		// trie.remove("welcome12");
		trie.remove("welcome123");

		System.out.println(trie.search("welcome"));
	}

}
