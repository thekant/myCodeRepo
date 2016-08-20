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
public class QueueListImplementation extends AbstractQueue<String> {
	private ListNode<String> front, rear;

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
	public void enQueue(String data) throws OverFlowException {
		ListNode<String> temp = new ListNode<>(data);
		if (rear == null) {
			rear = front = temp;
			return;
		}
		rear.setNext(temp);
		rear = temp;
		size++;
	}

	@Override
	public String deQueue() throws UnderFlowException {
		if (isEmpty()) {
			throw new UnderFlowException("no items on queue");
		}
		String data = front.getData();
		front = front.getNext();
		size--;
		return data;
	}

	@Override
	public String getFront() throws UnderFlowException {
		if (isEmpty()) {
			throw new UnderFlowException("no items on queue");
		}
		return front.getData();
	}

	@Override
	public String getRear() throws UnderFlowException {
		if (isEmpty()) {
			throw new UnderFlowException("no items on queue");
		}
		return rear.getData();
	}

	@Override
	protected boolean isEmpty() {
		return (front == null) ? true : false;
	}

	@Override
	protected boolean isFull() {
		return false;
	}

	public static void main(String[] args) throws OverFlowException {
		QueueListImplementation queue = new QueueListImplementation();
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
