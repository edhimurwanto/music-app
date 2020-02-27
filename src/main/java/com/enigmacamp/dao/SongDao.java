package com.enigmacamp.dao;

import java.util.List;
import java.util.Optional;

import com.enigmacamp.dto.SongFormDto;
import com.enigmacamp.entities.Song;

import javassist.NotFoundException;

public interface SongDao {
	
	List<Song> findAll();
	Optional<Song> findById(String id);
	Song create(SongFormDto song) throws NotFoundException;
	Song update(Song song);
	void delete(String id);
	Song findByid(String id);
	List<Song> findByTitle(String title);
}
