package com.kant.datastructure.strings;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class can handle any string to generate all permutations
 * 
 * @author shashi
 * 
 */

public class StringPermutations {

	/**
	 * 
	 * @param s
	 * @return
	 */
	public static ArrayList<String> getPerms(String s) {
		ArrayList<String> permutations = new ArrayList<String>();
		if (s == null) {
			System.err.println("Invalid input");
			return null;
		} else if (s.length() == 1) {
			permutations.add(s);
			return permutations;
		}

		// capture first character
		char first = s.charAt(0);
		String remainder = s.substring(1);

		ArrayList<String> words = getPerms(remainder);
		for (String word : words) {
			// c => 0|a 1|b 2|c 3|
			for (int j = 0; j <= word.length(); j++) {
				permutations.add(insertCharAt(word, first, j));
			}
		}
		return permutations;
	}

	/**
	 * 
	 * @param word
	 * @param c
	 * @param i
	 * @return
	 */
	public static String insertCharAt(String word, char c, int i) {
		return word.substring(0, i) + c + word.substring(i);
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Iterator<String> iterator = getPerms("GOD").iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}

}
