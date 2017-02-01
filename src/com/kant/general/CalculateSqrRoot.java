/**
 * 
 */
package com.kant.general;

/**
 * @author shashi
 * 
 */
public class CalculateSqrRoot {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(sqrt(10));
	}

	/**
	 * 
	 * @param a
	 * @return
	 */
	public static double sqrt(double a) {
		if (a < 0)
			return -1;
		if (a == 0 || a == 1)
			return a;

		double precision = 0.00001;
		double start = 0;
		double end = a;
		// we define these two start/end values because usually 0<sqrt(a)<a
		// however, if a<1; then 0<a<sqrt(a)
		if (a < 1)
			end = 1;

		// define a loop to continue if the precision is not yet achieved
		while (end - start > precision) {
			double mid = (start + end) / 2;
			double midSqr = mid * mid;
			if (midSqr == a)
				return mid;// we find the exact sqrt value!
			else if (midSqr < a)
				start = mid;// we shift our focus to bigger half
			else
				end = mid;// shift focus to smaller half
		}

		// if we did not find exact sqrt value, we return the approximated value
		// with the defined precision
		return (start + end) / 2;
	}

}
