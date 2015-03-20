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

import com.epam.musicstore.entity.Album;
import com.epam.musicstore.entity.Album_;

public class AlbumSearch {
	
	private EntityManagerFactory emf;
	private EntityManager em;
	private final static String SEARCH_STRING = "paris";

	public AlbumSearch() {
		emf = Persistence
				.createEntityManagerFactory("com.epam.musicstore.select");
		em = emf.createEntityManager();
	}
	public static void main(String[] args) {
		new AlbumSearch().run();
	}
	
	private void run() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Album> cq = cb.createQuery(Album.class);
		Root<Album> song = cq.from(Album.class);
		
		Path<String> title = song.get(Album_.title);
		cq.where(cb.like(title, "%" + SEARCH_STRING  + "%"));
		TypedQuery<Album> query = em.createQuery(cq);
		List<Album> albums = query.getResultList();
		printSongs(albums);
		
		em.close();
		emf.close();
	}
	
	private void printSongs(List<Album> albums) {
		for (Album album: albums) {
			System.out.println(album);
		}
		
	}
}