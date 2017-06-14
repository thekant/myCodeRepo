/**
 * 
 */
package com.kant.algorithms.greedy;

/**
 * http://www.geeksforgeeks.org/find-minimum-time-to-finish-all-jobs-with-given-
 * constraints/
 * 
 * @author shaskant
 *
 */
public class JobAssignmentWithConstraint {

	/**
	 * 
	 * @param arr
	 * @return
	 */
	private int getMax(int arr[]) {
		int result = arr[0];
		for (int i = 1; i < arr.length; i++)
			if (arr[i] > result)
				result = arr[i];
		return result;
	}

	/**
	 * Returns true if it is possible to finish jobs[] within given time 'time'
	 * 
	 * @param time
	 * @param K
	 *            number of assignees
	 * @param job
	 * @return
	 */
	private boolean isPossible(final int time, int K, int job[]) {
		int cnt = 1;
		int curr_time = 0; // time assigned to current assignee

		for (int i = 0; i < job.length;) {
			// If time assigned to current assignee exceeds max,
			// increment count of assignees.
			if (curr_time + job[i] > time) {
				curr_time = 0;
				cnt++;
			} else { // Else add time of job to current time and move
						// to next job.
				curr_time += job[i];
				i++;
			}
		}
		// Returns true if count is smaller than k
		return (cnt <= K);
	}

	/**
	 * Returns minimum time required to finish given array of jobs <br/>
	 * k --> number of assignees <br/>
	 * T --> Time required by every assignee to finish 1 unit <br/>
	 * m --> Number of jobs
	 **/
	public int findMinTime(int K, int T, int job[]) {
		// Set start and end for binary search
		// end provides an upper limit on time
		int end = 0, start = 0;
		for (int i = 0; i < job.length; ++i)
			end += job[i];

		int ans = end;// worst case 1 assignee to do all jobs

		// Find the job that takes maximum time
		int job_max = getMax(job);

		// Do binary search for minimum feasible time
		while (start <= end) {
			int mid = (start + end) / 2;

			// If it is possible to finish jobs in mid time
			if (mid >= job_max && isPossible(mid, K, job)) {
				ans = Math.min(ans, mid);
				end = mid - 1;
			} else
				start = mid + 1;
		}

		return (ans * T);
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int job[] = { 10, 7, 8, 12, 6, 8 };
		int k = 4, T = 5;
		JobAssignmentWithConstraint prob = new JobAssignmentWithConstraint();
		System.out.println(prob.findMinTime(k, T, job));
	}
}
