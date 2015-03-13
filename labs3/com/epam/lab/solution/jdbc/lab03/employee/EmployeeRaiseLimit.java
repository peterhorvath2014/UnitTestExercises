package com.epam.lab.solution.jdbc.lab03.employee;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class EmployeeRaiseLimit {

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

			entityManager.getTransaction().begin();

			Query query = entityManager.createQuery("from Employee e where e.position.monthlySalary < :monthlySalaryLimit");
			query.setParameter("monthlySalaryLimit", limit);
			List<Employee> employeesToDelete = query.getResultList();

			for (final Employee employee : employeesToDelete) {
				entityManager.remove(employee);
			}

			query = entityManager.createQuery("from Position p where p.monthlySalary > :monthlySalaryLimit");
			query.setParameter("monthlySalaryLimit", limit);
			List<Position> positionsToRaise = query.getResultList();

			for (final Position position : positionsToRaise) {
				position.setMonthlySalary((int) Math.round(position.getMonthlySalary() * 1.1));
			}

			query = entityManager.createQuery("select count(e) from Employee e");
			long employeeCount = (Long) query.getSingleResult();

			System.out.println(employeesToDelete.size() + " employees deleted, monthly salary is raised by 10% for " + employeeCount + " employees");

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
		new EmployeeRaiseLimit().run(args);
	}
}
