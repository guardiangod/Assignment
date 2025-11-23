package com.zendesk.service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import com.zendesk.enums.EventType;
import com.zendesk.enums.VehicleType;
import com.zendesk.model.ParkingEvent;
import com.zendesk.model.ParkingLot;
import com.zendesk.parser.ArgumentParser;
import com.zendesk.util.Utils;

public class ValetParkingService {

	private static final String MSG_REJECT = "Reject";
	
	private ArgumentParser inputArgs;
	private List<ParkingLot> parkingLots;
	
	public ValetParkingService(ArgumentParser inputArgs) {
		this.inputArgs = inputArgs;
		// initialize parking lots list
		parkingLots = new ArrayList<>();
		for (int i=0; i < inputArgs.getNbLots().size(); i++) {
			if (i >= VehicleType.count())
				break;
			// initialize a number of parking lots
			// which are reserved for a dedicated vehicle type
			VehicleType vType = VehicleType.getVehicleTypeByIndex(i);
			int lotNo = 1;
			while (lotNo <= inputArgs.getNbLots().get(i)) {
				parkingLots.add(new ParkingLot(vType, lotNo));
				lotNo++;
			}
		}
	}
	
	/**
	 * Manages parking spaces and handles vehicles entering/exiting valet park
	 * @return operation results
	 */
	public String manageParking() {
		List<String> results = new ArrayList<>();
		for (ParkingEvent event : inputArgs.getEvents()) {
			String result = "";
			
			if (event.getEventType() == EventType.ENTER) {
				result = enterValetPark(event.getVehicleType(), event.getVehicleNumber(), event.getTimestamp());
			}
			else if (event.getEventType() == EventType.EXIT) {
				result = exitValetPark(event.getVehicleNumber(), event.getTimestamp());
			}
			else {
				// reject invalid inputs
				result = MSG_REJECT;
			}
			
			results.add(result);
		}
		
		return String.join(System.lineSeparator(), results);
	}
	
	/**
	 * Handles event that a vehicle is turning in valet park
	 * @param vehicleType
	 * @param vehicleNumber
	 * @param enterTime
	 * @return the name of the lot being occupied by vehicle if it is accepted, otherwise Reject message
	 */
	private String enterValetPark(VehicleType vehicleType, String vehicleNumber, String enterTime) {
		// find available space for corresponding vehicle type
		int lotIndex = findAvailableLot(vehicleType);
		if (lotIndex >= 0) {
			// assign vehicle to the lot
			ParkingLot parkingLot = parkingLots.get(lotIndex);
			parkingLot.setVehicleNumber(vehicleNumber);
			parkingLot.setEnterTime(enterTime);
			
			// acknowledge the accepted parking entry
			return MessageFormat.format("Accept {0}", parkingLot.getLotDisplayName());
		}
		
		// deny the entry into park if no available lot
		return MSG_REJECT;
	}
	
	/**
	 * Handles event that a vehicle is leaving out valet park
	 * @param vehicleNumber
	 * @param exitTime
	 * @return the parking lot that vehicle is removed from and the parking fee
	 */
	private String exitValetPark(String vehicleNumber, String exitTime) {
		// find the parking lot that the vehicle will be removed from
		ParkingLot parkingLot = findLotByVehicle(vehicleNumber);
		if (parkingLot != null) {
			parkingLot.setExitTime(exitTime);
			
			// charge the parking fee to vehicle
			int charge = computeParkingFee(parkingLot);
			
			// confirm the released lot and parking fee
			String exitMsg = MessageFormat.format("{0} {1}", parkingLot.getLotDisplayName(), charge);
			
			// set the current lot to available
			parkingLot.release();
			return exitMsg;
		}
		
		// deny when wrong info were given
		return MSG_REJECT;
	}
	
	/**
	 * Finds any available parking lot for particular vehicle type
	 * @param vType	vehicle type
	 * @return index of lost if found, otherwise -1
	 */
	private int findAvailableLot(VehicleType vType) {
		return IntStream.range(0, parkingLots.size())
						.filter(i -> parkingLots.get(i).getVehicleType() == vType && parkingLots.get(i).isAvailable())
			      		.findFirst()
			      		.orElse(-1);
	}
	
	/**
	 * Finds the parking lots which is currently occupied by a specific vehicle
	 * @param vNumber vehicle number
	 * @return ParkingLot if found, otherwise null
	 */
	private ParkingLot findLotByVehicle(String vNumber) {
		return parkingLots.stream()
						  .filter(p -> !p.isAvailable() && vNumber.equalsIgnoreCase(p.getVehicleNumber()))
						  .findAny()
						  .orElse(null);
	}
	
	/**
	 * Calculates the parking fee will be charged to exiting vehicle
	 * @param pkgLot parking lot occupied by the vehicle
	 * @return integer amount without currency
	 */
	private int computeParkingFee(ParkingLot pkgLot) {
		// get total parking time rounded up to nearest hour (minimum = 1)
		int parkingHours = Utils.calculateHourDifference(pkgLot.getEnterTime(), pkgLot.getExitTime(), 1);
		// apply the rate of correspond vehicle type
		int parkingRate = pkgLot.getVehicleType().getParkingRate();
		return (parkingHours * parkingRate);
	}
}
