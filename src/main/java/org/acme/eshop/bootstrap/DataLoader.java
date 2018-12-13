package org.acme.eshop.bootstrap;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.acme.eshop.model.Category;
import org.acme.eshop.model.Order;
import org.acme.eshop.model.Product;
import org.acme.eshop.model.User;
import org.acme.eshop.service.AsyncServiceImpl;
import org.acme.eshop.service.CategoryService;
import org.acme.eshop.service.OrderService;
import org.acme.eshop.service.ProductService;
import org.acme.eshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

import static org.acme.eshop.util.ContentGenerator.createCategory;
import static org.acme.eshop.util.ContentGenerator.createOrder;
import static org.acme.eshop.util.ContentGenerator.createProduct;
import static org.acme.eshop.util.ContentGenerator.createUser;

@Component
@Profile("bootstrap")
@Slf4j
public class DataLoader implements ApplicationRunner {
	@Autowired
	CategoryService categoryService;
	@Autowired
	OrderService orderService;
	@Autowired
	ProductService productService;
	@Autowired
	UserService userService;
	@Autowired
	AsyncServiceImpl asyncService;

	@Override
	public void run(final ApplicationArguments args) throws ExecutionException, InterruptedException {
		log.debug("Create sample users");
		for (int i = 0; i < 5; i++) {
			userService.create(createUser());
		}
		log.debug("Create sample categories");
		for (int i = 0; i < 5; i++) {
			categoryService.create(createCategory());
		}
		log.debug("Create sample products");
		for (int i = 0; i < 10; i++) {
			productService.create(createProduct());
		}

		final List<User> registeredUsers = userService.findAll();
		final List<Product> products = productService.findAll();
		log.debug("Create sample orders");
		for (int i = 0; i < 10; i++) {
			orderService.create(createOrder(registeredUsers, products));
		}

		final CompletableFuture<List<User>> usersAsync = userService.findAllAsync();
		final CompletableFuture<List<Category>> categoriesAsync = categoryService.findAllAsync();
		final CompletableFuture<List<Product>> productsAsync = productService.findAllAsync();
		final CompletableFuture<List<Order>> ordersAsync = orderService.findAllAsync();

		log.debug("Async mode. Users: {}, Categories: {}, Products: {}, Orders: {}.",
				  usersAsync.get().size(), categoriesAsync.get().size(), productsAsync.get().size(),
				  ordersAsync.get().size());
	}
}
