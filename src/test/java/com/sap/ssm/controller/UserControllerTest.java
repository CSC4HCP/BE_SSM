package com.sap.ssm.controller;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.sap.ssm.WebTest;
import com.sap.ssm.persistence.model.User;
import com.sap.ssm.persistence.repository.UserRepository;

import net.minidev.json.JSONObject;

/**
 * @author Likai Deng
 */
public class UserControllerTest extends WebTest {
	@Autowired
	private UserRepository userRepositoryMock;

	/**
	 * Test for API - <b>"/user"</b> Method - <b>"GET"</b>
	 */
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
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].email", Matchers.is("test email 2"))).andReturn();

		userRepositoryMock.deleteAll();
	}

	/**
	 * Test for API - <b>"/user/{id}"</b> Method - <b>"GET"</b>
	 */
	@Test
	public void findOneById_shouldReturnUserById() throws Exception {
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

		this.mvc.perform(MockMvcRequestBuilders.get("/user/test name 1"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("test name 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.is("test firstname 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.lastName", Matchers.is("test lastname 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.team", Matchers.is("test team 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.role", Matchers.is("test role 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is("test email 1"))).andReturn();

		userRepositoryMock.deleteAll();
	}

	/**
	 * Test for API - <b>"/user"</b> Method - <b>"POST"</b>
	 */
	@Test
	public void createOne_shouldReturnCreatedUser() throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", "test name 1");
		jsonObject.put("firstName", "test firstname 1");
		jsonObject.put("lastName", "test lastname 1");
		jsonObject.put("role", "test role 1");
		jsonObject.put("email", "test email 1");
		jsonObject.put("team", "test team 1");
		String testRequest = jsonObject.toJSONString();

		this.mvc.perform(
				MockMvcRequestBuilders.post("/user").contentType(MediaType.APPLICATION_JSON).content(testRequest))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("test name 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.is("test firstname 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.lastName", Matchers.is("test lastname 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.team", Matchers.is("SAP SSM")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.role", Matchers.is("Everyone")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is("test email 1"))).andReturn();

		userRepositoryMock.deleteAll();
	}

	/**
	 * Test for API - <b>"/user/{id}"</b> Method - <b>"PUT"</b>
	 */
	@Test
	public void updateOne_shouldReturnUpdatedUser() throws Exception {
		User first = new User();
		first.setName("test name 1");
		first.setFirstName("test firstname 1");
		first.setLastName("test lastname 1");
		first.setEmail("test email 1");
		first.setRole("test role 1");
		first.setTeam("test team 1");

		userRepositoryMock.saveAndFlush(first);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", "test name 1");
		jsonObject.put("firstName", "test firstname 2");
		jsonObject.put("lastName", "test lastname 2");
		jsonObject.put("role", "test role 2");
		jsonObject.put("email", "test email 2");
		jsonObject.put("team", "test team 2");
		String testRequest = jsonObject.toJSONString();

		this.mvc.perform(MockMvcRequestBuilders.put("/user/test name 1").contentType(MediaType.APPLICATION_JSON)
				.content(testRequest)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("test name 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.is("test firstname 2")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.lastName", Matchers.is("test lastname 2")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.team", Matchers.is("test team 2")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.role", Matchers.is("test role 2")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is("test email 2"))).andReturn();

		userRepositoryMock.deleteAll();
	}

	/**
	 * Test for API - <b>"/user/{id}"</b> Method - <b>"DELETE"</b>
	 */
	@Test
	public void deleteOne_shouldReturnSuccess() throws Exception {
		User first = new User();
		first.setName("test name 1");
		first.setFirstName("test firstname 1");
		first.setLastName("test lastname 1");
		first.setEmail("test email 1");
		first.setRole("test role 1");
		first.setTeam("test team 1");
		userRepositoryMock.saveAndFlush(first);
		this.mvc.perform(MockMvcRequestBuilders.delete("/user/test name 1"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("Delete Success")).andReturn();

		userRepositoryMock.deleteAll();
	}
}
