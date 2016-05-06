package com.sap.ssm.persistence.model;

import java.sql.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-05-06T15:40:55.042+0800")
@StaticMetamodel(Joined.class)
public class Joined_ {
	public static volatile SingularAttribute<Joined, Long> id;
	public static volatile SingularAttribute<Joined, String> userId;
	public static volatile SingularAttribute<Joined, Long> session;
	public static volatile SingularAttribute<Joined, Date> date;
}
