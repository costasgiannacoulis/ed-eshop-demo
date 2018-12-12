package org.acme.eshop.repository;

import org.acme.eshop.model.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OrderRepositoryImpl extends AbstractRepository<Order> implements OrderRepository {
	@Value("${repository.order.sequence.start}")
	private Long seed;

	@Override
	public Long getSeed() {
		return seed;
	}
}
