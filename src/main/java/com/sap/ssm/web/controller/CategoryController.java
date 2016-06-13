package com.sap.ssm.web.controller;

import java.util.Collection;
import javax.validation.constraints.NotNull;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sap.ssm.persistence.model.Category;
import com.sap.ssm.service.CategoryService;
import com.sap.ssm.web.model.request.CategoryMergeRequest;
import com.sap.ssm.web.model.response.CategoryDetailResponse;

/**
 * The {@link}RestController for session entity.
 * 
 * @author Yuan Zhang
 *
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService CategoryService;
	private Transformer<Category, CategoryDetailResponse> DETAIL_RESPONSE_TRANSFORMER = new Transformer<Category, CategoryDetailResponse>() {

		@Override
		public CategoryDetailResponse transform(Category input) {
			return new CategoryDetailResponse(input);
		}
	};

	/**
	 * The API to create a new Category<br>
	 * <br>
	 * API URL - <b>"/api/category"</b><br>
	 * Method - <b>"POST"</b>
	 * 
	 * @param CategoryMergeRequest
	 *            the request body for Category
	 * @return a {@link}CategoryDetailResponse object.
	 */
	@RequestMapping(method = RequestMethod.POST)
	public CategoryDetailResponse createOne(@NotNull @RequestBody CategoryMergeRequest CategoryMergeRequest) {
		return new CategoryDetailResponse(CategoryService.createOne(CategoryMergeRequest));
	}

	/**
	 * The API for delete a category<br>
	 * <br>
	 * API URL - <b>"/api/Category/{id}"</b><br>
	 * Method - <b>"DELETE"</b>
	 * 
	 * @param id
	 *            session id
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public String deleteOneById(@PathVariable("id") Long id) {
		return CategoryService.deleteOneById(id);
	}

	/**
	 * The API for update a Category<br>
	 * <br>
	 * API URL - <b>"/api/category/{id}"</b><br>
	 * Method - <b>"PUT"</b>
	 * 
	 * @param id
	 *            Category id
	 * @param CategoryMergeRequest
	 *            request body for Category
	 * @return the response body for a Category
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public CategoryDetailResponse updateOne(@PathVariable("id") Long id,
			@NotNull @RequestBody CategoryMergeRequest CategoryMergeRequest) {
		return new CategoryDetailResponse(CategoryService.updateOne(id, CategoryMergeRequest));
	}

	/**
	 * The API for find a Category by id<br>
	 * <br>
	 * API URL - <b>"/api/category/{id}"</b><br>
	 * Method - <b>"GET"</b>
	 * 
	 * @param id
	 *            Category id
	 * @return response body for a session
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public CategoryDetailResponse findOneById(@PathVariable("id") Long id) {
		return new CategoryDetailResponse(CategoryService.findOneById(id));
	}

	/**
	 * The API for find all categories<br>
	 * <br>
	 * API URL - <b>"/api/category"</b><br>
	 * Method - <b>"GET"</b>
	 * 
	 * @return a collection of Category response body
	 */
	@RequestMapping(method = RequestMethod.GET)
	public Collection<CategoryDetailResponse> findAll() {

		return CollectionUtils.collect(CategoryService.findAll(), DETAIL_RESPONSE_TRANSFORMER);
	}
}