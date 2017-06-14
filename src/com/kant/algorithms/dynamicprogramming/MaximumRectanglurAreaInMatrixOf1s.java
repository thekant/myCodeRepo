/**
 * 
 */
package com.kant.algorithms.dynamicprogramming;

/**
 * https://www.youtube.com/watch?v=g8bSdXCG-lA&t=265s
 * 
 * https://github.com/mission-peace/interview/blob/master/src/com/interview/
 * dynamic/MaximumRectangularSubmatrixOf1s.java
 * 
 * @author shaskant
 *
 */
public class MaximumRectanglurAreaInMatrixOf1s {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		MaximumRectanglurAreaInMatrixOf1s problem = new MaximumRectanglurAreaInMatrixOf1s();
		int input[][] = { { 1, 1, 1, 0 }, { 1, 1, 1, 1 }, { 0, 1, 1, 0 },
				{ 0, 1, 1, 1 }, { 1, 0, 0, 1 }, { 1, 1, 1, 1 } };

		int maxRectangle = problem.solveProblem(
				new MaximumAreaUnderHistogram(), input);
		assert maxRectangle == 8;
		System.out.println(maxRectangle);
	}

	/**
	 * O(rows * cols)
	 * 
	 * @param histoSolver
	 * @param input
	 * @return
	 */
	public int solveProblem(MaximumAreaUnderHistogram histoSolver, int[][] input) {
		int maxArea = 0;
		int area = 0;
		int rows = input.length;
		int cols = input[0].length;
		int[] buffer = new int[cols];
		for (int xrow = 0; xrow < rows; xrow++) {
			for (int xcol = 0; xcol < cols; xcol++) {
				if (input[xrow][xcol] == 0) {
					buffer[xcol] = 0;
				} else {
					buffer[xcol] += input[xrow][xcol];
				}
			}
			area = histoSolver.maxHistogram(buffer);
			if (area > maxArea)
				maxArea = area;
		}
		return maxArea;
	}
}
