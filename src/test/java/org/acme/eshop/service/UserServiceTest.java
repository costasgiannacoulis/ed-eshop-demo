package org.acme.eshop.service;

import java.util.List;

import org.acme.eshop.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserService userService;

	@Before
	public void init() {
		log.debug("Starting tests...");
	}

	@Test
	public void testUserCreation() {
		final User user1 = createUser("Costas", "SampleFullname", "costas.samplefullname@gmail.com");
		final User user2 = createUser("Vasilis", "SecondFullname", "vasilis.secondfullname@gmail.com");
		final User user3 = createUser("Panos", "ThirdFullname", "panos.thirdfullname@gmail.com");

		userService.createAll(user1, user2, user3);

		final List<User> usersFound = userService.findAll();
		assertEquals(usersFound.size(), 3);

		for (final User user : usersFound) {
			log.debug(user.toString());
		}

		user2.setLastname("ReplacedFullname");
		user2.setEmail("vasilis.replacedfullname@gmail.com");
		userService.update(user2);

		final List<User> usersFoundAgain = userService.findAll();
		for (final User user : usersFoundAgain) {
			log.debug(user.toString());
		}

		userService.delete(user3);
		assertEquals(userService.findAll().size(), 2);
	}

	@After
	public void destroy() {
		log.debug("Finishing test.");
	}

	private User createUser(final String firstname, final String lastname, final String email) {
		final User user = User.builder().firstname(firstname).lastname(lastname).email(email).build();
		return user;
	}
}
