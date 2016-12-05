/**
 * 
 */
package com.kant.algorithms.dynamicprogramming;

/**
 * </b>Explanation of problem</b> :
 * <p>
 * The edit distance of two strings, s1 and s2, is defined as the minimum number
 * of point mutations required to change s1 into s2, where at a point mutation
 * is one of one of the forms: change a letter, insert a letter or delete a
 * letter
 * </p>
 * The following recurrence relations define the edit distance, d(s1,s2), of two
 * strings s1 and s2:<br/>
 * 
 * 1. d('', '') = 0 -- '' = empty string <br/>
 * 2. d(s, '') = d('', s) = |s| -- i.e. length of s <br/>
 * 3. d(s1+ch1, s2+ch2) = min( d(s1, s2) + if ch1==ch2 then 0 else 1 fi,
 * d(s1+ch1, s2) + 1, d(s1, s2+ch2) + 1 ) <br/>
 * <br/>
 * <p>
 * The first two rules above are obviously true, so it is only necessary
 * consider the last one. Here, neither string is the empty string, so each has
 * a last character, ch1 and ch2 respectively. Somehow, ch1 and ch2 have to be
 * explained in an edit of s1+ch1 into s2+ch2. If ch1 equals ch2, they can be
 * matched for no penalty, i.e. 0, and the overall edit distance is d(s1,s2). If
 * ch1 differs from ch2, then ch1 could be changed into ch2, i.e. 1, giving an
 * overall cost d(s1,s2)+1. Another possibility is to delete ch1 and edit s1
 * into s2+ch2, d(s1,s2+ch2)+1. The last possibility is to edit s1+ch1 into s2
 * and then insert ch2, d(s1+ch1,s2)+1. There are no other alternatives. We take
 * the least expensive, i.e. min, of these alternatives.
 * </p>
 * 
 * 
 * A two-dimensional matrix, m[0..|s1|,0..|s2|] is used to hold the edit
 * distance values:
 * 
 * m[i,j] = d(s1[1..i], s2[1..j])
 * 
 * m[0,0] = 0 m[i,0] = i, i=1..|s1| <br/>
 * m[0,j] = j, j=1..|s2| <br/>
 * 
 * m[i,j] = min( m[i-1,j-1] + if s1[i]=s2[j] then 0 else 1 fi, m[i-1, j] + 1,
 * m[i, j-1] + 1 ),<br/>
 * i=1..|s1|, j=1..|s2| <br/>
 * <p>
 * m[,] can be computed row by row. Row m[i,] depends only on row m[i-1,]. The
 * time complexity of this algorithm is O(|s1|*|s2|). If s1 and s2 have a
 * `similar' length, about `n' say, this complexity is O(n2), much better than
 * exponential!
 * </p>
 * 
 * @author shashi
 * 
 */
public class EdiDistanceProblem {

	/**
	 * Recursive implementation
	 * 
	 * @param X
	 * @param Y
	 * @param m
	 * @param n
	 * @return
	 */
	public static int editDistanceRecursion(char[] X, char[] Y, int m, int n) {
		// Base cases
		if (m == 0 && n == 0)
			return 0;
		if (m == 0)
			return n;
		if (n == 0)
			return m;

		// Recurse
		int left = editDistanceRecursion(X, Y, m - 1, n) + 1;
		int right = editDistanceRecursion(X, Y, m, n - 1) + 1;
		int both = editDistanceRecursion(X, Y, m - 1, n - 1)
				+ ((X[m - 1] != Y[n - 1]) ? 1 : 0);

		return Math.min(Math.min(left, right), both);
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String text1 = "ptest";
		String text2 = "pest";
		System.out.println(editDistanceRecursion(text1.toCharArray(),
				text2.toCharArray(), text1.length(), text2.length()));
	}
}
