package com.epam.musicstore.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
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

	@OneToMany(mappedBy = "album", fetch = FetchType.EAGER)
	@OrderBy("trackNumber ASC")
	private List<Track> tracks = new ArrayList<>();

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYearOfPublish() {
		return yearOfPublish;
	}

	public void setYearOfPublish(int yearOfPublish) {
		this.yearOfPublish = yearOfPublish;
	}

	public Long getId() {
		return id;
	}

	public List<Track> getTracks() {
		return tracks;
	}
}
