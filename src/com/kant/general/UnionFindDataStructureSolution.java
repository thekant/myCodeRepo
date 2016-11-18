/**
 * 
 */
package com.kant.general;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * http://www.geeksforgeeks.org/union-find/
 * <p>
 * </p>
 * TODO http://www.geeksforgeeks.org/disjoint-set-data-structures-java-
 * implementation/
 * 
 * @author shashi
 * 
 */
public class UnionFindDataStructureSolution {

	/**
	 * Simple Union Implementation.
	 * 
	 * @author shashi
	 * 
	 */
	class Union {
		private int[] unionDataArray;
		// private int[] rank; for compression

		public Union(int capacity) {
			unionDataArray = new int[capacity];
			// rank = new int[capacity];
			Arrays.fill(unionDataArray, -1);
			// Arrays.fill(rank, 0);
		}

		/**
		 * 
		 * @param index
		 * @return
		 */
		public int find(int index) {
			if (unionDataArray[index] == -1) {
				return index;
			}
			// for compression
			// unionDataArray[index] = find(unionDataArray[index]);
			// return unionDataArray[index];
			return find(unionDataArray[index]);
		}

		/**
		 * 
		 * @param parent1
		 * @param parent2
		 */
		public void union(int child1, int child2) {
			int x = find(child1);
			int y = find(child2);
			// for path compression compare ranks
			// if(rank[x]> rank[y]){
			// unionDataArray[y]=x;
			// rank[x]++;
			// }
			if (x != -1 && y != -1) {
				unionDataArray[y] = x;
			} else {
				throw new InvalidParameterException(
						"index of elements passed are out of range");
			}
		}
	}

	/**
	 * See below for other implementation of Union dataStructure
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		MinimalisticGraph graph = new MinimalisticGraph(3, 3);
		UnionOptimized unionSet = new UnionOptimized(3);
		graph.addEdge(new Edge(0, 1));
		graph.addEdge(new Edge(1, 2));
		graph.addEdge(new Edge(2, 0));

		Iterator<Edge> iterator = graph.getEdgelist().iterator();
		while (iterator.hasNext()) {
			Edge next = iterator.next();
			int set1 = unionSet.find(next.src);
			int set2 = unionSet.find(next.dest);
			if (set1 == set2) {
				System.out.println("Graph has circle");
				return;
			}
			unionSet.union(set1, set2);
		}
	}

}

/**
 * With rank and height compression included.
 * 
 * @author shashi
 * 
 */
class UnionOptimized {
	// can be switched to LinkedList representation [to support for any number
	// of nodes]
	private DataNode[] unionDataArray;

	class DataNode {
		int value = -1; // stores the index
		int rank = 0; // without compression it will be height of tree
						// afterwards it represents size
		DataNode parent = null;

		public DataNode(int value) {
			this.value = value;
		}
	}

	/**
	 * 
	 * @param capacity
	 */
	public UnionOptimized(int capacity) {
		unionDataArray = new DataNode[capacity];
		for (int count = 0; count < capacity; count++)
			unionDataArray[count] = new DataNode(count);
	}

	/**
	 * 
	 * @param index
	 * @return
	 */
	public int find(int index) {
		if (unionDataArray[index].parent == null) {
			return index;
		} else {
			// following code is [simplified] just to make all heights as 1
			// 1. get the root
			DataNode root = unionDataArray[find(unionDataArray[index].parent.value)];
			// 2. set this level's parent to root
			unionDataArray[index].parent = root;
			return root.value;
		}
		// return find(unionDataArray[index].parent.value);
	}

	/**
	 * 
	 * @param index1
	 * @param index2
	 */
	public void union(int index1, int index2) {
		int X = find(index1);
		int Y = find(index2);
		if (X == Y)
			return;
		if (unionDataArray[Y].rank > unionDataArray[X].rank) {
			int temp = X;
			X = Y;
			Y = temp;
		}
		// Y is less rank guy & X is Higher Rank Guy
		unionDataArray[Y].parent = unionDataArray[X];
		unionDataArray[X].rank++;
	}

}

class Edge {
	public int src;
	public int dest;

	/**
	 * @param src
	 * @param dest
	 */
	public Edge(int src, int dest) {
		super();
		this.src = src;
		this.dest = dest;
	}

}

class MinimalisticGraph {
	int vertices;
	int edges;

	private List<Edge> edgeList;

	public MinimalisticGraph(int V, int E) {
		this.vertices = V;
		this.edges = E;
		edgeList = new ArrayList<Edge>();
	}

	public void addEdge(Edge edge) {
		edgeList.add(edge);
	}

	public List<Edge> getEdgelist() {
		return edgeList;
	}
}
