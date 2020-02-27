package com.enigmacamp.dto;

import java.sql.Date;
import java.util.List;

import com.enigmacamp.enums.Gender;

public class SingerAlbumsDto {

	private String id;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private Gender gender;
	private List<AlbumDto> albums;
	
	public SingerAlbumsDto() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public List<AlbumDto> getAlbums() {
		return albums;
	}
	public void setAlbums(List<AlbumDto> albums) {
		this.albums = albums;
	}
}
