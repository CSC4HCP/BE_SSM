/**
 * 
 */
package com.sap.ssm.controller;

import com.sap.ssm.WebTest;
import com.sap.ssm.persistence.model.User;
import com.sap.ssm.persistence.repository.SessionRepository;
import com.sap.ssm.persistence.repository.UserRepository;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @author denglikai
 *
 */
public class UserControllerTest extends WebTest {
	@Autowired
	private UserRepository userRepositoryMock;

	@Test
	public void findAll_shouldReturnAllUsers() throws Exception {
		User first = new User();
		User second = new User();

		first.setName("test name 1");
		first.setFirstName("test firstname 1");
		first.setLastName("test lastname 1");
		first.setEmail("test email 1");
		first.setRole("test role 1");
		first.setTeam("test team 1");

		second.setName("test name 2");
		second.setFirstName("test firstname 2");
		second.setLastName("test lastname 2");
		second.setEmail("test email 2");
		second.setRole("test role 2");
		second.setTeam("test team 2");

		userRepositoryMock.saveAndFlush(first);
		userRepositoryMock.saveAndFlush(second);
		
		this.mvc.perform(MockMvcRequestBuilders.get("/user")).andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("test name 1")))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName", Matchers.is("test firstname 1")))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].lastName", Matchers.is("test lastname 1")))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].team", Matchers.is("test team 1")))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].role", Matchers.is("test role 1")))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].email", Matchers.is("test email 1")))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].name", Matchers.is("test name 2")))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].firstName", Matchers.is("test firstname 2")))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].lastName", Matchers.is("test lastname 2")))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].team", Matchers.is("test team 2")))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].role", Matchers.is("test role 2")))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].email", Matchers.is("test email 2")));		
	}

}
