package com.kant.datastructure.queues;

import com.kant.sortingnsearching.MyUtil;

/**
 * http://www.geeksforgeeks.org/find-a-tour-that-visits-all-stations/ <br/>
 * <p><b>Problem:</b><br/>
 * Suppose there is a circle. There are n petrol pumps on that circle. You are
 * given two sets of data.
 * </p>
 * <p>
 * 1. The amount of petrol that every petrol pump has. <br/> 2. Distance from that
 * petrol pump to the next petrol pump.
 * </p>
 * <p>
 * Calculate the first point from where a truck will be able to complete the
 * circle (The truck will stop at each petrol pump and it has infinite
 * capacity). Expected time complexity is O(n). Assume for 1 litre petrol, the
 * truck can go 1 unit of distance.
 * </p>
 * <p>
 * For example, let there be 4 petrol pumps with amount of petrol and distance
 * to next petrol pump value pairs as {4, 6}, {6, 5}, {7, 3} and {4, 5}. The
 * first point from where truck can make a circular tour is 2nd petrol pump.
 * Output should be “start = 1″ (index of 2nd petrol pump).
 * </p>
 * <p>
 * We can use a Queue to store the current tour. We first enqueue first petrol
 * pump to the queue, we keep enqueueing petrol pumps till we either complete
 * the tour, or current amount of petrol becomes negative. If the amount becomes
 * negative, then we keep dequeueing petrol pumps till the current amount
 * becomes positive or queue becomes empty.
 * </p>
 * <p>
 * <b>Solution:</b><br/>
 * Instead of creating a separate queue, we use the given array itself as queue.
 * We maintain two index variables start and end that represent rear and front
 * of queue.
 * </p>
 * 
 * @author shaskant
 *
 */
public class TourVisitToAllPetrolStationsInCircle {

	/**
	 * 
	 * @param data
	 * @return
	 */
	public int[] solve(PetrolPump[] data) {
		int[] result = null;
		return result;
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		TourVisitToAllPetrolStationsInCircle prob = new TourVisitToAllPetrolStationsInCircle();
		MyUtil.printArrayInt(prob.solve(null));
	}
}

/**
 * 
 * @author shaskant
 *
 */
class PetrolPump {
	private int petrol;
	private int distanceNext;

	/**
	 * @param petrol
	 * @param distanceNext
	 */
	public PetrolPump(int petrol, int distanceNext) {
		super();
		this.petrol = petrol;
		this.distanceNext = distanceNext;
	}

	/**
	 * @return the petrol
	 */
	public int getPetrol() {
		return petrol;
	}

	/**
	 * @param petrol
	 *            the petrol to set
	 */
	public void setPetrol(int petrol) {
		this.petrol = petrol;
	}

	/**
	 * @return the distanceNext
	 */
	public int getDistanceNext() {
		return distanceNext;
	}

	/**
	 * @param distanceNext
	 *            the distanceNext to set
	 */
	public void setDistanceNext(int distanceNext) {
		this.distanceNext = distanceNext;
	}

}