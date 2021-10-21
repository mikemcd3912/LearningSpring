package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class FetchJoinDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			// start a transaction
			session.beginTransaction();

			// get the instructor from db
			int theId =1;
			Instructor tempInstructor = session.get(Instructor.class, theId);
		
			System.out.println("Luv2Code: Getting courses for: "+ tempInstructor);
			System.out.println("Luv2Code: Courses: "+tempInstructor.getCourses());
			//commit transaction
			session.getTransaction().commit();
			
			// close the session
			session.close();
			System.out.println("\nluv2code: Session is now closed!\n");
		
			// since courses are lazy loaded, this should fail
			// get the course List
			System.out.println("Luv2Code: Courses: "+tempInstructor.getCourses());
			
			System.out.println("Luv2Code: Done!!");
			
		} finally {
			factory.close();
		}
		

	}

}
