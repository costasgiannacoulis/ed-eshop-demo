package org.acme.eshop.service;

import org.acme.eshop.model.Order;
import org.acme.eshop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service("orderService")
@DependsOn("userService")
public class OrderServiceImpl extends AbstractService<Order> implements OrderService {
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public JpaRepository<Order, Long> getRepository() {
		return orderRepository;
	}
}
