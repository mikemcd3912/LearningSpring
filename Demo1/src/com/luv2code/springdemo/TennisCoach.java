package com.luv2code.springdemo;

public class TennisCoach implements Coach {

	private FortuneService fortuneService;
	
	private String emailAddress;
	private String team;
	
	//create a no-arg constructor
	public TennisCoach() {
		System.out.println("TennisCoach: inside no arg constructor");
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		System.out.println("TennisCoach: inside Setter method - setEmailAddress");
		this.emailAddress = emailAddress;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		System.out.println("TennisCoach: inside Setter method - setTeam");
		this.team = team;
	}

	public void setFortuneService(FortuneService fortuneService) {
		System.out.println("TennisCoach: inside Setter method - setFortuneService");
		this.fortuneService = fortuneService;
	}

	@Override
	public String getDailyWorkout() {
		return "Play some Tennis";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}

}
