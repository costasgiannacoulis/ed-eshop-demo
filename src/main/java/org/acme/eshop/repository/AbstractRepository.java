package org.acme.eshop.repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.acme.eshop.model.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractRepository<T extends BaseEntity> implements BaseRepository<T, Long> {
	protected Logger log = LoggerFactory.getLogger(this.getClass());

	private final Map<Long, T> STORAGE = new LinkedHashMap();
	private final AtomicLong SEQUENCE = new AtomicLong(1);

	public abstract Long getSeed();

	@PostConstruct
	public void initialize() {
		log.debug("Starting {}.", this.getClass().getName());
		getSequence().set(getSeed());
	}

	public AtomicLong getSequence() {
		return SEQUENCE;
	}

	@Override
	public T create(final T entity) {
		final Long key = getSequence().incrementAndGet();

		entity.setId(key);
		STORAGE.put(key, entity);
		log.debug("Created entity {}, now containing {} entities.", entity, STORAGE.size());

		return entity;
	}

	@Override
	public void update(final T entity) {
		STORAGE.put(entity.getId(), entity);
		log.debug("Updated entity {}.", entity);
	}

	@Override
	public void delete(final T entity) {
		STORAGE.remove(entity.getId());
		log.debug("Removed entity {}, now containing {} entities.", entity, STORAGE.size());
	}

	@Override
	public boolean exists(final T entity) {
		final boolean exists = Objects.nonNull(STORAGE.get(entity.getId()));
		log.debug("Entity {} {}.", entity, exists ? "exists" : "does not exist");

		return Objects.nonNull(STORAGE.get(entity.getId()));
	}

	@Override
	public T get(final Long id) {
		final T entity = STORAGE.get(id);
		log.debug("Retrieve entity {}.", entity);

		return entity;
	}

	@Override
	public List<T> findAll() {
		final List<T> entities = STORAGE.values().stream().collect(Collectors.toList());
		log.debug("Retrieve entity {}.", entities);

		return entities;
	}
}
