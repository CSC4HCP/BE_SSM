package com.sap.ssm.web.controller;

import java.util.Collection;
import java.util.Optional;

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
 * @author Likai Deng, David Lin
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
	 * The API to create a new Session<br>
	 * <br>
	 * API URL - <b>"/api/session"</b><br>
	 * Method - <b>"POST"</b>
	 * 
	 * @param sessionMergeRequest
	 *            the request body for session
	 * @return a {@link}SessionDetailResponse object.
	 */
	@RequestMapping(method = RequestMethod.POST)
	public SessionDetailResponse createOne(@NotNull @RequestBody SessionMergeRequest sessionMergeRequest) {
		return new SessionDetailResponse(sessionService.createOne(sessionMergeRequest));
	}

	/**
	 * The API for delete a session<br>
	 * <br>
	 * API URL - <b>"/api/session/{id}"</b> <br>
	 * Method - <b>"DELETE"</b>
	 * 
	 * @param id
	 *            session id
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public String deleteOneById(@PathVariable("id") Long id) {
		return sessionService.deleteOneById(id);
	}

	/**
	 * The API for update a session<br>
	 * <br>
	 * API URL - <b>"/api/session/{id}"</b><br>
	 * Method - <b>"PUT"</b>
	 * 
	 * @param id
	 *            session id
	 * @param sessionMergeRequest
	 *            request body for session
	 * @return the response body for a session
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public SessionDetailResponse updateOne(@PathVariable("id") Long id,
			@NotNull @RequestBody SessionMergeRequest sessionMergeRequest) {
		return new SessionDetailResponse(sessionService.updateOne(id, sessionMergeRequest));
	}

	/**
	 * The API for find a session by id<br>
	 * <br>
	 * API URL - <b>"/api/session/{id}"</b><br>
	 * Method - <b>"GET"</b>
	 * 
	 * @param id
	 *            session id
	 * @return response body for a session
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public SessionDetailResponse findOneById(@PathVariable("id") Long id) {
		return new SessionDetailResponse(sessionService.findOneById(id));
	}

	/**
	 * The API for find sessions by several optional parameters<br>
	 * <br>
	 * API URL - <b>"/api/session"</b><br>
	 * Method - <b>"GET"</b>
	 * 
	 * @param owner
	 *            Optional session owner
	 * @param status
	 *            Optional session status
	 * @param category
	 *            Optional session category
	 * @param visibility
	 *            Optional session visibility
	 * @return a collection of session response body
	 */
	@RequestMapping(method = RequestMethod.GET)
	public Collection<SessionDetailResponse> findBySeveralConditions(@RequestParam("owner") Optional<String> owner,
			@RequestParam("status") Optional<String> status, @RequestParam("category") Optional<String> category,
			@RequestParam("visibility") Optional<Boolean> visibility) {
		if (visibility.isPresent()) {
			if (owner.isPresent()) {
				if (category.isPresent()) {
					return CollectionUtils.collect(sessionService.findByOwnerAndCategoryAndVisibility(owner.get(),
							category.get(), visibility.get()), DETAIL_RESPONSE_TRANSFORMER);
				} else if (status.isPresent()) {
					return CollectionUtils.collect(sessionService.findByOwnerAndStatusAndVisibility(owner.get(),
							status.get(), visibility.get()), DETAIL_RESPONSE_TRANSFORMER);
				} else {
					return CollectionUtils.collect(
							sessionService.findByOwnerAndVisibility(owner.get(), visibility.get()),
							DETAIL_RESPONSE_TRANSFORMER);
				}
			} else {
				if (category.isPresent()) {
					return CollectionUtils.collect(
							sessionService.findByCategoryAndVisibility(category.get(), visibility.get()),
							DETAIL_RESPONSE_TRANSFORMER);
				} else if (status.isPresent()) {
					return CollectionUtils.collect(
							sessionService.findByStatusAndVisibility(status.get(), visibility.get()),
							DETAIL_RESPONSE_TRANSFORMER);
				} else {
					return CollectionUtils.collect(sessionService.findByVisibility(visibility.get()),
							DETAIL_RESPONSE_TRANSFORMER);
				}
			}
		} else {
			if (owner.isPresent()) {
				if (category.isPresent()) {
					return CollectionUtils.collect(sessionService.findByOwnerAndCategory(owner.get(), category.get()),
							DETAIL_RESPONSE_TRANSFORMER);
				} else if (status.isPresent()) {
					return CollectionUtils.collect(sessionService.findByOwnerAndStatus(owner.get(), status.get()),
							DETAIL_RESPONSE_TRANSFORMER);
				} else {
					return CollectionUtils.collect(sessionService.findByOwner(owner.get()),
							DETAIL_RESPONSE_TRANSFORMER);
				}
			} else {
				if (category.isPresent()) {
					return CollectionUtils.collect(sessionService.findByCategory(category.get()),
							DETAIL_RESPONSE_TRANSFORMER);
				} else if (status.isPresent()) {
					return CollectionUtils.collect(sessionService.findByStatus(status.get()),
							DETAIL_RESPONSE_TRANSFORMER);
				} else {
					return CollectionUtils.collect(sessionService.findAll(), DETAIL_RESPONSE_TRANSFORMER);
				}
			}
		}
	}
}
