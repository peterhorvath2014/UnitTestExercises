package com.epam.lab.solution.jdbc.lab03.employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EmployeeCreate {

	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;

	private void run() {
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("com.epam.lab.solution.jpa.employeePersistenceUnit");
			entityManager = entityManagerFactory.createEntityManager();

			Position manager = new Position("manager", 3000);
			Position developer = new Position("developer", 1500);
			Position assistant = new Position("assistant", 800);

			entityManager.getTransaction().begin();

			entityManager.persist(manager);
			entityManager.persist(developer);
			entityManager.persist(assistant);

			entityManager.persist(new Employee("John", "Smith", Gender.Male, manager, 2010));
			entityManager.persist(new Employee("Mary", "Taylor", Gender.Female, developer, 2012));
			entityManager.persist(new Employee("Dave", "Stuart", Gender.Male, developer, 2013));
			entityManager.persist(new Employee("Wendy", "Black", Gender.Female, assistant, 2013));

			entityManager.getTransaction().commit();
		} finally {
			if (null != entityManager) {
				entityManager.close();
			}
			if (null != entityManagerFactory) {
				entityManagerFactory.close();
			}
		}
	}

	public static void main(String[] args) {
		new EmployeeCreate().run();
	}
}
