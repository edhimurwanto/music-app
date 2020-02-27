package com.enigmacamp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enigmacamp.entities.Song;

public interface SongRepository extends JpaRepository<Song, String> {

	Song findByid(String id);
	List<Song> findByTitle(String title);
}
