/**
 * 
 */
package com.sap.ssm.testcontroller;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.sap.ssm.WebTest;

/**
 * @author I326996 David Lin
 */
public class TestControllerTest extends WebTest {

	@Test
	public final void testGetString() throws Exception {
		final String test = "test";
		this.mvc.perform(MockMvcRequestBuilders.get("/test").accept(MediaType.TEXT_PLAIN))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(new ResultHandler() {

					@Override
					public void handle(MvcResult result) throws Exception {
						Assert.assertEquals(result.getResponse().getContentAsString(), test);
					}
				}).andReturn();
	}
}
