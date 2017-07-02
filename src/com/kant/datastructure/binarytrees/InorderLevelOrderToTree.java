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
	public Node constructBinaryTree(int[] inorder, int[] levelOrder,
			int startIndex, int endIndex) {
		if (startIndex > endIndex) {
			return null;
		}

		Node root = new Node(levelOrder[0]);
		if (startIndex == endIndex) {
			return root;
		}
		
		int index = findIndexForKey(inorder, levelOrder[0], startIndex, endIndex);

		int[] leftLevelOrdr = extractNextLevelOrder(inorder, levelOrder,
				startIndex, index - 1);
		int[] rightLevelOrdr = extractNextLevelOrder(inorder, levelOrder,
				index + 1, endIndex);

		root.left = constructBinaryTree(inorder, leftLevelOrdr, startIndex,
				index - 1);
		root.right = constructBinaryTree(inorder, rightLevelOrdr, index + 1,
				endIndex);

		return root;
	}

	private int[] extractNextLevelOrder(int[] inorder, int[] levelOrder,
			int startIndex, int endIndex) {
		int[] newlevel = new int[endIndex - startIndex + 1];
		int x = 0;
		for (int i = 0; i < levelOrder.length; i++) {
			if (findIndexForKey(inorder, levelOrder[i], startIndex, endIndex) != -1) {
				newlevel[x] = levelOrder[i];
				x++;
			}
		}
		return newlevel;
	}

	private int findIndexForKey(int[] dataArray, int key, int startIndex,
			int endIndex) {
		for (int i = startIndex; i <= endIndex; i++) {
			if (key == dataArray[i]) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 
	 * @param root
	 */
	public void traverseInorder(Node root) {
		if (root != null) {
			traverseInorder(root.left);
			System.out.print("  " + root.data);
			traverseInorder(root.right);
		}
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		int[] inOrder = { 4, 2, 5, 1, 6, 3, 7 };
		int[] levelOrder = { 1, 2, 3, 4, 5, 6, 7 };
		InorderLevelOrderToTree i = new InorderLevelOrderToTree();
		Node x = i.constructBinaryTree(inOrder, levelOrder, 0,
				inOrder.length - 1);
		System.out.println("inorder traversal of constructed tree : ");
		i.traverseInorder(x);
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
