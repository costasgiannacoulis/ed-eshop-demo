package org.acme.eshop.service;

import org.acme.eshop.model.Order;
import org.acme.eshop.repository.BaseRepository;
import org.acme.eshop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends AbstractService<Order> implements OrderService {
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public BaseRepository<Order, Long> getRepository() {
		return orderRepository;
	}
}
