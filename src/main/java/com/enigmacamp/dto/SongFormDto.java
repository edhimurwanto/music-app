package com.enigmacamp.dto;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Class for Request body of create new Songs.")
public class SongFormDto {
	
	@NotNull
	@ApiModelProperty(notes = "Title of songs.", position = 0)
	private String title;
	
	@NotNull
	@ApiModelProperty(notes = "Content of song", position = 1)
	private String content;
	
	@NotNull
	@ApiModelProperty(notes = "Singer id of song", position = 2)
	private String singer;
	
	@Null
	@ApiModelProperty(notes = "Album id of song", position = 3)
	private String album;
	
	public SongFormDto() {
		
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

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}
}
