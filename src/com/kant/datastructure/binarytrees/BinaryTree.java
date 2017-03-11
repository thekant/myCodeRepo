/**
 * 
 */
package com.kant.datastructure.binarytrees;

import java.util.LinkedList;
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
	 * calculates height along with the diameter<br/>
	 * 
	 * O(n)
	 */
	private int diameter(TreeNode<T> root, TheHeight theHeight) {
		if (root == null) {
			theHeight.value = 0;
			return 0;
		}

		TheHeight lh = new TheHeight();
		TheHeight rh = new TheHeight();

		int ldiameter = diameter(root.getLeft(), lh);
		int rdiameter = diameter(root.getRight(), rh);

		theHeight.value = Math.max(lh.value, rh.value) + 1;
		return Math
				.max(lh.value + rh.value + 1, Math.max(ldiameter, rdiameter));

	}

	/**
	 * get diameter of tree.
	 */
	public int getDiameter() {
		return diameter(root, new TheHeight());
	}

	class TheHeight {
		int value = 0;
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
	 * Level order or BFS (recursive)
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

	/**
	 * iterative approach.[Single Queue approach]
	 */
	public void traverseLevelOrder2() {
		System.out.println("using BFS to print level order:");
		LinkedList<TreeNode<T>> queue = new LinkedList<TreeNode<T>>();
		queue.add(root);
		TreeNode<T> marker = new TreeNode<T>(null, null, null);
		queue.add(marker);

		int level = 0;
		while (!queue.isEmpty()) {

			System.out.print("\nAt " + level + ": ");
			while (!isMarker(queue.peek())) {
				TreeNode<T> curNode = queue.poll();
				System.out.print(curNode.getData() + " ");

				if (curNode.getLeft() != null)
					queue.add(curNode.getLeft());
				if (curNode.getRight() != null)
					queue.add(curNode.getRight());
			}
			queue.poll();// remove marker
			if (!queue.isEmpty()) {
				level++;// increment level count
				queue.add(marker);
			}

		}

	}

	private boolean isMarker(TreeNode<T> node) {
		return node.getData() == null && node.getLeft() == null
				&& node.getRight() == null;
	}

	/**
	 * @return
	 */
	public boolean isLeaf(TreeNode<T> node) {
		return node.getLeft() == null && node.getRight() == null;
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

	public void printLeftView() {
		leftViewUtil(root, 0);
	}

	private static int max_level = 0;

	// recursive function to print left view
	private void leftViewUtil(TreeNode<T> node, int level) {
		// Base Case
		if (node == null)
			return;
		// If this is the first node of its level
		if (max_level < level) {
			System.out.print(" " + node.data);
			max_level = level;
		}

		// Recur for left and right subtrees
		leftViewUtil(node.getLeft(), level + 1);
		leftViewUtil(node.getRight(), level + 1);
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
			// L -> R
			while (!stack1.isEmpty()) {
				TreeNode<T> cur = stack1.pop();
				System.out.print(cur.getData() + " ");

				if (cur.getLeft() != null) {
					stack2.push(cur.getLeft());
				}
				if (cur.getRight() != null) {
					stack2.push(cur.getRight());
				}
			}

			System.out.println();
			// R->L
			while (!stack2.isEmpty()) {
				TreeNode<T> cur = stack2.pop();
				System.out.print(cur.getData() + " ");
				if (cur.getRight() != null) {
					stack1.push(cur.getRight());
				}
				if (cur.getLeft() != null) {
					stack1.push(cur.getLeft());
				}
			}
		}
	}

	/**
	 * TODO
	 * http://www.geeksforgeeks.org/construct-a-binary-tree-from-postorder-and
	 * -inorder/
	 * 
	 * TO be used for build tree from INorder and Preorder traversal values.
	 */
	static int preIndex = 0;

	/**
	 * Recursive function to construct binary of size len from Inorder traversal
	 * in[] and Preorder traversal pre[]. Initial values of inStrt and inEnd
	 * should be 0 and len - 1. The function doesn't do any error checking for
	 * cases where inorder and preorder do not form a tree.
	 */
	public TreeNode<T> buildTree(T in[], T pre[], int inStrt, int inEnd) {
		if (inStrt > inEnd)
			return null;

		/**
		 * Pick current node from Preorder traversal using preIndex and
		 * increment preIndex
		 */
		TreeNode<T> tNode = new TreeNode<>(pre[preIndex++]);

		/* If this node has no children then return */
		if (inStrt == inEnd)
			return tNode;

		/**
		 * Else find the index of this node in Inorder traversal
		 */
		int inIndex = search(in, inStrt, inEnd, tNode.data);

		/**
		 * Using index in Inorder traversal, construct left and right subtress
		 */
		tNode.setLeft(buildTree(in, pre, inStrt, inIndex - 1));
		tNode.setRight(buildTree(in, pre, inIndex + 1, inEnd));

		return tNode;
	}

	/**
	 * Function to find index of value in arr[start...end] The function assumes
	 * that value is present in in[]
	 */
	private int search(T arr[], int strt, int end, T value) {
		int i;
		for (i = strt; i <= end; i++) {
			if (arr[i].equals(value))
				return i;
		}
		return i;
	}

}
