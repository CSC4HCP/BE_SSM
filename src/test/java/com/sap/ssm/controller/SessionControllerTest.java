package com.sap.ssm.controller;

import java.sql.Date;
import java.util.Arrays;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.sap.ssm.WebTest;
import com.sap.ssm.persistence.model.Session;
import com.sap.ssm.service.SessionService;

public class SessionControllerTest extends WebTest {

	@Autowired
	@Mock
	private SessionService sessionServiceMock;

	@Test
	public void findAll_ShouldReturnFoundSessions() throws Exception {

		Session first = new Session();
		Session second = new Session();

		long testTime = System.currentTimeMillis();
		first.setId(1L);
		first.setTopic("test topic 1");
		first.setCategory("test category 1");
		first.setDescription("test description 1");
		first.setOwner("test owner 1");
		first.setStatus("test status 1");
		first.setMeetingRoom("test room 1");
		first.setMeetingTime(new Date(testTime));
		first.setFile(1);
		first.setSummary("test summary 1");
		first.setVisibility(true);

		second.setId(2L);
		second.setTopic("test topic 2");
		second.setCategory("test category 2");
		second.setDescription("test description 2");
		second.setOwner("test owner 2");
		second.setStatus("test status 2");
		second.setMeetingRoom("test room 2");
		second.setMeetingTime(new Date(testTime));
		second.setFile(2);
		second.setSummary("test summary 2");
		second.setVisibility(false);

		Mockito.when(sessionServiceMock.findAll()).thenReturn(Arrays.asList(first, second));

		this.mvc.perform(MockMvcRequestBuilders.get("/session")).andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].topic", Matchers.is("test topic 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].category", Matchers.is("test category 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].description", Matchers.is("test description 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].owner", Matchers.is("test owner 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].status", Matchers.is("test status 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].meetingRoom", Matchers.is("test room 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].meetingTime", Matchers.is(new Date(testTime))))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].file", Matchers.is(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].summary", Matchers.is("test summary 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].visibility", Matchers.is(true)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(2)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].topic", Matchers.is("test topic 2")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].category", Matchers.is("test category 2")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].description", Matchers.is("test description 2")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].owner", Matchers.is("test owner 2")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].status", Matchers.is("test status 2")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].meetingRoom", Matchers.is("test room 2")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].meetingTime", Matchers.is(new Date(testTime))))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].file", Matchers.is(2)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].summary", Matchers.is("test summary 2")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].visibility", Matchers.is(false))).andReturn();

		Mockito.verify(sessionServiceMock, Mockito.times(1)).findAll();
		Mockito.verifyNoMoreInteractions(sessionServiceMock);
	}

	@Test
	public void findOneById_ShouldReturnFoundSession() throws Exception {

		Session first = new Session();

		long testTime = System.currentTimeMillis();
		first.setId(1L);
		first.setTopic("test topic 1");
		first.setCategory("test category 1");
		first.setDescription("test description 1");
		first.setOwner("test owner 1");
		first.setStatus("test status 1");
		first.setMeetingRoom("test room 1");
		first.setMeetingTime(new Date(testTime));
		first.setFile(1);
		first.setSummary("test summary 1");
		first.setVisibility(true);

		Mockito.when(sessionServiceMock.findOneById(1L)).thenReturn(first);

		this.mvc.perform(MockMvcRequestBuilders.get("/session/1")).andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].topic", Matchers.is("test topic 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].category", Matchers.is("test category 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].description", Matchers.is("test description 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].owner", Matchers.is("test owner 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].status", Matchers.is("test status 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].meetingRoom", Matchers.is("test room 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].meetingTime", Matchers.is(new Date(testTime))))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].file", Matchers.is(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].summary", Matchers.is("test summary 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].visibility", Matchers.is(true))).andReturn();

		Mockito.verify(sessionServiceMock, Mockito.times(1)).findOneById(1L);
		Mockito.verifyNoMoreInteractions(sessionServiceMock);
	}
}
