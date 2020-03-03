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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;

@RestController
@RequestMapping("/singers")
@Api(tags = "Singers", value = "Set of endpoints for Creating, Retrieving, Updating and Deleting of Singers.")
public class SingerController {

	@Autowired
	SingerDao singerDao;

	@GetMapping("")
	@ApiOperation(value = "Return list of singers.")
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
	@ApiOperation(value = "Return singer by their identifier.")
	public ResponseEntity<CommonResponse<SingerDto>> findById(@PathVariable String id) {
		try {
			Singer singer = singerDao.findById(id);
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
	@ApiOperation(value = "Return list of album that haved by the singer")
	public ResponseEntity<CommonResponse<SingerAlbumsDto>> findAlbumBySingerId(@PathVariable String id) {
		try {
			Singer singer = singerDao.findById(id);
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
	@ApiOperation(value = "Return spicific album that haved by the singer.")
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
	@ApiOperation(value = "Create new singer.")
	public ResponseEntity<CommonResponse<SingerDto>> create(@RequestBody Singer singer) {
		try {
			Singer newSinger = singerDao.create(singer);
			return new ResponseEntity<CommonResponse<SingerDto>>(
					new CommonResponse<SingerDto>("201", "Created", ObjectMapperUtils.map(newSinger, SingerDto.class)),
					HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<CommonResponse<SingerDto>>(new CommonResponse<>("500", "Internal Server Error."),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("")
	@ApiOperation(value = "Update a singer.")
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
	@ApiOperation(value = "Delete singer by their identifier.")
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
