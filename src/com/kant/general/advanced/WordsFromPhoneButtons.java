/**
 * 
 */
package com.kant.general.advanced;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author shashi
 * 
 */
public class WordsFromPhoneButtons {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> findAllValidDictionaryWords = getDictionaryWords(0,
				new int[] { 2, 3, 4 });

		Iterator<String> iterator = findAllValidDictionaryWords.iterator();

		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		System.out.println(findAllValidDictionaryWords.size());
	}

	private static Map<Integer, String> numpad;
	private static Set<String> words;
	static {
		numpad = new HashMap<Integer, String>();
		numpad.put(1, " ");
		numpad.put(2, "abc");
		numpad.put(3, "def");
		numpad.put(4, "ghi");
		numpad.put(5, "jkl");
		numpad.put(6, "mno");
		numpad.put(7, "pqrs");
		numpad.put(8, "tuv");
		numpad.put(9, "wxyz");

		words = new HashSet<>();
		words.add("ada");
		words.add("mep");
		words.add("lull");
		words.add("test");
	}

	/**
	 * 
	 * Backtracking solution.
	 * 
	 * @param pos
	 * @param input
	 * @return
	 */
	public static List<String> getDictionaryWords(int pos, int[] input) {
		List<String> result = new ArrayList<>();
		if (pos < input.length) {
			List<String> suffixes = getDictionaryWords(pos + 1, input);
			for (int index = 0; index < numpad.get(input[pos]).length(); index++) {
				char prefix = numpad.get(input[pos]).charAt(index);
				for (String item : suffixes) {
					// un-comment below stuff to put a check for valid
					// dictionary .. better add a suffix tree impl.
					// words ....requires a suffix array.
					// if (words.contains(pref + items)) {
					// result.add(pref + items);
					// }
					result.add(prefix + item);
				}
			}
		} else {
			result.add("");
		}
		return result;
	}

	/**
	 * Iterative solution
	 * 
	 * @param digits
	 * @return
	 */
	public static ArrayList<String> letterCombinations(int[] digits) {
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<String> preResult = null;
		result.add("");
		for (int i = 0; i < digits.length; i++) {
			preResult = new ArrayList<String>();
			// for each string in previous iteration
			for (String str : result) {
				String letters = numpad.get(digits[i]);
				// add each char for numpad digit[i]
				for (int j = 0; j < letters.length(); j++)
					preResult.add(str + letters.charAt(j));

				result = preResult;
			}
		}
		return result;
	}

}
