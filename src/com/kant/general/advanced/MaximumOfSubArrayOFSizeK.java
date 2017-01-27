/**
 * 
 */
package com.kant.general.advanced;

import java.util.LinkedList;

/**
 * http://www.geeksforgeeks.org/maximum-of-all-subarrays-of-size-k/
 * 
 * @author shashi
 * 
 */
public class MaximumOfSubArrayOFSizeK {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int arr[] = { 12, 1, 78, 92, 90, 89, 56 };
		printMaxs(arr, 3);
	}

	/**
	 * Maintain a dequeue in decreasing order.
	 * 
	 * @param input
	 * @param k
	 */
	private static void printMaxs(int[] input, int k) {
		LinkedList<Integer> dequeue = new LinkedList<>();
		int i = 0;
		// insert at rear end if value is smaller than rear End else pop it.
		// NOTE: maintain decreasing order Qs >...>.. Qr.
		for (; i < k; i++) {
			while (!dequeue.isEmpty() && input[dequeue.getLast()] <= input[i])
				dequeue.pollLast();

			dequeue.addLast(i);
		}

		for (; i < input.length; i++) {
			System.out.println("Window [" + (i - k + 1) + "] => "
					+ input[dequeue.getFirst()]);

			// remove out of window elements
			while (!dequeue.isEmpty() && dequeue.getFirst() <= i - k)
				dequeue.pollFirst();

			// normal procedure : insert at rear end if val is smaller than rear
			// end item else pop it.
			while (!dequeue.isEmpty() && input[dequeue.getLast()] <= input[i])
				dequeue.pollLast();

			dequeue.addLast(i);
		}
		System.out.println("Window [" + (i - k + 1) + "] => "
				+ input[dequeue.getFirst()]);
	}
}
