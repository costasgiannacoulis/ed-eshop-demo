package org.acme.eshop.repository;

import java.util.concurrent.atomic.AtomicLong;

import org.acme.eshop.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryImpl extends AbstractRepository<User> implements UserRepository {
	private final AtomicLong SEQUENCE = new AtomicLong(1);

	@Override
	public AtomicLong getSequence() {
		return SEQUENCE;
	}
}
