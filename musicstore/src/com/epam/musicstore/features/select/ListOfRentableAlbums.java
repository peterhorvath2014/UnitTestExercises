package com.epam.musicstore.features.select;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.epam.musicstore.entity.Rental;
import com.epam.musicstore.entity.Rental_;

public class ListOfRentableAlbums {

	private EntityManagerFactory emf;
	private EntityManager em;

	public ListOfRentableAlbums() {
		emf = Persistence.createEntityManagerFactory("com.epam.musicstore.select");
		em = emf.createEntityManager();
	}

	public static void main(String[] args) {
		new ListOfRentableAlbums().run();
	}

	private void run() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Rental> cq = cb.createQuery(Rental.class);
		Root<Rental> rental = cq.from(Rental.class);
		
		Path<Date> out = rental.get(Rental_.out);
		
		Predicate outIsNotNull = cb.isNotNull(out);
		Predicate outIsLessThanNow = cb.lessThanOrEqualTo(out, new Date());
		
		Predicate fullWhere = cb.and(outIsNotNull, outIsLessThanNow);
		cq.where(fullWhere);
		
		
		//cq.where( || cb.lessThanOrEqualTo(out, new Date()));
		
		TypedQuery<Rental> query = em.createQuery(cq);
		List<Rental> rentals = query.getResultList();
		printEntities(rentals);

		em.close();
		emf.close();
	}

	private <T> void printEntities(List<T> entities) {
		for (T entity : entities) {
			System.out.println(entity);
		}

	}
}