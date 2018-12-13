package org.acme.eshop.util;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.acme.eshop.model.Category;
import org.acme.eshop.model.Order;
import org.acme.eshop.model.OrderItem;
import org.acme.eshop.model.Product;
import org.acme.eshop.model.User;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;

public class ContentGenerator {
	private static final Lorem LOREM = LoremIpsum.getInstance();

	public static Category createCategory() {
		return Category.builder().description(LOREM.getWords(1, 3)).build();
	}

	public static Product createProduct() {
		return Product.builder().description(LOREM.getWords(2, 4)).price(getRandomPrice()).build();
	}

	public static User createUser() {
		final String firstName = LOREM.getFirstName();
		final String lastName = LOREM.getLastName();
		return User.builder().firstname(firstName).lastname(lastName).email(String.format("%s.%s@example.com",
																						  firstName, lastName)).build();
	}

	public static Order createOrder(final List<User> users, final List<Product> products) {
		final List<OrderItem> orderItems = new ArrayList<>();
		for (int i = 0; i < ThreadLocalRandom.current().nextInt(1, 6); i++) {
			final Product selectedProduct = getRandomProduct(products);

			final OrderItem newOrderItem = OrderItem.builder().description(selectedProduct.getDescription()).price(
				selectedProduct.getPrice())
													.quantity(ThreadLocalRandom.current().nextInt(1, 3)).build();
			orderItems.add(newOrderItem);
		}

		return Order.builder().user(getRandomUser(users)).orderDate(getRandomDate()).orderItems(orderItems).build();
	}

	private static BigDecimal getRandomPrice() {
		final double leftLimit = 1D;
		final double rightLimit = 100D;
		final double generated = leftLimit + ThreadLocalRandom.current().nextDouble() * (rightLimit - leftLimit + 1);

		return BigDecimal.valueOf(generated).setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}

	private static Product getRandomProduct(final List<Product> products) {
		return products.get(ThreadLocalRandom.current().nextInt(products.size()));
	}

	private static User getRandomUser(final List<User> users) {
		return users.get(ThreadLocalRandom.current().nextInt(users.size()));
	}

	private static Date getRandomDate() {
		final ZonedDateTime fromZdt = LocalDateTime.of(2018, 1, 1, 0, 0, 0)
												   .atZone(ZoneId.of("Europe/Athens"));
		final Long fromInMillis = fromZdt.toInstant().toEpochMilli();
		final ZonedDateTime toZdt = LocalDateTime.of(2018, 11, 30, 23, 59, 59)
												 .atZone(ZoneId.of("Europe/Athens"));
		final Long toInMillis = toZdt.toInstant().toEpochMilli();

		return new Date(ThreadLocalRandom.current().nextLong(fromInMillis, toInMillis));
	}
}
