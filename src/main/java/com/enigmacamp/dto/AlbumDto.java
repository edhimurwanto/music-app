package com.enigmacamp.dto;

import java.sql.Date;

import com.enigmacamp.enums.Genre;

public class AlbumDto {
	
	private String name;
	private Date releaseDate;
	private Genre genre;
	private String images;
	
	public AlbumDto() {
		super();
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

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

}
