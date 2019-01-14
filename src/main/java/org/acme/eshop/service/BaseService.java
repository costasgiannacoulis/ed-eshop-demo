package org.acme.eshop.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface BaseService<T, ID> {
	T create(final T entity);

	void createAll(final T... entities);

	void update(T entity);

	void deleteById(ID id);

	void delete(T entity);

	boolean exists(T entity);

	T get(ID id);

	List<T> findAll();

	CompletableFuture<List<T>> findAllAsync();

	void checkAsync();
}
