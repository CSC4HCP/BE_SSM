package com.sap.ssm.persistence.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Log
 * @author I326996 David Lin
 */
@Entity
@Table(name = "T_LOG")
public class Log implements Serializable {

    /**
     * Generated serial version uid
     */
    private static final long serialVersionUID = 8029670391381462673L;

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "OPERATION")
    private String operation;

    @Column(name = "SESSION")
    private Long session;

    @Column(name = "DATE")
    private Timestamp date;

    /**
     * @return the id
     */
    public Long getId() {
	return id;
    }

    /**
     * Set log Id
     * @param id log's id
     */
    public void setId(Long id) {
	this.id = id;
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
