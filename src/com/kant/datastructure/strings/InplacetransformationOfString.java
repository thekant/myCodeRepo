/**
 * 
 */
package com.kant.datastructure.strings;

/**
 * http://www.geeksforgeeks.org/an-in-place-algorithm-for-string-transformation/
 * 
 * Let len be the length of the string. If we observe carefully, we find that
 * the new index is given by below formula:
 * 
 * if( oldIndex is odd ) <br/>
 * newIndex = len / 2 + oldIndex / 2; <br/>
 * else newIndex = oldIndex / 2;
 * 
 * @author shaskant
 *
 */
public class InplacetransformationOfString {

	private void swap(char[] data, int s, int d) {
		char t = data[s];
		data[s] = data[d];
		data[d] = t;
	}

	private char[] reverseString(char[] data, int low, int high) {
		while (low < high) {
			swap(data, low, high);
			low++;
			high--;
		}
		return data;
	}

	/**
	 * Cycle leader algorithm to move all even positioned elements at the end.
	 * 
	 * NOTE: Cycle leader iteration algorithm will be applied starting from the
	 * indices of the form 3^k, starting with k = 0.
	 * 
	 * Below are the steps:
	 * 
	 * 1. Find new position for item at position i. Before putting this item at
	 * new position, keep the back-up of element at new position. Now, put the
	 * item at new position.
	 * 
	 * 2. Repeat step#1 for new position until a cycle is completed, i.e. until
	 * the procedure comes back to the starting position.
	 * 
	 * 3. Apply cycle leader iteration algorithm to the next index of the form
	 * 3^k. Repeat this step until 3^k < len.
	 * 
	 * NOTE:
	 * http://stackoverflow.com/questions/22424985/why-does-array-size-have
	 * -to-be-3k1-for-cycle-leader-iteration-algorithm-to-wor
	 * 
	 * @param str
	 * @param start
	 * @param end
	 */
	public void cycleLeader(char[] str, int start, int end) {
		
	}

	private int get3powerK1(int size) {
		int power = 1;
		while (power * 3 + 1 < size)
			power++;
		return power;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		InplacetransformationOfString transformer = new InplacetransformationOfString();
		System.out.println(transformer.reverseString("12345".toCharArray(), 0,
				4));
	}

}
