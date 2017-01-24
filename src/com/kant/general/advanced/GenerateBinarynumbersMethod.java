package com.kant.general.advanced;

import java.util.ArrayList;
import java.util.List;

/**
 * Prints binary numbers till input.
 * 
 * @author shashi
 * 
 */
public class GenerateBinarynumbersMethod {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Program started---");
		new GenerateBinarynumbersMethod().generatePrintBinary(10);
	}

	/**
	 * This function uses queue data structure to print binary numbers.
	 *         1
	 *     0       1 
	 *   0   1   0   1
	 *   
	 *   BFS traversal. 
	 * @param number
	 */
	public void generatePrintBinary(int number) {
		// Create an empty queue of strings
		List<String> queue = new ArrayList<String>();
		// Enqueue the first binary number
		queue.add("1");

		// This loops is like BFS of a tree with 1 as root
		// 0 as left child and 1 as right child and so on ..
		while (number-- > 0) {
			String elem = queue.remove(0);
			System.out.println(elem);
			// Append "0" to s1 and enqueue it
			queue.add(elem + "0");
			// Append "1" to s2 and enqueue it. Note that s2 contains
			// the previous front
			queue.add(elem + "1");
		}
	}
}
