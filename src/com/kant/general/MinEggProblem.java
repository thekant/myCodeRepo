/**
 * 
 */
package com.kant.general;

/**
 * http://www.geeksforgeeks.org/dynamic-programming-set-11-egg-dropping-puzzle/ <br/>
 * The problem is not actually to find the critical floor, but merely to decide
 * floors from which eggs should be dropped so that total number of trials are
 * minimized.
 * <p>
 * Suppose that we wish to know which stories in a 36-story building are safe to
 * drop eggs from, and which will cause the eggs to break on landing. We make a
 * few assumptions:
 * 
 * …..An egg that survives a fall can be used again. …..A broken egg must be
 * discarded. …..The effect of a fall is the same for all eggs. …..If an egg
 * breaks when dropped, then it would break if dropped from a higher floor.
 * …..If an egg survives a fall then it would survive a shorter fall. …..It is
 * not ruled out that the first-floor windows break eggs, nor is it ruled out
 * that the 36th-floor do not cause an egg to break.
 * 
 * If only one egg is available and we wish to be sure of obtaining the right
 * result, the experiment can be carried out in only one way. Drop the egg from
 * the first-floor window; if it survives, drop it from the second floor window.
 * Continue upward until it breaks. In the worst case, this method may require
 * 36 droppings. Suppose 2 eggs are available. What is the least number of
 * egg-droppings that is guaranteed to work in all cases?
 * </p>
 * 
 * @author shashi
 * 
 */
public class MinEggProblem {

	int numberOfEggs;
	int numberOfFloors;

	/**
	 * @param numberOfEggs
	 * @param numberOfFloors
	 */
	public MinEggProblem(int numberOfEggs, int numberOfFloors) {
		super();
		this.numberOfEggs = numberOfEggs;
		this.numberOfFloors = numberOfFloors;
	}

	/**
	 * 
	 * @return
	 */
	public int solveForMinimumEffort() {
		return eggDropIterative(numberOfEggs, numberOfFloors);
	}

	/**
	 * <p>
	 * <b>Optimal Substructure:</b> When we drop an egg from a floor x, there
	 * can be two cases (1) The egg breaks (2) The egg doesn’t break.
	 * </p>
	 * <p>
	 * 1) If the egg breaks after dropping from xth floor, then we only need to
	 * check for floors lower than x with remaining eggs; so the problem reduces
	 * to x-1 floors and n-1 eggs <br/>
	 * 2) If the egg doesn’t break after dropping from the xth floor, then we
	 * only need to check for floors higher than x; so the problem reduces to
	 * k-x floors and n eggs.
	 * </p>
	 * 
	 * @param n
	 *            number of eggs.
	 * @param k
	 *            number of floors
	 * @return
	 */
	private int eggDrop(int n, int k) {
		// If there are no floors, then no trials needed. OR if there is
		// one floor, one trial needed.
		if (k == 1 || k == 0)
			return k;

		// We need k trials in worst case for '1 egg and k floors'
		if (n == 1)
			return k;

		int min = Integer.MAX_VALUE, x, res;

		// Consider all droppings from 1st floor to kth floor and
		// return the minimum of these values plus 1.
		for (x = 1; x <= k; x++) {
			res = 1 + Math.max(eggDrop(n - 1, x - 1), eggDrop(n, k - x));
			if (res < min)
				min = res;
		}

		return min;
	}

	/**
	 * Dynamic programming solution to the problem. <br/>
	 * Time Complexity: O(nk^2) Auxiliary Space: O(nk)
	 * 
	 * @param n
	 *            number of eggs.
	 * @param k
	 *            number of floors
	 * @return
	 */
	private int eggDropIterative(int n, int k) {
		int[][] eggFloor = new int[n + 1][k + 1];
		for (int eggs = 1; eggs <= n; eggs++) {
			eggFloor[eggs][0] = 0;// no floor no try
			eggFloor[eggs][1] = 1;// 1 floor 1 try
		}

		// We always need max of j trials for '1 egg and j floors'.
		for (int j = 1; j <= k; j++)
			eggFloor[1][j] = j;

		int x, res;
		for (int i = 2; i <= n; i++) {
			for (int j = 2; j <= k; j++) {
				eggFloor[i][j] = Integer.MAX_VALUE;
				for (x = 1; x <= j; x++) {
					res = 1 + Math.max(eggFloor[i - 1][x - 1], eggFloor[i][j
							- x]);
					if (res < eggFloor[i][j])
						eggFloor[i][j] = res;
				}
			}
		}

		// eggFloor[n][k] holds the result
		return eggFloor[n][k];
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Solution is: "
				+ new MinEggProblem(2, 10).solveForMinimumEffort());
	}
}
