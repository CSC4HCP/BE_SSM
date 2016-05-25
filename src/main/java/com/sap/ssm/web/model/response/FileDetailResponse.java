package com.sap.ssm.web.model.response;

import org.apache.chemistry.opencmis.client.api.QueryResult;
import org.apache.chemistry.opencmis.commons.PropertyIds;

public class FileDetailResponse {

	private String fileId;

	private String fileName;

	public FileDetailResponse() {

	}

	public FileDetailResponse(QueryResult result) {
		this.fileId = result.getPropertyValueByQueryName(PropertyIds.OBJECT_ID);
		this.fileName = result.getPropertyValueByQueryName(PropertyIds.CONTENT_STREAM_FILE_NAME);
	}

	/**
	 * @return the fileId
	 */
	public String getFileId() {
		return fileId;
	}

	/**
	 * @param fileId
	 *            the fileId to set
	 */
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName
	 *            the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
