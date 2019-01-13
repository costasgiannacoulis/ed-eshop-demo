package org.acme.eshop.exhibit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Profile("exhibit")
@Slf4j
@Primary
@Qualifier("P1")
public class SampleImplementationBean implements SampleInterface {
	@PostConstruct
	private void init() {
		log.debug("Starting {}.", this.getClass().getName());
	}

	@Override
	public String sayMyName() {
		return this.getClass().getName();
	}
}
