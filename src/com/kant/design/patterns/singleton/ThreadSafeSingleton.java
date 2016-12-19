/**
 * 
 */
package com.kant.design.patterns.singleton;

/**
 * @author shaskant
 *
 */
public class ThreadSafeSingleton {

	private static ThreadSafeSingleton instance;

	private ThreadSafeSingleton() {
	}

	public static synchronized ThreadSafeSingleton getInstanceWay() {
		if (instance == null) {
			instance = new ThreadSafeSingleton();
		}
		return instance;
	}

	/**
	 * Above implementation works fine and provides thread-safety but it reduces
	 * the performance because of cost associated with the synchronized method,
	 * although we need it only for the first few threads who might create the
	 * separate instances (Read: Java Synchronization). To avoid this extra
	 * overhead every time, double checked locking principle is used. In this
	 * approach, the synchronized block is used inside the if condition with an
	 * additional check to ensure that only one instance of singleton class is
	 * created.
	 * 
	 * Below code snippet provides the double checked locking implementation.
	 * 
	 * @return
	 */
	public static ThreadSafeSingleton getInstanceUsingDoubleLocking() {
		if (instance == null) {
			synchronized (ThreadSafeSingleton.class) {
				if (instance == null) {
					instance = new ThreadSafeSingleton();
				}
			}
		}
		return instance;
	}

}
