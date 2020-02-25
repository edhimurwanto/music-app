package com.enigmacamp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.enigmacamp.dao.SingerDao;
import com.enigmacamp.daoImpl.SingerDaoImpl;

@Configuration
public class DaoSpringConfig {

	@Bean
	public SingerDao singerDao() {
		return new SingerDaoImpl();
	}
}
