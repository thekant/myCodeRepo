/**
 * 
 */
package com.kant.sortingnsearching;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author shaskant
 *
 */
public class MergeSortApplication {

	/**
	 * 
	 * Make sure dA1 points to shorter array
	 * 
	 * @param arr1
	 * @param arr2
	 * @return
	 */
	public static int[] merge(int[] arr1, int[] arr2) {
		if (arr1 == null || arr1.length == 0)
			return arr2;
		else if (arr2 == null || arr2.length == 0)
			return arr1;

		int[] result = new int[arr1.length + arr2.length];
		Arrays.fill(result, -1);

		int i = 0, j = 0, k = 0;
		for (; i < arr1.length && j < arr2.length;) {
			if (arr1[i] < arr2[j]) {
				result[k++] = arr1[i++];
			} else {
				result[k++] = arr2[j++];
			}
		}

		while (i < arr1.length) {
			result[k++] = arr1[i++];
		}
		
		while (j < arr2.length) {
			result[k++] = arr2[j++];
		}
		return result;
	}

	/**
	 * 
	 * void mergeSort(int arr[], int l, int r)
		{
		   if (l < r)
		   {
		      int m = l+(r-l)/2; //Same as (l+r)/2 but avoids overflow for large l & h
		      mergeSort(arr, l, m);
		      mergeSort(arr, m+1, r);
		      merge(arr, l, m, r);
		   }
		}
	 * @param data
	 * @return new sorted output array.
	 */
	public static int[] mergeSort(int[] data) {
		int n = data.length;
		if (n <= 1) //base case
			return data;
		int mid = (int) Math.ceil(n / 2);
		int[] firstPart = new int[mid];
		int[] secondPart = new int[n - mid];

		System.arraycopy(data, 0, firstPart, 0, mid);
		System.arraycopy(data, mid, secondPart, 0, n - mid);

		return merge(mergeSort(firstPart), mergeSort(secondPart));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] data1 = { 12, 1, 10, 3, 5, 7, 9, 11, 0 };
		System.out.print("Output: ");
		// MyUtil.printArrayInt(mergeSort(data1));
		MergeIterative mergeItr = new MergeIterative();
		mergeItr.mergeSort(data1, data1.length);
		MyUtil.printArrayInt(data1);
		
		LinkedList<Integer> lister=new LinkedList<>();
		lister.iterator();
		Node list=new Node(1);
		list.next=new Node(2);
		list.next.next=new Node(3);
		list.next.next.next=new Node(4);
		list.next.next.next.next=new Node(5);
		list.next.next.next.next.next=new Node(6);
		
		Node[] parts=new Node[2];
		MergeLinkedList.splitListInHalf(list, parts);
		if(parts[0]!=null)
		System.out.println(parts[0].val);
		if(parts[1]!=null)
		System.out.println(parts[1].val);
		
	}
}


class MergeIterative {

	/**
	 * Iterative merge sort.
	 * NOTE: takes care of odd element array.
	 * 
	 * @param arr
	 * @param n
	 */
	public void mergeSort(int arr[], int n) {
		int winSize; // slot size varies from 1 --> n/2
		int low;  // picks start index of each window
		
		/**
		 * Merge subarrays in bottom up manner. First merge subarrays of size 1
		 * to create sorted subarrays of size 2, then merge subarrays of size 2
		 * to create sorted subarrays of size 4, and so on.
		 */
		for (winSize = 1; winSize <= n - 1; winSize = 2 * winSize) {
			for (low = 0; low < n - 1; low += 2 * winSize) {
				int mid = low + winSize - 1;
				int high = Math.min(low + 2 * winSize - 1, n - 1);
				merge(arr, low, mid, high);
			}
		}
	}

	private void merge(int[] arr, int low, int mid, int high) {
		int[] L = new int[mid - low + 1];
		int[] R = new int[high - mid];
		int i = 0, j = 0, k = low;
		for (; i < L.length; i++)
			L[i] = arr[low + i];
		for (; j < R.length; j++)
			R[j] = arr[mid + 1 + j];

		i = 0;
		j = 0;
		for (; k < arr.length && i < L.length && j < R.length;) {
			if (L[i] < R[j]) {
				arr[k++] = L[i++];
			} else {
				arr[k++] = R[j++];
			}
		}

		while (i < L.length) {
			arr[k++] = L[i++];
		}
		while (j < R.length) {
			arr[k++] = R[j++];
		}
	}
}


class Node {
	int val;
	Node next = null;

	public Node(int data) {
		val = data;
		next = null;
	}
}


class MergeLinkedList {
		
	/**
	 * considering h1 and h2 are sorted list.
	 * @param h1
	 * @param h2
	 * @return
	 */
	public static Node mergeLists(Node h1, Node h2) {
		Node result = null;
		if (h1 == null)
			return h2;
		if (h2 == null)
			return h1;
		if (h1.val <= h2.val) {
			result = h1;
			result.next = mergeLists(h1.next, h2);
		} else {
			result = h1;
			result.next = mergeLists(h1, h2.next);
		}
		
		return result;
	}
	
	public static void splitListInHalf(Node head, Node[] parts) {
       if (head == null || head.next == null) {
			parts[0] = head;
			parts[1] = null;
			return;
		}
		//at least 2 elements
		Node fast = head;
		Node slow = head;
		Node prevSlow = null;
		while (fast.next != null) {
			prevSlow = slow;
			slow = slow.next;
			fast = fast.next;
			if (fast.next != null) {
				fast = fast.next;
			}
		}
		if (prevSlow != null){
			prevSlow.next = null;
		}
		 
		parts[0] = head;
		parts[1] = slow;		
	}
	
	/**
	 * reverse list in groups of k elements.
	 * @param head
	 * @param k
	 * @return
	 */
	public static Node reverse(Node head, int k)
    {
       Node current = head;
       Node next = null;
       Node prev = null;
        
       int count = 0;
 
       /* Reverse first k nodes of linked list */
       while (count < k && current != null) 
       {
           next = current.next;
           current.next = prev;
           prev = current;
           current = next;
           count++;
       }
 
       /* next is now a pointer to (k+1)th node 
          Recursively call for the list starting from current.
          And make rest of the list as next of first node */
       if (next != null) 
          head.next = reverse(next, k);
 
       // prev is now head of input list
       return prev;
    }                 
}