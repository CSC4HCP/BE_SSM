package com.sap.ssm.service;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sap.ssm.persistence.model.Category;
import com.sap.ssm.persistence.repository.CategoryRepository;
import com.sap.ssm.web.model.request.CategoryMergeRequest;

/**
 * 
 * The {@link}CategoryService includes operations about the {@link}Category
 * object
 * 
 * @author Yuan Zhang
 */
@Service
@Transactional
public class CategoryService {

	@Autowired
	private CategoryRepository CategoryRepository;

	/**
	 * Create a Category
	 * 
	 * @param CategoryMergeRequest
	 *            the request include all Category information
	 * @return A {@link}Category object
	 */
	public Category createOne(@NotNull CategoryMergeRequest CategoryMergeRequest) {
		Category Category = new Category();
		mergeScalarProperties(CategoryMergeRequest, Category);
		return CategoryRepository.saveAndFlush(Category);
	}

	/**
	 * Find one Category by id
	 * 
	 * @param id
	 *            {@link}Category's id
	 * @return A {@link}Category object
	 */
	public Category findOneById(@NotNull Long id) {
		return CategoryRepository.findOne(id);
	}

	/**
	 * Update one Category by id
	 * 
	 * @param id
	 *            {@link}Category's author and Category belonged to
	 * @return A {@link}Category object
	 */
	public Category updateOne(@NotNull Long id, @NotNull CategoryMergeRequest CategoryMergeRequest) {
		Category Category = CategoryRepository.findOne(id);
		mergeScalarProperties(CategoryMergeRequest, Category);
		return CategoryRepository.saveAndFlush(Category);
	}

	/**
	 * Delete one Category by id
	 * 
	 * @param id
	 *            {@link}Category's id
	 * @return state message
	 */
	public String deleteOneById(@NotNull Long id) {
		try {
			CategoryRepository.delete(id);
			return "Delete Category successfully";
		} catch (IllegalArgumentException e) {
			return e.getMessage();
		}
	}

	/**
	 * Find all exist Categories
	 * 
	 * @return List the list of all Categories
	 */
	public List<Category> findAll() {
		return CategoryRepository.findAll();
	}

	private void mergeScalarProperties(CategoryMergeRequest CategoryMergeRequest, Category Category) {

		Category.setId(CategoryMergeRequest.getId());
		Category.setName(CategoryMergeRequest.getName());

	}
}
