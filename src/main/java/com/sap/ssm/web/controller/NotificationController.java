package com.sap.ssm.web.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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

import com.sap.ssm.persistence.model.Notification;
import com.sap.ssm.service.NotificationService;
import com.sap.ssm.web.model.request.NotificationMergeRequest;
import com.sap.ssm.web.model.response.NotificationDetailResponse;

/**
 * The {@link}RestController for notification entity.
 * @author Likai Deng
 */

@RestController
@RequestMapping("/notify")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;
    private Transformer<Notification, NotificationDetailResponse> DETAIL_RESPONSE_TRANSFORMER = new Transformer<Notification, NotificationDetailResponse>() {

	@Override
	public NotificationDetailResponse transform(Notification input) {
	    return new NotificationDetailResponse(input);
	}
    };

    /**
     * The API to <b>POST</b> create a new notification.<br> <br> API URL -
     * <b>"/api/notify"</b><br> Method - <b>"POST"</b>
     * @return the {@link}Collection of {@link}NotificationDetailResponse.
     */
    @RequestMapping(method = RequestMethod.POST)
    public NotificationDetailResponse createOne(
	    @NotNull @RequestBody NotificationMergeRequest notificationMergeRequest) {
	return new NotificationDetailResponse(notificationService.createOne(notificationMergeRequest));
    }

    /**
     * The API for delete a notification<br> <br> API URL -
     * <b>"/api/notify/{id}"</b> <br> Method - <b>"DELETE"</b>
     * @param id notification id
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public String deleteOneById(@PathVariable("id") Long id) {
	return notificationService.deleteOneById(id);
    }

    /**
     * The API for delete notifications in batch<br> <br> API URL -
     * <b>"/api/notify"</b> <br> Method - <b>"DELETE"</b>
     * @param notificationIdList A list of ids for notifications to delete
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public String deleteInBatch(@NotNull @RequestBody List<Long> notificationIdList) {
	List<Notification> notificationList = new ArrayList<Notification>();
	for (Long notificationId : notificationIdList) {
	    notificationList.add(notificationService.findOneById(notificationId));
	}
	return notificationService.deleteInBatch(notificationList);
    }

    /**
     * The API for update a notification<br> <br> API URL -
     * <b>"/api/notify/{id}"</b><br> Method - <b>"PUT"</b>
     * @param id notification id
     * @param notificationMergeRequest request body for notification
     * @return the response body for a notification
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public NotificationDetailResponse updateOne(@PathVariable("id") Long id,
	    @NotNull @RequestBody NotificationMergeRequest notificationMergeRequest) {
	return new NotificationDetailResponse(notificationService.updateOne(id, notificationMergeRequest));
    }

    /**
     * The API for find notifications by several optional parameters<br> <br>
     * API URL - <b>"/api/notify"</b><br> Method - <b>"GET"</b>
     * @param checked Optional notification is checked or not
     * @param target Optional notification's target
     * @return a collection of notifications
     */
    @RequestMapping(method = RequestMethod.GET)
    public Collection<NotificationDetailResponse> findBySeveralConditions(
	    @RequestParam("checked") Optional<Boolean> checked, @RequestParam("target") Optional<String> target) {
	if (checked.isPresent() && target.isPresent()) {
	    return CollectionUtils.collect(notificationService.findByCheckedAndTarget(checked.get(), target.get()),
		    DETAIL_RESPONSE_TRANSFORMER);
	} else if (checked.isPresent()) {
	    return CollectionUtils.collect(notificationService.findByChecked(checked.get()),
		    DETAIL_RESPONSE_TRANSFORMER);
	} else if (target.isPresent()) {
	    return CollectionUtils.collect(notificationService.findByTarget(target.get()), DETAIL_RESPONSE_TRANSFORMER);
	} else {
	    return CollectionUtils.collect(notificationService.findAll(), DETAIL_RESPONSE_TRANSFORMER);
	}
    }
}
