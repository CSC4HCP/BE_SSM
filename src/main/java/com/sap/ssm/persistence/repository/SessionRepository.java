package com.sap.ssm.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sap.ssm.persistence.model.Session;

/**
 * @author Likai Deng
 *
 */
@Repository
@Transactional
public interface SessionRepository extends JpaRepository<Session, Long> {

	// List<Session> findAllByCategory(String category);

	List<Session> findByOwner(String owner);

	List<Session> findByCategory(String owner);

	List<Session> findByStatus(String owner);

	List<Session> findByOwnerAndStatus(String owner, String status);

	List<Session> findByOwnerAndCategory(String owner, String category);

	// List<Session> findAllByStatus(String status);
	//
	// List<Session> findAllByOwnerAndCategory(String owner, String category);
	//
	// List<Session> findAllByOwnerAndStatus(String owner, String status);

}