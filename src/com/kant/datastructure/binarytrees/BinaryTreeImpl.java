package com.kant.datastructure.binarytrees;

/**
 * Finds max sum path from root to leaf.
 * 
 * @author shaskant
 *
 */
public class BinaryTreeImpl extends BinaryTree<Integer> {

	@Override
	public TreeNode<Integer> getRoot() {
		return super.getRoot();
	}

	@Override
	public boolean insert(Integer item) {
		if (root == null) {
			root = new TreeNode<Integer>(item);
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(Integer item) {
		return false;
	}

	@Override
	public boolean isPresent(Integer item) {
		return false;
	}

	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		BinaryTreeImpl tree = new BinaryTreeImpl();
		tree.insert(10);
		tree.getRoot().setLeft(new TreeNode<>(-2));
		tree.getRoot().setRight(new TreeNode<>(7));
		tree.getRoot().getLeft().setLeft(new TreeNode<>(8));
		tree.getRoot().getLeft().setRight(new TreeNode<>(-4));
		tree.traverseLevelOrder2();

		tree.findMaxSumPath();
	}

	/**
	 * 
	 */
	public void findMaxSumPath() {
		StringBuffer path = new StringBuffer();
		int maxSum = findPath(root, path);
		System.out.println("\nMaximum sum leaf to root : " + maxSum);
		printPath(path.reverse().toString(), root);
	}

	private int findPath(TreeNode<Integer> root, StringBuffer path) {
		if (root == null)
			return 0;
		int leftSum = findPath(root.getLeft(), path);
		int rightSum = findPath(root.getRight(), path);

		if (leftSum > rightSum) {
			path.append('0');
		} else {
			path.append('1');
		}

		return Math.max(leftSum, rightSum) + root.getData();
	}

	private void printPath(String path, TreeNode<Integer> node) {
		for (int index = 0; index < path.length() && node != null; index++) {
			System.out.println(node.getData());
			if (path.charAt(index) == '0') {
				node = node.getLeft();
			} else {
				node = node.getRight();
			}
		}
	}

}
