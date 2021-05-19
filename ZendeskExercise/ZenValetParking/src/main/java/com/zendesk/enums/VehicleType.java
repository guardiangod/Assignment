package com.zendesk.enums;

import java.util.Arrays;

public enum VehicleType {
	CAR("Car", 2),
	MOTORCYCLE("Motorcycle", 1);
	
	private String displayName;
	private int parkingRate; // parking charge per hour
	
	VehicleType(String name, int rate) {
		this.displayName = name;
		this.parkingRate = rate;
	}
	
	public String getDisplayName() {
		return displayName;
	}
	
	public int getParkingRate() {
		return parkingRate;
	}
	
	private static VehicleType[] vehicleTypes = VehicleType.values();
	
	public static int count() {
		return vehicleTypes.length;
	}
	
    public static VehicleType getVehicleTypeByIndex(int i) {
        return vehicleTypes[i];
    }
    
    public static VehicleType getVehicleTypeByName(String name) {
    	return Arrays.stream(vehicleTypes)
    				 .filter(v -> v.getDisplayName().equalsIgnoreCase(name))
    				 .findAny()
    				 .orElse(null);
    }
}
