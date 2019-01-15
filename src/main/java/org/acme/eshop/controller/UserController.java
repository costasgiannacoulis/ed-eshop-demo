package org.acme.eshop.controller;

import org.acme.eshop.model.User;
import org.acme.eshop.service.BaseService;
import org.acme.eshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController extends AbstractController<User> {
	@Autowired
	UserService userService;

	@Override
	public BaseService<User, Long> getBaseService() {
		return userService;
	}
}
