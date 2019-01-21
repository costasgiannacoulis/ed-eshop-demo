package org.acme.eshop.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.annotation.PostConstruct;

import org.acme.eshop.model.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;

public abstract class AbstractService<T extends BaseEntity> implements BaseService<T, Long> {
	protected Logger log = LoggerFactory.getLogger(this.getClass());

	public abstract JpaRepository<T, Long> getRepository();

	@PostConstruct
	private void init() {
		log.trace("Starting {}.", this.getClass().getName());
	}

	@Override
	public void createAll(final T... entities) {
		for (final T entity : entities) {
			create(entity);
		}
	}

	@Override
	@CachePut(key = "#entity.getId()", cacheResolver = "dynamicCacheResolver")
	public T create(final T entity) {
		log.trace("Creating {}.", entity);
		return getRepository().save(entity);
	}

	@Override
	@CachePut(key = "#entity.getId()", cacheResolver = "dynamicCacheResolver")
	public void update(final T entity) {
		log.trace("Updating {}.", entity);
		getRepository().save(entity);
	}

	@Override
	@CacheEvict(key = "#id", cacheResolver = "dynamicCacheResolver")
	public void deleteById(final Long id) {
		final T entityFound = getRepository().getOne(id);
		log.trace("Deleting {}.", entityFound);
		getRepository().deleteById(id);
	}

	@Override
	@CacheEvict(key = "#entity.getId()", cacheResolver = "dynamicCacheResolver")
	public void delete(final T entity) {
		log.trace("Deleting {}.", entity);
		getRepository().delete(entity);
	}

	@Override
	public boolean exists(final T entity) {
		log.trace("Checking whether {} exists.", entity);
		return getRepository().existsById(entity.getId());
	}

	@Override
	@Cacheable(key = "#id", cacheResolver = "dynamicCacheResolver")
	public T get(final Long id) {
		log.trace("Retrieving entity with id {}.", id);
		/*
		 * T findOne(ID id) (name in the old API) / Optional<T> findById(ID id) (name in the new API) relies on
		 * EntityManager.find() that performs an entity eager loading.
		 *
		 * T getOne(ID id) relies on EntityManager.getReference() that performs an entity lazy loading. So to ensure
		 * the effective loading of the entity, invoking a method on it is required.
		 */
		return getRepository().findById(id).get();
	}

	@Override
	@Cacheable(cacheResolver = "dynamicCacheResolver")
	public List<T> findAll() {
		log.trace("Retrieving all entities.");
		return getRepository().findAll();
	}

	@Override
	@Async("asyncExecutor")
	@Cacheable(cacheResolver = "dynamicCacheResolver")
	public CompletableFuture<List<T>> findAllAsync() {
		return CompletableFuture.completedFuture(getRepository().findAll());
	}

	@Override
	@Async("asyncExecutor")
	public void checkAsync() {
		log.trace("Running asynchronously.");
	}

	protected final void simulateSlowService() {
		try {
			Thread.sleep(2000L);
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
	}
}
