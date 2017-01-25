/**
 * 
 */
package com.kant.general.advanced;

/**
 * @author shashi
 * 
 */
public class FlattenLinkedList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// data feeding
		Node head = new Node(10);
		Node headChild = new Node(4);
		headChild.next = new Node(20);
		headChild.next.next = new Node(13);
		headChild.next.child = new Node(16);
		headChild.next.child.child = new Node(3);

		head.child = headChild;
		Node cur = head;
		for (int i = 0; i < 4; i++) {
			cur.next = new Node(i);
			cur = cur.next;
		}

		Node headChild1 = new Node(5);
		headChild1.next = new Node(12);
		headChild1.next.next = new Node(17);
		headChild1.next.child = new Node(9);
		headChild1.next.child.child = new Node(6);
		cur.child = headChild1;

		FlattenLinkedList flatUil = new FlattenLinkedList();
		/**
		 * flattening logic here
		 */
		flatUil.flatten(head);

		Node tail = head;
		while (tail != null) {
			System.out.print(tail.data + ",");
			tail = tail.next;
		}

	}

	/**
	 * other way is to use BFS
	 * 
	 * @param head
	 *            pass list's head
	 */
	public void flatten(Node head) {
		Node tail = head;
		if (head == null)
			return;
		// fix the tail, new children will be added at the tail.
		while (tail.next != null)
			tail = tail.next;

		Node cur = head;
		while (cur != null) {
			// loop and check this node has a child
			if (cur.child != null) {
				tail.next = cur.child;
				while (tail.next != null)
					tail = tail.next;

				cur.child = null;
			}
			cur = cur.next;
		}
	}
}

/**
 * 
 * @author shashi
 * 
 */
class Node {
	int data;
	Node next;
	Node child;

	/**
	 * 
	 * @param val
	 */
	public Node(int val) {
		data = val;
		next = null;
		child = null;
	}
}