/**
 * 
 */
package com.kant.datastructure.graphs;

/**
 * Given a directed graph and two vertices A and B in it, count all possible
 * walks from A to B with exactly k edges on the walk.
 * 
 * @author shashi
 * 
 */
public class KEdgeWalkFromSourceToDestination {

	public static final int V = 4;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int graph[][] = { { 0, 1, 1, 1 }, { 0, 0, 0, 1 }, { 0, 0, 0, 1 },
				{ 0, 0, 0, 0 } };
		int u = 0, v = 3, k = 2;
		System.out.println(countWalksRec(graph, u, v, k));
	}

	/**
	 * recursive approach.
	 * 
	 * @param gph
	 * @param src
	 * @param dest
	 * @param k
	 * @return
	 */
	public static int countWalksRec(int[][] gph, int src, int dest, int k) {
		if (k == 0 && src == dest)
			return 1;
		if (k == 1 && gph[src][dest] == 1)
			return 1;

		int result = 0;
		//ask neighbors only if k is more than 1.
		if (k > 1) {
			for (int i = 0; i < V; i++) {
				if (gph[src][i] == 1) {
					result += countWalksRec(gph, i, dest, k - 1);
				}
			}
		}
		return result;
	}

	/**
	 * A Dynamic programming based function to count walks from u to v with k
	 * edges
	 * 
	 * @param graph
	 * @param u
	 * @param v
	 * @param k
	 * @return
	 */
	public static int countwalks(int graph[][], int u, int v, int k) {
		// Table to be filled up using DP. The value count[i][j][e] will
		// store count of possible walks from i to j with exactly k edges
		int[][][] count = new int[V][V][k + 1];

		// Loop for number of edges from 0 to k
		for (int edgeCount = 0; edgeCount <= k; edgeCount++) {
			for (int src = 0; src < V; src++) {
				for (int dest = 0; dest < V; dest++) {
					// initialize value
					count[src][dest][edgeCount] = 0;

					// graph[x][y] x == y & eC=0
					if (edgeCount == 0 && src == dest)
						count[src][dest][edgeCount] = 1;

					// if src and dest are connected and ec == 1
					if (edgeCount == 1 && graph[src][dest] == 1)
						count[src][dest][edgeCount] = 1;

					// go to adjacent only when number of edges is more than 1
					if (edgeCount > 1) {
						for (int nextAdj = 0; nextAdj < V; nextAdj++)
							if (graph[src][nextAdj] == 1)
								count[src][dest][edgeCount] += count[nextAdj][dest][edgeCount - 1];
					}
				}
			}
		}
		return count[u][v][k];
	}
}
