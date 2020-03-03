package com.enigmacamp.dto;

import java.sql.Date;

import com.enigmacamp.enums.Genre;

public class AlbumDto {
	
	private String id;
	private String name;
	private Date releaseDate;
	private Genre genre;
	
	public AlbumDto() {
		
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseYear) {
		this.releaseDate = releaseYear;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

}
