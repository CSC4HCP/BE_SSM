/**
 * 
 */
package com.sap.ssm.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.sap.ssm.WebTest;

/**
 * @author I326996 David Lin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = com.sap.ssm.Test.Config.class)
public class TestControllerTest extends WebTest {

	@Test
	public final void testGetString() throws Exception {
		final String test = "test";
		this.mvc.perform(MockMvcRequestBuilders.get("/api/test").accept(MediaType.TEXT_PLAIN))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(new ResultHandler() {

					@Override
					public void handle(MvcResult result) throws Exception {
						Assert.assertEquals(result.getResponse().getContentAsString(), test);
					}
				}).andReturn();
	}
}
