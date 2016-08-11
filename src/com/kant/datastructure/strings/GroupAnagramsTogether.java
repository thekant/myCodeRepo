/**
 * 
 */
package com.kant.datastructure.strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * https://leetcode.com/problems/anagrams/
 * 
 * @author shaskant
 *
 */
public class GroupAnagramsTogether {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<List<String>> anagramsTogether = getAnagramsTogether(new String[] {
				"abc", "bca", "efg", "fgh", "gef", "lw" });
		Iterator<List<String>> iterator1 = anagramsTogether.iterator();

		while (iterator1.hasNext()) {
			List<String> next = iterator1.next();
			if (next != null) {
				Iterator<String> iterator2 = next.iterator();
				while (iterator2.hasNext()) {
					System.out.print(iterator2.next() + " ");
				}
			}
			System.out.println();
		}

	}

	/**
	 * 
	 * @param values
	 * @return
	 */
	public static List<List<String>> getAnagramsTogether(String[] values) {
		Map<String, List<String>> keyInAng = new HashMap<String, List<String>>();

		for (int i = 0; i < values.length; i++) {
			char[] charArray = values[i].toCharArray();
			Arrays.sort(charArray);
			String key = new String(charArray);
			if (!keyInAng.containsKey(key)) {
				keyInAng.put(key, new ArrayList<String>());
			}
			keyInAng.get(key).add(values[i]);
		}

		List<List<String>> result = new ArrayList<>();
		Iterator<Entry<String, List<String>>> iterator = keyInAng.entrySet()
				.iterator();
		while (iterator.hasNext()) {
			result.add(iterator.next().getValue());
		}
		return result;
	}

}
