package org.acme.eshop.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.acme.eshop.model.BaseEntity;
import org.acme.eshop.repository.BaseRepository;
import org.slf4j.Logger;

public abstract class AbstractService<T extends BaseEntity> implements BaseService<T, Long> {
	private static final Logger log = org.slf4j.LoggerFactory.getLogger(AbstractService.class);

	public abstract BaseRepository<T, Long> getRepository();

	@PostConstruct
	private void init() {
		log.debug("Starting {}.", this.getClass().getName());
	}


	@Override
	public T create(final T entity) {
		return getRepository().create(entity);
	}

	@Override
	public void createAll(final T... entities) {
		for (final T entity : entities) {
			create(entity);
		}
	}

	@Override
	public void update(final T entity) {
		getRepository().update(entity);
	}

	@Override
	public void delete(final T entity) {
		getRepository().delete(entity);
	}

	@Override
	public boolean exists(final T entity) {
		return getRepository().exists(entity);
	}

	@Override
	public T get(final Long id) {
		return getRepository().get(id);
	}

	@Override
	public List<T> findAll() {
		return getRepository().findAll();
	}
}
