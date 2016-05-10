package com.sap.ssm.service;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sap.ssm.persistence.model.Session;
import com.sap.ssm.persistence.repository.implementations.SessionRepositoryImpl;
import com.sap.ssm.web.model.request.SessionMergeRequest;

/**
 * 
 * The {@link}SessionService includes operations about the {@link}Session object
 * 
 * @author Likai Deng
 *
 */
@Service
@Transactional
public class SessionService {

	@Autowired
	private SessionRepositoryImpl sessionRepositoryImpl;

	/**
	 * Create a session
	 * 
	 * @param sessionMergeRequest
	 *            the request include all session information
	 * @return A {@link}Session object
	 */
	public Session createOne(@NotNull SessionMergeRequest sessionMergeRequest) {
		Session session = new Session();
		mergeScalarProperties(sessionMergeRequest, session);
		return sessionRepositoryImpl.saveAndFlush(session);
	}

	/**
	 * Find one session by id
	 * 
	 * @param id
	 *            {@link}Session's id
	 * @return A {@link}Session object
	 */
	public Session findOneById(@NotNull Long id) {
		return sessionRepositoryImpl.findOne(id);
	}

	/**
	 * Delete one session by id
	 * 
	 * @param id
	 *            {@link}Session's id
	 */
	public void deleteOneById(@NotNull Long id) {
		sessionRepositoryImpl.delete(id);
	}

	/**
	 * Find one session by owner
	 * 
	 * @param id
	 *            {@link}Session's owner
	 * @return A {@link}Session object
	 */
	public Session findAllByOwner(@NotNull String owner) {
		return sessionRepositoryImpl.findAllByOwner(owner);
	}

	/**
	 * Find one session by category
	 * 
	 * @param id
	 *            {@link}Session's category
	 * @return A {@link}Session object
	 */
	public Session findAllByCategory(@NotNull String category) {
		return sessionRepositoryImpl.findAllByCategory(category);
	}

	/**
	 * Find one session by status
	 * 
	 * @param id
	 *            {@link}Session's status
	 * @return A {@link}Session object
	 */
	public Session findAllByStatus(@NotNull String status) {
		return sessionRepositoryImpl.findAllByStatus(status);
	}

	/**
	 * Find one session by owner and category
	 * 
	 * @param id
	 *            {@link}Session's owner and category
	 * @return A {@link}Session object
	 */
	public Session findAllByOwnerAndCategory(@NotNull String owner, @NotNull String category) {
		return sessionRepositoryImpl.findAllByOwnerAndCategory(owner, category);
	}

	/**
	 * Find one session by owner and status
	 * 
	 * @param id
	 *            {@link}Session's owner and status
	 * @return A {@link}Session object
	 */
	public Session findAllByOwnerAndStatus(@NotNull String owner, @NotNull String status) {
		return sessionRepositoryImpl.findAllByOwnerAndStatus(owner, status);
	}

	/**
	 * Update one session by id
	 * 
	 * @param id
	 *            {@link}Session's owner and status
	 * @return A {@link}Session object
	 */
	public Session updateOne(@NotNull Long id, @NotNull SessionMergeRequest sessionMergeRequest) {
		Session session = sessionRepositoryImpl.findOne(id);
		mergeScalarProperties(sessionMergeRequest, session);
		return sessionRepositoryImpl.saveAndFlush(session);
	}

	/**
	 * Merge session's data from a {@link}SessionMergeRequest to a
	 * {@link}Session object
	 * 
	 * @param sessionMergeRequest
	 *            the request include all session information
	 * @param session
	 *            the {@link}Session object
	 */
	private void mergeScalarProperties(SessionMergeRequest sessionMergeRequest, Session session) {
		session.setTopic(sessionMergeRequest.getTopic());
		session.setCategory(sessionMergeRequest.getCategory());
		session.setDescription(sessionMergeRequest.getDescription());
		session.setOwner(sessionMergeRequest.getOwner());
		session.setStatus(sessionMergeRequest.getStatus());
		session.setMeetingRoom(sessionMergeRequest.getMeetingRoom());
		session.setMeetingTime(sessionMergeRequest.getMeetingTime());
		session.setSummary(sessionMergeRequest.getSummary());
		session.setFile(sessionMergeRequest.getFile());
		session.setVisibility(sessionMergeRequest.isVisibility());
	}
}
