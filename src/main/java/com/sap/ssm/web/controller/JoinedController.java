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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sap.ssm.persistence.model.Joined;
import com.sap.ssm.service.JoinedService;
import com.sap.ssm.web.model.request.JoinedMergeRequest;
import com.sap.ssm.web.model.response.JoinedDetailResponse;

/**
 * The RestController for join entity
 * 
 * @author I326962 Zero Yu
 */
@RestController
@RequestMapping("/joined")
public class JoinedController {
	@Autowired
	private JoinedService joinedService;

	private Transformer<Joined, JoinedDetailResponse> DETAIL_RESPONSE_TRANSFORMER = new Transformer<Joined, JoinedDetailResponse>() {

		@Override
		public JoinedDetailResponse transform(Joined input) {
			return new JoinedDetailResponse(input);
		}
	};

	/**
	 * The API to <b>CREATE</b> a new joined object.<br>
	 * <br>
	 * API URL - <b>"/api/joined"</b><br>
	 * Method - <b>"POST"</b>
	 * 
	 * @param joinedMergeRequest
	 *            the {@link}JoinedMergeRequest object.
	 * @return a {@link}UserDetailResponse object.
	 */
	@RequestMapping(method = RequestMethod.POST)
	public JoinedDetailResponse createOne(@NotNull @RequestBody JoinedMergeRequest joinedMergeRequest) {
		return new JoinedDetailResponse(joinedService.createOne(joinedMergeRequest));
	}

	/**
	 * The API to <b>GET</b> a set of joined objects by userId.<br>
	 * <br>
	 * API URL - <b>"/api/joined/{userId}"</b><br>
	 * Method - <b>"GET"</b>
	 * 
	 * @param userId
	 *            the user id.
	 * @return the {@link}Collection of {@link}JoinedDetailResponse object if
	 *         not null.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public Collection<JoinedDetailResponse> findByUserId(@RequestParam("userId") String userId) {
		return CollectionUtils.collect(joinedService.findByUserId(userId), DETAIL_RESPONSE_TRANSFORMER);
	}

	/**
	 * The API to <b>GET</b> one joined object by session id.<br>
	 * <br>
	 * API URL - <b>"/api/joined/{session}"</b><br>
	 * Method - <b>"GET"</b>
	 * 
	 * @param session
	 *            the session id.
	 * @return a {@link}JoinedDetailResponse object if not null.
	 */
	@RequestMapping(value = "/{session}", method = RequestMethod.GET)
	public Collection<JoinedDetailResponse> findBySession(@PathVariable("session") Long session) {
		return CollectionUtils.collect(joinedService.findBySessionId(session), DETAIL_RESPONSE_TRANSFORMER);
	}
}
