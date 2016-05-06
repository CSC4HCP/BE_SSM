package com.sap.ssm.persistence.model;

import java.sql.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-05-06T15:40:55.011+0800")
@StaticMetamodel(Comment.class)
public class Comment_ {
	public static volatile SingularAttribute<Comment, Long> id;
	public static volatile SingularAttribute<Comment, String> user;
	public static volatile SingularAttribute<Comment, Long> session;
	public static volatile SingularAttribute<Comment, String> content;
	public static volatile SingularAttribute<Comment, Date> date;
}
