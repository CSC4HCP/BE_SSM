package com.sap.ssm.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.sap.ssm.WebTest;
import com.sap.ssm.persistence.model.Session;
import com.sap.ssm.persistence.repository.SessionRepository;

public class SessionControllerTest extends WebTest {

	@Autowired
	private SessionRepository sessionRepositoryMock;

	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

	@Test
	public void findAll_ShouldReturnFoundSessions() throws Exception {

		Session first = new Session();
		Session second = new Session();

		long testTimeMillis = System.currentTimeMillis();
		Timestamp testTime = new Timestamp(testTimeMillis);
		String testTimeString = simpleDateFormat.format(testTime);

		first.setTopic("test topic 1");
		first.setCategory("test category 1");
		first.setDescription("test description 1");
		first.setOwner("test owner 1");
		first.setStatus("test status 1");
		first.setMeetingRoom("test room 1");
		first.setMeetingTime(testTime);
		first.setFile(1);
		first.setSummary("test summary 1");
		first.setVisibility(true);

		second.setTopic("test topic 2");
		second.setCategory("test category 2");
		second.setDescription("test description 2");
		second.setOwner("test owner 2");
		second.setStatus("test status 2");
		second.setMeetingRoom("test room 2");
		second.setMeetingTime(testTime);
		second.setFile(2);
		second.setSummary("test summary 2");
		second.setVisibility(false);

		sessionRepositoryMock.saveAndFlush(first);
		sessionRepositoryMock.saveAndFlush(second);

		this.mvc.perform(MockMvcRequestBuilders.get("/session")).andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].topic", Matchers.is("test topic 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].category", Matchers.is("test category 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].description", Matchers.is("test description 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].owner", Matchers.is("test owner 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].status", Matchers.is("test status 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].meetingRoom", Matchers.is("test room 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].meetingTime", Matchers.is(testTimeString)))
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
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].meetingTime", Matchers.is(testTimeString)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].file", Matchers.is(2)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].summary", Matchers.is("test summary 2")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].visibility", Matchers.is(false))).andReturn();
	}

	@Test
	public void findOneById_ShouldReturnFoundSession() throws Exception {

		Session first = new Session();

		long testTimeMillis = System.currentTimeMillis();
		Timestamp testTime = new Timestamp(testTimeMillis);
		String testTimeString = simpleDateFormat.format(testTime);

		first.setId(1L);
		first.setTopic("test topic 1");
		first.setCategory("test category 1");
		first.setDescription("test description 1");
		first.setOwner("test owner 1");
		first.setStatus("test status 1");
		first.setMeetingRoom("test room 1");
		first.setMeetingTime(testTime);
		first.setFile(1);
		first.setSummary("test summary 1");
		first.setVisibility(true);

		sessionRepositoryMock.saveAndFlush(first);

		this.mvc.perform(MockMvcRequestBuilders.get("/session/1")).andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.topic", Matchers.is("test topic 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.category", Matchers.is("test category 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.description", Matchers.is("test description 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.owner", Matchers.is("test owner 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is("test status 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.meetingRoom", Matchers.is("test room 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.meetingTime", Matchers.is(testTimeString)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.file", Matchers.is(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.summary", Matchers.is("test summary 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.visibility", Matchers.is(true))).andReturn();
	}
}
