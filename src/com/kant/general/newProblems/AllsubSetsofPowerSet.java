/**
 * 
 */
package com.kant.general.newProblems;

/**
 * @author shaskant
 *
 */
public class AllsubSetsofPowerSet {
	static int[] B = { 1, 2, 3 }; // power set

	/**
	 * http://algorithms.tutorialhorizon.com/print-all-the-subsets-of-a-given-
	 * set-power-set/
	 * 
	 * @param A
	 * @param x
	 */
	public void combinations(int[] A, int x) {
		if (x == A.length - 1) {
			A[x] = 0; // last digit, don't select it
			printArray(A); // print the set
			A[x] = 1; // // last digit, select it
			printArray(A);
			return;
		}
		A[x] = 0; // either you will not select this digit
		combinations(A, x + 1);
		A[x] = 1; // either you will select this digit
		combinations(A, x + 1);
	}

	public void printArray(int[] A) {
		boolean isEmpty = true;
		System.out.print("{ ");
		for (int i = 0; i < B.length; i++) {
			if (A[i] == 1) {
				System.out.print(B[i] + " ");
				isEmpty = false;
			}
		}
		if (isEmpty == false) {
			System.out.print("}");
			System.out.print("  ");
		}

		if (isEmpty) {
			System.out.print("Empty");
			System.out.print(" } ");
		}
	}

	public static void main(String[] args) {
		AllsubSetsofPowerSet a = new AllsubSetsofPowerSet();
		int[] A = new int[B.length];
		a.combinations(A, 0);

	}
}
