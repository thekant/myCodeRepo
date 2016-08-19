package com.kant.datastructure.list;

/**
 * 
 * @author shaskant
 *
 * @param <T>
 */
public class ListDNode<T> {
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