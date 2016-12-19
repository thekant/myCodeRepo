/**
 * 
 */
package com.kant.design.patterns.singleton;

/**
 * @author shaskant
 *
 */
public class LazyInitializedSingleton {

	private static LazyInitializedSingleton instance;

	private LazyInitializedSingleton() {
	}

	/**
	 * NOT THREAD SAFE
	 * 
	 * @return
	 */
	public static LazyInitializedSingleton getInstance() {
		if (instance == null) {
			instance = new LazyInitializedSingleton();
		}
		return instance;
	}
}