package com.enigmacamp.daoImpl.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enigmacamp.dao.AlbumDao;
import com.enigmacamp.dao.SingerDao;
import com.enigmacamp.dto.AlbumFormDto;
import com.enigmacamp.entities.Album;
import com.enigmacamp.entities.Singer;
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
	public Optional<Album> findById(String id) {
		return albumRepo.findById(id);
	}

	@Override
	public Album create(AlbumFormDto form) throws NotFoundException {
		
		Album album = new Album();
		Singer singer = singerDao.findByid(form.getSingerId());
		album.setName(form.getName());
		album.setReleaseYear(form.getReleaseYear());
		album.setSinger(singer);
		
		return albumRepo.save(album);
	}

	@Override
	public Album update(Album album) throws NotFoundException{
		
		Optional<Album> newAlbum = this.findById(album.getId());
		
		if(newAlbum.isEmpty()) {
			throw new NotFoundException("Album not found");
		}
		
		return albumRepo.save(album);
	}

	@Override
	public void delete(String id) {
		albumRepo.deleteById(id);
	}

	@Override
	public List<Album> findByNameOrReleaseYear(String name, String releaseYear) {
		return albumRepo.findByNameOrReleaseYear(name, releaseYear);
	}

	@Override
	public Album findByid(String id) {
		return albumRepo.findByid(id);
	}

}
