package com.singtel.a4;

public class DuckSound implements SoundBehaviour {
	
	@Override
	public String sound(){
		System.out.println("Quack, quack");
		return "Quack, quack";
	}

}
