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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import com.enigmacamp.enums.Genre;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "albums")
@ApiModel(value = "Class representing albums")
public class Album {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "uuid")
	@ApiModelProperty(notes = "Unique identifier of album. No two albums can have the same id.", required = true, position = 0)
	private String id;

	@Column(length = 100, nullable = false)
	@ApiModelProperty(notes = "Name of the album", required = true, position = 1)
	private String name;
	
	@Column(nullable = false)
	@ApiModelProperty(notes = "Date of release the album", required = true, position = 2)
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date releaseDate;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 50)
	@ApiModelProperty("Genre of albums.")
	private Genre genre;
	
	@Column
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@Column
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	@ManyToOne
    @JoinColumn(name="singer", nullable=true)
	@ApiModelProperty(notes = "Singer of the album", required = false, position = 3 )
	private Singer singer;

	@OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true, mappedBy="album")
	@ApiModelProperty(notes = "List of songs in the album")
	private List<Song> songs;
	
	public Album() {
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

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Singer getSinger() {
		return singer;
	}

	public void setSinger(Singer singer) {
		this.singer = singer;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}	
}
