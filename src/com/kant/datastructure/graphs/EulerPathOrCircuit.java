/**
 * 
 */
package com.kant.datastructure.graphs;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Eulerian Path is a path in graph that visits every edge exactly
 * once(semi_euler).<br/>
 * Eulerian Circuit is an Eulerian Path which starts and ends on the same
 * vertex(euler).
 * 
 * http://www.geeksforgeeks.org/eulerian-path-and-circuit/
 * 
 * @author shaskant
 *
 */
public class EulerPathOrCircuit {

	/**
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		AdjacencyGraph g1 = new AdjacencyGraph(5);
		g1.addEdge(1, 0);
		g1.addEdge(0, 2);
		g1.addEdge(2, 1);
		g1.addEdge(0, 3);
		g1.addEdge(3, 4);
		g1.test();

		AdjacencyGraph g2 = new AdjacencyGraph(5);
		g2.addEdge(1, 0);
		g2.addEdge(0, 2);
		g2.addEdge(2, 1);
		g2.addEdge(0, 3);
		g2.addEdge(3, 4);
		g2.addEdge(4, 0);
		g2.test();

		AdjacencyGraph g3 = new AdjacencyGraph(5);
		g3.addEdge(1, 0);
		g3.addEdge(0, 2);
		g3.addEdge(2, 1);
		g3.addEdge(0, 3);
		g3.addEdge(3, 4);
		g3.addEdge(1, 3);
		g3.test();

		// Let us create a graph with 3 vertices
		// connected in the form of cycle
		AdjacencyGraph g4 = new AdjacencyGraph(3);
		g4.addEdge(0, 1);
		g4.addEdge(1, 2);
		g4.addEdge(2, 0);
		g4.test();

		// Let us create a graph with all veritces
		// with zero degree
		AdjacencyGraph g5 = new AdjacencyGraph(3);
		g5.test();
	}
}

/**
 * Undirected graph.
 * 
 * @author shaskant
 *
 */
class AdjacencyGraph {
	private int V; // No. of vertices
	private LinkedList<Integer> adj[];

	@SuppressWarnings("unchecked")
	public AdjacencyGraph(int v) {
		V = v;
		adj = new LinkedList[v];
		for (int i = 0; i < v; ++i)
			adj[i] = new LinkedList<Integer>();
	}

	/**
	 * 
	 * @param v
	 * @param w
	 */
	public void addEdge(int v, int w) {
		adj[v].add(w);
		adj[w].add(v);
	}

	private void dfsUtil(int v, boolean visited[]) {
		visited[v] = true;
		// Recur for all the vertices adjacent to this vertex
		Iterator<Integer> i = adj[v].listIterator();
		while (i.hasNext()) {
			int n = i.next();
			if (!visited[n])
				dfsUtil(n, visited);
		}
	}

	/**
	 * Method to check if all non-zero degree vertices are connected. It mainly
	 * does DFS traversal starting from
	 * 
	 * @return
	 */
	private boolean isConnected() {
		boolean visited[] = new boolean[V];
		int i;
		for (i = 0; i < V; i++)
			visited[i] = false;

		// Find a vertex with non-zero degree
		for (i = 0; i < V; i++)
			if (adj[i].size() != 0)
				break;
		// If there are no edges in the graph, return true
		if (i == V)
			return true;

		// Start DFS traversal from a vertex with non-zero degree
		dfsUtil(i, visited);

		// Check if all non-zero degree vertices are visited
		for (i = 0; i < V; i++)
			if (adj[i].size() > 0 && visited[i] == false)
				return false;

		return true;
	}

	/**
	 * The function returns one of the following values
	 *  0 --> If graph is not Eulerian 
	 *  1 --> If graph has an Euler path (Semi-Eulerian) 
	 *  2 --> If graph has an Euler Circuit (Eulerian)
	 * 
	 **/
	private EULERPROP isEulerian() {
		/**
		 *  Check if all non-zero degree vertices are connected
		 */
		if (isConnected() == false)
			return EULERPROP.NOT_EULERIAN;

		/**
		 * Count vertices with odd degree
		 */
		int odd = 0;
		for (int i = 0; i < V; i++)
			if (adj[i].size() % 2 != 0)
				odd++;

		/**
		 *  If count is more than 2, then graph is not Eulerian
		 */
		if (odd > 2)
			return EULERPROP.NOT_EULERIAN;

		// If odd count is 2, then semi-eulerian.
		// If odd count is 0, then eulerian
		// Note that odd count can never be 1 for undirected graph
		return (odd == 2) ? EULERPROP.EULER_PATH
				: EULERPROP.EULER_CIRCUIT;
	}

	/**
	 * 
	 */
	public EULERPROP test() {
		EULERPROP result = isEulerian();
		if (result == EULERPROP.NOT_EULERIAN)
			System.out.println("graph is not Eulerian");
		else if (result == EULERPROP.EULER_PATH)
			System.out.println("graph has a Euler path");
		else
			System.out.println("graph has a Euler cycle");
		return result;
	}
}

enum EULERPROP {
	NOT_EULERIAN, EULER_PATH, EULER_CIRCUIT;
}