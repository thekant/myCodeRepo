/**
 * 
 */
package com.kant.datastructure.binarytrees.more;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shashi
 * 
 */
public class BSTTree<E extends Comparable<E>> {
	private Node<E> root = null;

	/**
	 * 
	 * @return
	 */
	public Node<E> getRootOfBSTree() {
		return root;
	}

	/**
	 * 
	 * @param item
	 * @return
	 */
	public boolean insertToBStTree(E item) {
		if (item != null) {
			if (root == null) {
				root = new Node<E>(item);
				return true;
			} else {
				Node<E> finder = root;
				Node<E> prev = finder;
				do {
					prev = finder;
					if (finder.getItem().compareTo(item) > 0) {
						finder = finder.left;
					} else
						finder = finder.right;
				} while (finder != null);

				if (prev.getItem().compareTo(item) > 0) {
					prev.left = new Node<E>(item, null, null, prev);
				} else
					prev.right = new Node<E>(item, null, null, prev);

				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 */
	public void traverseBSTInorder() {
		if (root != null)
			inorderTraverse(root);
	}

	/**
	 * 
	 * @param traveler
	 */
	private void inorderTraverse(Node<E> traveler) {
		if (traveler != null) {
			inorderTraverse(traveler.left);
			System.out.print("[" + traveler.getItem() + "] ");
			inorderTraverse(traveler.right);
		}
	}

	/**
	 * 
	 */
	public void printGivenLevel(int level) {
		printGivenLevel(root, level);
	}

	/**
	 * Printing out elements at a particular level.
	 * 
	 * @param thisNode
	 * @param level
	 */
	private void printGivenLevel(Node<E> thisNode, int level) {
		if (thisNode != null) {
			if (level == 1) {
				System.out.print("[" + thisNode.getItem() + "]");
			} else {
				printGivenLevel(thisNode.left, level - 1);
				printGivenLevel(thisNode.right, level - 1);
			}
		}
	}

	/**
	 * 
	 * @return
	 */
	public List<List<Node<E>>> getListOfLevels() {
		int level = 0;
		List<List<Node<E>>> result = new ArrayList<>();
		List<Node<E>> rootLevel = new ArrayList<>();
		rootLevel.add(root);
		result.add(level, rootLevel);

		while (true) {
			List<Node<E>> currentLevel = new ArrayList<>();
			for (int i = 0; i < result.get(level).size(); i++) {
				Node<E> itemNode = result.get(level).get(i);
				if (itemNode.left != null)
					currentLevel.add(itemNode.left);
				if (itemNode.right != null)
					currentLevel.add(itemNode.right);
			}

			if (currentLevel.size() > 0) {
				result.add(level + 1, currentLevel);
			} else {
				break;
			}

			level++;
		}
		return result;
	}

	/**
	 * gets max depth.
	 * 
	 * @param root
	 * @return
	 */
	private int maxdepth(Node<E> traveler) {
		if (traveler == null) {
			return 0;
		}
		return 1 + Math.max(maxdepth(traveler.left), maxdepth(traveler.right));
	}

	/**
	 * 
	 * @param item1
	 * @param item2
	 * @return
	 */
	public E getLowestCommonAncestor(E item1, E item2) {
		E maxTerm = (item1.compareTo(item2) > 0) ? item1 : item2;
		E minTerm = (item1.compareTo(item2) < 0) ? item1 : item2;
		return getLCA(root, minTerm, maxTerm);
	}

	/**
	 * Logic to find Lowest Common Ancestor.
	 * 
	 * @param traveler
	 * @param item1
	 * @param item2
	 * @return
	 */
	private E getLCA(Node<E> traveler, E item1, E item2) {
		if (traveler != null) {
			if (traveler.getItem().compareTo(item1) > 0
					&& traveler.getItem().compareTo(item2) > 0) {
				return getLCA(traveler.left, item1, item2);
			} else if (traveler.getItem().compareTo(item1) < 0
					&& traveler.getItem().compareTo(item2) < 0) {
				return getLCA(traveler.right, item1, item2);
			} else if (item1.compareTo(traveler.getItem()) <= 0
					&& traveler.getItem().compareTo(item2) <= 0) {
				return traveler.getItem();
			}
		}
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isBSTTree() {
		@SuppressWarnings("unchecked")
		Node<E>[] prevN = new Node[1];
		return isBST(root, prevN);
	}

	// private Node<E> prev = null; // for BST property

	/**
	 * Logic to find if tree maintains BST property.
	 * 
	 * @param node
	 * @return
	 */
	private boolean isBST(Node<E> node, Node<E>[] prevN) {
		// traverse the tree in inOrder fashion and keep track of prev node
		if (node != null) {
			if (!isBST(node.left, prevN))
				return false;

			// Allows only distinct valued nodes
			if (prevN[0] != null
					&& node.getItem().compareTo(prevN[0].getItem()) <= 0)
				return false;

			prevN[0] = node;

			return isBST(node.right, prevN);
		}

		return true;
	}

	public int getMaxdepth() {
		return maxdepth(root);
	}
}
