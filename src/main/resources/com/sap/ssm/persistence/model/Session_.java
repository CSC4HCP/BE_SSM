package com.sap.ssm.persistence.model;

import java.sql.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-05-06T15:40:55.047+0800")
@StaticMetamodel(Session.class)
public class Session_ {
	public static volatile SingularAttribute<Session, Long> id;
	public static volatile SingularAttribute<Session, String> topic;
	public static volatile SingularAttribute<Session, String> category;
	public static volatile SingularAttribute<Session, String> description;
	public static volatile SingularAttribute<Session, String> owner;
	public static volatile SingularAttribute<Session, String> status;
	public static volatile SingularAttribute<Session, Date> meetingTime;
	public static volatile SingularAttribute<Session, String> meetingRoom;
	public static volatile SingularAttribute<Session, Integer> file;
	public static volatile SingularAttribute<Session, String> summary;
}
