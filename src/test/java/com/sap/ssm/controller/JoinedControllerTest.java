/**
 * 
 */
package com.sap.ssm.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.sql.Date;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.sap.ssm.WebTest;
import com.sap.ssm.persistence.model.Joined;
import com.sap.ssm.persistence.repository.JoinedRepository;

import net.minidev.json.JSONObject;

/**
 * @author I326996 David Lin
 */
public class JoinedControllerTest extends WebTest {

	@Autowired
	private JoinedRepository joinedRepositoryMock;

	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	String timeString = simpleDateFormat.format(timestamp);

	Date date = new Date(System.currentTimeMillis());
	String dateString = simpleDateFormat.format(date);

	/**
	 * Test API - <b>"/api/joined"</b><br>
	 * Method - <b>"POST"</b>
	 * 
	 * @throws Exception
	 */
	@Test
	public void createOne_ShouldReturnAJoinedObject() throws Exception {
		JSONObject jsonJoinedObject = new JSONObject();
		jsonJoinedObject.put("id", 1L);
		jsonJoinedObject.put("userId", "test user 1");
		jsonJoinedObject.put("session", 1L);
		jsonJoinedObject.put("date", dateString);

		String jsonJoinedString = jsonJoinedObject.toJSONString();

		this.mvc.perform(MockMvcRequestBuilders.post("/joined").content(jsonJoinedString.getBytes())
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print()).andReturn();
	}

	/**
	 * Test API - <b>"/api/joined"</b><br>
	 * Method - <b>"GET"</b><br>
	 * Param - <b>"userId"</b>
	 * 
	 * @throws Exception
	 */
	@Test
	public void findByUserId_ShouldReturnAJoinedObject() throws Exception {

		Joined joined = new Joined();
		joined.setId(1L);
		joined.setUserId("test user 1");
		joined.setSession(1L);
		joined.setDate(timestamp);

		joinedRepositoryMock.saveAndFlush(joined);

		this.mvc.perform(MockMvcRequestBuilders.get("/joined").param("userId", "test user 1"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].userId", Matchers.is("test user 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].session", Matchers.is(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].date", Matchers.is(timeString))).andReturn();

		joinedRepositoryMock.deleteAll();
	}

	/**
	 * Test API - <b>"/api/joined"</b><br>
	 * Method - <b>"POST"</b><br>
	 * Param - <b>"session"</b>
	 * 
	 * @throws Exception
	 */
	@Test
	public void findBySession_ShouldReturnAJoinedObject() throws Exception {

		Joined joined = new Joined();
		joined.setId(1L);
		joined.setUserId("test user 1");
		joined.setSession(1L);
		joined.setDate(timestamp);

		joinedRepositoryMock.saveAndFlush(joined);

		this.mvc.perform(MockMvcRequestBuilders.get("/joined").param("session", String.valueOf(1)))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].userId", Matchers.is("test user 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].session", Matchers.is(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].date", Matchers.is(timeString))).andReturn();

		joinedRepositoryMock.deleteAll();
	}
}
