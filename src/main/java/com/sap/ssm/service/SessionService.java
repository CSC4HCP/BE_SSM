package com.sap.ssm.service;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sap.ssm.persistence.model.Session;
import com.sap.ssm.persistence.repository.SessionRepository;
import com.sap.ssm.web.model.request.SessionMergeRequest;

/**
 * 
 * The {@link}SessionService includes operations about the {@link}Session object
 * 
 * @author Likai Deng
 */
@Service
@Transactional
public class SessionService {

	@Autowired
	private SessionRepository sessionRepository;

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
		return sessionRepository.saveAndFlush(session);
	}

	/**
	 * Find one session by id
	 * 
	 * @param id
	 *            {@link}Session's id
	 * @return A {@link}Session object
	 */
	public Session findOneById(@NotNull Long id) {
		return sessionRepository.findOne(id);
	}

	/**
	 * Update one session by id
	 * 
	 * @param id
	 *            {@link}Session's owner and status
	 * @return A {@link}Session object
	 */
	public Session updateOne(@NotNull Long id, @NotNull SessionMergeRequest sessionMergeRequest) {
		Session session = sessionRepository.findOne(id);
		mergeScalarProperties(sessionMergeRequest, session);
		return sessionRepository.saveAndFlush(session);
	}

	/**
	 * Delete one session by id
	 * 
	 * @param id
	 *            {@link}Session's id
	 * @return state message
	 */
	public String deleteOneById(@NotNull Long id) {
		try {
			sessionRepository.delete(id);
			return "Delete session successfully";
		} catch (IllegalArgumentException e) {
			return e.getMessage();
		}
	}

	/**
	 * Find all exist sessions
	 * 
	 * @return List the list of all sessions
	 */
	public List<Session> findAll() {
		return sessionRepository.findAll();
	}

	/**
	 * Find one session by owner
	 * 
	 * @param id
	 *            {@link}Session's owner
	 * @return A {@link}Session object
	 */
	public List<Session> findByOwner(@NotNull String owner) {
		return (sessionRepository).findByOwner(owner);
	}

	/**
	 * Find one session by category
	 * 
	 * @param id
	 *            {@link}Session's category
	 * @return A {@link}Session object
	 */
	public List<Session> findByCategory(@NotNull String category) {
		return sessionRepository.findByCategory(category);
	}

	/**
	 * Find one session by status
	 * 
	 * @param id
	 *            {@link}Session's status
	 * @return A {@link}Session object
	 */
	public List<Session> findByStatus(@NotNull String status) {
		return sessionRepository.findByStatus(status);
	}

	/**
	 * Find one session by owner and category
	 * 
	 * @param id
	 *            {@link}Session's owner and category
	 * @return A {@link}Session object
	 */
	public List<Session> findByOwnerAndCategory(@NotNull String owner, @NotNull String category) {
		return sessionRepository.findByOwnerAndCategory(owner, category);
	}

	/**
	 * Find one session by owner and status
	 * 
	 * @param id
	 *            {@link}Session's owner and status
	 * @return A {@link}Session object
	 */
	public List<Session> findByOwnerAndStatus(@NotNull String owner, @NotNull String status) {
		return sessionRepository.findByOwnerAndStatus(owner, status);
	}

	/**
	 * Find sessions by visibility
	 * 
	 * @param visibility
	 *            sessions' visibility
	 * @return List a list of sessions
	 */
	public List<Session> findByVisibility(boolean visibility) {
		return sessionRepository.findByVisibility(visibility);
	}

	/**
	 * Find sessions by category and visibility
	 * 
	 * @param category
	 *            sessions' category
	 * @param visibility
	 *            sessions' visibility
	 * @return List a list of sessions
	 */
	public List<Session> findByCategoryAndVisibility(@NotNull String category, boolean visibility) {
		return sessionRepository.findByCategoryAndVisibility(category, visibility);
	}

	/**
	 * Find sessions by owner and visibility
	 * 
	 * @param owner
	 *            sessions' owner
	 * @param visibility
	 *            sessions' visibility
	 * @return List a list of sessions
	 */
	public List<Session> findByOwnerAndVisibility(@NotNull String owner, boolean visibility) {
		return sessionRepository.findByOwnerAndVisibility(owner, visibility);
	}

	/**
	 * Find sessions by status and visibility
	 * 
	 * @param status
	 *            sessions' status
	 * @param visibility
	 *            sessions' visibility
	 * @return List a list of sessions
	 */
	public List<Session> findByStatusAndVisibility(@NotNull String status, boolean visibility) {
		return sessionRepository.findByStatusAndVisibility(status, visibility);
	}

	/**
	 * Find sessions by owner, category and visibility
	 * 
	 * @param owner
	 *            sessions' owner
	 * @param category
	 *            sessions' category
	 * @param visibility
	 *            sessions' visibility
	 * @return List a list of sessions
	 */
	public List<Session> findByOwnerAndCategoryAndVisibility(@NotNull String owner, @NotNull String category,
			boolean visibility) {
		return sessionRepository.findByOwnerAndCategoryAndVisibility(owner, category, visibility);
	}

	/**
	 * Find sessions by owner, status and visibility
	 * 
	 * @param owner
	 *            sessions' owner
	 * @param status
	 *            sessions' status
	 * @param visibility
	 *            sessions' visibility
	 * @return List a list of sessions
	 */
	public List<Session> findByOwnerAndStatusAndVisibility(@NotNull String owner, @NotNull String status,
			boolean visibility) {
		return sessionRepository.findByOwnerAndStatusAndVisibility(owner, status, visibility);
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
