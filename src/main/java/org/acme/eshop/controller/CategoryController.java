package org.acme.eshop.controller;

import java.util.List;

import org.acme.eshop.model.Category;
import org.acme.eshop.model.system.ApiResponse;
import org.acme.eshop.service.BaseService;
import org.acme.eshop.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController extends AbstractController<Category> {
	private final CategoryService categoryService;

	@Override
	public BaseService<Category, Long> getBaseService() {
		return categoryService;
	}

	@GetMapping(headers = {"Action=allCacheableCategories"})
	public ResponseEntity<ApiResponse> findAllCategories() {
		return new ResponseEntity<>(
			ApiResponse.<List<Category>>builder().data(categoryService.findAllCacheableCategories()).build(),
			HttpStatus.OK);
	}

	@GetMapping(headers = {"Action=topCacheableCategories"})
	public ResponseEntity<ApiResponse> findTopCategories() {
		return new ResponseEntity<>(
			ApiResponse.<List<Category>>builder().data(categoryService.findTopCacheableCategories()).build(),
			HttpStatus.OK);
	}
}
