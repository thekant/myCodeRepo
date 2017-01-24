/**
 * 
 */
package com.kant.datastructure.binarytrees;

/**
 * @author shaskant
 *
 */
public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

	/**
	 * Insert to tree.
	 */
	@Override
	public boolean insert(T data) {
		TreeNode<T> item = new TreeNode<T>(data);
		if (root == null) {
			root = item;
			return true;
		} else {
			TreeNode<T> prev = search(data);
			if (prev.getData().compareTo(data) > 0) {
				prev.setLeft(item);
			} else if (prev.getData().compareTo(data) < 0)
				prev.setRight(item);
			else {
				System.out.println("[INFO] item already present in the tree");
				return false;
			}
			return true;
		}
	}

	/**
	 * Returns parent location where data would have been inserted , if the data
	 * searched is not on the tree. otherwise if data is present will return
	 * node with that value.
	 * 
	 * @param data
	 * @return
	 */
	private TreeNode<T> search(T data) {
		TreeNode<T> cur = root;
		TreeNode<T> prev = null;
		while (cur != null) {
			prev = cur;
			if (cur.getData().compareTo(data) > 0) {
				cur = cur.getLeft();
			} else if (cur.getData().compareTo(data) < 0) {
				cur = cur.getRight();
			} else {
				return cur;
			}
		}
		return prev;
	}

	@Override
	public boolean delete(T data) {
		return false;
	}

	@Override
	public boolean isPresent(T data) {
		return search(data) != null;
	}

	/**
	 * test bench
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		int[] data = { 8, 4, 13, 2, 7, 9, 15, 1, 3, 6 };
		for (int i = 0; i < data.length; i++) {
			tree.insert(data[i]);
		}
		tree.traverseLevelOrder2();
		System.out.println();
		//tree.traverseLevelOrder();
		tree.printSpiral();
	}
}
