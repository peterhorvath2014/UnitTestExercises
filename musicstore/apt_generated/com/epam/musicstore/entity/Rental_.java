package com.epam.musicstore.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Rental.class)
public abstract class Rental_ {

	public static volatile SingularAttribute<Rental, Long> id;
	public static volatile SingularAttribute<Rental, Album> album;
	public static volatile SingularAttribute<Rental, User> user;
	public static volatile SingularAttribute<Rental, Date> in;
	public static volatile SingularAttribute<Rental, Date> out;

}

