/**
 * 
 */
package com.kant.general.advanced;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * http://www.geeksforgeeks.org/find-the-k-most-frequent-words-from-a-file/
 * 
 * @author shaskant
 *
 */
public class KMostFrequentWordsInAFile {
	private MTrieNode wordStore;
	private MinHeap minHeapImpl;

	public KMostFrequentWordsInAFile() {
		wordStore = new MTrieNode();
	}

	/**
	 * 
	 * @param k
	 * @param from
	 * @return
	 */
	public List<String> getTheListOf(int k, InputStream from) {
		minHeapImpl = new MinHeap(k);
		List<String> result = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new InputStreamReader(from));
		String line;
		try {
			while ((line = br.readLine()) != null) {
				String[] words = line.split(" ");
				for (String word : words) {
					word = word.trim();
					MTrieNode insertedSourceTrie = wordStore.insertWord(word);
					// case 1:
					if (insertedSourceTrie.getIndexMinHeap() != -1) {
						minHeapImpl.updateHeapAtIndex(insertedSourceTrie
								.getIndexMinHeap());
					}
					// case 3
					else if (minHeapImpl.isFull()) {
						if (minHeapImpl.getMin().getfrequency() < insertedSourceTrie
								.getFrequency()) {
							minHeapImpl.replaceMinElem(new MinHeapNode(
									insertedSourceTrie, word));
						}
					} else {// case 2:
						minHeapImpl.insert(new MinHeapNode(insertedSourceTrie,
								word));
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			minHeapImpl = null;
		}

		if (minHeapImpl != null) {
			MinHeapNode[] sortedItems = minHeapImpl.sort();
			for (int index = 1; index < sortedItems.length; index++) {
				MinHeapNode node = sortedItems[index];
				if (node != null) {
					result.add(node.getWord());
					System.out.println(node.getWord() + " : "
							+ node.getfrequency());
				}
			}
		}
		return result;
	}

	/**
	 * 
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		KMostFrequentWordsInAFile prob = new KMostFrequentWordsInAFile();
		prob.getTheListOf(5, new FileInputStream(
				"C:\\Users\\shaskant.ORADEV\\Desktop\\toRead.txt"));
	}

}

/**
 * 
 * @author shaskant
 *
 */
class MTrieNode {
	@SuppressWarnings("unused")
	private boolean EOFWord;
	private int frequency;
	private int indexMinHeap;
	private MTrieNode[] children;

	public MTrieNode() {
		EOFWord = false;
		children = new MTrieNode[255];
		Arrays.fill(children, null);
		indexMinHeap = -1;
		frequency = 0;
	}

	/**
	 * 
	 * @param input
	 * @param meaning
	 */
	public MTrieNode insertWord(String input) {
		if (input.isEmpty()) {
			this.EOFWord = true;
			frequency++;
			return this;
		}

		int first = input.charAt(0);
		if (children[first] == null) {
			children[first] = new MTrieNode();
		}
		return children[first].insertWord(input.substring(1));
	}

	public int getFrequency() {
		return frequency;
	}

	public int getIndexMinHeap() {
		return indexMinHeap;
	}

	public void setIndexMinHeap(int indexMinHeap) {
		this.indexMinHeap = indexMinHeap;
	}
}

/**
 * 
 * @author shaskant
 *
 */
class MinHeapNode {
	private MTrieNode trieSource; // indicates the leaf node of TRIE
	private String word; // the actual word stored

	/**
	 * no heap node without a word i.e. without a trieNode attached
	 * 
	 * @param trieSource
	 * @param frequency
	 * @param word
	 */
	public MinHeapNode(MTrieNode trieSource, String word) {
		super();
		this.trieSource = trieSource;
		this.word = word;
	}

	public String getWord() {
		return word;
	}

	public int getfrequency() {
		return trieSource.getFrequency();
	}

	public void setHeapIndexInTrie(int index) {
		trieSource.setIndexMinHeap(index);
	}
}

/**
 * 
 * @author shaskant
 *
 */
class MinHeap {
	private MinHeapNode[] nodes;
	private int capacity;
	private int size;
	private static final int FRONT = 1;// marks the front index of this heap

	public MinHeap(int maxsize) {
		this.capacity = maxsize;
		this.size = 0;
		nodes = new MinHeapNode[this.capacity + 1];
		Arrays.fill(nodes, null);
		nodes[0] = new MinHeapNode(null, null);
	}

	// private void buildHeap(int aSize) {
	// for (int i = getParent(size); i >= FRONT; i--) {
	// heapify(i);
	// }
	// }

	public void updateHeapAtIndex(int index) {
		if (isValid(index)) {
			heapify(index);
		}
	}

	public boolean isFull() {
		return capacity == size;
	}

	private void heapify(int pos) {
		int leftC = getLeftChildPos(pos);
		int rightC = getRightChildPos(pos);
		int smallerElemPos = leftC;
		if (isValid(rightC)) {
			if (nodes[leftC].getfrequency() < nodes[rightC].getfrequency())
				smallerElemPos = leftC;
			else
				smallerElemPos = rightC;
		}

		if (isValid(smallerElemPos)
				&& nodes[pos].getfrequency() > nodes[smallerElemPos]
						.getfrequency()) {
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

	private int getParent(int size) {
		return size / 2;
	}

	/**
	 * 
	 * @param pos1
	 * @param pos2
	 */
	private void swapp(int pos1, int pos2) {
		MinHeapNode temp = nodes[pos1];
		nodes[pos1] = nodes[pos2];
		nodes[pos2] = temp;
		nodes[pos1].setHeapIndexInTrie(pos1);
		nodes[pos2].setHeapIndexInTrie(pos2);
	}

	/**
	 * 
	 * @return
	 */
	public MinHeapNode getMin() {
		return nodes[FRONT];
	}

	/**
	 * 
	 * @param item
	 */
	public void replaceMinElem(MinHeapNode item) {
		nodes[FRONT].setHeapIndexInTrie(-1);
		nodes[FRONT] = item;
		heapify(FRONT);
	}

	/**
	 * 
	 * @param item
	 */
	public void insert(MinHeapNode item) {
		if (size + 1 > capacity) {
			System.out.println("size limit exceeded");
			return;
		}
		nodes[++size] = item;
		item.setHeapIndexInTrie(size);
		int current = size;
		while (isValid(current)
				&& isValid(getParent(current))
				&& nodes[getParent(current)].getfrequency() > nodes[current]
						.getfrequency()) {
			swapp(getParent(current), current);
			current = current / 2;
		}
	}

	/**
	 * makes sure no NPE
	 * 
	 * @param pos
	 * @return
	 */
	private boolean isValid(int pos) {
		return pos >= FRONT && pos <= size;
	}

	/**
	 * returns sorted data in non-increasing order. After sorting ,heap is
	 * destroyed.
	 * 
	 * @return
	 */
	public MinHeapNode[] sort() {
		int N = size;
		for (int i = N; i >= FRONT; i--) {
			swapp(FRONT, i);
			size--;
			heapify(FRONT);
		}
		return nodes;
	}

}
