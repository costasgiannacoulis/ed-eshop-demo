package org.acme.eshop.controller;

import org.acme.eshop.model.Product;
import org.acme.eshop.service.BaseService;
import org.acme.eshop.service.ProductService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController extends AbstractController<Product> {
	private final ProductService productService;

	@Override
	public BaseService<Product, Long> getBaseService() {
		return productService;
	}
}
