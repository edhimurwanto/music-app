package com.enigmacamp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/singers")
public class SingerController {

	@Autowired
	SingerDao singerDao;

	@GetMapping("")
	public CommonResponse<List<SingerDto>> getAll() {
		List<Singer> singers = singerDao.findAll();

		return new CommonResponse<List<SingerDto>>(ObjectMapperUtils.mapAll(singers, SingerDto.class));
	}

	@GetMapping("/{id}")
	public CommonResponse<SingerDto> findById(@PathVariable String id) {
		Singer singer = singerDao.findById(id);
		return new CommonResponse<SingerDto>(ObjectMapperUtils.map(singer, SingerDto.class));
	}

	@PostMapping("")
	public CommonResponse<SingerDto> create(@RequestBody Singer singer) {
		Singer newSinger = singerDao.create(singer);
		return new CommonResponse<SingerDto>(ObjectMapperUtils.map(newSinger, SingerDto.class));
	}

	@PutMapping("")
	public CommonResponse<SingerDto> update(@RequestBody Singer singer) {
		Singer updatedSinger = singerDao.update(singer);
		return new CommonResponse<SingerDto>(ObjectMapperUtils.map(updatedSinger, SingerDto.class));
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable String id) {
		singerDao.delete(id);
	}

}
