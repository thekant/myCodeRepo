package com.kant.datastructure.graphs;

/**
 * http://www.geeksforgeeks.org/find-shortest-safe-route-in-a-path-with-
 * landmines/ <br/>
 * Given a path in the form of a rectangular matrix having few landmines
 * arbitrarily placed (marked as 0), calculate length of the shortest safe route
 * possible from any cell in the first column to any cell in the last column of
 * the matrix.
 * 
 * We have to avoid landmines and their four adjacent cells (left, right, above
 * and below) as they are also unsafe. We are allowed to move to only adjacent
 * cells which are not landmines. i.e. the route cannot contains any diagonal
 * moves.
 * 
 * @author shaskant
 *
 */
public class FindSafestPathInLandMines {
	private int M, N;
	private int[][] theMine;
	private int[] rows = { -1, 0, 1, 0 };
	private int[] cols = { 0, -1, 0, 1 };
	private int minDistance = Integer.MAX_VALUE;

	/**
	 * 1 means safe if not adjacent to mine , 0 means mine
	 */
	public FindSafestPathInLandMines(int[][] mine) {
		theMine = mine;
		M = mine.length;
		N = mine[0].length;
	}

	public static void main(String[] args) {
		int mat[][] = { { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 1, 0, 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 1, 1, 1, 0, 1, 1, 1, 1, 1, 1 },
				{ 1, 1, 1, 1, 0, 1, 1, 1, 1, 1 },
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 1, 1, 1, 1, 1, 0, 1, 1, 1, 1 },
				{ 1, 0, 1, 1, 1, 1, 1, 1, 0, 1 },
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 0, 1, 1, 1, 1, 0, 1, 1, 1, 1 },
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 1, 1, 1, 0, 1, 1, 1, 1, 1, 1 } };

		// find shortest path
		FindSafestPathInLandMines prob = new FindSafestPathInLandMines(mat);
		System.out.println(prob.shortestPath());
	}

	/**
	 * 
	 */
	private void markAllUnsafeMineAreas() {
		for (int i = 0; i < M; i++)
			for (int j = 0; j < N; j++) {
				if (theMine[i][j] == 0) {
					for (int k = 0; k < 4; k++) {
						if (isSafe(i + rows[k], j + cols[k]))
							theMine[i + rows[k]][j + cols[k]] = -1;
					}
				}
			}
	}

	/**
	 * returns shortest path from left column to right column which is safe
	 * 
	 * @return
	 */
	public int shortestPath() {
		markAllUnsafeMineAreas();
		int[][] visited = new int[M][];
		for (int count = 0; count < M; count++) {
			visited[count] = new int[N];
		}
		for (int i = 0; i < M; i++) {
			if (theMine[i][0] == 1) {
				findShortestPath(i, 0, visited, 0);
			}
			// if min distance is already found
			if (minDistance == N - 1)
				break;
		}

		return minDistance;
	}

	private void findShortestPath(int i, int j, int[][] visited, int dist) {
		if (j == N - 1) {
			minDistance = Math.min(dist, minDistance);
			return;
		}
		if (dist > minDistance)
			return;
		visited[i][j] = 1;

		// Recurse for all safe adjacent neighbors
		for (int k = 0; k < 4; k++) {
			if (isSafe(i + rows[k], j + cols[k])
					&& visited[i + rows[k]][j + cols[k]] != 1) {
				findShortestPath(i + rows[k], j + cols[k], visited, dist + 1);
			}
		}
		// clear all visited to be reused for next iteration
		visited[i][j] = 0;
	}

	private boolean isValid(int i, int j) {
		return (i >= 0 && i <= M - 1 && j >= 0 && j <= N - 1);
	}

	private boolean isSafe(int i, int j) {
		return isValid(i, j) && theMine[i][j] > 0;
	}

}
