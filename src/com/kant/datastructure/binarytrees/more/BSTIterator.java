/**
 * 
 */
package com.kant.datastructure.binarytrees.more;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * Implement an iterator over a binary search tree (BST). Your iterator will be
 * initialized with the root node of a BST. Calling next() will return the next
 * smallest number in the BST. Note: next() and hasNext() should run in average
 * O(1) time and uses O(h) memory, where h is the height of the tree.
 * 
 * http://www.programcreek.com/2014/04/leetcode-binary-search-tree-iterator-java/
 * 
 * @author shaskant
 * @param <E>
 * @param <T>
 *
 */
public class BSTIterator<E> implements Iterator<Node<E>> {
	private Stack<Node<E>> stack;

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		BSTTree<Integer> tree = buildTestData();
		BSTIterator<Integer> iterator = new BSTIterator<>(
				tree.getRootOfBSTree());
		while (iterator.hasNext()) {
			System.out.print(iterator.next().getItem() + " ");
		}
	}

	/**
	 * 
	 * @param node
	 */
	public BSTIterator(Node<E> node) {
		stack = new Stack<Node<E>>();
		while (node != null) {
			stack.push(node);
			node = node.left;
		}
	}

	@Override
	public boolean hasNext() {
		return !stack.isEmpty();
	}

	@Override
	public Node<E> next() {
		Node<E> result = stack.pop();
		if (result.right != null) {
			Node<E> righto = result.right;
			while (righto != null) {
				stack.push(righto);
				righto = righto.left;
			}
		}
		return result;
	}

	/**
	 * @return
	 * 
	 */
	private static BSTTree<Integer> buildTestData() {
		BSTTree<Integer> tree = new BSTTree<>();
		tree.insertToBStTree(7);
		tree.insertToBStTree(2);
		tree.insertToBStTree(9);
		tree.insertToBStTree(1);
		tree.insertToBStTree(6);
		tree.insertToBStTree(8);
		tree.insertToBStTree(3);
		tree.insertToBStTree(4);
		tree.insertToBStTree(5);
		System.out
				.println("--------Print test tree<level order>------------------");
		Iterator<List<Node<Integer>>> iterator = tree.getListOfLevels()
				.iterator();
		while (iterator.hasNext()) {
			Iterator<Node<Integer>> iterator2 = iterator.next().iterator();
			while (iterator2.hasNext()) {
				System.out.print(iterator2.next().getItem() + " ");
			}
			System.out.println();
		}
		System.out.println(tree.isBSTTree());
		System.out.println("--------------------------");
		return tree;
	}

}
