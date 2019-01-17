package org.acme.eshop.controller;

import org.acme.eshop.model.system.ApiError;
import org.acme.eshop.model.system.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class CustomizedExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ApiResponse> handleAllExceptions(final Exception ex, final WebRequest request) {
		return new ResponseEntity<>(
			ApiResponse.builder().apiError(getApiError(ex, HttpStatus.INTERNAL_SERVER_ERROR, request)).build(),
									HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	protected ResponseEntity<ApiResponse> handleMissingServletRequestParameter(
		final MissingServletRequestParameterException ex, final WebRequest request) {
		return new ResponseEntity<>(
			ApiResponse.builder().apiError(getApiError(ex, HttpStatus.BAD_REQUEST, request)).build(),
			HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<ApiResponse> andleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
																	  final WebRequest request) {
		return new ResponseEntity<>(
			ApiResponse.builder().apiError(getApiError(ex, HttpStatus.BAD_REQUEST, request)).build(),
			HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ApiResponse> handleMethodArgumentTypeMismatch(final MethodArgumentTypeMismatchException ex,
																		final WebRequest request) {
		return new ResponseEntity<>(
			ApiResponse.builder().apiError(getApiError(ex, HttpStatus.BAD_REQUEST, request)).build(),
			HttpStatus.BAD_REQUEST);
	}

	private ApiError getApiError(final Exception ex, final HttpStatus status, final WebRequest request) {
		String path = request.getDescription(false);
		if (path.indexOf("uri=") == 0) {
			path = StringUtils.replace(path, "uri=", "");
		}
		return new ApiError(status.value(), ex.getMessage(), path);
	}
}

