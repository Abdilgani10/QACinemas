package com.qa.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.qa.domain.Movie;
import com.qa.utility.JSONUtil;

@RunWith(MockitoJUnitRunner.class)
public class MovieServiceTest {

	@InjectMocks
	private MovieService repo;

	@Mock
	private EntityManager manager;

	@Mock
	private Query query;

	private JSONUtil util;

	

	private static final String MOCK_OBJECT = "{\"title\":\"John\",\"genre\":\"action\",\"ratingr\":\"18\"}";

	@Before
	public void setup() {
		repo.setManager(manager);
		util = new JSONUtil();
		repo.setUtil(util);
	}
	
	@Test
	public void testCreateMovie() {
		String reply = repo.addMovie(MOCK_OBJECT);
		Assert.assertEquals(reply, "{\"message\": \"movie sucessfully added\"}");
	}
	
	@Test
	public void testDeleteMovie() {
		
		Mockito.when(repo.findMovie((long) 1)).thenReturn(util.getObjectForJSON("{\"title\":\"John\",\"genre\":\"action\",\"ratingr\":\"18\"}", Movie.class));
		String reply = (String) repo.removeMovie((long) 1);
		Assert.assertEquals(reply, "{\"message\": \"movie sucessfully removed\"}");
		
		Mockito.when(repo.findMovie((long) 1)).thenReturn(null);
		reply =  repo.removeMovie((long) 1);
		Assert.assertEquals(reply, "{\"message\": \"movie couldn't be removed\"}");
		
	}

}