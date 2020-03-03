package com.enigmacamp.dao;

import java.util.List;

import com.enigmacamp.dto.SongFormDto;
import com.enigmacamp.entities.Song;

import javassist.NotFoundException;

public interface SongDao {
	
	List<Song> findAll();
	Song findById(String id) throws NotFoundException;
	Song create(SongFormDto song) throws NotFoundException;
	Song update(Song song) throws NotFoundException;
	void delete(String id) throws NotFoundException;
	List<Song> findByTitle(String title);
}
