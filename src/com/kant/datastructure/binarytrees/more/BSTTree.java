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
public class BSTTree<E extends Integer> {
	private Node<E> root = null;

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
					if (finder.getItem().intValue() > item) {
						finder = finder.left;
					} else
						finder = finder.right;
				} while (finder != null);

				if (prev.getItem().intValue() > item) {
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
		E maxTerm = (item1.intValue() > item2.intValue()) ? item1 : item2;
		E minTerm = (item1.intValue() < item2.intValue()) ? item1 : item2;
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
			if (traveler.getItem().intValue() > item1.intValue()
					&& traveler.getItem().intValue() > item2.intValue()) {
				return getLCA(traveler.left, item1, item2);
			} else if (traveler.getItem().intValue() < item1.intValue()
					&& traveler.getItem().intValue() < item2.intValue()) {
				return getLCA(traveler.right, item1, item2);
			} else if (item1.intValue() <= traveler.getItem().intValue()
					&& traveler.getItem().intValue() <= item2.intValue()) {
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
		return isBST(root);
	}

	private Node<E> prev = null; // for BST property

	/**
	 * Logic to find if tree maintains BST property.
	 * 
	 * @param node
	 * @return
	 */
	private boolean isBST(Node<E> node) {
		// traverse the tree in inOrder fashion and keep track of prev node
		if (node != null) {
			if (!isBST(node.left))
				return false;

			// Allows only distinct valued nodes
			if (prev != null
					&& node.getItem().intValue() <= prev.getItem()
							.intValue())
				return false;

			prev = node;

			return isBST(node.right);
		}

		return true;
	}

	public int getMaxdepth() {
		return maxdepth(root);
	}
}
