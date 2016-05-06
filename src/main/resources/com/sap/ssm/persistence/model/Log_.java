package com.sap.ssm.persistence.model;

import java.sql.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-05-06T15:40:55.045+0800")
@StaticMetamodel(Log.class)
public class Log_ {
	public static volatile SingularAttribute<Log, Long> id;
	public static volatile SingularAttribute<Log, String> userId;
	public static volatile SingularAttribute<Log, String> operation;
	public static volatile SingularAttribute<Log, Long> session;
	public static volatile SingularAttribute<Log, Date> date;
}
