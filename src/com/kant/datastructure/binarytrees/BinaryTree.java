/**
 * 
 */
package com.kant.datastructure.binarytrees;

import java.util.Stack;

/**
 * @author shaskant
 *
 */
public abstract class BinaryTree<T> {
	protected TreeNode<T> root;

	/**
	 * 
	 */
	public BinaryTree() {
		this(null);
	}

	/**
	 * start with a different tree
	 * 
	 * @param node
	 */
	public BinaryTree(TreeNode<T> node) {
		this.root = node;
	}

	public TreeNode<T> getRoot() {
		return root;
	}

	public abstract boolean insert(T item);

	public abstract boolean delete(T item);

	public abstract boolean isPresent(T item);

	/**
	 * 
	 * @return
	 */
	public int getSize() {
		return calcSize(root);
	}

	/**
	 * Size of a tree = Size of left subtree + 1 + Size of right subtree
	 * 
	 * @param node
	 * @return
	 */
	private int calcSize(TreeNode<T> node) {
		if (node != null) {
			return 1 + calcSize(node.getLeft()) + calcSize(node.getRight());
		}
		return 0;
	}

	public int getHeightMax() {
		return calcMaxHeight(root);
	}

	private int calcMaxHeight(TreeNode<T> node) {
		if (node != null) {
			return 1 + Math.max(calcMaxHeight(node.getLeft()),
					calcMaxHeight(node.getRight()));
		}
		return 0;
	}

	/**
	 * 
	 */
	public void traverseInOrder() {
		inOrderDisplay(root);
	}

	/**
	 * 
	 */
	public void traversePostOrder() {
		postOrderDisplay(root);
	}

	/**
	 * 
	 */
	public void traversePreOrder() {
		preOrderDisplay(root);
	}

	/**
	 * Level order or BFS
	 */
	public void traverseLevelOrder() {
		System.out.println("Printing Level Order:");
		for (int d = 0; d < getHeightMax(); d++) {
			printThisLevel(getRoot(), d);
			System.out.println();
		}
	}

	private void printThisLevel(TreeNode<T> node, int d) {
		if (node != null) {
			if (d == 0) {
				System.out.print(node.getData() + " ");
				return;
			} else if (d > 0) {
				printThisLevel(node.getLeft(), d - 1);
				printThisLevel(node.getRight(), d - 1);
			}
		}
	}

	private void inOrderDisplay(TreeNode<T> node) {
		if (node != null) {
			inOrderDisplay(node.getLeft());
			System.out.print(" " + node.getData() + " ");
			inOrderDisplay(node.getRight());
		}
	}

	private void postOrderDisplay(TreeNode<T> node) {
		if (node != null) {
			postOrderDisplay(node.getLeft());
			postOrderDisplay(node.getRight());
			System.out.print(" " + node.getData());
		}
	}

	private void preOrderDisplay(TreeNode<T> node) {
		if (node != null) {
			System.out.print(node.getData() + " ");
			preOrderDisplay(node.getLeft());
			preOrderDisplay(node.getRight());
		}
	}

	/**
	 * printing spirally
	 */
	public void printSpiral() {
		System.out.print("\nPrinting spiral order: ");
		Stack<TreeNode<T>> stack1 = new Stack<>();
		Stack<TreeNode<T>> stack2 = new Stack<>();
		stack1.push(root);

		while (!stack1.isEmpty()) {
			System.out.println();
			while (!stack1.isEmpty()) {
				TreeNode<T> cur = stack1.pop();
				System.out.print(cur.getData()+" ");

				if (cur.getLeft() != null) {
					stack2.push(cur.getLeft());
				}
				if (cur.getRight() != null) {
					stack2.push(cur.getRight());
				}
			}

			System.out.println();
			while (!stack2.isEmpty()) {
				TreeNode<T> cur = stack2.pop();
				System.out.print(cur.getData()+" ");
				if (cur.getRight() != null) {
					stack1.push(cur.getRight());
				}
				if (cur.getLeft() != null) {
					stack1.push(cur.getLeft());
				}
			}
		}
	}

}
