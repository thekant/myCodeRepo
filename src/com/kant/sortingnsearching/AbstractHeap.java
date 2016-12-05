/**
 * 
 */
package com.kant.sortingnsearching;

/**
 * @author shaskant
 *
 */
public abstract class AbstractHeap {
	public abstract int[] heapify();

	public abstract int[] heapify(int heapSize);

	protected int getLeftChildPos(int position) {
		if (isValid(2 * (position + 1) - 1))
			return 2 * (position + 1) - 1;
		return -1;
	}

	protected int getRightChildPos(int position) {
		if (isValid(2 * (position + 1)))
			return 2 * (position + 1);
		return -1;
	}

	protected abstract boolean isValid(int pos);

}
