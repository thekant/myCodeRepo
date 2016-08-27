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
	 * 
	 */
	@Override
	public boolean insert(T data) {
		TreeNode<T> item = new TreeNode<T>(data);
		if (root == null) {
			root = item;
			return true;
		} else {
			TreeNode<T> prev = search(data);
			if (prev.getData().compareTo(data) < 0) {
				prev.setLeft(item);
			} else if (prev.getData().compareTo(data) > 0)
				prev.setRight(item);
			else {
				System.out.println("[INFO] item already present in the tree");
				return false;
			}
			return true;
		}
	}

	/**
	 * @param data
	 * @return
	 */
	private TreeNode<T> search(T data) {
		TreeNode<T> cur = root;
		TreeNode<T> prev = null;
		while (cur != null) {
			prev = cur;
			if (cur.getData().compareTo(data) < 0) {
				cur = cur.getLeft();
			} else if (cur.getData().compareTo(data) > 0) {
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

}
