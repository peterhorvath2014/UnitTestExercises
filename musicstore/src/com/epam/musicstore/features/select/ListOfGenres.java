package com.epam.musicstore.features.select;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import com.epam.musicstore.entity.Genre;

public class ListOfGenres {

	private EntityManagerFactory emf;
	private EntityManager em;

	public ListOfGenres() {
		emf = Persistence.createEntityManagerFactory("com.epam.musicstore.select");
		em = emf.createEntityManager();
	}

	public static void main(String[] args) {
		new ListOfGenres().run();
	}

	private void run() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Genre> cq = cb.createQuery(Genre.class);
		cq.from(Genre.class);
		TypedQuery<Genre> query = em.createQuery(cq);
		List<Genre> genres = query.getResultList();
		printEntity(genres);

		em.close();
		emf.close();
	}

	private void printEntity(List<Genre> genres) {
		for (Genre genre : genres) {
			System.out.println(genre);
		}

	}
}