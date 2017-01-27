/**
 * 
 */
package com.kant.general.advanced;

/**
 * http://www.geeksforgeeks.org/maximum-and-minimum-in-an-array/
 * 
 * @author shashi
 * 
 */
public class MinMaxOFArray {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int arr[] = { 1000, 11, 445, 1, 330, 3000 };
		Pair minMax = getMinMax(arr, 0, arr.length - 1);
		System.out.println(minMax.min + " " + minMax.max);
	}

	/**
	 * Divide and conquer technique.
	 * 
	 * @param data
	 * @param start
	 * @param end
	 * @return
	 */
	public static Pair getMinMax(int[] data, int start, int end) {
		Pair result = new Pair();
		// if 1 element
		if (start == end) {
			result.min = result.max = data[start];
			return result;
		}
		// if 2 elements
		else if (start + 1 == end) {
			result.min = data[start] < data[end] ? data[start] : data[end];
			result.max = data[start] > data[end] ? data[start] : data[end];
			return result;
		}

		// for array with more than 2 elements, divide into left and right.
		int mid = start + (end - start) / 2;
		Pair fromLeft = getMinMax(data, start, mid);
		Pair fromRight = getMinMax(data, mid + 1, end);

		result.min = fromLeft.min < fromRight.min ? fromLeft.min : fromRight.min;
		result.max = fromLeft.max > fromRight.max ? fromLeft.max : fromRight.max;
		return result;
	}
}

/**
 * 
 * @author shashi
 * 
 */
class Pair {
	int min;
	int max;
}
