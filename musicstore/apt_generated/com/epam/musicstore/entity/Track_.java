package com.epam.musicstore.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Track.class)
public abstract class Track_ {

	public static volatile SingularAttribute<Track, Long> id;
	public static volatile SingularAttribute<Track, Song> song;
	public static volatile SingularAttribute<Track, Album> album;
	public static volatile SingularAttribute<Track, Integer> trackNumber;

}

