package org.acme.eshop.controller;

import org.acme.eshop.model.Category;
import org.acme.eshop.service.BaseService;
import org.acme.eshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController extends AbstractController<Category> {
	@Autowired
	CategoryService categoryService;

	@Override
	public BaseService<Category, Long> getBaseService() {
		return categoryService;
	}
}
