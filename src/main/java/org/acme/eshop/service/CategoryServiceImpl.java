package org.acme.eshop.service;

import org.acme.eshop.model.Category;
import org.acme.eshop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service("categoryService")
public class CategoryServiceImpl extends AbstractService<Category> implements CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public JpaRepository<Category, Long> getRepository() {
		return categoryRepository;
	}
}
