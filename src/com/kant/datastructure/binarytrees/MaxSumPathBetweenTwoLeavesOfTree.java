package com.kant.datastructure.binarytrees;

/**
 * 
 * Calculates maximum sum path between any two leaves of tree.
 * 
 * @author shashi
 * 
 */
public class MaxSumPathBetweenTwoLeavesOfTree {

	int result;

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

	}

	// Utility function to find maximum of two integers
	private int max(int a, int b) {
		return (a >= b) ? a : b;
	}

	// A utility function to find the maximum sum between any two leaves.
	// This function calculates two values:
	// 1) Maximum path sum between two leaves which is stored in res.
	// 2) The maximum root to leaf path sum which is returned.
	private int maxPathSumUtil(Node root) {
		// Base case
		if (root == null)
			return 0;

		// Find maximum sum in left and right subtree. Also find
		// maximum root to leaf sums in left and right subtrees
		// and store them in lLPSum and rLPSum
		int lLPSum = maxPathSumUtil(root.left);
		int rLPSum = maxPathSumUtil(root.right);

		// Find the maximum path sum passing through root
		int curr_sum = max((lLPSum + rLPSum + root.data), max(lLPSum, rLPSum));

		// Update res (or result) if needed
		if (result < curr_sum)
			result = curr_sum;

		// Return the maximum root to leaf path sum
		return max(lLPSum, rLPSum) + root.data;
	}

	// The main function which returns sum of the maximum
	// sum path between two leaves. This function mainly uses
	// maxPathSumUtil()
	public int maxPathSum(Node root) {
		result = 0;
		maxPathSumUtil(root);
		return result;
	}

	/**
	 * 
	 * @author shashi
	 * 
	 */
	class Node {
		int data;
		Node left;
		Node right;
	}
}
