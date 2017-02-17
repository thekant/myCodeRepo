package com.kant.general.tofactorIn;

/**
 * 2d matrix sorted left to right and top to bottom.
 * 
 * Tournament sort
 * 
 * @author shashi
 * 
 */
public class FindingKSmallestElementsFrom2dMatrix {
	public final static int M = 3, N = 4;

	public static void main(String[] args) {
		int[][] dataArray = new int[][] { { 1, 4, 6, 7 }, { 5, 8, 10, 11 },
				{ 7, 9, 41, 51 } };
		printKSmalletsElements(dataArray);

	}

	/**
	 * keep count number of elements to restrict value of k.
	 * 
	 * @param dataArray
	 */
	private static void printKSmalletsElements(int[][] dataArray) {
		HeapNew heap = new HeapNew(dataArray[0]);
		HeapNode nextMin;

		for (int k = 1; k <= 8; k++) {
			nextMin = heap.getNextMin();
			if (k > 1)
				System.out.print(";");
			System.out.print(nextMin);

			// insert element from the next row for same column.
			if (nextMin.getRow() + 1 < M) {
				heap.setNewData(nextMin.getRow() + 1, nextMin.getCol(),
						dataArray[nextMin.getRow() + 1][nextMin.getCol()]);
			}
			//no element left in the column
			else if (nextMin.getRow() == M - 1) {
				heap.setNewData(nextMin.getRow(), nextMin.getCol(),
						Integer.MAX_VALUE);
			}
		}

	}
}

/**
 * 
 * Min-Heap implementation that stores row column values.
 * 
 * @author shashi
 * 
 */
class HeapNew {
	HeapNode[] data;
	int heapSize;

	// public static void main(String[] args) {
	// new HeapNew(new int[] { 1, 3, 5, 7, 12, 3, 9, 0, 10 });
	// }

	/**
	 * Initially all row and column values are 0,0
	 * 
	 * @param dataArray
	 */
	public HeapNew(int[] dataArray) {
		data = new HeapNode[dataArray.length];
		heapSize = data.length;

		int index = 0;
		for (int item : dataArray) {
			data[index] = new HeapNode(item);
			data[index].setCol(index);
			index++;
		}
		buildHeap();
	}

	public HeapNode getNextMin() {
		return data[0];
	}

	public void setNewData(int r, int c, int newValue) {
		data[0].setCol(c);
		data[0].setRow(r);
		data[0].setValue(newValue);
		heapify(0, heapSize);
	}

	/**
	 * 
	 */
	private void buildHeap() {
		for (int count = (heapSize) / 2 - 1; count >= 0; count--) {
			heapify(count, heapSize);
		}

		System.out
				.println("===================The Min Heap ====================");
		for (int i = 0; i < heapSize; i++) {
			if (i >= 1)
				System.out.print(",");
			System.out.print(data[i].getValue());
		}
		System.out
				.println("\n============================================================");

	}

	/**
	 * 
	 * @param startIndex
	 * @param size
	 */
	private void heapify(int startIndex, int size) {
		int nextIndex = startIndex;
		int leftChild = getLeftChild(startIndex, size);
		int rightChild = getRightChild(startIndex, size);

		if (leftChild != -1) {
			if (data[leftChild].getValue() < data[nextIndex].getValue()) {
				nextIndex = leftChild;
			}
		}

		if (rightChild != -1) {
			if (data[rightChild].getValue() < data[nextIndex].getValue()) {
				nextIndex = rightChild;
			}
		}

		HeapNode temp = data[nextIndex];
		data[nextIndex] = data[startIndex];
		data[startIndex] = temp;
		if (startIndex != nextIndex)
			heapify(nextIndex, size);
	}

	/**
	 * it's min heap so running this sort will non-decreasing data.
	 */
	public void sortHeap() {
		for (int count = 1; count < heapSize; count++) {
			HeapNode temp = data[0];
			data[0] = data[heapSize - count];
			data[heapSize - count] = temp;
			heapify(0, heapSize - count);
		}
	}

	/**
	 * 
	 * @param index
	 * @param size
	 * @return
	 */
	private int getRightChild(int index, int size) {
		if ((2 * index + 2) < size)
			return 2 * index + 2;
		else
			return -1;
	}

	/**
	 * 
	 * @param index
	 * @param size
	 * @return
	 */
	private int getLeftChild(int index, int size) {
		if ((2 * index + 1) < size)
			return 2 * index + 1;
		else
			return -1;
	}
}

/**
 * 
 * @author shashi
 * 
 */
class HeapNode {
	private int value;
	private int row;
	private int col;

	public HeapNode(int data) {
		this.value = data;
		row = 0;
		col = 0;
	}

	@Override
	public String toString() {
		return "[" + value + ",(" + row + "," + col + ")]";
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @param row
	 *            the row to set
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * @return the col
	 */
	public int getCol() {
		return col;
	}

	/**
	 * @param col
	 *            the col to set
	 */
	public void setCol(int col) {
		this.col = col;
	}

}