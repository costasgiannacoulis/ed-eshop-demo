package org.acme.eshop.service;

import java.util.List;

import org.acme.eshop.model.BaseEntity;
import org.acme.eshop.repository.BaseRepository;

public abstract class AbstractService<T extends BaseEntity> implements BaseService<T, Long> {
	public abstract BaseRepository<T, Long> getRepository();

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

//	private T locate(final Long id) {
//		final Optional<T> entityOptional = getRepository().findById(id);
//
//		if (!entityOptional.isPresent()) {
//			entityOptional
//				.orElseThrow(() -> new ResourceNotFoundException(String.format("Object with id %d was not found.",
//																			   id)));
//		}
//		return entityOptional.get();
//	}
}
