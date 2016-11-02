package com.kant.datastructure.graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * http://www.geeksforgeeks.org/stepping-numbers/
 * 
 * How to build the graph? Every node in the graph represents a stepping number;
 * there will be a directed edge from a node U to V if V can be transformed from
 * U. (U and V are Stepping Numbers) A Stepping Number V can be transformed from
 * U in following manner.
 * 
 * lastDigit refers to the last digit of U (i.e. U % 10) An adjacent number V
 * can be:
 * 
 * U*10 + lastDigit + 1 (Neighbor A) U*10 + lastDigit – 1 (Neighbor B) By
 * applying above operations a new digit is appended to U, it is either
 * lastDigit-1 or lastDigit+1, so that the new number V formed from U is also a
 * Stepping Number. Therefore, every Node will have at most 2 neighboring Nodes.
 * 
 * Edge Cases: When the last digit of U is 0 or 9
 * 
 * Case 1: lastDigit is 0 : In this case only digit ‘1’ can be appended. Case 2:
 * lastDigit is 9 : In this case only digit ‘8’ can be appended.
 * 
 * What will be the source/starting Node?
 * 
 * Every single digit number is considered as a stepping Number, so bfs
 * traversal for every digit will give all the stepping numbers starting from
 * that digit. Do a bfs/dfs traversal for all the numbers from [0,9].
 * 
 * <br/>
 * -> 0 is a stepping Number and it is in the range so display it. -> 1 is a
 * Stepping Number, find neighbors of 1 i.e., 10 and 12 and push them into the
 * queue
 * 
 * How to get 10 and 12? Here U is 1 and last Digit is also 1 V = 10 + 0 = 10 (
 * Adding lastDigit - 1 ) V = 10 + 2 = 12 ( Adding lastDigit + 1 )
 * 
 * Then do the same for 10 and 12 this will result into 101, 123, 121 but these
 * Numbers are out of range. Now any number transformed from 10 and 12 will
 * result into a number greater than 21 so no need to explore their neighbors.
 * 
 * -> 2 is a Stepping Number, find neighbors of 2 i.e. 21, 23. -> 23 is out of
 * range so it is not considered as a Stepping Number (Or a neighbor of 2)
 * 
 * The other stepping numbers will be 3, 4, 5, 6, 7, 8, 9.
 * 
 * @author shaskant
 *
 */
public class SteppingNumbersgraphSolution {

	// Prints all stepping numbers reachable from num
	// and in range [n, m]
	public static void bfs(int n, int m, int num) {
		// Queue will contain all the stepping Numbers
		Queue<Integer> q = new LinkedList<Integer>();

		q.add(num);

		while (!q.isEmpty()) {
			// Get the front element and pop from
			// the queue
			int stepNum = q.poll();

			// If the Stepping Number is in
			// the range [n,m] then display
			if (stepNum <= m && stepNum >= n) {
				System.out.print(stepNum + " ");
			}

			// If Stepping Number is 0 or greater
			// then m ,need to explore the neighbors
			if (stepNum == 0 || stepNum > m)
				continue;

			// Get the last digit of the currently
			// visited Stepping Number
			int lastDigit = stepNum % 10;

			// There can be 2 cases either digit
			// to be appended is lastDigit + 1 or
			// lastDigit - 1
			int stepNumA = stepNum * 10 + (lastDigit - 1);
			int stepNumB = stepNum * 10 + (lastDigit + 1);

			// If lastDigit is 0 then only possible
			// digit after 0 can be 1 for a Stepping
			// Number
			if (lastDigit == 0)
				q.add(stepNumB);

			// If lastDigit is 9 then only possible
			// digit after 9 can be 8 for a Stepping
			// Number
			else if (lastDigit == 9)
				q.add(stepNumA);

			else {
				q.add(stepNumA);
				q.add(stepNumB);
			}
		}
	}

	// Prints all stepping numbers in range [n, m]
	// using BFS.
	public static void displaySteppingNumbers(int n, int m) {
		// For every single digit Number 'i'
		// find all the Stepping Numbers
		// starting with i
		for (int i = 0; i <= 9; i++)
			bfs(n, m, i);
	}

	// Driver code
	public static void main(String args[]) {
		int n = 0, m = 21;

		// Display Stepping Numbers in
		// the range [n,m]
		displaySteppingNumbers(n, m);
	}

	// Method display's all the stepping numbers
	// in range [n, m]
	public static void dfs(int n, int m, int stepNum) {
		// If Stepping Number is in the
		// range [n,m] then display
		if (stepNum <= m && stepNum >= n)
			System.out.print(stepNum + " ");

		// If Stepping Number is 0 or greater
		// than m then return
		if (stepNum == 0 || stepNum > m)
			return;

		// Get the last digit of the currently
		// visited Stepping Number
		int lastDigit = stepNum % 10;

		// There can be 2 cases either digit
		// to be appended is lastDigit + 1 or
		// lastDigit - 1
		int stepNumA = stepNum * 10 + (lastDigit - 1);
		int stepNumB = stepNum * 10 + (lastDigit + 1);

		// If lastDigit is 0 then only possible
		// digit after 0 can be 1 for a Stepping
		// Number
		if (lastDigit == 0)
			dfs(n, m, stepNumB);

		// If lastDigit is 9 then only possible
		// digit after 9 can be 8 for a Stepping
		// Number
		else if (lastDigit == 9)
			dfs(n, m, stepNumA);
		else {
			dfs(n, m, stepNumA);
			dfs(n, m, stepNumB);
		}
	}

}
