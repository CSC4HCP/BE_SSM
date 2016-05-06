/**
 * 
 */
package com.sap.ssm;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author I326996 David Lin
 */
@DirtiesContext
public abstract class WebTest extends MockTest {

	@Autowired
	protected WebApplicationContext wac;

	protected MockMvc mvc;

	@PostConstruct
	public void setup() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
}
