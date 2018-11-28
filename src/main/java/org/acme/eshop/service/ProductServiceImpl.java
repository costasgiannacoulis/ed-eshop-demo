package org.acme.eshop.service;

import org.acme.eshop.model.Product;
import org.acme.eshop.repository.BaseRepository;
import org.acme.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends AbstractService<Product> implements ProductService {
	@Autowired
	private ProductRepository productRepository;

	@Override
	public BaseRepository<Product, Long> getRepository() {
		return productRepository;
	}
}
