/**
 * 
 */
package com.kant.datastructure.queues;

/**
 * http://quiz.geeksforgeeks.org/queue-set-1introduction-and-array-
 * implementation/
 * 
 * @author shaskant
 *
 */
public interface Queue<T> {

	/**
	 * adds data at rear.
	 * 
	 * @param data
	 */
	void enQueue(T data) throws OverFlowException;

	/**
	 * removes data from front
	 * 
	 * @return
	 */
	T deQueue() throws UnderFlowException;

	/**
	 * gets front item
	 * 
	 * @return
	 */
	T getFront() throws UnderFlowException;

	/**
	 * gets rear item
	 * 
	 * @return
	 */
	T getRear() throws UnderFlowException;
}
