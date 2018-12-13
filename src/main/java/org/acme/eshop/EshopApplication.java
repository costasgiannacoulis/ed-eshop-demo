package org.acme.eshop;

import org.acme.eshop.exhibit.PrototypeBean;
import org.acme.eshop.exhibit.SingletonBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@PropertySource({
	"classpath:my.properties"
})
public class EshopApplication {
	public static void main(final String[] args) {
		final ConfigurableApplicationContext context = SpringApplication.run(EshopApplication.class, args);

		final SingletonBean singletonBean1 = context.getBean(SingletonBean.class);
		final SingletonBean singletonBean2 = context.getBean(SingletonBean.class);

		final PrototypeBean prototypeBean1 = context.getBean(PrototypeBean.class);
		final PrototypeBean prototypeBean2 = context.getBean(PrototypeBean.class);
	}
}
