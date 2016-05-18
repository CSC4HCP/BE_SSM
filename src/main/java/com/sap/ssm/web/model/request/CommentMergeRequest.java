package com.sap.ssm.web.model.request;

import java.io.Serializable;
import java.sql.Timestamp;

import org.hibernate.validator.constraints.NotBlank;

/**
 * The SessionMergeRequest is a request object which used to store session data
 * before the data merged into a {@link}Session object
 * 
 * @author Yuan Zhang
 */
public class CommentMergeRequest implements Serializable {
	/**
	 * Generated serial version uid
	 */
	private static final long serialVersionUID = -1272715216787472506L;

	@NotBlank
	private Long id;

	@NotBlank
	private String author;

	@NotBlank
	private Long session;

	@NotBlank
	private String content;

	@NotBlank
	private Timestamp date;

	public CommentMergeRequest() {

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

}
