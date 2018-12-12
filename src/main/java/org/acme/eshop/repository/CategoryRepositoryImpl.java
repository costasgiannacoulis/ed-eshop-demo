package org.acme.eshop.repository;

import org.acme.eshop.model.Category;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CategoryRepositoryImpl extends AbstractRepository<Category> implements CategoryRepository {
	@Value("${repository.category.sequence.start}")
	private Long seed;

	@Override
	public Long getSeed() {
		return seed;
	}
}
