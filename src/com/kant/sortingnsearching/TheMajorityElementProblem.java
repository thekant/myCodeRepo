/**
 * 
 */
package com.kant.sortingnsearching;

/**
 * Using Moor's voting algorithm.
 * 
 * DONE
 * 
 * @author shashi
 * 
 */
public class TheMajorityElementProblem {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] data = { 4, 3, 1, 1, 1, 3, 3, 1, 3, 4, 1 };
		TheMajorityElementProblem majorProb = new TheMajorityElementProblem();

		int majorityElement = majorProb.getMajorityElement(data);
		System.out.println(majorProb.isMajority(majorityElement, data));
	}

	/**
	 * @param data
	 */
	public int getMajorityElement(int[] data) {
		int majIndex = 0;
		int count = 1;

		for (int i = 1; i < data.length; i++) {
			if (data[majIndex] == data[i])
				count++;
			else
				count--;

			if (count == 0) {
				majIndex = i;
				count = 1;
			}
		}
		System.out.println(data[majIndex]);
		return data[majIndex];
	}

	/**
	 * If majority element has count > half the size of array
	 * 
	 * if returns false ..it mean there is no majority element in Array
	 */
	public boolean isMajority(int majorityElement, int[] data) {
		int count = 0;
		for (int item : data) {
			if (item == majorityElement)
				count++;
			if ((data.length % 2 == 0) && (count == data.length / 2 + 1)
					|| (count == (data.length + 1) / 2))
				return true;
		}
		return false;
	}

}
