package com.kant.general;

/**
 * 
 * @author shaskant
 *
 */
public class NumberOps {

	/**
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static int calGCD(int a, int b) {
		if (a == 0)
			return b;
		else
			return calGCD(b % a, a);
	}
	
	public static int calLcm(int a,int b){
		return a*b / calGCD(a, b);
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(calGCD(3, 2));
	}
}
