/**
 * 
 */
package com.kant.general.advanced;

import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeMap;

/**
 * @author shaskant
 *
 */
public class LexicoGraphicPermutation {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		char[] input = "DCBA".toCharArray();
		// method 1
		generateLexicoGraphicPermutations(input);

		// method 2
		input = new char[]{'D','C','B','A'};
		boolean canDoMore = true;
		Arrays.sort(input);
		while (canDoMore) {
			System.out.println(input);
			canDoMore = generateNextHigerPermutation(input);
		}

		// method 2 can be used to generate next higher number with same digits
		input = "123459876".toCharArray();
		generateNextHigerPermutation(input);
		System.out.println(input);
	}

	/**
	 * @param input
	 */
	private static boolean generateNextHigerPermutation(char[] input) {
		int targetIndx = -1;
		/**
		 * I) Start from the right most digit and find the first digit that is
		 * smaller than the digit next to it
		 */
		for (int i = input.length - 1; i > 0; i--) {
			if (input[i - 1] < input[i]) {
				targetIndx = i - 1;
				break;
			}
		}

		/**
		 * If no such digit is found, then all digits are in descending order
		 * means there cannot be a greater number with same set of digits
		 */
		if (targetIndx == -1) {
			System.out.println("next higher perm not possible");
			return false;
		}

		/**
		 * Find the smallest digit on right side of (targetIndx)'th digit that
		 * is greater than input[targetIndx]'th digit.
		 */
		int nxtGreatrIndx = targetIndx + 1;
		for (int i = targetIndx + 1; i < input.length; i++) {
			if (input[i] > input[targetIndx]) {
				// get smallest all greats
				if (input[nxtGreatrIndx] > input[i]) {
					nxtGreatrIndx = i;
				}
			}
		}

		/**
		 * III) Swap the above found smallest digit with input[target]
		 */
		swapp(input, targetIndx, nxtGreatrIndx);
		/**
		 * IV) Sort the digits after (targetIndx) in ascending order
		 */
		Arrays.sort(input, targetIndx + 1, input.length);
		return true;
	}

	/**
	 * utility to swapp elements on a Array
	 */
	private static void swapp(char[] input, int indx1, int indx2) {
		char temp = input[indx1];
		input[indx1] = input[indx2];
		input[indx2] = temp;
	}

	/**
	 * @param charCountMap
	 */
	private static void generateLexicoGraphicPermutations(char[] input) {
		TreeMap<Character, Integer> charCountMap = buildCharCountMap(input);
		int index;
		int countArr[] = new int[charCountMap.size()];
		char inputArr[] = new char[charCountMap.size()];
		Iterator<Character> iterator = charCountMap.keySet().iterator();
		index = 0;
		while (iterator.hasNext()) {
			Character next = iterator.next();
			countArr[index] = charCountMap.get(next);
			inputArr[index] = next.charValue();
			index++;
		}

		permute(inputArr, countArr, 0, new StringBuffer());
	}

	/**
	 * @param input
	 * @return
	 */
	private static TreeMap<Character, Integer> buildCharCountMap(char[] input) {
		TreeMap<Character, Integer> charCountMap = new TreeMap<>();
		int index = 0;
		while (index < input.length) {
			if (!charCountMap.containsKey(input[index])) {
				charCountMap.put(input[index], 1);
			}
			charCountMap.put(input[index], charCountMap.get(input[index]) + 1);
			index++;
		}
		return charCountMap;
	}

	private static void permute(char[] inputArr, int[] countArr, int len,
			StringBuffer output) {
		if (len == inputArr.length) {
			System.out.println(output);
			return;
		}

		for (int index = 0; index < inputArr.length; index++) {
			if (countArr[index] == 0)
				continue;
			countArr[index]--;
			output.append(inputArr[index]);

			permute(inputArr, countArr, len + 1, output);

			countArr[index]++;
			output.deleteCharAt(output.length() - 1);
		}

	}

}
