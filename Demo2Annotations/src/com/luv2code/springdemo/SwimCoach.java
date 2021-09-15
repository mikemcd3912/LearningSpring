package com.luv2code.springdemo;

import org.springframework.stereotype.Component;

@Component
public class SwimCoach implements Coach {

	@Override
	public String getDailyWorkout() {
		return "Swim a Mile. Stroke! Stroke! Stroke!";
	}

}
