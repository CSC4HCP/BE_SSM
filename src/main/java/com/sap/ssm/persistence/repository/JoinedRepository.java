package com.sap.ssm.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sap.ssm.persistence.model.Joined;

/**
 * The JPA Repository for Joined
 * 
 * @author I326962 Zero Yu
 */
@Repository
@Transactional
public interface JoinedRepository extends JpaRepository<Joined, Long> {
	
	/**
	 * Find join objects by one's userId
	 * 
	 * @param userId
	 *            user id
	 * @return List<Joined> a list of join objects
	 */
	List<Joined> findByUserId(String userId);
	
	/**
	 * Find join objects by session id
	 * 
	 * @param Session
	 *            Session id
	 * @return List<Joined> one join object
	 */
	List<Joined> findBySession(Long Session);
}
