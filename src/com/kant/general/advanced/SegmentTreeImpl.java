/**
 * 
 */
package com.kant.general.advanced;

/**
 * NOTE: Once we have built a segtree we cannot change its structure i.e., its
 * structure is static. We can update the values of nodes but we cannot change
 * its structure. Segment tree is recursive in nature. Because of its recursive
 * nature, Segment tree is very easy to implement. Segment tree provides two
 * operations:
 * 
 * Update: In this operation we can update an element in the Array and reflect
 * the corresponding change in the Segment tree.
 * 
 * Query: In this operation we can query on an interval or segment and return
 * the answer to the problem on that particular interval.
 * 
 * 
 * https://www.hackerearth.com/practice/notes/segment-tree-and-lazy-propagation/
 * 
 * @author shaskant
 *
 */
public class SegmentTreeImpl {
	private int[] tree;
	private int N;
	private int[] dataArray;// not necessary to store

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int arr[] = { 1, 3, 5, 7, 9, 11 };
		SegmentTreeImpl segmentTree = new SegmentTreeImpl(arr);
		// MyUtil.printArrayInt(segmentTree.tree);
		System.out.println(segmentTree.getSumForRange(1, 3));
	}

	/**
	 * 
	 */
	public SegmentTreeImpl(int[] A) {
		N = A.length;
		dataArray = A;
		/**
		 * Height of segment tree
		 */
		int x = (int) (Math.ceil(Math.log(N) / Math.log(2)));

		/**
		 * Maximum size of segment tree
		 */
		int max_size = 2 * (int) Math.pow(2, x) - 1;

		tree = new int[max_size];
		build(0, 0, N - 1);
	}

	/**
	 * Complexity of build() is O(N).
	 * 
	 * @param A
	 *            data array
	 * @param segIndx
	 *            segment tree index
	 * @param start
	 *            for data array
	 * @param end
	 *            for data array
	 */
	public void build(int segIndx, int start, int end) {
		if (start == end) {
			/**
			 * Leaf node will have a single element
			 */
			tree[segIndx] = dataArray[start];
		} else {
			int mid = getMid(start, end);
			build(2 * segIndx + 1, start, mid);
			build(2 * segIndx + 2, mid + 1, end);
			// Internal node will have the sum of both of its children
			tree[segIndx] = tree[2 * segIndx + 1] + tree[2 * segIndx + 2];
		}
	}

	/**
	 * return sum of values between indexes
	 * 
	 * @param l
	 *            start index of Input Array
	 * @param r
	 *            end index of Input Array
	 * @return
	 */
	public int getSumForRange(int l, int r) {
		return query(0, 0, N - 1, l, r);
	}

	private int query(int node, int start, int end, int l, int r) {
		if (r < start || end < l) {
			/**
			 * range represented by a node is completely outside the given range
			 */
			return 0;
		}
		if (l <= start && end <= r) {
			/**
			 * range represented by a node is completely inside the given range
			 */
			return tree[node];
		}
		/**
		 * range represented by a node is partially inside and partially outside
		 * the given range
		 */
		int mid = getMid(start, end);
		int p1 = query(2 * node + 1, start, mid, l, r);
		int p2 = query(2 * node + 2, mid + 1, end, l, r);
		return (p1 + p2);
	}

	/**
	 * Complexity of update will be O(logN).
	 */
	public void updateValue(int index, int value) {
		// Check for erroneous input index
		if (index < 0 || index > N - 1) {
			System.out.println("Invalid Input");
			return;
		}

		// Get the difference between new value and old value
		int diff = value - dataArray[index];
		update(0, 0, N - 1, index, diff);
	}

	private void update(int node, int start, int end, int idx, int val) {
		if (start == end) {
			// Leaf node
			dataArray[idx] += val;// not necessary
			tree[node] += val;
		} else {
			int mid = getMid(start, end);
			if (start <= idx && idx <= mid) {
				// go to left child
				update(2 * node + 1, start, mid, idx, val);
			} else {
				// go to right child
				update(2 * node + 2, mid + 1, end, idx, val);
			}
			/**
			 * Internal node will have the sum of both of its children
			 */
			tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
		}
	}

	/**
	 * update a range by diff amount.
	 */
	public void updateRangeByVal(int l, int r, int diff) {
		updateRange(0, 0, N - 1, l, r, diff);
	}

	private void updateRange(int segIndx, int start, int end, int l, int r,
			int val) {
		// out of range
		if (start > end || start > r || end < l)
			return;

		// Current node is a leaf node
		if (start == end) {
			// Add the difference to current node
			tree[segIndx] += val;
		} else {
			// If not a leaf node, recur for children.
			int mid = (start + end) / 2;
			updateRange(segIndx * 2, start, mid, l, r, val);
			updateRange(segIndx * 2 + 1, mid + 1, end, l, r, val);

			// Use the result of children calls to update this node
			tree[segIndx] = tree[segIndx * 2] + tree[segIndx * 2 + 1];
		}
	}

	/**
	 * @param start
	 * @param end
	 * @return
	 */
	private int getMid(int start, int end) {
		return start + (end - start) / 2;
	}
}
