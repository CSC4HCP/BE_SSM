package com.sap.ssm.web.model.response;

import com.sap.ssm.persistence.model.Category;

public class CategoryDetailResponse {

	private Long id;

	private String name;

	public CategoryDetailResponse() {

	}

	public CategoryDetailResponse(Category Category) {
		/**
		 * set the id
		 */
		this.setId(Category.getId());
		/**
		 * set the name
		 */
		this.setname(Category.getName());

	}

	/**
	 * get the id
	 */
	public Long getid() {
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
	public String getname() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setname(String name) {
		this.name = name;
	}

}
