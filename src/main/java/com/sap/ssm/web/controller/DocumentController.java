package com.sap.ssm.web.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.apache.chemistry.opencmis.commons.enums.VersioningState;
import org.apache.chemistry.opencmis.commons.exceptions.CmisNameConstraintViolationException;
import org.apache.chemistry.opencmis.commons.exceptions.CmisObjectNotFoundException;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.sap.ecm.api.EcmService;
import com.sap.ecm.api.RepositoryOptions;
import com.sap.ecm.api.RepositoryOptions.Visibility;

/**
 * The RestController for Document Service
 * 
 * @author I326996 David Lin
 */
@RestController
@RequestMapping("/document")
public class DocumentController {

	@Autowired
	private InitialContext initialContext;

	@Autowired
	private CommonsMultipartResolver multipartResolver;

	private static final String uniqueName = "ssm_document_repository";

	private static final String repoKey = "my_repository_key";

	private Session openCmisSession;

	private void initOpenCmisSession() {
		if (this.openCmisSession == null) {
			EcmService ecmSvc = null;
			try {
				ecmSvc = (EcmService) initialContext.lookup("java:comp/env/EcmService");
				this.openCmisSession = ecmSvc.connect(uniqueName, repoKey);
			} catch (CmisObjectNotFoundException e) {
				RepositoryOptions repoOpt = new RepositoryOptions();
				repoOpt.setUniqueName(uniqueName);
				repoOpt.setRepositoryKey(repoKey);
				repoOpt.setVisibility(Visibility.PROTECTED);
				ecmSvc.createRepository(repoOpt);
				this.openCmisSession = ecmSvc.connect(uniqueName, repoKey);
			} catch (NamingException e) {
				throw new IllegalStateException(e);
			}
		}
	}

	/**
	 * API for upload files<br>
	 * <br>
	 * API URL - <b>"/api/document/upload/{sessionId}"</b><br>
	 * Method - <b>"POST"</b>
	 * 
	 * @param request
	 *            the HTTP Servlet Request
	 * @param session
	 *            the session id
	 * @return the Array of document id or error message if no file uploaded
	 * @throws {@link}}IOException
	 */
	@RequestMapping(value = "/upload/{session}", method = RequestMethod.POST)
	public Object[] uploadFiles(HttpServletRequest request, @PathVariable("session") String session)
			throws IOException {
		if (this.multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest mRequest = this.multipartResolver.resolveMultipart(request);
			Iterator<String> files = mRequest.getFileNames();
			List<String> fileIds = new ArrayList<>();
			while (files.hasNext()) {
				MultipartFile mFile = mRequest.getFile(files.next());
				if (mFile != null) {
					if (this.openCmisSession == null) {
						initOpenCmisSession();
					}
					Folder rootFolder = this.openCmisSession.getRootFolder();
					Map<String, String> newFileProps = new HashMap<>();
					newFileProps.put(PropertyIds.OBJECT_TYPE_ID, "cmis:document");
					newFileProps.put(PropertyIds.NAME, UUID.randomUUID().toString());
					newFileProps.put(PropertyIds.DESCRIPTION, session);
					newFileProps.put(PropertyIds.CREATED_BY, request.getRemoteUser());
					newFileProps.put(PropertyIds.CREATION_DATE, (new Date(System.currentTimeMillis())).toString());
					ContentStream cStream;
					Document document;
					try {
						cStream = openCmisSession.getObjectFactory().createContentStream(mFile.getOriginalFilename(),
								mFile.getBytes().length, mFile.getContentType(), mFile.getInputStream());
						document = rootFolder.createDocument(newFileProps, cStream, VersioningState.NONE);
						fileIds.add(document.getId());
					} catch (CmisNameConstraintViolationException e) {
						fileIds.add(e.getMessage());
					}
				}
			}
			return fileIds.toArray();
		}
		return new String[] { "No file uploaded" };
	}

	/**
	 * API for download file<br>
	 * <br>
	 * API URL - <b>"/api/document/download/{fileId}"</b><br>
	 * Method - <b>"GET"</b>
	 * 
	 * @param fileId
	 *            file id
	 * @param response
	 *            the HTTP Servlet Response
	 * @throws {@link}IOException
	 */
	@RequestMapping(value = "/download/{fileId}", method = RequestMethod.GET)
	public void downloadFile(@PathVariable String fileId, HttpServletResponse response) throws IOException {
		if (this.openCmisSession == null) {
			initOpenCmisSession();
		}
		Document document = (Document) openCmisSession.getObject(fileId);
		ContentStream cStream = document.getContentStream();
		InputStream iStream = cStream.getStream();
		response.setContentType(cStream.getMimeType());
		response.setHeader("content-disposition",
				"attachment;filename=" + document.getPropertyValue(PropertyIds.CONTENT_STREAM_FILE_NAME));
		IOUtils.copy(iStream, response.getOutputStream());
	}
}
