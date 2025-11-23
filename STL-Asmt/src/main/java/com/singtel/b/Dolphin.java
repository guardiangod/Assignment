package com.singtel.b;

public class Dolphin implements SwimBehaviour{

	@Override
	public String swim() {
		System.out.println("Dolphins are good swimmers");
		return "Dolphins are good swimmers";
	}

}
