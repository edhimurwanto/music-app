package com.enigmacamp.controllers;

import java.util.List;

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
import com.enigmacamp.enums.Genre;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("albums")
@Api(tags = "Albums", description = "Set of endpoints for Creating, Retrieving, Updating and Deleting of Albums.")
public class AlbumController {

	@Autowired
	AlbumDao albumDao;

	@GetMapping("")
	@ApiOperation(value = "Return list of albums.")
	public ResponseEntity<CommonResponse<List<Album>>> findAll(
			@RequestParam(required = false, name = "genre") Genre genre,
			@RequestParam(required = false, name = "name") String name) {

		if (!(name == null) || !(genre == null)) {
			List<Album> albums = albumDao.findByNameOrGenre(name, genre);
			return new ResponseEntity<CommonResponse<List<Album>>>(new CommonResponse<List<Album>>(albums),
					HttpStatus.OK);
		} else {
			List<Album> albums = albumDao.findAll();
			return new ResponseEntity<CommonResponse<List<Album>>>(new CommonResponse<List<Album>>(albums),
					HttpStatus.OK);
		}
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Return albums by their identifier. 404 it does not exist.")
	public ResponseEntity<CommonResponse<Album>> findById(@PathVariable String id) throws Exception {

		Album album = albumDao.findById(id);
		return new ResponseEntity<CommonResponse<Album>>(new CommonResponse<Album>(album), HttpStatus.OK);

	}

	@PostMapping("")
	@ApiOperation(value = "Create new albums.")
	public ResponseEntity<CommonResponse<Album>> create(@RequestBody AlbumFormDto form) throws Exception {

		Album createdAlbum = albumDao.create(form);
		return new ResponseEntity<CommonResponse<Album>>(new CommonResponse<Album>("201", "Created", createdAlbum),
				HttpStatus.CREATED);

	}

	@PutMapping("")
	@ApiOperation(value = "Update an albums.")
	public ResponseEntity<CommonResponse<Album>> update(@RequestBody Album album) throws Exception {

		Album updatedAlbum = albumDao.update(album);
		return new ResponseEntity<CommonResponse<Album>>(new CommonResponse<Album>(updatedAlbum), HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Delete an albums by their identifier.")
	public ResponseEntity<CommonResponse<Album>> delete(@PathVariable String id) throws Exception {

		albumDao.delete(id);
		return new ResponseEntity<CommonResponse<Album>>(HttpStatus.NO_CONTENT);

	}

}
