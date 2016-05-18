package com.sap.ssm.service;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sap.ssm.persistence.model.Comment;
import com.sap.ssm.persistence.repository.CommentRepository;
import com.sap.ssm.web.model.request.CommentMergeRequest;

/**
 * 
 * The {@link}SessionService includes operations about the {@link}Session object
 * 
 * @author Yuan Zhang
 */
@Service
@Transactional
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;

	/**
	 * Create a comment
	 * 
	 * @param commentMergeRequest
	 *            the request include all comment information
	 * @return A {@link}Comment object
	 */
	public Comment createOne(@NotNull CommentMergeRequest commentMergeRequest) {
		Comment comment = new Comment();
		mergeScalarProperties(commentMergeRequest, comment);
		return commentRepository.saveAndFlush(comment);
	}

	/**
	 * Find one comment by id
	 * 
	 * @param id
	 *            {@link}Comment's id
	 * @return A {@link}Comment object
	 */
	public Comment findOneById(@NotNull Long id) {
		return commentRepository.findOne(id);
	}

	/**
	 * Find one comment by author
	 * 
	 * @param author
	 *            {@link}Comment's author
	 * @return A {@link}comment object
	 */
	public List<Comment> findOneByAuthor(@NotNull String author) {
		return commentRepository.findByAuthor(author);
	}

	/**
	 * Update one comment by id
	 * 
	 * @param id
	 *            {@link}comment's author and session belonged to
	 * @return A {@link}Comment object
	 */
	public Comment updateOne(@NotNull Long id, @NotNull CommentMergeRequest commentMergeRequest) {
		Comment comment = commentRepository.findOne(id);
		mergeScalarProperties(commentMergeRequest, comment);
		return commentRepository.saveAndFlush(comment);
	}

	/**
	 * Delete one comment by id
	 * 
	 * @param id
	 *            {@link}Comment's id
	 * @return state message
	 */
	public String deleteOneById(@NotNull Long id) {
		try {
			commentRepository.delete(id);
			return "Delete session successfully";
		} catch (IllegalArgumentException e) {
			return e.getMessage();
		}
	}

	/**
	 * Find all exist comments
	 * 
	 * @return List the list of all comments
	 */
	public List<Comment> findAll() {
		return commentRepository.findAll();
	}

	/**
	 * Find one session by owner
	 * 
	 * @param id
	 *            {@link}Session's owner
	 * @return A {@link}Session object
	 */
	public List<Comment> findBySession(@NotNull Long session) {
		return (commentRepository).findBySession(session);
	}

	/**
	 * Find one comment by author and session
	 * 
	 * @param author
	 *            {@link}Comment's author and session
	 * @return A {@link}Comment object
	 */
	public List<Comment> findByAuthorAndSession(@NotNull String author, @NotNull Long session) {
		return commentRepository.findByAuthorAndSession(author, session);
	}
	/**
	 * @param date
	 * @param id
	 * @param session
	 * @param content
	 * @param author
	 *            the date,id,session,content,author to set
	 */

	private void mergeScalarProperties(CommentMergeRequest commentMergeRequest, Comment comment) {
		comment.setDate(commentMergeRequest.getDate());
		comment.setId(commentMergeRequest.getId());
		comment.setSession(commentMergeRequest.getSession());
		comment.setContent(commentMergeRequest.getContent());
		comment.setAuthor(commentMergeRequest.getAuthor());
	}
}
