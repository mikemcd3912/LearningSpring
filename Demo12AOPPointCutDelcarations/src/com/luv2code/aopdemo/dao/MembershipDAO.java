package com.luv2code.aopdemo.dao;

import org.springframework.stereotype.Component;

@Component
public class MembershipDAO {

	public boolean addChonkyMember() {
		System.out.println(getClass()+": Doing Stuff: adding a membership account");
		return true;
	}
	
	public void goToSleep() {
		System.out.println(getClass() + ": I'm going to sleep now...zZzZz");
	}
	
}
