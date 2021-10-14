package com.luv2code.springdemo;

public class CricketCoach implements Coach {

	private FortuneService sleepyFortuneService;
	
	public CricketCoach(FortuneService theSleepyFortuneService) {
		sleepyFortuneService = theSleepyFortuneService;
	}
	
	@Override
	public String getDailyWorkout() {
		return "Do the Cricket!!";
	}

	@Override
	public String getDailyFortune() {
		
		return sleepyFortuneService.getFortune();
	}

}
