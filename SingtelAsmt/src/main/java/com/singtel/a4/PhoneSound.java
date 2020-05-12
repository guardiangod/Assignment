package com.singtel.a4;

public class PhoneSound implements SoundBehaviour {
	
	@Override
	public String sound(){
		System.out.println("Tring tring");
		return "Tring tring";
	}

}
