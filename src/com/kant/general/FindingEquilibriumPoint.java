/**
 * 
 */
package com.kant.general;

/**
 * Find the the index of the array such that sum of elements on it's left == sum
 * of elements on its's right.
 * 
 * @author shashi
 * 
 */
public class FindingEquilibriumPoint {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int a[] = { 9, -7, 4, 5, 2, 3, 0, 8 /* 1, 1, 1, 2, 1, 1, 1 */};
		int n = a.length, i = 0, j = n - 1;
		int sum1 = a[i++], sum2 = a[j--];
		// at each iteration lesser side will get to choose.
		while (i < j) {
			if (sum1 < sum2)
				sum1 = sum1 + a[i++];
			else
				sum2 = sum2 + a[j--];
			
			if (sum1 == sum2 && i == j)
				System.out.printf(
						"\n equilibrium point is i = %d and value is = %d \n",
						i, a[i]);
		} // End of while
		if (i > j)
			System.out.println("no such equilibrium point");
	}

}

/**
 * Just for fun
 * 
 * @author shashi
 * 
 */
class SevenSegmentDisplay {
	/**
	 * 
	 * @param args
	 */
	public static void print(String args[]) {
		int num = Integer.parseInt(args[0]);
		String i = "", j = "", k = "";
		if (num == 1) {
			i = "";
			j = "|";
			k = "|";
		} else if (num == 2) {
			i = " __";
			j = " __|";
			k = "|__";
		} else if (num == 3) {
			i = " __";
			j = " __|";
			k = " __|";
		} else if (num == 4) {
			i = "";
			j = "|__|";
			k = "   |";
		} else if (num == 5) {
			i = " __";
			j = "|__";
			k = " __|";
		} else if (num == 6) {
			i = " __";
			j = "|__";
			k = "|__|";
		} else if (num == 7) {
			i = " __";
			j = "   |";
			k = "   |";
		} else if (num == 8) {
			i = " __";
			j = "|__|";
			k = "|__|";
		} else if (num == 9) {
			i = " __";
			j = "|__|";
			k = " __|";
		} else if (num == 0) {
			i = " __";
			j = "|  |";
			k = "|__|";
		}
		System.out.println("\t" + i);
		System.out.println("\t" + j);
		System.out.println("\t" + k);
		System.out.println("\t");
	}
}