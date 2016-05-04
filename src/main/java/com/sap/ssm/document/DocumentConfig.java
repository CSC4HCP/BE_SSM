package main.java.com.sap.ssm.document;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
public class DocumentConfig {

	public static final String ORIGINAL_NAME = "cmis:originalName";

	public static final String SESSION = "cmis:session";

	protected CommonsMultipartResolver multipartResolver;

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
