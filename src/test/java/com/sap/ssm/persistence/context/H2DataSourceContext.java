package com.sap.ssm.persistence.context;

import javax.sql.DataSource;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

@Primary
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class H2DataSourceContext implements DataSourceContext {

	protected DriverManagerDataSource dataSource;

	protected static final String DRIVER_NAME = "org.h2.Driver";
	protected static final String URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";

	@Override
	public DataSource getDataSource() {
		if (this.dataSource == null) {
			this.dataSource = new DriverManagerDataSource();
			// Set properties
			this.dataSource.setDriverClassName(DRIVER_NAME);
			this.dataSource.setUrl(URL);
			DatabasePopulatorUtils.execute(this.createDatabasePopulator(), this.dataSource);
		}

		return dataSource;
	}

	protected DatabasePopulator createDatabasePopulator() {
		ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
		String[] scriptPaths = { "t_currency_conversion.sql", "t_bidding_config.sql", };
		for (final String path : scriptPaths) {
			final ClassPathResource script = new ClassPathResource(path);
			if (script.exists()) {
				databasePopulator.addScript(script);
			}
		}
		return databasePopulator;
	}
}
