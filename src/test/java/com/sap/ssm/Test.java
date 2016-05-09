/**
 * 
 */
package com.sap.ssm;

import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author I326996 David Lin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({ @ContextConfiguration(name = "app", classes = Test.AppConfig.class),
		@ContextConfiguration(name = "web", classes = Test.WebConfig.class) })
public class Test {

	@Configuration
	@ComponentScan(basePackages = "com.sap.ssm")
	public static class AppConfig {

	}

	@Configuration
	@ComponentScan(basePackages = "com.sap.ssm.web")
	@EnableWebMvc
	public static class WebConfig {

	}
}
