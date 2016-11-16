/**
 * 
 */
package com.kant.general;

/**
 * @author shaskant
 *
 */
public class LongestPalindromeProductOfTwoNdigNumbers {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int n = 3;
		int lowerLimit = 1;
		int upperLimit = 0;
		for (int i = 1; i < n; i++) {
			lowerLimit *= 10;
		}
		upperLimit = lowerLimit * 10 - 1;
		System.out.println("[Searching for range]^2: "+lowerLimit + " -> " + upperLimit);

		int max_product = -1;
		for (int i = lowerLimit; i <= upperLimit; i++) {
			for (int j = i; j <= upperLimit; j++) {
				int productTemp = j * i;
				if (productTemp < max_product)
					break;
				int reverseProd = 0;
				int number = productTemp;

				while (number != 0) {
					reverseProd = reverseProd * 10 + number % 10;
					number = number / 10;
				}
				if (reverseProd == productTemp && productTemp > max_product) {
					max_product = productTemp;
				}
			}
		}
		System.out.println("[Result]:"+max_product);
	}
}
