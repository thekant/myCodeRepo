/**
 * 
 */
package com.kant.sortingnsearching;

/**
 * Find first positive value for a given function
 * 
 * @author shashi
 * 
 */
public class FindFirstPositiveOutputOfFunction {
	private Function func;

	public void setFunc(Function func) {
		this.func = func;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FindFirstPositiveOutputOfFunction prob = new FindFirstPositiveOutputOfFunction();

		prob.setFunc(new Function() {
			@Override
			public int execute(int x) {
				return (x * x - 10 * x - 20);
			}
		});
		System.out.println(prob.findFirstPositive());
	}

	/**
	 * Returns the value x where above function f() becomes positive first time.
	 * 
	 * @return
	 */
	public int findFirstPositive() {
		// When first value itself is positive
		if (func.execute(0) > 0)
			return 0;

		// Find 'high' for binary search by repeated doubling
		int i = 1;
		while (func.execute(i) <= 0)
			i = i * 2;

		// Call binary search
		return binarySearch(i / 2, i);
	}

	private int binarySearch(int low, int high) {
		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (func.execute(mid) > 0
					&& (mid == low || func.execute(mid - 1) <= 0))
				return mid;
			else if (func.execute(mid) < 0) {
				low = mid;
			} else {
				high = mid;
			}
		}
		return -1;
	}
}

interface Function {
	public int execute(int x);
}
