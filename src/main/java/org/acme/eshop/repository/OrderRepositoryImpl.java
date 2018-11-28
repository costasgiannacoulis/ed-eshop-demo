package org.acme.eshop.repository;

import java.util.concurrent.atomic.AtomicLong;

import org.acme.eshop.model.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderRepositoryImpl extends AbstractRepository<Order> implements OrderRepository {
	private final AtomicLong SEQUENCE = new AtomicLong(1);

	@Override
	public AtomicLong getSequence() {
		return SEQUENCE;
	}
}
