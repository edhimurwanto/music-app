package com.enigmacamp.daoImpl.db;

import java.util.List;
import java.util.Optional;

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
	public Optional<Singer> findById(String id) {
		return singerRepo.findById(id);
	}

	@Override
	public Singer create(Singer singer) {
		return singerRepo.save(singer);
	}

	@Override
	public Singer update(Singer form) throws NotFoundException {
		Singer singer = this.findByid(form.getId());
		if (singer == null)
			throw new NotFoundException("Singer not found.");
		
		return singerRepo.save(singer);
	}

	@Override
	public void delete(String id) throws NotFoundException {
		try {
			singerRepo.deleteById(id);
		} catch (Exception e) {
			throw new NotFoundException("Singer not found.");
		}

	}

	@Override
	public Singer findByid(String id) throws NotFoundException {
		Singer singer = singerRepo.findByid(id);
		if (singer == null)
			throw new NotFoundException("Singer not found.");
		
		return singer;
	}

	@Override
	public Singer findByIdAndAlbumId(String singerId, String albumId) {
		return singerRepo.findByIdAndAlbumsId(singerId, albumId);
	}

}
