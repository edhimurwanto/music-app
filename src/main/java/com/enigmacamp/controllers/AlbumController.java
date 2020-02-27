package com.enigmacamp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.enigmacamp.dao.AlbumDao;
import com.enigmacamp.dto.AlbumFormDto;
import com.enigmacamp.dto.CommonResponse;
import com.enigmacamp.entities.Album;

@RestController
@RequestMapping("albums")
public class AlbumController {

	@Autowired
	AlbumDao albumDao;

	@GetMapping("")
	public ResponseEntity<CommonResponse<List<Album>>> findAll(
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String releaseYear) {

		try {
			if (!(name == null) || !(releaseYear == null)) {
				List<Album> albums = albumDao.findByNameOrReleaseYear(name, releaseYear);
				return new ResponseEntity<CommonResponse<List<Album>>>(new CommonResponse<List<Album>>(albums),
						HttpStatus.OK);
			} else {
				List<Album> albums = albumDao.findAll();
				return new ResponseEntity<CommonResponse<List<Album>>>(new CommonResponse<List<Album>>(albums),
						HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<CommonResponse<List<Album>>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public Optional<Album> findById(@PathVariable String id) {
		return albumDao.findById(id);
	}

	@PostMapping("")
	public ResponseEntity<CommonResponse<Album>> create(@RequestBody AlbumFormDto form) {
		try {

			Album createdAlbum = albumDao.create(form);
			return new ResponseEntity<CommonResponse<Album>>(new CommonResponse<Album>(createdAlbum),
					HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<CommonResponse<Album>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping("")
	public ResponseEntity<CommonResponse<Album>> update(@RequestBody Album album) throws Exception {
		try {
			Album updatedAlbum = albumDao.update(album);
			return new ResponseEntity<CommonResponse<Album>>(new CommonResponse<Album>(updatedAlbum), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<CommonResponse<Album>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable String id) {
		albumDao.delete(id);
	}

}
