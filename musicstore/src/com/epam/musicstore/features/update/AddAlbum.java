package com.epam.musicstore.features.update;

import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.epam.musicstore.entity.Album;

public class AddAlbum {

	private EntityManagerFactory emf;
	private EntityManager em;

	public AddAlbum() {
		emf = Persistence.createEntityManagerFactory("com.epam.musicstore.select");
		em = emf.createEntityManager();
	}

	public static void main(String[] args) {
		new AddAlbum().run();
	}

	private void run() {
		try {
			em.getTransaction().begin();
			Album newAlbum = new Album();
			newAlbum.setTitle("Album" + new Random().nextInt(100000));
			newAlbum.setYearOfPublish(new Random().nextInt(30) + 1980);
			em.persist(newAlbum);
			
			if (em.getTransaction().isActive()) {
				em.getTransaction().commit();
			}
			
			System.out.println(newAlbum + " saved");

		} catch (Exception ex) {
			ex.printStackTrace();
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}

		em.close();
		emf.close();
	}

}