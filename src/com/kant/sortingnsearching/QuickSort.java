package com.kant.sortingnsearching;

import java.util.Random;

/**
 * For linkedList version :
 * 1. Consider tail as the Pivot 
 * 2. scan from head upto pivot.
 *      if(node.value > pivot.value)
 *         append node by the tail.
 * 
 * @author shaskant
 *
 */
public class QuickSort {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int[] data = { 5, 2, 7, 1, 4, 9, 0, 3 };
		// doQuickSortIterative(data);
		sort(data);
		MyUtil.printArrayInt(data);
	}

	/**
	 * sorts integer array.
	 * 
	 * @param values
	 */
	public static void sort(int[] values) {
		doQuickSort(values, 0, values.length - 1);
	}

	/**
	 * http://quiz.geeksforgeeks.org/quick-sort/ <br/>
	 * There are many different versions of quickSort that pick pivot in
	 * different ways.
	 * 
	 * Always pick first element as pivot.
	 * 
	 * Always pick last element as pivot (implemented below)
	 * 
	 * Pick a random element as pivot.
	 * 
	 * Pick median as pivot.
	 */
	private static void doQuickSort(int[] values, int low, int high) {
		if (low < high) {
			int partIndx = partition(values, low, high);
			doQuickSort(values, low, partIndx - 1);
			doQuickSort(values, partIndx + 1, high);
		}
	}

	/**
	 * Iterative version of Quick Sort.
	 * 
	 * @param values
	 */
	public static void doQuickSortIterative(int[] values) {
		// create auxiliary stack
		int stack[] = new int[values.length];

		// initialize top of stack
		int top = -1;

		// push initial values in the stack
		stack[++top] = 0; // low
		stack[++top] = values.length - 1; // highs

		// keep popping elements until stack is not empty
		while (top >= 0) {
			// pop high and low
			int high = stack[top--];
			int low = stack[top--];

			// set pivot element at it's proper position
			int p = partition(values, low, high);

			// If there are elements on left side of pivot,
			// then push left side to stack
			if (p - 1 > low) {
				stack[++top] = low;
				stack[++top] = p - 1;
			}

			// If there are elements on right side of pivot,
			// then push right side to stack
			if (p + 1 < high) {
				stack[++top] = p + 1;
				stack[++top] = high;
			}
		}
	}

	/**
	 * Picks last element as pivot Value. <br/>
	 * + randomness added.
	 */
	private static int partition(int[] values, int low, int high) {
		randomness(values, low, high);
		int pivot = values[high];
		int idx = low; // will give pivot index
		for (int j = low; j <= high - 1; j++) {
			/**
			 * If current element is smaller than or equal to pivot
			 */
			if (values[j] <= pivot) {
				exchange(values, idx, j);
				idx++;
			}
		}

		/**
		 * put pivot value at pivot index
		 */
		exchange(values, idx, high);
		return idx;
	}

	/**
	 * @param values
	 * @param low
	 * @param high
	 */
	private static void randomness(int[] values, int low, int high) {
		Random rand = new Random();
		int randIndx = low + rand.nextInt(high - low + 1);
		exchange(values, randIndx, high);
	}

	/**
	 * @param values
	 * @param i
	 * @param j
	 */
	private static void exchange(int[] values, int i, int j) {
		int temp = values[i];
		values[i] = values[j];
		values[j] = temp;
	}

}
