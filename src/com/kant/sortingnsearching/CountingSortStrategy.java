/**
 * 
 */
package com.kant.sortingnsearching;

import java.util.Arrays;

/**
 * Counting sort implementation.
 * 
 * @author shashi
 * 
 */
public class CountingSortStrategy {
	final static int RANGE = 101;
	int[] counts;

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		CountingSortStrategy strategy = new CountingSortStrategy();
		int[] output = strategy.sort(new int[] { 3, 2, 6, 63, 8, 3, 99, 12, 1,
				2, 1, 2, 4, 3 });
		for (int index = 0; index < output.length; index++) {
			if (index >= 1)
				System.out.print(",");
			System.out.print(output[index]);
		}

	}

	public CountingSortStrategy() {
		counts = new int[RANGE];
		Arrays.fill(counts, 0);
	}

	/**
	 * 
	 * @param dataArray
	 * @return
	 */
	public int[] sort(int[] dataArray) {
		for (int item : dataArray) {
			counts[item]++;
		}
		// now counts will hold the location of every element in the output
		for (int index = 1; index < RANGE; index++) {
			counts[index] += counts[index - 1];
		}

		int[] outputArray = new int[dataArray.length];
		Arrays.fill(outputArray, 0);
		for (int index = 0; index < dataArray.length; index++) {
			counts[dataArray[index]]--;
			outputArray[counts[dataArray[index]]] = dataArray[index];
		}
		return outputArray;
	}
}
