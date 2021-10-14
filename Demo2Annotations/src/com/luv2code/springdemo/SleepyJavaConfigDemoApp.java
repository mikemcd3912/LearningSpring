package com.luv2code.springdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SleepyJavaConfigDemoApp {

	public static void main(String[] args) {
		// Get the Context from the config File
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SleepyConfig.class);
		
		// Get the bean from the spring container
		Coach sleepy = context.getBean("cricketCoach", CricketCoach.class);
		
		//use the method on the bean
		System.out.println(sleepy.getDailyFortune());
		System.out.println(sleepy.getDailyWorkout());
		
		//close the bean context
		context.close();
	}

}
