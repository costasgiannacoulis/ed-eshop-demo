package org.acme.eshop.service;

import org.acme.eshop.model.Order;
import org.acme.eshop.repository.OrderRepository;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service("orderService")
@DependsOn("userService")
@RequiredArgsConstructor
public class OrderServiceImpl extends AbstractService<Order> implements OrderService {
	private final OrderRepository orderRepository;

	@Override
	public JpaRepository<Order, Long> getRepository() {
		return orderRepository;
	}
}
