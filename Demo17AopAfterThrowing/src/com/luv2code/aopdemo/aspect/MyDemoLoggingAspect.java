package com.luv2code.aopdemo.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {
	
	@AfterThrowing(
			pointcut="execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
			throwing="theExc")
	public void afterThrowingFindAccountsAdvice(
			JoinPoint theJoinPoint, Throwable theExc) {
		// print out which method we are advising on
			String method= theJoinPoint.getSignature().toShortString();
			System.out.println("\n=====>>> Executing @AfterThrowing on method "+method);
			
			// log the exception
			System.out.println("\n=====>>> The exception is: " + theExc);
	}
	
	// add @AfterReturning on the findAccounts method
	@AfterReturning(
			pointcut="execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
			returning="result")
	public void afterReturningFindAccountsAdvice(
			JoinPoint theJoinPoint, List<Account> result) {
	
		// print out which method we are advising on
		String method= theJoinPoint.getSignature().toShortString();
		System.out.println("\n=====>>> Executing @AfterReturning on method "+method);
		
		// print out the results of the method call
		System.out.println("\n====>>> result is: "+result);
		
		// let's post-process the data, midify before returning
		
		// convert the account names to uppercase
		convertAccountNamesToUpperCase(result);
		
		// print out the results of the method call after modification
		System.out.println("\n====>>> Modified result is: "+result);
		
	}
	
	private void convertAccountNamesToUpperCase(List<Account> result) {
		for(Account tempAcct : result) {
			tempAcct.setName(tempAcct.getName().toUpperCase());
		}
		
	}

	@Before("com.luv2code.aopdemo.aspect.AopExpressions.forDaoPackageNoGetterSetter()")
	public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
		System.out.println("\n=====>>> Executing @Before advice on method");
		
		// display the message signiture
		MethodSignature methodSig = (MethodSignature)theJoinPoint.getSignature();
		System.out.println("Method: "+methodSig);
		
		// display the message arguments
		
		// get args
		Object[] args = theJoinPoint.getArgs();
		
		// loop through args
		for(Object arg : args) {
			System.out.println(arg);
			if(arg instanceof Account) {
				// downcast and print account specific stuff
				
				Account theAccount = (Account)arg;
				System.out.println("account name: "+theAccount.getName());
				System.out.println("account level: "+theAccount.getLevel());
			}
		}
	}

}
