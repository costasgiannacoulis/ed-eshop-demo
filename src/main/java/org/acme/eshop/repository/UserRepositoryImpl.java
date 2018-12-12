package org.acme.eshop.repository;

import org.acme.eshop.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryImpl extends AbstractRepository<User> implements UserRepository {
	@Value("${repository.user.sequence.start}")
	private Long seed;

	@Override
	public Long getSeed() {
		return seed;
	}
}
