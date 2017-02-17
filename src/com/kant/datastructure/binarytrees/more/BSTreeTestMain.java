package com.kant.datastructure.binarytrees.more;

import java.util.Iterator;
import java.util.List;

public class BSTreeTestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		setUpAndTestBSTTree();
	}

	/**
	 * 
	 */
	private static void setUpAndTestBSTTree() {
		int dataArray[] = { 5, 2, 7, 1, 3, 6, 8 };
		BSTTree<Integer> tree = new BSTTree<>();
		for (int data : dataArray) {
			tree.insertToBStTree(data);
		}

		// tree.traverseBSTInorder();
		for (int i = 1; i <= tree.getMaxdepth(); i++) {
			tree.printGivenLevel(i);
			System.out.println();
		}
		System.out.println("\n[LCA]: " + tree.getLowestCommonAncestor(8, 6));
		System.out.println(tree.isBSTTree());

		List<List<Node<Integer>>> result = tree.getListOfLevels();
		Iterator<List<Node<Integer>>> iterator = result.iterator();
		while (iterator.hasNext()) {
			List<Node<Integer>> thisLevel = iterator.next();
			Iterator<Node<Integer>> thisLevelIterator = thisLevel.iterator();
			while (thisLevelIterator.hasNext()) {
				Node<Integer> item = thisLevelIterator.next();
				System.out.print(" " + item.getItem());
			}
			System.out.println();
		}
	}
}
