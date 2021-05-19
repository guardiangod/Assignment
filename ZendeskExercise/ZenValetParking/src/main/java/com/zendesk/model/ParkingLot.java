package com.zendesk.model;

import java.text.MessageFormat;

import com.zendesk.enums.VehicleType;

public class ParkingLot {
	
	private VehicleType vehicleType;
	private int lotNumber;
	private String vehicleNumber;
	private String enterTime;
	private String exitTime;
	
	public ParkingLot(VehicleType vehicleType, int lotNumber) {
		this.vehicleType = vehicleType;
		this.lotNumber = lotNumber;
	}
	
	public VehicleType getVehicleType() {
		return vehicleType;
	}
	
	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}
	
	public int getLotNumber() {
		return lotNumber;
	}
	
	public void setLotNumber(int lotNumber) {
		this.lotNumber = lotNumber;
	}
	
	public String getVehicleNumber() {
		return vehicleNumber;
	}
	
	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}
	
	public String getEnterTime() {
		return enterTime;
	}
	
	public void setEnterTime(String enterTime) {
		this.enterTime = enterTime;
	}
	
	public String getExitTime() {
		return exitTime;
	}
	
	public void setExitTime(String exitTime) {
		this.exitTime = exitTime;
	}
	
	public boolean isAvailable() {
		return (this.vehicleNumber == null || this.vehicleNumber.isEmpty());
	}
	
	public String getLotDisplayName() {
		return MessageFormat.format("{0}Lot{1}", this.vehicleType.getDisplayName(), this.lotNumber);
	}
	
	public void release() {
		this.vehicleNumber = "";
		this.enterTime = "";
		this.exitTime = "";
	}
	
}
