package com.singtel.a4;

public class RoosterSound implements SoundBehaviour {
	
	@Override
	public String sound(){
		System.out.println("Cock-a-doodle-doo");
		return "Cock-a-doodle-doo";
	}

}
