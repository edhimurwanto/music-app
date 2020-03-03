package com.enigmacamp.daoImpl.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enigmacamp.dao.AlbumDao;
import com.enigmacamp.dao.SingerDao;
import com.enigmacamp.dto.AlbumFormDto;
import com.enigmacamp.entities.Album;
import com.enigmacamp.entities.Singer;
import com.enigmacamp.enums.Genre;
import com.enigmacamp.repositories.AlbumRepository;

import javassist.NotFoundException;

@Service
public class AlbumDaoDBImpl implements AlbumDao {

	@Autowired
	AlbumRepository albumRepo;

	@Autowired
	SingerDao singerDao;

	@Override
	public List<Album> findAll() {
		return albumRepo.findAll();
	}

	@Override
	public Album findById(String id) throws NotFoundException {
		return albumRepo.findById(id).orElseThrow(() -> new NotFoundException("Album not found."));
	}

	@Override
	public Album create(AlbumFormDto form) throws NotFoundException {

		Album album = new Album();
		Singer singer = singerDao.findById(form.getSingerId());
		
		album.setName(form.getName());
		album.setReleaseDate(form.getReleaseDate());
		album.setGenre(form.getGenre());
		album.setSinger(singer);

		return albumRepo.save(album);
	}

	@Override
	public Album update(Album album) throws NotFoundException {

		Album newAlbum = this.findById(album.getId());

		newAlbum.setId(album.getId());
		newAlbum.setName(album.getName());
		newAlbum.setReleaseDate(album.getReleaseDate());
		newAlbum.setGenre(album.getGenre());
//		newAlbum.setSinger(album.getSinger());

		return albumRepo.save(newAlbum);
	}

	@Override
	public void delete(String id) throws NotFoundException {
		Album album = this.findById(id);

		albumRepo.delete(album);
	}

	@Override
	public List<Album> findByNameOrGenre(String name, Genre genre) {
		return albumRepo.findByNameOrGenre(name, genre);
	}

}
