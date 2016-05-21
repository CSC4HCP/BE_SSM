package com.sap.ssm.controller;

import java.sql.Timestamp;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.sap.ssm.WebTest;
import com.sap.ssm.persistence.model.Comment;
import com.sap.ssm.persistence.repository.CommentRepository;

import net.minidev.json.JSONObject;

/*
 * @author Likai Deng
 */
public class CommentControllerTest extends WebTest {
	@Autowired
	private CommentRepository commentRepositoryMock;
	Timestamp timestamp1 = new Timestamp(1463833120001L);
	Timestamp timestamp2 = new Timestamp(1463833120002L);

	/**
	 * Test for API - <b>"/comment"</b> Method - <b>"GET"</b>
	 */
	@Test
	public void findAll_shouldReturnAllComments() throws Exception {
		Comment first = new Comment();
		Comment second = new Comment();
		
		first.setId(1L);
		first.setAuthor("test author 1");
		first.setSession(1L);
		first.setContent("test content 1");
		first.setDate(timestamp1);

		second.setId(2L);
		second.setAuthor("test author 2");
		second.setSession(2L);
		second.setContent("test content 2");
		second.setDate(timestamp2);

		commentRepositoryMock.saveAndFlush(first);
		commentRepositoryMock.saveAndFlush(second);

		this.mvc.perform(MockMvcRequestBuilders.get("/comment")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].author", Matchers.is("test author 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].session", Matchers.is(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].content", Matchers.is("test content 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].date", Matchers.is(1463833120001L)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(2)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].author", Matchers.is("test author 2")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].session", Matchers.is(2)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].date", Matchers.is(1463833120002L)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].content", Matchers.is("test content 2"))).andReturn();

		commentRepositoryMock.deleteAll();
	}

	/**
	 * Test for API - <b>"/comment"</b> Method - <b>"POST"</b>
	 */
	@Test
	public void createOne_shouldReturnCreatedComment() throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("author", "test author 1");
		jsonObject.put("session", 1L);
		jsonObject.put("content", "test content 1");
		jsonObject.put("date", "2013-09-09");

		String testRequest = jsonObject.toJSONString();

		this.mvc.perform(
				MockMvcRequestBuilders.post("/comment").contentType(MediaType.APPLICATION_JSON).content(testRequest))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.author", Matchers.is("test author 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.session", Matchers.is(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.date", Matchers.is(1378684800000L)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("test content 1"))).andReturn();
	}

	/**
	 * Test for API - <b>"/comment/{id}"</b> Method - <b>"DELETE"</b>
	 */
	@Test
	public void deleteOne_shouldReturnSuccessMessage() throws Exception {
		Comment first = new Comment();
		first.setId(1L);
		first.setAuthor("test author 1");
		first.setSession(1L);
		first.setContent("test content 1");
		first.setDate(timestamp1);
		commentRepositoryMock.saveAndFlush(first);
		this.mvc.perform(MockMvcRequestBuilders.delete("/comment" + "/1"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.content().string("Delete session successfully")).andReturn();

		commentRepositoryMock.deleteAll();
	}

	/**
	 * Test for API - <b>"/comment/{id}"</b> Method - <b>"PUT"</b>
	 */
	@Test
	public void updateOne_shouldReturnUpdatedUser() throws Exception {
		Comment first = new Comment();
		first.setId(1L);
		first.setAuthor("test author 1");
		first.setSession(1L);
		first.setContent("test content 1");
		first.setDate(timestamp1);

		commentRepositoryMock.saveAndFlush(first);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("author", "test author 2");
		jsonObject.put("session", 2L);
		jsonObject.put("content", "test content 2");
		jsonObject.put("date", "2013-09-10");

		String testRequest = jsonObject.toJSONString();

		this.mvc.perform(
				MockMvcRequestBuilders.put("/comment/1").contentType(MediaType.APPLICATION_JSON).content(testRequest))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.jsonPath("$.author", Matchers.is("test anthor 2")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.session", Matchers.is(2)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("test content 2")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.date", Matchers.is(1378684800000L))).andReturn();
	}

	/**
	 * Test for API - <b>"/comment/{id}"</b> Method - <b>"GET"</b>
	 */
	@Test
	public void findOneById_shouldReturnFoundComments() throws Exception {
		Comment first = new Comment();
		Comment second = new Comment();

		first.setId(1L);
		first.setAuthor("test author 1");
		first.setSession(1L);
		first.setContent("test content 1");
		first.setDate(timestamp1);

		second.setId(2L);
		second.setAuthor("test author 2");
		second.setSession(2L);
		second.setContent("test content 2");
		second.setDate(timestamp2);

		commentRepositoryMock.saveAndFlush(first);
		commentRepositoryMock.saveAndFlush(second);

		this.mvc.perform(MockMvcRequestBuilders.get("/comment/1")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.author", Matchers.is("test author 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.session", Matchers.is(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("test content 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.date", Matchers.is(1463833120001L))).andReturn();

		this.mvc.perform(MockMvcRequestBuilders.get("/comment/2")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(2)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.author", Matchers.is("test author 2")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.session", Matchers.is(2)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("test content 2")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.date", Matchers.is(1463833120002L))).andReturn();

		commentRepositoryMock.deleteAll();
	}

	/**
	 * Test for API - <b>"/comment"</b> with RequestParams Method - <b>"GET"</b>
	 */
	@Test
	public void findByConditions_shouldReturnFoundComments() throws Exception {
		Comment first = new Comment();
		Comment second = new Comment();

		first.setId(1L);
		first.setAuthor("test author 1");
		first.setSession(1L);
		first.setContent("test content 1");
		first.setDate(timestamp1);

		second.setId(2L);
		second.setAuthor("test author 2");
		second.setSession(2L);
		second.setContent("test content 2");
		second.setDate(timestamp2);

		commentRepositoryMock.saveAndFlush(first);
		commentRepositoryMock.saveAndFlush(second);

		this.mvc.perform(MockMvcRequestBuilders.get("/comment").param("author", "test author 1"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].author", Matchers.is("test author 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].session", Matchers.is(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].content", Matchers.is("test content 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].date", Matchers.is(1463833120001L))).andReturn();

		this.mvc.perform(MockMvcRequestBuilders.get("/comment").param("session", "1"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].author", Matchers.is("test author 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].session", Matchers.is(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].content", Matchers.is("test content 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].date", Matchers.is(1463833120001L))).andReturn();

		this.mvc.perform(MockMvcRequestBuilders.get("/comment").param("author", "test author 1").param("session", "1"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].author", Matchers.is("test author 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].session", Matchers.is(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].content", Matchers.is("test content 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].date", Matchers.is(1463833120001L))).andReturn();
		
		this.mvc.perform(MockMvcRequestBuilders.get("/comment").param("author", "test author 1").param("session", "2"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.empty())).andReturn();

		commentRepositoryMock.deleteAll();
	}
}
