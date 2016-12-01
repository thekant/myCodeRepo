/**
 * 
 */
package com.kant.datastructure.strings.patterns;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * If all characters of pattern have been processed, i.e., there is a path from
 * root for characters of the given pattern, then print print all indexes where
 * pattern is present. <br/>
 * To store indexes, use a list with every node that stores indexes of suffixes
 * starting at the node.
 * 
 * @author shaskant
 *
 */
public class SuffixTrieNode {
	private Map<Character, SuffixTrieNode> children;
	private List<Integer> indexes;

	/**
	 * 
	 */
	public SuffixTrieNode() {
		children = new HashMap<Character, SuffixTrieNode>();
		indexes = new ArrayList<>();
	}

	/**
	 * 
	 * @param key
	 * @param index
	 *            is the suffix's index to be stored
	 */
	public void insert(String key, int index) {
		indexes.add(index);
		if (key.length() > 0) {
			if (!children.containsKey(key.charAt(0))) {
				children.put(key.charAt(0), new SuffixTrieNode());
			}
			children.get(key.charAt(0)).insert(key.substring(1), index);
		}
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public List<Integer> search(String key) {
		if (key.length() == 0) {
			return indexes;
		}
		if (!children.containsKey(key.charAt(0)))
			return null;
		return children.get(key.charAt(0)).search(key.substring(1));
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Indexes: [ ");
		if (indexes != null) {
			Iterator<Integer> iterator = indexes.iterator();
			while (iterator.hasNext()) {
				buffer.append(iterator.next() + " ");
			}
		}
		buffer.append("]");
		return buffer.toString();
	}

	/**
	 * 
	 * @param level
	 */
	public void traverse(int level) {
		Iterator<Character> iterator = children.keySet().iterator();
		System.out.println("at level (" + level + ")");
		System.out.print("{ ");
		while (iterator.hasNext()) {
			Character next = iterator.next();
			System.out.print(next + " ");
		}
		System.out.println("}" + this.toString());
		iterator = children.keySet().iterator();
		while (iterator.hasNext()) {
			Character next = iterator.next();
			System.out.println("via " + next);
			System.out.println("--------------");
			children.get(next).traverse(level + 1);
		}
	}

	/**
	 * 
	 * @return
	 */
	public int countNumberOfNodes() {
		Iterator<Character> iterator = children.keySet().iterator();
		int count = 0;
		while (iterator.hasNext()) {
			Character next = iterator.next();
			count += children.get(next).countNumberOfNodes();
		}
		return count + 1;
	}
}
