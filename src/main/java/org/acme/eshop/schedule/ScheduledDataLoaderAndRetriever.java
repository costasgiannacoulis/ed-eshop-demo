package org.acme.eshop.schedule;

import org.acme.eshop.service.CategoryService;
import org.acme.eshop.service.OrderService;
import org.acme.eshop.service.ProductService;
import org.acme.eshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

import static org.acme.eshop.util.ContentGenerator.createCategory;
import static org.acme.eshop.util.ContentGenerator.createOrder;
import static org.acme.eshop.util.ContentGenerator.createProduct;
import static org.acme.eshop.util.ContentGenerator.createUser;

@Component
@Profile("schedule")
@Slf4j
public class ScheduledDataLoaderAndRetriever {
	@Autowired
	CategoryService categoryService;
	@Autowired
	OrderService orderService;
	@Autowired
	ProductService productService;
	@Autowired
	UserService userService;

	@Scheduled(cron = "0 */1 * * * *")
	public void createUsers() {
		userService.create(createUser());
	}

	@Scheduled(cron = "0 */5 * * * *")
	public void createCategories() {
		categoryService.create(createCategory());
	}

	@Scheduled(cron = "0 */1 * * * *")
	public void createProducts() {
		productService.create(createProduct());
	}

	@Scheduled(cron = "*/20 * * * * *")
	public void createrOrders() {
		orderService.create(createOrder(userService.findAll(), productService.findAll()));
	}

	@Scheduled(cron = "*/30 * * * * *")
	public void getContentStats() {
		log.debug("Users: {}, Categories: {}, Products: {}, Orders: {}.", userService.findAll().size(),
				  categoryService.findAll().size(), productService.findAll().size(), orderService.findAll().size());
	}

	@Scheduled(cron = "*/10 * * * * *")
	public void testAsync() {
		categoryService.checkAsync();
		productService.checkAsync();
		orderService.checkAsync();
		userService.checkAsync();
	}
}
