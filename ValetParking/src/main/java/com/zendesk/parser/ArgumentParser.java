package com.zendesk.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.zendesk.enums.EventType;
import com.zendesk.enums.VehicleType;
import com.zendesk.model.ParkingEvent;
import com.zendesk.util.Utils;

public class ArgumentParser {
	
	private List<Integer> nbLots = new ArrayList<>();
	private List<ParkingEvent> events = new ArrayList<>();
	
	public ArgumentParser(List<String> inputs) {
		// get number of parking lots for each type of vehicle
		Arrays.asList(inputs.get(0).split(" ")).forEach(str -> {
			nbLots.add(Utils.tryParseInt(str, 0));
		});
		
		// parse line-by-line inputs into events list
		for (int i=1; i < inputs.size(); i++) {
			events.add(parseEventAgurments(inputs.get(i)));
		}
	}
	
	private ParkingEvent parseEventAgurments(String eventEntry) {
		ParkingEvent event = new ParkingEvent();
		String[] args = eventEntry.split(" ");
		// 1. set event type
		if (args.length == 4 && EventType.ENTER.name().equalsIgnoreCase(args[0].trim())) {
			event.setEventType(EventType.ENTER);
			
			// 2. get vehicle type
			VehicleType vType = VehicleType.getVehicleTypeByName(args[1].trim());
			if (vType != null) {
				event.setVehicleType(vType);
				
				// 3. get entering vehicle number
				event.setVehicleNumber(args[2].trim());
				
				// 4. get enter time
				event.setTimestamp(args[3].trim());
			}
			else {
				// mark as invalid event if the vehicle is not in the list of acceptable types 
				event.setEventType(EventType.INVALID);
				event.setVehicleType(null);
			}
		}
		else if (args.length == 3 && EventType.EXIT.name().equalsIgnoreCase(args[0].trim())) {
			event.setEventType(EventType.EXIT);
			
			// 2. get exiting vehicle number
			event.setVehicleNumber(args[1].trim());
			
			// 3. get exit time
			event.setTimestamp(args[2].trim());
		}
		else {
			// unrecognized event type or invalid syntax
			event.setEventType(EventType.INVALID);
		}
		
		return event;
	}
	
	public List<Integer> getNbLots() {
		return nbLots;
	}

	public void setNbLots(List<Integer> nbLots) {
		this.nbLots = nbLots;
	}

	public List<ParkingEvent> getEvents() {
		return events;
	}
	
	public void setEvents(List<ParkingEvent> events) {
		this.events = events;
	}
	
}
