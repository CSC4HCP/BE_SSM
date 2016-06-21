package com.sap.ssm.web.model.response;

import java.sql.Timestamp;

import com.sap.ssm.persistence.model.Comment;

public class CommentDetailResponse {

	private Long id;

	private String author;

	private Long session;

	private String content;

	private Timestamp date;

	private String sessionTopic;

	public CommentDetailResponse() {

	}

	public CommentDetailResponse(Comment comment) {
		/**
		 * set the id
		 */
		this.setId(comment.getId());
		/**
		 * get the content
		 */
		this.setContent(comment.getContent());
		/**
		 * get the session
		 */
		this.setSession(comment.getSession());
		/**
		 * get the date
		 */
		this.setDate(comment.getDate());
		/**
		 * get the author
		 */
		this.setAuthor(comment.getAuthor());
		/**
		 * get the sessionTopic
		 */
		this.setSessionTopic(comment.getSessionTopic());
	}

	/**
	 * get the id
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
	 * get the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author
	 *            the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * get the session
	 */
	public Long getSession() {
		return session;
	}

	/**
	 * @param session
	 *            the session to set
	 */
	public void setSession(Long session) {
		this.session = session;
	}

	/**
	 * get the content
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
	 * get the date
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

	/**
	 * @return the sessionTopic
	 */
	public String getSessionTopic() {
		return sessionTopic;
	}

	/**
	 * @param sessionTopic
	 *            the sessionTopic to set
	 */
	public void setSessionTopic(String sessionTopic) {
		this.sessionTopic = sessionTopic;
	}
}
