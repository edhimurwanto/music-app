package com.enigmacamp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.enigmacamp.dao.AlbumDao;
import com.enigmacamp.dao.SingerDao;
import com.enigmacamp.dao.SongDao;
//import com.enigmacamp.daoImpl.SingerDaoImpl;
import com.enigmacamp.daoImpl.db.AlbumDaoDBImpl;
import com.enigmacamp.daoImpl.db.SingerDaoDBImpl;
import com.enigmacamp.daoImpl.db.SongDaoDBImpl;

@Configuration
public class DaoSpringConfig {

	@Bean
	public SingerDao singerDao() {
		return new SingerDaoDBImpl();
	}

	@Bean
	public AlbumDao albumDao() {
		return new AlbumDaoDBImpl();
	}
	
	@Bean
	public SongDao songDao() {
		return new SongDaoDBImpl();
	}

}