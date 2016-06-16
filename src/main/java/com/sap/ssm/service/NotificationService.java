package com.sap.ssm.service;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sap.ssm.persistence.model.Notification;
import com.sap.ssm.persistence.repository.NotificationRepository;
import com.sap.ssm.web.model.request.NotificationMergeRequest;

@Service
@Transactional
public class NotificationService {

	@Autowired
	private NotificationRepository notificationRepository;

	/**
	 * Create a notification
	 * 
	 * @param notificationMergeRequest
	 *            the request include all notification information
	 * @return A {@link}Notification object
	 */
	public Notification createOne(@NotNull NotificationMergeRequest notificationMergeRequest) {
		Notification notification = new Notification();
		mergeScalarProperties(notificationMergeRequest, notification);
		return notificationRepository.saveAndFlush(notification);
	}

	/**
	 * Delete one notification by id
	 * 
	 * @param id
	 *            {@link}notification's id
	 * @return a message of the result
	 */
	public String deleteOneById(@NotNull Long id) {
		try {
			notificationRepository.delete(id);
			return "Delete notification successfully";
		} catch (IllegalArgumentException e) {
			return e.getMessage();
		}
	}

	/**
	 * Update one notification by id
	 * 
	 * @param id
	 *            {@link}notification's id
	 * @return A {@link}notification object
	 */
	public Notification updateOne(@NotNull Long id, @NotNull NotificationMergeRequest notificationMergeRequest) {
		Notification notification = notificationRepository.findOne(id);
		mergeScalarProperties(notificationMergeRequest, notification);
		return notificationRepository.saveAndFlush(notification);
	}

	/**
	 * Find all exist notifications
	 * 
	 * @return List the list of all notifications
	 */
	public List<Notification> findAll() {
		return notificationRepository.findAll();
	}

	/**
	 * Find one notification by id
	 * 
	 * @param id
	 *            {@link}notification's id
	 * @return A {@link}Notification object
	 */
	public Notification findOneById(@NotNull Long id) {
		return notificationRepository.findOne(id);
	}

	/**
	 * Find one notification by target
	 * 
	 * @param id
	 *            {@link}Notification's target
	 * @return A list of {@link}Notification objects
	 */
	public List<Notification> findByTarget(@NotNull String target) {
		return notificationRepository.findByTarget(target);
	}

	/**
	 * Find notifications by checked
	 * 
	 * @param checked
	 *            {@link}Notification is checked or not
	 * @return A list of {@link}Notification objects
	 */
	public List<Notification> findByChecked(@NotNull Boolean checked) {
		return notificationRepository.findByChecked(checked);
	}

	/**
	 * Find notifications by checked and target
	 * 
	 * @param checked
	 *            {@link}Notification is checked or not
	 * @param target
	 *            {@link}Notification's target
	 * @return A list of {@link}Notification objects
	 */
	public List<Notification> findByCheckedAndTarget(@NotNull Boolean checked, @NotNull String target) {
		return notificationRepository.findByCheckedAndTarget(checked, target);
	}

	/**
	 * Delete notifications in batch
	 * 
	 * @param notificationIdList
	 *            A list of ids for notifications to delete
	 */
	public String deleteInBatch(@NotNull List<Notification> list) {
		try {
			notificationRepository.deleteInBatch(list);
			return "Delete notifications in batch successfully";
		} catch (IllegalArgumentException e) {
			return e.getMessage();
		}
	}

	/**
	 * Merge notification's data from a {@link}notificationMergeRequest to a
	 * {@link}notification object
	 * 
	 * @param notificationMergeRequest
	 *            the request include all notification information
	 * @param notification
	 *            the {@link}notification object
	 */
	private void mergeScalarProperties(NotificationMergeRequest notificationMergeRequest, Notification notification) {
		notification.setTarget(notificationMergeRequest.getTarget());
		notification.setContent(notificationMergeRequest.getContent());
		notification.setChecked(notificationMergeRequest.getChecked());
	}

	/**
	 * Find whether the User of the given target has unchecked notification
	 * 
	 * @param Boolean
	 *            checked Always true
	 * @param String
	 *            target The target's I/C/D number
	 * @return Boolean true if there are unchecked notifications,false if there
	 *         are not unchecked notifications
	 */
	public Boolean findWhetherTheTargetHasUncheckedNotification(@NotNull Boolean checked, @NotNull String target) {
		List<Notification> notification = notificationRepository.findByCheckedAndTarget(checked, target);
		if (notification.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
}
