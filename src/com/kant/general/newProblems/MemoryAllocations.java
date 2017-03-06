/**
 * 
 */
package com.kant.general.newProblems;

import java.util.Arrays;

/**
 * @author shaskant
 *
 */
public class MemoryAllocations {
	private AllocStrategy strategy;

	public AllocStrategy getStrategy() {
		return strategy;
	}

	public void setStrategy(AllocStrategy strategy) {
		this.strategy = strategy;
	}

	/**
	 * prints memory allocation values
	 */
	public void allocateMemory(int[] blockSize, int m, int[] processSize, int n) {
		int[] allocations = strategy.allocate(blockSize, m, processSize, n);
		printAllocationValues(processSize, n, allocations);
	}

	/**
	 * @param processSize
	 * @param n
	 * @param allocations
	 */
	private void printAllocationValues(int[] processSize, int n,
			int[] allocations) {
		System.out.print("For " + getStrategy().toString() + " Strategy: ");
		System.out.println("\nProcess No.\tProcess Size\tBlock no.");
		for (int i = 0; i < n; i++) {
			System.out
					.print("   " + (i + 1) + "\t\t" + processSize[i] + "\t\t");
			if (allocations[i] != -1)
				System.out.println(allocations[i] + 1);
			else
				System.out.println("Not Allocated");
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MemoryAllocations memoryAllocatr = new MemoryAllocations();
		// memoryAllocatr.setStrategy(new FirstFit());
		// memoryAllocatr.setStrategy(new BestFit());
		memoryAllocatr.setStrategy(new WorstFit());
		int blockSize[] = { 100, 500, 200, 300, 600 };
		int processSize[] = { 212, 417, 112, 426 };
		memoryAllocatr.allocateMemory(blockSize, blockSize.length, processSize,
				processSize.length);

	}

}

interface AllocStrategy {
	/**
	 * returns allocation values for each process. -1 in case allocation is not
	 * possible for a process otherwise index of the blockSize.
	 */
	int[] allocate(int blockSize[], int m, int processSize[], int n);
}

/**
 * 
 * In the first fit, partition is allocated which is first sufficient from the
 * <b>top of Main Memory.</b>
 * 
 * http://www.geeksforgeeks.org/program-first-fit-algorithm-memory- management/
 * 
 * @author shaskant
 *
 */
class FirstFit implements AllocStrategy {

	@Override
	public int[] allocate(int[] blockSize, int m, int[] processSize, int n) {
		int allocations[] = new int[n];
		Arrays.fill(allocations, -1);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (blockSize[j] >= processSize[i]) {
					allocations[i] = j + 1;
					blockSize[j] -= processSize[i];
					break;
				}
			}
		}
		return allocations;
	}

	@Override
	public String toString() {
		return "First Fit";
	}
}

/**
 * Best fit allocates the process to a partition which is the smallest
 * sufficient partition among the free available partitions.
 * 
 * http://www.geeksforgeeks.org/program-best-fit-algorithm-memory-management/
 * 
 * @author shaskant
 *
 */
class BestFit implements AllocStrategy {

	@Override
	public int[] allocate(int[] blockSize, int m, int[] processSize, int n) {
		int allocations[] = new int[n];
		Arrays.fill(allocations, -1);

		for (int i = 0; i < n; i++) {
			int bestIndex = -1;
			for (int j = 0; j < m; j++) {
				if (blockSize[j] >= processSize[i]) {
					if (bestIndex == -1)
						bestIndex = j;
					else if (blockSize[bestIndex] > blockSize[j]) {
						bestIndex = j;
					}
				}
			}
			if (bestIndex != -1) {
				blockSize[bestIndex] -= processSize[i];
				allocations[i] = bestIndex;
			}
		}
		return allocations;
	}

	@Override
	public String toString() {
		return "Best Fit";
	}
}

/**
 * http://www.geeksforgeeks.org/program-worst-fit-algorithm-memory-management/
 * 
 * Worst Fit allocates a process to the partition which is largest sufficient
 * among the freely available partitions available in the main memory. If a
 * large process comes at a later stage, then memory will not have space to
 * accommodate it.
 * 
 * @author shaskant
 *
 */
class WorstFit implements AllocStrategy {

	@Override
	public int[] allocate(int[] blockSize, int m, int[] processSize, int n) {
		int allocations[] = new int[n];
		Arrays.fill(allocations, -1);

		for (int i = 0; i < n; i++) {
			int worstIndex = -1;
			for (int j = 0; j < m; j++) {
				if (blockSize[j] >= processSize[i]) {
					if (worstIndex == -1)
						worstIndex = j;
					else if (blockSize[worstIndex] < blockSize[j]) {
						worstIndex = j;
					}
				}
			}
			if (worstIndex != -1) {
				blockSize[worstIndex] -= processSize[i];
				allocations[i] = worstIndex;
			}
		}
		return allocations;
	}

	@Override
	public String toString() {
		return "Worst Fit";
	}
}