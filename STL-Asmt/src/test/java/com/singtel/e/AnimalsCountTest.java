package com.singtel.e;

import org.junit.Before;
import org.junit.Test;

import com.singtel.e.Animal;
import com.singtel.e.Bird;
import com.singtel.e.Butterfly;
import com.singtel.e.Cat;
import com.singtel.e.Chicken;
import com.singtel.e.Clownfish;
import com.singtel.e.Dog;
import com.singtel.e.Dolhpin;
import com.singtel.e.Duck;
import com.singtel.e.Fish;
import com.singtel.e.Frog;
import com.singtel.e.Parrot;
import com.singtel.e.Rooster;
import com.singtel.e.Shark;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class AnimalsCountTest {

	Animal[] animals;
	int flyCount = 0;
	int swimCount = 0;
	int walkCount = 0;
	int singCount = 0;

	@Before
	public void setUp() throws Exception {
		animals = new Animal[]{
				new Bird(),
				new Duck(),
				new Chicken(),
				new Rooster(),
				new Parrot(),
				new Fish(),
				new Shark(),
				new Clownfish(),
				new Dolhpin(),
				new Frog(),
				new Dog(),
				new Butterfly(),
				new Cat()
		};	

	}

	@Test
	public void testFlyAnimalCount()
	{
		for(int i = 0 ; i< animals.length; i++){
			if(animals[i].fly()){
				flyCount = flyCount + 1;
			}

		}
		Assert.assertEquals(5, flyCount);
	}


	@Test
	public void testSingAnimalCount()
	{
		for(int i = 0 ; i< animals.length; i++){
			if(animals[i].sing()){
				singCount = singCount + 1;
			}
		}
		Assert.assertEquals(2, singCount);
	}


	@Test
	public void testWalkAnimalCount()
	{
		for(int i = 0 ; i< animals.length; i++){

			if(animals[i].walk()){
				walkCount = walkCount + 1;
			}

		}
		Assert.assertEquals(8, walkCount);
	}

	@Test
	public void testSwimAnimalCount()
	{
		for(int i = 0 ; i< animals.length; i++){

			if(animals[i].swim()){
				swimCount = swimCount + 1;
			}
		}
		Assert.assertEquals(7, swimCount);
	}


}
