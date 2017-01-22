/**
 * 
 */
package com.kant.datastructure.graphs;

import com.kant.datastructure.binarytrees.TreeNode;

/**
 * @author shaskant
 *
 */
public class VertexCoverTree {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TreeNode<Integer> root = new TreeNode<>(20);
		root.setLeft(new TreeNode<>(8, new TreeNode<>(4), new TreeNode<>(12)));
		root.setRight(new TreeNode<>(22, null, new TreeNode<>(25)));

		root.getLeft().getRight().setLeft(new TreeNode<>(10));
		root.getLeft().getRight().setRight(new TreeNode<>(14));

		TreeNodeWithvc rootVc = new TreeNodeWithvc(root);
		System.out.println(new VertexCoverTree().vCover(rootVc));
	}

	/**
	 * @param root
	 * @return
	 */
	public int vCover(TreeNodeWithvc root) {
		// The size of minimum vertex cover is zero if tree is empty or there
		// is only one node
		if (root == null)
			return 0;
		if (root.getLeft() == null && root.getRight() == null)
			return 0;
		if (root.getVc() != -1)
			return root.getVc();

		// Calculate size of vertex cover when root is part of it
		int size_incl = 1 + vCover((TreeNodeWithvc) root.getLeft())
				+ vCover((TreeNodeWithvc) root.getRight());

		// Calculate size of vertex cover when root is not part of it.
		// 1 for left and 1 for right
		int size_excl = 0;
		if (root.getLeft() != null)
			size_excl += 1 + vCover((TreeNodeWithvc) root.getLeft().getLeft())
					+ vCover((TreeNodeWithvc) root.getLeft().getRight());
		if (root.getRight() != null)
			size_excl += 1 + vCover((TreeNodeWithvc) root.getRight().getLeft())
					+ vCover((TreeNodeWithvc) root.getRight().getRight());

		root.setVc(Math.min(size_incl, size_excl));
		// Return the minimum of two sizes
		return root.getVc();
	}

}

/**
 * Kind-a-adaptor-a-decorator
 * 
 * @author shaskant
 *
 */
class TreeNodeWithvc extends TreeNode<Integer> {
	private int vc; // store this value
	private TreeNode<Integer> node;

	/**
	 * 
	 */
	public TreeNodeWithvc() {
		super();
	}

	/**
	 * @param data
	 * @param left
	 * @param right
	 */
	public TreeNodeWithvc(TreeNode<Integer> node) {
		this.node = node;
		vc = -1;
	}

	public int getVc() {
		return vc;
	}

	public void setVc(int vc) {
		this.vc = vc;
	}

	@Override
	public Integer getData() {
		return node.getData();
	}

	@Override
	public TreeNode<Integer> getLeft() {
		if (node.getLeft() != null)
			return new TreeNodeWithvc(node.getLeft());
		else
			return null;
	}

	@Override
	public TreeNode<Integer> getRight() {
		if (node.getRight() != null) {
			return new TreeNodeWithvc(node.getRight());
		} else
			return null;
	}
}