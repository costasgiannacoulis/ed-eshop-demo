package org.acme.eshop.exhibit;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;

@Component
@Profile("exhibit")
@Value
@Slf4j
@Scope("prototype")
public class PrototypeBean {

	@PostConstruct
	private void init() {
		log.debug("Starting {}.", this.getClass().getName());
	}
}
