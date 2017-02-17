/**
 * 
 */
package com.kant.algorithms.dynamicprogramming;

/**
 * http://www.geeksforgeeks.org/maximum-decimal-value-path-in-a-binary-matrix/
 * 
 * @author shashi
 * 
 */
public class MaximumValuePathInMatrix {

	private int[][] dataMatrix;

	public MaximumValuePathInMatrix(int[][] data) {
		this.dataMatrix = data;
	}

	/**
	 * Use dijakstra's with modification for max value at each step. source
	 * (0,0) and destination (M,N)
	 * 
	 * Function starts from (M,N) and move towards (0,0)
	 * 
	 * @return
	 */
	public int findMaxPath() {
		return findMaxPathUtil(dataMatrix.length - 1, dataMatrix[0].length - 1);
	}

	/**
	 * if i < 0 then f(i, j) = 0 <br/>
	 * if i == 0 then f(i, j) = value(i, j) <br/>
	 * if i > 0 then f(i, j) = max(f(i-1, j), f(i, j-1)) + value(i, j)
	 * 
	 * @return
	 */
	private int findMaxPathUtil(int i, int j) {
		if (i < 0 || j < 0)
			return 0;
		if (i == 0 && j == 0)
			return dataMatrix[i][j];

		return dataMatrix[i][j]
				+ Math.max(findMaxPathUtil(i - 1, j), findMaxPathUtil(i, j - 1));
	}

	private boolean isSafe(int x, int y) {
		if (x >= 0 && x < dataMatrix.length) {
			if (y >= 0 && y < dataMatrix[0].length)
				return true;
		}
		return false;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[][] inputArray = { { 0, 10, 1, 4, 1 }, { 44, 9, 1, 5, 9 },
				{ 12, 34, 98, 1, 0 }, { 12, 9, 6, 2, 19 } };
		MaximumValuePathInMatrix prob = new MaximumValuePathInMatrix(inputArray);
		System.out.println(prob.findMaxPath());
	}

}
