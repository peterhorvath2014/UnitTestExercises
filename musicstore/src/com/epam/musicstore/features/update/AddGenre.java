package com.epam.musicstore.features.update;

import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.epam.musicstore.entity.Genre;

public class AddGenre {

	private EntityManagerFactory emf;
	private EntityManager em;

	public AddGenre() {
		emf = Persistence.createEntityManagerFactory("com.epam.musicstore.select");
		em = emf.createEntityManager();
	}

	public static void main(String[] args) {
		new AddGenre().run();
	}

	private void run() {
		try {
			em.getTransaction().begin();
			Genre newGenre = new Genre();
			newGenre.setName("genre" + new Random().nextInt(100000));
			em.persist(newGenre);
			if (em.getTransaction().isActive()) {
				em.getTransaction().commit();
			}
			System.out.println(newGenre + " saved");

		} catch (Exception ex) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}

		em.close();
		emf.close();
	}

}