package com.singtel.b;

public class ClownfishBehaviour implements FishBehaviour{
	
	@Override
	public String color() {
		System.out.println("Clownfish are small and colorful");
		return "Clownfish are small and colorful";
	}

	@Override
	public String behaviour() {
		System.out.println("Clownfish make jokes");
		return "Clownfish make jokes";
	}

}
