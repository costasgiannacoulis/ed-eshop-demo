package org.acme.eshop.repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.acme.eshop.model.BaseEntity;

public abstract class AbstractRepository<T extends BaseEntity> implements BaseRepository<T, Long> {
	private final Map<Long, T> STORAGE = new LinkedHashMap();

	public abstract AtomicLong getSequence();

	@Override
	public T create(final T entity) {
		final Long key = getSequence().getAndIncrement();
		entity.setId(key);
		STORAGE.put(key, entity);
		return entity;
	}

	@Override
	public void update(final T entity) {
		STORAGE.put(entity.getId(), entity);
	}

	@Override
	public void delete(final T entity) {
		STORAGE.remove(entity.getId());
	}

	@Override
	public boolean exists(final T entity) {
		return Objects.nonNull(STORAGE.get(entity.getId()));
	}

	@Override
	public T get(final Long id) {
		return STORAGE.get(id);
	}

	@Override
	public List<T> findAll() {
		return STORAGE.values().stream().collect(Collectors.toList());
	}
}
