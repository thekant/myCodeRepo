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
	public void insert(String key, int index) {
		if (index == key.length()) {
			leaf = true;
			return;
		}
		if (!children.containsKey(key.charAt(index))) {
			children.put(key.charAt(index), new TrieNode());
		}
		children.get(key.charAt(index)).insert(key, index + 1);
	}

	/**
	 * 
	 * @param key
	 * @param index
	 * @return
	 */
	public boolean search(String key, int index) {
		if (index == key.length())
			return isLeaf();
		if (!children.containsKey(key.charAt(index)))
			return false;
		return children.get(key.charAt(index)).search(key, index + 1);
	}

	/**
	 * 
	 * @param key
	 * @param index
	 * @return true (if child returned true then parent has to act on this) <br/>
	 *         false (ignore)
	 */
	public boolean remove(String key, int index) {
		// base case
		if (index == key.length()) {
			// key is found
			if (isLeaf()) {
				// if no children then parent should remove entry
				if (children.size() == 0) {
					return true;
				}
				leaf = false;//unmark
			}
			return false;
		}
		
		//key is not found 
		if (!children.containsKey(key.charAt(index)))
			return false;

		if (children.get(key.charAt(index)).remove(key, index + 1)) {
			//remove entry
			children.remove(key.charAt(index));
			//if no child and is not marked as leaf for a substring of the key
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
