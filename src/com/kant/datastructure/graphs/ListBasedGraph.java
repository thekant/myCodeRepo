/**
 * 
 */
package com.kant.datastructure.graphs;

import java.util.HashMap;
import java.util.Map;

/**
 * a variable length graph that can grow
 * 
 * @author shaskant
 *
 */
public class ListBasedGraph<T extends Comparable<T>, P> {
	private Map<T, GNode<T, P>> store;

	public ListBasedGraph(Map<T, GNode<T, P>> aStore) {
		super();
		this.store = aStore;
		if (aStore == null)
			store = new HashMap<T, GNode<T, P>>();
	}

	public ANode<T, P> getEdgeWt(T src, T des) {
		return null;
	}

	public void addEdge(T src, T des, P Wt) {

	}

	public void removeEdge(T src, T des) {

	}

	public ANode<T, P>[] getAdjacent(T src) {
		return null;

	}

}

class ANode<T, P> {
	private T info;
	private P wt; // wrt to src Gnode
	private ANode<T, P> aNext;

}

class GNode<T extends Comparable<T>, P> {
	private T info;
	private GNode<T, P> gNext = null;
	private ANode<T, P> aNext = null;

	public GNode() {
	}

	public GNode(T info, GNode<T, P> next) {
		this(info, next, null);
	}

	public GNode(T info, GNode<T, P> gNext, ANode<T, P> aNext) {
		super();
		this.info = info;
		this.gNext = gNext;
		this.aNext = aNext;
	}

	/**
	 * @return the aNext
	 */
	public ANode<T, P> getaNext() {
		return aNext;
	}

	/**
	 * @param aNext
	 *            the aNext to set
	 */
	public void setaNext(ANode<T, P> aNext) {
		this.aNext = aNext;
	}

	/**
	 * @return the info
	 */
	public T getInfo() {
		return info;
	}

	/**
	 * @param info
	 *            the info to set
	 */
	public void setInfo(T info) {
		this.info = info;
	}

	public GNode<T, P> getgNext() {
		return gNext;
	}

	public void setgNext(GNode<T, P> gNext) {
		this.gNext = gNext;
	}
}