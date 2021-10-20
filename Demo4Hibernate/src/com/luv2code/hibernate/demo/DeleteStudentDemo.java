package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

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
			
			// delete the student
			// System.out.println("Deleting student: "+myStudent);
			// session.delete(myStudent);
			
			// delete the student w/ ID=2
			System.out.println("Deleting student id=2");
			session.createQuery("delete from Student where id=2").executeUpdate();
			
			// Commit the transaction
			session.getTransaction().commit();
			
			
			System.out.println("Done!!");
			
		} finally {
			factory.close();
		}
		

	}

}
