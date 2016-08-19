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
	protected int capacity;

	/**
	 * 
	 */
	public AbstractQueue(int capacity) {
		this.capacity = capacity;
	}

	protected boolean isEmpty() {
		return (size == 0) ? true : false;
	}
	
	protected boolean isFull() {
		return (size == capacity) ? true : false;
	}


	protected int incrIndex(int val) {
		return (val + 1) % capacity;
	}

	protected void logMessage(String head, String message) {
		System.out.println("\n[" + head + "]:" + message);
	}

}
