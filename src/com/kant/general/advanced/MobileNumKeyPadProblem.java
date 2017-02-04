/**
 * 
 */
package com.kant.general.advanced;

/**
 * http://www.geeksforgeeks.org/mobile-numeric-keypad-problem/
 *
 * Given the mobile numeric keypad. You can only press buttons that are up,
 * left, right or down to the current button. You are not allowed to press
 * bottom row corner buttons (i.e. * and # ).
 *
 * Generate number of possible combinations for a given length
 * 
 * @author shashi
 * 
 */
public class MobileNumKeyPadProblem {

	// left, up, right, down move from current location
	private final int rowNbr[] = { 0, 0, -1, 0, 1 };
	private final int colNbr[] = { 0, -1, 0, 1, 0 };

	private char keypad[][] = { { '1', '2', '3' }, { '4', '5', '6' },
			{ '7', '8', '9' }, { '*', '0', '#' } };

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MobileNumKeyPadProblem problem = new MobileNumKeyPadProblem();
		System.out.println(problem.getCountDpApproach(5));
	}

	/**
	 * Bottom up approach.<br/>
	 * start counting from 0 to n and store values for (row,column) pair which
	 * is basically a number on keypad.
	 * 
	 * 
	 * 
	 * @param n
	 * @return
	 */
	public int getCountDpApproach(int n) {
		// row,column --> digit
		int[][] allStepCounts = new int[10][n + 1];

		// initialize
		for (int i = 0; i <= 9; i++) {
			allStepCounts[i][0] = 0;
			allStepCounts[i][1] = 1;
		}

		for (int k = 2; k <= n; k++)
			for (int i = 0; i <= 3; i++)
				for (int j = 0; j <= 2; j++) {
					if (keypad[i][j] != '#' && keypad[i][j] != '*') {
						int num = keypad[i][j] - '0';
						allStepCounts[num][k] = 0;
						int ro, co;
						for (int move = 0; move < 5; move++) {
							ro = i + rowNbr[move];
							co = j + colNbr[move];

							if (ro >= 0 && ro <= 3 && co >= 0 && co <= 2
									&& keypad[ro][co] != '#'
									&& keypad[ro][co] != '*') {
								int numkey = keypad[ro][co] - '0';
								allStepCounts[num][k] += allStepCounts[numkey][k - 1];
							}
						}
					}

				}

		int totalCount = 0;
		for (int i = 0; i <= 9; i++)
			totalCount += allStepCounts[i][n];
		return totalCount;
	}

	/**
	 * If N = 1 
	 *        Count(i, j, N) = 10 
	 * Else 
	 *        Count(i, j, N) = Sum of all Count(r, c, N-1) where (r, c) is 
	 *        new position after valid move of length 1 from current position (i, j)
	 * 
	 * @param n
	 * @return
	 */
	public int getCount(int n) {
		int totalCount = 0;
		if (n == 0)
			return 0;
		if (n == 1)
			return 10;
		for (int row = 0; row < keypad.length; row++)
			for (int col = 0; col < keypad[0].length; col++) {
				if (keypad[row][col] != '#' && keypad[row][col] != '*')
					totalCount += getCountUtil(row, col, n);
			}

		return totalCount;
	}

	/**
	 * main recursive method.
	 * 
	 */
	private int getCountUtil(int row, int col, int n) {
		if (n == 0 || n == 1)
			return n;

		int ro, co, totalCount = 0;
		for (int move = 0; move < 5; move++) {
			ro = row + rowNbr[move];
			co = col + colNbr[move];

			if (ro >= 0 && ro <= 3 && co >= 0 && co <= 2
					&& keypad[ro][co] != '#' && keypad[ro][co] != '*') {
				totalCount += getCountUtil(ro, co, n - 1);
			}
		}
		return totalCount;
	}

}
