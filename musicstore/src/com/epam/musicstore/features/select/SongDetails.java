package com.epam.musicstore.features.select;

import java.util.ArrayList;
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
import com.epam.musicstore.entity.Song;
import com.epam.musicstore.entity.Track;
import com.epam.musicstore.entity.Track_;

public class SongDetails {

	private EntityManagerFactory emf;
	private EntityManager em;
	private final static Long SONG_ID = 1L;

	public SongDetails() {
		emf = Persistence.createEntityManagerFactory("com.epam.musicstore.select");
		em = emf.createEntityManager();
	}

	public static void main(String[] args) {
		new SongDetails().run();
	}

	private void run() {
		{
			Song foundSong = em.find(Song.class, SONG_ID);
			ArrayList<Song> printList = new ArrayList<Song>();
			printList.add(foundSong);
			printEntities(printList);

			CriteriaBuilder cb2 = em.getCriteriaBuilder();
			CriteriaQuery<Album> cq2 = cb2.createQuery(Album.class);
			Root<Track> track = cq2.from(Track.class);

			Path<Song> searchedSongColumn = track.get(Track_.song);
			cq2.where(cb2.equal(searchedSongColumn, foundSong));
			cq2.select(track.get(Track_.album));

			TypedQuery<Album> query2 = em.createQuery(cq2);
			List<Album> albums = query2.getResultList();
			printEntities(albums);
		}

		em.close();
		emf.close();
	}

	private <T> void printEntities(List<T> entities) {
		for (T entity : entities) {
			System.out.println(entity);
		}

	}
}