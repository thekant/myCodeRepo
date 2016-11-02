/**
 * 
 */
package com.kant.datastructure.binarytrees;

/**
 * @author shaskant
 *
 */
public class GenericBinaryTree extends BinaryTree<String> {

	public GenericBinaryTree(TreeNode<String> root) {
		super(root);
	}

	public Result getLCP() {
		return findLCP(getRoot());
	}

	/**
	 * 
	 * @param node
	 * @return
	 */
	private Result findLCP(TreeNode<String> node) {
		if (node != null) {
			// in case of a leaf node
			if (node.getLeft() == null && node.getRight() == null) {
				Result lcp = new Result();
				lcp.head = node;
				lcp.value = 1;
				return lcp;
			}
			Result lcpLeft = findLCP(node.getLeft());
			Result lcpRight = findLCP(node.getRight());
			if (lcpLeft != null) {
				if (Integer.parseInt(lcpLeft.head.getData())
						- Integer.parseInt(node.getData()) == 1) {
					lcpLeft.head = node;
					lcpLeft.value++;
				}
			}
			if (lcpRight != null) {
				if (Integer.parseInt(lcpRight.head.getData())
						- Integer.parseInt(node.getData()) == 1) {
					lcpRight.head = node;
					lcpRight.value++;
				}
			}
			if (lcpLeft == null)
				return lcpRight;
			else if (lcpRight == null)
				return lcpLeft;
			else if (lcpLeft.value > lcpRight.value)
				return lcpLeft;
			else
				return lcpRight;
		}
		return null;
	}

	@Override
	public boolean insert(String item) {
		return false;
	}

	@Override
	public boolean delete(String item) {
		return false;
	}

	@Override
	public boolean isPresent(String item) {
		return false;
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		TreeNode<String> root = new TreeNode<String>("6");
		root.setRight(new TreeNode<String>("9"));
		root.getRight().setLeft(new TreeNode<String>("7"));
		root.getRight().setRight(new TreeNode<String>("10"));
		root.getRight().getRight().setRight(new TreeNode<String>("11"));
		GenericBinaryTree tree = new GenericBinaryTree(root);
		Result result = tree.getLCP();
		System.out.println(result.value);
		System.out.println(result.head.getData());
	}

	class Result {
		public int value;
		public TreeNode<String> head;
	}
}
