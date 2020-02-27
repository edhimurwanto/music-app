package com.enigmacamp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.enigmacamp.dao.SongDao;
import com.enigmacamp.dto.CommonResponse;
import com.enigmacamp.dto.SongFormDto;
import com.enigmacamp.entities.Song;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;

@RestController
@RequestMapping("songs")
@Api("Set of endpoints for Creating, Retrieving, Updating and Deleting of Customers.")
public class SongController {

	@Autowired
	SongDao songDao;

	@GetMapping("")
	@ApiOperation("Return list of song.")
	public ResponseEntity<CommonResponse<List<Song>>> findAll(@RequestParam(required = false) String title) {

		try {

			if (!(title == null)) {
				List<Song> songs = songDao.findByTitle(title);
				return new ResponseEntity<CommonResponse<List<Song>>>(new CommonResponse<List<Song>>(songs),
						HttpStatus.OK);
			} else {
				List<Song> songs = songDao.findAll();
				return new ResponseEntity<CommonResponse<List<Song>>>(new CommonResponse<List<Song>>(songs),
						HttpStatus.OK);
			}

		} catch (Exception e) {
			return new ResponseEntity<CommonResponse<List<Song>>>(
					new CommonResponse<List<Song>>("500", "Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	@ApiOperation("Return a song by their identifier. 404 if does not exist.")
	public ResponseEntity<CommonResponse<Song>> findById(@PathVariable String id) {
		try {
			Song song = songDao.findById(id).orElseThrow(() -> new NotFoundException("Song not found"));
			return new ResponseEntity<CommonResponse<Song>>(new CommonResponse<Song>(song), HttpStatus.OK);
		} catch (NotFoundException e) {
			return new ResponseEntity<CommonResponse<Song>>(new CommonResponse<Song>("404", e.getMessage()),
					HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<CommonResponse<Song>>(new CommonResponse<Song>("500", "Internal Server Error"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("")
	@ApiOperation("Create new songs.")
	public ResponseEntity<CommonResponse<Song>> create(@RequestBody SongFormDto form) {
		try {
			Song song = songDao.create(form);
			return new ResponseEntity<CommonResponse<Song>>(new CommonResponse<Song>(song), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<CommonResponse<Song>>(new CommonResponse<Song>("500", "Internal Server Error"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("")
	@ApiOperation("Updated a song.")
	public ResponseEntity<CommonResponse<Song>> update(@RequestBody Song song) {
		try {
			Song updatedSong = songDao.update(song);
			return new ResponseEntity<CommonResponse<Song>>(new CommonResponse<Song>(updatedSong), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<CommonResponse<Song>>(new CommonResponse<Song>("500", "Internal Server Error"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	@ApiOperation("Delete song by their identifier.")
	public ResponseEntity<CommonResponse<Song>> delete(@PathVariable String id) {
		try {
			songDao.delete(id);
			return new ResponseEntity<CommonResponse<Song>>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<CommonResponse<Song>>(new CommonResponse<>("500", "Internal Server Error."),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
