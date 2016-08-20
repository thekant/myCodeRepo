/**
 * 
 */
package com.kant.datastructure.queues;

/**
 * @author shaskant
 *
 */
public class QueueCyclicImplementation extends AbstractQueue<String> {
	private int frontIdx;
	private int rearIdx;
	private String[] dataStore;

	/**
	 * 
	 * @param capacity
	 */
	public QueueCyclicImplementation(int capacity) {
		super(capacity);
		dataStore = new String[capacity];
		size = 0;
		frontIdx = -1;
		rearIdx = -1;
	}

	@Override
	public void enQueue(String data) throws OverFlowException {
		if (!isFull()) {
			rearIdx = incrIndex(rearIdx);
			dataStore[rearIdx] = data;
			if (isEmpty())
				frontIdx = rearIdx;
			size++;
		} else
			throw new OverFlowException(
					"Queue is found to be full to brink while inserting : "
							+ data);
	}

	@Override
	public String deQueue() throws UnderFlowException {
		if (!isEmpty()) {
			String temp = dataStore[frontIdx];
			frontIdx = incrIndex(frontIdx);
			size--;
			return temp;
		}
		throw new UnderFlowException("No data found on queue");
	}

	@Override
	public String getFront() throws UnderFlowException {
		if (!isEmpty()) {
			return dataStore[frontIdx];
		}
		throw new UnderFlowException("No data found on queue");
	}

	@Override
	public String getRear() throws UnderFlowException {
		if (!isEmpty()) {
			return dataStore[rearIdx];
		}
		throw new UnderFlowException("No data found on queue");
	}

	public static void main(String[] args) {
		QueueCyclicImplementation queue = new QueueCyclicImplementation(5);
		try {
			queue.enQueue("this");
			queue.enQueue("is");
			queue.enQueue("shashi");
			queue.enQueue("shashi1");
			queue.enQueue("shashi2");
			queue.enQueue("shashi3");
		} catch (OverFlowException e) {
			System.out.println(e.getMessage());
		}
		try {
			System.out.println(queue.deQueue());
			System.out.println(queue.deQueue());
			System.out.println(queue.deQueue());
			System.out.println(queue.deQueue());
			System.out.println(queue.deQueue());
			System.out.println(queue.deQueue());
			System.out.println(queue.deQueue());
			System.out.println(queue.deQueue());
		} catch (UnderFlowException e) {
			System.out.println(e.getMessage());
		}
	}

}
