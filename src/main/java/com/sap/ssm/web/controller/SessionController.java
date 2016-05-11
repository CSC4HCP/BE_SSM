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

import com.sap.ssm.persistence.model.Session;
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
	private Transformer<Session, SessionDetailResponse> DETAIL_RESPONSE_TRANSFORMER = new Transformer<Session, SessionDetailResponse>() {

		@Override
		public SessionDetailResponse transform(Session input) {
			return new SessionDetailResponse(input);
		}
	};

	/**
	 * The API to create a new Session
	 * 
	 * API URL - <b>"/create"<b>
	 * 
	 * Method - <b>"POST"</b>
	 * 
	 * @return a {@link}SessionDetailResponse object.
	 */

	@RequestMapping(method = RequestMethod.GET)
	public Collection<SessionDetailResponse> findAll() {
		return CollectionUtils.collect(sessionService.findAll(), DETAIL_RESPONSE_TRANSFORMER);
	}

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

	@RequestMapping(method = RequestMethod.GET, value = "/owner/{owner}")
	public Collection<SessionDetailResponse> findByOwner(@PathVariable("owner") String owner) {
		return CollectionUtils.collect(sessionService.findByOwner(owner), DETAIL_RESPONSE_TRANSFORMER);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/category/{category}")
	public Collection<SessionDetailResponse> findByCategory(@PathVariable("category") String category) {
		// return sessionService.findByCategory(category);
		return CollectionUtils.collect(sessionService.findByCategory(category), DETAIL_RESPONSE_TRANSFORMER);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/status/{status}")
	public Collection<SessionDetailResponse> findByStatus(@PathVariable("status") String status) {
		// return sessionService.findByStatus(status);
		return CollectionUtils.collect(sessionService.findByStatus(status), DETAIL_RESPONSE_TRANSFORMER);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/byOwnerAndStatus")
	public Collection<SessionDetailResponse> findByOwnerAndStatus(@RequestParam("owner") String owner,
			@RequestParam("status") String status) {
		// return sessionService.findByOwnerAndStatus(owner, status);
		return CollectionUtils.collect(sessionService.findByOwnerAndStatus(owner, status), DETAIL_RESPONSE_TRANSFORMER);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/byOwnerAndCategory")
	public Collection<SessionDetailResponse> findByOwnerAndCategory(@RequestParam("owner") String owner,
			@RequestParam("category") String category) {
		// return sessionService.findByOwnerAndCategory(owner, category);
		return CollectionUtils.collect(sessionService.findByOwnerAndCategory(owner, category),
				DETAIL_RESPONSE_TRANSFORMER);
	}

}
