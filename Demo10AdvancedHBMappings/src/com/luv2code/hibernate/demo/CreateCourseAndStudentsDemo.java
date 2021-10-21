package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;

public class CreateCourseAndStudentsDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			// start a transaction
			session.beginTransaction();

			// create a course
			Course tempCourse = new Course("Jogging, who bother? A case for Cardio");
			
			 // save the course
			System.out.println("Saving the course" + tempCourse);
			session.save(tempCourse);
			System.out.println("Saved the Course: " + tempCourse);
			
			// create the students
			Student tempStudent1 = new Student("Tater", "Tot", "TaterTot@Mike.com");
			Student tempStudent2 = new Student("Colt", "Kent", "Colt@Mike.com");
			
			// add students to the course
			tempCourse.addStudent(tempStudent1);
			tempCourse.addStudent(tempStudent2);
			
			// save the students
			System.out.println("Saving the Students");
			session.save(tempStudent1);
			session.save(tempStudent2);
			System.out.println("Saved the Students: "+tempCourse.getStudents());
			
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!!");
			
		} finally {
			factory.close();
		}
		

	}

}
