package com.sap.ssm.web.model.response;

import java.io.Serializable;

import com.sap.ssm.persistence.model.Notification;

public class NotificationDetailResponse implements Serializable {

	/**
	 * Generated serial version id
	 */
	private static final long serialVersionUID = -5945317077332386084L;

	private Long id;

	private String target;

	private String content;

	private Boolean checked;

	public NotificationDetailResponse(Notification notification) {
		this.setId(notification.getId());
		this.setContent(notification.getContent());
		this.setChecked(notification.getChecked());
		this.setTarget(notification.getTarget());
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
	 * @return the target
	 */
	public String getTarget() {
		return target;
	}

	/**
	 * @param target
	 *            the target to set
	 */
	public void setTarget(String target) {
		this.target = target;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the checked
	 */
	public Boolean getChecked() {
		return checked;
	}

	/**
	 * @param checked
	 *            the checked to set
	 */
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
}
