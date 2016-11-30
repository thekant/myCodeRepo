/**
 * 
 */
package com.kant.datastructure.graphs;

import com.kant.datastructure.queues.Dequeue;
import com.kant.datastructure.queues.DequeueListImplementation;
import com.kant.datastructure.queues.OverFlowException;
import com.kant.datastructure.queues.Queue;
import com.kant.datastructure.queues.QueueListImplementation;
import com.kant.datastructure.queues.UnderFlowException;

/**
 * http://www.geeksforgeeks.org/minimum-time-required-so-that-all-oranges-become
 * -rotten/
 * 
 * Given a matrix of dimension m*n where each cell in the matrix can have values
 * 0, 1 or 2 which has the following meaning:
 * 
 * 0: Empty cell
 * 
 * 1: Cells have fresh oranges
 * 
 * 2: Cells have rotten oranges So we have to determine what is the minimum time
 * required so that all the oranges become rotten. A rotten orange at index
 * [i,j] can rot other fresh orange at indexes [i-1,j], [i+1,j], [i,j-1],
 * [i,j+1] (up, down, left and right). If it is impossible to rot every orange
 * then simply return -1.
 * 
 * @author shaskant
 *
 */
public class RotAllOrangesInMatrix_BFS {

	private int Row;
	private int Col;
	private int[][] data;

	public RotAllOrangesInMatrix_BFS(int row, int col, int[][] data) {
		super();
		Row = row;
		Col = col;
		this.data = data;
	}

	/**
	 * number of time frames needed to rot all oranges or return -1 if not
	 * possible.
	 * 
	 * @return
	 * @throws OverFlowException
	 * @throws UnderFlowException
	 */
	public int solveProblem() throws OverFlowException, UnderFlowException {
		Dequeue<Point> queue = new DequeueListImplementation<>();
		int ans = 0;// stores number of iteration
		// Store all the cells having rotten orange in first time frame
		for (int i = 0; i < Row; i++) {
			for (int j = 0; j < Col; j++) {
				if (data[i][j] == 2) {
					queue.enQueue(new Point(i, j));
				}
			}
		}

		// Separate these rotten oranges from the oranges which will rot
		// because of the oranges in first time frame using delimiter which is
		// (-1, -1)
		queue.enQueue(new Point(-1, -1));

		// Process the grid while there are rotten oranges in the Queue
		while (!queue.isEmpty()) {
			// This flag is used to determine whether even a single fresh
			// orange gets rotten due to rotten oranges in current time
			// frame so we can increase the count of the required time.
			boolean flag = false;
			int[] rows = { -1, 0, 1, 0 };
			int[] cols = { 0, -1, 0, 1 };

			// Process all the rotten oranges in current time frame.
			while (!queue.getFront().isDelimiter()) {
				Point temp = queue.getFront();
				// spread in all 4 directions
				for (int k = 0; k < 4; k++) {
					if (isValid(temp.x + rows[k], temp.y + cols[k])
							&& data[temp.x + rows[k]][temp.y + cols[k]] == 1) {
						if (!flag) {
							ans++;// increment only once per spread cycle.
							flag = true;
						}
						data[temp.x + rows[k]][temp.y + cols[k]] = 2;
						queue.enQueue(new Point(temp.x + rows[k], temp.y
								+ cols[k]));
					}
				}
				queue.deQueue();
			}
			// remove the delimiter
			queue.deQueue();
			// If oranges were rotten in current frame then separate the
			// rotten oranges using delimiter for the next frame for processing.
			if (!queue.isEmpty()) {
				queue.enQueue(new Point(-1, -1));
			}
			// If Queue was empty than no rotten oranges left to process so exit
		}
		return (verifyAll()) ? -1 : ans;
	}

	private boolean isValid(int x, int y) {
		return (x >= 0 && y >= 0 && x < Row && y < Col);
	}

	private boolean verifyAll() {
		for (int i = 0; i < Row; i++)
			for (int j = 0; j < Col; j++)
				if (data[i][j] == 1)
					return true;
		return false;
	}

	/**
	 * @param args
	 * @throws UnderFlowException
	 * @throws OverFlowException
	 */
	public static void main(String[] args) throws OverFlowException,
			UnderFlowException {
		int[][] arr = { { 2, 1, 0, 2, 1 }, { 1, 0, 1, 2, 1 }, { 1, 0, 0, 2, 1 } };
		RotAllOrangesInMatrix_BFS problem = new RotAllOrangesInMatrix_BFS(3, 5,
				arr);
		System.out.println(problem.solveProblem());
	}

	/**
	 * -1 -1 for delimiter
	 * 
	 * @author shaskant
	 *
	 */
	class Point {
		public int x;
		public int y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		public boolean isDelimiter() {
			return x == -1 && y == -1;
		}

	}

}
