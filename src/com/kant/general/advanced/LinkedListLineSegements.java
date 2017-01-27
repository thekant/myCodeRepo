package com.kant.general.advanced;

/**
 * http://www.geeksforgeeks.org/given-linked-list-line-segments-remove-middle-
 * points/
 * 
 * Given a linked list of co-ordinates where adjacent points either form a
 * vertical line or a horizontal line. Delete points from the linked list which
 * are in the middle of a horizontal or vertical line.
 * 
 * 
	Input:  (0,10)->(1,10)->(5,10)->(7,10)
	                                  |
	                                (7,5)->(20,5)->(40,5)
	Output: Linked List should be changed to following
	        (0,10)->(7,10)
	                  |
	                (7,5)->(40,5) 
	The given linked list represents a horizontal line from (0,10) 
	to (7, 10) followed by a vertical line from (7, 10) to (7, 5), 
	followed by a horizontal line from (7, 5) to (40, 5).
	
	Input:     (2,3)->(4,3)->(6,3)->(10,3)->(12,3)
	Output: Linked List should be changed to following
	    (2,3)->(12,3) 
	There is only one vertical line, so all middle points are removed.
 * 
 * @author shashi
 * 
 */
public class LinkedListLineSegements {

	private NodeX head;

	public LinkedListLineSegements(NodeX root) {
		head = root;
	}

	/**
	 * 0,10)->(1,10)->(5,10)->(7,10) <br/>
	 * |<br/>
	 * (7,5)->(20,5)->(40,5)
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		NodeX head = new NodeX(0, 10);
		head.next = new NodeX(1, 10);
		head.next.next = new NodeX(5, 10);
		head.next.next.next = new NodeX(7, 10);
		head.next.next.next.next = new NodeX(7, 5);
		head.next.next.next.next.next = new NodeX(20, 5);
		head.next.next.next.next.next.next = new NodeX(40, 5);
		LinkedListLineSegements prob = new LinkedListLineSegements(head);
		prob.removeMiddlePoints();
		prob.displayList();

	}

	/**
	 * removes middle points from line segment set
	 * 
	 * @param head
	 */
	public void removeMiddlePoints() {
		//select 3 points
		// if all have some attr matching delete second.
		NodeX first = head;
		NodeX second = (first != null) ? first.next : null;
		NodeX cur = (second != null) ? second.next : null;
		
		while (cur != null) {
			if (first.x == second.x && second.x == cur.x || first.y == second.y
					&& second.y == cur.y) {
				first.next = cur;
				second = cur;
			} else {
				first = second;
				second = cur;
			}
			cur = cur.next;
		}
	}

	/**
	 * 
	 */
	public void displayList() {
		NodeX cur = head;
		while (cur != null) {
			System.out.print(cur.x + " " + cur.y + " | ");
			cur = cur.next;
		}
	}
}

/**
 * 
 * @author shaskant
 *
 */
class NodeX {
	int x;
	int y;
	NodeX next;

	/**
	 * @param x
	 * @param y
	 */
	public NodeX(int x, int y) {
		this.x = x;
		this.y = y;
		next = null;
	}

}