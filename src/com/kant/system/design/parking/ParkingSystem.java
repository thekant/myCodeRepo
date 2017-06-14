/**
 * 
 */
package com.kant.system.design.parking;

import java.security.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * TODO add null checks
 * 
 * @author shaskant
 *
 */
public class ParkingSystem {
	List<ParkingSlot> availableSlots;
	Map<Certificate, ParkingSlot> usedSpace;

	public double calculateParkingCharges(Certificate certificate) {
		return 0;
	}

	public ParkingSlot findParkingLot(Certificate certificate) {
		return usedSpace.get(certificate);
	}

	public void unParkVehicle(Certificate certi) {
		findParkingLot(certi).unPark(certi);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}

/**
 * auto increment
 * 
 * @author shaskant
 *
 */
class Certificate {
	String keyID;
	String licensePlate;
    long time;
    
	/**
	 * @param keyID
	 * @param licensePlate
	 */
	public Certificate(String keyID, String licensePlate) {
		super();
		this.keyID = keyID;
		this.licensePlate = licensePlate;
		time =System.currentTimeMillis();
	}

	/**
	 * @return the licensePlate
	 */
	public String getLicensePlate() {
		return licensePlate;
	}

	/**
	 * @param licensePlate
	 *            the licensePlate to set
	 */
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getKeyID() {
		return keyID;
	}

	public void setKeyID(String keyID) {
		this.keyID = keyID;
	}

	@Override
	public int hashCode() {
		if (keyID == null)
			return super.hashCode();
		return keyID.hashCode();
	}
}

enum VehicleSize {
	SMALL, MEDIUM, BIG;
}

class Vehicle {
	String licensePlate;
	VehicleSize vehicleSize;

	public VehicleSize getVehicleSize() {
		return vehicleSize;
	}

	public void setVehicleSize(VehicleSize vehicleSize) {
		this.vehicleSize = vehicleSize;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
}

/**
 * extends to Regular and Hadicapp parking slots.
 * 
 * @author shaskant
 *
 */
abstract class ParkingSlot {
	protected VehicleSize size;
	protected double hourlyRate;

	public abstract double getAmount();

	protected boolean available;
	protected Certificate certificate;

	public Certificate parkAtSpot(Vehicle vehicle) {
		setAvailable(true);
		setCertificate(new Certificate(vehicle.getLicensePlate(), null));
		return getCertificate();
	}

	public boolean unPark(Certificate certi) {
		if (certificate.equals(certi)) {
			setAvailable(true);
			certificate = null;
			System.out.println("vehicle un parked");
			return true;
		}
		return false;
	}

	public Certificate getCertificate() {
		return certificate;
	}

	public void setCertificate(Certificate certificate) {
		this.certificate = certificate;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public VehicleSize getSize() {
		return size;
	}

	public void setSize(VehicleSize size) {
		this.size = size;
	}

}
