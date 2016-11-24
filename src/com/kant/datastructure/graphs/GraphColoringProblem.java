/**
 * 
 */
package com.kant.datastructure.graphs;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author shaskant
 *
 */
public class GraphColoringProblem {
	private BasicGraph graph;
	private COLORS[] colors;// for each vertex

	/**
	 * 
	 * @param graphe
	 */
	public GraphColoringProblem(BasicGraph graphe) {
		graph = graphe;
		colors = new COLORS[graphe.getsize()];
		Arrays.fill(colors, COLORS.WHITE);
	}

	/**
	 * 
	 * @param vertex
	 * @param numColors
	 * @return
	 */
	public boolean solveGraphColorProblem(int vertex, int numColors) {
		if (vertex >= graph.getsize()) {
			printColorMap();
			return true;
		}
		for (int i = 0; i < numColors; i++) {
			// try a possible color and see if it leads to all vertices being colored
			if (isColorPossible(vertex, COLORS.values()[i])) {
				colors[vertex] = COLORS.values()[i];
				if (solveGraphColorProblem(vertex + 1, numColors)) {
					return true;
				}
				// retry another color
			}
		}
		return false;
	}

	/**
	 * 
	 */
	private void printColorMap() {
		System.out.println("Graph coloring output:\n----------------------");
		for (int i = 0; i < colors.length; i++) {
			System.out.println("vertex " + i + " colored with " + colors[i]);
		}
		System.out.println("----------------------");
	}

	/**
	 * 
	 * @param v
	 * @param color
	 * @return
	 */
	private boolean isColorPossible(int v, COLORS color) {
		Iterator<Integer> iterator = graph.getNeighbors(v).iterator();
		while (iterator.hasNext()) {
			int next = iterator.next().intValue();
			if (colors[next] == color) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BasicGraph graph = new BasicGraph(4);
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(0, 3);
		graph.addEdge(1, 2);
		graph.addEdge(2, 3);
		graph.printGraph();
		GraphColoringProblem prob = new GraphColoringProblem(graph);
		if (prob.solveGraphColorProblem(0, 2)) {
			System.out.println("Graph coloring possible");
		} else {
			System.out.println("Graph coloring not possible");
		}
	}

}

/**
 * 
 * @author shaskant
 *
 */
class BasicGraph {
	private LinkedList<Integer>[] adjacents;

	@SuppressWarnings("unchecked")
	public BasicGraph(int size) {
		adjacents = new LinkedList[size];
		for (int i = 0; i < adjacents.length; i++) {
			adjacents[i] = new LinkedList<Integer>();
		}
	}

	/**
	 * 
	 * @param u
	 * @param v
	 */
	public void addEdge(int u, int v) {
		adjacents[u].add(v);
		adjacents[v].add(u);
	}

	public LinkedList<Integer> getNeighbors(int u) {
		return adjacents[u];
	}

	public int getsize() {
		return adjacents.length;
	}

	public void printGraph() {
		for (int i = 0; i < adjacents.length; i++) {
			System.out.print("\n[" + i + "]=>");
			Iterator<Integer> iterator = adjacents[i].iterator();
			while (iterator.hasNext()) {
				System.out.print("[" + iterator.next() + "]");
			}
		}
		System.out.println();
	}

}

enum COLORS {
	RED(0), BLUE(1), GREEN(2), YELLOW(3), WHITE(4), VOILET(5), TESTCOLOR(6);
	int value;

	private COLORS(int val) {
		value = val;
	}
}