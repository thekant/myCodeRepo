/**
 * 
 */
package com.kant.datastructure.binarytrees;

/**
 * http://algorithms.tutorialhorizon.com/construct-a-binary-tree-from-given-
 * inorder-and-level-order-traversal/
 * 
 * @author shaskant
 *
 */
public class InorderLevelOrderToTree {

	/**
	 * 
	 * @return
	 */
	public Node makeBTree(int[] inorder, int[] levelOrder, int iStart, int iEnd) {
		if (iStart > iEnd) {
			return null;
		}

		int rootVal = levelOrder[0];
		Node root = new Node(rootVal);
		if (iStart == iEnd) {
			return root;
		}
		int index = findIndex(inorder, rootVal, iStart, iEnd);

		int[] leftLevelOrdr = newLevelOrder(inorder, levelOrder, iStart,
				index - 1);
		int[] rightLevelOrdr = newLevelOrder(inorder, levelOrder, index + 1,
				iEnd);

		root.left = makeBTree(inorder, leftLevelOrdr, iStart, index - 1);
		root.right = makeBTree(inorder, rightLevelOrdr, index + 1, iEnd);

		return root;
	}

	private int[] newLevelOrder(int[] inorder, int[] levelOrder, int iStart,
			int iEnd) {
		int[] newlevel = new int[iEnd - iStart + 1];
		int x = 0;
		for (int i = 0; i < levelOrder.length; i++) {
			if (findIndex(inorder, levelOrder[i], iStart, iEnd) != -1) {
				newlevel[x] = levelOrder[i];
				x++;
			}
		}
		return newlevel;
	}

	private int findIndex(int[] inorder, int value, int iStart, int iEnd) {
		for (int i = iStart; i <= iEnd; i++) {
			if (value == inorder[i]) {
				return i;
			}
		}
		return -1;
	}

	public void printINORDER(Node root) {
		if (root != null) {
			printINORDER(root.left);
			System.out.print("  " + root.data);
			printINORDER(root.right);
		}
	}

	public static void main(String args[]) {
		int[] inOrder = { 4, 2, 5, 1, 6, 3, 7 };
		int[] levelOrder = { 1, 2, 3, 4, 5, 6, 7 };
		InorderLevelOrderToTree i = new InorderLevelOrderToTree();
		Node x = i.makeBTree(inOrder, levelOrder, 0, inOrder.length - 1);
		System.out.println("inorder traversal of constructed tree : ");
		i.printINORDER(x);
	}
}

class Node {
	int data;
	Node left;
	Node right;

	public Node(int data) {
		this.data = data;
		left = null;
		right = null;
	}
}
