package org.acme.eshop.service;

import org.acme.eshop.model.User;
import org.acme.eshop.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service("userService")
@RequiredArgsConstructor
public class UserServiceImpl extends AbstractService<User> implements UserService {
	private final UserRepository userRepository;

	@Override
	public JpaRepository<User, Long> getRepository() {
		return userRepository;
	}
}
