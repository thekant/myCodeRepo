/**
 * 
 */
package com.kant.datastructure.queues;

/**
 * @author shaskant
 *
 */
public abstract class AbstractQueue<T> implements Queue<T> {
	protected int size;
	protected int capacity; // default to -1 leads unlimited size

	/**
	 * 
	 */
	public AbstractQueue(int capacity) {
		this.capacity = capacity;
		size = 0;
	}

	public AbstractQueue() {
		this.capacity = -1;
		size = 0;
	}

	protected boolean isEmpty() {
		return (size == 0) ? true : false;
	}

	protected boolean isFull() {
		if (capacity == -1)
			return false;
		return (size == capacity) ? true : false;
	}

	protected int incrIndex(int val) {
		if (capacity == -1)
			return val + 1;
		return (val + 1) % capacity;
	}

	protected void logMessage(String head, String message) {
		System.out.println("\n[" + head + "]:" + message);
	}

}
