/**
 * 
 */
package com.kant.datastructure.binarytrees;

/**
 * http://www.geeksforgeeks.org/connect-nodes-at-same-level-with-o1-extra-space/
 * http://www.geeksforgeeks.org/connect-nodes-at-same-level/
 * 
 * @author shashi
 * 
 */
public class BinaryTreeNextRight {
	private XTreeNode root;
	private boolean nextRightsConnected = false;

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		BinaryTreeNextRight tree = new BinaryTreeNextRight();
		int[] data = { 4, 3, 6, 1, 2, 5, 7, 8 };
		for (int item : data)
			tree.insert(item);
		//tree.connectNextRights();
		tree.printLevelOrder();
		tree.printInorder();
	}

	/**
	 * 
	 * @param value
	 */
	public void insert(int value) {
		root = insert(root, value);
	}

	/**
	 * 
	 * @param root
	 * @param value
	 * @return
	 */
	private XTreeNode insert(XTreeNode root, int value) {
		if (root == null) {
			root = new XTreeNode(value);
			return root;

		} else {
			if (root.data < value) {
				root.rnode = insert(root.rnode, value);
			} else {
				root.lnode = insert(root.lnode, value);
			}
			return root;
		}
	}

	/**
	 * 
	 */
	public void printInorder() {
		// root = connectNextRights();
		inOrderTraverse(root);
	}

	/**
	 * 
	 * @param node
	 */
	private void inOrderTraverse(XTreeNode node) {
		if (node != null) {
			inOrderTraverse(node.lnode);
			System.out.print(node.data + " ");
			inOrderTraverse(node.rnode);
		}
	}

	/**
	 * not perfect
	 */
	public void printLevelOrder() {
		if(!nextRightsConnected)connectNextRights();
		XTreeNode cur = root;
		while (cur != null) {
			XTreeNode nexter = cur;
			while (nexter != null) {
				System.out.print(nexter.data + " ");
				nexter = nexter.nextRight;
			}
			if (cur.lnode != null)
				cur = cur.lnode;
			else if (cur.rnode != null)
				cur = cur.rnode;
			else
				cur = getNextRightUnder(cur);
			System.out.println();
		}
	}

	/**
	 * 
	 * @return
	 */
	public XTreeNode connectNextRights() {
		nextRightsConnected = true;
		if (root == null)
			return null;
		root.nextRight = null;

		XTreeNode looper = root;
		while (looper != null) {
			XTreeNode parentNode = looper;

			/**
			 */
			while (parentNode != null) {
				XTreeNode leftChild = parentNode.lnode;
				XTreeNode rightChild = parentNode.rnode;

				if (leftChild != null) {
					if (rightChild != null) {
						leftChild.nextRight = rightChild;
					} else {
						leftChild.nextRight = getNextRightUnder(parentNode);
					}
				}
				if (rightChild != null) {
					rightChild.nextRight = getNextRightUnder(parentNode);
				}
				// setup next right
				parentNode = parentNode.nextRight;
			}

			// keep leftmost
			if (looper.lnode != null)
				looper = looper.lnode;
			else if (looper.rnode != null)
				looper = looper.rnode;
			else
				looper = getNextRightUnder(looper);
		}
		return root;
	}

	/**
	 * return the leftmost child of next element on the right at same level
	 * 
	 * @param node
	 * @return
	 */
	private XTreeNode getNextRightUnder(XTreeNode node) {
		XTreeNode nexter = node.nextRight;
		while (nexter != null) {
			if (nexter.lnode != null)
				return nexter.lnode;
			if (nexter.rnode != null)
				return nexter.rnode;
			nexter = nexter.nextRight;
		}
		return null;
	}
}

/**
 * 
 * @author shashi
 * 
 */
class XTreeNode {
	int data;
	XTreeNode lnode;
	XTreeNode rnode;
	XTreeNode nextRight;

	public XTreeNode(int value) {
		this.data = value;
		lnode = null;
		rnode = null;
		nextRight = null;
	}

}