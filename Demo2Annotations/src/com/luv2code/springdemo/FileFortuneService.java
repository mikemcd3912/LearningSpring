package com.luv2code.springdemo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class FileFortuneService implements FortuneService {

	public List<String> fortunes;
	public File input = new File("C:\\Users\\Mikem\\Documents\\GitHub\\LearningSpring\\Demo2Annotations\\src\\com\\luv2code\\springdemo\\fortunes.txt");
	public Random number = new Random();
	
	
	@Override
	public String getFortune() {
		return fortunes.get(number.nextInt(fortunes.size()));
	}
	
	@PostConstruct
	public List<String> loadFortunes() throws FileNotFoundException {
		Scanner reader = new Scanner(input);
		fortunes = new ArrayList<String>();
		reader.nextLine();
		while(reader.hasNextLine()) {
			String line = reader.nextLine();
			fortunes.add(line);
		}
		reader.close();
		return fortunes;
	}

}
