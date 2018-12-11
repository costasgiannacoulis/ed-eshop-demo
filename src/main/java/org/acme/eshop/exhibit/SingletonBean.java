package org.acme.eshop.exhibit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@ConditionalOnExpression("${mine.samplekey} == 15")
public class SingletonBean {
	@Autowired
	@Qualifier("P2")
	private SampleInterface sampleInterface;

	@PostConstruct
	private void init() {
		log.debug("Starting {}.", this.getClass().getName());
		log.debug("SampleInterface responded: {}", sampleInterface.sayMyName());
	}
}
