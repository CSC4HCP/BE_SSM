package com.sap.ssm.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sap.ssm.persistence.model.Session;

/*
 * @author Likai Deng
 */
@Repository
@Transactional
public interface SessionRepository extends JpaRepository<Session, Long> {
    /**
     * Find one session by owner
     * @param owner Session's owner
     * @return List<Session> a list of sessions
     */
    List<Session> findByOwner(String owner);

    /**
     * Find one session by category
     * @param category Session's category
     * @return List<Session> a list of sessions
     */
    List<Session> findByCategory(String category);

    /**
     * Find one session by status
     * @param status Session's status
     * @return List<Session> a list of sessions
     */
    List<Session> findByStatus(String status);

    /**
     * Find one session by owner and status
     * @param owner Session's owner
     * @param status Session's status
     * @return List<Session> a list of sessions
     */
    List<Session> findByOwnerAndStatus(String owner, String status);

    /**
     * Find one session by owner and category
     * @param owner Session's owner
     * @param category Session's category
     * @return List<Session> a list of sessions
     */
    List<Session> findByOwnerAndCategory(String owner, String category);

}