package com.enigmacamp.daoImpl.dummy;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.enigmacamp.dao.SingerDao;
import com.enigmacamp.entities.Singer;

import javassist.NotFoundException;

@Service
public class SingerDaoImpl implements SingerDao {

	List<Singer> singers = new ArrayList<Singer>();

	@Override
	public List<Singer> findAll() {
		return singers;
	}

	@Override
	public Singer create(Singer singer) {
		singers.add(singer);
		return singer;
	}

	@Override
	public Singer update(Singer singer) throws NotFoundException {

		Singer newSinger = findById(singer.getId());

		if (newSinger == null) {
			return null;
		} else {
			newSinger.setId(singer.getId());
			newSinger.setFirstName(singer.getFirstName());
			newSinger.setLastName(singer.getLastName());
			newSinger.setBirthDate(singer.getBirthDate());
			newSinger.setGender(singer.getGender());
			return newSinger;
		}
	}

	@Override
	public void delete(String id) throws NotFoundException {

		Singer singer = findById(id);

		if (singer == null)
			System.out.println("Not found.");
		else
			singers.remove(singer);

	}

	@Override
	public Singer findByIdAndAlbumId(String singerId, String albumId) {
		return null;
	}

	@Override
	public Singer findById(String id) throws NotFoundException {
		return null;
	}

}
