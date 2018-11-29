package org.acme.eshop.exhibit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Qualifier("P2")
public class AnotherSampleImplementationBean implements SampleInterface {
	@PostConstruct
	private void init() {
		log.debug("Starting {}.", this.getClass().getName());
	}

	public String sayMyName() {
		return this.getClass().getName();
	}
}
