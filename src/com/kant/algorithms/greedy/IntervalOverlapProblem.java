/**
 * 
 */
package com.kant.algorithms.greedy;

import java.util.Arrays;

/**
 * Problem is trains with arrival and departure times given. Find the minimum
 * number of platforms needed.
 * 
 * http://www.geeksforgeeks.org/minimum-number-platforms-required-railwaybus-
 * station/
 * 
 * @author shashi
 * 
 */
public class IntervalOverlapProblem {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		/**
		 * Prob 1: finding maximum overlap.
		 * 
		 * Basically finding out in worst case how many platforms are necessary
		 * at station at any time.
		 */
		int arr[] = { 900, 940, 950, 1100, 1500, 1800 };
		int dep[] = { 910, 1200, 1120, 1130, 1900, 2000 };
		System.out.println("Minimum number of platforms needed: "
				+ minPlatFormsNeeded(arr, dep));

		/**
		 * Prob 2: finding maximum size set with no overlap.
		 * 
		 * Basically how many trains can be serviced with single platform.
		 */
		arr = new int[] { 900, 940, 950, 1100, 1500, 1800 };
		dep = new int[] { 910, 1200, 1120, 1130, 1900, 2000 };

		Interval[] intervals = new Interval[arr.length];
		for (int index = 0; index < arr.length; index++) {
			intervals[index] = new Interval(arr[index], dep[index]);
		}

		Arrays.sort(intervals);
		System.out.println("\nSorted intervals based on finish time:");
		System.out.println("---------------------------------------");
		for (Interval interval : intervals) {
			System.out.println(interval);
		}

		System.out.println("\nLargest non overlapping set: ");
		System.out.println("---------------------------------------");
		for (int item : largestNonOverlappingSubset(intervals)) {
			System.out.println(intervals[item]);
		}

	}

	/**
	 * sort all events .. <br/>
	 * if a train arrive [platform++] <br/>
	 * else if a train departs [platform--]
	 * 
	 * track the max platform needed at any event.
	 * 
	 * @param arr
	 * @param dep
	 * @return
	 */
	public static int minPlatFormsNeeded(int[] arr, int[] dep) {
		int platformCount = 1;
		int i = 1, j = 0;
		int count = 1;
		Arrays.sort(arr);
		Arrays.sort(dep);

		// merge process
		while (i < arr.length && j < dep.length) {
			if (arr[i] < dep[j]) {
				count++;
				i++;
				if (platformCount < count)
					platformCount = count;
			} else {
				count--;
				j++;
			}
		}
		return platformCount;
	}

	/**
	 * Largest subset will have {1,2,,,n} such that start[i] > finish[j] or
	 * start[j] > finish[i].
	 * 
	 * GREEDY APPROACH
	 * 
	 * @param intervals
	 * @return
	 */
	public static int[] largestNonOverlappingSubset(Interval[] intervals) {
		int[] subset = new int[intervals.length];
		// first interval with least finish time will be in set.
		subset[0] = 0;
		int count = 0;

		// next onwards if a interval has a start > finish of last interval ,
		// keep it
		for (int i = 1; i < intervals.length; i++) {
			if (intervals[i].start > intervals[subset[count]].finish) {
				count++;
				subset[count] = i;
			}
		}
		return subset;
	}

}

class Interval implements Comparable<Interval> {
	public int start;
	public int finish;

	/**
	 * @param start
	 * @param finish
	 */
	public Interval(int start, int finish) {
		super();
		this.start = start;
		this.finish = finish;
	}

	@Override
	public int compareTo(Interval other) {
		if (this.finish < other.finish)
			return -1;
		else if (this.finish > other.finish)
			return 1;
		return 0;
	}

	@Override
	public String toString() {
		return "Start: " + start + " <----> Finish: " + finish;
	}

}
