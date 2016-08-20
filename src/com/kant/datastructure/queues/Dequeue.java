package com.kant.datastructure.queues;

public interface Dequeue<T> extends Queue<T> {
	/**
	 * Adds an item at the front of Dequeue.
	 * 
	 * @param data
	 * @throws OverFlowException
	 */
	void insertFront(T data) throws OverFlowException;

	/**
	 * Adds an item at the rear of Dequeue.
	 * 
	 * @param data
	 * @throws OverFlowException
	 */
	void insertLast(T data) throws OverFlowException;

	/**
	 * Deletes an item from front of Deque
	 * 
	 * @return
	 * @throws UnderFlowException
	 */
	T deleteFront() throws UnderFlowException;

	/**
	 * Deletes an item from rear of Deque.
	 * 
	 * @return
	 * @throws UnderFlowException
	 */
	T deleteLast() throws UnderFlowException;

	/**
	 * Gets the front item from queue.
	 * 
	 * @return
	 * @throws UnderFlowException
	 */
	T getFront() throws UnderFlowException;

	/**
	 * Gets the last item from queue.
	 * 
	 * @return
	 * @throws UnderFlowException
	 */
	T getRear() throws UnderFlowException;

	/**
	 * 
	 * @return
	 */
	boolean isEmpty();

	/**
	 * 
	 * @return
	 */
	boolean isFull();
}
