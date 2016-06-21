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

	@RequestMapping(method = RequestMethod.POST)
	public LogDetailResponse createOne(@NotNull @RequestBody LogMergeRequest logMergeRequest) {
		return new LogDetailResponse(logService.createOne(logMergeRequest));
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public String deleteOneById(@PathVariable("id") Long id) {
		return logService.deleteOneById(id);
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public void deleteLogsSevenDaysAgo() {
		logService.deleteLogsSevenDaysAgo();
	}

	@RequestMapping(method = RequestMethod.GET)
	public Collection<LogDetailResponse> findBySeveralConditions(@RequestParam("userId") Optional<String> userId,
			@RequestParam("startDate") Optional<String> date1, @RequestParam("endDate") Optional<String> date2) {
		if (userId.isPresent()) {
			return CollectionUtils.collect(logService.findByUserId(userId.get()), DETAIL_RESPONSE_TRANSFORMER);
		} else if (date1.isPresent() && date2.isPresent()) {
			// SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd
			// HH:mm:ss");

			long date1Long = Long.parseLong(date1.get());
			Timestamp date1Ts = new Timestamp(date1Long);

			// Timestamp afterTs = new Timestamp(System.currentTimeMillis());
			long date2Long = Long.parseLong(date2.get());
			Timestamp date2Ts = new Timestamp(date2Long);
			return CollectionUtils.collect(logService.findByDateBetween(date1Ts, date2Ts), DETAIL_RESPONSE_TRANSFORMER);
			// return
			// CollectionUtils.collect(logService.findByUserId(beforeDate.get()),
			// DETAIL_RESPONSE_TRANSFORMER);

		} else {
			return CollectionUtils.collect(logService.findAll(), DETAIL_RESPONSE_TRANSFORMER);
		}
	}
}
