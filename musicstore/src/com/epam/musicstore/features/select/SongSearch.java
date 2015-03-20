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

import com.epam.musicstore.entity.Song;
import com.epam.musicstore.entity.Song_;

public class SongSearch {
	
	private EntityManagerFactory emf;
	private EntityManager em;
	private final static String SEARCH_STRING = "c#";

	public SongSearch() {
		emf = Persistence
				.createEntityManagerFactory("com.epam.musicstore.select");
		em = emf.createEntityManager();
	}
	public static void main(String[] args) {
		new SongSearch().run();
	}
	
	private void run() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Song> cq = cb.createQuery(Song.class);
		Root<Song> song = cq.from(Song.class);
		
		Path<String> title = song.get(Song_.title);
		cq.where(cb.like(title, "%" + SEARCH_STRING  + "%"));
		TypedQuery<Song> query = em.createQuery(cq);
		List<Song> songs = query.getResultList();
		printSongs(songs);
		
		em.close();
		emf.close();
	}
	private void printSongs(List<Song> songs) {
		for (Song song: songs) {
			System.out.println(song);
		}
		
	}
}