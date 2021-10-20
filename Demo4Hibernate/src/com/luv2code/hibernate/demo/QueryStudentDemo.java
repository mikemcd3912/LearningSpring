package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			// start a transaction
			session.beginTransaction();
			
			// query students
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			
			//display students
			displayStudents(theStudents);
			
			// query students: all with last name Strange
			theStudents = session.createQuery("from Student s where s.lastName='Strange'").getResultList();
			
			
			//display students
			System.out.println("\n\nStudents with last name Strange");
			displayStudents(theStudents);
			
			
			// query students: lastName='Strange' OR firstName='king'
			theStudents = session.createQuery("from Student s where s.lastName='Strange' OR s.firstName='king'").getResultList();
			System.out.println("\n\nStudents with last name Strange or firstName of king");
			displayStudents(theStudents);
			
			// query students: where email LIKE %luv2code.com
			theStudents = session.createQuery("from Student s where s.email LIKE '%gmail.com'").getResultList();
			System.out.println("\n\nStudents with email that ends with luv2code.com");
			displayStudents(theStudents);
			
			
			
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!!");
			
		} finally {
			factory.close();
		}
		

	}

	private static void displayStudents(List<Student> theStudents) {
		for(Student item : theStudents) {
			System.out.println(item);
		}
	}

}
