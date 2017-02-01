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
public class MaxHeap {
	private int[] data;
	private int size;
	private int maxSize;
	private static final int FRONT = 1;// marks the front index of this heap

	/**
	 * 
	 * @param maxsize
	 */
	public MaxHeap(int maxsize) {
		this.maxSize = maxsize;
		this.size = 0;
		data = new int[this.maxSize + 1];
		data[0] = Integer.MAX_VALUE;
	}

	/**
	 * 
	 */
	public MaxHeap(int[] aData) {
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
	public MaxHeap(int[] aData, int aMaxSize) {
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
		int greaterElemPos = leftC;
		if (isValid(rightC)) {
			if (data[leftC] > data[rightC])
				greaterElemPos = leftC;
			else
				greaterElemPos = rightC;
		}

		if (isValid(greaterElemPos) && data[pos] < data[greaterElemPos]) {
			swapp(pos, greaterElemPos);
			heapify(greaterElemPos);
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
	public int getMax() {
		return data[FRONT];
	}

	/**
	 * 
	 * @param item
	 */
	public void replaceMaxElem(int item) {
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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] testData = new int[] { 12, 1, 34, -1, 5, 48, 9, 96 };
		int flow = 2;
		switch (flow) {
		case 1:
			MaxHeap heap = new MaxHeap(testData);
			System.out.println(heap.getMax());// 96
			heap.replaceMaxElem(2);
			System.out.println(heap.getMax());// 48
			MyUtil.printArrayInt(heap.sort());
			break;
		case 2:
			MaxHeap heap2 = new MaxHeap(testData, 20);
			heap2.replaceMaxElem(2);// new max 48 as 96 is replaced by 2
			heap2.insert(50);
			System.out.println(heap2.getMax());// 50
			MyUtil.printArrayInt(heap2.sort());
			break;
		}
	}

}

/**
 * Method 3 (Using Max-Heap) We can also use Max Heap for finding the k’th
 * smallest element. Following is algorithm. 1) Build a Max-Heap MH of the first
 * k elements (arr[0] to arr[k-1]) of the given array. O(k)
 * 
 * 2) For each element, after the k’th element (arr[k] to arr[n-1]), compare it
 * with root of MH. ……a) If the element is less than the root then make it root
 * and call heapify for MH ……b) Else ignore it. // The step 2 is O((n-k)*logk)
 * 
 * 3) Finally, root of the MH is the kth smallest element.
 * 
 * Time complexity of this solution is O(k + (n-k)*Logk)
 **/

/**
 * Method 2 (Using Min Heap – HeapSelect) We can find k’th smallest element in
 * time complexity better than O(nLogn). A simple optomization is to create a
 * Min Heap of the given n elements and call extractMin() k times. The following
 * is C++ implementation of above method.
 */
