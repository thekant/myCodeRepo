package com.kant.general.tofactorIn;

/**
 * 
 * @author shashi
 * 
 */
public class OrganiseNegativeNPositiveNumbers {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int[] data = { 1, 2, 3, 0, -1, -2, 4, -4, -5 };
		reArrangeOrderMaintained(data);
		displayData(data);
	}

	/**
	 * order still not maintained.
	 * 
	 * @param data
	 */
	private static void reArrangeOrderMaintained(int[] data) {
		int posIndex = -1;
		int negIndex = -1;
		int count = 0;

		while (count < data.length) {
			if (data[count] >= 0 && count % 2 != 0) {
				negIndex = nextNegIndex(data, negIndex);
				if (negIndex == -1)
					return;
				int temp = data[negIndex];
				data[negIndex] = data[count];
				data[count] = temp;
			} else if (data[count] < 0 && count % 2 == 0) {
				posIndex = nextPosIndex(data, posIndex);
				if (posIndex == -1)
					return;
				int temp = data[posIndex];
				data[posIndex] = data[count];
				data[count] = temp;
			}

			count++;

		}

	}

	/**
	 * @param data
	 * @param negIndex
	 * @return
	 */
	public static int nextNegIndex(int[] data, int negIndex) {
		int curIndex = negIndex + 1;
		while (curIndex < data.length && data[curIndex] >= 0)
			curIndex++;
		if (negIndex == curIndex || curIndex >= data.length)
			return -1;
		return curIndex;
	}

	/**
	 * @param data
	 * @param posIndex
	 * @return
	 */
	public static int nextPosIndex(int[] data, int posIndex) {
		int curIndex = posIndex + 1;
		while (curIndex < data.length && data[curIndex] < 0)
			curIndex++;
		if (curIndex == posIndex || curIndex >= data.length)
			return -1;
		return curIndex;
	}

	/**
	 * @param data
	 */
	public static void displayData(int[] data) {
		for (int x : data)
			System.out.print(" " + x);
	}

	/**
	 * 
	 * @param data
	 */
	private static void reArrangeData(int[] data) {
		int moveRight = 0, moveLeft = data.length - 1;
		while (true) {
			moveRight = nextPosIndex(data, moveRight);
			while (moveLeft >= 0 && data[moveLeft] > 0)
				moveLeft--;
			if (moveLeft <= moveRight)
				break;
			else if (data[moveRight] > data[moveLeft]) {
				int temp = data[moveRight];
				data[moveRight] = data[moveLeft];
				data[moveLeft] = temp;
			}
		}

		// ----ve 0 +++++ve
		int i = 0;
		while (i < data.length && data[i] < 0) {
			int temp = data[moveRight];
			data[moveRight] = data[i];
			data[i] = temp;
			i = i + 2;
			moveRight++;
		}
	}
}
