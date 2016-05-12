package com.sap.ssm.persistence.context;

import javax.sql.DataSource;

/**
 * The interface for DataSource context
 * 
 * @author I326996 David Lin
 */
public interface DataSourceContext {
	/**
	 * interface method used to get DataSource
	 * 
	 * @return a new instance of {@link}DataSource
	 */
	DataSource getDataSource();
}
