package com.sap.ssm.web.controller;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sap.ssm.persistence.model.Log;
import com.sap.ssm.service.LogService;
import com.sap.ssm.web.model.request.LogMergeRequest;
import com.sap.ssm.web.model.response.LogDetailResponse;

/**
 * The {@link}RestController for log entity.
 * 
 * @author Likai Deng
 */
@RestController
@RequestMapping("/log")
public class LogController {
	@Autowired
	private LogService logService;
	private Transformer<Log, LogDetailResponse> DETAIL_RESPONSE_TRANSFORMER = new Transformer<Log, LogDetailResponse>() {

		@Override
		public LogDetailResponse transform(Log input) {
			return new LogDetailResponse(input);
		}
	};

	/**
	 * The API to create a new Log<br>
	 * <br>
	 * API URL - <b>"/api/log"</b><br>
	 * Method - <b>"POST"</b>
	 * 
	 * @param logMergeRequest
	 *            the request body for creating a new log
	 * @return a {@link}LogDetailResponse object.
	 */
	@RequestMapping(method = RequestMethod.POST)
	public LogDetailResponse createOne(@NotNull @RequestBody LogMergeRequest logMergeRequest) {
		return new LogDetailResponse(logService.createOne(logMergeRequest));
	}

	/**
	 * The API for delete a log by its id<br>
	 * <br>
	 * API URL - <b>"/api/session/{id}"</b> <br>
	 * Method - <b>"DELETE"</b>
	 * 
	 * @param id
	 *            session id
	 * @return the result message
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public String deleteOneById(@PathVariable("id") Long id) {
		return logService.deleteOneById(id);
	}

	/**
	 * The API for find logs by several optional parameters<br>
	 * <br>
	 * API URL - <b>"/api/session"</b><br>
	 * Method - <b>"GET"</b>
	 * 
	 * @param userId
	 *            Optional log userId The userId of the log
	 * @param date1
	 *            Optional log date1 The beginning date
	 * @param date2
	 *            Optional log date2 The ending date
	 * @return a collection of log response
	 */
	@RequestMapping(method = RequestMethod.GET)
	public Collection<LogDetailResponse> findBySeveralConditions(@RequestParam("userId") Optional<String> userId,
			@RequestParam("date1") Optional<String> date1, @RequestParam("date2") Optional<String> date2) {
		if (userId.isPresent()) {
			return CollectionUtils.collect(logService.findByUserId(userId.get()), DETAIL_RESPONSE_TRANSFORMER);
		} else if (date1.isPresent() && date2.isPresent()) {

			Timestamp date1Ts = new Timestamp(System.currentTimeMillis());
			date1Ts = Timestamp.valueOf(date1.get());

			Timestamp date2Ts = new Timestamp(System.currentTimeMillis());
			date2Ts = Timestamp.valueOf(date2.get());

			return CollectionUtils.collect(logService.findByDateBetween(date1Ts, date2Ts), DETAIL_RESPONSE_TRANSFORMER);

		} else {
			return CollectionUtils.collect(logService.findAll(), DETAIL_RESPONSE_TRANSFORMER);

		}
	}
}
