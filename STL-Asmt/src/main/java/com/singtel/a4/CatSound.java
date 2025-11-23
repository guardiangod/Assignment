package com.singtel.a4;

public class CatSound implements SoundBehaviour {
	
	@Override
	public String sound(){
		System.out.println("Meow");
		return "Meow";
	}

}
