package org.acme.eshop.controller;

import org.acme.eshop.model.Order;
import org.acme.eshop.service.BaseService;
import org.acme.eshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
@RequestMapping("/orders")
public class OrderController extends AbstractController<Order> {
	@Autowired
	OrderService orderService;

	@Override
	public BaseService<Order, Long> getBaseService() {
		return orderService;
	}

	@GetMapping(headers = "X-API-VERSION=1")
	public MappingJacksonValue findAllV1() {
		return getMappingJacksonValue(getBaseService().findAll());
		//return new ResponseEntity<>(ApiResponse.builder().data(mappingJacksonValue.getValue()).build(), HttpStatus
		// .OK);
	}

	@GetMapping(headers = "X-API-VERSION=2")
	public MappingJacksonValue findAllV2() {
		return getMappingJacksonValue(getBaseService().findAll(), "id", "user", "orderDate");
		//return new ResponseEntity<>(ApiResponse.builder().data(mappingJacksonValue.getValue()).build(), HttpStatus
		// .OK);
	}

	private MappingJacksonValue getMappingJacksonValue(final Object orders, final String... attributes) {
		SimpleBeanPropertyFilter filter = null;
		if (attributes.length > 0) {
			filter = SimpleBeanPropertyFilter.filterOutAllExcept(attributes);
		} else {
			filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "user", "orderDate", "orderItems");
		}
		final FilterProvider filters = new SimpleFilterProvider().addFilter("dynaFilter", filter);
		final MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(orders);
		mappingJacksonValue.setFilters(filters);
		return mappingJacksonValue;
	}
}
