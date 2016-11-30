package com.kant.general;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 
 * Treating state of each element of a set as '0' or '1' so One has a string of
 * bits that is a Integer one can generate all subsets by generating all
 * integers till then.
 * 
 * @author shashi
 * 
 */
public class SubSetofSets {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<Integer> valueset = new ArrayList<Integer>();
		valueset.add(1);
		valueset.add(2);
		valueset.add(3);
		valueset.add(4);
		valueset.add(5);

		Iterator<ArrayList<Integer>> iterator = SubSetofSets.getSubsets(
				valueset).iterator();
		while (iterator.hasNext()) {
			ArrayList<Integer> next = iterator.next();
			counter++;
			printArrayList(next);
		}
		System.out.println(counter + " sets");
	}

	static int counter = 0;

	/**
	 * 
	 * @param next
	 */
	private static void printArrayList(ArrayList<Integer> next) {
		Iterator<Integer> iterator = next.iterator();
		System.out.print("=>( ");
		while (iterator.hasNext()) {
			System.out.print(iterator.next() + " ");
		}
		System.out.println(")");
	}

	/**
	 * 
	 * @param set
	 * @return
	 */
	public static ArrayList<ArrayList<Integer>> getSubsets(
			ArrayList<Integer> set) {
		ArrayList<ArrayList<Integer>> allsubsets = new ArrayList<ArrayList<Integer>>();

		int max = 1 << set.size(); // 2^size.
		for (int i = 0; i < max; i++) { // 0 to max-1
			ArrayList<Integer> subset = new ArrayList<Integer>();
			int k = i;
			int index = 0;
			// loop around the number to get element states and store them in
			// corresponding subset
			while (k > 0) {
				if ((k & 1) > 0) { // check first bit from right
					subset.add(set.get(index));
				}
				k >>= 1;
				index++;
			}
			allsubsets.add(subset);
		}
		return allsubsets;
	}

}
