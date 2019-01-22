package org.acme.eshop.service;

import org.acme.eshop.model.Product;
import org.acme.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service("productService")
@DependsOn(value = "categoryService")
@Slf4j
public class ProductServiceImpl extends AbstractService<Product> implements ProductService {
	@Autowired
	private ProductRepository productRepository;

	@Override
	public JpaRepository<Product, Long> getRepository() {
		return productRepository;
	}

	@Override
	@CachePut(key = "#product.getId()", cacheResolver = "dynamicCacheResolver", condition = "#product.price>50")
	public Product create(final Product product) {
		log.trace("Creating {}.", product);
		return getRepository().save(product);
	}
}
