package com.enigmacamp.controllers;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import org.springframework.web.multipart.MultipartFile;

import com.enigmacamp.dao.AlbumDao;
import com.enigmacamp.dao.StorageService;
import com.enigmacamp.dto.AlbumDto;
import com.enigmacamp.dto.AlbumFormDto;
import com.enigmacamp.dto.CommonResponse;
import com.enigmacamp.entities.Album;
import com.enigmacamp.enums.Genre;
import com.enigmacamp.utils.ObjectMapperUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;

@RestController
@RequestMapping("albums")
@Api(tags = "Albums")
public class AlbumController {

	@Autowired
	private StorageService storageService;

	@Autowired
	AlbumDao albumDao;
	
	@Value("${user.home}${app.upload.dir:${user.home}}")
	public String uploadDir;

	@GetMapping("")
	@ApiOperation(value = "Return list of albums.")
	public ResponseEntity<CommonResponse<List<Album>>> findAll(
			@RequestParam(required = false, name = "genre") Genre genre,
			@RequestParam(required = false, name = "name") String name) {

		if (name != null || genre != null) {
			List<Album> albums = albumDao.findByNameOrGenre(name, genre);
			return new ResponseEntity<>(new CommonResponse<List<Album>>(albums),
					HttpStatus.OK);
		} else {
			List<Album> albums = albumDao.findAll();
			return new ResponseEntity<>(new CommonResponse<List<Album>>(albums),
					HttpStatus.OK);
		}
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Return albums by their identifier. 404 it does not exist.")
	public ResponseEntity<CommonResponse<Album>> findById(@PathVariable String id) throws NotFoundException {

		Album album = albumDao.findById(id);
		return new ResponseEntity<>(new CommonResponse<Album>(album), HttpStatus.OK);

	}

	@PostMapping("")
	@ApiOperation(value = "Create new albums.")
	public ResponseEntity<CommonResponse<Album>> create(@RequestBody AlbumFormDto form) throws NotFoundException {

		Album createdAlbum = albumDao.create(form);
		return new ResponseEntity<>(new CommonResponse<Album>("201", "Created", createdAlbum),
				HttpStatus.CREATED);

	}

	@PutMapping("")
	@ApiOperation(value = "Update an albums.")
	public ResponseEntity<CommonResponse<Album>> update(@RequestBody AlbumDto album) throws NotFoundException {

		Album updatedAlbum = albumDao.update( ObjectMapperUtils.map(album, Album.class));
		return new ResponseEntity<>(new CommonResponse<Album>(updatedAlbum), HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Delete an albums by their identifier.")
	public ResponseEntity<CommonResponse<Album>> delete(@PathVariable String id) throws NotFoundException {

		albumDao.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

	@PostMapping("/{id}/upload")
	public ResponseEntity<CommonResponse<Album>> handleFileUpload(@RequestParam("file") MultipartFile file,
			@PathVariable String id) throws FileNotFoundException, NotFoundException {

		
		String path  = storageService.store(file, id);
		Album updatedAlbum = albumDao.updateImages(id, path);
		return new ResponseEntity<>(new CommonResponse<Album>(updatedAlbum), HttpStatus.OK);
	}

	@GetMapping("/{id}/download")
	public ResponseEntity<Resource> download(@PathVariable String id) throws MalformedURLException, NotFoundException {
		
		Album album = albumDao.findById(id);
		
		Resource resource = storageService.loadAsResource(album.getImages());
		
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType("application/octet-stream"))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
}
