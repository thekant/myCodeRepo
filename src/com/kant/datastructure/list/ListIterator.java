/**
 * 
 */
package com.kant.datastructure.list;

/**
 * Iterator pattern support
 * 
 * @author shaskant
 *
 * @param <T>
 */
public interface ListIterator<T extends ListNode<?>> {
	boolean hasMore();

	T next();
}