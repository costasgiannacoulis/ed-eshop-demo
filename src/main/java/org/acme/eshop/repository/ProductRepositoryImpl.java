package org.acme.eshop.repository;

import org.acme.eshop.model.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProductRepositoryImpl extends AbstractRepository<Product> implements ProductRepository {
	@Value("${repository.product.sequence.start}")
	private Long seed;

	@Override
	public Long getSeed() {
		return seed;
	}
}
