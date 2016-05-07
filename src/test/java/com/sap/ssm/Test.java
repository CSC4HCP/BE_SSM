/**
 * 
 */
package com.sap.ssm;

import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author I326996 David Lin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = Test.Config.class)
public class Test {

	@Configuration
	@ComponentScan(basePackages = "com.sap.ssm.web.testcontroller")
	@EnableWebMvc
	public static class Config {

	}
}
