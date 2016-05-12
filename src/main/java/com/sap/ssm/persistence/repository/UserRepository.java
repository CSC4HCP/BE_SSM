/**
 * 
 */
package com.sap.ssm.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sap.ssm.persistence.model.User;

/**
 * The JPA Repository for User
 * 
 * @author I326996 David Lin
 */
@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, String> {

}
