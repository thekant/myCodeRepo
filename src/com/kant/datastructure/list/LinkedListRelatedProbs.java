/**
 * 
 */
package com.kant.datastructure.list;

/**
 * DONE
 * 
 * @author shashi
 * 
 */
public class LinkedListRelatedProbs {
	Node head;

	/**
	 * 
	 */
	public LinkedListRelatedProbs() {
		head = null;
	}

	public void insertToList(int value) {
		Node newNode = new Node(value);
		newNode.next = head;
		head = newNode;
	}

	public void reverseList() {
		head = reverseList(head);
	}

	/**
	 * prev current nextInLine ==>
	 * 
	 * Works by reversing pointers
	 */
	private Node reverseList(Node head) {
		if (head == null || head.next == null)
			return head;
		Node prev = null;
		Node current = head;
		Node nextInLine = null;
		while (current != null) {
			nextInLine = current.next;
			// reverse the next link to point back.
			current.next = prev;

			prev = current;
			current = nextInLine;
		}
		head = prev;
		return head;
	}

	/**
	 * 
	 * @param k
	 */
	public void reverseKElements(int k) {
		if (k == 1) {
			head = reverseList(head);
			return;
		}
		Node start = head; // points to first element of the sublist
		Node prevSlotTail = null;
		Node cur = head;
		Node prev = cur;
		int count = 0;
		while (cur != null) {
			if (count < k) {
				count++;
				prev = cur;
				cur = cur.next;
			} else {
				prev.next = null;
				Node subHead = reverseList(start);
				if (prevSlotTail == null) {
					head = subHead;
				} else {
					prevSlotTail.next = subHead;
				}
				prevSlotTail = start;// start should be at end of reverted list.
				count = 0;
				start = cur;
			}
		}
	}

	/**
	 * 
	 */
	public void display() {
		Node cur = head;
		while (cur != null) {
			System.out.print(cur.data + " ");
			cur = cur.next;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LinkedListRelatedProbs prob = new LinkedListRelatedProbs();
		int[] data = { 1, 4, 2, 6, 5, 9, 77, 0, 8, 23 };
		for (int item : data)
			prob.insertToList(item);
		// prob.reverseList();
		prob.display();
		prob.reverseKElements(2);
		System.out.println();
		prob.display();
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

	public Node(int val) {
		this.data = val;
		next = null;
	}
}
