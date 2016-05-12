package com.sap.ssm.common;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * This is the common config includes some common {@link}Bean
 * 
 * @author I326996 David Lin
 */
@Configuration
public class CommonConfig {

	/**
	 * Initial Context for JNDI
	 * 
	 * @return a new instance of InitialContext
	 * @throws {@link}}NamingException
	 */
	@Bean(autowire = Autowire.BY_TYPE)
	@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
	public InitialContext initialContext() throws NamingException {
		return new InitialContext();
	}

}
