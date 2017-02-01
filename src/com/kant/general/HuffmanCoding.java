/**
 * 
 */
package com.kant.general;

/**
 * http://www.geeksforgeeks.org/greedy-algorithms-set-3-huffman-coding/
 * 
 * Steps to build Huffman Tree Input is array of unique characters along with
 * their frequency of occurrences and output is Huffman Tree.
 * 
 * 1. Create a leaf node for each unique character and build a min heap of all
 * leaf nodes (Min Heap is used as a priority queue. The value of frequency
 * field is used to compare two nodes in min heap. Initially, the least frequent
 * character is at root)
 * 
 * 2. Extract two nodes with the minimum frequency from the min heap.
 * 
 * 3. Create a new internal node with frequency equal to the sum of the two
 * nodes frequencies. Make the first extracted node as its left child and the
 * other extracted node as its right child. Add this node to the min heap.
 * 
 * 4. Repeat steps#2 and #3 until the heap contains only one node. The remaining
 * node is the root node and the tree is complete.
 * 
 * 
 * 
 * Implementation: create heap of data. then a build a tree out of that data.
 * 
 * @author shashi
 * 
 */
public class HuffmanCoding {
	private MinHeapNode treeRoot = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		char arr[] = { 'a', 'b', 'c', 'd', 'e', 'f' };
		int freq[] = { 5, 9, 12, 13, 16, 45 };
		HuffmanCoding huffy = new HuffmanCoding();
		huffy.buildHuffmanTree(arr, freq, freq.length);
	}

	public void buildHuffmanTree(char data[], int freq[], int size) {
		MinHeap heap = createAndBuildMinHeap(data, freq, size);
		while (heap.getSize() != 1) {
			// Step 2: Extract the two minimum freq items from min heap
			MinHeapNode left = heap.extractMin();
			MinHeapNode right = heap.extractMin();

			// Step 3: Create a new internal node with frequency equal to the
			// sum of the two nodes frequencies. Make the two extracted node as
			// left and right children of this new node. Add this node to the
			// min heap
			// '$' is a special value for internal nodes, not used
			MinHeapNode top = new MinHeapNode('$', left.getFrequency()
					+ right.getFrequency());
			top.setLeft(left);
			top.setRight(right);
			heap.insertItem(top);
		}
		treeRoot = heap.extractMin();
		printCodes(treeRoot, new int[100], 0);
	}

	public MinHeap createAndBuildMinHeap(char data[], int freq[], int size) {
		MinHeapNode[] aheapStorage = new MinHeapNode[size];
		for (int i = 0; i < size; ++i) {
			aheapStorage[i] = new MinHeapNode(data[i], freq[i]);
		}

		MinHeap heap = new MinHeap(aheapStorage);
		return heap;
	}

	public void printCodes(MinHeapNode root, int arr[], int top) {
		// Assign 0 to left edge and recur
		if (root.getLeft() != null) {
			arr[top] = 0;
			printCodes(root.getLeft(), arr, top + 1);
		}

		// Assign 1 to right edge and recur
		if (root.getRight() != null) {
			arr[top] = 1;
			printCodes(root.getRight(), arr, top + 1);
		}

		// If this is a leaf node, then it contains one of the input
		// characters, print the character and its code from arr[]
		if (root.getLeft() == null && root.getRight() == null) {
			System.out.printf("%c: ", root.getData());
			printArr(arr, top);
		}
	}

	private void printArr(int[] arr, int n) {
		for (int i = 0; i < n; ++i)
			System.out.printf("%d", arr[i]);
		System.out.printf("\n");
	}
}

class MinHeapNode {
	private char data;
	private MinHeapNode right = null;
	private MinHeapNode left = null;
	private int frequency;

	public MinHeapNode(char data, int frequency) {
		this.data = data;
		this.frequency = frequency;
	}

	public MinHeapNode getRight() {
		return right;
	}

	public void setRight(MinHeapNode right) {
		this.right = right;
	}

	public MinHeapNode getLeft() {
		return left;
	}

	public void setLeft(MinHeapNode left) {
		this.left = left;
	}

	public int getFrequency() {
		return frequency;
	}

	public char getData() {
		return data;
	}
}

class MinHeap {
	private MinHeapNode[] heapStorage;
	private int size;
	private int capacity;
	private static final int FRONT = 1;

	public MinHeap(MinHeapNode[] aheapStorage) {
		this.heapStorage = new MinHeapNode[aheapStorage.length + 1];
		heapStorage[FRONT] = null;
		this.size = aheapStorage.length;
		capacity = size;
		System.arraycopy(aheapStorage, 0, heapStorage, 1, size);
		// for (int i = 0; i < aheapStorage.length; i++) {
		// heapStorage[i + 1] = aheapStorage[i];
		// }

		buildHeap(size);
	}

	private void buildHeap(int asize) {
		for (int i = getParent(asize); i >= FRONT; i--) {
			heapify(i);
		}
	}

	public MinHeapNode extractMin() {
		MinHeapNode result = heapStorage[FRONT];

		heapStorage[FRONT] = heapStorage[size];
		heapStorage[size] = null;
		size--;
		heapify(FRONT);
		return result;
	}

	public void insertItem(MinHeapNode item) {
		if (size < capacity) {
			heapStorage[++size] = item;
			int current = size;
			while (isValid(current)
					&& isValid(getParent(current))
					&& heapStorage[getParent(current)].getFrequency() > heapStorage[current]
							.getFrequency()) {
				swapp(getParent(current), current);
				current = current / 2;
			}
		} else {
			System.out.println("cannot insert : no space left");
		}
	}

	private void heapify(int index) {
		int leftChildIdx = getLeftChild(index);
		int rightChildIdx = getRightChild(index);
		int smallestIdx = leftChildIdx;

		if (isValid(rightChildIdx)) {
			if (heapStorage[rightChildIdx].getFrequency() < heapStorage[leftChildIdx]
					.getFrequency()) {
				smallestIdx = rightChildIdx;
			}
		}

		if (isValid(smallestIdx)
				&& heapStorage[smallestIdx].getFrequency() < heapStorage[index]
						.getFrequency()) {
			swapp(smallestIdx, index);
			heapify(smallestIdx);
		}
	}

	private void swapp(int smallestIdx, int index) {
		MinHeapNode temmp = heapStorage[smallestIdx];
		heapStorage[smallestIdx] = heapStorage[index];
		heapStorage[index] = temmp;
	}

	private boolean isValid(int index) {
		return index >= FRONT && index <= size;
	}

	private int getParent(int index) {
		return index / 2;
	}

	private int getLeftChild(int index) {
		return 2 * index;
	}

	private int getRightChild(int index) {
		return 2 * index + 1;
	}

	public int getSize() {
		return size;
	}

}