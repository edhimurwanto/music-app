package com.enigmacamp.entities;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import com.enigmacamp.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "singers")
@ApiModel(value = "Class representing singer.")
public class Singer {
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "uuid")
	@ApiModelProperty(notes = "Unique identifier of singer. No two singers can have the same id.", required = true, position = 0)
	private String id;
	
	@Column(name = "first_name", length = 15, nullable = false)
	@ApiModelProperty(notes = "First name of singer", required = true, position=1)
	private String firstName;
	
	@Column(name = "last_name", length = 30, nullable = false)
	@ApiModelProperty(notes = "Last name of singer", required = true, position = 2)
	private String lastName;
	
	@Column(name = "birth_date", nullable = false)
	@ApiModelProperty(notes = "Birth Date of singer", dataType = "Date", required = true, position = 3 )
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date birthDate;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "gender", length = 8, nullable = false)
	@ApiModelProperty(notes = "Gender of singer", dataType = "Enum", required = true, position = 4)
	private Gender gender;
	
	
	@Column
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@Column
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true, mappedBy="singer")
	@ApiModelProperty(notes = "List of album that haved by the singer", position = 5)
	private List<Album> albums;
	
	public Singer() {
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

	public List<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}
	
}
