package com.sap.ssm.persistence.context;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sap.ssm.persistence.context.DataSourceContext;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class HcpDataSourceContext implements DataSourceContext {

	protected DataSource dataSource;

	@Autowired
	private InitialContext initialContext;

	@Override
	public synchronized DataSource getDataSource() {
		if (dataSource == null) {
			try {
				dataSource = (DataSource) initialContext.lookup("java:comp/env/jdbc/DefaultDB");
			} catch (NamingException ne) {
				throw new IllegalStateException(ne);
			}
		}
		return dataSource;
	}

}
