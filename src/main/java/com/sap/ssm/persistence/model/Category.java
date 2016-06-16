package com.sap.ssm.persistence.model;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Category
 * 
 * @author I326958 Yuan Zhang
 */
@Entity
@Table(name = "T_Category")
public class Category implements Serializable {

	/**
	 * Generated serial version uid
	 */
	private static final long serialVersionUID = -1749920622168853190L;

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME", nullable = false)
	private String name;

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
	 * @return the name
	 */

	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
