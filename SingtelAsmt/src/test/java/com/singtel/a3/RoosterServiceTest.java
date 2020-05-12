package com.singtel.a3;

import org.junit.Test;

import com.singtel.a3.FlyWithWings;
import com.singtel.a3.Rooster;
import com.singtel.a3.RoosterSound;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class RoosterServiceTest {
	
	@Test
	public void testRoosterService(){
		Rooster rooster = new Rooster();
		rooster.setFlyBehaviour(new FlyWithWings());
		Assert.assertEquals("I am flying",rooster.performFly());
		
		rooster.setSoundBehaviour(new RoosterSound());
		Assert.assertEquals("Cock-a-doodle-doo",rooster.performSound());
		
		rooster.walk();
	}

}
