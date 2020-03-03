package com.enigmacamp.daoImpl.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enigmacamp.dao.AlbumDao;
import com.enigmacamp.dao.SingerDao;
import com.enigmacamp.dao.SongDao;
import com.enigmacamp.dto.SongFormDto;
import com.enigmacamp.entities.Album;
import com.enigmacamp.entities.Singer;
import com.enigmacamp.entities.Song;
import com.enigmacamp.repositories.SongRepository;

import javassist.NotFoundException;

@Service
public class SongDaoDBImpl implements SongDao {

	@Autowired
	SongRepository songRepo;

	@Autowired
	SingerDao singerDao;

	@Autowired
	AlbumDao albumDao;

	@Override
	public List<Song> findAll() {
		return songRepo.findAll();
	}

	@Override
	public Song findById(String id) throws NotFoundException {
		return songRepo.findById(id).orElseThrow(() -> new NotFoundException("Song not found."));
	}

	@Override
	public Song create(SongFormDto form) throws NotFoundException {

		Song song = new Song();
		song.setTitle(form.getTitle());
		song.setContent(form.getContent());

		Singer singer = singerDao.findById(form.getSinger());
		Album album = albumDao.findById(form.getAlbum());

		song.setSinger(singer);
		song.setAlbum(album);

		return songRepo.save(song);
	}

	@Override
	public Song update(Song form) throws NotFoundException {

		Song song = this.findById(form.getId());

		song.setId(form.getId());
		song.setTitle(form.getTitle());
		song.setContent(form.getContent());

		return songRepo.save(song);
	}

	@Override
	public void delete(String id) throws NotFoundException {
		
		Song song = this.findById(id);
		songRepo.delete(song);

	}

	@Override
	public List<Song> findByTitle(String title) {
		return songRepo.findByTitle(title);
	}

}
