/**
 * 
 */
package com.kant.general.tofactorIn;

/**
 * @author shashi
 * 
 */
public class ReplaceZeroToGetMax1s {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] data = { 0, 0, 0, 1, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 1 };
		System.out.println(new ReplaceZeroToGetMax1s().findmax1s(data));
	}

	/**
	 * Returns number of 1's that replacement will make.
	 * 
	 * References back to Kadane's algo. [MY Algo]
	 * 
	 * @param data
	 * @return
	 */
	public int findmax1s(int[] data) {
		int max = -1;
		int left = 0, right = 0;
		int lastZeroIndex = -1;
		int resultIndeX = -1;
		for (int countIndex = 0; countIndex < data.length; countIndex++) {
			if (data[countIndex] == 0) {
				if (max < left + right) {
					max = left + right;
					resultIndeX = lastZeroIndex;
				}
				left = right;
				right = 0;
				lastZeroIndex = countIndex;
			} else {
				right++;
			}

		}
		System.out.println("0 to replace at " + resultIndeX);
		return max + 1;// left+ 0->1 + right
	}
}
