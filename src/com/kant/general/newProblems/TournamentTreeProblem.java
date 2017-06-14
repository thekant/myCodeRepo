/**
 * 
 */
package com.kant.general.newProblems;

import java.util.LinkedList;

/**
 * http://www.geeksforgeeks.org/tournament-tree-and-binary-heap/
 * 
 * http://www.geeksforgeeks.org/second-minimum-element-using-minimum-comparisons
 * 
 * 
 * @author shaskant
 *
 */
public class TournamentTreeProblem {
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int arr[] = { 61, 6, 100, 9, 10, 12, 17 };
		TournamentTreeProblem prob = new TournamentTreeProblem();
		System.out.println(prob.findSecondMin(arr));
	}

	/**
	 * 
	 * @param arr
	 * @return
	 */
	public int findSecondMin(int[] arr) {
		int n = arr.length;
		LinkedList<Tnode> list = new LinkedList<>();
		for (int i = 0; i < n; i += 2) {
			Tnode t1 = new Tnode(i);
			Tnode t2 = null;
			if (i + 1 < n) {
				t2 = new Tnode(i + 1);
				Tnode root = (arr[i] < arr[i + 1]) ? new Tnode(i) : new Tnode(
						i + 1);
				root.left = t1;
				root.right = t2;
				list.add(root);
			} else
				list.add(t1);
		}

		while (list.size() != 1) {
			Tnode t1 = list.pollLast();
			Tnode t2 = list.pollLast();
			Tnode root = (arr[t1.idx] < arr[t2.idx]) ? new Tnode(t1.idx)
					: new Tnode(t2.idx);
			root.left = t1;
			root.right = t2;
			list.add(root);
		}
		/**
		 * list.getFirst() --> contains first minimum element.
		 */
		Result result = new Result();
		fetchSecondSmallest(list.getFirst(), arr, result);
		return arr[result.value];
	}

	class Result {
		int value = Integer.MAX_VALUE;
	}

	private void fetchSecondSmallest(Tnode node, int[] arr, Result result) {
		// Base case
		if (node == null || (node.left == null && node.right == null))
			return;
		/**
		 * check with side != root's idx and less than result and move to side
		 * with indx == root's idx
		 */
		if (result.value > arr[node.left.idx] && node.idx != node.left.idx) {
			result.value = node.left.idx;
			fetchSecondSmallest(node.right, arr, result);
		} else if (result.value > arr[node.right.idx]
				&& node.idx != node.right.idx) {
			result.value = node.right.idx;
			fetchSecondSmallest(node.left, arr, result);
		}
	}
}

class Tnode {
	int idx;
	Tnode left;
	Tnode right;

	/**
	 * @param val
	 */
	public Tnode(int val) {
		this.idx = val;
		left = right = null;
	}

}