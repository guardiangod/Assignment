package com.singtel.a2;

public class FlyWithWings implements FlyBehaviour{
	
	@Override
	public String fly(){
		System.out.println("I am flying");
		return "I am flying";
	}

}
