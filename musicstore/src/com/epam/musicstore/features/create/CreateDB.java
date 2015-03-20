package com.epam.musicstore.features.create;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.epam.musicstore.entity.Album;
import com.epam.musicstore.entity.Author;
import com.epam.musicstore.entity.Genre;
import com.epam.musicstore.entity.Song;
import com.epam.musicstore.entity.Track;
import com.epam.musicstore.entity.User;

public class CreateDB {

	private EntityManager em;
	private EntityManagerFactory emf;

	public CreateDB() {
		emf = Persistence.createEntityManagerFactory("com.epam.musicstore.create");
		em = emf.createEntityManager();
	}

	public static void main(String[] args) {
		new CreateDB().run();
	}

	private void run() {
		try {
			em.getTransaction().begin();

			{
				User user = new User();
				user.setName("Klaudia");
				em.persist(user);
				
				User user1 = new User();
				user1.setName("Péter");
				em.persist(user1);
				
				Genre genre = new Genre();
				genre.setName("classical");
				em.persist(genre);

				Author author = new Author();
				author.setName("Rachmaninov");
				em.persist(author);

				Song song = new Song();
				song.setTitle("C# prelude");
				song.setAuthor(author);
				song.setGenre(genre);
				song.setLength(532);
				em.persist(song);

				Album album = new Album();
				album.setTitle("Best of Rachmaninov");
				album.setYearOfPublish(1997);
				em.persist(album);

				Track track = new Track();
				track.setAlbum(album);
				track.setSong(song);
				track.setTrackNumber(2);
				em.persist(track);
			}

			{
				
				Genre genre = new Genre();
				genre.setName("jazz");
				em.persist(genre);

				Author author = new Author();
				author.setName("Keith Jarrett");
				em.persist(author);

				Song song = new Song();
				song.setTitle("The Good America");
				song.setAuthor(author);
				song.setGenre(genre);
				song.setLength(312);
				em.persist(song);

				Album album = new Album();
				album.setTitle("The Paris Concert");
				album.setYearOfPublish(1986);
				em.persist(album);

				Track track = new Track();
				track.setAlbum(album);
				track.setSong(song);
				track.setTrackNumber(3);
				em.persist(track);
			}

			if (em.getTransaction().isActive()) {
				em.getTransaction().commit();
			}
		} catch (Exception ex) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}

		em.close();
		emf.close();
	}
}
