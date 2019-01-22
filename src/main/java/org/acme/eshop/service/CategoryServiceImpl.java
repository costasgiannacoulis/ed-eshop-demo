package org.acme.eshop.service;

import java.util.List;
import java.util.stream.Collectors;

import org.acme.eshop.model.Category;
import org.acme.eshop.repository.CategoryRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service("categoryService")
@RequiredArgsConstructor
public class CategoryServiceImpl extends AbstractService<Category> implements CategoryService {
	private final CategoryRepository categoryRepository;

	@Override
	public JpaRepository<Category, Long> getRepository() {
		return categoryRepository;
	}

	@Override
	@Cacheable(value = "Categories-Static-Cache", keyGenerator = "CustomCacheKeyGenerator")
	public List<Category> findAllCacheableCategories() {
		log.trace("Retrieving all categories.");
		simulateSlowService();
		return getRepository().findAll();
	}

	@Override
	@Cacheable(value = "Categories-Static-Cache", keyGenerator = "CustomCacheKeyGenerator")
	public List<Category> findTopCacheableCategories() {
		log.trace("Retrieving top categories.");
		simulateSlowService();
		return getRepository().findAll().stream().limit(3).collect(Collectors.toList());
	}
}
