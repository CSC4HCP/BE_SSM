/**
 * 
 */
package com.sap.ssm.web.model.request;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

/**
 * * The NotificationMergeRequest is a request object which used to store
 * notification data before the data merged into a {@link}Notification object
 * @author Likai Deng
 */
public class NotificationMergeRequest implements Serializable {

    /**
     * Generated serial version id
     */
    private static final long serialVersionUID = 1655880420433320294L;

    @NotBlank
    private Long id;

    @NotBlank
    private String target;

    @NotBlank
    private String content;

    @NotBlank
    private Boolean checked;

    /**
     * @return the id
     */
    public Long getId() {
	return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
	this.id = id;
    }

    /**
     * @return the target
     */
    public String getTarget() {
	return target;
    }

    /**
     * @param target the target to set
     */
    public void setTarget(String target) {
	this.target = target;
    }

    /**
     * @return the content
     */
    public String getContent() {
	return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
	this.content = content;
    }

    /**
     * @return the checked
     */
    public Boolean getChecked() {
	return checked;
    }

    /**
     * @param checked the checked to set
     */
    public void setChecked(Boolean checked) {
	this.checked = checked;
    }

}
