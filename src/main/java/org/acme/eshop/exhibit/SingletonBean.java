package org.acme.eshop.exhibit;

import javax.annotation.PostConstruct;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;

@Component
@Value
@Slf4j
@ConditionalOnExpression("${mine.samplekey} == 15")
public class SingletonBean {
	@PostConstruct
	private void init() {
		log.debug("Starting {}.", this.getClass().getName());
	}
}
