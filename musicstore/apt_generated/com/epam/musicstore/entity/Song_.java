package com.epam.musicstore.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Song.class)
public abstract class Song_ {

	public static volatile SingularAttribute<Song, Long> id;
	public static volatile SingularAttribute<Song, Genre> genre;
	public static volatile SingularAttribute<Song, Author> author;
	public static volatile SingularAttribute<Song, String> title;
	public static volatile SingularAttribute<Song, Integer> length;

}

