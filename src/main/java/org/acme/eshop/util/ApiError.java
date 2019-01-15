package org.acme.eshop.util;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.Value;

/**
 * Our custom class to communicate back to the calling side exceptions in a unified way.
 */
@Value
@RequiredArgsConstructor
@ToString
public class ApiError {
	/**
	 * When the error happened.
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private final LocalDateTime timestamp = LocalDateTime.now();
	/**
	 * The {@link HttpStatus} to return for this error.
	 */
	private final Integer status;
	/**
	 * Error's message.
	 */
	private final String message;
	/**
	 * The initial path that triggered the flow whose execution has thrown the exception.
	 */
	private final String path;
}
