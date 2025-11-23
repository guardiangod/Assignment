package com.singtel.d;

import org.junit.Test;

import com.singtel.d.Butterfly;
import com.singtel.d.Caterpillar;

public class ButterflyServiceTest {

	@Test
	public void testFishService(){
		Caterpillar caterpillar = new Caterpillar();
		caterpillar.walk();
		caterpillar.fly();

		caterpillar.setFlyBehaviour(new Butterfly());
		caterpillar.setSoundBehaviour(new Butterfly());

		caterpillar.performAction();
	}
}

