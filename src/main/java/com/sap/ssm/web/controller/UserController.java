package com.sap.ssm.web.controller;

import java.util.Collection;

import javax.validation.constraints.NotNull;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sap.ssm.persistence.model.User;
import com.sap.ssm.service.UserService;
import com.sap.ssm.web.model.request.UserMergeRequest;
import com.sap.ssm.web.model.response.UserDetailResponse;

/**
 * The {@link}RestController for user entity.
 * 
 * @author David Lin
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	private Transformer<User, UserDetailResponse> DETAIL_RESPONSE_TRANSFORMER = new Transformer<User, UserDetailResponse>() {

		@Override
		public UserDetailResponse transform(User input) {
			return new UserDetailResponse(input);
		}
	};

	/**
	 * The API to <b>GET</b> all exist user.<br>
	 * <br>
	 * API URL - <b>"/user"</b><br>
	 * Method - <b>"GET"</b>
	 * 
	 * @return the {@link}Collection of {@link}UserDetailResponse.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public Collection<UserDetailResponse> findAll() {
		return CollectionUtils.collect(userService.findAll(), DETAIL_RESPONSE_TRANSFORMER);
	}

	/**
	 * The API to <b>GET</b> one user by id.<br>
	 * <br>
	 * API URL - <b>"/user/{id}"</b><br>
	 * Method - <b>"GET"</b>
	 * 
	 * @param id
	 *            the user id.
	 * @return a {@link}UserDetailResponse object if not null.
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public UserDetailResponse findOneById(@PathVariable("id") String id) {
		User user = userService.findOneById(id);
		if (user == null) {
			return null;
		}
		return new UserDetailResponse(user);
	}

	/**
	 * The API to <b>CREATE</b> a new user.<br>
	 * <br>
	 * API URL - <b>"/user"</b><br>
	 * Method - <b>"POST"</b>
	 * 
	 * @param userMergeRequest
	 *            the {@link}UserMergeRequest object.
	 * @return a {@link}UserDetailResponse object.
	 */
	@RequestMapping(method = RequestMethod.POST)
	public UserDetailResponse createOne(@NotNull @RequestBody UserMergeRequest userMergeRequest) {
		return new UserDetailResponse(userService.createOne(userMergeRequest));
	}

	/**
	 * The API to <b>UPDATE</b> a user's data by id.<br>
	 * <br>
	 * API URL - <b>"/user/{id}"</b><br>
	 * Method - <b>"PUT"</b>
	 * 
	 * @param id
	 *            the user id.
	 * @param userMergeRequest
	 *            the {@link}UserMergeRequest object.
	 * @return a {@link}UserDetailResponse object.
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public UserDetailResponse updateOne(@PathVariable("id") String id,
			@NotNull @RequestBody UserMergeRequest userMergeRequest) {
		return new UserDetailResponse(userService.updateOne(id, userMergeRequest));
	}

	/**
	 * The API to DELETE a user by id.<br>
	 * <br>
	 * API URL - <b>"/user/{id}"</b><br>
	 * Method - <b>"DELETE"</b>
	 * 
	 * @param id
	 *            the user id.
	 * @return the status message string.
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String deleteOne(@PathVariable("id") String id) {
		return userService.deleteOneById(id);
	}
}
