package com.sap.ssm.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sap.ssm.persistence.model.Category;

/**
 * @author Yuan Zhang
 */
@Repository
@Transactional
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
