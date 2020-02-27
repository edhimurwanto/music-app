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
import org.springframework.web.bind.annotation.RestController;

import com.enigmacamp.dao.SingerDao;
import com.enigmacamp.dto.CommonResponse;
import com.enigmacamp.dto.SingerAlbumsDto;
import com.enigmacamp.dto.SingerDto;
import com.enigmacamp.entities.Singer;
import com.enigmacamp.utils.ObjectMapperUtils;

import javassist.NotFoundException;

@RestController
@RequestMapping("/singers")
public class SingerController {

	@Autowired
	SingerDao singerDao;

	@GetMapping("")
	public ResponseEntity<CommonResponse<List<SingerDto>>> getAll() {
		try {
			List<Singer> singers = singerDao.findAll();

			return new ResponseEntity<CommonResponse<List<SingerDto>>>(
					new CommonResponse<List<SingerDto>>(ObjectMapperUtils.mapAll(singers, SingerDto.class)),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<CommonResponse<List<SingerDto>>>(
					new CommonResponse<>("500", "Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<CommonResponse<SingerDto>> findById(@PathVariable String id) {
		try {
			Singer singer = singerDao.findByid(id);
			return new ResponseEntity<CommonResponse<SingerDto>>(
					new CommonResponse<SingerDto>(ObjectMapperUtils.map(singer, SingerDto.class)), HttpStatus.OK);
		} catch (NotFoundException e) {
			return new ResponseEntity<CommonResponse<SingerDto>>(new CommonResponse<>("404", e.getMessage()),
					HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<CommonResponse<SingerDto>>(new CommonResponse<>("500", "Internal Server Error."),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}/albums")
	public ResponseEntity<CommonResponse<SingerAlbumsDto>> findAlbumBySingerId(@PathVariable String id) {
		try {
			Singer singer = singerDao.findByid(id);
			return new ResponseEntity<CommonResponse<SingerAlbumsDto>>(
					new CommonResponse<SingerAlbumsDto>(ObjectMapperUtils.map(singer, SingerAlbumsDto.class)),
					HttpStatus.OK);
		} catch (NotFoundException e) {
			return new ResponseEntity<CommonResponse<SingerAlbumsDto>>(new CommonResponse<>("404", e.getMessage()),
					HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<CommonResponse<SingerAlbumsDto>>(
					new CommonResponse<>("500", "Internal Server Error."), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{singerId}/albums/{albumId}")
	public ResponseEntity<CommonResponse<SingerAlbumsDto>> findBySingerIdAndAlbumId(@PathVariable String singerId,
			@PathVariable String albumId) {
		try {
			Singer singer = singerDao.findByIdAndAlbumId(singerId, albumId);
			return new ResponseEntity<CommonResponse<SingerAlbumsDto>>(
					new CommonResponse<SingerAlbumsDto>(ObjectMapperUtils.map(singer, SingerAlbumsDto.class)),
					HttpStatus.OK);
			// }
			// catch (NotFoundException e) {
			// return new ResponseEntity<CommonResponse<SingerAlbumDto>>(new
			// CommonResponse<>("404", e.getMessage()),
			// HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<CommonResponse<SingerAlbumsDto>>(
					new CommonResponse<>("500", "Internal Server Error."), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("")
	public ResponseEntity<CommonResponse<SingerDto>> create(@RequestBody Singer singer) {
		try {
			Singer newSinger = singerDao.create(singer);
			return new ResponseEntity<CommonResponse<SingerDto>>(
					new CommonResponse<SingerDto>(ObjectMapperUtils.map(newSinger, SingerDto.class)),
					HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<CommonResponse<SingerDto>>(new CommonResponse<>("500", "Internal Server Error."),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("")
	public ResponseEntity<CommonResponse<SingerDto>> update(@RequestBody Singer singer) {
		try {
			Singer updatedSinger = singerDao.update(singer);
			return new ResponseEntity<CommonResponse<SingerDto>>(
					new CommonResponse<SingerDto>(ObjectMapperUtils.map(updatedSinger, SingerDto.class)),
					HttpStatus.OK);
		} catch (NotFoundException e) {
			return new ResponseEntity<CommonResponse<SingerDto>>(new CommonResponse<>("404", e.getMessage()),
					HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<CommonResponse<SingerDto>>(new CommonResponse<>("500", "Internal Server Error."),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<CommonResponse<SingerDto>> delete(@PathVariable String id) {
		try {
			singerDao.delete(id);
			return new ResponseEntity<CommonResponse<SingerDto>>(HttpStatus.NO_CONTENT);
		} catch (NotFoundException e) {
			return new ResponseEntity<CommonResponse<SingerDto>>(new CommonResponse<>("404", e.getMessage()),
					HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<CommonResponse<SingerDto>>(new CommonResponse<>("500", "Internal Server Error."),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
