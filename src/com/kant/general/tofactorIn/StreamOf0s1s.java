/**
 * 
 */
package com.kant.general.tofactorIn;

/**
 * @author shashi
 * 
 */
public class StreamOf0s1s {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] data = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1 };
		System.out.println(new StreamOf0s1s().findOne(data, 0, data.length));

	}

	/**
	 * This method could handle any part(startIndex --> endIndex) of stream of
	 * 0s and 1s.
	 * 
	 * @param A
	 * @param startIndex
	 * @param endIndex
	 * @return
	 */
	public int findOne(int[] A, int startIndex, int endIndex) {
		int start = startIndex;
		int end = startIndex + 1;
		int mid;
		while (end < endIndex && A[end] != 1) {
			start = end;
			end *= 2;
		}
		if (end > endIndex && A[endIndex - 1] != 1)
			return -1;

		while (start < end && A[start + 1] != 1) {
			mid = (start + end) / 2;
			if (A[mid] == 0)
				start = mid;
			else
				end = mid;
		}
		if (start == end)
			return -1;
		return start + 1;
	}

	// A is the input array
	// 1. start=0, end=1, mid=0
	// 2. if((A[start]==0)&&(A[end]==1)) return start;
	// 3. while(A[end] != 1)
	// start=end;
	// end=end<<;1;
	// 4. while(A[start+1] != 1)
	// mid=(start+end)/2;
	// if(A[mid]==0)
	// start=mid;
	// else
	// end=mid;
	// 5. return start;

}
