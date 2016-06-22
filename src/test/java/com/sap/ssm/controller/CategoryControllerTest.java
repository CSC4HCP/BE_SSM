package com.sap.ssm.controller;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.sap.ssm.WebTest;
import com.sap.ssm.persistence.model.Category;
import com.sap.ssm.persistence.repository.CategoryRepository;

import net.minidev.json.JSONObject;

public class CategoryControllerTest extends WebTest {
    @Autowired
    private CategoryRepository categoryRepositoryMock;

    /**
     * Test for API - <b>"/category"</b> Method - <b>"GET"</b>
     */
    @Test
    public void findAll_shouldReturnAllLogs() throws Exception {
	Category first = new Category();
	Category second = new Category();

	first.setId(1L);
	first.setName("test name 1");
	second.setId(2L);
	second.setName("test name 2");
	categoryRepositoryMock.saveAndFlush(first);
	categoryRepositoryMock.saveAndFlush(second);

	this.mvc.perform(MockMvcRequestBuilders.get("/category")).andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("test name 1")))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(2)))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].name", Matchers.is("test name 2"))).andReturn();

	categoryRepositoryMock.deleteAll();
    }

    /**
     * Test for API - <b>"/category/{id}"</b> Method - <b>"PUT"</b>
     */
    @Test
    public void updateOne_shouldReturnUpdatedCategory() throws Exception {
	Category first = new Category();
	first.setId(1L);
	first.setName("test name 1");

	categoryRepositoryMock.saveAndFlush(first);

	JSONObject jsonObject = new JSONObject();
	jsonObject.put("id", "1");
	jsonObject.put("name", "test name 2");

	String testRequest = jsonObject.toJSONString();

	this.mvc.perform(
		MockMvcRequestBuilders.put("/category/1").contentType(MediaType.APPLICATION_JSON).content(testRequest))
		.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
		.andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("test name 2"))).andReturn();
	categoryRepositoryMock.deleteAll();
    }

    /**
     * Test for API - <b>"/category/{id}"</b> Method - <b>"DELETE"</b>
     */
    @Test
    public void deleteOne_shouldReturnSuccess() throws Exception {
	Category first = new Category();
	first.setId(1L);
	first.setName("test content 1");
	categoryRepositoryMock.saveAndFlush(first);
	this.mvc.perform(MockMvcRequestBuilders.delete("/category/1")).andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().string("Delete Category successfully"));
	this.mvc.perform(MockMvcRequestBuilders.get("/category")).andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0))).andReturn();
	categoryRepositoryMock.deleteAll();
    }

    /**
     * Test for API - <b>"/category"</b> <br> Method - <b>"GET"</b>
     */
    @Test
    public void findOneById_shouldReturnCategory() throws Exception {
	Category first = new Category();
	Category second = new Category();

	first.setId(1L);
	first.setName("test name 1");
	second.setId(2L);
	second.setName("test name 2");
	categoryRepositoryMock.saveAndFlush(first);
	categoryRepositoryMock.saveAndFlush(second);

	this.mvc.perform(MockMvcRequestBuilders.get("/category/2")).andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(2)))
		.andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("test name 2")));

	this.mvc.perform(MockMvcRequestBuilders.get("/category/1")).andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
		.andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("test name 1"))).andReturn();

	categoryRepositoryMock.deleteAll();
    }

    /**
     * Test for API - <b>"/category"</b> Method - <b>"POST"</b>
     */
    @Test
    public void createOne_shouldReturnCreatedcategory() throws Exception {
	JSONObject jsonObject = new JSONObject();
	jsonObject.put("id", 1L);
	jsonObject.put("name", "test name 1");

	String testRequest = jsonObject.toJSONString();

	this.mvc.perform(
		MockMvcRequestBuilders.post("/category").contentType(MediaType.APPLICATION_JSON).content(testRequest))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
		.andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("test name 1"))).andReturn();
    }
}
