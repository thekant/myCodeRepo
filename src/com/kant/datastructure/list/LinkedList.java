/**
 * 
 */
package com.kant.datastructure.list;

/**
 * @author shaskant
 *
 */
public class LinkedList {
	private ListNode<String> head = null;
	private int length = 0;

	public LinkedList() {
	}

	/**
	 * initialize from array
	 * 
	 * @param datas
	 */
	public LinkedList(String[] datas) {
		for (int i = 0; i < datas.length; i++) {
			pushEnd(datas[i]);
		}
	}

	/**
	 * TESTED
	 * 
	 * @param val
	 */
	public void pushFront(String val) {
		if (head == null) {
			head = new ListNode<String>(val);
		} else
			head = new ListNode<String>(val, head);
		length++;
	}

	/**
	 * TESTED
	 * 
	 * @param val
	 */
	public void pushEnd(String val) {
		if (head == null) {
			head = new ListNode<String>(val);
		} else {
			ListNode<String> looper = head;
			while (looper.getNext() != null)
				looper = looper.getNext();
			looper.setNext(new ListNode<String>(val));
		}
		length++;
	}

	/**
	 * TESTED
	 * 
	 * @param key
	 * @return
	 */
	public boolean deleteItem(String key) {
		if (head == null)
			return false;
		if (key.equals(head.getData())) {
			head = head.getNext();
			length--;
			return true;
		}
		ListNode<String> looper = head;
		while (looper.getNext() != null) {
			if (key.equals(looper.getNext().getData())) {
				looper.setNext(looper.getNext().getNext());
				length--;
				return true;
			}
			looper = looper.getNext();
		}
		return false;
	}

	/**
	 * TESTED
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean swapNodes(String x, String y) {
		// find x and y in doing so also find following
		// 1. x.pre x.next 2. y.pre y.next
		// if x , y are adjacent -> simple
		// x is head or y is tail
		System.out.println("\n[LOG] swapping " + x + " " + y);
		if (head != null || length > 1) {
			ListNode<String> preX = null;
			ListNode<String> preY = null;
			ListNode<String> curX = null;
			ListNode<String> curY = null;
			ListNode<String> cur = head;
			ListNode<String> pre = null;

			while (cur != null) {
				if (x.equals(cur.getData())) {
					preX = pre;
					curX = cur;
					break;
				}
				pre = cur;
				cur = cur.getNext();
			}
			cur = head;
			pre = null;
			while (cur != null) {
				if (y.equals(cur.getData())) {
					preY = pre;
					curY = cur;
					break;
				}
				pre = cur;
				cur = cur.getNext();
			}

			if (curY != null && curX != null && curY != curX) {
				if (preX != null)
					preX.setNext(curY);
				else
					head = curY;
				if (preY != null)
					preY.setNext(curX);
				else
					head = curX;

				ListNode<String> nextTemp = curY.getNext();
				curY.setNext(curX.getNext());
				curX.setNext(nextTemp);

				nextTemp = null;
				return true;
			}
		}
		return false;
	}

	/**
	 * TESTED
	 */
	public boolean reverseList() {
		System.out.println("[LOG] reversing the list.");
		if (head == null)
			return false;
		if (head.getNext() == null)
			return true;
		/**
		 * head->1->2->3->4L head->2->3->4L->1 head->3->4L->2->1 head4L-3->2->1
		 */
		ListNode<String> cur = head;
		ListNode<String> tail = null;
		while (cur.getNext() != null) {
			cur = cur.getNext();
		}
		tail = cur;
		cur = null;
		while (head != tail) {
			ListNode<String> temp = head;
			head = head.getNext();

			// insert after tail.
			temp.setNext(tail.getNext());
			tail.setNext(temp);
		}
		return true;
	}

	/**
	 * TESTED
	 */
	public void printList() {
		if (head != null) {
			ListNode<String> looper = head;

			while (looper != null) {
				System.out.print(looper.getData() + " ");
				looper = looper.getNext();
			}
		}
	}

	/**
	 * TESTED
	 * 
	 * @return
	 */
	public ListIterator<ListNode<String>> iterator() {
		return new ListIterator<ListNode<String>>() {
			private ListNode<String> locHead = head;
			int index = 0;

			@Override
			public boolean hasMore() {
				if (index < length)
					return true;
				else
					return false;
			}

			@Override
			public ListNode<String> next() {
				if (hasMore()) {
					ListNode<String> temp = locHead;
					locHead = locHead.getNext();
					index++;
					return temp;
				}
				return null;
			}
		};
	}

	/**
	 * TESTED
	 * 
	 * @param anotherList
	 */
	public void insertListAfter(LinkedList anotherList) {
		ListIterator<ListNode<String>> iterator = anotherList.iterator();
		while (iterator.hasMore()) {
			this.pushEnd(iterator.next().getData());
		}
	}

	/**
	 * TESTED
	 */
	public void sort() {
		System.out.println("[LOG] merging");
		if (head == null || head.getNext() == null)
			return;
		head = mergeSort(head);
	}

	/**
	 * TESTED
	 * 
	 * @param firstHalfStart
	 * @return
	 */
	private ListNode<String> mergeSort(ListNode<String> firstHalfStart) {
		if (firstHalfStart == null)
			return null;
		else if (firstHalfStart.getNext() == null)
			return firstHalfStart;

		ListNode<String> secondHalfStart = splitInHalf(firstHalfStart);

		firstHalfStart = mergeSort(firstHalfStart);
		secondHalfStart = mergeSort(secondHalfStart);

		return sortedMerging(firstHalfStart, secondHalfStart);
	}

