package com.sap.ssm.web.model.request;

import java.io.Serializable;


import org.hibernate.validator.constraints.NotBlank;

/**
 * The CategoryMergeRequest is a request object which used to store Category
 * data before the data merged into a {@link}Category object
 * 
 * @author Yuan Zhang
 */
public class CategoryMergeRequest implements Serializable {

	/**
	 * Generated serial version uid
	 */
	private static final long serialVersionUID = -5957693354653346025L;

	@NotBlank
	private Long id;

	@NotBlank
	private String name;

	public CategoryMergeRequest() {

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
	 * get the name
	 */

	public String getName() {
		return name;
	}

	/**
	 * @param author
	 *            the name to set
	 */

	public void setName(String name) {
		this.name = name;
	}

}
