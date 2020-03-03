package com.enigmacamp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enigmacamp.entities.Singer;

public interface SingerRepository extends JpaRepository<Singer, String> {
	
	Singer findByIdAndAlbumsId(String singerId, String albumId);

}
