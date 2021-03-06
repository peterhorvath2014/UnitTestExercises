package com.epam.lab.solution.jdbc.lab03.employee;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class EmployeeListLimit {

	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;

	private void run(String[] args) {
		if (args.length != 1) {
			System.err.println("Error: must provide a number as the limit value.");
			return;
		}

		final int limit = Integer.parseInt(args[0]);

		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("com.epam.lab.solution.jpa.employeePersistenceUnit");
			entityManager = entityManagerFactory.createEntityManager();

			final Query query = entityManager.createQuery("from Employee e where e.position.monthlySalary > :monthlySalaryLimit order by e.position.monthlySalary desc, e.lastName");
			query.setParameter("monthlySalaryLimit", limit);
			final List<Employee> results = query.getResultList();

			for (final Employee employee : results) {
				System.out.println(employee);
			}

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
		new EmployeeListLimit().run(args);
	}
}
