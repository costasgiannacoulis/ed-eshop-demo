package org.acme.eshop.controller;

import java.util.List;

import javax.validation.Valid;

import org.acme.eshop.model.BaseEntity;
import org.acme.eshop.model.system.ApiResponse;
import org.acme.eshop.service.BaseService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public abstract class AbstractController<T extends BaseEntity> {
	protected abstract BaseService<T, Long> getBaseService();

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> get(@PathVariable("id") final Long id) {
		return new ResponseEntity<>(ApiResponse.<T>builder().data(getBaseService().get(id)).build(), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<ApiResponse> findAll() {
		return new ResponseEntity<>(ApiResponse.<List<T>>builder().data(getBaseService().findAll()).build(),
									HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ApiResponse> create(@Valid @RequestBody final T entity) {
		return new ResponseEntity<>(ApiResponse.<T>builder().data(getBaseService().create(entity)).build(),
									HttpStatus.CREATED);
	}

	@PutMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@Valid @RequestBody final T entity) {
		if (getBaseService().exists(entity)) {
			getBaseService().update(entity);
		}
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") final Long id) {
		getBaseService().deleteById(id);
	}

	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@Valid @RequestBody final T entity) {
		if (getBaseService().exists(entity)) {
			getBaseService().delete(entity);
		}
	}

	@PatchMapping("/{id}")
	public void patch(@Valid @RequestBody final T entity) {
		//TODO
	}

	protected HttpHeaders getNoCacheHeaders() {
		final HttpHeaders headers = new HttpHeaders();
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");
		return headers;
	}

	protected HttpHeaders getDownloadHeaders(final String filename) {
		final HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=" + filename);
		return headers;
	}
}
