/**
 * 
 */
package com.sap.ssm.service;

import org.springframework.transaction.annotation.Transactional;

import com.sap.ssm.persistence.model.User;
import com.sap.ssm.persistence.repository.UserRepository;
import com.sap.ssm.web.model.request.UserMergeRequest;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The {@link}UserService includes operations about the {@link}User object
 * 
 * @author David Lin
 */
@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * Find all exist users
	 * 
	 * @return {@link}List of users
	 */
	public List<User> findAll() {
		return userRepository.findAll();
	}

	/**
	 * Find one user by id
	 * 
	 * @param id
	 *            {@link}User's id
	 * @return A {@link}User object
	 */
	public User findOneById(@NotNull String id) {
		return userRepository.findOne(id);
	}

	/**
	 * Create a user
	 * 
	 * @param userMergeRequest
	 *            the request include all user information
	 * @return A {@link}User object
	 */
	public User createOne(@NotNull UserMergeRequest userMergeRequest) {
		User user = new User();
		mergeScalarProperties(userMergeRequest, user);
		return userRepository.saveAndFlush(user);
	}

	/**
	 * Update a user's data
	 * 
	 * @param id
	 *            {@link}User's id
	 * @param userMergeRequest
	 *            the request include all user information
	 * @return
	 */
	public User updateOne(@NotNull String id, @NotNull UserMergeRequest userMergeRequest) {
		User user = userRepository.findOne(id);
		if (id == null) {
			throw new IllegalArgumentException("id");
		}
		mergeScalarProperties(userMergeRequest, user);
		return userRepository.saveAndFlush(user);
	}

	/**
	 * Delete a {@link}User by id
	 * 
	 * @param id
	 *            {@link}User's id
	 * @return status message
	 */
	public String deleteOneById(@NotNull String id) {
		try {
			userRepository.delete(id);
			return "Delete Success";
		} catch (IllegalArgumentException e) {
			return "Delete Failed " + e.getMessage();
		}
	}

	/**
	 * Merge user's data from a {@link}UserMergeRequest to a {@link}User object
	 * 
	 * @param userMergeRequest
	 *            the request include all user information
	 * @param user
	 *            the {@link}User object
	 */
	private void mergeScalarProperties(UserMergeRequest userMergeRequest, User user) {
		user.setId(userMergeRequest.getId());
		user.setEmail(userMergeRequest.getEmail());
		user.setFirstName(userMergeRequest.getFirstName());
		user.setLastName(userMergeRequest.getLastName());
		user.setRole(userMergeRequest.getRole());
		user.setTeam(userMergeRequest.getTeam());
	}
}
