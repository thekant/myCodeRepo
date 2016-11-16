/**
 * 
 */
package com.kant.general;

/**
 * @author shaskant
 *
 */
public class FibonacciGen {

	/**
	 * Fn = Fn-1 + Fn-2 [Expanding both terms]<br/>
	 * = Fn-2 + Fn-3 + Fn-3 + Fn-4 = Fn-2 + 2Fn-3 + Fn-4 [Expending first term]<br/>
	 * = Fn-3 + Fn-4 + 2Fn-3 + Fn-4 = 3Fn-3 + 2Fn-4 [Expending one Fn-4]<br/>
	 * = 3Fn-3 + Fn-4 + Fn-5 + Fn-6 [Combing Fn-4 and Fn-5]<br/>
	 * = 4Fn-3 + Fn-6 <br/>
	 * Using fact EFn = F3n <br/>
	 * Since every third Fibonacci Number is even,<br/>
	 * EFn = 4f3(n-1)+f3(n-2) = 4EFn-1 + EFn-2
	 * 
	 * @param n
	 * @return
	 */
	public static int evenFib(int n) {
		if (n < 1)
			return n;
		if (n == 1)
			return 2;
		return 4 * evenFib(n - 1) + evenFib(n - 2);
	}

	/**
	 * log(N) approach to multiplication
	 * 
	 * @param x
	 * @param n
	 * @return
	 */
	public static int[][] power(int[][] x, int n) {
		if (n == 0)
			return new int[][] { { 1, 0 }, { 0, 1 } };// identity matrix
		int[][] result = power(x, n / 2);
		result = multiplyMatrix(result, result);
		if (n % 2 == 0) {
			return result;
		} else {
			return multiplyMatrix(x, result);
		}
	}

	/**
	 * log(N) complexity
	 * 
	 * @param n
	 * @return
	 */
	public static int fib(int n) {
		int F[][] = new int[][] { { 1, 1 }, { 1, 0 } };
		if (n == 0)
			return 0;
		F = power(F, n - 1);
		return F[0][0];
	}

	/**
	 * 2,2 matrix multi
	 */
	public static int[][] multiplyMatrix(int[][] M, int[][] F) {
		int x = F[0][0] * M[0][0] + F[0][1] * M[1][0];
		int y = F[0][0] * M[0][1] + F[0][1] * M[1][1];
		int z = F[1][0] * M[0][0] + F[1][1] * M[1][0];
		int w = F[1][0] * M[0][1] + F[1][1] * M[1][1];

		int[][] result = new int[2][2];
		result[0][0] = x;
		result[0][1] = y;
		result[1][0] = z;
		result[1][1] = w;
		return result;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for (int i = 0; i < 9; i++)
			System.out.println(fib(i));
	}

	// http://www.geeksforgeeks.org/program-for-nth-fibonacci-number/

}
