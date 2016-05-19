package com.sap.ssm.web.controller;

import java.util.Collection;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
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
	 * The API for find joined objects by several optional parameters<br>
	 * <br>
	 * API URL - <b>"/api/joined"</b><br>
	 * Method - <b>"GET"</b>
	 * 
	 * @param userId
	 *            Optional session userId
	 * @param session
	 *            Optional session session id
	 * @return a collection of joined objects response body
	 */
	@RequestMapping(method = RequestMethod.GET)
	public Collection<JoinedDetailResponse> findBySeveralConditions(@RequestParam("userId") Optional<String> userId,
			@RequestParam("session") Optional<Long> session) {
		if (userId.isPresent()) {
			if (session.isPresent()) {
				return CollectionUtils.collect(joinedService.findByUserIdAndSession(userId.get(), session.get()),
						DETAIL_RESPONSE_TRANSFORMER);
			} else {
				return CollectionUtils.collect(joinedService.findByUserId(userId.get()), DETAIL_RESPONSE_TRANSFORMER);
			}
		} else {
			if (session.isPresent()) {
				return CollectionUtils.collect(joinedService.findBySession(session.get()), DETAIL_RESPONSE_TRANSFORMER);
			} else {
				return CollectionUtils.collect(joinedService.findAll(), DETAIL_RESPONSE_TRANSFORMER);
			}
		}

	}
}
