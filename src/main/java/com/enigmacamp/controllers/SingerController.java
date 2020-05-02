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
import com.enigmacamp.dto.SingerDto;
import com.enigmacamp.entities.Singer;
import com.enigmacamp.utils.ObjectMapperUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;

@RestController
@RequestMapping("/singers")
@Api(tags = "Singers")
public class SingerController {

	@Autowired
	SingerDao singerDao;

	@GetMapping("")
	@ApiOperation(value = "Return list of singers.")
	public ResponseEntity<CommonResponse<List<SingerDto>>> getAll() {

		List<Singer> singers = singerDao.findAll();
		return new ResponseEntity<>(
				new CommonResponse<List<SingerDto>>(ObjectMapperUtils.mapAll(singers, SingerDto.class)), HttpStatus.OK);

	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Return singer by their identifier.")
	public ResponseEntity<CommonResponse<SingerDto>> findById(@PathVariable String id) throws NotFoundException {

		Singer singer = singerDao.findById(id);
		return new ResponseEntity<>(
				new CommonResponse<SingerDto>(ObjectMapperUtils.map(singer, SingerDto.class)), HttpStatus.OK);
	}

	@GetMapping("/{id}/albums")
	@ApiOperation(value = "Return list of album that haved by the singer")
	public ResponseEntity<CommonResponse<Singer>> findAlbumBySingerId(@PathVariable String id)
			throws NotFoundException {

		Singer singer = singerDao.findById(id);
		return new ResponseEntity<>(
				new CommonResponse<Singer>(ObjectMapperUtils.map(singer, Singer.class)),
				HttpStatus.OK);
	}

	@GetMapping("/{singerId}/albums/{albumId}")
	@ApiOperation(value = "Return spicific album that haved by the singer.")
	public ResponseEntity<CommonResponse<Singer>> findBySingerIdAndAlbumId(@PathVariable String singerId, @PathVariable String albumId) {

		Singer singer = singerDao.findByIdAndAlbumId(singerId, albumId);
		return new ResponseEntity<>(
				new CommonResponse<Singer>(ObjectMapperUtils.map(singer, Singer.class)),
				HttpStatus.OK);
	}

	@PostMapping("")
	@ApiOperation(value = "Create new singer.")
	public ResponseEntity<CommonResponse<SingerDto>> create(@RequestBody SingerDto singer) {

		Singer newSinger = singerDao.create(ObjectMapperUtils.map(singer, Singer.class));
		return new ResponseEntity<>(
				new CommonResponse<SingerDto>("201", "Created", ObjectMapperUtils.map(newSinger, SingerDto.class)),
				HttpStatus.CREATED);
	}

	@PutMapping("")
	@ApiOperation(value = "Update a singer.")
	public ResponseEntity<CommonResponse<SingerDto>> update(@RequestBody SingerDto singer) throws NotFoundException {

		Singer updatedSinger = singerDao.update(ObjectMapperUtils.map(singer, Singer.class));
		return new ResponseEntity<>(
				new CommonResponse<SingerDto>(ObjectMapperUtils.map(updatedSinger, SingerDto.class)), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Delete singer by their identifier.")
	public ResponseEntity<CommonResponse<SingerDto>> delete(@PathVariable String id) throws NotFoundException {

		singerDao.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

}
