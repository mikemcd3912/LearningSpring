package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Employee;

public class Practice8Demo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Employee.class)
								.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			Employee temp1 = new Employee("one", "derp", "Google");
			Employee temp2 = new Employee("two", "tator", "Google");
			Employee temp3 = new Employee("three", "groot", "Twitter");
			Employee temp4 = new Employee("four", "plop", "Facebook");
			Employee temp5 = new Employee("five", "BigTuna", "Facebook");
			
			session.beginTransaction();
			System.out.println("Loading Employees...");

			session.save(temp1);
			session.save(temp2);
			session.save(temp3);
			session.save(temp4);
			session.save(temp5);

			session.getTransaction().commit();
			System.out.println("Employees Loaded!");
			
			System.out.println("Fetching Employee 2...");
			session = factory.getCurrentSession();
			session.beginTransaction();
			List<Employee> tempEmployees = session.createQuery("from Employee s where s.id=2").getResultList();
			printEmployees(tempEmployees);

			session.getTransaction().commit();

			session= factory.getCurrentSession();
			session.beginTransaction();
			tempEmployees = session.createQuery("from Employee where company='Google'").getResultList();
			printEmployees(tempEmployees);
			session.getTransaction().commit();
			
			
			session = factory.getCurrentSession();
			session.beginTransaction();
			session.createQuery("delete from Employee where id=4").executeUpdate();
			session.getTransaction().commit();
		}finally {
			factory.close();
		}

	}

	private static void printEmployees(List<Employee> tempEmployees) {
		for(Employee guy : tempEmployees) {
			System.out.println(guy);
		}
	}

}
