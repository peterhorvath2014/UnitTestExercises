package com.epam.musicstore.features.update;

import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import com.epam.musicstore.entity.Author;
import com.epam.musicstore.entity.Author_;
import com.epam.musicstore.entity.Genre;
import com.epam.musicstore.entity.Genre_;
import com.epam.musicstore.entity.Song;

public class AddSong {

	private EntityManagerFactory emf;
	private EntityManager em;

	public AddSong() {
		emf = Persistence.createEntityManagerFactory("com.epam.musicstore.select");
		em = emf.createEntityManager();
	}

	public static void main(String[] args) {
		new AddSong().run();
	}

	private void run() {
		try {
			em.getTransaction().begin();
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Genre> cq = cb.createQuery(Genre.class);
			Root<Genre> genreFrom = cq.from(Genre.class);
			Path<Long> id = genreFrom.get(Genre_.id);
			cq.where(cb.equal(id, 1));
			TypedQuery<Genre> query = em.createQuery(cq);
			Genre genre = query.getSingleResult();
			
			CriteriaBuilder cb2 = em.getCriteriaBuilder();
			CriteriaQuery<Author> cq2 = cb2.createQuery(Author.class);
			Root<Author> authorFrom = cq2.from(Author.class);
			Path<Long> id2 = authorFrom.get(Author_.id);
			cq2.where(cb2.equal(id2, 1));
			TypedQuery<Author> query2 = em.createQuery(cq2);
			Author author = query2.getSingleResult();
			
			Song newSong = new Song();
			newSong.setTitle("Song" + new Random().nextInt(100000));
			newSong.setLength(new Random().nextInt(400) + 100);
			newSong.setGenre(genre);
			newSong.setAuthor(author);
			em.persist(newSong);
			
			if (em.getTransaction().isActive()) {
				em.getTransaction().commit();
			}
			
			System.out.println(newSong + " saved");

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