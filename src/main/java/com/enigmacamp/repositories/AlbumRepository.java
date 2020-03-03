package com.enigmacamp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enigmacamp.entities.Album;
import com.enigmacamp.enums.Genre;

public interface AlbumRepository extends JpaRepository<Album, String> {

	List<Album> findByNameOrGenre(String title, Genre genre);
}
