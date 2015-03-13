package com.epam.jpademo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.epam.jpademo.entity.Employee;
import com.epam.jpademo.entity.Gender;
import com.epam.jpademo.entity.Position;

public class JpaEmployeeCreate {

	private EntityManager em;
	private EntityManagerFactory emf;

	public JpaEmployeeCreate() {
		emf = Persistence
				.createEntityManagerFactory("com.epam.lab.solution.jpa.employeePersistenceUnit");
		em = emf.createEntityManager();
	}

	private void run() {
		try {
			em.getTransaction().begin();
			
			Position assistant = new Position("assistant", 800);
			em.persist(assistant);
			
			Position developer = new Position("developer", 1500);
			em.persist(developer);
			
			Position manager = new Position("manager", 3000);
			em.persist(manager);
			
			{
				Employee e = new Employee();
				e.setFirstName("Elek");
				e.setLastName("Teszt");
				e.setGender(Gender.M);
				e.setPosition(assistant);
				e.setYearOfEmplyment(2010);
				em.persist(e);
			}
			
			{
				Employee e = new Employee();
				e.setFirstName("Béla");
				e.setLastName("Végh");
				e.setGender(Gender.M);
				e.setPosition(manager);
				e.setYearOfEmplyment(2010);
				em.persist(e);
			}
			
			{
				Employee e = new Employee();
				e.setFirstName("Manci");
				e.setLastName("Ka");
				e.setGender(Gender.F);
				e.setPosition(developer);
				e.setYearOfEmplyment(1990);
				em.persist(e);
			}
			if (em.getTransaction().isActive()) {
				em.getTransaction().commit();
			}
		} catch (Exception ex) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}
		
		em.close();
		emf.close();
	}

	public static void main(String[] args) {
		new JpaEmployeeCreate().run();

	}
}