	/**
	 * TESTED
	 * 
	 * @param firstHalfStart
	 * @param secondHalfStart
	 * @return
	 */
	private ListNode<String> sortedMerging(ListNode<String> firstHalfStart,
			ListNode<String> secondHalfStart) {
		if (firstHalfStart == null && secondHalfStart == null)
			return null;
		if (firstHalfStart == null)
			return secondHalfStart;
		else if (secondHalfStart == null)
			return firstHalfStart;

		ListNode<String> result = null;
		if (firstHalfStart.getData().compareTo(secondHalfStart.getData()) < 1) {
			result = firstHalfStart;
			result.setNext(sortedMerging(firstHalfStart.getNext(),
					secondHalfStart));
		} else {
			result = secondHalfStart;
			result.setNext(sortedMerging(firstHalfStart,
					secondHalfStart.getNext()));
		}
		return result;
	}

	/**
	 * TESTED NOTE: can be only one element
	 * 
	 * @param firstHalfStart
	 * @return
	 */
	public ListNode<String> splitInHalf(ListNode<String> firstHalfStart) {
		if (firstHalfStart == null || firstHalfStart.getNext() == null)
			return null;

		ListNode<String> slow = firstHalfStart;
		ListNode<String> fast = firstHalfStart.getNext();

		while (fast.getNext() != null) {
			fast = fast.getNext();
			if (fast.getNext() != null) {
				slow = slow.getNext();
				fast = fast.getNext();
			}
		}

		ListNode<String> secondHalfStart = slow.getNext();
		slow.setNext(null); // partition here fh---->s sh------f
		return secondHalfStart;
	}

	/**
	 * TESTED
	 * http://www.geeksforgeeks.org/detect-and-remove-loop-in-a-linked-list/
	 * [method_2] This method is also dependent on Floyd’s Cycle detection
	 * algorithm. <br/>
	 * 1) Detect Loop using Floyd’s Cycle detection algo and get the pointer to
	 * a loop node. <br/>
	 * 2) Count the number of nodes in loop. Let the count be k.<br/>
	 * 3) Fix one pointer to the head and another to kth node from head.<br/>
	 * 4) Move both pointers at the same pace, they will meet at loop starting
	 * node.<br/>
	 * 5) Get pointer to the last node of loop and make next of it as NULL.<br/>
	 * 
	 * TODO
	 * http://www.geeksforgeeks.org/detect-and-remove-loop-in-a-linked-list/
	 * MEthod 3 without counting
	 */
	public void removeLoop() {
		// step 1
		ListNode<String> loopNode = detectLoop();
		ListNode<String> cur = loopNode.getNext();
		int loopLen = 1;
		// step 2
		while (cur != loopNode) {
			cur = cur.getNext();
			loopLen++;
		}
		// step 3
		cur = head;
		while (loopLen != 0) {
			cur = cur.getNext();
			loopLen--;
		}

		// step 4 and 5
		loopNode = head;
		boolean flag = false;
		while (true) {
			if (flag || loopNode == cur) {
				if (!flag) {
					flag = true;
				} else {
					if (cur.getNext() == loopNode)
						break;
					else
						cur = cur.getNext();
				}
			} else {
				cur = cur.getNext();
				loopNode = loopNode.getNext();
			}
		}

		cur.setNext(null);
	}

	/**
	 * TESTED
	 * 
	 * @return
	 */
	private ListNode<String> detectLoop() {
		ListNode<String> slow = head;
		ListNode<String> fast = head.getNext();

		while (slow != fast && fast.getNext() != null) {
			fast = fast.getNext();
			if (fast.getNext() != null) {
				slow = slow.getNext();
				fast = fast.getNext();
			}
		}

		if (slow != fast) {
			return null;
		}

		return slow;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LinkedList list = new LinkedList();
		list.pushFront("1");
		list.pushFront("2");
		list.pushFront("3");
		list.pushFront("4");

		LinkedList anotherList = new LinkedList();
		anotherList.pushEnd("5");
		anotherList.pushEnd("6");
		anotherList.pushEnd("7");
		anotherList.pushEnd("8");

		list.insertListAfter(anotherList);

		list.printList();
		list.getItem("8").setNext(list.getItem("5"));
		System.out.println();
		list.removeLoop();
		// list.deleteItem("4");
		//
		// list.printList();
		// System.out.println();
		// list.swapNodes("8", "3");
		// list.sort();
		// System.out.println("\nList after sorting:");
		// list.printList();
		//
		// list.reverseList();

		System.out.println("\nList after reversal:");
		ListIterator<ListNode<String>> iterator = list.iterator();
		while (iterator.hasMore()) {
			System.out.print(iterator.next().getData() + " ");
		}

	}

	/**
	 * TESTED
	 * 
	 * @return
	 */
	public ListNode<String> gethead() {
		return head;
	}

	/**
	 * TESTED
	 * 
	 * @param val
	 * @return
	 */
	public ListNode<String> getItem(String val) {
		ListIterator<ListNode<String>> iterator = this.iterator();
		while (iterator.hasMore()) {
			ListNode<String> next = iterator.next();
			if (next.getData().equalsIgnoreCase(val))
				return next;
		}
		return null;
	}

}

/**
 * Data structure to hold list
 * 
 * @author shaskant
 *
 * @param <T>
 */
class ListNode<T> {
	private T data;
	private ListNode<T> next;

	public ListNode() {
		this(null, null);
	}

	public ListNode(T data) {
		this(data, null);
	}

	public ListNode(T data, ListNode<T> next) {
		this.data = data;
		this.next = next;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public ListNode<T> getNext() {
		return next;
	}

	public void setNext(ListNode<T> next) {
		this.next = next;
	}
}

/**
 * Iterator pattern support
 * 
 * @author shaskant
 *
 * @param <T>
 */
interface ListIterator<T extends ListNode<?>> {
	boolean hasMore();

	T next();
}