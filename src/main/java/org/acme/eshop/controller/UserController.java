package org.acme.eshop.controller;

import java.util.Locale;

import org.acme.eshop.model.User;
import org.acme.eshop.model.system.ApiResponse;
import org.acme.eshop.service.BaseService;
import org.acme.eshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController extends AbstractController<User> {
	@Autowired
	UserService userService;

	@Autowired
	MessageSource messageSource;

	@Override
	public BaseService<User, Long> getBaseService() {
		return userService;
	}

	@GetMapping(headers = "action=getLocalizedContentWithHeaders")
	public ResponseEntity<ApiResponse> getLocalizedContentWithHeaders(
		@RequestHeader(name = "Accept-Language", required = false) final Locale locale,
		@RequestParam(name = "token", required = true) final String token) {
		String content = "Default message in case resource bundle key is not found.";
		try {
			content = messageSource.getMessage(token, null, locale);
		} catch (final NoSuchMessageException nsmex) {

		}
		return new ResponseEntity<>(ApiResponse.<String>builder().data(content).build(), HttpStatus.OK);
	}
}
