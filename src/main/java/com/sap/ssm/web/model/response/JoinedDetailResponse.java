package com.sap.ssm.web.model.response;

import java.io.Serializable;
import java.util.Date;

import com.sap.ssm.persistence.model.Joined;

/**
 * The <b>{@link}JoinedDetailResponse</b> is a response object which includes joined
 * data used by {@link}RestController to return to client
 * 
 * @author I326962 Zero Yu
 */
public class JoinedDetailResponse implements Serializable {

	/**
	 * Generated serial version id
	 */
	private static final long serialVersionUID = -500116067882764423L;

	private Long id;
	private String userId;
	private Long session;
	private Date date;
	
	/**
	 * Non-param constructor
	 */
	public JoinedDetailResponse() {

	}
	
	/**
	 * Constructor
	 * 
	 * @param joined
	 *            the {@link}Joined object returned to client
	 */
	public JoinedDetailResponse(Joined joined) {
		this.setId(joined.getId());
		this.setUserId(joined.getUserId());
		this.setSession(joined.getSession());
		this.setDate(joined.getDate());
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
