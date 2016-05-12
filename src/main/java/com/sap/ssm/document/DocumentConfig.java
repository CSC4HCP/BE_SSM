package com.sap.ssm.document;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * This is the configuration for Document Service
 * 
 * @author I326996 David Lin
 */
@Configuration
public class DocumentConfig {

	protected CommonsMultipartResolver multipartResolver;

	/**
	 * Bean constructor for the {@link}CommonsMultipartResolver
	 * 
	 * @return a new instance of CommonsMultipartResolver using UTF-8 encode
	 */
	@Bean
	@Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON)
	public CommonsMultipartResolver initMultipartResolver() {
		if (this.multipartResolver == null) {
			this.multipartResolver = new CommonsMultipartResolver();
			this.multipartResolver.setDefaultEncoding("UTF-8");
		}
		return this.multipartResolver;
	}
}
