package com.enigmacamp.dao;

import java.util.List;

import com.enigmacamp.dto.AlbumFormDto;
import com.enigmacamp.entities.Album;
import com.enigmacamp.enums.Genre;

import javassist.NotFoundException;

public interface AlbumDao {

	List<Album> findAll();

	Album findById(String id) throws NotFoundException;

	Album create(AlbumFormDto album) throws NotFoundException;

	Album update(Album album) throws NotFoundException;

	void delete(String id) throws NotFoundException;

	List<Album> findByNameOrGenre(String name, Genre genre);
}
