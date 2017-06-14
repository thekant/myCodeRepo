/**
 * 
 */
package com.kant.general;

import com.kant.sortingnsearching.MyUtil;

/**
 * http://www.geeksforgeeks.org/rearrange-positive-and-negative-numbers-publish/
 * http ://www.geeksforgeeks.org/rearrange-array-alternating-positive-negative-
 * items -o1-extra-space/
 * 
 * @author shaskant
 *
 */
public class RearrancePosNegAlternatively {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RearrancePosNegAlternatively prob = new RearrancePosNegAlternatively();
		int arr[] = { -1, 2, -3, 4, 5, 6, -7, 8, 9 };
		int n = arr.length;
		prob.rearrange(arr, n);
		System.out.println("Array after rearranging: ");
		MyUtil.printArrayInt(arr);
	}

	/**
	 * NOTE: Doesn't maintain order of appearance because of partition stage.
	 * 
	 * @param arr
	 * @param n
	 */
	public void rearrange(int arr[], int n) {
		/**
		 * STAGE:1 <begin> The following few lines are similar to partition
		 * process of QuickSort. The idea is to consider 0 as pivot and divide
		 * the array around it.
		 */
		int i = 0, temp = 0;
		for (int j = 0; j < n; j++) {
			if (arr[j] < 0) {
				temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				i++;
			}
		}

		/**
		 * STAGE:1 <end> input is now divided into -ves ...0...+ves
		 * STAGE:2<begin> By now all positive numbers are at end and negative
		 * numbers at the beginning of array. Initialize indexes for starting
		 * point of positive and negative numbers to be swapped
		 */
		int pos = i, neg = 0;

		// Increment the negative index by 2 and positive index by 1, i.e.,
		// swap every alternate negative number with next positive number
		while (pos < n && neg < pos && arr[neg] < 0) {
			temp = arr[neg];
			arr[neg] = arr[pos];
			arr[pos] = temp;
			pos++;
			neg += 2;
		}
		// STAGE: 2 <end>
	}

	/**
	 * NOTE: maintains order of appearance. <br/>
	 * Algo tries to store neg @ odd location and pos @ even location
	 * 
	 * @param arr
	 * @param n
	 */
	public void rearrangeOrdered(int arr[], int n) {
		int outofplace = -1;

		for (int index = 0; index < n; index++) {
			if (outofplace >= 0) {
				/**
				 * find the item which must be moved into the out-of-place entry
				 * if out-of-place entry is positive and current entry is
				 * negative OR if out-of-place entry is negative and current
				 * entry is negative then right rotate <br/>
				 * [...-3, -4, -5, 6...] --> [...6, -3, -4, -5...] <br/>
				 * ^ ^<br/>
				 * | |<br/>
				 * outofplace --> outofplace<br/>
				 */
				if (((arr[index] >= 0) && (arr[outofplace] < 0))
						|| ((arr[index] < 0) && (arr[outofplace] >= 0))) {
					rightrotate(arr, n, outofplace, index);

					// index is pointing to last good location
					if (index - outofplace > 2)
						outofplace = outofplace + 2;
					else
						outofplace = -1;
				}
			}

			// if no entry has been flagged out-of-place
			if (outofplace == -1) {
				// check if current entry is out-of-place
				if (((arr[index] >= 0) && ((index & 0x01) == 0))
						|| ((arr[index] < 0) && (index & 0x01) == 1))
					outofplace = index;
			}
		}
	}

	private void rightrotate(int arr[], int n, int outofplace, int curIndx) {
		int tmp = arr[curIndx];
		for (int i = curIndx; i > outofplace; i--)
			arr[i] = arr[i - 1];
		arr[outofplace] = tmp;
	}

}

/**
 * WHY if (index - outofplace > 2) outofplace = outofplace + 2; ? Take the
 * example given in article above: [...-3, -4, -5, 6...] <br/>
 * Lets say -3 is outOfPlace with index "k" (k will be odd), then next positive
 * number is 6 with index "k+3". <br/>
 * Now we will right rotate numbers from -3 to 6 as below:<br/>
 * <br/>
 * [...-3, -4, -5, 6...] --> [...6, -3, -4, -5...] <br/>
 * After the rotate, position k is good with a positive number. <br/>
 * Here difference between outOfPlace (k) and next good index "k+3" is 3 (>2). <br/>
 * This means there are at least 2 negative numbers after -3. <br/>
 * 
 * <br/>
 * After right rotate, index k will be good and there are at least 2 <br/>
 * negative numbers afterwards. <br/>
 * Since k is positive now, position "k+1" with a negative number is good.<br/>
 * But then "k+2" position is also negative which is not good and so this is the next <br/>
 * outOfPlace position. <br/>
 * 
 */
