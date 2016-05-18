package com.sap.ssm.service;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sap.ssm.persistence.model.Joined;
import com.sap.ssm.persistence.repository.JoinedRepository;
import com.sap.ssm.web.model.request.JoinedMergeRequest;

/**
 * The {@link}JoinedService includes operations about the {@link}Joined object
 * 
 * @author I326962 Zero Yu
 */
@Service
@Transactional
public class JoinedService {
	
	@Autowired
	private JoinedRepository joinedRepository;
	
	/**
	 * Create a joined object
	 * 
	 * @param userMergeRequest
	 *            the request include all user information
	 * @return A {@link}User object
	 */
	public Joined createOne(@NotNull JoinedMergeRequest joinedMergeRequest) {
		Joined joined = new Joined();
		mergeScalarProperties(joinedMergeRequest, joined);
		return joinedRepository.saveAndFlush(joined);
	}
	
	/**
	 * Find join objects by userId
	 * 
	 * @param userId
	 *            {@link}User's userId
	 * @return a set of {@link}Joined objects
	 */
	public List<Joined> findByUserId(@NotNull String userId) {
		return joinedRepository.findByUserId(userId);
	}
	
	/**
	 * Find join objects by session
	 * 
	 * @param userId
	 *            {@link}Session's session
	 * @return a set of {@link}Joined objects
	 */
	public List<Joined> findBySessionId(@NotNull Long session) {
		return joinedRepository.findBySession(session);
	}
	
	/**
	 * Merge joined data from a {@link}JoinedMergeRequest to a {@link}Joined object
	 * 
	 * @param joinedMergeRequest
	 *            the request include all user information
	 * @param joined
	 *            the {@link}Joined object
	 */
	private void mergeScalarProperties(JoinedMergeRequest joinedMergeRequest, Joined joined) {
		joined.setUserId(joinedMergeRequest.getUserId());
		joined.setSession(joinedMergeRequest.getSession());
		joined.setDate(joinedMergeRequest.getDate());
	}
	
}
