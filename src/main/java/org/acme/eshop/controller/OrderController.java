package org.acme.eshop.controller;

import org.acme.eshop.model.Order;
import org.acme.eshop.service.BaseService;
import org.acme.eshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController extends AbstractController<Order> {
	@Autowired
	OrderService orderService;

	@Override
	public BaseService<Order, Long> getBaseService() {
		return orderService;
	}
}
