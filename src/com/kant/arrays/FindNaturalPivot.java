/**
 * 
 */
package com.kant.arrays;

/**
 * @author shaskant
 *
 */
public class FindNaturalPivot {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int arr[] = { 10, 11, 12, 9, 5, 13, 14, 15, 16, 17 }; // ans 13
		System.out.println(findNaturalPivot(arr));
	}

	/**
	 * returns natural pivot
	 * 
	 * @param arr
	 * @return
	 */
	public static int findNaturalPivot(int arr[]) {
		int maxCur = arr[0];
		int cur = 1;
		int index = 1;
		int n = arr.length - 1;
		while (true) {
			// find pivot
			while (cur < n && arr[cur] <= maxCur)
				cur++;
			if (cur == n)
				return -1;
			// assuming this is pivot
			maxCur = arr[cur];
			index = cur + 1;
			int nxtMax = maxCur;

			while (index < n) {
				if (arr[cur] < arr[index]) {// if all values after assumed pivot
											// are smaller
					index++;
					if (arr[index] > nxtMax)
						nxtMax = arr[index]; // calculate nxtMax for fail safe
												// [in case it's not pivot]
				} else { // now assume this is the pivot and start over
					cur = index;
					maxCur = nxtMax;
					break;
				}
				if (index == n) // if we have reached end , only possible if
								// assumed pivot is natural pivot
					return arr[cur];
			}
		}
	}

}
