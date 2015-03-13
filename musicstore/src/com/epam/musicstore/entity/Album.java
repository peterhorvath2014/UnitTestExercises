package com.epam.musicstore.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "album")
public class Album {
	public Album() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;

	@Column(name = "title")
	private String title;
	
	@Column(name = "year_of_public")
	private int yearOfPublish;
	
	@OneToMany
	private List<Track> tracks;
}
