package com.sap.ssm.web.controller;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sap.ssm.service.SessionService;
import com.sap.ssm.web.model.request.SessionMergeRequest;
import com.sap.ssm.web.model.response.SessionDetailResponse;

/**
 * The {@link}RestController for session entity.
 * 
 * @author Likai Deng
 *
 */
@RestController
@RequestMapping("/session")
public class SessionController {

	@Autowired
	private SessionService sessionService;

	/**
	 * The API to create a new Session
	 * 
	 * API URL - <b>"/create"<b>
	 * 
	 * Method - <b>"POST"</b>
	 * 
	 * @return a {@link}SessionDetailResponse object.
	 */
	@RequestMapping(method = RequestMethod.POST)
	public SessionDetailResponse createOne(@NotNull @RequestBody SessionMergeRequest sessionMergeRequest) {
		return new SessionDetailResponse(sessionService.createOne(sessionMergeRequest));
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public void deleteOneById(@PathVariable("id") Long id) {
		sessionService.deleteOneById(id);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public SessionDetailResponse updateOne(@PathVariable("id") Long id,
			@NotNull @RequestBody SessionMergeRequest sessionMergeRequest) {
		return new SessionDetailResponse(sessionService.updateOne(id, sessionMergeRequest));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public SessionDetailResponse findOneById(@PathVariable("id") Long id) {
		return new SessionDetailResponse(sessionService.findOneById(id));
	}
}
