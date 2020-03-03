package com.enigmacamp.dto;

import java.sql.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.enigmacamp.enums.Genre;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Album DTO for create new album")
public class AlbumFormDto {
	
	@NotNull
	@Size(max = 100, message = "Name of album can't more than 100 character")
	@ApiModelProperty(notes = "Name of the album", required = true, position = 0)
	private String name;
	
	@Size(min = 4, max = 4, message = "Year of release must min and max = 4")
	@ApiModelProperty(notes = "Year of release the album", required = true, position = 1, example = "2020-01-01")
	private Date releaseDate;
	
	@Size(message = "Genre of albums")
	@ApiModelProperty(notes = "Genre of albums", required = true, position = 2)
	private Genre genre;
	
	@Size(message = "Insert valid singer id to successfully create the album")
	@ApiModelProperty(notes = "Singer identifier of the album", required = true, position = 3)
	private String singerId;
	
	public AlbumFormDto() {
		
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

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getSingerId() {
		return singerId;
	}
	public void setSingerId(String singerId) {
		this.singerId = singerId;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

}
