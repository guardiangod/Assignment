package com.singtel.d;

import com.singtel.d.Animal;

public class Caterpillar extends Animal implements FlyBehaviour {

	private FlyBehaviour flyBehaviour;
	private SoundBehaviour soundBehaviour;

	public void setFlyBehaviour(FlyBehaviour flyBehaviour) {
		this.flyBehaviour = flyBehaviour;
	}

	public void setSoundBehaviour(SoundBehaviour soundBehaviour) {
		this.soundBehaviour = soundBehaviour;
	}

	public void performAction(){
	  flyBehaviour.fly();
	  soundBehaviour.sound();
	}

	@Override
	public String fly() {
		System.out.println("Caterpillar can not fly");
		return "Caterpillar can not fly";
	}
	
}
