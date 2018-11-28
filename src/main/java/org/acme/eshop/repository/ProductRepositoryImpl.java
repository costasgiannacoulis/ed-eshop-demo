package org.acme.eshop.repository;

import java.util.concurrent.atomic.AtomicLong;

import org.acme.eshop.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductRepositoryImpl extends AbstractRepository<Product> implements ProductRepository {
	private final AtomicLong SEQUENCE = new AtomicLong(1);

	@Override
	public AtomicLong getSequence() {
		return SEQUENCE;
	}
}
