package com.enigmacamp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enigmacamp.entities.Album;

public interface AlbumRepository extends JpaRepository<Album, String> {

	List<Album> findByNameOrReleaseYear(String title, String releaseYear);
	Album findByid(String id);
}
