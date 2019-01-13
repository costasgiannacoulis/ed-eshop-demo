package org.acme.eshop.exhibit;

import javax.annotation.PostConstruct;

import org.acme.eshop.service.UserServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Profile("exhibit")
@Slf4j
@ConditionalOnClass(UserServiceImpl.class)
@ConditionalOnMissingClass(value = {"org.acme.eshop.exhibit.ExperimentService.class"})
public class ConditionalService {
	@PostConstruct
	private void init() {
		log.debug("Starting {}.", this.getClass().getName());
	}
}
