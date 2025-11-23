package com.singtel.a3;

import com.singtel.a3.SoundBehaviour;

public class RoosterSound implements SoundBehaviour{
	public String sound(){
		System.out.println("Cock-a-doodle-doo");
		return "Cock-a-doodle-doo";
	}
}
