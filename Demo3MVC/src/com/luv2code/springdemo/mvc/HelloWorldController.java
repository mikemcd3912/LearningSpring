package com.luv2code.springdemo.mvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/hello")
public class HelloWorldController {

	
	// need a controller method to show initial HTML form
	@RequestMapping("/showForm")
	public String showForm() {
		return "helloworld-form";
	}
	
	// need a controller method to process the HTML form
	@RequestMapping("/processForm")
	public String processForm() {
		return "helloworld";
	}
	
	// new controller method to read form data and add data to the model
	@RequestMapping("/processFormTwo")
	public String letsShoutDude(HttpServletRequest request, Model model) {
		
		// Read the request parameter from the HTML Form
		String theName = request.getParameter("studentName");
		
		//Convert the data to all caps
		theName = theName.toUpperCase();
		
		//create the message
		String result = "Yo! "+theName;
		
		//Add the message to the model
		model.addAttribute("message", result);
		
		return "helloworld";
	}
	
	@RequestMapping("/processFormThree")
	public String processFormVersionThree(@RequestParam("studentName") String theName, Model model) {
		
		//Convert the data to all caps
		theName = theName.toUpperCase();
		
		//create the message
		String result = "Hey Buddy! "+theName;
		
		//Add the message to the model
		model.addAttribute("message", result);
		
		return "helloworld";
	}
	
	
}
