package com.sap.ssm.persistence.repository;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sap.ssm.persistence.model.Comment;

/**
 * @author Yuan Zhang
 */
@Repository
@Transactional
public interface CommentRepository extends JpaRepository<Comment, Long> {

	/**
	 * Find one comment by author
	 * 
	 * @param author
	 *            Comment's author
	 * @return List<Comment> a list of Comments
	 */
	List<Comment> findByAuthor(String author);

	/**
	 * Find one comment by session
	 * 
	 * @param session
	 *            comment's session
	 * @return List<Session> a list of comments
	 */
	List<Comment> findBySession(Long session);

	/**
	 * Find one session by author and session
	 * 
	 * @param author
	 * @param
	 * @return A {@link}Comment object
	 */
	List<Comment> findByAuthorAndSession(@NotNull String author, @NotNull Long session);

}
