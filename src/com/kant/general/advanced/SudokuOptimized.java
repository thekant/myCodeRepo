/**
 * 
 */
package com.kant.general.advanced;

import java.util.HashSet;

/**
 * Pass any N^2 X N^2 grid to sudoku
 * 
 * @author shaskant
 *
 */
public class SudokuOptimized {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int grid[][] = { { 3, 0, 6, 5, 0, 8, 4, 0, 0 },
				{ 5, 2, 0, 0, 0, 0, 0, 0, 0 }, { 0, 8, 7, 0, 0, 0, 0, 3, 1 },
				{ 0, 0, 3, 0, 1, 0, 0, 8, 0 }, { 9, 0, 0, 8, 6, 3, 0, 0, 5 },
				{ 0, 5, 0, 0, 9, 0, 6, 0, 0 }, { 1, 3, 0, 0, 0, 0, 2, 5, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 7, 4 }, { 0, 0, 5, 2, 0, 6, 3, 0, 0 } };
		SudokuOptimized solver = new SudokuOptimized();
		solver.solveSudoku(grid);

		printBoard(grid);
	}

	private int boxSize = 0;

	public void solveSudoku(int[][] board) {
		boxSize = (int) Math.sqrt(boxSize);
		solve(board);
	}

	/**
	 * no better than previous version
	 * 
	 * @param board
	 * @return
	 */
	private boolean solve(int[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] != 0)
					continue;

				for (int k = 1; k <= board.length; k++) {
					board[i][j] = k;
					if (isValid(board, i, j) && solve(board))
						return true;
					board[i][j] = 0;
				}
				return false;
			}
		}
		return true; // does not matter
	}

	// no performance gain
	private boolean isValid(int[][] board, int i, int j) {
		HashSet<Integer> set = new HashSet<Integer>();

		// search across columns
		for (int k = 0; k < board.length; k++) {
			if (set.contains(board[i][k]))
				return false;

			if (board[i][k] != 0) {
				set.add(board[i][k]);
			}
		}

		set.clear();

		for (int k = 0; k < board.length; k++) {
			if (set.contains(board[k][j]))
				return false;

			if (board[k][j] != 0) {
				set.add(board[k][j]);
			}
		}

		set.clear();

		for (int m = 0; m < boxSize; m++) {
			for (int n = 0; n < boxSize; n++) {
				int x = i / boxSize * boxSize + m;
				int y = j / boxSize * boxSize + n;
				if (set.contains(board[x][y]))
					return false;

				if (board[x][y] != 0) {
					set.add(board[x][y]);
				}
			}
		}
		return true;
	}

	public static void printBoard(int grid[][]) {
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid.length; col++)
				System.out.printf("%2d", grid[row][col]);
			System.out.printf("\n");
		}
	}

}
