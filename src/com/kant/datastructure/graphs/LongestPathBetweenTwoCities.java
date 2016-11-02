/**
 * 
 */
package com.kant.datastructure.graphs;

import com.kant.datastructure.graphs.Graph.Pair;

/**
 * http://www.geeksforgeeks.org/longest-path-between-any-pair-of-vertices/
 * O(V*(V+E))
 * 
 * @author shaskant
 *
 */
public class LongestPathBetweenTwoCities {

	private Graph cityMap;
	private int max_len;// stores final result

	/**
	 * 
	 */
	public LongestPathBetweenTwoCities(Graph map) {
		cityMap = map;
	}

	/**
	 * 
	 * @return
	 */
	public int getLongestPath() {
		boolean[] visited = new boolean[cityMap.getNumberOfvertices()];
		max_len = Integer.MIN_VALUE;
		doDfs(0, 0, visited);
		return max_len;
	}

	/**
	 * 
	 * @param src
	 * @param g
	 * @param prevLen
	 * @param visited
	 */
	public void doDfs(int src, int prevLen, boolean[] visited) {
		visited[src] = true;
		int currLen = 0;

		// visit all adjacent nodes
		for (int i = 0; i < cityMap.getAdjacent(src).length; i++) {
			int des = cityMap.getAdjacent(src)[i];
			if (!visited[des]) {
				// Total length of cable from src city
				// to its adjacent
				currLen = prevLen + cityMap.getEdgeWt(new Pair(src, des));
				doDfs(des, currLen, visited);
			}
			if (max_len < currLen)
				max_len = currLen;
			currLen = 0;
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Graph cityMap = new Graph(null, 6);
		cityMap.addEdgeWithWt(new Pair(0, 1), 3);
		cityMap.addEdgeWithWt(new Pair(1, 2), 4);
		cityMap.addEdgeWithWt(new Pair(1, 5), 2);
		cityMap.addEdgeWithWt(new Pair(3, 5), 6);
		cityMap.addEdgeWithWt(new Pair(4, 5), 5);

		LongestPathBetweenTwoCities problem = new LongestPathBetweenTwoCities(
				cityMap);
		System.out.println(problem.getLongestPath());

	}

}
