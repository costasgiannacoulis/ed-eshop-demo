package org.acme.eshop.bootstrap;

import org.acme.eshop.model.Order;
import org.acme.eshop.model.Product;
import org.acme.eshop.model.User;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Component
@Profile("revisit")
@Slf4j
public class RestClientDemonstration implements ApplicationRunner {
	@Override
	public void run(final ApplicationArguments args) {
		final RestTemplate restTemplate = new RestTemplate();

		final ResponseEntity<User> userRetrieved = restTemplate.getForEntity("http://localhost:8080/users/1",
																			 User.class);
		log.debug("Status returned {} for user {}.", userRetrieved.getStatusCode(), userRetrieved.getBody());

		final ResponseEntity<Product[]> productsRetrieved = restTemplate.getForEntity("http://localhost:8080/products",
																					  Product[].class);
		log.debug("Status returned {} for {} products.", productsRetrieved.getStatusCode(),
				  productsRetrieved.getBody().length);

		final Order[] ordersRetrieved = restTemplate.getForObject("http://localhost:8080/orders", Order[].class);
		log.debug("Returned {} orders.", ordersRetrieved.length);
	}
}
