package com.sap.ssm.controller;

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
import com.sap.ssm.persistence.model.Notification;
import com.sap.ssm.persistence.repository.NotificationRepository;

import net.minidev.json.JSONObject;

/**
 * @author Yuan Zhang
 */
public class NotificationControllerTest extends WebTest {
	@Autowired
	private NotificationRepository notificationRepositoryMock;

	/**
	 * Test for API - <b>"/notify"</b> Method - <b>"GET"</b>
	 */
	@Test
	public void findAll_shouldReturnAllNotifications() throws Exception {
		Notification first = new Notification();
		Notification second = new Notification();

		first.setId(1L);
		first.setContent("test content 1");
		first.setTarget("test target 1");
		first.setChecked(false);
		second.setId(2L);
		second.setContent("test content 2");
		second.setTarget("test target 2");
		second.setChecked(true);
		notificationRepositoryMock.saveAndFlush(first);
		notificationRepositoryMock.saveAndFlush(second);

		this.mvc.perform(MockMvcRequestBuilders.get("/notify")).andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].content", Matchers.is("test content 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].target", Matchers.is("test target 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].checked", Matchers.is(false)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(2)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].content", Matchers.is("test content 2")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].target", Matchers.is("test target 2")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].checked", Matchers.is(true))).andReturn();

		notificationRepositoryMock.deleteAll();
	}

	/**
	 * Test for API - <b>"/notify"</b> <br>
	 * Method - <b>"GET"</b>
	 */
	@Test
	public void findOneBytarget_shouldReturnNotification() throws Exception {
		Notification first = new Notification();
		Notification second = new Notification();

		first.setId(1L);
		first.setContent("test content 1");
		first.setTarget("test target 1");
		first.setChecked(false);
		second.setId(2L);
		second.setContent("test content 2");
		second.setTarget("test target 2");
		second.setChecked(true);
		notificationRepositoryMock.saveAndFlush(first);
		notificationRepositoryMock.saveAndFlush(second);

		this.mvc.perform(MockMvcRequestBuilders.get("/notify").param("target", "test target 1"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].content", Matchers.is("test content 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].target", Matchers.is("test target 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].checked", Matchers.is(false))).andReturn();

		notificationRepositoryMock.deleteAll();
	}

	/**
	 * Test for API - <b>"/notify"</b> <br>
	 * Method - <b>"GET"</b>
	 */
	@Test
	public void findNotificationByChecked_shouldReturnNotification() throws Exception {
		Notification first = new Notification();
		Notification second = new Notification();

		first.setId(1L);
		first.setContent("test content 1");
		first.setTarget("test target 1");
		first.setChecked(false);
		second.setId(2L);
		second.setContent("test content 2");
		second.setTarget("test target 2");
		second.setChecked(true);
		notificationRepositoryMock.saveAndFlush(first);
		notificationRepositoryMock.saveAndFlush(second);

		this.mvc.perform(MockMvcRequestBuilders.get("/notify").param("checked", "false"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].content", Matchers.is("test content 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].target", Matchers.is("test target 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].checked", Matchers.is(false))).andReturn();

		notificationRepositoryMock.deleteAll();
	}

	/**
	 * Test for API - <b>"/notify/{id}"</b> Method - <b>"PUT"</b>
	 */
	@Test
	public void updateOne_shouldReturnUpdatedNotification() throws Exception {
		Notification first = new Notification();
		first.setId(1L);
		first.setContent("test content 1");
		first.setTarget("test target 1");
		first.setChecked(false);

		notificationRepositoryMock.saveAndFlush(first);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", "1");
		jsonObject.put("content", "test content 2");
		jsonObject.put("target", "test target 2");
		jsonObject.put("checked", "true");

		String testRequest = jsonObject.toJSONString();

		this.mvc.perform(
				MockMvcRequestBuilders.put("/notify/1").contentType(MediaType.APPLICATION_JSON).content(testRequest))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("test content 2")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.target", Matchers.is("test target 2")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.checked", Matchers.is(true))).andReturn();
		notificationRepositoryMock.deleteAll();

	}

	/**
	 * Test for API - <b>"/notify/{id}"</b> Method - <b>"DELETE"</b>
	 */
	@Test
	public void deleteOne_shouldReturnSuccess() throws Exception {
		Notification first = new Notification();
		first.setId(1L);
		first.setContent("test content 1");
		first.setTarget("test target 1");
		first.setChecked(false);
		notificationRepositoryMock.saveAndFlush(first);
		this.mvc.perform(MockMvcRequestBuilders.delete("/notify/1")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("Delete notification successfully")).andReturn();

		notificationRepositoryMock.deleteAll();
	}

	/**
	 * Test for API - <b>"/notify"</b> <br>
	 * Method - <b>"GET"</b>
	 */
	@Test
	public void findByCheckedAndTarget_shouldReturnNotification() throws Exception {
		Notification first = new Notification();
		Notification second = new Notification();
		Notification third = new Notification();

		first.setId(1L);
		first.setContent("test content 1");
		first.setTarget("test target 1");
		first.setChecked(false);
		second.setId(2L);
		second.setContent("test content 2");
		second.setTarget("test target 2");
		second.setChecked(true);
		third.setId(3L);
		third.setContent("test content 3");
		third.setTarget("test target 1");
		third.setChecked(true);
		notificationRepositoryMock.saveAndFlush(first);
		notificationRepositoryMock.saveAndFlush(second);
		notificationRepositoryMock.saveAndFlush(third);

		MultiValueMap<String, String> params1 = new LinkedMultiValueMap<String, String>();
		;
		params1.add("target", "test target 1");
		params1.add("checked", "true");
		this.mvc.perform(MockMvcRequestBuilders.get("/notify").params(params1))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(3)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].content", Matchers.is("test content 3")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].target", Matchers.is("test target 1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].checked", Matchers.is(true))).andReturn();

		notificationRepositoryMock.deleteAll();
	}

	/**
	 * Test for API - <b>"/notify/"</b> Method - <b>"DELETE"</b>
	 */
	@Test
	public void deleteInBatch_shouldReturnSuccess() throws Exception {
		Notification first = new Notification();
		Notification second = new Notification();
		first.setId(1L);
		first.setContent("test content 1");
		first.setTarget("test target 1");
		first.setChecked(false);
		first.setId(2L);
		first.setContent("test content 2");
		first.setTarget("test target 2");
		first.setChecked(true);
		notificationRepositoryMock.saveAndFlush(first);
		notificationRepositoryMock.saveAndFlush(second);

		String testRequest = "[1,2]";
		this.mvc.perform(
				MockMvcRequestBuilders.delete("/notify").contentType(MediaType.APPLICATION_JSON).content(testRequest))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("Delete notifications in batch successfully"))
				.andReturn();

		notificationRepositoryMock.deleteAll();
	}

}
