/**
 * 
 */
package com.kant.datastructure.queues;

import com.kant.sortingnsearching.MyUtil;

/**
 * @author shaskant
 *
 */
public class GenerateBinaryNumbers1toN {

	/**
	 * 
	 * @param n
	 * @return
	 * @throws OverFlowException
	 * @throws UnderFlowException
	 */
	public String[] solve(int n) throws OverFlowException, UnderFlowException {
		String[] result = new String[n];
		System.out.println("input # " + n);
		Queue<String> dequeue = new QueueListImplementation<String>();
		dequeue.enQueue("1");
		for (int count = 0; count < n; count++) {
			result[count] = dequeue.deQueue();
			dequeue.enQueue(result[count]+"0");
			dequeue.enQueue(result[count]+"1");
		}
		return result;
	}

	/**
	 * 
	 * @param args
	 * @throws OverFlowException
	 * @throws UnderFlowException
	 */
	public static void main(String[] args) throws OverFlowException, UnderFlowException {
		GenerateBinaryNumbers1toN prob = new GenerateBinaryNumbers1toN();

		System.out.print("output # ");
		MyUtil.printArrayType(prob.solve(10));

	}

}
