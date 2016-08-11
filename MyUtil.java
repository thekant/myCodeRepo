/**
 * 
 */
package com.kant.sortingnsearching;

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
		for (int i = 0; i < data.length; i++) {
			if (i != 0)
				System.out.print(" ");
			System.out.print(data[i]);
		}
		System.out.println();
	}

}
