package com.enigmacamp.dao;

import java.util.List;
import java.util.Optional;

import com.enigmacamp.entities.Singer;

import javassist.NotFoundException;

public interface SingerDao {

	List<Singer> findAll();
	Optional<Singer> findById(String id);
	Singer create(Singer singer);
	Singer update(Singer singer) throws NotFoundException;
	void delete(String id) throws NotFoundException;
	Singer findByid(String id) throws NotFoundException;
	Singer findByIdAndAlbumId(String singerId, String albumId);
}
