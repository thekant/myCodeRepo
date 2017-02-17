package com.kant.datastructure.binarytrees.more;

/**
 * Represents a Tree node with left right and parent pointer.
 * 
 * @author shashi
 * 
 * @param <E>
 */
public class Node<E extends Number> {
	private E item;
	public Node<E> left = null;
	public Node<E> right = null;
	public Node<E> parent = null;

	/**
	 * 
	 * @param data
	 */
	public Node(E data) {
		this.item = data;
	}

	/**
	 * 
	 * @param item
	 * @param left
	 * @param right
	 * @param parent
	 */
	public Node(E item, Node<E> left, Node<E> right, Node<E> parent) {
		super();
		this.item = item;
		this.left = left;
		this.right = right;
		this.parent = parent;
	}

	/**
	 * @return the item
	 */
	public E getItem() {
		return item;
	}

	/**
	 * @param item
	 *            the item to set
	 */
	public void setItem(E item) {
		this.item = item;
	}

}
