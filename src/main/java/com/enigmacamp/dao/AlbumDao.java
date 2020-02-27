package com.enigmacamp.dao;

import java.util.List;
import java.util.Optional;

import com.enigmacamp.dto.AlbumFormDto;
import com.enigmacamp.entities.Album;

import javassist.NotFoundException;

public interface AlbumDao {

	List<Album> findAll();
	Optional<Album> findById(String id);
	Album create(AlbumFormDto album) throws NotFoundException;
	Album update(Album album) throws NotFoundException;
	void delete(String id);
	List<Album> findByNameOrReleaseYear(String name, String releaseYear);
	Album findByid(String id);
}
