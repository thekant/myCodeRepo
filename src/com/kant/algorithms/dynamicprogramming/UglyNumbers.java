package com.kant.algorithms.dynamicprogramming;

import java.util.Arrays;

/**
 * 
 * 
 * @author shaskant
 *
 */
public class UglyNumbers {

	private int maxDivisible(int num, int div) {

		while (num % div == 0) {
			num = num / div;
		}
		return num;
	}

	public boolean isUgly(int num) {
		return maxDivisible(maxDivisible(maxDivisible(num, 5), 3), 2) == 1;
	}

	/**
	 * 
	 * @param n
	 * @return
	 */
	public int findNthUglyNumber(int n) {
		int count = 1;
		int i = 2;
		while (count < n) {
			if (isUgly(i++))
				count++;
		}
		return --i;
	}

	/**
	 * 
	 * @param n
	 * @return
	 */
	public int findNthUglyNumberDynamic(int n) {
		int[] ugly = new int[n];
		ugly[0] = 1;
		int i2 = 0;
		int i3 = 0;
		int i5 = 0;
		for (int i = 1; i < n; i++) {
			ugly[i] = getMinOutof(ugly[i2] * 2, ugly[i3] * 3, ugly[i5] * 5);
			// do not use else conditions here as all three or two can be equal
			// and in that case both should be updated
			if (ugly[i] == ugly[i2] * 2) {
				i2 += 1;
			}
			if (ugly[i] == ugly[i3] * 3) {
				i3 += 1;
			}
			if (ugly[i] == ugly[i5] * 5) {
				i5 += 1;
			}
		}

		return ugly[n - 1];

	}

	public int getMinOutof(int a, int b, int c) {
		return Math.min(Math.min(a, b), c);
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		UglyNumbers ugly = new UglyNumbers();
		System.out.println(ugly.findNthUglyNumberDynamic(150));
	}
}
