package com.epam.musicstore.features.update;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import com.epam.musicstore.entity.Album;
import com.epam.musicstore.entity.Album_;
import com.epam.musicstore.entity.Rental;
import com.epam.musicstore.entity.User;
import com.epam.musicstore.entity.User_;

public class Rent {

	private EntityManagerFactory emf;
	private EntityManager em;

	public Rent() {
		emf = Persistence.createEntityManagerFactory("com.epam.musicstore.select");
		em = emf.createEntityManager();
	}

	public static void main(String[] args) {
		new Rent().run();
	}

	private void run() {
		try {
			Album foundAlbum;
			{
				CriteriaBuilder cb = em.getCriteriaBuilder();
				CriteriaQuery<Album> cq = cb.createQuery(Album.class);
				Root<Album> album = cq.from(Album.class);

				Path<Long> id = album.get(Album_.id);
				cq.where(cb.equal(id, 1));
				TypedQuery<Album> query = em.createQuery(cq);
				foundAlbum = query.getSingleResult();
			}

			User foundUser;
			{
				CriteriaBuilder cb = em.getCriteriaBuilder();
				CriteriaQuery<User> cq = cb.createQuery(User.class);
				Root<User> user = cq.from(User.class);

				Path<Long> id = user.get(User_.id);
				cq.where(cb.equal(id, 1));
				TypedQuery<User> query = em.createQuery(cq);
				foundUser = query.getSingleResult();
			}

			em.getTransaction().begin();
			Rental newRental = new Rental();
			newRental.setAlbum(foundAlbum);
			newRental.setUser(foundUser);
			newRental.setIn(new Date());
			em.persist(newRental);

			if (em.getTransaction().isActive()) {
				em.getTransaction().commit();
			}

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