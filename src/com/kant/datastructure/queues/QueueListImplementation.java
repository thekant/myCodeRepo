/**
 * 
 */
package com.kant.datastructure.queues;

import com.kant.datastructure.list.ListNode;

/**
 * 
 * TESTED
 * 
 * @author shaskant
 *
 */
public class QueueListImplementation<T> extends AbstractQueue<T> {
	private ListNode<T> front, rear;

	/**
	 * capacity will be ignored
	 * 
	 * @param capacity
	 */
	public QueueListImplementation() {
		front = null;
		rear = null;
	}

	@Override
	public void enQueue(T data) throws OverFlowException {
		ListNode<T> temp = new ListNode<>(data);
		size++;
		if (rear == null) {
			rear = front = temp;
			return;
		}
		rear.setNext(temp);
		rear = temp;

	}

	@Override
	public T deQueue() throws UnderFlowException {
		if (isEmpty()) {
			throw new UnderFlowException("no items on queue");
		}
		T data = front.getData();
		front = front.getNext();
		size--;
		if (size == 0)
			rear = null;
		return data;
	}

	@Override
	public T getFront() throws UnderFlowException {
		if (isEmpty()) {
			throw new UnderFlowException("no items on queue");
		}
		return front.getData();
	}

	@Override
	public T getRear() throws UnderFlowException {
		if (isEmpty()) {
			throw new UnderFlowException("no items on queue");
		}
		return rear.getData();
	}

	@Override
	protected boolean isEmpty() {
		return (front == null && rear == null) ? true : false;
	}

	@Override
	protected boolean isFull() {
		return false;
	}

	public static void main(String[] args) throws OverFlowException {
		QueueListImplementation<String> queue = new QueueListImplementation<String>();
		queue.enQueue("this");
		queue.enQueue("is");
		queue.enQueue("shashi");
		try {
			System.out.println(queue.deQueue());
			System.out.println(queue.deQueue());
			System.out.println(queue.deQueue());
			System.out.println(queue.deQueue());
		} catch (UnderFlowException e) {
			System.out.println(e.getMessage());
		}

	}
}
