package com.enigmacamp.dao;

import java.util.List;

import com.enigmacamp.entities.Singer;

import javassist.NotFoundException;

public interface SingerDao {

	List<Singer> findAll();
	Singer findById(String id) throws NotFoundException;
	Singer create(Singer singer);
	Singer update(Singer singer) throws NotFoundException;
	void delete(String id) throws NotFoundException;
	Singer findByIdAndAlbumId(String singerId, String albumId);
}
