package org.acme.eshop.controller;

import org.acme.eshop.model.Product;
import org.acme.eshop.service.BaseService;
import org.acme.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController extends AbstractController<Product> {
	@Autowired
	ProductService productService;

	@Override
	public BaseService<Product, Long> getBaseService() {
		return productService;
	}
}
