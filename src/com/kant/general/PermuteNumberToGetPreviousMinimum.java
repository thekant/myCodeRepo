/**
 * 
 */
package com.kant.general;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * A Split the sequence of digits in two parts, so that the right part is as
 * long as possible while remaining in increasing order. 54321 => (5432)(1) or
 * 12435 => (124)(35) <br/>
 * <br/>
 * 
 * B. Take the last digit of the first sequence, and swap it with the smallest
 * digit in the second part that is smaller than this digit.
 * (124)(35)=>(123)(45) or (5432)(1)=> (5431)(2) <br/>
 * <br/>
 * 
 * C. Sort the second sequence into decreasing order: (123)(45) => (123)(54)<br/>
 * <br/>
 * 
 * @author shashi
 * 
 */
public class PermuteNumberToGetPreviousMinimum {

	/**
	 * 
	 * @param number
	 * @return
	 */
	public String integerToStringOfDigits(int number) {
		return String.valueOf(number);
	}

	/**
	 * 
	 * Inserts a integer at any index around string.
	 * 
	 * @param number
	 * @param position
	 * @param item
	 * @return
	 */
	public String insertToNumberStringAtPosition(String number, int item,
			int position) {
		return number.substring(0, position) + item
				+ number.substring(position, number.length());
	}

	/**
	 * To generate permutations of a number.
	 * 
	 * @param number
	 * @return
	 */
	public List<String> permuteNumber(String number) {
		List<String> permutations = new ArrayList<String>();
		if (number.length() == 1) {
			permutations.add(number);
			return permutations;
		}
		// else
		int digToInsert = (int) (number.charAt(0) - '0');
		Iterator<String> iterator = permuteNumber(number.substring(1))
				.iterator();
		while (iterator.hasNext()) {
			String subPerm = iterator.next();
			for (int pos = 0; pos <= subPerm.length(); pos++) {
				permutations.add(insertToNumberStringAtPosition(subPerm,
						digToInsert, pos));
			}
		}
		return permutations;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> permuteNumber = new PermuteNumberToGetPreviousMinimum()
				.permuteNumber("12345");
		Iterator<String> iterator = permuteNumber.iterator();
		int[] numbers = new int[permuteNumber.size()];
		int count = 0;
		while (iterator.hasNext()) {
			numbers[count++] = Integer.parseInt(iterator.next());
		}

		System.out.println("printing all possible permutations .. ");
		Arrays.sort(numbers);
		for (int num : numbers) {
			System.out.print(num + " ");
		}

		int searchIndex = Arrays.binarySearch(numbers, 12435);
		System.out.println();
		if (searchIndex <= 0) {
			System.out.println("Number is already the least");
			return;
		}
		System.out.println("solution:" + numbers[searchIndex - 1]);
	}
}
