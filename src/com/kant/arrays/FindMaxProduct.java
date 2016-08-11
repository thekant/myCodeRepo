/**
 * 
 */
package com.kant.arrays;

/**
 * @author shaskant
 *
 */
public class FindMaxProduct {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int arr[] = { 1, 4, 3, 6, 7, 0, -9, -7 };
		printMaxProdcut(arr);
	}

	public static void printMaxProdcut(int[] data) {
		if (data.length < 2) {
			System.out.println("min size should be 2");
			return;
		}
		if (data.length == 2) {
			System.out.println(data[0] + " x " + data[1]);
			return;
		}

		int posA = Integer.MIN_VALUE, posB = Integer.MIN_VALUE, negA = Integer.MIN_VALUE, negB = Integer.MIN_VALUE;

		for (int i = 0; i < data.length; i++) {
			if (data[i] > posA) {
				posB = posA;
				posA = data[i];
			} else if (data[i] > posB)
				posB = data[i];
			if (data[i] < 0 && (Math.abs(negA) < Math.abs(data[i]))) {
				negB = negA;
				negA = data[i];
			} else if (data[i] < 0 && (Math.abs(negB) < Math.abs(data[i])))
				negB = data[i];
		}
		if (negB * negA > posA * posB)
			System.out.println(negA + " x " + negB);
		else
			System.out.println(posA + " x " + posB);
	}
}
