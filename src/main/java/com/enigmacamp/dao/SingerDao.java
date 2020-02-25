package com.enigmacamp.dao;

import java.util.List;

import com.enigmacamp.entities.Singer;

public interface SingerDao {

	List<Singer> findAll();
	Singer findById(String id);
	Singer create(Singer singer);
	Singer update(Singer singer);
	void delete(String id);
}
