/**
 * 
 */
package com.kant.general;

import java.util.Arrays;

/**
 * http://www.geeksforgeeks.org/lexicographic-rank-of-a-string/
 * 
 * <b>Lexicographic Ranking</b>
 * 
 * <b>LOGIC</b><br/>
 * Let the given string be "STRING" . In the input string, 'S' is the first
 * character. There are total 6 characters and 4 of them are smaller than 'S'.
 * So there can be 4 * 5! smaller strings where first character is smaller than
 * 'S', like following
 * 
 * R X X X X X <br/>
 * I X X X X X <br/>
 * N X X X X X <br/>
 * G X X X X X
 * 
 * Now let us Fix 'S' and find the smaller strings staring with 'S'.
 * 
 * Repeat the same process for T, rank is 4*5! + 4*4! + ..
 * 
 * Now fix T and repeat the same process for R, rank is 4*5! + 4*4! + 3*3! + ..
 * 
 * Now fix R and repeat the same process for I, rank is 4*5! + 4*4! + 3*3! +
 * 1*2! + ..
 * 
 * Now fix I and repeat the same process for N, rank is 4*5! + 4*4! + 3*3! +
 * 1*2! + 1*1! + ..
 * 
 * Now fix N and repeat the same process for G, rank is 4*5! + 4*4 + 3*3! + 1*2!
 * + 1*1! + 0*0!
 * 
 * Rank = 4*5! + 4*4! + 3*3! + 1*2! + 1*1! + 0*0! = 597
 * 
 * Since the value of rank starts from 1, the final rank = 1 + 597 = 598
 * 
 * @author shashi
 * 
 */
public class WordRankFinderProblem {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		char[] test = { 'S', 'T', 'R', 'I', 'N', 'G' };
		WordRankFinderProblem prob = new WordRankFinderProblem();
		System.out.println(prob.findRankOptimal(test));
	}

	/**
	 * O(n^2)
	 * 
	 * @param word
	 * @return
	 */
	public int findRankOfWord(char[] word) {
		int n = word.length;
		int mul = factorial(n);
		int rank = 1;

		for (int i = 0; i < n; i++) {
			mul = mul / (n - i);
			int countSmaller = findSmallerOnRight(word, i);
			rank += countSmaller * mul;
		}
		return rank;
	}

	private int findSmallerOnRight(char[] input, int pos) {
		int smallerCount = 0;
		for (int count = pos + 1; count < input.length; count++) {
			if (input[count] < input[pos])
				smallerCount++;
		}
		return smallerCount;
	}

	/**
	 * 
	 * @param n
	 * @return
	 */
	private int factorial(int n) {
		if (n == 0 || n == 1)
			return 1;
		return n * factorial(n - 1);
	}

	final static int MAX_CHAR = 256;

	/**
	 * O(n)
	 * 
	 * @param word
	 * @return
	 */
	public int findRankOptimal(char[] word) {
		int n = word.length;
		int mul = factorial(n);
		int rank = 1;
		int[] count = new int[MAX_CHAR];
		Arrays.fill(count, 0);
		populateAndIncreaseCount(count, word);

		for (int i = 0; i < n; i++) {
			mul /= (n - i);
			rank += count[word[i] - 1] * mul;// all chars smaller than this char
			updatecount(count, word[i]);
		}
		return rank;
	}

	/**
	 * Construct a count array where value at every index contains count of
	 * smaller characters in whole string
	 * 
	 * @param count
	 * @param str
	 */
	private void populateAndIncreaseCount(int[] count, char[] str) {
		int i;

		for (i = 0; i < str.length; ++i)
			++count[str[i]];

		for (i = 1; i < MAX_CHAR; ++i)
			count[i] += count[i - 1];
	}

	/**
	 * Removes a character ch from count[] array constructed by
	 * populateAndIncreaseCount()
	 * 
	 * @param count
	 * @param ch
	 */
	private void updatecount(int[] count, char ch) {
		int i;
		for (i = ch; i < MAX_CHAR; ++i)
			--count[i];
	}

}
