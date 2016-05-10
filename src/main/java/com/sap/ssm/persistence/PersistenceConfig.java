package com.sap.ssm.persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;

import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.SharedEntityManagerCreator;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.sap.ssm.persistence.context.DataSourceContext;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories
@EnableTransactionManagement
public class PersistenceConfig {

	public static final String PERSISTENCE_UNIT_NAME = "SSM";

	@Autowired
	protected DataSourceContext dataSourceContext;

	protected DataSource dataSource;

	protected EntityManagerFactory entityManagerFactory;

	protected EntityManager entityManager;

	protected JpaTransactionManager transactionManager;

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
	public synchronized DataSource initDataSource() {
		if (this.dataSource != null || this.dataSourceContext == null) {
			return this.dataSource;
		}
		try {
			final DataSource dataSource = this.dataSourceContext.getDataSource();
			try (final Connection connection = dataSource.getConnection()) {
				return this.dataSource = dataSource;
			}
		} catch (SQLException sqle) {
			throw new IllegalStateException(sqle);
		}
	}

	@Bean
	public synchronized EntityManagerFactory entityManagerFactory() {
		if (this.entityManagerFactory == null) {
			try {
				HashMap<String, Object> properties = new HashMap<>();
				properties.put(PersistenceUnitProperties.NON_JTA_DATASOURCE, this.initDataSource());
				this.entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME, properties);
			} catch (Exception e) {
				throw new IllegalStateException(e);
			}
		}
		return this.entityManagerFactory;
	}

	@Bean
	@Primary
	public synchronized EntityManager entityManager() {
		if (this.entityManager == null) {
			this.entityManager = SharedEntityManagerCreator.createSharedEntityManager(this.entityManagerFactory());
		}
		return this.entityManager;
	}

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
	public synchronized PlatformTransactionManager transactionManager() {
		if (this.transactionManager == null) {
			this.transactionManager = new JpaTransactionManager();
			this.transactionManager.setEntityManagerFactory(this.entityManagerFactory());
		}
		return this.transactionManager;
	}
}
