/**
 * 
 */
package com.kant.datastructure.graphs;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of
 * vertices such that for every directed edge uv, vertex u comes before v in the
 * ordering. Topological Sorting for a graph is not possible if the graph is not
 * a DAG. <br/>
 * Time Complexity: The above algorithm is simply DFS with an extra stack. So
 * time complexity is same as DFS which is O(V+E). <br/>
 * Applications: <b>Topological Sorting</b> is mainly used for scheduling jobs
 * from the given dependencies among jobs. In computer science, applications of
 * this type arise in instruction scheduling, ordering of formula cell
 * evaluation when recomputing formula values in spreadsheets, logic synthesis,
 * determining the order of compilation tasks to perform in makefiles, data
 * serialization, and resolving symbol dependencies in linkers
 * 
 * 
 * http://www.geeksforgeeks.org/shortest-path-for-directed-acyclic-graphs/
 * 
 * @author shaskant
 *
 */
public class Graph_TopoLogicalSort {
	private int V;// no. of vertices
	private LinkedList<Integer>[] adj;

	@SuppressWarnings("unchecked")
	public Graph_TopoLogicalSort(int aV) {
		this.V = aV;
		adj = new LinkedList[V];
		for (int i = 0; i < V; i++)
			adj[i] = new LinkedList<Integer>();
	}

	/**
	 * NOTE: on way edge only [FOR DAG]
	 * 
	 * @param src
	 * @param des
	 */
	public void addEdge(int src, int des) {
		adj[src].add(des);
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
	 * The algorithm loops through each node of the graph, in an arbitrary
	 * order, initiating a depth-first search that terminates when it hits any
	 * node that has already been visited since the beginning of the topological
	 * sort or the node has no outgoing edges (i.e. a leaf node):
	 * 
	 * <br/>
	 * Each node n gets prepended to the output list L only after considering
	 * all other nodes which depend on n (all descendants of n in the graph).
	 * Specifically, when the algorithm adds node n, we are guaranteed that all
	 * nodes which depend on n are already in the output list L: they were added
	 * to L either by the recursive call to visit() which ended before the call
	 * to visit n, or by a call to visit() which started even before the call to
	 * visit n. Since each edge and node is visited once, the algorithm runs in
	 * linear time
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

	public void topologicalTraverse() {
		boolean[] visited = new boolean[V];
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < V; i++)
			if (!visited[i])
				doTopologicalSort(i, stack, visited);

		while (!stack.isEmpty()) {
			System.out.print(stack.pop() + " ");
		}
	}

	/**
	 * 
	 * @param nv
	 * @param stack
	 * @param visited
	 */
	private void doTopologicalSort(int nv, Stack<Integer> stack,
			boolean[] visited) {
		visited[nv] = true;

		// visit all neighbors
		Iterator<Integer> iterator = adj[nv].iterator();
		while (iterator.hasNext()) {
			int next = iterator.next();
			if (!visited[next]) {
				doTopologicalSort(next, stack, visited);
			}
		}
		stack.push(nv);
	}

	public static void main(String[] args) {
		Graph_TopoLogicalSort g = new Graph_TopoLogicalSort(6);
		g.addEdge(5, 2);
		g.addEdge(5, 0);
		g.addEdge(4, 0);
		g.addEdge(4, 1);
		g.addEdge(2, 3);
		g.addEdge(3, 1);

		g.topologicalTraverse();
	}
}
