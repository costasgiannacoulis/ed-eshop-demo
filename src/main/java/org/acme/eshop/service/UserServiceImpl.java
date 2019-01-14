package org.acme.eshop.service;

import org.acme.eshop.model.User;
import org.acme.eshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl extends AbstractService<User> implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public JpaRepository<User, Long> getRepository() {
		return userRepository;
	}
}
