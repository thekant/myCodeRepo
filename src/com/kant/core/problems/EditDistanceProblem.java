/**
 * 
 */
package com.kant.core.problems;

/**
 * http://algorithms.tutorialhorizon.com/dynamic-programming-edit-distance-
 * problem/
 * 
 * Let’s say given strings are s1 and s2 with lengths m and n respectively.
 * 
 * case 1: last char­ac­ters are same , ignore the last character. Recur­sively
 * solve for m-1, n-1
 * 
 * case 2: last char­ac­ters are not same then try all the pos­si­ble
 * oper­a­tions recursively.
 * 
 * a. Insert a char­ac­ter into s1 (same as last char­ac­ter in string s2 so
 * that last char­ac­ter in both the strings are same): now s1 length will be
 * m+1, s2 length : n, ignore the last char­ac­ter and Recur­sively solve for m,
 * n-1.
 * 
 * b. Remove the last char­ac­ter from string s1. now s1 length will be m-1, s2
 * length : n, Recur­sively solve for m-1, n.
 * 
 * c. Replace last char­ac­ter into s1 (same as last char­ac­ter in string s2 so
 * that last char­ac­ter in both the strings are same): s1 length will be m, s2
 * length : n, ignore the last char­ac­ter and Recur­sively solve for m-1, n-1.
 * 
 * Choose the min­i­mum of ( a, b, c).
 * 
 * @author shaskant
 *
 */
public class EditDistanceProblem {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

	public int editDistanceRecursion(String s1, String s2, int m, int n) {
		if (m == 0)
			return n;
		if (n == 0)
			return m;

		if (s1.charAt(m - 1) == s2.charAt(n - 1))
			return editDistanceRecursion(s1, s2, m - 1, n - 1);
		return 1 + minOf(editDistanceRecursion(s1, s2, m, n - 1), // insert
				editDistanceRecursion(s1, s2, m - 1, n), // delete
				editDistanceRecursion(s1, s2, m - 1, n - 1)); // replace
	}

	private int minOf(int a, int b, int c) {
		if (a > b && a > c)
			return a;
		if (b > a && b > c)
			return b;
		return c;
	}
}
