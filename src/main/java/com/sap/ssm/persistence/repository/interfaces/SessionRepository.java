package com.sap.ssm.persistence.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import com.sap.ssm.persistence.model.Session;

/**
 * @author Likai Deng
 *
 */
@NoRepositoryBean
@Transactional
public interface SessionRepository extends JpaRepository<Session, Long> {

	Session findAllByCategory(String category);

	Session findAllByOwner(String owner);

	Session findAllByStatus(String status);

	Session findAllByOwnerAndCategory(String owner, String category);

	Session findAllByOwnerAndStatus(String owner, String status);

}