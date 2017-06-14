/**
 * 
 */
package com.kant.sortingnsearching;

/**
 * Working heap implementation. <br/>
 * Any operations performed through api should not distort heap property.
 * 
 * @author shaskant
 *
 */
public class MinHeap {
	private int[] data;
	private int size; // no. of elements in heap at any instance.
	private int maxSize;
	private static final int FRONT = 1;// marks the front index of this heap

	/**
	 * 
	 * @param maxsize
	 */
	public MinHeap(int maxsize) {
		this.maxSize = maxsize;
		this.size = 0;
		data = new int[this.maxSize + 1];
		data[0] = Integer.MIN_VALUE;
	}

	/**
	 * 
	 */
	public MinHeap(int[] aData) {
		size = aData.length;
		maxSize = size;
		data = new int[maxSize + 1];
		System.arraycopy(aData, 0, data, 1, size);
		data[0] = Integer.MAX_VALUE;
		buildHeap(size);
	}

	/**
	 * 
	 * @param aData
	 * @param aMaxSize
	 */
	public MinHeap(int[] aData, int aMaxSize) {
		this(aMaxSize);
		System.arraycopy(aData, 0, data, 1, aData.length);
		size = aData.length;
		buildHeap(size);
	}

	/**
	 * start from last internal node.
	 * 
	 * @param aSize
	 */
	private void buildHeap(int aSize) {
		for (int i = getParent(aSize); i >= FRONT; i--) {
			heapify(i);
		}
	}

	/**
	 * returns sorted data in non-increasing order. After sorting ,heap is
	 * destroyed.
	 * 
	 * @return
	 */
	public int[] sort() {
		int N = size;
		for (int i = N; i >= FRONT; i--) {
			swapp(FRONT, i);
			size--;
			heapify(FRONT);
		}
		return data;
	}

	/**
	 * l leaves , t internal nodes , n total number of nodes<br/>
	 * l+t=n; l=t+1; 2l=n+1; t=(n-1)/2;
	 */
	private void heapify(int pos) {
		int leftC = getLeftChildPos(pos);
		int rightC = getRightChildPos(pos);
		int smallerElemPos = leftC;
		if (isValid(rightC)) {
			if (data[leftC] < data[rightC])
				smallerElemPos = leftC;
			else
				smallerElemPos = rightC;
		}

		if (isValid(smallerElemPos) && data[pos] > data[smallerElemPos]) {
			swapp(pos, smallerElemPos);
			heapify(smallerElemPos);
		}
	}

	private int getRightChildPos(int pos) {
		return 2 * pos + 1;
	}

	private int getLeftChildPos(int pos) {
		return 2 * pos;
	}

	private int getParent(int pos) {
		return pos / 2;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * @param pos
	 * @param greaterElemPos
	 */
	private void swapp(int pos, int greaterElemPos) {
		int temp = data[pos];
		data[pos] = data[greaterElemPos];
		data[greaterElemPos] = temp;
	}

	/**
	 * 
	 * @return
	 */
	public int getMin() {
		return data[FRONT];
	}

	public int extractMin() {
		int result = getMin();
		int replacer = data[size];
		data[size] = -1;
		size--;
		replaceMinElem(replacer);
		return result;
	}

	/**
	 * 
	 * @param item
	 */
	public void replaceMinElem(int item) {
		data[FRONT] = item;
		heapify(FRONT);
	}

	/**
	 * 
	 * @param item
	 */
	public void insert(int item) {
		if (size + 1 > maxSize) {
			System.out.println("size limit exceeded");
			return;
		}
		data[++size] = item;
		int current = size;
		while (isValid(current) && isValid(getParent(current))
				&& data[getParent(current)] < data[current]) {
			swapp(getParent(current), current);
			current = current / 2;
		}
	}

	/**
	 * 
	 * @param pos
	 * @return
	 */
	private boolean isValid(int pos) {
		return pos >= FRONT && pos <= size;
	}

	public int getSize() {
		return size + 1;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] testData = new int[] { 12, 1, 34, -1, 5, 48, 9, 96 };
		int flow = 1;
		switch (flow) {
		case 1:
			MinHeap heap = new MinHeap(testData);
			System.out.println(heap.getMin());
			heap.replaceMinElem(2);
			System.out.println(heap.getMin());
			MyUtil.printArrayInt(heap.sort());
			break;
		case 2:
			MinHeap heap2 = new MinHeap(testData, 20);
			heap2.replaceMinElem(2);// new max 48 as 96 is replaced by 2
			heap2.insert(50);
			System.out.println(heap2.getMin());// 50
			MyUtil.printArrayInt(heap2.sort());
			break;
		}
	}

}
