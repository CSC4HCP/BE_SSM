package com.sap.ssm.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sap.ssm.persistence.model.Notification;

@Repository
@Transactional
public interface NotificationRepository extends JpaRepository<Notification, Long> {
	/**
	 * Find one notification by category
	 * 
	 * @param category
	 *            notification category
	 * @return List<Notification> a list of notifications
	 */
	List<Notification> findByTarget(String target);

	/**
	 * Find one notification by checked
	 * 
	 * @param checked
	 *            whether the notification is checked already
	 * @return List<Notification> a list of notifications
	 */
	List<Notification> findByChecked(Boolean checked);

	/**
	 * Find notifications by checked and target
	 * 
	 * @param checked
	 *            whether the notification is checked already
	 * @param target
	 *            target who the notification is sent to
	 * @return List<Notification> a list of notifications
	 */
	List<Notification> findByCheckedAndTarget(Boolean checked, String target);
}
