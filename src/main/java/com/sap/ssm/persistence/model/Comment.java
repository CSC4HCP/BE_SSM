package com.sap.ssm.persistence.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Comment
 * 
 * @author I326996 David Lin
 */
@Entity
@Table(name = "T_COMMENT")
public class Comment implements Serializable {

	/**
	 * Generated serial version uid
	 */
	private static final long serialVersionUID = 2493665474053947254L;

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;

	@Column(name = "USER_ID", nullable = false)
	private String user;

	@Column(name = "SESSION", nullable = false)
	private Long session;

	@Column(name = "CONTENT", nullable = false)
	private String content;

	@Column(name = "DATE", nullable = false)
	private Date date;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the session
	 */
	public Long getSession() {
		return session;
	}

	/**
	 * @param session
	 *            the session to set
	 */
	public void setSession(Long session) {
		this.session = session;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
}
