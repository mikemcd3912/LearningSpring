package com.luv2code.aopdemo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;

@Component
public class AccountDAO {

	private String name;
	private String serviceCode;
	
	// add a new method: findAccounts();
	
	public List<Account> findAccounts(boolean tripWire){
		if(tripWire) {
			throw new RuntimeException("No Soup for You!");
		}
		List<Account> myAccounts = new ArrayList<>();
	
		// Create some sample accounts
		Account temp1 = new Account("Tony", "Purple");
		Account temp2 = new Account("Tater", "Gold");
		Account temp3 = new Account("Zeek", "Joke");
		
		// add to the created list of accounts 
		myAccounts.add(temp1);
		myAccounts.add(temp2);
		myAccounts.add(temp3);
		return myAccounts;
	}
	
	public String getName() {
		System.out.println(getClass() + ": in getName()");
		return name;
	}

	public void setName(String name) {
		System.out.println(getClass() + ": in setName()");
		this.name = name;
	}

	public String getServiceCode() {
		System.out.println(getClass() + ": in getServiceCode()");
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		System.out.println(getClass() + ": in setServiceCode()");
		this.serviceCode = serviceCode;
	}

	public void addAccount(Account theAccount, boolean vipFlag) {
		System.out.println(getClass() + ": Doing my DB work: Adding an account");
	}
	
	public boolean doWork() {
		System.out.println(getClass() + ": doWork()");
		return true;
	}
}
