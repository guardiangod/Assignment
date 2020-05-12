package com.singtel.a4;

public class DogSound implements SoundBehaviour {
	
	@Override
	public String sound(){
		System.out.println("Woof, woof");
		return "Woof, woof";
	}
}
