/**
 * 
 */
package com.kant.dividenrule;

/**
 * Divide and rule based power calculation.
 * 
 * @author shaskant
 *
 */
public class PowerCalculation {

	/**
	 * 
	 * T(n)=T(n/2)+constant(multiplication)
	 * @param x
	 * @param n
	 * @return
	 */
	public static double calPow(double x, int n) {
		if(x==0)return 0;
		if (n == 0)
			return 1;
		if (n == 1)
			return x;
		double resultPart = calPow(x, n / 2);
		if (n % 2 == 0) {
			return resultPart * resultPart;
		}
		return x * resultPart * resultPart;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(calPow(2, 10));
	}

}
