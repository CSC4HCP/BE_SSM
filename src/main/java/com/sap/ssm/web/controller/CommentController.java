package com.sap.ssm.web.controller;

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

import com.sap.ssm.persistence.model.Comment;
import com.sap.ssm.service.CommentService;
import com.sap.ssm.web.model.request.CommentMergeRequest;
import com.sap.ssm.web.model.response.CommentDetailResponse;

/**
 * The {@link}RestController for session entity.
 * 
 * @author Yuan Zhang
 *
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	private CommentService commentService;
	private Transformer<Comment, CommentDetailResponse> DETAIL_RESPONSE_TRANSFORMER = new Transformer<Comment, CommentDetailResponse>() {

		@Override
		public CommentDetailResponse transform(Comment input) {
			return new CommentDetailResponse(input);
		}
	};

	/**
	 * The API to create a new comment<br>
	 * <br>
	 * API URL - <b>"/api/comment"</b><br>
	 * Method - <b>"POST"</b>
	 * 
	 * @param commentMergeRequest
	 *            the request body for comment
	 * @return a {@link}commentDetailResponse object.
	 */
	@RequestMapping(method = RequestMethod.POST)
	public CommentDetailResponse createOne(@NotNull @RequestBody CommentMergeRequest commentMergeRequest) {
		return new CommentDetailResponse(commentService.createOne(commentMergeRequest));
	}

	/**
	 * The API for delete a comment<br>
	 * <br>
	 * API URL - <b>"/api/comment/{id}"</b><br>
	 * Method - <b>"DELETE"</b>
	 * 
	 * @param id
	 *            session id
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public String deleteOneById(@PathVariable("id") Long id) {
		return commentService.deleteOneById(id);
	}

	/**
	 * The API for update a comment<br>
	 * <br>
	 * API URL - <b>"/api/comment/{id}"</b><br>
	 * Method - <b>"PUT"</b>
	 * 
	 * @param id
	 *            comment id
	 * @param commentMergeRequest
	 *            request body for comment
	 * @return the response body for a comment
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public CommentDetailResponse updateOne(@PathVariable("id") Long id,
			@NotNull @RequestBody CommentMergeRequest commentMergeRequest) {
		return new CommentDetailResponse(commentService.updateOne(id, commentMergeRequest));
	}

	/**
	 * The API for find a comment by id<br>
	 * <br>
	 * API URL - <b>"/api/comment/{id}"</b><br>
	 * Method - <b>"GET"</b>
	 * 
	 * @param id
	 *            comment id
	 * @return response body for a session
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public CommentDetailResponse findOneById(@PathVariable("id") Long id) {
		return new CommentDetailResponse(commentService.findOneById(id));
	}

	/**
	 * The API for find sessions by several optional parameters<br>
	 * <br>
	 * API URL - <b>"/api/comment"</b><br>
	 * Method - <b>"GET"</b>
	 * 
	 * @param author
	 *            Optional comment author
	 * @param status
	 *            Optional comment session
	 * 
	 * @return a collection of comment response body
	 */
	@RequestMapping(method = RequestMethod.GET)
	public Collection<CommentDetailResponse> findBySeveralConditions(@RequestParam("author") Optional<String> author,
			@RequestParam("session") Optional<Long> session) {
		if (author.isPresent()) {
			if (session.isPresent()) {
				return CollectionUtils.collect(commentService.findByAuthorAndSession(author.get(), session.get()),
						DETAIL_RESPONSE_TRANSFORMER);
			} else {
				return CollectionUtils.collect(commentService.findOneByAuthor(author.get()),
						DETAIL_RESPONSE_TRANSFORMER);
			}
		} else {
			if (session.isPresent()) {
				return CollectionUtils.collect(commentService.findBySession(session.get()),
						DETAIL_RESPONSE_TRANSFORMER);
			} else {
				return CollectionUtils.collect(commentService.findAll(), DETAIL_RESPONSE_TRANSFORMER);
			}
		}
	}
}