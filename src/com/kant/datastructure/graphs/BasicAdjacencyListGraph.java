/**
 * 
 */
package com.kant.datastructure.graphs;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * This class represents a directed graph using adjacency list representation<br/>
 * BFS implementation
 * 
 * @author shaskant
 *
 */
public class BasicAdjacencyListGraph {

	private int V; // No. of vertices
	private LinkedList<Integer> adj[]; // Adjacency Lists

	@SuppressWarnings("unchecked")
	public BasicAdjacencyListGraph(int v) {
		V = v;
		adj = (LinkedList<Integer>[]) new LinkedList[v];
		for (int i = 0; i < v; ++i)
			adj[i] = new LinkedList<Integer>();
	}

	// Function to add an edge into the graph
	void addEdge(int v, int w) {
		adj[v].add(w);
	}

	/**
	 * 
	 */
	public void printGraph() {
		boolean[] visited = new boolean[V];

		for (int i = 0; i < V; i++)
			if (!visited[i])
				doDfs(i, visited);
	}

	/**
	 * 
	 * @param nv
	 * @param visited
	 */
	private void doDfs(int nv, boolean[] visited) {
		visited[nv] = true;
		System.out.println(nv);

		Iterator<Integer> iterator = adj[nv].iterator();
		while (iterator.hasNext()) {
			int next = iterator.next();
			if (!visited[next]) {
				doDfs(next, visited);
			}
		}
	}

	
	/**
	 * 
	 * @param src
	 */
	void BFS(int src) {
		boolean[] visited = new boolean[V];
		LinkedList<Integer> queue = new LinkedList<>();
		queue.add(src);
		visited[src] = true;

		while (!queue.isEmpty()) {
			int item = queue.poll();
			System.out.println(item);
			Iterator<Integer> iterator = adj[item].iterator();
			while (iterator.hasNext()) {
				int next = iterator.next();
				if (!visited[next]) {
					visited[next] = true;
					queue.add(next);
				}
			}
		}
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		BasicAdjacencyListGraph g = new BasicAdjacencyListGraph(4);

		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(1, 2);
		g.addEdge(2, 0);
		g.addEdge(2, 3);
		g.addEdge(3, 3);

		System.out.println("Following is Breadth First Traversal "
				+ "(starting from vertex 2)");

		g.BFS(2);
	}

}