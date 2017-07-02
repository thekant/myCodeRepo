package com.kant.datastructure.binarytrees.more;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Contains many tree utility functionalities.
 * 
 * @author shashi
 */
public class ReverseOrderTraversalAndFindingKeySum {

	private TreeNode root;

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ReverseOrderTraversalAndFindingKeySum plateform = new ReverseOrderTraversalAndFindingKeySum();
		ReverseOrderTraversalAndFindingKeySum plateform2 = new ReverseOrderTraversalAndFindingKeySum();

		plateform.createBSTree(new int[] { 21, 67, 3, 4, 8, 5, 34, 100, 1, 0 });
		plateform2
				.createBSTree(new int[] { 22, 67, 3, 4, 100, 5, 34, 99, 1, 0 });

		// plateform.traverseInorder();
		// System.out.println(plateform.isMirrorImage(plateform.getTree(),
		// plateform2.getTree()));

		plateform.printReverseLevelOrder();
		System.out.println();
		plateform.multiLineSpiralLevelOrder();
		System.out.println();
		plateform.printNormalLevelOrder();
		System.out.println();
		plateform.multiLineNormalLevelOrder();
		System.out.println();
		System.out.println(plateform.isBst(plateform.getTree()));
		plateform.traverseInorder();
		System.out.println();
		plateform.findKeySum(39);
	}

	/**
	 * needed for recursive BST checking.
	 */
	private transient TreeNode prev = null;

	/**
	 * checking if tree is BST.
	 * 
	 * @param curNode
	 * @return
	 */
	public boolean isBst(TreeNode curNode) {
		if (curNode != null) {
			// left subtree must be BST
			if (!isBst(curNode.left))
				return false;

			// must follow increasing order
			if (prev != null && prev.data > curNode.data)
				return false;
			prev = curNode;

			// right subtree must be BST
			return isBst(curNode.right);
		}
		return true;
	}

	/**
	 * can be modified to spiral level order. Use 2 stacks for spiral order
	 * 
	 * @throws InterruptedException
	 */
	public void multiLineNormalLevelOrder() {
		System.out.println("Level order output(mutliline): ");
		List<TreeNode> queue1 = new ArrayList<TreeNode>();
		List<TreeNode> queue2 = new ArrayList<TreeNode>();
		queue1.add(root);
		boolean bothEmpty = false;
		while (!bothEmpty) {
			bothEmpty = true;
			while (!queue1.isEmpty()) {
				TreeNode next = queue1.remove(0);
				System.out.print(next.data + " ");
				if (next.left != null)
					queue2.add(next.left);
				if (next.right != null)
					queue2.add(next.right);
				bothEmpty = false;
			}
			System.out.println();
			while (!queue2.isEmpty()) {
				TreeNode next = queue2.remove(0);
				System.out.print(next.data + " ");
				if (next.left != null)
					queue1.add(next.left);
				if (next.right != null)
					queue1.add(next.right);
				bothEmpty = false;
			}
			System.out.println();
		}
	}

	/**
	 * 
	 */
	public void multiLineSpiralLevelOrder() {
		System.out.println("Spiral Order Output:  ");
		Stack<TreeNode> stack1 = new Stack<TreeNode>();
		Stack<TreeNode> stack2 = new Stack<TreeNode>();
		stack1.add(root);
		boolean bothEmpty = false;
		while (!bothEmpty) {
			bothEmpty = true;
			while (!stack1.isEmpty()) {
				TreeNode next = stack1.pop();
				System.out.print(next.data + " ");
				if (next.left != null)
					stack2.add(next.left);
				if (next.right != null)
					stack2.add(next.right);
				bothEmpty = false;
			}
			System.out.println();
			while (!stack2.isEmpty()) {
				TreeNode next = stack2.pop();
				System.out.print(next.data + " ");
				if (next.right != null)
					stack1.add(next.right);
				if (next.left != null)
					stack1.add(next.left);

				bothEmpty = false;
			}
			System.out.println();
		}
	}

	/**
	 * revert output wrt normal level order traversal.
	 */
	public void printReverseLevelOrder() {
		System.out.println("Reverse level order output: ");
		TreeNode crawler = null;
		List<TreeNode> queue = new ArrayList<TreeNode>();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		queue.add(root);
		while (!queue.isEmpty()) {
			crawler = queue.remove(0);
			stack.add(crawler);

			if (crawler.left != null) {
				queue.add(crawler.left);
			}
			if (crawler.right != null) {
				queue.add(crawler.right);
			}
		}
		while (!stack.isEmpty()) {
			System.out.print(stack.pop().data + " ");
		}
	}

	/**
	 * One liner printing.
	 * 
	 * TODO can be made multiliner. By finishing up at each level add a marker
	 * element(null) to mark end of a level.
	 */
	public void printNormalLevelOrder() {
		System.out.println("Level order output: ");
		TreeNode crawler = null;
		List<TreeNode> queue = new ArrayList<TreeNode>();
		queue.add(root);
		while (!queue.isEmpty()) {
			crawler = queue.remove(0);
			System.out.print(crawler.data + " ");
			if (crawler.left != null)
				queue.add(crawler.left);
			if (crawler.right != null)
				queue.add(crawler.right);
		}
	}

	public TreeNode getTree() {
		return root;
	}

	/**
	 * check if two trees are mirror images of each other.
	 */
	public boolean isMirrorImage(TreeNode node1, TreeNode node2) {
		if (node1 == null && node2 == null)
			return true;
		if ((node1 != null && node2 == null)
				|| (node2 != null && node1 == null)) {
			return false;
		}
		// not necessary to check the value for calculating mirroImages;
		return isMirrorImage(node1.left, node2.left)
				&& isMirrorImage(node1.right, node2.right);
	}

	/**
	 * finds two nodes whose sum is equals to the given Value.
	 * 
	 * @param sum
	 */
	public void findKeySum(int sum) {
		Stack<TreeNode> stackIn = new Stack<TreeNode>();
		Stack<TreeNode> stackRev = new Stack<TreeNode>();
		TreeNode inCur = root;
		TreeNode revCur = root;

		boolean stepIn = true;
		boolean stepRev = true;
		// this is BST. So iterate In Order and Reverse Order
		while (true) {

			if (stepIn) {
				while (inCur != null) {
					stackIn.push(inCur);
					inCur = inCur.left;
				}
				if (stackIn.isEmpty())
					break;
				inCur = stackIn.pop();// store smallest node
				stepIn = false;
			}
			if (stepRev) {
				while (revCur != null) {
					stackRev.push(revCur);
					revCur = revCur.right;
				}
				if (stackRev.isEmpty())
					break;
				revCur = stackRev.pop(); // store largest node
				stepRev = false;
			}

			if (inCur != null && revCur != null && revCur != inCur) {
				if (revCur.data + inCur.data == sum) {
					System.out.println(inCur.data + " & " + revCur.data);
					return;
				}
				if (revCur.data + inCur.data > sum) {
					stepRev = true;
					revCur = revCur.left;
				} else {
					stepIn = true;
					inCur = inCur.right;
				}
			} else {
				break;
			}
		}
		System.out.println("No such pair exists");
	}

	/**
	 * Iterative in-order traversal.
	 * 
	 * O(N) 
	 */
	public void traverseInorder() {
		Stack<TreeNode> stack = new Stack<>();
		TreeNode node = root;
		while (true) {
			while (node != null) {
				stack.push(node);
				node = node.left;
			}
			if (stack.isEmpty())
				break;
			node = stack.pop();
			System.out.print(" " + node.data);
			node = node.right;
		}
	}

	/**
	 * traverse Reverse order [R N L]<br/>
	 * NOTE: each step of the outer while loop ..leads to next greatest value
	 * which smaller than previous.
	 * 
	 * O(N) on an average run over all nodes of tree.
	 */
	public void traverseReverseInorder() {
		Stack<TreeNode> stack = new Stack<>();
		TreeNode node = root;
		while (true) {
			while (node != null) {
				stack.push(node);
				node = node.right;
			}

			if (stack.isEmpty())
				break;
			node = stack.pop();
			System.out.print(" " + node.data);
			node = node.left;
		}
	}

	/**
	 * inserts into BST
	 */
	public TreeNode insert(TreeNode node, int value) {
		if (node == null) {
			node = new TreeNode(value);
			return node;
		} else {
			if (node.data < value) {
				node.right = insert(node.right, value);
			} else {
				node.left = insert(node.left, value);
			}
			return node;
		}
	}

	/**
	 * creates BST from array
	 */
	public TreeNode createBSTree(int[] array) {
		if (root == null) {
			for (int x : array) {
				root = insert(root, x);
			}
		}
		return root;
	}

}

class TreeNode {
	int data;

	TreeNode left;
	TreeNode right;

	/**
	 * @param data
	 */
	public TreeNode(int data) {
		super();
		this.data = data;
		left = null;
		right = null;
	}

}