/**
 * 
 */
package com.kant.general;

import com.kant.datastructure.binarytrees.TreeNode;

/**
 * http://www.geeksforgeeks.org/count-smaller-elements-on-right-side/ <br/>
 * Use BST self balancing to keep track of elements
 * 
 * @author shaskant
 *
 */
public class CountSmallerElementsOnRightSide {

	static void printArray(int[] arr) {
		for (int i : arr)
			System.out.print(i + " ");
		System.out.println();
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int[] arr = { 10, 6, 15, 20, 30, 5, 7 };
		// printArray(arr);
		int[] countSmaller = countSmallerElementsOnRightSide(arr);
		//for (int i = 0; i < arr.length; i++) {
			//countSmaller[i] = arr.length - countSmaller[i] + 1;
		//}
		printArray(countSmaller);
	}

	/**
	 * 
	 * @param arr
	 * @return
	 */
	public static int[] countSmallerElementsOnRightSide(int[] arr) {
		int n = arr.length;
		int[] countSmaller = new int[n];
		TheAVLTree tree = new TheAVLTree();
		for (int i = n - 1; i >= 0; i--)
			tree.insert(arr[i], countSmaller, i);
		return countSmaller;
	}
}

class AugmentTreeNode extends TreeNode<Integer> {
	private int size;
	private int height;

	/**
	 * @param data
	 */
	public AugmentTreeNode(Integer data) {
		super(data);
		this.size = 1;
		this.height = 1;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}

/**
 * AVL tree impl
 * 
 * @author shaskant
 *
 */
class TheAVLTree {
	private AugmentTreeNode root = null;

	/**
	 * 
	 * @param data
	 * @param countSmaller
	 * @param x
	 */
	public void insert(int data, int[] countSmaller, int x) {
		root = insert(root, data, countSmaller, x);
	}

	public void printInOrder() {
		inOrder(root);
	}

	void inOrder(TreeNode<Integer> root) {
		if (root != null) {
			inOrder(root.getLeft());
			System.out.println(root.getData() + " ");
			inOrder(root.getRight());
		}
	}

	/**
	 * http://www.geeksforgeeks.org/avl-tree-set-1-insertion/
	 * 
	 * @return
	 */
	private AugmentTreeNode insert(AugmentTreeNode node, int data,
			int[] countSmaller, int indx) {
		/* 1. Perform the normal BST rotation */
		if (node == null)
			return (new AugmentTreeNode(data));

		if (data < node.getData())
			node.setLeft(insert((AugmentTreeNode) node.getLeft(), data,
					countSmaller, indx));
		else if (data > node.getData()) {
			node.setRight(insert((AugmentTreeNode) node.getRight(), data,
					countSmaller, indx));
			countSmaller[indx] += getSize(node.getLeft()) + 1;
		}

		/* 2. Update height and size of this ancestor node */
		node.setHeight(1 + Math.max(getHeight(node.getLeft()),
				getHeight(node.getRight())));
		node.setSize(getSize(node.getLeft()) + getSize(node.getRight()) + 1);

		/*
		 * 3. Get the balance factor of this ancestor node to check whether this
		 * node became unbalanced
		 */
		int balance = getBalance(node);

		// If this node becomes unbalanced, then there are 4 cases

		// Left Left Case
		if (balance > 1 && data < node.getLeft().getData())
			rightRotate(node);

		// Right Right Case
		else if (balance < -1 && data > node.getRight().getData())
			leftRotate(node);

		// Left Right Case
		else if (balance > 1 && data > node.getLeft().getData()) {
			node.setLeft(leftRotate(node.getLeft()));
			rightRotate(node);
		}

		// Right Left Case
		else if (balance < -1 && data < node.getRight().getData()) {
			node.setRight(rightRotate(node.getRight()));
			leftRotate(node);
		}
		return node;
	}

	private int getHeight(TreeNode<Integer> node) {
		if (node == null)
			return 0;
		else
			return ((AugmentTreeNode) node).getHeight();
	}

	private int getSize(TreeNode<Integer> node) {
		if (node == null)
			return 0;
		else
			return ((AugmentTreeNode) node).getSize();
	}

	/**
	 */
	private AugmentTreeNode rightRotate(TreeNode<Integer> y) {
		AugmentTreeNode x = (AugmentTreeNode) y.getLeft();
		AugmentTreeNode T2 = (AugmentTreeNode) x.getRight();

		// Perform rotation
		x.setRight(y);
		y.setLeft(T2);

		// Update heights
		((AugmentTreeNode) y).setHeight(1 + Math.max(getHeight(y.getLeft()),
				getHeight(y.getRight())));
		x.setHeight(1 + Math.max(getHeight(x.getLeft()),
				getHeight(x.getRight())));

		// Update sizes
		((AugmentTreeNode) y).setSize(getSize(y.getLeft())
				+ getSize(y.getRight()) + 1);
		x.setSize(getSize(x.getLeft()) + getSize(x.getRight()) + 1);

		// Return new root
		return x;
	}

	/**
	 */
	private AugmentTreeNode leftRotate(TreeNode<Integer> x) {
		AugmentTreeNode y = (AugmentTreeNode) x.getRight();
		AugmentTreeNode T2 = (AugmentTreeNode) y.getLeft();

		// Perform rotation
		y.setLeft(x);
		x.setRight(T2);

		// Update heights
		((AugmentTreeNode) x).setHeight(1 + Math.max(getHeight(x.getLeft()),
				getHeight(x.getRight())));
		y.setHeight(1 + Math.max(getHeight(y.getLeft()),
				getHeight(y.getRight())));

		((AugmentTreeNode) x).setSize(getSize(x.getLeft())
				+ getSize(x.getRight()) + 1);
		y.setSize(getSize(y.getLeft()) + getSize(y.getRight()) + 1);

		// Return new root
		return y;
	}

	/**
	 * 
	 */
	public int getBalance(AugmentTreeNode node) {
		if (node == null)
			return 0;
		return getHeight(node.getLeft()) - getHeight(node.getRight());
	}
}
