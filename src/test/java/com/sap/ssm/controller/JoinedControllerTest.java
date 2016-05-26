/**
 * 
 */
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
import com.sap.ssm.persistence.model.Joined;
import com.sap.ssm.persistence.repository.JoinedRepository;

/**
 * @author I326996 David Lin
 *
 */
public class JoinedControllerTest extends WebTest {

	@Autowired
	private JoinedRepository joinedRepositoryMock;

	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
	String timeString = simpleDateFormat.format(timestamp);

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
}
