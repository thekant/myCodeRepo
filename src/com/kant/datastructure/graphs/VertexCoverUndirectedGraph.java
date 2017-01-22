/**
 * 
 */
package com.kant.datastructure.graphs;

/**
 * 
 * A vertex cover of an undirected graph is a subset of its vertices such that
 * for every edge (u, v) of the graph, either u or v is in vertex cover.
 * Although the name is Vertex Cover, the set covers all edges of the given
 * graph. Given an undirected graph, the vertex cover problem is to find minimum
 * size vertex cover. <br/>
 * <br/>
 * Vertex Cover Problem is a known NP Complete problem, i.e., there is no
 * polynomial time solution for this unless P = NP
 * 
 * Vertex Cover - find minimum set of vertex that covers all the edges in the graph
 * Spanning tree - covers all vertices of graph with minimum number of edges.
 * 
 * http://www.geeksforgeeks.org/vertex-cover-problem-set-1-introduction-
 * approximate-algorithm-2/
 * 
 * @author shashi
 * 
 */
//Java Program to print Vertex Cover of a given undirected graph
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Approximate Algorithm for Vertex Cover:
 * 
 * 1) Initialize the result as {}<br/>
 * 2) Consider a set of all edges in given graph. Let the set be E.<br/>
 * 3) Do following while E is not empty ...<br/>
 * a) Pick an arbitrary edge (u, v) from set E and add 'u' and 'v' to result ...<br/>
 * b) Remove the edge (u, v) from E. <br/>
 * 4) Return result
 */
public class VertexCoverUndirectedGraph {
	// Driver method
	public static void main(String args[]) {
		// Create a graph given in the above diagram
		XGraph g = new XGraph(7);
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(1, 3);
		g.addEdge(3, 4);
		g.addEdge(4, 5);
		g.addEdge(5, 6);

		g.printVertexCover();
	}

}

// This class represents an undirected graph using adjacency list
class XGraph {
	private int V; // No. of vertices
	private LinkedList<Integer> adj[];

	XGraph(int v) {
		V = v;
		adj = new LinkedList[v];
		for (int i = 0; i < v; ++i)
			adj[i] = new LinkedList<Integer>();
	}

	void addEdge(int v, int w) {
		adj[v].add(w); // Add w to v's list.
		adj[w].add(v); // Graph is undirected
	}

	// The function to print vertex cover
	void printVertexCover() {
		// Initialize all vertices as not visited.
		boolean visited[] = new boolean[V];
		for (int i = 0; i < V; i++)
			visited[i] = false;

		Iterator<Integer> i;

		// Consider all edges one by one
		for (int u = 0; u < V; u++) {
			// An edge is only picked when both visited[u]
			// and visited[v] are false
			if (visited[u] == false) {
				// Go through all adjacent of u and pick the
				// first not yet visited vertex (We are basically
				// picking an edge (u, v) from remaining edges.
				i = adj[u].iterator();
				while (i.hasNext()) {
					int v = i.next();
					if (visited[v] == false) {
						// Add the vertices (u, v) to the result
						// set. We make the vertex u and v visited
						// so that all edges from/to them would
						// be ignored
						visited[v] = true;
						visited[u] = true;
						break;
					}
				}
			}
		}
		// Print the vertex cover
		for (int j = 0; j < V; j++)
			if (visited[j])
				System.out.print(j + " ");
	}

}
