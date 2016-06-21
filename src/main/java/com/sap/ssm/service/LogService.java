package com.sap.ssm.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sap.ssm.persistence.model.Log;
import com.sap.ssm.persistence.repository.LogRepository;
import com.sap.ssm.web.model.request.LogMergeRequest;

/**
 * The {@link}LogService includes operations about the {@link}Log object
 * @author Likai Deng
 */
@Configuration
@Service
@Transactional
@EnableScheduling
public class LogService {
    @Autowired
    private LogRepository logRepository;

    /**
     * Find all exist Log objects
     * @return List the list of all Log objects
     */
    public List<Log> findAll() {
	return logRepository.findAll();
    }

    /**
     * Create a Log object
     * @param logMergeRequest the request include all Log information
     * @return A {@link}Log object
     */
    public Log createOne(@NotNull LogMergeRequest logMergeRequest) {
	Log log = new Log();
	mergeScalarProperties(logMergeRequest, log);
	return logRepository.saveAndFlush(log);
    }

    /**
     * Merge log data from a {@link}logMergeRequest to a {@link}log object
     * @param logMergeRequest Object containing Log data
     * @param log the {@link}Log object
     */
    private void mergeScalarProperties(LogMergeRequest logMergeRequest, Log log) {
	log.setUserId(logMergeRequest.getUserId());
	log.setSession(logMergeRequest.getSession());
	log.setDate(logMergeRequest.getDate());
	log.setOperation(logMergeRequest.getOperation());

    }

    /**
     * Delete one log by id
     * @param id {@link}log's id
     * @return a message of the result
     */
    public String deleteOneById(Long id) {
	try {
	    logRepository.delete(id);
	    return "Delete Log successfully";
	} catch (IllegalArgumentException e) {
	    return e.getMessage();
	}
    }

    /**
     * Find logs by userId
     * @param userId {@link}log's userId
     * @return A list of {@link}Log objects
     */
    public List<Log> findByUserId(String userId) {
	return logRepository.findByUserId(userId);
    }

    /**
     * Automatically delete logs seven days ago everyday
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void deleteLogsSevenDaysAgo() {
	LocalDate dateSevenDaysAgo = LocalDate.now().minusDays(7L);
	long dateSevenDaysAgoTs = Timestamp.valueOf(dateSevenDaysAgo.atStartOfDay()).getTime();
	try {
	    logRepository.deleteInBatch(logRepository.findByDateBefore(dateSevenDaysAgoTs));
	} catch (IllegalArgumentException e) {
	}
    }

    /**
     * Find logs between the given days
     * @param date1 beginning date
     * @param date2 ending date
     * @return A list of {@link}Log objects
     */
    public List<Log> findByDateBetween(Timestamp date1, Timestamp date2) {
	return logRepository.findByDateBetween(date1, date2);
    }

}
