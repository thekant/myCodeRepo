/**
 * 
 */
package com.kant.general.advanced;

/**
 * subgrid of size 3×3 contains exactly one instance of the digits from 1 to 9
 * http://www.geeksforgeeks.org/backtracking-set-7-suduku/
 * 
 * @author shashi
 * 
 */
public class SudokuProblem {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int grid[][] = { { 3, 0, 6, 5, 0, 8, 4, 0, 0 },
				{ 5, 2, 0, 0, 0, 0, 0, 0, 0 }, { 0, 8, 7, 0, 0, 0, 0, 3, 1 },
				{ 0, 0, 3, 0, 1, 0, 0, 8, 0 }, { 9, 0, 0, 8, 6, 3, 0, 0, 5 },
				{ 0, 5, 0, 0, 9, 0, 6, 0, 0 }, { 1, 3, 0, 0, 0, 0, 2, 5, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 7, 4 }, { 0, 0, 5, 2, 0, 6, 3, 0, 0 } };

		SudokuProblem problem = new SudokuProblem();
		problem.printGrid(grid);
		
		//if (problem.solveSudoku(grid))
			//problem.printGrid(grid);
	}

	/**
	 * similar backtracking solution for 8-queens problem
	 * 
	 * @param grid
	 * @return
	 */
	public boolean solveSudoku(int[][] grid) {
		Point point = new Point(0, 0);
		if (!findUnassignedLocation(grid, point)) {
			return true;
		}

		for (int num = 1; num <= 9; num++) {
			if (isSafe(grid, point.row, point.col, num)) {

				grid[point.row][point.col] = num;
				if (solveSudoku(grid)) {
					return true;
				}
				// backtracking step
				grid[point.row][point.col] = 0;
			}
		}

		return false;
	}

	/**
	 * Searches the grid to find an entry that is still unassigned. If found,
	 * the reference parameters row, col will be set with the location that is
	 * unassigned, and true is returned. If no unassigned entries remain, false
	 * is returned.
	 * 
	 **/
	private boolean findUnassignedLocation(int grid[][], Point point) {
		for (int row = 0; row < grid.length; row++)
			for (int col = 0; col < grid.length; col++)
				if (grid[row][col] == 0) {
					point.row = row;
					point.col = col;
					return true;
				}

		return false;
	}

	/**
	 * Returns a boolean which indicates whether it will be legal to assign num
	 * to the given row,col location. <br/>
	 * 1. safe in terms of row.<br/>
	 * 2. safe in terms of column<br/>
	 * 3. safe in terms of the box<br/>
	 **/
	boolean isSafe(int grid[][], int row, int col, int num) {
		return !usedInRow(grid, row, num) && !usedInCol(grid, col, num)
				&& !usedInBox(grid, row - row % 3, col - col % 3, num);
	}

	/**
	 * Returns a boolean which indicates whether any assigned entry in the
	 * specified row matches the given number.
	 * 
	 **/
	boolean usedInRow(int grid[][], int row, int num) {
		for (int col = 0; col < grid.length; col++)
			if (grid[row][col] == num)
				return true;
		return false;
	}

	/**
	 * Returns a boolean which indicates whether any assigned entry in the
	 * specified column matches the given number.
	 **/
	boolean usedInCol(int grid[][], int col, int num) {
		for (int row = 0; row < grid.length; row++)
			if (grid[row][col] == num)
				return true;
		return false;
	}

	/**
	 * 
	 * @param grid
	 * @param cellRow
	 * @param cellCol
	 * @param num
	 * @return
	 */
	private boolean usedInBox(int[][] grid, int cellRow, int cellCol, int num) {
		for (int row = 0; row < 3; row++)
			for (int col = 0; col < 3; col++) {
				if (grid[row + cellRow][col + cellCol] == num)
					return true;
			}
		return false;
	}

	/**
	 * A utility function to print grid
	 * 
	 * @param grid
	 */
	public void printGrid(int grid[][]) {
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid.length; col++)
				System.out.printf("%2d", grid[row][col]);
			System.out.printf("\n");
		}
	}
}

class Point {
	int row;
	int col;

	/**
	 * @param row
	 * @param col
	 */
	public Point(int row, int col) {
		super();
		this.row = row;
		this.col = col;
	}
}
