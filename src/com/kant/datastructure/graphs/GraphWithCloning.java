/**
 * 
 */
package com.kant.datastructure.graphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author shaskant
 *
 */
public class GraphWithCloning<T> {
	private GraphNode<T> srcG; // since it's connected graph

	/**
	 * 
	 */
	public GraphWithCloning(GraphNode<T> src) {
		srcG = src;
	}

	public GraphNode<T> getSource() {
		return srcG;
	}

	public static void main(String[] args) {
		GraphWithCloning<Integer> graph = new GraphWithCloning<Integer>(
				buildGraphForTest());
		System.out.println("BFS traversal of a graph before cloning");
		graph.doBFS();
		GraphNode<Integer> newSource = graph.cloneGraph();
		graph = new GraphWithCloning<Integer>(newSource);
		System.out.println("BFS traversal of a graph after cloning");
		graph.doBFS();
	}

	/**
	 * 
	 * @return
	 */
	private GraphNode<T> cloneGraph() {
		LinkedList<GraphNode<T>> queue = new LinkedList<>();
		queue.add(srcG);
		HashMap<GraphNode<T>, GraphNode<T>> map = new HashMap<>();
		map.put(srcG, new GraphNode<T>(srcG.getInfo()));

		while (!queue.isEmpty()) {
			GraphNode<T> cur = queue.poll();
			// no processing needed.. look for neighbors
			if (cur.getNeighbours() != null) {
				Iterator<GraphNode<T>> iterator = cur.getNeighbours()
						.iterator();
				while (iterator.hasNext()) {
					GraphNode<T> next = iterator.next();
					if (!map.containsKey(next)) {
						GraphNode<T> next2 = new GraphNode<T>(next.getInfo());
						
						map.put(next, next2);
						map.get(cur).getNeighbours().add(next2);
						next2.getNeighbours().add(map.get(cur));
				
						queue.add(next);
					}
				}
			}

		}

		return map.get(srcG);
	}

	private void doBFS() {
		LinkedList<GraphNode<T>> queue = new LinkedList<>();
		queue.add(srcG);
		Set<T> visited = new HashSet<>();
		visited.add(srcG.getInfo());

		while (!queue.isEmpty()) {
			GraphNode<T> cur = queue.poll();
			System.out.println(cur.getInfo());
			if (cur.getNeighbours() != null) {
				Iterator<GraphNode<T>> iterator = cur.getNeighbours()
						.iterator();
				while (iterator.hasNext()) {
					GraphNode<T> next = iterator.next();
					if (!visited.contains(next.getInfo())) {
						visited.add(next.getInfo());
						queue.add(next);
					}
				}
			}
		}
	}

	public static GraphNode<Integer> buildGraphForTest() {
		/*
		 * Note : All the edges are Undirected Given Graph: 
		 * 1--2 
		 * | | 
		 * 4--3
		 */
		GraphNode<Integer> node1 = new GraphNode<Integer>(1);
		GraphNode<Integer> node2 = new GraphNode<Integer>(2);
		GraphNode<Integer> node3 = new GraphNode<Integer>(3);
		GraphNode<Integer> node4 = new GraphNode<Integer>(4);
		
		//for 1
		List<GraphNode<Integer>> v = new LinkedList<GraphNode<Integer>>();
		v.add(node2);
		v.add(node4);
		node1.setNeighbours(v);
		
		//for 2
		v = new LinkedList<GraphNode<Integer>>();
		v.add(node1);
		v.add(node3);
		node2.setNeighbours(v);
		
		//for 3
		v = new LinkedList<GraphNode<Integer>>();
		v.add(node2);
		v.add(node4);
		node3.setNeighbours(v);
		
		//for 4
		v = new LinkedList<GraphNode<Integer>>();
		v.add(node3);
		v.add(node1);
		node4.setNeighbours(v);
		return node1;
	}

}

/**
 * 
 * @author shaskant
 *
 * @param <T>
 */
class GraphNode<T> {
	private T info;
	private List<GraphNode<T>> neighbours;

	public GraphNode(T info) {
		this.info = info;
		neighbours = new LinkedList<>();
	}

	public T getInfo() {
		return info;
	}

	public void setInfo(T info) {
		this.info = info;
	}

	public List<GraphNode<T>> getNeighbours() {
		return neighbours;
	}

	public void setNeighbours(List<GraphNode<T>> neighbours) {
		this.neighbours = neighbours;
	}

}
