package com.sap.ssm.persistence.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sap.ssm.persistence.model.Log;

@Repository
@Transactional
public interface LogRepository extends JpaRepository<Log, Long> {

	/**
	 * Find one log by id
	 * 
	 * @param id
	 *            log's id
	 * @return List<log> a list of logs
	 */
	List<Log> findByUserId(String userId);

	/**
	 * Find logs between the given two dates
	 * 
	 * @param date1
	 *            The beginning date
	 * @param date2
	 *            The ending date
	 * @return List<log> a list of logs
	 */
	List<Log> findByDateBetween(Timestamp date1, Timestamp date2);

	/**
	 * Find logs before the given date
	 * 
	 * @param date1
	 * @return List<log> a list of logs
	 */
	List<Log> findByDateBefore(Long date1);

}
