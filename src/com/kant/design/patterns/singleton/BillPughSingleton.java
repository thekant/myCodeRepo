/**
 * 
 */
package com.kant.design.patterns.singleton;

/**
 * Notice the private inner static class that contains the instance of the
 * singleton class. When the singleton class is loaded, SingletonHelper class is
 * not loaded into memory and only when someone calls the getInstance method,
 * this class gets loaded and creates the Singleton class instance.
 * 
 * This is the most widely used approach for Singleton class as it doesn’t
 * require synchronization. I am using this approach in many of my projects and
 * it’s easy to understand and implement also.
 * 
 * @author shaskant
 *
 */
public class BillPughSingleton {

	private BillPughSingleton() {
	}

	/**
	 * private inner class http://www.journaldev.com/996/java-inner-class
	 * 
	 * @author shaskant
	 *
	 */
	private static class SingletonHelper {
		private static final BillPughSingleton INSTANCE = new BillPughSingleton();
	}

	public static BillPughSingleton getInstance() {
		return SingletonHelper.INSTANCE;
	}
}