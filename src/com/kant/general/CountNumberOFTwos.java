package com.kant.general;

/**
 * TODO
 * 
 * http://www.geeksforgeeks.org/count-numbers-from-1-to-n-that-have-4-as-a-a-
 * digit/
 * 
 * @author shashi
 * 
 */
public class CountNumberOFTwos {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(count2sR(27));
	}

	/**
	 * <br/>
	 * Count of numbers from 0 to 9 = 1 <br/>
	 * Count of numbers from 0 to 99 = 1*9 + 10 = 19 <br/>
	 * Count of numbers from 0 to 999 = count(99)*9 + 100 = 271
	 * 
	 * <br/>
	 * In general, we can write <br/>
	 * count(10^d) = 9 * count(10^(d - 1)) + 10^(d - 1)
	 * 
	 * @param N
	 * @return
	 */

	public static int solve(int N) {
		int result = 0;

		return -1;
	}

	/**
	 * 
	 * @param n
	 * @return
	 */
	public static int count2sR(int n) {
		// Base case
		if (n == 0)
			return 0;
		// 513 will be divided into 5 * 100 + 13. [Power = 100; First = 5;
		// Remainder = 13]
		int power = 1;
		while (10 * power < n)
			power *= 10;
		int first = n / power;// 5
		int remainder = n % power;// 13
		// Counts 2s from first digit
		int nTwosFirst = 0;
		if (first > 2)
			nTwosFirst += power;
		else if (first == 2)
			nTwosFirst += remainder + 1;
		// Count 2s from all other digits
		int nTwosOther = first * count2sR(power - 1) + count2sR(remainder);
		return nTwosFirst + nTwosOther;
	}

}
