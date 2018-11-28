package org.acme.eshop.service;

import java.util.List;

public interface BaseService<T, N> {
	T create(final T entity);

	void createAll(final T... entities);

	void update(T entity);

	void delete(T entity);

	boolean exists(T entity);

	T get(N id);

	List<T> findAll();
}
