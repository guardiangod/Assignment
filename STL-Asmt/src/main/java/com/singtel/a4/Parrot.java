package com.singtel.a4;

public class Parrot {
	
	//implementing using Composition with abstraction
	/**The composition is another mechanism provided by OOP for reusing implementation.

	composition allows us to model objects that are made up of other objects, 
	thus defining a “has-a” relationship between them.*/
	
	private SoundBehaviour soundBehaviour;
	
	public void setSoundBehaviour(SoundBehaviour soundBehaviour) {
		this.soundBehaviour = soundBehaviour;
	}

	public String performSound(){
		return soundBehaviour.sound();
	}
	

}
