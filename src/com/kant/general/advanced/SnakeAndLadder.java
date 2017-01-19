/**
 * 
 */
package com.kant.general.advanced;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shashi
 * 
 */
public class SnakeAndLadder {

	/**
	 * This function returns minimum number of dice throws required to Reach
	 * last cell from 0'th cell in a 'Snake and Ladder' game. <br/>
	 * 
	 * move[] is an array of size N where N is no. of cells on board. If there
	 * is no snake or ladder from cell i, then move[i] is -1 Otherwise move[i]
	 * contains cell to which snake or ladder at i takes to.
	 */
	public int getMinDiceThrowsToReachEnd(int move[]) {
		// The graph has N vertices. Mark all the vertices as
		// not visited
		int N = move.length;
		boolean[] visited = new boolean[N];
		for (int i = 0; i < N; i++)
			visited[i] = false;

		// Create a queue for BFS
		List<QueueEntry> queue = new ArrayList<QueueEntry>();

		// Mark the node 0 as visited and enqueue it.
		visited[0] = true;
		queue.add(new QueueEntry(0, 0));

		QueueEntry qEntry = null; 
		while (queue.size() != 0) {
			qEntry = queue.remove(0);
			int vertex = qEntry.getVertex();

			// reached final cell
			if (vertex == N - 1)
				break;

			// check its adjacent vertices reachable
			// through a dice throw.
			for (int j = vertex + 1; j <= (vertex + 6) && j < N; ++j) {
				if (!visited[j]) {
					QueueEntry newEntry = new QueueEntry();
					newEntry.setSteps(qEntry.getSteps() + 1);
					visited[j] = true;

					// Check if there a snake or ladder at 'j'
					// then tail of snake or top of ladder
					// become the adjacent of 'i'
					if (move[j] != -1)
						newEntry.setVertex(move[j]);
					else
						newEntry.setVertex(j);
					queue.add(newEntry);
				}
			}
		}

		// We reach here when 'qe' has last vertex
		// return the distance of vertex in 'qe'
		return qEntry.getSteps();
	}

	/**
	 * Driver program to test.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Construct the 'Snake and Ladder' board.
		int N = 30;
		int moves[] = new int[N];
		for (int i = 0; i < N; i++)
			moves[i] = -1;

		// Ladders
		moves[2] = 21;// from 2 to 21 a ladder
		moves[4] = 7;
		moves[10] = 25;
		moves[19] = 28;

		// Snakes
		moves[26] = 0;// from 26 to 0 a snake
		moves[20] = 8;
		moves[16] = 3;
		moves[18] = 6;

		System.out.println("Min Dice throws required is "
				+ new SnakeAndLadder().getMinDiceThrowsToReachEnd(moves));
	}
}

/**
 * An entry in queue used in BFS.
 * 
 * @author shaskant
 *
 */
class QueueEntry {
	private int vertex; // Vertex number
	private int steps; // Steps taken to reach vertex from source

	public QueueEntry() {
	}

	public QueueEntry(int aVertex, int aDist) {
		this.vertex = aVertex;
		this.steps = aDist;
	}

	public int getSteps() {
		return steps;
	}

	public void setSteps(int distance) {
		this.steps = distance;
	}

	public int getVertex() {
		return vertex;
	}

	public void setVertex(int vertex) {
		this.vertex = vertex;
	}
}
