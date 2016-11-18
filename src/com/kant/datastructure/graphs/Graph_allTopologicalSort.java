/**
 * 
 */
package com.kant.datastructure.graphs;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author shaskant
 *
 */
public class Graph_allTopologicalSort {
	static public String value=Graph_TopoLogicalSort.class.getPackage().getName();

	public static void main(String[] args) {
		System.out.println(value);
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

		int[] res = new int[V];
		alltopologicalSortUtil(res, visited);
	}

	private void alltopologicalSortUtil(int[] res, boolean[] visited) {
		
	}
}
