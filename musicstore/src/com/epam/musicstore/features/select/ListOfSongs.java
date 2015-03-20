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
import com.epam.musicstore.entity.Album_;
import com.epam.musicstore.entity.Author;
import com.epam.musicstore.entity.Author_;
import com.epam.musicstore.entity.Genre;
import com.epam.musicstore.entity.Genre_;
import com.epam.musicstore.entity.Song;
import com.epam.musicstore.entity.Song_;
import com.epam.musicstore.entity.Track;
import com.epam.musicstore.entity.Track_;

public class ListOfSongs {

	private EntityManagerFactory emf;
	private EntityManager em;
	private final static String ALBUM_ID = "1";
	private final static String AUTHOR_ID = "1";
	private final static String GENRE_ID = "2";

	public ListOfSongs() {
		emf = Persistence.createEntityManagerFactory("com.epam.musicstore.select");
		em = emf.createEntityManager();
	}

	public static void main(String[] args) {
		new ListOfSongs().run();
	}

	private void run() {
		{
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Album> cq = cb.createQuery(Album.class);
			Root<Album> album = cq.from(Album.class);

			Path<Long> id = album.get(Album_.id);
			cq.where(cb.equal(id, ALBUM_ID));
			TypedQuery<Album> query = em.createQuery(cq);
			Album foundAlbum = query.getSingleResult();
			ArrayList<Album> printList = new ArrayList<Album>();
			printList.add(foundAlbum);
			printEntities(printList);

			CriteriaBuilder cb2 = em.getCriteriaBuilder();
			CriteriaQuery<Track> cq2 = cb2.createQuery(Track.class);
			Root<Track> track = cq2.from(Track.class);

			Path<Album> searchedSongColumn = track.get(Track_.album);
			cq2.where(cb2.equal(searchedSongColumn, foundAlbum));
			TypedQuery<Track> query2 = em.createQuery(cq2);
			List<Track> tracks = query2.getResultList();
			printEntities(tracks);
		}

		{
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Author> cq = cb.createQuery(Author.class);
			Root<Author> authorRoot = cq.from(Author.class);
			
			Path<Long> id = authorRoot.get(Author_.id);
			cq.where(cb.equal(id, AUTHOR_ID));
			TypedQuery<Author> query = em.createQuery(cq);
			Author foundAuthor = query.getSingleResult();
			
			CriteriaBuilder cb2 = em.getCriteriaBuilder();
			CriteriaQuery<Song> cq2 = cb2.createQuery(Song.class);
			Root<Song> song = cq2.from(Song.class);

			Path<Author> author = song.get(Song_.author);
			cq2.where(cb2.equal(author, foundAuthor));
			TypedQuery<Song> query2 = em.createQuery(cq2);
			List<Song> songs = query2.getResultList();
			printEntities(songs);
		}
		
		{
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Genre> cq = cb.createQuery(Genre.class);
			Root<Genre> genreRoot = cq.from(Genre.class);
			
			Path<Long> id = genreRoot.get(Genre_.id);
			cq.where(cb.equal(id, GENRE_ID));
			TypedQuery<Genre> query = em.createQuery(cq);
			Genre foundGenre = query.getSingleResult();
			
			CriteriaBuilder cb2 = em.getCriteriaBuilder();
			CriteriaQuery<Song> cq2 = cb2.createQuery(Song.class);
			Root<Song> song = cq2.from(Song.class);

			Path<Genre> genre = song.get(Song_.genre);
			cq2.where(cb2.equal(genre, foundGenre));
			TypedQuery<Song> query2 = em.createQuery(cq2);
			List<Song> songs = query2.getResultList();
			printEntities(songs);
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