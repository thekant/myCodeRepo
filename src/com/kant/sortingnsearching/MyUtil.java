package com.kant.sortingnsearching;

import java.util.Arrays;

/**
 * 
 */

/**
 * @author shaskant
 *
 */
public class MyUtil {

	/**
	 * 
	 * @param data
	 */
	public static <T> void printArrayType(T[] data) {
		for (int i = 0; i < data.length; i++) {
			if (i != 0)
				System.out.print(" ");
			System.out.print(data[i].toString());
		}
		System.out.println();
	}

	/**
	 * 
	 * @param data
	 */
	public static void printArrayInt(int[] data) {
		if (data == null) {
			System.out.println("Null Array");
			return;
		}
		for (int i = 0; i < data.length; i++) {
			if (i != 0)
				System.out.print(" ");
			System.out.print(data[i]);
		}
		System.out.println();
	}

	/**
	 * Uses {@link Arrays} copyOfRange() Copies target from index 'l' to 'h-1'
	 * 
	 * @param from
	 * @param l
	 * @param h
	 * @return
	 */
	public static int[] getAnotherSubCopyOf(int[] from, int l, int h) {
		return Arrays.copyOfRange(from, l, h);
	}

}
