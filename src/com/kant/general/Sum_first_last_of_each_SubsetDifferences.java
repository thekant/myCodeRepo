package com.kant.general;

/**
 * 
 * Let us say that the elements of S are {a1, a2, a3,…, an}. Note the following
 * observation:
 * 
 * Subsets containing element a1 as the first element can be obtained by taking
 * any subset of {a2, a3,…, an} and then including a1 into it. Number of such
 * subsets will be 2n-1. Subsets containing element a2 as the first element can
 * be obtained by taking any subset of {a3, a4,…, an} and then including a2 into
 * it. Number of such subsets will be 2n-2. Subsets containing element ai as the
 * first element can be obtained by taking any subset of {ai, a(i+1),…, an} and
 * then including ai into it. Number of such subsets will be 2n-i
 * 
 * Therefore, the sum of first element of all subsets will be: SumF = a1.2n-1 +
 * a2.2n-2 +…+ an.1
 * 
 * In a similar way we can compute the sum of last element of all subsets of S
 * (Taking at every step ai as last element instead of first element and then
 * obtaining all the subsets). SumL = a1.1 + a2.2 +…+ an.2n-1
 * 
 * Finally, the answer of our problem will be SumL – SumF
 * 
 * @author shaskant
 *
 */
public class Sum_first_last_of_each_SubsetDifferences {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int[] data = new int[] { 5, 2, 9, 6};
		int sumF = 0, sumL = 0;
		for (int i = 0; i < data.length; i++) {
			sumF += data[i] * Math.pow(2, (data.length - 1 - i));
			sumL+=data[i]*Math.pow(2, i);
		}
		System.out.println(sumL-sumF);
	}

}
