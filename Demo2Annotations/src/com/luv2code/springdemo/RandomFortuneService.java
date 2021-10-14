package com.luv2code.springdemo;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class RandomFortuneService implements FortuneService {

	
	// create an array of Strings
	private String[] data = {
			"Beware of the wolf in Sheep's clothing",
			"Diligence is the mother of good luck",
			"The Journey is the reward"
	};
	
	// create a random number generator
	private Random myrandom = new Random();
	
	
	@Override
	public String getFortune() {
		// pick a random string from the array
		int index = myrandom.nextInt(data.length);
		String theFortune = data[index];
		return theFortune;
	}

}
