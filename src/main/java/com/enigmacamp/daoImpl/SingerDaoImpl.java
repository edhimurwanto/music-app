package com.enigmacamp.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.enigmacamp.dao.SingerDao;
import com.enigmacamp.entities.Singer;

@Service
public class SingerDaoImpl implements SingerDao {

	List<Singer> singers = new ArrayList<Singer>();

	@Override
	public List<Singer> findAll() {
		return singers;
	}

	@Override
	public Singer findById(String id) {

		for (Singer singer : singers) {
			if (singer.getId().equals(id)) {
				return singer;
			}
		}

		return null;
	}

	@Override
	public Singer create(Singer singer) {
		singers.add(singer);
		return singer;
	}

	@Override
	public Singer update(Singer singer) {

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
	public void delete(String id) {

		Singer singer = findById(id);

		if (singer == null)
			System.out.println("Not found.");
		else
			singers.remove(singer);

	}

}
