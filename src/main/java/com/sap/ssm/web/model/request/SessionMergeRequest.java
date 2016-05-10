package com.sap.ssm.web.model.request;

import java.io.Serializable;
import java.sql.Date;

import org.hibernate.validator.constraints.NotBlank;

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
	private Date meetingTime;

	private String meetingRoom;

	@NotBlank
	private Integer file;

	private String summary;

	@NotBlank
	private boolean visibility;

	public SessionMergeRequest() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getMeetingTime() {
		return meetingTime;
	}

	public void setMeetingTime(Date meetingTime) {
		this.meetingTime = meetingTime;
	}

	public String getMeetingRoom() {
		return meetingRoom;
	}

	public void setMeetingRoom(String meetingRoom) {
		this.meetingRoom = meetingRoom;
	}

	public Integer getFile() {
		return file;
	}

	public void setFile(Integer file) {
		this.file = file;
	}

	public String getSummary() {
		return summary;
	}

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
