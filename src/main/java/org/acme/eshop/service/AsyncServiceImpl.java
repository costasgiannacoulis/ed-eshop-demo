package org.acme.eshop.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.acme.eshop.model.Category;
import org.acme.eshop.model.Order;
import org.acme.eshop.model.Product;
import org.acme.eshop.model.User;
import org.acme.eshop.repository.CategoryRepository;
import org.acme.eshop.repository.OrderRepository;
import org.acme.eshop.repository.ProductRepository;
import org.acme.eshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncServiceImpl {
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private UserRepository userRepository;

	@Async("asyncExecutor")
	public CompletableFuture<List<Category>> findAllCategories() {
		return CompletableFuture.completedFuture(categoryRepository.findAll());
	}

	@Async("asyncExecutor")
	public CompletableFuture<List<Order>> findAllOrders() {
		return CompletableFuture.completedFuture(orderRepository.findAll());
	}

	@Async("asyncExecutor")
	public CompletableFuture<List<Product>> findAllProducts() {
		return CompletableFuture.completedFuture(productRepository.findAll());
	}

	@Async("asyncExecutor")
	public CompletableFuture<List<User>> findAllUsers() {
		return CompletableFuture.completedFuture(userRepository.findAll());
	}
}
