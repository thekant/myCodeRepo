/**
 * 
 */
package com.kant.design.patterns.factory;

/**
 * Factory design pattern is used when we have a super class with multiple
 * sub-classes and based on input, we need to return one of the sub-class. This
 * pattern take out the responsibility of instantiation of a class from client
 * program to the factory class.
 * 
 * @author shaskant
 *
 */
public class ComputerFactory {

	/**
	 * We can keep Factory class Singleton or we can keep the method that
	 * returns the subclass as static. Notice that based on the input parameter,
	 * different subclass is created and returned. getComputer is the <b>factory
	 * method</b>.
	 * 
	 * @param type
	 * @param ram
	 * @param hdd
	 * @param cpu
	 * @return
	 */
	public static Computer getComputer(String type, String ram, String hdd,
			String cpu) {
		if ("PC".equalsIgnoreCase(type))
			return new PC(ram, hdd, cpu);
		else if ("Server".equalsIgnoreCase(type))
			return new Server(ram, hdd, cpu);

		return null;
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Computer pc = ComputerFactory.getComputer("pc", "2 GB", "500 GB",
				"2.4 GHz");
		Computer server = ComputerFactory.getComputer("server", "16 GB",
				"1 TB", "2.9 GHz");
		System.out.println("Factory PC Config::" + pc);
		System.out.println("Factory Server Config::" + server);
	}
}