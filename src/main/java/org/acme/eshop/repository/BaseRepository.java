package org.acme.eshop.repository;

import java.util.List;

import org.acme.eshop.model.BaseEntity;

public interface BaseRepository<T extends BaseEntity, N> {
	T create(final T entity);

	void update(T entity);

	void delete(T entity);

	boolean exists(T entity);

	T get(N id);

	List<T> findAll();
}
