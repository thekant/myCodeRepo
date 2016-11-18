package com.kant.general;

/**
 * 
 * @author shashi
 * 
 */
public class BitwiseOnOffCheck {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int carGarage = 218;//upto 2^8
		int garagSize = 8;// 8 bits of a int

		// check status of each car
		showBitStatus(carGarage, garagSize);

		// flipover using std tech
		int newcarstatus = flipOverStd(carGarage, 1);
		showBitStatus(newcarstatus, garagSize);

		newcarstatus = flipOverStd(newcarstatus, 1);
		showBitStatus(newcarstatus, garagSize);

		// flipover using xor's
		newcarstatus = flipOverUsingXor(newcarstatus, 1);
		showBitStatus(newcarstatus, garagSize);
	}

	/**
	 * Shows each bit status and prints the output
	 * 
	 * @param garage
	 * @param garageSize
	 */
	public static void showBitStatus(int garage, int garageSize) {
		System.out.print("->" + garage + " \"[");
		for (int x = garageSize; x >= 0; x--) {
			if (checkBitStatus(garage, x))
				System.out.print("1");
			else
				System.out.print("0");
		}
		System.out.println("]\"");
	}

	/**
	 * checks particular bit status
	 * 
	 * @param garage
	 * @param car_num
	 * @return
	 */
	public static boolean checkBitStatus(int garage, int car_num) {
		return ((garage & (1 << car_num)) > 0);

	}

	public static int setBitOn(int garage, int car_num) {
		return garage | (1 << car_num);
	}

	public static int setBitOff(int garage, int car_num) {
		return garage & ~(1 << car_num);
	}

	public static int flipOverStd(int garage, int car_num) {
		if (checkBitStatus(garage, car_num))
			return setBitOff(garage, car_num);
		else
			return setBitOn(garage, car_num);
	}

	// no effort flipover
	public static int flipOverUsingXor(int garage, int car_num) {
		return garage ^ (1 << car_num);
	}

}
