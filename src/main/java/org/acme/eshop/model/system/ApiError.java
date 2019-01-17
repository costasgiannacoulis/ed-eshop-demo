package org.acme.eshop.model.system;

import lombok.ToString;
import lombok.Value;

@Value
@ToString
public class ApiError {
	private final Integer status;
	private final String message;
	private final String path;
}
