package org.acme.eshop.model.system;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ApiResponse<T> implements Serializable {
	private static final long serialVersionUID = -963324132797950700L;
	private final String transactionId = UUID.randomUUID().toString().toUpperCase();
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss.SSS")
	private final LocalDateTime createdAt = LocalDateTime.now();
	private final T data;
	private final ApiError apiError;
}
