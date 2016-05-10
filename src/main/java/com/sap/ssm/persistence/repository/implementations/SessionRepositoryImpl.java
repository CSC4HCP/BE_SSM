/**
 * 
 */
package com.sap.ssm.persistence.repository.implementations;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sap.ssm.persistence.model.Session;
import com.sap.ssm.persistence.repository.interfaces.SessionRepository;

/**
 * @author I327338
 *
 */
@Repository
@Transactional
public class SessionRepositoryImpl extends SimpleJpaRepository<Session, Long>implements SessionRepository {

	@Autowired
	public SessionRepositoryImpl(EntityManager em) {
		super(Session.class, em);
	}

	@Override
	public Session findAllByCategory(String category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Session findAllByOwner(String owner) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Session findAllByStatus(String status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Session findAllByOwnerAndCategory(String owner, String category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Session findAllByOwnerAndStatus(String owner, String status) {
		// TODO Auto-generated method stub
		return null;
	}

}
