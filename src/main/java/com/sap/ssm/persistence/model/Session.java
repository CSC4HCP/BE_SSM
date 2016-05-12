package com.sap.ssm.persistence.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Session
 * 
 * @author I326996 David Lin
 */
@Entity
@Table(name = "T_SESSION")
public class Session implements Serializable {

	/**
	 * Generated serial version uid
	 */
	private static final long serialVersionUID = 273914554317461374L;

	@Id
	@GeneratedValue
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "TOPIC", nullable = false)
	private String topic;

	@Column(name = "CATEGORY", nullable = false)
	private String category;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "OWNER", nullable = false)
	private String owner;

	@Column(name = "STATUS", nullable = false)
	private String status;

	@Column(name = "MEETING_TIME")
	private Timestamp meetingTime;

	@Column(name = "MEETING_ROOM")
	private String meetingRoom;

	@Column(name = "FILE")
	private Integer file;

	@Column(name = "SUMMARY")
	private String summary;

	@Column(name = "VISIBILITY")
	private boolean visibility;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Set Session Id
	 * 
	 * @param id
	 *            session id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the topic
	 */
	public String getTopic() {
		return topic;
	}

	/**
	 * @param topic
	 *            the topic to set
	 */
	public void setTopic(String topic) {
		this.topic = topic;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * @param owner
	 *            the owner to set
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the meetingTime
	 */
	public Timestamp getMeetingTime() {
		return meetingTime;
	}

	/**
	 * @param meetingTime
	 *            the meetingTime to set
	 */
	public void setMeetingTime(Timestamp meetingTime) {
		this.meetingTime = meetingTime;
	}

	/**
	 * @return the meetingRoom
	 */
	public String getMeetingRoom() {
		return meetingRoom;
	}

	/**
	 * @param meetingRoom
	 *            the meetingRoom to set
	 */
	public void setMeetingRoom(String meetingRoom) {
		this.meetingRoom = meetingRoom;
	}

	/**
	 * @return the file
	 */
	public Integer getFile() {
		return file;
	}

	/**
	 * @param file
	 *            the file to set
	 */
	public void setFile(Integer file) {
		this.file = file;
	}

	/**
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * @param summary
	 *            the summary to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}

	public boolean isVisibility() {
		return visibility;
	}

	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}
}
