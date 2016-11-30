package com.kant.datastructure.graphs;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * http://www.geeksforgeeks.org/find-paths-from-corner-cell-to-middle-cell-in-
 * maze/ <br/>
 * The idea is to use backtracking. We start with each corner cell of the maze
 * and recursively checks if it leads to the solution or not. Following is the
 * Backtracking algorithm –
 * 
 * If destination is reached
 * 
 * print the path Else
 * 
 * Mark current cell as visited and add it to path array. Move forward in all 4
 * allowed directions and recursively check if any of them leads to a solution.
 * If none of the above solutions work then mark this cell as not visitedand
 * remove it from path array.
 * 
 * @author shaskant
 *
 */
public class FindPathsFromCornerToMiddle {

	private int M, N;
	private int theMaze[][];
	// For searching in all 4 direction
	private int row[] = { -1, 1, 0, 0 };
	private int col[] = { 0, 0, -1, 1 };

	// Cordinates of 4 corners of matrix
	private int _row[];
	private int _col[];

	public FindPathsFromCornerToMiddle(int[][] maze) {
		theMaze = maze;
		M = maze.length;
		N = maze[0].length;
		_row = new int[] { 0, 0, M - 1, M - 1 };
		_col = new int[] { 0, N - 1, 0, N - 1 };

	}

	private boolean isValid(Pair<Integer, Integer> pt) {
		return (pt.first >= 0) && (pt.first <= M - 1) && (pt.second >= 0)
				&& (pt.second <= N - 1);
	}

	// Function to print path from source to middle coordinate
	public void printPath(List<Pair<Integer, Integer>> path) {
		Iterator<Pair<Integer, Integer>> iterator = path.iterator();
		while (iterator.hasNext()) {
			System.out.print("->");
			System.out.print(iterator.next());
		}
		System.out.println();
	}

	private void findPathInMazeUtil(LinkedList<Pair<Integer, Integer>> path,
			Set<Pair<Integer, Integer>> visited, Pair<Integer, Integer> curr) {
		if (curr.first == M / 2 && curr.second == N / 2) {
			printPath(path);
			return;
		}
		// spread in all 4 directions
		for (int i = 0; i < 4; ++i) {
			int n = theMaze[curr.first][curr.second];

			// We can move N cells in either of 4 directions
			int x = curr.first + row[i] * n;
			int y = curr.second + col[i] * n;

			Pair<Integer, Integer> next = new Pair<>(x, y);
			if (isValid(next) && !visited.contains(next)) {
				// mark cell as visited
				visited.add(next);
				// add cell to current path
				path.addLast(next);
				findPathInMazeUtil(path, visited, next);
				// backtrack
				path.removeLast();
				// remove cell from current path
				visited.remove(next);
			}
		}
	}

	public void findPathInMaze() {
		// list to store complete path from source to destination
		LinkedList<Pair<Integer, Integer>> path = new LinkedList<>();
		// to store cells already visited in current path
		Set<Pair<Integer, Integer>> visited = new HashSet<>();

		// Consider each corners as the starting
		// point and search in maze
		for (int i = 0; i < 4; ++i) {
			int x = _row[i];
			int y = _col[i];
			// Constructs a Pair object
			Pair<Integer, Integer> pt = new Pair<>(x, y);
			// mark cell as visited
			visited.add(pt);
			// add cell to current path
			path.add(pt);
			findPathInMazeUtil(path, visited, pt);
			// backtrack
			path.removeLast();
			// remove cell from current path
			visited.remove(pt);
		}
	}

	public static void main(String[] args) {
		int maze[][] = { { 3, 5, 4, 4, 7, 3, 4, 6, 3 },
				{ 6, 7, 5, 6, 6, 2, 6, 6, 2 }, { 3, 3, 4, 3, 2, 5, 4, 7, 2 },
				{ 6, 5, 5, 1, 2, 3, 6, 5, 6 }, { 3, 3, 4, 3, 0, 1, 4, 3, 4 },
				{ 3, 5, 4, 3, 2, 2, 3, 3, 5 }, { 3, 5, 4, 3, 2, 6, 4, 4, 3 },
				{ 3, 5, 1, 3, 7, 5, 3, 6, 4 }, { 6, 2, 4, 3, 4, 5, 4, 5, 1 } };

		FindPathsFromCornerToMiddle prob = new FindPathsFromCornerToMiddle(maze);
		prob.findPathInMaze();

	}

}

class Pair<P, T> {
	P first;
	T second;

	/**
	 * 
	 * @param val1
	 * @param val2
	 */
	public Pair(P val1, T val2) {
		this.first = val1;
		this.second = val2;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		Pair<P, T> p = (Pair<P, T>) obj;
		return this.first.equals(p.first) && this.second.equals(p.second);
	}

	@Override
	public int hashCode() {
		return first.hashCode() + 3 * second.hashCode();
	}

	@Override
	public String toString() {
		return " (" + first.toString() + " , " + second.toString() + ") ";
	}

}
