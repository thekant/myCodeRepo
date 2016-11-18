package com.kant.general;

import com.kant.sortingnsearching.MyUtil;

/**
 * 
 * @author shaskant
 *
 */
public class Segregate0s1s {

	/**
	 * 
	 */
	public static void main(String[] args) {
		int[] data = { 0, 1, 0, 1, 1, 0,0 };

		segregateMethod1(data);
		MyUtil.printArrayInt(data);
	}

	/**
	 * 
	 * @param data
	 */
	public static void segregateMethod1(int[] data) {
		int left = 0, right = data.length - 1;
		while (true) {
			while (data[left] == 0)
				left++;
			while (data[right] == 1)
				right--;

			if (left < right) {
				int temp = data[left];
				data[left] = data[right];
				data[right] = temp;
			}else{
				break;
			}
		}
	}

}
