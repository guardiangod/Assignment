package com.singtel.a2;

public class FlyWithoutWings implements FlyBehaviour{
	
	@Override
	public String fly(){
		System.out.println("I am not flying");
		return "I am not flying";
	}

}
