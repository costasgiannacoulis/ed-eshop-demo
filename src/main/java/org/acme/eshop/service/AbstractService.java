package org.acme.eshop.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.annotation.PostConstruct;

import org.acme.eshop.model.BaseEntity;
import org.acme.eshop.repository.BaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;

public abstract class AbstractService<T extends BaseEntity> implements BaseService<T, Long> {
	protected Logger log = LoggerFactory.getLogger(this.getClass());

	public abstract BaseRepository<T, Long> getRepository();

	@PostConstruct
	private void init() {
		log.debug("Starting {}.", this.getClass().getName());
	}

	@Override
	public void createAll(final T... entities) {
		for (final T entity : entities) {
			create(entity);
		}
	}
	@Override
	public T create(final T entity) {
		log.debug("Creating {}.", entity);
		return getRepository().create(entity);
	}

	@Override
	public void update(final T entity) {
		log.debug("Updating {}.", entity);
		getRepository().update(entity);
	}

	@Override
	public void delete(final T entity) {
		log.debug("Deleting {}.", entity);
		getRepository().delete(entity);
	}

	@Override
	public boolean exists(final T entity) {
		log.debug("Checking whether {} exists.", entity);
		return getRepository().exists(entity);
	}

	@Override
	public T get(final Long id) {
		log.debug("Retrieving entity with id {}.", id);
		return getRepository().get(id);
	}

	@Override
	public List<T> findAll() {
		log.debug("Retrieving all entities.");
		return getRepository().findAll();
	}

	@Override
	@Async("asyncExecutor")
	public CompletableFuture<List<T>> findAllAsync() {
		return CompletableFuture.completedFuture(getRepository().findAll());
	}

	@Override
	@Async("asyncExecutor")
	public void checkAsync() {
		log.debug("Running asynchronously.");
	}
}
