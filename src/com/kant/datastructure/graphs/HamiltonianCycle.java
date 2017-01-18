/**
 * 
 */
package com.kant.datastructure.graphs;

/**
 * Backtracking solution [np-complete]
 * 
 * @author shaskant
 *
 */
public class HamiltonianCycle {
	private int V;
	private int[] path;

	public int hamCycle(int graph[][], int aV) {
		init(aV);
		/**
		 * Put vertex 0 as the first vertex in the path. NOTE: If there is a
		 * Hamiltonian Cycle, then the path can be started from any point of the
		 * cycle as the graph is undirected.
		 **/
		path[0] = 0;
		if (hamCycleUtil(graph, 1) == false) {
			System.out.println("\nSolution does not exist");
			return 0;
		}

		printSolution();
		path = null;
		V = -1;
		return 1;
	}

	/**
	 * @param aV
	 */
	private void init(int aV) {
		V = aV;
		path = new int[V];
		for (int i = 0; i < V; i++)
			path[i] = -1;
	}

	/**
	 * 
	 */
	private void printSolution() {
		System.out.println("Solution: HamCycle o/p");
		for (int i = 0; i < V; i++) {
			System.out.print(path[i] + "->");
		}
		System.out.println(path[0]);
	}

	/**
	 * 
	 * @param graph
	 * @param pos
	 * @return
	 */
	private boolean hamCycleUtil(int[][] graph, int pos) {
		if (pos == V) {
			// if start and end points on the path are connected ..that's
			// hamiltonian cycle
			if (graph[path[pos - 1]][path[0]] == 1)
				return true;
			else
				return false;
		}

		for (int v = 1; v < V; v++) {
			/*
			 * Check if this vertex can be added to Hamiltonian Cycle
			 */
			if (isSafe(v, graph, pos)) {
				path[pos] = v;

				/* recur to construct rest of the path */
				if (hamCycleUtil(graph, pos + 1) == true)
					return true;
				/*
				 * If adding vertex v doesn't lead to a solution, then remove it
				 */
				path[pos] = -1;
			}
		}

		return false;
	}

	/**
	 * A utility function to check if the vertex v can be added at index 'pos'in
	 * the Hamiltonian Cycle constructed so far (stored in 'path[]')
	 * 
	 * Should be adjacent to path(pos-1) and not already included in path.
	 **/
	private boolean isSafe(int v, int graph[][], int pos) {
		/*
		 * Check if this vertex is an adjacent vertex of the previously added
		 * vertex.
		 */
		if (graph[path[pos - 1]][v] == 0)
			return false;
		/*
		 * Check if the vertex has already been included. This step can be
		 * optimized by creating an array of size V
		 */
		for (int i = 0; i < pos; i++)
			if (path[i] == v)
				return false;

		return true;
	}

	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		HamiltonianCycle hamiltonian = new HamiltonianCycle();
		/* 
		 * Let us create the following graph
			(0)--(1)--(2)
			|   / \   |
			|  /   \  |
			| /     \ |
			(3)-------(4)    
		*/
		int graph1[][] = { { 0, 1, 0, 1, 0 }, { 1, 0, 1, 1, 1 },
				{ 0, 1, 0, 0, 1 }, { 1, 1, 0, 0, 1 }, { 0, 1, 1, 1, 0 }, };

		// Print the solution
		hamiltonian.hamCycle(graph1,5);

		/* 
		 * Let us create the following graph
			(0)--(1)--(2)
			|   / \   |
			|  /   \  |
			| /     \ |
			(3)       (4)    
		*/
		int graph2[][] = { { 0, 1, 0, 1, 0 }, { 1, 0, 1, 1, 1 },
				{ 0, 1, 0, 0, 1 }, { 1, 1, 0, 0, 0 }, { 0, 1, 1, 0, 0 }, };

		//Print the solution
		hamiltonian.hamCycle(graph2,5);
}
	
}
