/**
 * 
 */
package com.kant.datastructure.suffix;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shaskant
 *
 */
public class TrieNode {
	boolean endOfWord = false;
	Map<Char, TrieNode> children = new HashMap<Char, TrieNode>();

	/**
	 * recursive
	 * 
	 * @param data
	 * @param index
	 */
	public void insertDataR(String data, int index) {
		if (index == data.length()) {
			endOfWord = true;
			return;
		}
		Char ch = new Char(data.charAt(index));
		if (!children.containsKey(ch)) {
			children.put(ch, new TrieNode());
		}
		children.get(ch).insertDataR(data, index + 1);
	}

	public boolean search(String word, int index) {
		if (index == word.length()) {
			return endOfWord;
		}

		Char ch = new Char(word.charAt(index));
		if (!children.containsKey(ch))
			return false;
		return children.get(ch).search(word, index + 1);
	}

	public static void main(String[] args) {
		TrieNode head = new TrieNode();
		head.insertDataR("welcome", 0);
		head.insertDataR("wello", 0);
		head.insertDataR("welcome1", 0);
		head.insertDataR("hello", 0);
		System.out.println(head.search("hello", 0));
	}

}

/**
 * 
 * @author shaskant
 *
 */
class Char {
	public char c;

	public Char(char c) {
		this.c = c;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Char))
			return false;
		return this.c == ((Char) obj).c;
	}

	@Override
	public int hashCode() {
		return (int) c;
	}
}