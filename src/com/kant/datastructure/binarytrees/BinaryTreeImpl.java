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
	 * Lowest common ancestor.
	 * 
	 * @param root
	 * @param n1
	 * @param n2
	 * @return
	 */
	public TreeNode<Integer> getLCA(TreeNode<Integer> node, int n1, int n2) {
		if (node != null) {
			if (node.getData() == n1 || node.getData() == n2) {
				return node;
			}
			TreeNode<Integer> left = getLCA(node.getLeft(), n1, n2);
			TreeNode<Integer> right = getLCA(node.getRight(), n1, n2);

			if (left != null && right != null) {
				return node;
			}
			if (left != null) {
				return left;
			} else if (right != null) {
				return right;
			}
		}
		return null;
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

		tree.findMaxSumPathRootToLeaf();
	}

	private int maxSumSoFar = 0;

	public int findMaxSumLeafToLeaf() {
		return Math.max(findMaxSumLeafToLeaf(root), maxSumSoFar);
	}

	/**
	 * takes care of negative values also.
	 * 
	 * @param root
	 * @return
	 */
	private int findMaxSumLeafToLeaf(TreeNode<Integer> root) {
		if (root == null)
			return 0;
		int leftSum = findMaxSumLeafToLeaf(root.getLeft());
		int rightSum = findMaxSumLeafToLeaf(root.getRight());
		int sumCurrent;
		if (leftSum < 0 && rightSum < 0) {
			sumCurrent = root.data;
		} else {
			sumCurrent = Math.max(leftSum + rightSum + root.data,
					Math.max(leftSum, rightSum));
		}
		if (maxSumSoFar < sumCurrent) {
			maxSumSoFar = sumCurrent;
		}
		return Math.max(leftSum, rightSum) + root.getData();
	}

	/**
	 * 
	 * Leaf to Root maximum sum path.
	 */
	public void findMaxSumPathRootToLeaf() {
		StringBuffer path = new StringBuffer();
		int maxSum = findPathRootToLeaf(root, path);
		System.out.println("\nMaximum sum leaf to root : " + maxSum);
		printPathRootToLeaf(path.reverse().toString(), root);
	}

	private int findPathRootToLeaf(TreeNode<Integer> root, StringBuffer path) {
		if (root == null)
			return 0;
		int leftSum = findPathRootToLeaf(root.getLeft(), path);
		int rightSum = findPathRootToLeaf(root.getRight(), path);

		if (leftSum > rightSum) {
			path.append('0');
		} else {
			path.append('1');
		}

		return Math.max(leftSum, rightSum) + root.getData();
	}

	private void printPathRootToLeaf(String path, TreeNode<Integer> node) {
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
