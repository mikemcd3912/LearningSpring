package com.luv2code.aopdemo.aspect;

import java.util.List;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
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

	private static Logger myLogger = Logger.getLogger(MyDemoLoggingAspect.class.getName());
	
	@Around("execution(* com.luv2code.aopdemo.service.*.getFortune(..))")
	public Object aroundGetFortune(
			ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {
		
		// print out which method we are advising on
		String method= theProceedingJoinPoint.getSignature().toShortString();
		myLogger.info("\n=====>>> Executing @Around on method "+method);
		
		// get begin timestamp
		long begin = System.currentTimeMillis();
		
		// now, execute the method
		Object result = null;
		
		try {
			result = theProceedingJoinPoint.proceed();
		} catch (Exception e) {
			// Log the expression
			myLogger.warning(e.getMessage());
			
			// rethrow Exception
			throw e;
		}
		
		// get end timestamp
		long end = System.currentTimeMillis();
		
		// compute duration and display
		long duration = end-begin;
		myLogger.info("\n====> Duration: "+duration / 1000.0 + " Seconds");
		
		return result;
	}
	
	
	@After("execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
	public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint) {
		// print out which method we are advising on
		String method= theJoinPoint.getSignature().toShortString();
		myLogger.info("\n=====>>> Executing @After(Finally) on method "+method);
	}
	
	
	@AfterThrowing(
			pointcut="execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
			throwing="theExc")
	public void afterThrowingFindAccountsAdvice(
			JoinPoint theJoinPoint, Throwable theExc) {
		// print out which method we are advising on
		String method= theJoinPoint.getSignature().toShortString();
		myLogger.info("\n=====>>> Executing @AfterThrowing on method "+method);
			
		// log the exception
		myLogger.info("\n=====>>> The exception is: " + theExc);
	}
	
	// add @AfterReturning on the findAccounts method
	@AfterReturning(
			pointcut="execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
			returning="result")
	public void afterReturningFindAccountsAdvice(
			JoinPoint theJoinPoint, List<Account> result) {
	
		// print out which method we are advising on
		String method= theJoinPoint.getSignature().toShortString();
		myLogger.info("\n=====>>> Executing @AfterReturning on method "+method);
		
		// print out the results of the method call
		myLogger.info("\n====>>> result is: "+result);
		
		// let's post-process the data, midify before returning
		
		// convert the account names to uppercase
		convertAccountNamesToUpperCase(result);
		
		// print out the results of the method call after modification
		myLogger.info("\n====>>> Modified result is: "+result);
		
	}
	
	private void convertAccountNamesToUpperCase(List<Account> result) {
		for(Account tempAcct : result) {
			tempAcct.setName(tempAcct.getName().toUpperCase());
		}
		
	}

	@Before("com.luv2code.aopdemo.aspect.AopExpressions.forDaoPackageNoGetterSetter()")
	public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
		myLogger.info("\n=====>>> Executing @Before advice on method");
		
		// display the message signiture
		MethodSignature methodSig = (MethodSignature)theJoinPoint.getSignature();
		myLogger.info("Method: "+methodSig);
		
		// display the message arguments
		
		// get args
		Object[] args = theJoinPoint.getArgs();
		
		// loop through args
		for(Object arg : args) {
			myLogger.info(arg.toString());
			if(arg instanceof Account) {
				// downcast and print account specific stuff
				
				Account theAccount = (Account)arg;
				myLogger.info("account name: "+theAccount.getName());
				myLogger.info("account level: "+theAccount.getLevel());
			}
		}
	}

}
