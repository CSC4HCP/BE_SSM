package com.sap.ssm.persistence.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Joined
 * 
 * @author I326996 David Lin
 */
@Entity
@Table(name = "T_JOINED")
public class Joined implements Serializable {

	/**
	 * Generated serial version uid
	 */
	private static final long serialVersionUID = -3168791522155774829L;

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;

	@Column(name = "USER_ID", nullable = false)
	private String userId;

	@Column(name = "SESSION", nullable = false)
	private Long session;

	@Column(name = "DATE", nullable = false)
	private Date date;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
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
