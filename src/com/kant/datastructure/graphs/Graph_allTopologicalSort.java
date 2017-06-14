/**
 * 
 */
package com.kant.datastructure.graphs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author shaskant
 *
 */
public class Graph_allTopologicalSort {
	static public String value = Graph_TopoLogicalSort.class.getPackage()
			.getName();

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Graph_allTopologicalSort g = new Graph_allTopologicalSort(6);
		g.addEdge(5, 2);
		g.addEdge(5, 0);
		g.addEdge(4, 0);
		g.addEdge(4, 1);
		g.addEdge(2, 3);
		g.addEdge(3, 1);

		g.alltopologicalSort();
	}

	private int V;// no. of vertices
	private LinkedList<Integer>[] adj;
	private int[] inDegree;

	@SuppressWarnings("unchecked")
	public Graph_allTopologicalSort(int aV) {
		this.V = aV;
		adj = new LinkedList[V];
		for (int i = 0; i < V; i++)
			adj[i] = new LinkedList<Integer>();
		inDegree = new int[aV];
	}

	/**
	 * NOTE: on way edge only [FOR DAG] , also maintains indegree
	 * 
	 * @param src
	 * @param des
	 */
	public void addEdge(int src, int des) {
		adj[src].add(des); // src --> des
		inDegree[des]++;
	}

	public void alltopologicalSort() {
		// Mark all the vertices as not visited
		boolean[] visited = new boolean[V];
		alltopologicalSortUtil(new ArrayList<Integer>(), visited);
	}

	private void alltopologicalSortUtil(List<Integer> res, boolean[] visited) {
		boolean flag = false;
		for (int i = 0; i < V; i++) {
			/**
			 * If indegree is 0 and not yet visited then only choose that vertex
			 */
			if (inDegree[i] == 0 && !visited[i]) {
				// reducing indegree of adjacent vertices
				Iterator<Integer> iterator = adj[i].iterator();
				while (iterator.hasNext())
					inDegree[iterator.next()]--;

				// including in result
				res.add(i);
				visited[i] = true;
				alltopologicalSortUtil(res, visited);

				/**
				 * resetting visited, res and indegree for backtracking
				 */
				visited[i] = false;
				res.remove(res.size() - 1);
				iterator = adj[i].iterator();
				while (iterator.hasNext())
					inDegree[iterator.next()]++;
				flag = true;
			}
		}

		// We reach here if all vertices are visited.
		// So we print the solution here
		if (!flag) {
			for (int i = 0; i < res.size(); i++)
				System.out.print(res.get(i) + " ");
			System.out.println();
		}

	}
}
