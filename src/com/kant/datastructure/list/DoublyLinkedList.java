/**
 * 
 */
package com.kant.datastructure.list;

/**
 * @author shaskant
 *
 */
public class DoublyLinkedList {
	ListDNode<String> head;

	/**
	 * 
	 * @param value
	 */
	public void insertFront(String value) {
		ListDNode<String> temp = new ListDNode<String>(value);
		if (head != null) {
			head.setPrev(temp);
			temp.setNext(head);
		}
		head = temp;
	}

	/**
	 * 
	 */
	public void reverse() {
		if (head == null || head.getNext() == null)
			return;
		ListDNode<String> cur = head;
		ListDNode<String> temp = null;
		while (cur != null) {
			temp = cur.getNext();
			cur.setNext(cur.getPrev());
			cur.setPrev(temp);
			if (temp == null)
				head = cur;
			cur = temp;

		}
	}

	/**
	 * TESTED
	 */
	public void printList() {
		if (head != null) {
			ListDNode<String> looper = head;

			while (looper != null) {
				System.out.print(looper.getData() + " ");
				looper = looper.getNext();
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DoublyLinkedList dll = new DoublyLinkedList();
		dll.insertFront("1");
		dll.insertFront("2");
		dll.insertFront("3");
		dll.insertFront("4");

		dll.reverse();
		dll.printList();

	}

}

class ListDNode<T> {
	private T data;
	private ListDNode<T> next;
	private ListDNode<T> prev;

	public ListDNode() {
		this(null, null);
	}

	public ListDNode(T data) {
		this(data, null);
	}

	public ListDNode(T data, ListDNode<T> next) {
		this(data, next, null);
	}

	public ListDNode(T data, ListDNode<T> next, ListDNode<T> prev) {
		this.data = data;
		this.next = next;
		this.setPrev(prev);
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public ListDNode<T> getNext() {
		return next;
	}

	public void setNext(ListDNode<T> next) {
		this.next = next;
	}

	/**
	 * @return the prev
	 */
	public ListDNode<T> getPrev() {
		return prev;
	}

	/**
	 * @param prev
	 *            the prev to set
	 */
	public void setPrev(ListDNode<T> prev) {
		this.prev = prev;
	}
}