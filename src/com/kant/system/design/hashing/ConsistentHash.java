package com.kant.system.design.hashing;

import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * http://www.tom-e-white.com/2007/11/consistent-hashing.html
 * 
 * The circle is represented as a sorted map of integers, which represent the
 * hash values, to caches (of type T here). When a ConsistentHash object is
 * created each node is added to the circle map a number of times (controlled by
 * numberOfReplicas). The location of each replica is chosen by hashing the
 * node's name along with a numerical suffix, and the node is stored at each of
 * these points in the map.
 * 
 * @author shaskant
 *
 * @param <T>
 */
public class ConsistentHash<T> {

	private final HashFunction hashFunction;
	private final int numberOfReplicas;
	private final SortedMap<Integer, T> circle = new TreeMap<Integer, T>();

	public ConsistentHash(HashFunction hashFunction, int numberOfReplicas,
			Collection<T> nodes) {

		this.hashFunction = hashFunction;
		this.numberOfReplicas = numberOfReplicas;

		for (T node : nodes) {
			add(node);
		}
	}

	public void add(T node) {
		for (int i = 0; i < numberOfReplicas; i++) {
			circle.put(hashFunction.hash(node.toString() + i), node);
		}
	}

	/**
	 * remove a cache node and it's replicas
	 * 
	 * @param node
	 */
	public void remove(T node) {
		for (int i = 0; i < numberOfReplicas; i++) {
			circle.remove(hashFunction.hash(node.toString() + i));
		}
	}

	/**
	 * To find a node for an object (the get method), the hash value of the
	 * object is used to look in the map. Most of the time there will not be a
	 * node stored at this hash value (since the hash value space is typically
	 * much larger than the number of nodes, even with replicas), so the next
	 * node is found by looking for the first key in the tail map. If the tail
	 * map is empty then we wrap around the circle by getting the first key in
	 * the circle.
	 * 
	 * @param key
	 * @return cache node where to point to
	 */
	public T get(Object key) {
		if (circle.isEmpty()) {
			return null;
		}
		int hash = hashFunction.hash(key);
		if (!circle.containsKey(hash)) {
			SortedMap<Integer, T> tailMap = circle.tailMap(hash);
			hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
		}
		return circle.get(hash);
	}

}

/**
 * 
 * @author shaskant
 *
 */
interface HashFunction {
	/**
	 * define hash.
	 * 
	 * @param obj
	 * @return
	 */
	public int hash(Object obj);
}