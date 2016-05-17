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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

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

		second.setId(2L);
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

		sessionRepositoryMock.deleteAll();
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

		sessionRepositoryMock.deleteAll();
	}

	@Test
	public void createOne_ShouldReturnSession() throws Exception {

		String testTimeString = "2016-09-10T22:09:13+0800";

		String testjson = "{\"id\":\"1\",\"topic\":\"test topic 1\",\"category\":\"test category 1\","
				+ "\"description\":\"test description 1\",\"owner\":\"test owner 1\",\"status\":\"test status 1\","
				+ "\"meetingRoom\":\"test room 1\",\"meetingTime\":\"2016-09-10T14:9:13.000Z\",\"file\":1,\"summary\":\"test summary 1\","
				+ "\"visibility\":\"true\"}";

		this.mvc.perform(MockMvcRequestBuilders.post("/session").characterEncoding("UTF-8")
				.contentType(MediaType.APPLICATION_JSON).content(testjson))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print())
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

		sessionRepositoryMock.deleteAll();
	}

	@Test
	public void updateOne_ShouldReturnUpdatedSession() throws Exception {

		Session first = new Session();

		long testTimeMillis = System.currentTimeMillis();
		Timestamp testTime = new Timestamp(testTimeMillis);

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

		String testjson = "{\"id\":\"1\",\"topic\":\"test topic 2\",\"category\":\"test category 2\","
				+ "\"description\":\"test description 2\",\"owner\":\"test owner 2\",\"status\":\"test status 2\","
				+ "\"meetingRoom\":\"test room 2\",\"meetingTime\":\"2016-09-10T14:9:23.000Z\",\"file\":2,\"summary\":\"test summary 2\","
				+ "\"visibility\":\"true\"}";
		this.mvc.perform(MockMvcRequestBuilders.put("/session/1").characterEncoding("UTF-8")
				.contentType(MediaType.APPLICATION_JSON).content(testjson))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.topic", Matchers.is("test topic 2")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.category", Matchers.is("test category 2")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.description", Matchers.is("test description 2")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.owner", Matchers.is("test owner 2")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is("test status 2")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.meetingRoom", Matchers.is("test room 2")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.meetingTime", Matchers.is("2016-09-10T22:09:23+0800")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.file", Matchers.is(2)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.summary", Matchers.is("test summary 2")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.visibility", Matchers.is(true))).andReturn();
		sessionRepositoryMock.deleteAll();
	}

	@Test
	public void findOneByOwner_ShouldReturnFoundSession() throws Exception {

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

		this.mvc.perform(MockMvcRequestBuilders.get("/session").param("owner", "test owner 1"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
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
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].visibility", Matchers.is(true))).andReturn();

		sessionRepositoryMock.deleteAll();
	}

	@Test
	public void findByCategory_ShouldReturnFoundSessions() throws Exception {

		Session first = new Session();
		Session second = new Session();

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

		second.setId(2L);
		second.setTopic("test topic 2");
		second.setCategory("test category 1");
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

		this.mvc.perform(MockMvcRequestBuilders.get("/session").param("category", "test category 1"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print())
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
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].category", Matchers.is("test category 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].description", Matchers.is("test description 2")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].owner", Matchers.is("test owner 2")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].status", Matchers.is("test status 2")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].meetingRoom", Matchers.is("test room 2")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].meetingTime", Matchers.is(testTimeString)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].file", Matchers.is(2)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].summary", Matchers.is("test summary 2")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].visibility", Matchers.is(false))).andReturn();

		sessionRepositoryMock.deleteAll();
	}

	@Test
	public void findByStatus_ShouldReturnFoundSessions() throws Exception {

		Session first = new Session();
		Session second = new Session();

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

		second.setId(2L);
		second.setTopic("test topic 2");
		second.setCategory("test category 2");
		second.setDescription("test description 2");
		second.setOwner("test owner 2");
		second.setStatus("test status 1");
		second.setMeetingRoom("test room 2");
		second.setMeetingTime(testTime);
		second.setFile(2);
		second.setSummary("test summary 2");
		second.setVisibility(false);

		sessionRepositoryMock.saveAndFlush(first);
		sessionRepositoryMock.saveAndFlush(second);

		this.mvc.perform(MockMvcRequestBuilders.get("/session").param("status", "test status 1"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print())
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
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].status", Matchers.is("test status 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].meetingRoom", Matchers.is("test room 2")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].meetingTime", Matchers.is(testTimeString)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].file", Matchers.is(2)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].summary", Matchers.is("test summary 2")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].visibility", Matchers.is(false))).andReturn();

		sessionRepositoryMock.deleteAll();
	}

	@Test
	public void findByVisibility_ShouldReturnFoundSessions() throws Exception {

		Session first = new Session();
		Session second = new Session();

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

		second.setId(2L);
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

		this.mvc.perform(MockMvcRequestBuilders.get("/session").param("visibility", "false"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(2)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].topic", Matchers.is("test topic 2")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].category", Matchers.is("test category 2")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].description", Matchers.is("test description 2")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].owner", Matchers.is("test owner 2")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].status", Matchers.is("test status 2")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].meetingRoom", Matchers.is("test room 2")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].meetingTime", Matchers.is(testTimeString)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].file", Matchers.is(2)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].summary", Matchers.is("test summary 2")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].visibility", Matchers.is(false))).andReturn();

		sessionRepositoryMock.deleteAll();
	}

	@Test
	public void findByOwnerAndCategory_ShouldReturnFoundSessions() throws Exception {

		Session first = new Session();
		Session second = new Session();
		Session third = new Session();
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

		second.setId(2L);
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

		third.setId(3L);
		third.setTopic("test topic 3");
		third.setCategory("test category 2");
		third.setDescription("test description 3");
		third.setOwner("test owner 1");
		third.setStatus("test status 3");
		third.setMeetingRoom("test room 3");
		third.setMeetingTime(testTime);
		third.setFile(3);
		third.setSummary("test summary 3");
		third.setVisibility(false);

		sessionRepositoryMock.saveAndFlush(first);
		sessionRepositoryMock.saveAndFlush(second);
		sessionRepositoryMock.saveAndFlush(third);

		MultiValueMap<String, String> params1 = new LinkedMultiValueMap<String, String>();
		params1.add("owner", "test owner 1");
		params1.add("category", "test category 2");
		this.mvc.perform(MockMvcRequestBuilders.get("/session").params(params1))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(3)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].topic", Matchers.is("test topic 3")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].category", Matchers.is("test category 2")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].description", Matchers.is("test description 3")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].owner", Matchers.is("test owner 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].status", Matchers.is("test status 3")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].meetingRoom", Matchers.is("test room 3")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].meetingTime", Matchers.is(testTimeString)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].file", Matchers.is(3)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].summary", Matchers.is("test summary 3")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].visibility", Matchers.is(false))).andReturn();

		sessionRepositoryMock.deleteAll();
	}

	@Test
	public void findByOwnerAndStatus_ShouldReturnFoundSessions() throws Exception {

		Session first = new Session();
		Session second = new Session();
		Session third = new Session();
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

		second.setId(2L);
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

		third.setId(3L);
		third.setTopic("test topic 3");
		third.setCategory("test category 3");
		third.setDescription("test description 3");
		third.setOwner("test owner 1");
		third.setStatus("test status 2");
		third.setMeetingRoom("test room 3");
		third.setMeetingTime(testTime);
		third.setFile(3);
		third.setSummary("test summary 3");
		third.setVisibility(false);

		sessionRepositoryMock.saveAndFlush(first);
		sessionRepositoryMock.saveAndFlush(second);
		sessionRepositoryMock.saveAndFlush(third);

		MultiValueMap<String, String> params1 = new LinkedMultiValueMap<String, String>();
		params1.add("owner", "test owner 1");
		params1.add("status", "test status 2");
		this.mvc.perform(MockMvcRequestBuilders.get("/session").params(params1))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(3)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].topic", Matchers.is("test topic 3")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].category", Matchers.is("test category 3")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].description", Matchers.is("test description 3")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].owner", Matchers.is("test owner 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].status", Matchers.is("test status 2")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].meetingRoom", Matchers.is("test room 3")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].meetingTime", Matchers.is(testTimeString)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].file", Matchers.is(3)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].summary", Matchers.is("test summary 3")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].visibility", Matchers.is(false))).andReturn();

		sessionRepositoryMock.deleteAll();
	}

	@Test
	public void findByCategoryAndVisibility_ShouldReturnFoundSessions() throws Exception {

		Session first = new Session();
		Session second = new Session();
		Session third = new Session();
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

		second.setId(2L);
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

		third.setId(3L);
		third.setTopic("test topic 3");
		third.setCategory("test category 1");
		third.setDescription("test description 3");
		third.setOwner("test owner 3");
		third.setStatus("test status 3");
		third.setMeetingRoom("test room 3");
		third.setMeetingTime(testTime);
		third.setFile(3);
		third.setSummary("test summary 3");
		third.setVisibility(false);

		sessionRepositoryMock.saveAndFlush(first);
		sessionRepositoryMock.saveAndFlush(second);
		sessionRepositoryMock.saveAndFlush(third);

		MultiValueMap<String, String> params1 = new LinkedMultiValueMap<String, String>();
		params1.add("category", "test category 1");
		params1.add("visibility", "false");
		this.mvc.perform(MockMvcRequestBuilders.get("/session").params(params1))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(3)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].topic", Matchers.is("test topic 3")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].category", Matchers.is("test category 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].description", Matchers.is("test description 3")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].owner", Matchers.is("test owner 3")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].status", Matchers.is("test status 3")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].meetingRoom", Matchers.is("test room 3")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].meetingTime", Matchers.is(testTimeString)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].file", Matchers.is(3)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].summary", Matchers.is("test summary 3")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].visibility", Matchers.is(false))).andReturn();

		sessionRepositoryMock.deleteAll();
	}

	@Test
	public void findByOwnerAndVisibility_ShouldReturnFoundSessions() throws Exception {

		Session first = new Session();
		Session second = new Session();
		Session third = new Session();
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

		second.setId(2L);
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

		third.setId(3L);
		third.setTopic("test topic 3");
		third.setCategory("test category 3");
		third.setDescription("test description 3");
		third.setOwner("test owner 1");
		third.setStatus("test status 3");
		third.setMeetingRoom("test room 3");
		third.setMeetingTime(testTime);
		third.setFile(3);
		third.setSummary("test summary 3");
		third.setVisibility(false);

		sessionRepositoryMock.saveAndFlush(first);
		sessionRepositoryMock.saveAndFlush(second);
		sessionRepositoryMock.saveAndFlush(third);

		MultiValueMap<String, String> params1 = new LinkedMultiValueMap<String, String>();
		params1.add("owner", "test owner 1");
		params1.add("visibility", "false");
		this.mvc.perform(MockMvcRequestBuilders.get("/session").params(params1))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(3)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].topic", Matchers.is("test topic 3")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].category", Matchers.is("test category 3")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].description", Matchers.is("test description 3")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].owner", Matchers.is("test owner 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].status", Matchers.is("test status 3")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].meetingRoom", Matchers.is("test room 3")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].meetingTime", Matchers.is(testTimeString)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].file", Matchers.is(3)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].summary", Matchers.is("test summary 3")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].visibility", Matchers.is(false))).andReturn();

		sessionRepositoryMock.deleteAll();
	}

	@Test
	public void findByStatusAndVisibility_ShouldReturnFoundSessions() throws Exception {

		Session first = new Session();
		Session second = new Session();
		Session third = new Session();
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

		second.setId(2L);
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

		third.setId(3L);
		third.setTopic("test topic 3");
		third.setCategory("test category 3");
		third.setDescription("test description 3");
		third.setOwner("test owner 3");
		third.setStatus("test status 1");
		third.setMeetingRoom("test room 3");
		third.setMeetingTime(testTime);
		third.setFile(3);
		third.setSummary("test summary 3");
		third.setVisibility(false);

		sessionRepositoryMock.saveAndFlush(first);
		sessionRepositoryMock.saveAndFlush(second);
		sessionRepositoryMock.saveAndFlush(third);

		MultiValueMap<String, String> params1 = new LinkedMultiValueMap<String, String>();
		params1.add("status", "test status 1");
		params1.add("visibility", "false");
		this.mvc.perform(MockMvcRequestBuilders.get("/session").params(params1))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(3)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].topic", Matchers.is("test topic 3")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].category", Matchers.is("test category 3")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].description", Matchers.is("test description 3")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].owner", Matchers.is("test owner 3")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].status", Matchers.is("test status 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].meetingRoom", Matchers.is("test room 3")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].meetingTime", Matchers.is(testTimeString)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].file", Matchers.is(3)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].summary", Matchers.is("test summary 3")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].visibility", Matchers.is(false))).andReturn();

		sessionRepositoryMock.deleteAll();
	}

	@Test
	public void findByOwnerAndCategoryAndVisibility_ShouldReturnFoundSessions() throws Exception {

		Session first = new Session();
		Session second = new Session();
		Session third = new Session();
		Session forth = new Session();
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

		second.setId(2L);
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

		third.setId(3L);
		third.setTopic("test topic 3");
		third.setCategory("test category 4");
		third.setDescription("test description 3");
		third.setOwner("test owner 1");
		third.setStatus("test status 3");
		third.setMeetingRoom("test room 3");
		third.setMeetingTime(testTime);
		third.setFile(3);
		third.setSummary("test summary 3");
		third.setVisibility(false);

		forth.setId(4L);
		forth.setTopic("test topic 4");
		forth.setCategory("test category 4");
		forth.setDescription("test description 4");
		forth.setOwner("test owner 4");
		forth.setStatus("test status 4");
		forth.setMeetingRoom("test room 4");
		forth.setMeetingTime(testTime);
		forth.setFile(4);
		forth.setSummary("test summary 4");
		forth.setVisibility(true);

		sessionRepositoryMock.saveAndFlush(first);
		sessionRepositoryMock.saveAndFlush(second);
		sessionRepositoryMock.saveAndFlush(third);
		sessionRepositoryMock.saveAndFlush(forth);

		MultiValueMap<String, String> params1 = new LinkedMultiValueMap<String, String>();
		params1.add("owner", "test owner 1");
		params1.add("visibility", "false");
		params1.add("category", "test category 4");
		this.mvc.perform(MockMvcRequestBuilders.get("/session").params(params1))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(3)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].topic", Matchers.is("test topic 3")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].category", Matchers.is("test category 4")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].description", Matchers.is("test description 3")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].owner", Matchers.is("test owner 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].status", Matchers.is("test status 3")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].meetingRoom", Matchers.is("test room 3")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].meetingTime", Matchers.is(testTimeString)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].file", Matchers.is(3)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].summary", Matchers.is("test summary 3")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].visibility", Matchers.is(false))).andReturn();

		sessionRepositoryMock.deleteAll();
	}

	@Test
	public void findByOwnerAndStatusAndVisibility_ShouldReturnFoundSessions() throws Exception {

		Session first = new Session();
		Session second = new Session();
		Session third = new Session();
		Session forth = new Session();
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

		second.setId(2L);
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

		third.setId(3L);
		third.setTopic("test topic 3");
		third.setCategory("test category 3");
		third.setDescription("test description 3");
		third.setOwner("test owner 1");
		third.setStatus("test status 4");
		third.setMeetingRoom("test room 3");
		third.setMeetingTime(testTime);
		third.setFile(3);
		third.setSummary("test summary 3");
		third.setVisibility(false);

		forth.setId(4L);
		forth.setTopic("test topic 4");
		forth.setCategory("test category 4");
		forth.setDescription("test description 4");
		forth.setOwner("test owner 4");
		forth.setStatus("test status 4");
		forth.setMeetingRoom("test room 4");
		forth.setMeetingTime(testTime);
		forth.setFile(4);
		forth.setSummary("test summary 4");
		forth.setVisibility(true);

		sessionRepositoryMock.saveAndFlush(first);
		sessionRepositoryMock.saveAndFlush(second);
		sessionRepositoryMock.saveAndFlush(third);
		sessionRepositoryMock.saveAndFlush(forth);

		MultiValueMap<String, String> params1 = new LinkedMultiValueMap<String, String>();
		params1.add("owner", "test owner 1");
		params1.add("visibility", "false");
		params1.add("status", "test status 4");
		this.mvc.perform(MockMvcRequestBuilders.get("/session").params(params1))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(3)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].topic", Matchers.is("test topic 3")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].category", Matchers.is("test category 3")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].description", Matchers.is("test description 3")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].owner", Matchers.is("test owner 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].status", Matchers.is("test status 4")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].meetingRoom", Matchers.is("test room 3")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].meetingTime", Matchers.is(testTimeString)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].file", Matchers.is(3)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].summary", Matchers.is("test summary 3")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].visibility", Matchers.is(false))).andReturn();

		sessionRepositoryMock.deleteAll();
	}
}
