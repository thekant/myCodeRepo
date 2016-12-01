/**
 * 
 */
package com.kant.algorithms.dynamicprogramming;

/**
 * Counts total number of ways in which a change can be collected equal to the
 * sum.
 * 
 * @author shashi
 * 
 */
public class CoinChangeProb {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int pocket[] = { 1, 4, 3, 2, 1 };
		int amount = 4;
		System.out.println(new CoinChangeProb().countChange(pocket, amount));

	}

	/**
	 * 
	 * @param pocket
	 * @param amount
	 * @return
	 */
	public int countChange(int[] pocket, int amount) {
		return count(pocket, pocket.length, amount);
	}

	/**
	 * Non DP approach.
	 * 
	 * @param pocket
	 * @param numberOFCoins
	 * @param amount
	 * @return
	 */
	private int count(int pocket[], int numberOFCoins, int amount) {
		if (amount == 0) {
			return 1;
		} else if (numberOFCoins == 0) {
			return 0;
		}

		if (pocket[numberOFCoins - 1] > amount)
			return count(pocket, numberOFCoins - 1, amount);

		return count(pocket, numberOFCoins - 1, amount)
				+ count(pocket, numberOFCoins - 1, amount
						- pocket[numberOFCoins - 1]);
	}

}
