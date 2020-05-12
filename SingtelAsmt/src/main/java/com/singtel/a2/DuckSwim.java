package com.singtel.a2;

public class DuckSwim implements SwimBehaviour{

	@Override
	public String swim() {
		System.out.println("Duck can Swim");
		return "Duck can Swim";
	}

}
