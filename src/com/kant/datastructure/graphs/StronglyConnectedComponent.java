package com.kant.datastructure.graphs;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

/**
 * To find all scc's in graph:
 * http://www.geeksforgeeks.org/strongly-connected-components.<br/>
 * <br/>
 * To just find if a graph is strongly connected: <br/>
 * 1. do dfs => if all nodes are not visited then return false; <br/>
 * 2. transpose() <br/>
 * 3. do dfs from same vertex as in step 1.. if all vertices are visited then
 * return true. otherwise return false
 * 
 * @author shashi
 * 
 */
public class StronglyConnectedComponent {

	// ----------------------
	// Kosaraju's algorithm
	// ----------------------
	// step 1: do topological sort.
	// step 2: find transpose of the graph
	// step 3: pop 'v' from stack obtained in step 1 , do dfs from 'v' on new
	// graph to get SCC starting from 'v'.
	public static void main(String[] args) {
		DirectedGraph g = new DirectedGraph(5);
		g.addEdge(1, 0);
		g.addEdge(0, 2);
		g.addEdge(2, 1);
		g.addEdge(0, 3);
		g.addEdge(3, 4);

		System.out.println("Following are strongly connected components "
				+ "in given graph ");
		g.printStronglyConnectedComponents();
	}

}

/**
 * 
 * @author shaskant
 *
 */
class DirectedGraph {
	private int V;
	private LinkedList<Integer> adj[];

	public DirectedGraph(int aV) {
		this.V = aV;
		adj = new LinkedList[V];
		for (int i = 0; i < V; i++) {
			adj[i] = new LinkedList<Integer>();
		}
	}

	public DirectedGraph(int aV, LinkedList<Integer>[] edgedata) {
		V = aV;
		adj = edgedata;
	}

	public void addEdge(int src, int des) {
		adj[src].add(des);
	}

	public Iterator<Integer> getAdjacentNodes(int src) {
		if (src > V)
			return null;
		return adj[src].iterator();
	}

	/**
	 * 
	 */
	public void printStronglyConnectedComponents() {
		Stack<Integer> stack = new Stack<Integer>();

		boolean[] visited = new boolean[V];
		Arrays.fill(visited, false);
		for (int i = 0; i < V; i++)
			if (visited[i] == false)
				doDfsUtil(i, visited, stack);

		DirectedGraph invertGph = getTranspose();
		Arrays.fill(visited, false);

		while (!stack.isEmpty()) {
			int thisV = stack.pop();
			if (visited[thisV] == false) {
				invertGph.doDfsUtil(thisV, visited, null);
				System.out.println();
			}
		}
	}

	/**
	 * 
	 * @return
	 */
	private DirectedGraph getTranspose() {
		LinkedList<Integer>[] inverted = new LinkedList[V];

		for (int i = 0; i < V; i++) {
			Iterator<Integer> adjacentNodes = getAdjacentNodes(i);
			while (adjacentNodes.hasNext()) {
				int next = adjacentNodes.next();
				// for each i --> next
				// create next --> i
				if (inverted[next] == null)
					inverted[next] = new LinkedList<Integer>();
				inverted[next].add(i);
			}
		}

		return new DirectedGraph(V, inverted);
	}

	/**
	 * Prints scc if stack is NULL.
	 * 
	 * @param thisV
	 * @param visited
	 * @param stack
	 */
	private void doDfsUtil(int thisV, boolean[] visited, Stack<Integer> stack) {
		visited[thisV] = true;
		if (stack == null) {
			System.out.print(" " + thisV + " ");
		}

		Iterator<Integer> adjacentNodes = this.getAdjacentNodes(thisV);
		while (adjacentNodes.hasNext()) {
			int next = adjacentNodes.next().intValue();
			if (!visited[next]) {
				doDfsUtil(next, visited, stack);
			}
		}
		if (stack != null)
			stack.push(thisV);
	}
}
