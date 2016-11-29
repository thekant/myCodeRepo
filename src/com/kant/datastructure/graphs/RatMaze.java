/**
 * 
 */
package com.kant.datastructure.graphs;

import java.util.Arrays;

/**
 * Solve maze to find the path. (0,0) to (M,N)<br/>
 * If destination is reached print the solution matrix Else <br/>
 * a) Mark current cell in solution matrix as 1. <br/>
 * b) Move forward in horizontal direction and recursively check if this move
 * leads to a solution. <br/>
 * c) If the move chosen in the above step doesn't lead to a solution then move
 * down and check if this move leads to a solution. <br/>
 * d) If none of the above solutions work then unmark this cell as 0 (BACKTRACK)
 * and return false.
 * 
 * @author shaskant
 *
 */
public class RatMaze {

	private int M, N;
	private int[][] theMaze;

	/**
	 * 1 means free , 0 means blocked
	 */
	public RatMaze(int[][] maze) {
		theMaze = maze;
		M = maze.length;
		N = maze[0].length;
	}

	private boolean isSafe(int i, int j) {
		return (i >= 0 && i <= M - 1 && j >= 0 && j <= N - 1 && theMaze[i][j] == 1);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int maze[][] = { { 1, 0, 0, 0 }, { 1, 1, 0, 1 }, { 0, 1, 0, 0 },
				{ 1, 1, 1, 1 } };
		RatMaze rat = new RatMaze(maze);
		rat.solveMaze();
	}

	/**
	 * 
	 * @return
	 */
	public boolean solveMaze() {
		int[][] sol = new int[M][];
		for (int count = 0; count < M; count++) {
			sol[count] = new int[N];
		}

		if (solveMazeUtil(0, 0, sol) == false) {
			System.out.print("Solution doesn't exist");
			return false;
		}
		printSolution(sol);
		return true;
	}

	private boolean solveMazeUtil(int i, int j, int[][] sol) {
		if (i == M - 1 && j == N - 1) {
			sol[i][j] = 1;
			return true;
		}
		if (isSafe(i, j)) {
			sol[i][j] = 1;
			if (solveMazeUtil(i + 1, j, sol))
				return true;
			if (solveMazeUtil(i, j + 1, sol))
				return true;
			//backtrack step
			sol[i][j] = 0;
		}
		return false;
	}

	private void printSolution(int[][] sol) {
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++)
				System.out.print(" " + sol[i][j] + " ");
			System.out.println();
		}
	}
}
