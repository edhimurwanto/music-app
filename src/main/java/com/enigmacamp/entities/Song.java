package com.enigmacamp.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "songs")
@ApiModel(value = "Class representing songs")
public class Song {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "uuid")
	@ApiModelProperty(notes = "Unique identifier of song. No two songs can have the same id.", required = true, position = 0)
	private String id;

	@Column(name = "title", length = 100, nullable = false)
	@ApiModelProperty(notes = "Title of song.", required = true, position = 1)
	private String title;

	@Column(name = "content", length = 1000, nullable = false)
	@ApiModelProperty(notes = "Contents of song.", required = true, position = 2)
	private String content;
	
	@Column
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@Column
	@UpdateTimestamp
	private LocalDateTime updatedAt;

	@ManyToOne
	@JoinColumn(name = "singer", nullable = false)
	@ApiModelProperty(notes = "Singer of the song", required = true, position = 3)
	private Singer singer;

	@ManyToOne
	@JoinColumn(name = "album", nullable = true)
	@ApiModelProperty(notes = "Album of the song", required = false, position = 4)
	private Album album;
	
	public Song() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Singer getSinger() {
		return singer;
	}

	public void setSinger(Singer singer) {
		this.singer = singer;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}
}
