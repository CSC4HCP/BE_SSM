package com.sap.ssm.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sap.ssm.common.MyJobOne;
import com.sap.ssm.persistence.model.Log;
import com.sap.ssm.persistence.repository.LogRepository;
import com.sap.ssm.web.model.request.LogMergeRequest;

@Configuration
@Service
@Transactional
@EnableScheduling
public class LogService {
    @Autowired
    private LogRepository logRepository;
    public static final Logger logger = LoggerFactory.getLogger(MyJobOne.class);

    /**
     * Find all exist Log objects
     * @return List the list of all Log objects
     */
    public List<Log> findAll() {
	return logRepository.findAll();
    }

    /**
     * Create a joined object
     * @param userMergeRequest the request include all user information
     * @return A {@link}User object
     */
    public Log createOne(@NotNull LogMergeRequest logMergeRequest) {
	Log log = new Log();
	mergeScalarProperties(logMergeRequest, log);
	return logRepository.saveAndFlush(log);
    }

    private void mergeScalarProperties(LogMergeRequest logMergeRequest, Log log) {
	log.setUserId(logMergeRequest.getUserId());
	log.setSession(logMergeRequest.getSession());
	log.setDate(logMergeRequest.getDate());
	log.setOperation(logMergeRequest.getOperation());

    }

    public String deleteOneById(Long id) {
	try {
	    logRepository.delete(id);
	    return "Delete Log successfully";
	} catch (IllegalArgumentException e) {
	    return e.getMessage();
	}
    }

    public List<Log> findByUserId(String userId) {
	return logRepository.findByUserId(userId);
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void deleteLogsSevenDaysAgo() {
	LocalDate dateSevenDaysAgo = LocalDate.now().minusDays(7L);
	Timestamp dateSevenDaysAgoTs = Timestamp.valueOf(dateSevenDaysAgo.atStartOfDay());
	try {
	    logRepository.deleteInBatch(logRepository.findByDateBefore(dateSevenDaysAgoTs));
	} catch (IllegalArgumentException e) {
	}
    }

    public List<Log> findByDateBetween(Timestamp date1, Timestamp date2) {

	return logRepository.findByDateBetween(date1, date2);
    }

    public static Specification<Log> isSevenDaysAgo() {
	return new Specification<Log>() {
	    @Override
	    public Predicate toPredicate(Root<Log> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		root = query.from(Log.class);
		Path<Timestamp> time = root.get("date");
		LocalDate date = LocalDate.now();
		Timestamp currentDate = Timestamp.valueOf(date.atStartOfDay());
		return builder.lessThan(time, currentDate);
	    }
	};
    }

    public static Specification<Log> isBeforeGivenDate(Timestamp beforeDate) {
	return new Specification<Log>() {
	    @Override
	    public Predicate toPredicate(Root<Log> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		root = query.from(Log.class);
		Path<Timestamp> time = root.get("date");
		return builder.lessThan(time, beforeDate);
	    }
	};
    }

    public static Specification<Log> isAfterGivenDate(Timestamp afterDate) {
	return new Specification<Log>() {
	    @Override
	    public Predicate toPredicate(Root<Log> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		root = query.from(Log.class);
		Path<Timestamp> time = root.get("date");
		return builder.greaterThan(time, afterDate);
	    }
	};
    }
}
