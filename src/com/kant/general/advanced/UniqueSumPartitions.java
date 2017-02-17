package com.kant.general.advanced;


/**
 * http://www.geeksforgeeks.org/generate-unique-partitions-of-an-integer/
 * 
 * @author shaskant
 *
 */
public class UniqueSumPartitions {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int n = 2;
		int[] data = new int[4];
		// partitn(n, n, 0, data);

		// System.out.println(tryout(3));
	}

	// public static StringBuffer tryout(int n) {
	// StringBuffer buffer = new StringBuffer("");
	// for (int i = n; i > 0; i--) {
	// buffer.append(i);
	// if (n - i > 0) {
	// buffer.append(" " + tryout(n - i));
	// }
	// buffer.append(",");
	// }
	// return buffer;
	// }

	static void partitn(int n, int k, int index, int[] data) {
		if (n == 0) {
			for (int i = 0; i < index; i++)
				System.out.print(data[i] + " ");
			System.out.println();
			return;
		}

		// i loops from k to 1
		for (int i = k; i > 0; i--) {
			if (i > n)
				continue;
			data[index] = i;
			partitn(n - i, i, index + 1, data);
		}

	}

}
