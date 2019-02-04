package org.acme.eshop.service.report;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.acme.eshop.model.Order;
import org.acme.eshop.model.system.KeyValue;
import org.acme.eshop.service.OrderService;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@DependsOn("orderService")
@RequiredArgsConstructor
@Slf4j
public class OrderReportServiceImpl implements OrderReportService {
	private final OrderService orderService;

	@Override
	public List<KeyValue<Order, BigDecimal>> getOrdersSummary() {
		final List<Order> orders = orderService.findAll();
		log.trace("Returning order total cost for {} orders.", orders.size());

		return orders.stream().map(o -> {
			final BigDecimal totalCost = o.getOrderItems().parallelStream().map(
				oi -> oi.getPrice().multiply(BigDecimal.valueOf(oi.getQuantity()))).reduce(BigDecimal.ZERO,
																						   BigDecimal::add);
			return new KeyValue<>(o, totalCost);
		}).collect(Collectors.toList());
	}
}
