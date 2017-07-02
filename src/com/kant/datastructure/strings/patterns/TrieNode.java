/**
 * 
 */
package com.kant.datastructure.strings.patterns;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shaskant
 *
 */
public class TrieNode {
	protected Map<Character, TrieNode> children;
	private boolean leaf;

	/**
	 * 
	 */
	public TrieNode() {
		children = new HashMap<Character, TrieNode>();
	}

	/**
	 * 
	 * @param key
	 * @param index
	 */
	public void insert(String key) {
		if (key.isEmpty()) {
			leaf = true;
			return;
		}
		if (!children.containsKey(key.charAt(0))) {
			children.put(key.charAt(0), new TrieNode());
		}
		children.get(key.charAt(0)).insert(key.substring(1));
	}

	/**
	 * 
	 * @param key
	 * @param index
	 * @return
	 */
	public boolean search(String key) {
		if (key.isEmpty())
			return isLeaf();
		if (!children.containsKey(key.charAt(0)))
			return false;
		return children.get(key.charAt(0)).search(key.substring(1));
	}

	/**
	 * 
	 * @param key
	 * @param index
	 * @return true (if child returned true then parent has to act on this) <br/>
	 *         false (ignore)
	 */
	public boolean removeKey(String key) {
		// base case
		if (key.isEmpty()) {
			// key is found
			if (isLeaf()) {
				// if no children then parent should remove entry
				if (children.size() == 0) {
					return true;
				}
				leaf = false; // unmark
			}
			return false;
		}

		// key is not found
		if (!children.containsKey(key.charAt(0)))
			return false;

		if (children.get(key.charAt(0)).removeKey(key.substring(1))) {
			// remove entry
			children.remove(key.charAt(0));
			// if no child and is not marked as leaf for a substring of the key
			if (children.size() == 0) {
				if (!isLeaf())
					return true;
			}
		}
		return false;
	}

	public boolean isLeaf() {
		return leaf;
	}

}
