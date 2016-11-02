/**
 * 
 */
package com.kant.datastructure.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * matrix implementation of Graph -1 means no edge
 * 
 * @author shaskant
 *
 */
public class Graph {
	private int[][] data;
	private int Row;

	/**
	 * 
	 */
	public Graph(int[][] aData, int row) {
		this.data = aData;
		Row = row;
		if (aData == null) {
			this.data = new int[Row][Row];
			for (int[] val : data) {
				val = new int[Row];
				Arrays.fill(val, -1);
			}
		}

	}

	/**
	 * more like update edge
	 * 
	 * @param pair
	 * @return
	 */
	public boolean addEdge(Pair pair) {
		return addEdgeWithWt(pair, 1);
	}

	/**
	 * 
	 * @param pair
	 * @param wt
	 * @return
	 */
	public boolean addEdgeWithWt(Pair pair, int wt) {
		if (isValid(pair)) {
			data[pair.src][pair.dest] = wt;
			data[pair.dest][pair.src] = wt;
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param p
	 * @return
	 */
	public int getEdgeWt(Pair p) {
		if (isValid(p)) {
			return data[p.src][p.dest];
		}
		System.out.println("[log]: no such edge exists between " + p.src
				+ " and " + p.dest);
		return -1;
	}

	/**
	 * 
	 * @param pair
	 * @return
	 */
	public boolean removeEdge(Pair pair) {
		if (isValid(pair)) {
			data[pair.src][pair.dest] = -1;
			data[pair.dest][pair.src] = -1;
			return true;
		}
		return false;
	}

	/**
	 * Overhead ..can be optimized.
	 * 
	 * @param src
	 * @return
	 */
	public Integer[] getAdjacent(int src) {
		if (src >= 0 && src < Row) {
			List<Integer> list = new ArrayList<Integer>();
			Integer[] result = new Integer[1];
			for (int i = 0; i < Row; i++) {
				if (data[src][i] != -1)
					list.add(i);
			}
			result = list.toArray(result);
			return result;
		} else
			return null;
	}

	public int getNumberOfvertices() {
		return Row;
	}

	private boolean isValid(Pair p) {
		return (p.src >= 0 && p.dest >= 0 && p.src < Row && p.dest < Row);
	}

	static class Pair {
		public int src;
		public int dest;

		public Pair(int src, int dest) {
			super();
			this.src = src;
			this.dest = dest;
		}

	}
}
