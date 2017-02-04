package com.kant.datastructure.graphs;

import java.util.Arrays;

/**
 * Find out all islands in the matrix.. can also give the list of islands with
 * their elements
 * 
 * What is an island? A group of connected 1s forms an island. For example, the
 * below matrix contains 5 islands
 * 
 * {1, 1, 0, 0, 0}, <br/>
 * {0, 1, 0, 0, 1}, <br/>
 * {1, 0, 0, 1, 1}, <br/>
 * {0, 0, 0, 0, 0}, <br/>
 * {1, 0, 1, 0, 1}
 * 
 * @author Shashi
 * 
 * 
 */
public class IslandFindProblem {
	private int row;
	private int col;
	private int[][] matrix;
	private boolean[][] visited;

	/**
	 * 
	 * @param matrix
	 */
	public IslandFindProblem(int[][] matrix) {
		this.matrix = matrix;
		row = matrix.length;
		col = matrix[0].length;
		visited = new boolean[row][col];
	}

	/**
	 * 
	 * @return
	 */
	public int findNumberOfIslands() {
		int numIslands = 0;
		for (int i = 0; i < row; i++)
			for (int j = 0; j < col; j++) {
				if (matrix[i][j] == 1 && !visited[i][j]) {
					numIslands += 1;
					extractThisIsland(i, j);
				}
			}
		return numIslands;
	}

	/**
	 * row number is in range, column number is in range and value is 1 and not
	 * yet visited.
	 * 
	 * @param arow
	 * @param acol
	 * @return
	 */
	private boolean isSafe(int arow, int acol) {
		return (arow >= 0) && (arow < row) && (acol >= 0) && (acol < col)
				&& (matrix[arow][acol] == 1 && !visited[arow][acol]);
	}

	// These arrays are used to get row and column numbers
	// of 8 neighbors of a given cell
	private static final int rowNbr[] = new int[] { -1, -1, -1, 0, 0, 1, 1, 1 };
	private static final int colNbr[] = new int[] { -1, 0, 1, -1, 1, -1, 0, 1 };

	/**
	 * Basically DFS
	 * 
	 * @param i
	 * @param j
	 */
	private void extractThisIsland(int i, int j) {
		visited[i][j] = true;
		// cover adjacent neighbors
		for (int k = 0; k < 8; k++) {
			if (isSafe(i + rowNbr[k], j + colNbr[k])) {
				extractThisIsland(i + rowNbr[k], j + colNbr[k]);
			}
		}
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int[][] matrix = new int[][] { { 1, 1, 0, 0, 0 }, { 0, 1, 0, 0, 1 },
				{ 1, 0, 0, 1, 1 }, { 0, 0, 0, 0, 0 }, { 1, 0, 1, 0, 1 } };

		int num = 0;
		IslandFindProblem prob = new IslandFindProblem(matrix);
		num = prob.findNumberOfIslands();

		System.out.println(num);

	}

}
