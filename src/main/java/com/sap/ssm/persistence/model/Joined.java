package com.sap.ssm.persistence.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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
	private Timestamp date;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
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
	 * @return the session id
	 */
	public Long getSession() {
		return session;
	}

	/**
	 * @param session
	 *            the session id to set
	 */
	public void setSession(Long session) {
		this.session = session;
	}

	/**
	 * @return the date
	 */
	public Timestamp getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Timestamp date) {
		this.date = date;
	}
}
