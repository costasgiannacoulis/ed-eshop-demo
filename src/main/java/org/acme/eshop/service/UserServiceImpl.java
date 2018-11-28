package org.acme.eshop.service;

import org.acme.eshop.model.User;
import org.acme.eshop.repository.BaseRepository;
import org.acme.eshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends AbstractService<User> implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public BaseRepository<User, Long> getRepository() {
		return userRepository;
	}
}
