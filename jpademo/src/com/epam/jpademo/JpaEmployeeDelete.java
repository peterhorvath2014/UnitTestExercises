package com.epam.jpademo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import com.epam.jpademo.entity.Employee_;
import com.epam.jpademo.entity.Employee;
import com.epam.jpademo.entity.Position_;

public class JpaEmployeeDelete {

	private static final int SALARY_LIMIT = 1000;
	private EntityManagerFactory emf;
	private EntityManager em;

	public JpaEmployeeDelete() {
		emf = Persistence.createEntityManagerFactory("com.epam.lab.solution.jpa.employeePersistenceUnit");
		em = emf.createEntityManager();
	}

	private void run() {
		/*
		 * TypedQuery<Employee> query = em.createQuery(
		 * "SELECT e FROM Employee e WHERE e.position.monthlySalary > :limit",
		 * Employee.class); query.setParameter("limit", SALARY_LIMIT);
		 * 
		 * List<Employee> employees = query.getResultList();
		 * printEmplyees(employees);
		 */

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
		Root<Employee> employee = cq.from(Employee.class);

		Path<Integer> mothlySalary = employee.get(Employee_.position).get(Position_.monthlySalary);
		cq.where(cb.greaterThan(mothlySalary, SALARY_LIMIT));
		TypedQuery<Employee> query = em.createQuery(cq);
		List<Employee> employees = query.getResultList();
		printEmployees(employees);

		em.close();
		emf.close();
	}

	private void printEmployees(List<Employee> employees) {
		for (Employee employee : employees) {
			System.out.println(employee);
		}

	}

	public static void main(String[] args) {
		new JpaEmployeeDelete().run();

	}
}
