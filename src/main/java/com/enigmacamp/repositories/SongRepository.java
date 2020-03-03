package com.enigmacamp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enigmacamp.entities.Song;

public interface SongRepository extends JpaRepository<Song, String> {

	List<Song> findByTitle(String title);
}
