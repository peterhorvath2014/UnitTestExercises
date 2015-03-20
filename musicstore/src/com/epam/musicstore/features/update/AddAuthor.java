package com.epam.musicstore.features.update;

import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.epam.musicstore.entity.Author;

public class AddAuthor {

	private EntityManagerFactory emf;
	private EntityManager em;

	public AddAuthor() {
		emf = Persistence.createEntityManagerFactory("com.epam.musicstore.select");
		em = emf.createEntityManager();
	}

	public static void main(String[] args) {
		new AddAuthor().run();
	}

	private void run() {
		try {
			em.getTransaction().begin();
			Author newAuthor = new Author();
			newAuthor.setName("Author" + new Random().nextInt(100000));
			em.persist(newAuthor);
			
			if (em.getTransaction().isActive()) {
				em.getTransaction().commit();
			}
			
			System.out.println(newAuthor + " saved");

		} catch (Exception ex) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}

		em.close();
		emf.close();
	}

}