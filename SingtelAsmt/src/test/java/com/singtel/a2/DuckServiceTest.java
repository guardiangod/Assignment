package com.singtel.a2;

import org.junit.Test;

import com.singtel.a2.Chicken;
import com.singtel.a2.ChickenSound;
import com.singtel.a2.ChickenSwim;
import com.singtel.a2.Duck;
import com.singtel.a2.DuckSound;
import com.singtel.a2.DuckSwim;
import com.singtel.a2.FlyWithWings;
import com.singtel.a2.FlyWithoutWings;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class DuckServiceTest {

	@Test
	public void test(){
		Duck duck = new Duck();
		duck.setFlyBehaviour(new FlyWithWings());
		Assert.assertEquals("I am flying",duck.performFly());

		duck.setSoundBehaviour(new DuckSound());
		Assert.assertEquals("Quack, quack",duck.performSound());

		duck.setSwimBehaviour(new DuckSwim());
		Assert.assertEquals("Duck can Swim",duck.performSwim());

		//Chicken
		Chicken chicken = new Chicken();
		chicken.setFlyBehaviour(new FlyWithoutWings());
		Assert.assertEquals("I am not flying",chicken.performFly());

		chicken.setSoundBehaviour(new ChickenSound());
		Assert.assertEquals("Cluck, cluck",chicken.performSound());

		chicken.setSwimBehaviour(new ChickenSwim());
		Assert.assertEquals("Chicken can't swim",chicken.performSwim());
	}

}
