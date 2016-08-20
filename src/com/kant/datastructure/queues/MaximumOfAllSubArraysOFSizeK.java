/**
 * 
 */
package com.kant.datastructure.queues;

import com.kant.sortingnsearching.MyUtil;

/**
 * http://www.geeksforgeeks.org/maximum-of-all-subarrays-of-size-k/ <br/>
 * <br/>
 * We create a Dequeue, Qi of capacity k, that stores only useful elements of
 * current window of k elements. An element is useful if it is in current window
 * and is greater than all other elements on left side of it in current window.
 * We process all array elements one by one and maintain Qi to contain useful
 * elements of current window and these useful elements are maintained in sorted
 * order. The element at front of the Qi is the largest and element at rear of
 * Qi is the smallest of current window.
 * 
 * @author shaskant
 *
 */
public class MaximumOfAllSubArraysOFSizeK {

	/**
	 * TIP: Maintain reverse sorted order in the queue.
	 * 
	 * @param data
	 * @param k
	 * @return
	 * @throws UnderFlowException
	 * @throws OverFlowException
	 */
	public int[] solve(int[] data, int k) throws UnderFlowException,
			OverFlowException {
		System.out.print("processing input...# ");
		MyUtil.printArrayInt(data);
		System.out.println("window size: " + k);
		int[] result = new int[data.length - k + 1];
		Dequeue<Integer> dq = new DequeueListImplementation<Integer>();
		int i;
		// set up initial k window dequeue
		for (i = 0; i < k; ++i) {
			// all items on left should be greater
			while (!dq.isEmpty() && data[i] >= data[dq.getRear()]) {
				dq.deleteLast();
			}
			dq.insertLast(i);
		}

		for (; i < data.length; ++i) {
			// front will be the max element for previous window.
			result[i - k] = data[dq.getFront()];
			// if element at front is within window
			while (!dq.isEmpty() && i - k >= dq.getFront()) {
				dq.deleteFront();
			}
			// all items on left should be greater
			while (!dq.isEmpty() && data[i] >= data[dq.getRear()]) {
				dq.deleteLast();
			}
			dq.insertLast(i);
		}
		// maximum element of last window
		result[i - k] = data[dq.getFront()];
		return result;
	}

	/**
	 * 
	 * @param args
	 * @throws OverFlowException
	 * @throws UnderFlowException
	 */
	public static void main(String[] args) throws UnderFlowException,
			OverFlowException {
		int[] arr = { 12, 1, 78, 90, 57, 89, 56 };
		MaximumOfAllSubArraysOFSizeK prob = new MaximumOfAllSubArraysOFSizeK();
		int[] result = prob.solve(arr, 3);
		System.out.print("output# ");
		MyUtil.printArrayInt(result);
	}
}
