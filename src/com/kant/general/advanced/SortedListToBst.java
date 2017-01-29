/**
 * 
 */
package com.kant.general.advanced;

/**
 * 
 * @author shashi
 * 
 */
public class SortedListToBst {
	private TreeNodeX root;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ListNode head = new ListNode(0);
		ListNode cur = head;
		for (int x = 1; x < 6; x++) {
			cur.next = new ListNode(x);
			cur = cur.next;
		}

		SortedListToBst tree = new SortedListToBst();
		tree.buildTree(head);
		tree.printInorder();
	}

	public void buildTree(ListNode head) {
		root = buildTreeFromList(head);
	}

	/**
	 * Divides list into two equal parts. take middle and make it root. Left
	 * part gives left subtree and right part gives right subtree.
	 * 
	 * @param head
	 * @return
	 */
	private TreeNodeX buildTreeFromList(ListNode head) {
		ListNode fast = head, mid = head, prev = null;
		if (head == null)
			return null;

		while (fast != null && fast.next != null) {
			prev = mid;
			mid = mid.next;
			fast = fast.next.next;
		}

		TreeNodeX thisRoot = new TreeNodeX(mid.data);
		if (prev != null) {
			prev.next = null;
			thisRoot.lnode = buildTreeFromList(head);
		}
		if (mid.next != null) {
			thisRoot.rnode = buildTreeFromList(mid.next);
		}

		return thisRoot;
	}

	public void printInorder() {
		inOrderTraverse(root);
	}

	/**
	 * 
	 * @param node
	 */
	private void inOrderTraverse(TreeNodeX node) {
		if (node != null) {
			inOrderTraverse(node.lnode);
			System.out.print(node.data + " ");
			inOrderTraverse(node.rnode);
		}
	}

}

class ListNode {
	int data;
	ListNode next;

	/**
	 * @param data
	 */
	public ListNode(int data) {
		this.data = data;
	}
}

class TreeNodeX {
	int data;
	TreeNodeX lnode;
	TreeNodeX rnode;

	public TreeNodeX(int value) {
		this.data = value;
		lnode = null;
		rnode = null;
	}

}
