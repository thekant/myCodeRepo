package com.kant.datastructure.list;
/**
 * Data structure to hold list
 * 
 * @author shaskant
 *
 * @param <T>
 */
public class ListNode<T> {
	private T data;
	private ListNode<T> next;

	public ListNode() {
		this(null, null);
	}

	public ListNode(T data) {
		this(data, null);
	}

	public ListNode(T data, ListNode<T> next) {
		this.data = data;
		this.next = next;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public ListNode<T> getNext() {
		return next;
	}

	public void setNext(ListNode<T> next) {
		this.next = next;
	}
}
