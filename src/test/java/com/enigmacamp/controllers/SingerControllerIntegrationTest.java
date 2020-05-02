package com.enigmacamp.controllers;

import com.enigmacamp.dao.impl.db.SingerDaoDBImpl;
import com.enigmacamp.entities.Singer;
import com.enigmacamp.enums.Gender;
import com.enigmacamp.repositories.SingerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SingerControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private SingerDaoDBImpl service;
	
	@MockBean
	private SingerRepository repo;
	
	ObjectMapper mapper = new ObjectMapper();

	@Test
	public void givenSinger_whenFindAll_thenReturnJSONArray_andStatusOk() throws Exception {

		mvc.perform(MockMvcRequestBuilders.get("/singers")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn().getResponse().getContentAsString();

	}
	
	@Test
	public void whenFindById_thenStatusOk() throws UnsupportedEncodingException, Exception {
		
		// given
        Singer singer = new Singer();
        singer.setId("01");
        singer.setFirstName("Edi");
        singer.setLastName("Murwanto");
        singer.setBirthDate(Date.valueOf("2000-01-01"));
        singer.setGender(Gender.MALE);
		
		Mockito.when(repo.findById(ArgumentMatchers.anyString())).thenReturn(Optional.of(singer));

		mvc.perform(MockMvcRequestBuilders.get("/singers/01")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn().getResponse().getContentAsString();
	}

	@Test
	public void whenFindById_ifIdNotFound_thenThrowNotFound() throws UnsupportedEncodingException, Exception {
		
		mvc.perform(MockMvcRequestBuilders.get("/singers/02")).andExpect(MockMvcResultMatchers.status().isNotFound())
				.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn().getResponse().getContentAsString();
	}
	
//	@Test
//	public void whenCreatSinger_thenReturnStatusCreated() throws UnsupportedEncodingException, JsonProcessingException, Exception {
//		
//		// given
//        Singer singer = new Singer();
//        singer.setFirstName("Edi");
//        singer.setLastName("Murwanto");
//        singer.setBirthDate(Date.valueOf("2000-01-01"));
//        singer.setGender(Gender.MALE);
        
        // ResponseEntity<CommonResponse<Singer>> resultForSaved = new ResponseEntity<CommonResponse<Singer>>(new CommonResponse<Singer>("201", "Created", singer), HttpStatus.CREATED);
        
		
		// String result = 
//		mvc
//				.perform(MockMvcRequestBuilders.post("/singers")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(mapper.writeValueAsString(singer)))
//				.andExpect(MockMvcResultMatchers.status().isCreated())
//				.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//				.andReturn().getResponse().getContentAsString();

		// JavaType savedSinger = mapper.getTypeFactory().constructParametricType(ResponseEntity.class, Singer.class);
		// ResponseEntity<CommonResponse<Singer>> actual = mapper.readValue(result, savedSinger);

		// assertEquals(resultForSaved, actual);
//	}

}