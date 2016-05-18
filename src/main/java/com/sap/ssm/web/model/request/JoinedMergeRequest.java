package com.sap.ssm.web.model.request;

import java.io.Serializable;
import java.sql.Timestamp;

import org.hibernate.validator.constraints.NotBlank;

/**
 * The JoinedMergeRequest is a request object which used to store joined data before
 * the data merged into a {@link}Joined object
 * 
 * @author I326962 Zero Yu
 */
public class JoinedMergeRequest implements Serializable {

	/**
	 * Generated serial version id
	 */
	private static final long serialVersionUID = -5285472004206334355L;
	
	@NotBlank
	private Long id;
	
	@NotBlank
	private String userId;
	
	@NotBlank
	private Long session;
	
	@NotBlank
	private Timestamp date;
	
	/**
	 * Non-param constructor
	 */
	public JoinedMergeRequest() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Long getSession() {
		return session;
	}

	public void setSession(Long session) {
		this.session = session;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

}
