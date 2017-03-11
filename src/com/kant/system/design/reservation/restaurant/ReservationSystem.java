/**
 * 
 */
package com.kant.system.design.reservation.restaurant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Segment tree of the slots.. with each slot has waiting reservation.
 * 
 * As soon as a table is freed , go to that time slot and for any
 * reservation that can be accommodated , verify and update status of table and
 * notify user for the reservation.
 * 
 * USE OBSERVER PATTERN with TABLE <--> ReservationSystem
 * 
 * @author shaskant
 */
public abstract class ReservationSystem {
	int numberOfTables; // Total numbers of table can be accomodate in this
						// restaurant
	long openingTime; // Restaurant opening time
	long closeTime; // Closing time, all the requests should be performed in
					// between these two times
	ArrayList<Table> tables; // Holds all the table information, used to query
								// the table information
	ArrayList<Reservation> waitingList; // All the reservations currently in
										// waiting state
	HashMap<Integer, Reservation> reservationMap; // searches any reservation
													// query with
													// booking/reservation ID.

	/**
	 * This variable is used to reduce the time for the query like this :
	 * "Give all the free tables" at any specific time. We can extract the same
	 * information using 'tables' variable as it holds the free slot times also.
	 * But in some heavy traffic restaurant, which accepts pre-booking, looping
	 * all the tables and extracting the availability will be a tedious one. So,
	 * again
	 */
	protected HashSet<Table> freeSlots[];

	public abstract boolean cancelReservation(long ID);

	/**
	 * notify user in case there is change in status of reservation
	 */
	public abstract void contactCustomer(Reservation rID);

	public abstract boolean closeReservation(long ID);

	public abstract Reservation searchReservation(long ID);

	public abstract Reservation makeReservation(int noOfPerson, long start,
			long end);

	public abstract int checkAvailablity(int noOfperson, long start, long end,
			ArrayList<Table> tables, ArrayList<Long> alternateTimes);
}

class Reservation {
	long bookingId;
	long startTime; // when starts
	long endTime; // when ends
	Guest forPerson; // who asks for this reservation
	int totalPersonCount; // total member count

	// This details below will be filled by the Restaurant system
	boolean isCancelled;
	boolean isWaiting;
	ArrayList<Table> allocatedTables; // which tables has been allocated to this
										// reservation

	/**
	 * This function will be triggered if booking is successful, can not be made
	 * or in waiting.
	 */
	public void notifyUserWithDetails() {
		// notify user
	}
}

class Table {
	long tableID; // table identifier
	int seatingCapacity; // Max number of guests it can accomodate
	int category; // It's not really important but various categories like
					// family, dinner, couple is required
	boolean inOccupied; // whether this table is currently occupied or not.

	// this variable is trickier. We divided our 24 hours into 48 halves.
	// Whenever we book this table set true is occupied for that specified half
	// hour. This variable is mainly used to checking availability of this table
	// during any specific time.
	boolean[] occupiedHalfHours = new boolean[48];

	public Table() {
		// initialize all the variables }
	}

	/**
	 * checks whether this table is reserved for this time window or not
	 */
	public boolean isReserved(long start, long end) {
		return false;
	}

	public boolean isFitToRequest(int totalNum, long start, long end) {
		if (seatingCapacity >= totalNum) {
			return isReserved(start, end);
		}
		return false;
	}

}

/**
 * can be Guest or employee
 * 
 * @author shaskant
 *
 */
class Person {
	String name;
	String phone;
}

class Guest extends Person {
	String emailId;
	String address;
}

class Restaurant {
	int restaurantID; // restaurant ID
	long timeOfStarting; // opening time
	long timeOfClosing; // closing time
	int totalSeats; // total seats that this reservation has
	ReservationSystem system;

	public void initalizeReservationSystem(long openTime, long closingTime,
			int seats) {
	}
}
