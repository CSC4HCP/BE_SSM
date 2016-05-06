/**
 * 
 */
package com.sap.ssm;

import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author I326996 David Lin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@Transactional
@Rollback(true)
@ContextHierarchy(@ContextConfiguration(classes = Test.Config.class))
public class Test {

	@Configuration
	@ComponentScan
	@EnableWebMvc
	public static class Config {

	}
}
