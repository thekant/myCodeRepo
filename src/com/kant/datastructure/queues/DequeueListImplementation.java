/**
 * 
 */
package com.kant.datastructure.queues;

import com.kant.datastructure.list.ListDNode;

/**
 * TESTED
 * 
 * @author shaskant
 *
 */
public class DequeueListImplementation<T> extends AbstractQueue<T> implements
		Dequeue<T> {
	private ListDNode<T> front, rear;

	public DequeueListImplementation() {
		super();
		front = rear = null;
	}

	@Override
	public void enQueue(T data) throws OverFlowException {
		ListDNode<T> temp = new ListDNode<>(data);
		size++;
		if (isEmpty()) {
			rear = front = temp;
			return;
		}
		rear.setNext(temp);
		temp.setPrev(rear);
		rear = temp;
	}

	@Override
	public T deQueue() throws UnderFlowException {
		if (isEmpty()) {
			throw new UnderFlowException("no items on queue");
		}
		T data = front.getData();
		front.setPrev(null);
		front = front.getNext();
		if (front == null)
			rear = null;
		size--;
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
	public boolean isEmpty() {
		return (front == null && rear == null) ? true : false;
	}

	@Override
	public boolean isFull() {
		return false;
	}

	@Override
	public void insertFront(T data) {
		ListDNode<T> temp = new ListDNode<T>(data);
		if (isEmpty()) {
			front = rear = temp;
			return;
		}
		temp.setNext(front);
		front.setPrev(temp);
		front = temp;
		size++;
	}

	@Override
	public void insertLast(T data) throws OverFlowException {
		enQueue(data);
	}

	@Override
	public T deleteFront() throws UnderFlowException {
		return deQueue();
	}

	@Override
	public T deleteLast() throws UnderFlowException {
		if (isEmpty()) {
			throw new UnderFlowException("queue is empty");
		}
		T temp = rear.getData();
		rear = rear.getPrev();
		if (rear != null)
			rear.setNext(null);
		else
			front = null;
		size--;
		return temp;
	}

	/**
	 * 
	 * @param args
	 * @throws OverFlowException
	 */
	public static void main(String[] args) throws OverFlowException {
		Dequeue<String> dequeue = new DequeueListImplementation<String>();
		dequeue.insertFront("this3");
		dequeue.insertFront("this2");
		dequeue.insertFront("this1");
		dequeue.insertLast("is");
		dequeue.insertLast("shashi1");
		dequeue.insertLast("shashi2");
		dequeue.insertLast("shashi3");

		try {
			dequeue.deleteFront();
			dequeue.deleteLast();
			dequeue.deleteLast();
			dequeue.deleteLast();
			dequeue.deleteLast();
			dequeue.deleteLast();
			dequeue.deleteLast();
			dequeue.deleteLast();
			System.out.println(dequeue.getFront());
			System.out.println(dequeue.getRear());
		} catch (UnderFlowException e) {
			System.out.println(e.getMessage());
		}
	}

}
