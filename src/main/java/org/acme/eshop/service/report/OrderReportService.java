package org.acme.eshop.service.report;

import java.math.BigDecimal;
import java.util.List;

import org.acme.eshop.model.Order;
import org.acme.eshop.model.system.KeyValue;

public interface OrderReportService {
	List<KeyValue<Order, BigDecimal>> getOrdersSummary();
}
