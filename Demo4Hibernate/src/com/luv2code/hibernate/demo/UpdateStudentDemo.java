package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			int studentID =1;
			
			// new get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// retrieve the student based on ID: Primary key
			System.out.println("\n Getting student with ID: "+ studentID);
			
			Student myStudent = session.get(Student.class, studentID);
			
			System.out.println("Updating Student: "+myStudent);
			myStudent.setFirstName("Scooby");
			
			// Commit the transaction
			session.getTransaction().commit();
			
			// New for Mass update
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// update email for all students
			System.out.println("Updating email for all students");
			session.createQuery("update Student set email='foo@gmail.com'").executeUpdate();
			
			//commit the transaction
			session.getTransaction().commit();
			
			
			
			System.out.println("Done!!");
			
		} finally {
			factory.close();
		}
		

	}

}
