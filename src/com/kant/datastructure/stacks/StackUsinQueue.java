/**
 * 
 */
package com.kant.datastructure.stacks;

/**
 * http://www.geeksforgeeks.org/implement-stack-using-queue/
 * 
 * Method 1 (By making push operation costly) This method makes sure that newly
 * entered element is always at the front of ‘q1′, so that pop operation just
 * dequeues from ‘q1′. ‘q2′ is used to put every new element at front of ‘q1′.
 * 
 * push(s, x) // x is the element to be pushed and s is stack 1) Enqueue x to q2
 * 2) One by one dequeue everything from q1 and enqueue to q2. 3) Swap the names
 * of q1 and q2 // Swapping of names is done to avoid one more movement of all
 * elements // from q2 to q1.
 * 
 * pop(s) 1) Dequeue an item from q1 and return it. Method 2 (By making pop
 * operation costly) In push operation, the new element is always enqueued to
 * q1. In pop() operation, if q2 is empty then all the elements except the last,
 * are moved to q2. Finally the last element is dequeued from q1 and returned.
 * 
 * push(s, x) 1) Enqueue x to q1 (assuming size of q1 is unlimited).
 * 
 * pop(s) 1) One by one dequeue everything except the last element from q1 and
 * enqueue to q2. 2) Dequeue the last item of q1, the dequeued item is result,
 * store it. 3) Swap the names of q1 and q2 4) Return the item stored in step 2.
 * // Swapping of names is done to avoid one more movement of all elements //
 * from q2 to q1.
 * 
 * @author shaskant
 *
 */
public class StackUsinQueue implements Stack<String> {

	/**
	 * 
	 */
	public StackUsinQueue() {
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String peek() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String pop() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void push(String data) {
		// TODO Auto-generated method stub

	}

}
