package com.epam.musicstore.features.select;

import java.util.List;

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

public class AuthorSearch {
	
	private EntityManagerFactory emf;
	private EntityManager em;
	private final static String SEARCH_STRING = "Rach";

	public AuthorSearch() {
		emf = Persistence
				.createEntityManagerFactory("com.epam.musicstore.select");
		em = emf.createEntityManager();
	}
	public static void main(String[] args) {
		new AuthorSearch().run();
	}
	
	private void run() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Author> cq = cb.createQuery(Author.class);
		Root<Author> author = cq.from(Author.class);
		
		Path<String> name = author.get(Author_.name);
		cq.where(cb.like(name, "%" + SEARCH_STRING  + "%"));
		TypedQuery<Author> query = em.createQuery(cq);
		List<Author> authors = query.getResultList();
		printEntity(authors);
		
		em.close();
		emf.close();
	}
	private void printEntity(List<Author> authors) {
		for (Author author: authors) {
			System.out.println(author);
		}
		
	}
}