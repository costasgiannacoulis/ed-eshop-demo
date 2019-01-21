package org.acme.eshop.service;

import java.util.List;

import org.acme.eshop.model.Category;

public interface CategoryService extends BaseService<Category, Long> {
	/* Adding this method to demonstrate static cache declaration. */
	List<Category> findAllCacheableCategories();

	/* Adding this method to demonstrate static cache declaration. */
	List<Category> findTopCacheableCategories();
}
