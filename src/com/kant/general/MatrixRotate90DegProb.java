package com.kant.general;

/**
 * 
 * Matrix or image rotation by 90 deg.
 * 
 * @author shashi
 * 
 */
public class MatrixRotate90DegProb {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int[][] image = { { 1, 2, 3, 4, 5 }, { 6, 7, 8, 9, 10 },
				{ 11, 12, 13, 14, 15 }, { 16, 17, 18, 19, 20 },
				{ 21, 22, 23, 24, 25 } };

		System.out.println("BEFORE:");
		for (int[] x : image) {
			for (int val : x) {
				System.out.printf(" %2d", val);
			}
			System.out.println();
		}

		rotate(image, 5);

		System.out.println("AFTER:");
		for (int[] x : image) {
			for (int val : x) {
				System.out.printf(" %2d", val);
			}
			System.out.println();
		}

	}

	/**
	 * cyclic rotation layer by layer in clockwise manner. <br/>
	 * save top: copy left to top: copy bottom to left:copy right to bottom:
	 * copy saved top to right.
	 * 
	 * @param matrix
	 * @param n
	 */
	public static void rotate(int[][] matrix, int n) {
		for (int layer = 0; layer < n / 2; ++layer) {
			System.out.println("Round " + layer);
			int first = layer;
			int last = n - 1 - layer;
			// clockwise copy
			// extreme points
			// [first,first]<-[first,last]<-[last,last]<-[last,first]
			// first defines row and column where one should start
			// [first,i]<-[last-offset,first]<-[last,last-offset]<-[i,last]
			for (int i = first; i < last; ++i) {
				int offset = i - first;
				// save top_left
				int top = matrix[first][i];
				// bottom_left -> top_left
				matrix[first][i] = matrix[last - offset][first];
				// bottom_right -> bottom_left
				matrix[last - offset][first] = matrix[last][last - offset];
				// top_right -> bottom_right
				matrix[last][last - offset] = matrix[i][last];
				// top -> right
				matrix[i][last] = top; // right <- saved top
			}

		}
	}

	/**
	 * @param x
	 * @param y
	 */
	private static void printFormatted(int x, int y) {
		System.out.println("[" + x + "," + y + "]");
	}

}
