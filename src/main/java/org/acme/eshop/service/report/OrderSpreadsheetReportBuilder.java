package org.acme.eshop.service.report;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.acme.eshop.model.Order;
import org.acme.eshop.model.OrderItem;
import org.acme.eshop.model.system.KeyValue;
import org.acme.eshop.util.SpreadsheetStructureBuilder;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import static org.acme.eshop.util.SpreadsheetStructureBuilder.DATEFORMAT;

@Component
public class OrderSpreadsheetReportBuilder {
	public Workbook getOrdersSummary(final List<KeyValue<Order, BigDecimal>> ordersSummary) {
		final SpreadsheetStructureBuilder report = new SpreadsheetStructureBuilder("Orders Summary");

		report.registerCellStyles(report.numberCellStyle, report.defaultCellStyle, report.defaultCellStyle,
								  report.dateCellStyle, report.numberCellStyle, report.currencyCellStyle);

		report.createHeaderRow(0, "Order ID", "Fullname", "Email Address", "Order Date", "Order Items",
							   "Total" + " Cost");

		final AtomicInteger rowNumberToWriteTo = new AtomicInteger(0);
		ordersSummary.stream().forEach(i -> report
			.createRow(rowNumberToWriteTo.incrementAndGet(), i.getKey().getId().toString(),
					   i.getKey().getUser().getFirstname() + " " + i.getKey().getUser().getLastname(),
					   i.getKey().getUser().getEmail(), DATEFORMAT.format(i.getKey().getOrderDate()),
					   String.valueOf(i.getKey().getOrderItems().parallelStream().map(OrderItem::getQuantity)
									   .mapToInt(Integer::intValue).sum()),
					   i.getValue().setScale(2, BigDecimal.ROUND_HALF_UP).toString()));

		return report.workbook;
	}
}
