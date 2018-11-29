package org.acme.eshop.exhibit;

import javax.annotation.PostConstruct;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@ConditionalOnProperty(prefix = "mine", name = "samplekey", havingValue = "15")
public class ExperimentService {
	@PostConstruct
	private void init() {
		log.debug("Starting {}.", this.getClass().getName());
	}
}
