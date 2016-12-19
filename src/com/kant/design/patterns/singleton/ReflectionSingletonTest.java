/**
 * 
 */
package com.kant.design.patterns.singleton;

import java.lang.reflect.Constructor;

/**
 * Reflection can be used to destroy all the above singleton implementation
 * approaches. Let’s see this with an example class
 * 
 * @author shaskant
 *
 */
public class ReflectionSingletonTest {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		EagerInitializedSingleton instanceOne = EagerInitializedSingleton
				.getInstance();
		EagerInitializedSingleton instanceTwo = null;
		try {
			Constructor[] constructors = EagerInitializedSingleton.class
					.getDeclaredConstructors();
			for (Constructor constructor : constructors) {
				// Below code will destroy the singleton pattern
				constructor.setAccessible(true);
				instanceTwo = (EagerInitializedSingleton) constructor
						.newInstance();
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(instanceOne.hashCode());
		System.out.println(instanceTwo.hashCode());
	}

}