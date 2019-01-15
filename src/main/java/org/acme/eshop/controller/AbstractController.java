package org.acme.eshop.controller;

import java.util.List;

import javax.validation.Valid;

import org.acme.eshop.model.BaseEntity;
import org.acme.eshop.service.BaseService;
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
	public T get(@PathVariable("id") final Long id) {
		return getBaseService().get(id);
	}

	@GetMapping
	public List<T> findAll() {
		return getBaseService().findAll();
	}

	@PostMapping
	public ResponseEntity<T> create(@Valid @RequestBody final T entity) {
		final T savedEntity = getBaseService().create(entity);
		return new ResponseEntity<>(savedEntity, HttpStatus.CREATED);
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

	/**
	 * Patches the entity.
	 *
	 * @param entity the entity to patch.
	 */
	@PatchMapping("/{id}")
	public void patch(@Valid @RequestBody final T entity) {
		//TODO
	}
}
