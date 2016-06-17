package com.sap.ssm.web.model.request;

import java.io.Serializable;
import java.sql.Timestamp;

import org.hibernate.validator.constraints.NotBlank;

public class LogMergeRequest implements Serializable {

    /**
     * Generated serial version uid
     */
    private static final long serialVersionUID = 8029670391381462673L;

    @NotBlank
    private Long id;

    @NotBlank
    private String userId;

    @NotBlank
    private String operation;

    @NotBlank
    private Long session;

    @NotBlank
    private Timestamp date;

    /**
     * @return the id
     */
    public Long getId() {
	return id;
    }

    /**
     * @return the user
     */
    public String getUserId() {
	return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
	this.userId = userId;
    }

    /**
     * @return the operation
     */
    public String getOperation() {
	return operation;
    }

    /**
     * @param operation the operation to set
     */
    public void setOperation(String operation) {
	this.operation = operation;
    }

    /**
     * @return the session
     */
    public Long getSession() {
	return session;
    }

    /**
     * @param session the session to set
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
     * @param date the date to set
     */
    public void setDate(Timestamp date) {
	this.date = date;
    }
}
