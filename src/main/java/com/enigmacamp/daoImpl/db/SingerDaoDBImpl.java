package com.enigmacamp.daoImpl.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enigmacamp.dao.SingerDao;
import com.enigmacamp.entities.Singer;
import com.enigmacamp.repositories.SingerRepository;

import javassist.NotFoundException;

@Service
public class SingerDaoDBImpl implements SingerDao {

	@Autowired
	SingerRepository singerRepo;

	@Override
	public List<Singer> findAll() {

		return singerRepo.findAll();
	}

	@Override
	public Singer findById(String id) throws NotFoundException {
		return singerRepo.findById(id).orElseThrow(() -> new NotFoundException("Singer not found."));
	}

	@Override
	public Singer create(Singer singer) {
		return singerRepo.save(singer);
	}

	@Override
	public Singer update(Singer form) throws NotFoundException {
		
		Singer singer = this.findById(form.getId());
		
		singer.setId(form.getId());
		singer.setFirstName(form.getFirstName());
		singer.setLastName(form.getLastName());
		singer.setBirthDate(form.getBirthDate());
		singer.setGender(form.getGender());

		return singerRepo.save(singer);
	}

	@Override
	public void delete(String id) throws NotFoundException {
	
		Singer singer = this.findById(id);
		singerRepo.delete(singer);

	}

	@Override
	public Singer findByIdAndAlbumId(String singerId, String albumId) {
		return singerRepo.findByIdAndAlbumsId(singerId, albumId);
	}

}
