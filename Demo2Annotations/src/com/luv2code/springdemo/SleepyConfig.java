package com.luv2code.springdemo;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:mylogger.properties")
//@ComponentScan("com.luv2code.springdemo")
public class SleepyConfig {

	// define bean for Sad fortune service
	@Bean	
	public FortuneService sleepyFortuneService() {
		return new SleepyFortuneService();
	}
	
	// define bean for our swim coach and inject dependency
	@Bean
	public Coach cricketCoach() {
		return new CricketCoach(sleepyFortuneService());
	}
	
	@Value("${root.logger.level}")
	private String rootLoggerLevel;
	
	@Value("${printed.logger.level}")
	private String printedLoggerLevel;
	
	@PostConstruct
	public void initLogger() {

		//parse levels
		Level rootLevel = Level.parse(rootLoggerLevel);
		Level printedLevel = Level.parse(printedLoggerLevel);
		
		//get logger for App context
		Logger applicationContextLogger = Logger.getLogger(AnnotationConfigApplicationContext.class.getName());
		
		// get parent logger
		Logger loggerParent = applicationContextLogger.getParent();
		
		// get root logging level
		loggerParent.setLevel(rootLevel);
		
		//set up console handler
		ConsoleHandler consoleHandler = new ConsoleHandler();
		consoleHandler.setLevel(printedLevel);
		consoleHandler.setFormatter(new SimpleFormatter());
		
		//add handler to the logger
		loggerParent.addHandler(consoleHandler);
		
		
		//
	}
}
