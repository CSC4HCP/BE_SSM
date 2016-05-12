/**
 * 
 */
package com.sap.ssm.web.model.response;

import java.io.Serializable;

import com.sap.ssm.persistence.model.User;

/**
 * The <b>{@link}UserDetailResponse</b> is a response object which includes user
 * data used by {@link}RestController to return to client
 * 
 * @author David Lin
 */
public class UserDetailResponse implements Serializable {

	/**
	 * Generated serial version uid
	 */
	private static final long serialVersionUID = 8336069967946759824L;

	private String name;

	private String firstName;

	private String lastName;

	private String team;

	private String role;

	private String email;

	/**
	 * Non-param constructor
	 */
	public UserDetailResponse() {

	}

	/**
	 * Constructor
	 * 
	 * @param user
	 *            the {@link}User object returned to client
	 */
	public UserDetailResponse(User user) {
		this.setId(user.getId());
		this.setEmail(user.getEmail());
		this.setFirstName(user.getFirstName());
		this.setLastName(user.getLastName());
		this.setRole(user.getRole());
		this.setTeam(user.getTeam());
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return name;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String name) {
		this.name = name;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the team
	 */
	public String getTeam() {
		return team;
	}

	/**
	 * @param team
	 *            the team to set
	 */
	public void setTeam(String team) {
		this.team = team;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
}
