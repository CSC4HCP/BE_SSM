package com.sap.ssm.web.model.response;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import com.sap.ssm.persistence.model.Session;

public class SessionDetailResponse {

	private Long id;

	private String topic;

	private String category;

	private String description;

	private String owner;

	private String status;

	private Timestamp meetingTime;

	private String meetingRoom;

	private Integer file;

	private String summary;

	private boolean visibility;

	public SessionDetailResponse() {

	}

	public SessionDetailResponse(Session session) {
		this.setCategory(session.getCategory());
		this.setDescription(session.getDescription());
		this.setId(session.getId());
		this.setMeetingTime(session.getMeetingTime());
		this.setOwner(session.getOwner());
		this.setStatus(session.getStatus());
		this.setTopic(session.getTopic());
		this.setFile(session.getFile());
		this.setMeetingRoom(session.getMeetingRoom());
		this.setSummary(session.getSummary());
		this.setVisibility(session.isVisibility());
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

	public String getMeetingTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
		return dateFormat.format(meetingTime);
	}

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
}
