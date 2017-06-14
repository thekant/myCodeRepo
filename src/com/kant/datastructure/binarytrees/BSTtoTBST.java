/**
 * 
 */
package com.kant.datastructure.binarytrees;

/**
 * http://algorithms.tutorialhorizon.com/convert-binary-tree-into-threaded-
 * binary-tree/
 * 
 * @author shaskant
 *
 */
public class BSTtoTBST {

	public static XNODE root;

	public void convert(XNODE root) {
		inorder(root, null);
	}

	public void inorder(XNODE root, XNODE previous) {
		if (root == null) {
			return;
		} else {
			inorder(root.right, previous);
			if (root.right == null && previous != null) {
				root.right = previous;
				root.rightThread = true;
			}
			inorder(root.left, root);
		}
	}

	public void print(XNODE root) {
		// first go to most left TNODE
		XNODE current = leftMostTNODE(root);
		// now travel using right pointers
		while (current != null) {
			System.out.print(" " + current.data);
			// check if TNODE has a right thread
			if (current.rightThread)
				current = current.right;
			else
				// else go to left most TNODE in the right subtree
				current = leftMostTNODE(current.right);
		}
		System.out.println();
	}

	public XNODE leftMostTNODE(XNODE TNODE) {
		if (TNODE == null) {
			return null;
		} else {
			while (TNODE.left != null) {
				TNODE = TNODE.left;
			}
			return TNODE;
		}
	}

	public static void main(String[] args) {
		root = new XNODE(10);
		root.left = new XNODE(5);
		root.right = new XNODE(15);
		root.left.left = new XNODE(1);
		root.left.right = new XNODE(7);
		root.right.left = new XNODE(12);
		root.right.right = new XNODE(20);

		BSTtoTBST bsTtoTBST = new BSTtoTBST();
		bsTtoTBST.convert(root);
		System.out.println("Inorder Traversal: ");
		bsTtoTBST.print(root);
	}
}


class XNODE {
	XNODE left;
	XNODE right;
	int data;
	boolean rightThread;

	public XNODE(int data) {
		this.data = data;
		rightThread = false;
	}
}
