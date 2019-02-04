package org.acme.eshop.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.acme.eshop.model.Order;
import org.acme.eshop.model.system.ApiResponse;
import org.acme.eshop.model.system.KeyValue;
import org.acme.eshop.service.BaseService;
import org.acme.eshop.service.OrderService;
import org.acme.eshop.service.report.OrderReportService;
import org.acme.eshop.service.report.OrderSpreadsheetReportBuilder;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static org.acme.eshop.util.SpreadsheetStructureBuilder.XLSX_MEDIA_TYPE;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController extends AbstractController<Order> {
	private final OrderService orderService;
	private final OrderReportService orderReportService;
	private final OrderSpreadsheetReportBuilder reportBuilder;

	@Override
	public BaseService<Order, Long> getBaseService() {
		return orderService;
	}

	@GetMapping(headers = "Action=report-getOrdersSummary")
	public ResponseEntity<Resource> reportGetOrdersSummary(
		@RequestHeader(value = "Content-Type", required = false, defaultValue = MediaType.APPLICATION_JSON_VALUE) String contentType)
		throws IOException {

		final HttpHeaders headers = new HttpHeaders();
		headers.addAll(getNoCacheHeaders());
		headers.addAll(getDownloadHeaders("report-getOrdersSummary.xlsx"));

		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		final List<KeyValue<Order, BigDecimal>> ordersSummary = orderReportService.getOrdersSummary();
		if (XLSX_MEDIA_TYPE.toString().equalsIgnoreCase(contentType)) {
			log.trace("Generating XLSX report for {} orders.", ordersSummary.size());
			reportBuilder.getOrdersSummary(ordersSummary).write(outputStream);
		} else {
			log.trace("Returning JSON representation for {} orders.", ordersSummary.size());
			final ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(outputStream,
							  ApiResponse.<List<KeyValue<Order, BigDecimal>>>builder().data(ordersSummary).build());
			contentType = MediaType.APPLICATION_JSON_VALUE;
		}

		return ResponseEntity.ok().contentType(MediaType.valueOf(contentType)).headers(headers).contentLength(
			outputStream.size()).body(new InputStreamResource(new ByteArrayInputStream(outputStream.toByteArray())));
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
