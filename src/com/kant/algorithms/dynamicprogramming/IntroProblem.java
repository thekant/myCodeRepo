/**
 * 
 */
package com.kant.algorithms.dynamicprogramming;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;

/**
 * @author shaskant
 *
 */
public class IntroProblem {

	private int[] mStore;

	/**
	 * recursive version
	 * 
	 * @param n
	 * @return
	 */
	public int fibR(int n) {
		if (n == 1 || n == 0)
			return n;
		else
			return fibR(n - 1) + fibR(n - 2);
	}

	public int solveFibByMemVersion(int n) {
		setupMStore(n);
		return fibM(n);
	}

	/**
	 * 
	 * @param n
	 */
	private void setupMStore(int n) {
		mStore = new int[n];
		Arrays.fill(mStore, -1);
	}

	/**
	 * Memorized version {top down}
	 * 
	 * @param n
	 * @return
	 */
	private int fibM(int n) {
		if (n == 1 || n == 0)
			return n;
		int rF = mStore[n - 1] != -1 ? mStore[n - 1] : fibM(n - 1);
		mStore[n - 1] = rF;
		int lF = mStore[n - 2] != -1 ? mStore[n - 2] : fibM(n - 2);
		mStore[n - 2] = lF;
		return rF + lF;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IntroProblem prob = new IntroProblem();
		Timestamp timestamp1 = new Timestamp(new Date().getTime());
		System.out.println(prob.fibR(50));
		Timestamp timestamp2 = new Timestamp(new Date().getTime());
		System.out.println(timestamp2.getTime() - timestamp1.getTime());

	}

}
