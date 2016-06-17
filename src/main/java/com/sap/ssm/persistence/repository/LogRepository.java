package com.sap.ssm.persistence.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sap.ssm.persistence.model.Log;

@Repository
@Transactional
public interface LogRepository extends JpaRepository<Log, Long> {

    List<Log> findByUserId(String userId);

    List<Log> findAll(Specification<Log> sevenDaysAgo);

    List<Log> findByDateBetween(Timestamp date1, Timestamp date2);

    List<Log> findByDateBefore(Timestamp date1);

}
