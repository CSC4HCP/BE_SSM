package com.sap.ssm.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sap.ssm.persistence.model.Session;

/**
 * @author Likai Deng, David Lin
 */
@Repository
@Transactional
public interface SessionRepository extends JpaRepository<Session, Long> {

	/**
	 * Find one session by owner
	 * 
	 * @param owner
	 *            Session's owner
	 * @return List<Session> a list of sessions
	 */
	List<Session> findByOwner(String owner);

	/**
	 * Find one session by category
	 * 
	 * @param category
	 *            Session's category
	 * @return List<Session> a list of sessions
	 */
	List<Session> findByCategory(String category);

	/**
	 * Find one session by status
	 * 
	 * @param status
	 *            Session's status
	 * @return List<Session> a list of sessions
	 */
	List<Session> findByStatus(String status);

	/**
	 * Find one session by owner and status
	 * 
	 * @param owner
	 *            Session's owner
	 * @param status
	 *            Session's status
	 * @return List<Session> a list of sessions
	 */
	List<Session> findByOwnerAndStatus(String owner, String status);

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
	List<Session> findByOwnerAndCategoryAndVisibility(String owner, String category, boolean visibility);

	/**
	 * Find one session by owner and category
	 * 
	 * @param owner
	 *            Session's owner
	 * @param category
	 *            Session's category
	 * @return List<Session> a list of sessions
	 */
	List<Session> findByOwnerAndCategory(String owner, String category);

	/**
	 * Find sessions by visibility
	 * 
	 * @param visibility
	 *            sessions' visibility
	 * @return List a list of sessions
	 */
	List<Session> findByVisibility(boolean visibility);

	/**
	 * Find sessions by category and visibility
	 * 
	 * @param category
	 *            sessions' category
	 * @param visibility
	 *            sessions' visibility
	 * @return List a list of sessions
	 */
	List<Session> findByCategoryAndVisibility(String category, boolean visibility);

	/**
	 * Find sessions by owner and visibility
	 * 
	 * @param owner
	 *            sessions' owner
	 * @param visibility
	 *            sessions' visibility
	 * @return List a list of sessions
	 */
	List<Session> findByOwnerAndVisibility(String owner, boolean visibility);

	/**
	 * Find sessions by status and visibility
	 * 
	 * @param status
	 *            sessions' status
	 * @param visibility
	 *            sessions' visibility
	 * @return List a list of sessions
	 */
	List<Session> findByStatusAndVisibility(String status, boolean visibility);

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
	List<Session> findByOwnerAndStatusAndVisibility(String owner, String status, boolean visibility);
}