/**
 * 
 */
package com.kant.sortingnsearching;

/**
 * @author shaskant
 *
 */
public class CalSquareRoot {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(solve(5));
	}

	public static String solve(int number) {
		Boolean isNegative = false;
		if (number < 0) {
			number = -number;
			isNegative = true;
		}

		if (number == 1) {
			return number + (isNegative ? "i" : "");
		}
		double start = 0;
		double end = number;
		double mid = (start + end) / 2;
		double prevMid = 0;
		double diff = Math.abs(mid - prevMid);
		/**
		 * precision
		 */
		double precision = 0.0005;

		while ((mid * mid != number) && (diff > precision)) {
			if (mid * mid > number) {
				end = mid;
			} else {
				start = mid;
			}
			prevMid = mid;
			mid = (start + end) / 2;
			diff = Math.abs(mid - prevMid);
		}

		return mid + (isNegative ? "i" : "");

	}

}
