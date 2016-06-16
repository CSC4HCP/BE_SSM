package com.sap.ssm.web.model.request;

import java.io.Serializable;
import java.sql.Timestamp;

import org.hibernate.validator.constraints.NotBlank;

/**
 * The SessionMergeRequest is a request object which used to store session data
 * before the data merged into a {@link}Session object
 * 
 * @author David Lin
 */
public class SessionMergeRequest implements Serializable {
	/**
	 * Generated serial version uid
	 */
	private static final long serialVersionUID = -1272715216787472506L;

	@NotBlank
	private Long id;

	@NotBlank
	private String topic;

	@NotBlank
	private String category;

	private String description;

	@NotBlank
	private String owner;

	@NotBlank
	private String status;

	@NotBlank
	private Timestamp meetingTime;

	private String meetingRoom;

	private String summary;

	@NotBlank
	private boolean visibility;

	public SessionMergeRequest() {

	}

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

	/**
	 * @return the visibility
	 */
	public boolean isVisibility() {
		return visibility;
	}

	/**
	 * @param visibility
	 *            the visibility to set
	 */
	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}
}
